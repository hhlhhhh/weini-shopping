package com.weini.controller;

import com.weini.POJO.Do.Commodity;
import com.weini.common.response.Result;
import com.weini.service.CommodityService;
import com.weini.service.TypeService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/commodity")
public class CommodityController {

    @Resource
    CommodityService commodityService;

    @Resource
    TypeService typeService;


    @GetMapping("/list")
    public Result getCommoditiesByPage(@RequestParam Integer current,@RequestParam(required = false,defaultValue = "10") Integer size){
        return commodityService.getCommoditiesByPage(current,size);
    }

    @GetMapping("/detail/{id}")
    public Result getCommodityDetail(@PathVariable("id") String id){
        return commodityService.getCommodityDetail(id);
    }

    @RequiresRoles({"merchant"})
    @PostMapping("/add")
    public Result addCommodity(Commodity commodity){
        return commodityService.addCommodity(commodity);
    }

    @PutMapping("/update")
    public Result updateCommodity(Commodity commodity){
        return commodityService.updateCommodity(commodity);
    }

    @DeleteMapping("/del")
    public Result deleteCommodity(@RequestParam String id,@RequestParam String userId){
        return commodityService.deleteCommodity(id);
    }

    @GetMapping("/type/list")
    public Result getCommodityTypeOption(@RequestParam String id){
        return typeService.getCommodityType(id);
    }

}
