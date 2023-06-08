package com.weini.service;

import com.weini.POJO.Do.SccChoice;
import com.weini.POJO.Do.ShoppingCart;
import com.weini.POJO.Do.ShoppingCartCommodity;
import com.weini.common.response.Result;

import java.util.List;

public interface ShoppingCartService {

    public Result getShoppingCartCommoditiesList(Integer current,Integer size);

    public Result addCommoditiesToShoppingCart(List<ShoppingCartCommodity> list, List<List<SccChoice>> choiceList);

    public Result removeCommoditiesFromShoppingCart(List<String> ids);

    public Result updateShoppingCart(List<ShoppingCartCommodity> list,String id);

    public Result getShoppingCartTypeChoice(String sc_Commodity_id); //传递商品的id
}
