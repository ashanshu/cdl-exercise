package com.cdl.price.pojo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class Trolley {

    /**
     * SKU
     * */
    @Setter
    @Getter
    private String sku;

    /**
     * Unit of the SKU in Trolley
     * */
    @Setter
    @Getter
    private Integer unit;

    /**
     * Total price of the SKU in Trolley
     * */
    @Setter
    @Getter
    private BigDecimal amount;
}
