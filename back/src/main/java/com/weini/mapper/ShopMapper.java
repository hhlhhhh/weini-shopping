package com.weini.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weini.POJO.Do.Commodity;
import com.weini.POJO.Do.Shop;

import java.util.List;

public interface ShopMapper extends BaseMapper<Shop> {

    public List<Commodity> getSelledTop(String id,int n);

}
