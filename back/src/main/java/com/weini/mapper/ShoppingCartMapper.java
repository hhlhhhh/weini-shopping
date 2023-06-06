package com.weini.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weini.POJO.Do.SccChoice;
import com.weini.POJO.Do.ShoppingCart;
import com.weini.POJO.Do.ShoppingCartCommodity;

import java.util.List;


public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

    public ShoppingCart getShoppingCartList(Integer current, Integer size, String id);

    public int addCommodities(ShoppingCartCommodity shoppingCartCommodity);

    public int removeCommoditiesFromShoppingCart(String shopping_cart_id,String commodity_id);

    public int updateShoppingCart(ShoppingCartCommodity shoppingCartCommodity);

    public List<SccChoice> getShoppingCartTypeChoice(String id);

}
