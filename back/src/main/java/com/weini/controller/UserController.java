package com.weini.controller;

import com.weini.common.response.Result;
import com.weini.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @GetMapping("/detail/{id}")
    public Result getUserMesById(@PathVariable("id") String id){
        return userService.getUserMesById(id);
    }

    @GetMapping("/list")
    public Result getUserList(@RequestParam Integer page,@RequestParam(required = false,defaultValue = "10") Integer pageSize){
        return userService.getUserList(page,pageSize);
    }

}
