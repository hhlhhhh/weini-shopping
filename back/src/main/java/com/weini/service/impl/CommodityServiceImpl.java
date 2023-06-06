package com.weini.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weini.POJO.Do.Commodity;
import com.weini.POJO.Do.CommodityMedia;
import com.weini.common.exception.ParameterErrorException;
import com.weini.common.response.Result;
import com.weini.mapper.CommodityMapper;
import com.weini.mapper.TypeMapper;
import com.weini.service.CommodityMediaService;
import com.weini.service.CommodityService;
import com.weini.service.TypeService;
import com.weini.utils.RandomId;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity> implements CommodityService {

    @Resource
    CommodityMapper commodityMapper;

    @Resource
    CommodityMediaService commodityMediaService;

    @Resource
    TypeMapper typeMapper;

    @Override
    public Result getCommoditiesByPage(Integer current, Integer size) {
        if(current<1||size<1)throw ParameterErrorException.Builder("参数应为整数！");

        Page<Commodity> commodityPage = new Page<>(current,size,true);
        Page<Commodity> selectPage = commodityMapper.selectPage(commodityPage, new QueryWrapper<Commodity>());
        if(selectPage.getCurrent()!=current){        //如果查不到返回空
            return Result.succ(new ArrayList<>());
        }
        selectPage.getRecords().forEach(e->{
            e.setMediaUrlList(commodityMediaService.list(new QueryWrapper<CommodityMedia>().eq("commodity_id",e.getId()))); //获取图片列表
            e.setTypeList(typeMapper.getCommodityType(e.getId()));  //获取分类列表
        });
        return Result.succ(selectPage);
    }

    @Override
    public Result getCommodityDetail(String id) {
        return Result.succ(commodityMapper.selectById(id));
    }

    @Override
    public Result addCommodity(Commodity commodity) {
        Optional<Commodity> optional = Optional.of(commodity);
        Commodity data = new Commodity();

        optional.map(Commodity::getName)
                .filter(e->e.length()<=50)
                .ifPresentOrElse(data::setName,() -> {
                    throw ParameterErrorException.Builder("商品名不能超过50个字！");
                });
        optional.map(Commodity::getTag).filter(e->{
            String[] tagList = e.split("\\.");
            if(tagList.length>3)return false;
            for (String s : tagList) {
                if (s.length() > 10) return false;
            }
            return true;
        }).ifPresentOrElse(data::setTag,()->{
            throw ParameterErrorException.Builder("标签格式错误！");
        });

        optional.map(Commodity::getIntroduce).filter(e->e.length()<=200)
                .ifPresentOrElse(data::setIntroduce,() -> {
                    throw ParameterErrorException.Builder("商品介绍不能超过50个字！");
                });
        optional.map(Commodity::getInventory).filter(e->e>0)
                .ifPresentOrElse(data::setInventory,() -> {
                    throw ParameterErrorException.Builder("商品库存不能为负数！");
                });
        optional.map(Commodity::getPrice).filter(e->e>0)
                .ifPresentOrElse(data::setPrice,() -> {
                    throw ParameterErrorException.Builder("商品价格不能为负数！");
                });

        data.setId(RandomId.idConstruct());
        data.setShop_id(commodity.getShop_id());
        commodityMapper.insert(commodity);
        return Result.succ("新增商品成功！");
    }

    @Override
    public Result deleteCommodity(String id) {
        if(0 == commodityMapper.deleteById(id)){
            return Result.fail("删除失败！");
        }
        return Result.succ("删除成功！");
    }

    @Override
    public Result updateCommodity(Commodity commodity) {
        if(commodity.getId()==null){
            throw ParameterErrorException.Builder("未指定需要更新的商品！");
        }

        Optional<Commodity> optional = Optional.of(commodity);
        Commodity data = new Commodity();
        optional.map(Commodity::getName)
                .ifPresent(name->Optional.of(name)
                        .filter(e->e.length()<=50)
                        .ifPresentOrElse(data::setName,() -> {
                            throw ParameterErrorException.Builder("商品名不能超过50个字！");
                        }));
        optional.map(Commodity::getTag)
                .ifPresent(tag-> Optional.of(tag).filter(e->{
                    String[] tagList = e.split("\\.");
                    if(tagList.length>3)return false;
                    for (String s : tagList) {
                        if (s.length() > 10) return false;
                    }
                    return true;
                }).ifPresentOrElse(data::setTag,()->{
                    throw ParameterErrorException.Builder("标签格式错误！");
                }));

        optional.map(Commodity::getIntroduce)
                .ifPresent(introduce->Optional.of(introduce)
                        .filter(e->e.length()<=200)
                        .ifPresentOrElse(data::setIntroduce,() -> {
                            throw ParameterErrorException.Builder("商品介绍不能超过50个字！");
                        }));

        optional.map(Commodity::getInventory).ifPresent(inventory->Optional.of(inventory)
                .filter(e->e>0)
                .ifPresentOrElse(data::setInventory,() -> {
                    throw ParameterErrorException.Builder("商品库存不能为负数！");
                }));

        optional.map(Commodity::getPrice).ifPresent(price->Optional.of(price)
                .filter(e->e>0)
                .ifPresentOrElse(data::setPrice,() -> {
                    throw ParameterErrorException.Builder("商品价格不能为负数！");
                }));

        int res = commodityMapper.update(commodity,new QueryWrapper<Commodity>().eq("id",commodity.getId()).eq("shop_id",commodity.getId()));
        if(0 == res){
            return Result.succ("更新失败！");
        }
        return Result.succ("更新商品成功！");
    }
}
