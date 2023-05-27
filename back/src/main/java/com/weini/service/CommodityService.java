package com.weini.service;

import com.weini.POJO.Do.Commodity;
import com.weini.common.response.Result;

public interface CommodityService {

    public Result getCommoditiesByPage(Integer page,Integer pageSize);

    public Result getCommodityDetail(String id);

    public Result addCommodity(Commodity commodity);

    public Result deleteCommodity(String id);

    public Result updateCommodity(Commodity commodity);

}
