package com.weini.controller;

import com.weini.common.response.Result;
import com.weini.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    ShopService shopService;

    @GetMapping("/detail")
    public Result getShopMes(@RequestParam("id") String shop_id){
        return shopService.getShopMes(shop_id);
    }

    @GetMapping("/sell/top")
    public Result getSelledTop(@RequestParam("id") String id,@RequestParam int n){
        return shopService.getSelledTop(id,n);
    }

}