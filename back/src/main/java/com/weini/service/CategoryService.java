package com.weini.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weini.common.response.Result;

public interface CategoryService {

    public Result getCategoryByCommodityId(String id);


}
