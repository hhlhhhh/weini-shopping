package com.weini.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weini.POJO.Do.*;
import com.weini.common.exception.ParameterErrorException;
import com.weini.common.response.Result;
import com.weini.mapper.*;
import com.weini.service.CommodityMediaService;
import com.weini.service.ShoppingCartService;
import com.weini.utils.MyThreadLocal;
import com.weini.utils.RandomId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

    @Resource
    ShoppingCartMapper shoppingCartMapper;

    @Resource
    CommodityMapper commodityMapper;

    @Resource
    CommodityMediaService commodityMediaService;

    @Resource
    TypeMapper typeMapper;

    @Resource
    TypeChoiceMapper typeChoiceMapper;

    @Resource
    SccChoiceMapper sccChoiceMapper;

    @Resource
    UserMapper userMapper;

    @Override
    public Result getShoppingCartCommoditiesList(Integer current,Integer size) {
        String userId = MyThreadLocal.getVar().getUserId();
        ShoppingCart cart = shoppingCartMapper.selectOne(new QueryWrapper<ShoppingCart>().eq("user_id", userId));
        ShoppingCart shoppingCart = shoppingCartMapper.getShoppingCartList(current-1,size,cart.getId());

        for (ShoppingCartCommodity commodity : shoppingCart.getCommodities()) {
            commodity.setCommodity(commodityMapper.selectById(commodity.getCommodity_id()));
            commodity.getCommodity().setMediaUrlList(commodityMediaService.list(new QueryWrapper<CommodityMedia>().eq("commodity_id",commodity.getCommodity_id())));
        }

        return Result.succ(shoppingCart);
    }

    @Transactional
    @Override
    public Result addCommoditiesToShoppingCart(List<ShoppingCartCommodity> list,List<List<SccChoice>> choiceList) {
        String id = MyThreadLocal.getVar().getUserId();
        ShoppingCart shoppingCart = shoppingCartMapper.selectOne(new QueryWrapper<ShoppingCart>().eq("user_id", id).select("id"));
        for (int i = 0;i < list.size();i++) {
            ShoppingCartCommodity shoppingCartCommodity = list.get(i);
            boolean exists = commodityMapper.exists(new QueryWrapper<Commodity>().eq("id", shoppingCartCommodity.getCommodity_id()));
            shoppingCartCommodity.setShopping_cart_id(shoppingCart.getId());
            if(exists){
                if(shoppingCartCommodity.getCount()<=0)throw ParameterErrorException.Builder("商品数量不能为空！");
                shoppingCartCommodity.setId(RandomId.idConstruct());
                shoppingCartCommodity.setCreate_time(new Date());
                shoppingCartCommodity.setUpdate_time(new Date());
                shoppingCartMapper.addCommodities(shoppingCartCommodity);

                List<SccChoice> choices = choiceList.get(i);

                for (int j = 0; j < choices.size(); j++) {
                    SccChoice sccChoice = choices.get(j);
                    sccChoice.setId(RandomId.idConstruct());
                    sccChoiceMapper.insert(sccChoice);
                }
            }
        }
        return Result.succ("添加成功！");
    }

    @Override
    public Result removeCommoditiesFromShoppingCart(List<String> list,String id) {
        ShoppingCart shoppingCart = shoppingCartMapper.selectOne(new QueryWrapper<ShoppingCart>().eq("user_id", id).select("id"));
        list.forEach(e->{
            shoppingCartMapper.removeCommoditiesFromShoppingCart(shoppingCart.getId(),e);
        });
        return Result.succ("删除成功！");
    }

    @Override
    public Result updateShoppingCart(List<ShoppingCartCommodity> list,String id) {
        ShoppingCart shoppingCart = shoppingCartMapper.selectOne(new QueryWrapper<ShoppingCart>().eq("user_id", id).select("id"));
        list.forEach(e->{
            e.setShopping_cart_id(shoppingCart.getId());
            shoppingCartMapper.updateShoppingCart(e);
        });
        return Result.succ("添加成功！");
    }

    @Override
    public Result getShoppingCartTypeChoice(String sc_Commodity_id) {
        List<SccChoice> shoppingCartTypeChoice = shoppingCartMapper.getShoppingCartTypeChoice(sc_Commodity_id);

        shoppingCartTypeChoice.forEach(e->{
            e.setType(typeMapper.selectById(e.getType_id()));
            e.setTypeChoice(typeChoiceMapper.selectById(e.getChoice_id()));
        });

        return Result.succ(shoppingCartTypeChoice);
    }

}
