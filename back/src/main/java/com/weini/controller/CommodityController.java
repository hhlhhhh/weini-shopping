package com.weini.controller;

import com.weini.POJO.Do.Commodity;
import com.weini.common.annotation.WeiniPermissionAnnotation;
import com.weini.common.response.Result;
import com.weini.service.CommodityService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/commodity")
public class CommodityController {

    @Resource
    CommodityService commodityService;


    @GetMapping("/list")
    public Result getCommoditiesByPage(@RequestParam Integer page,@RequestParam(required = false,defaultValue = "10") Integer pageSize){
        return commodityService.getCommoditiesByPage(page,pageSize);
    }

    @GetMapping("/detail/{id}")
    public Result getCommodityDetail(@PathVariable("id") String id){
        return commodityService.getCommodityDetail(id);
    }

    @RequiresRoles({"merchant"})
    @WeiniPermissionAnnotation(param = "commodity")
    @PostMapping("/add")
    public Result addCommodity(Commodity commodity){
        return commodityService.addCommodity(commodity);
    }

    @PutMapping("/update")
    public Result updateCommodity(Commodity commodity){
        return commodityService.updateCommodity(commodity);
    }

    @WeiniPermissionAnnotation(param = "userId")
    @DeleteMapping("/del")
    public Result deleteCommodity(@RequestParam String id,@RequestParam String userId){
        return commodityService.deleteCommodity(id);
    }

}
