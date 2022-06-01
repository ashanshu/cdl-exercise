package com.cdl.price.pojo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class ItemPrice {

    /**
     * SKU
     * */
    @Getter
    @Setter
    private String sku;

    /**
     * Unit Price
     * */
    @Getter
    @Setter
    private BigDecimal price;

    /**
     * Buy N for a Special Price
     * */
    @Getter
    @Setter
    private Integer nFor;

    /**
     * The Special Price of N
     * */
    @Getter
    @Setter
    private BigDecimal nForPrice;
}
