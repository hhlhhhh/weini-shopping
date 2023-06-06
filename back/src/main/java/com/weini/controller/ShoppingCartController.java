package com.weini.controller;

import com.weini.POJO.Do.SccChoice;
import com.weini.POJO.Do.ShoppingCartCommodity;
import com.weini.common.response.Result;
import com.weini.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @DeleteMapping("/remove")
    public Result removeCommodities(@RequestParam List<String> ids){
        return shoppingCartService.removeCommoditiesFromShoppingCart(ids,"");
    }

    @GetMapping("/list")
    public Result getSHoppingCartList(@RequestParam Integer current,@RequestParam(defaultValue = "5") Integer size){
        return shoppingCartService.getShoppingCartCommoditiesList(current,size);
    }

    @GetMapping("/type-choice/list")
    public Result getShoppingCartTypeChoice(@RequestParam String id){
        return shoppingCartService.getShoppingCartTypeChoice(id);
    }


    @PostMapping("/add")
    public Result addCommoditiesToShoppingCart(@RequestBody List<Map<String ,Object>> list ){
        List<ShoppingCartCommodity> shoppingCartCommodityList = new ArrayList<>();
        List<List<SccChoice>> sccChoiceList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Map<String,Object> e = list.get(i);
            shoppingCartCommodityList.add(new ShoppingCartCommodity().setCommodity_id((String) e.get("commodity_id"))
                    .setCount((Integer) e.get("count")));
            List<String> types = (List<String>) e.get("types");
            List<String> choices = (List<String>) e.get("choices");
            List<SccChoice> sccChoices = new ArrayList<>();
            for (int j = 0; j < types.size(); j++) {
                sccChoices.add(new SccChoice().setChoice_id(types.get(i))
                        .setType_id(choices.get(j))
                        .setSc_commodity_id((String) e.get("commodity_id")));
            }
            sccChoiceList.add(sccChoices);
        }

        return shoppingCartService.addCommoditiesToShoppingCart(shoppingCartCommodityList,sccChoiceList);
    }

    @GetMapping("/test")
    public String test(){
        return "6565";
    }

}
