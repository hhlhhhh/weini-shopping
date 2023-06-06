package com.weini.service.impl;


import com.weini.POJO.Do.Type;
import com.weini.common.response.Result;
import com.weini.mapper.TypeMapper;
import com.weini.service.TypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Resource
    TypeMapper typeMapper;

    @Override
    public Result getCommodityType(String id) {
        List<Type> commodityType = typeMapper.getCommodityType(id);
        return Result.succ(commodityType);
    }




}
