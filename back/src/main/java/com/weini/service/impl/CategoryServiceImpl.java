package com.weini.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weini.POJO.Do.CategoryCommodity;
import com.weini.common.response.Result;
import com.weini.mapper.CategoryMapper;
import com.weini.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    CategoryMapper categoryMapper;


    @Override
    public Result getCategoryByCommodityId(String id) {

        List<CategoryCommodity> res = categoryMapper.getCategoryListByCommodity(id);

        return null;
    }
}
