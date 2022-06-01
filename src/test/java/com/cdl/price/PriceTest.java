package com.cdl.price;

import com.cdl.price.pojo.Trolley;
import com.cdl.price.service.PriceService;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PriceTest{

    @SpyBean
    PriceService priceService;

    /**
     * When running the test, system will wait for the command line input. So comment out the Application.java implements CommandLineRunner
     * Or click stop to terminate the test, then the test result comes out.
     * */
    @Test
    public void testPrice() {

        BigDecimal finalTotal = new BigDecimal(0);
        Gson gson = new Gson();
        List<Trolley> trolleys = priceService.addItem("A");
        String json = gson.toJson(trolleys);
        System.out.println("My Trolley: " + json);
        finalTotal = priceService.getFinalTotal(trolleys);
        System.out.println("Final Total: " + finalTotal);
        Assert.assertEquals(finalTotal, new BigDecimal(50));

        trolleys = priceService.addItem("A");
        json = gson.toJson(trolleys);
        System.out.println("My Trolley: " + json);
        finalTotal = priceService.getFinalTotal(trolleys);
        System.out.println("Final Total: " + finalTotal);
        Assert.assertEquals(finalTotal, new BigDecimal(100));

        trolleys = priceService.addItem("B");
        json = gson.toJson(trolleys);
        System.out.println("My Trolley: " + json);
        finalTotal = priceService.getFinalTotal(trolleys);
        System.out.println("Final Total: " + finalTotal);
        Assert.assertEquals(finalTotal, new BigDecimal(130));

        trolleys = priceService.addItem("C");
        json = gson.toJson(trolleys);
        System.out.println("My Trolley: " + json);
        finalTotal = priceService.getFinalTotal(trolleys);
        System.out.println("Final Total: " + finalTotal);
        Assert.assertEquals(finalTotal, new BigDecimal(150));

        trolleys = priceService.addItem("A");
        json = gson.toJson(trolleys);
        System.out.println("My Trolley: " + json);
        finalTotal = priceService.getFinalTotal(trolleys);
        System.out.println("Final Total: " + finalTotal);
        Assert.assertEquals(finalTotal, new BigDecimal(180));

        trolleys = priceService.addItem("A");
        json = gson.toJson(trolleys);
        System.out.println("My Trolley: " + json);
        finalTotal = priceService.getFinalTotal(trolleys);
        System.out.println("Final Total: " + finalTotal);
        Assert.assertEquals(finalTotal, new BigDecimal(230));

        trolleys = priceService.addItem("B");
        json = gson.toJson(trolleys);
        System.out.println("My Trolley: " + json);
        finalTotal = priceService.getFinalTotal(trolleys);
        System.out.println("Final Total: " + finalTotal);
        Assert.assertEquals(finalTotal, new BigDecimal(245));

        trolleys = priceService.addItem("C");
        json = gson.toJson(trolleys);
        System.out.println("My Trolley: " + json);
        finalTotal = priceService.getFinalTotal(trolleys);
        System.out.println("Final Total: " + finalTotal);
        Assert.assertEquals(finalTotal, new BigDecimal(265));

    }
}
