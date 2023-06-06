package com.weini.service;

import com.weini.POJO.Do.Shop;
import com.weini.common.response.Result;

public interface ShopService {

    public Result getShopMes(String shop_id);

    public Result getSelledTop(String id,int n);

}
