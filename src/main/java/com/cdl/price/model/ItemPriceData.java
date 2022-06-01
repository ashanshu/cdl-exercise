package com.cdl.price.model;

import com.cdl.price.pojo.ItemPrice;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemPriceData {

    /**
     * In production environment we can get item price form Database
     * */
    public ItemPrice getItemPriceData(String sku) {

        ItemPrice itemPriceA = new ItemPrice();
        itemPriceA.setSku("A");
        itemPriceA.setPrice(new BigDecimal(50));
        itemPriceA.setNFor(3);
        itemPriceA.setNForPrice(new BigDecimal(130));

        ItemPrice itemPriceB = new ItemPrice();
        itemPriceB.setSku("B");
        itemPriceB.setPrice(new BigDecimal(30));
        itemPriceB.setNFor(2);
        itemPriceB.setNForPrice(new BigDecimal(45));

        ItemPrice itemPriceC = new ItemPrice();
        itemPriceC.setSku("C");
        itemPriceC.setPrice(new BigDecimal(20));

        ItemPrice itemPriceD = new ItemPrice();
        itemPriceD.setSku("D");
        itemPriceD.setPrice(new BigDecimal(15));

        List<ItemPrice> items = new ArrayList<>();
        items.add(itemPriceA);
        items.add(itemPriceB);
        items.add(itemPriceC);
        items.add(itemPriceD);

        for (int i = 0; i < items.size(); i++) {
            if (sku.equals(items.get(i).getSku())) {
                return items.get(i);
            }
        }

        return new ItemPrice();
    }
}
