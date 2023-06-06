package com.weini.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weini.POJO.Do.Commodity;
import com.weini.POJO.Do.CommodityMedia;
import com.weini.POJO.Do.Shop;
import com.weini.POJO.server.ThreadLocalVar;
import com.weini.common.response.Result;
import com.weini.mapper.ShopMapper;
import com.weini.service.CommodityMediaService;
import com.weini.service.ShopService;
import com.weini.utils.MyThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Resource
    ShopMapper shopMapper;

    @Resource
    CommodityMediaService commodityMediaService;

    @Override
    public Result getShopMes(String shop_id) {
        Shop shop = shopMapper.selectById(shop_id);
        if(shop==null)return Result.fail("无此店铺！");

        ThreadLocalVar var = MyThreadLocal.getVar();
        String userId = var.getUserId();

        if(shop.getUser_id().equals(userId)){
            return Result.succ(shop);
        }
        shop.setUpdate_time(null);
        shop.setUser_id(null);
        return Result.succ(shop);
    }

    @Override
    public Result getSelledTop(String id,int n) {
        List<Commodity> selledTop = shopMapper.getSelledTop(id, n);
        selledTop.forEach(e->{
            e.setMediaUrlList(commodityMediaService.list(new QueryWrapper<CommodityMedia>().eq("commodity_id",e.getId())));
        });
        return Result.succ(selledTop);
    }

}
