package com.cdl.price.controller;

import com.cdl.price.utils.ApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/price")
public class PriceController {

    //TODO: Rest api for pirce
    @RequestMapping(value = "add")
    public ApiResponse addItem() {
        return null;
    }

    @RequestMapping(value = "remove")
    public ApiResponse removeItem() {
        return null;
    }
}
