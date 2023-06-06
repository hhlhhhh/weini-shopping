package com.weini.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weini.POJO.Do.Category;
import com.weini.POJO.Do.CategoryCommodity;

import java.util.List;

public interface CategoryMapper extends BaseMapper<Category> {

    public List<CategoryCommodity> getCategoryListByCommodity(String id);


}
