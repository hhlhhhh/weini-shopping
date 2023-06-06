package com.weini.controller;


import com.weini.common.response.Result;
import com.weini.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {


    @Autowired
    CategoryService categoryService;

    @GetMapping("/commodity")
    public Result getCategoryByCommodityId(@RequestParam String id){
        return categoryService.getCategoryByCommodityId(id);
    }

}
