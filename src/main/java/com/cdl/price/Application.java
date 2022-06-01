package com.cdl.price;

import com.cdl.price.pojo.Trolley;
import com.cdl.price.service.PriceService;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

@EnableCaching
@SpringBootApplication
@ServletComponentScan
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Resource
    PriceService priceService;

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String sku = scanner.nextLine();
            // print the item details
            List<Trolley> trolleys = priceService.getPriceDetail(sku);
            Gson gson = new Gson();
            String json = gson.toJson(trolleys);
            System.out.println("My Trolley: " + json);
            // print the final total
            BigDecimal finalTotal = priceService.getFinalTotal(trolleys);
            System.out.println("Final Total: " + finalTotal);
        }
    }

}
