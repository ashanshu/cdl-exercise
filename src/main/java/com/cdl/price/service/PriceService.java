package com.cdl.price.service;

import com.cdl.price.model.ItemPriceData;
import com.cdl.price.pojo.ItemPrice;
import com.cdl.price.pojo.Trolley;
import com.cdl.price.utils.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PriceService {

    @Autowired
    ItemPriceData itemPriceData;

    public List<Trolley> getPriceDetail(String sku) {
        List<Trolley> trolleys = new ArrayList<>();
        if ("clear".equals(sku.toLowerCase())) {
            trolleys = this.clearItem();
        }
        else if ("-".equals(sku.substring(0,1))) {
            trolleys = this.removeItem(sku.substring(1));
        }
        else {
            trolleys = this.addItem(sku);
        }
        return trolleys;
    }

    public ItemPrice getItemPrice(String sku) {
        ItemPrice itemPrice = itemPriceData.getItemPriceData(sku);
        return itemPrice;
    }

    public List<Trolley> addItem(String sku) {
        // In production environment we can use Redis instead of Java Cache.
        // Hard code key 0001 for command line scan
        Object trolleyCache = CacheUtil.get("trolleyCache","0001");
        List<Trolley> trolleys = (ArrayList<Trolley>)trolleyCache;
        ItemPrice itemPrice = this.getItemPrice(sku);
        if (itemPrice.getSku() != null) {    // no item found
            int isNew = 1;
            if (trolleys != null) {     // found data in cache
                for (int i = 0; i < trolleys.size(); i++) {
                    if (sku.equals(trolleys.get(i).getSku())) {
                        int unit = trolleys.get(i).getUnit() + 1;
                        trolleys.get(i).setUnit(unit);
                        if (itemPrice.getNFor() != null) {
                            int nFor = itemPrice.getNFor();
                            BigDecimal price = itemPrice.getPrice();
                            BigDecimal nForPrice = itemPrice.getNForPrice();
                            if (nFor > 0) {
                                BigDecimal amount = nForPrice.multiply(new BigDecimal(unit / nFor)).add(price.multiply(new BigDecimal(unit % nFor)));
                                trolleys.get(i).setAmount(amount);
                            }
                        }
                        else {
                            BigDecimal amount = itemPrice.getPrice().multiply(new BigDecimal(unit));
                            trolleys.get(i).setAmount(amount);
                        }
                        isNew = 0;
                    }
                }
            }
            else {
                trolleys = new ArrayList<>();
            }
            if (isNew == 1 || trolleys.size() == 0) {
                Trolley trolley = new Trolley();
                trolley.setSku(itemPrice.getSku());
                trolley.setUnit(1);
                trolley.setAmount(itemPrice.getPrice());
                trolleys.add(trolley);
            }

            Object trolleyCacheNew = (Object)trolleys;
            CacheUtil.put("trolleyCache", "0001", trolleyCacheNew);
        }
        else {
            if (trolleys == null) {
                trolleys = new ArrayList<>();
            }
        }
        return trolleys;
    }

    public List<Trolley> removeItem(String sku) {
        // In production environment we can use Redis instead of Java Cache.
        // Hard code key 0001 for command line scan
        Object trolleyCache = CacheUtil.get("trolleyCache","0001");
        List<Trolley> trolleys = (ArrayList<Trolley>)trolleyCache;
        ItemPrice itemPrice = this.getItemPrice(sku);
        if (itemPrice.getSku() != null) {    // no item found
            if (trolleys != null) {
                for (int i = 0; i < trolleys.size(); i++) {
                    if (sku.equals(trolleys.get(i).getSku())) {
                        int unit = trolleys.get(i).getUnit() - 1;
                        if (unit == 0) {
                            trolleys.remove(i);
                        }
                        else {
                            trolleys.get(i).setUnit(unit);
                            if (itemPrice.getNFor() != null) {
                                int nFor = itemPrice.getNFor();
                                BigDecimal price = itemPrice.getPrice();
                                BigDecimal nForPrice = itemPrice.getNForPrice();
                                if (nFor > 0) {
                                    BigDecimal amount = nForPrice.multiply(new BigDecimal(unit / nFor)).add(price.multiply(new BigDecimal(unit % nFor)));
                                    trolleys.get(i).setAmount(amount);
                                }
                            }
                            else {
                                BigDecimal amount = itemPrice.getPrice().multiply(new BigDecimal(unit));
                                trolleys.get(i).setAmount(amount);
                            }
                        }
                    }
                }
            }
            else {
                trolleys = new ArrayList<>();
            }
            Object trolleyCacheNew = (Object)trolleys;
            CacheUtil.put("trolleyCache", "0001", trolleyCacheNew);
        }
        return trolleys;
    }

    public List<Trolley> clearItem() {
        List<Trolley> trolleys = new ArrayList<>();
        Object trolleyCacheNew = (Object)trolleys;
        CacheUtil.put("trolleyCache", "0001", trolleyCacheNew);
        return trolleys;
    }

    public BigDecimal getFinalTotal(List<Trolley> trolleys) {
        BigDecimal finalTotal = new BigDecimal(0);
        for (int i = 0; i < trolleys.size(); i++) {
            finalTotal = finalTotal.add(trolleys.get(i).getAmount());
        }
        return finalTotal;
    }
}
