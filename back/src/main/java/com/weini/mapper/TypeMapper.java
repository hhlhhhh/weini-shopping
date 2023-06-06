package com.weini.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weini.POJO.Do.Type;

import java.util.List;

public interface TypeMapper extends BaseMapper<Type> {

    public List<Type> getCommodityType(String commodityId);

}
