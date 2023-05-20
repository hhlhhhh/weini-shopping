package com.weini.controller;

import com.weini.common.response.Result;
import com.weini.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login/{type}")
    public Result login(@PathVariable("type")String type,
                        @RequestParam("arg1")String arg1,
                        @RequestParam("arg2")String arg2,
                        HttpServletResponse response){
        if("email".equals(type))return userService.loginByEmail(arg1,arg2,response);
        else if("phone".equals(type))return userService.loginByPassword(arg1,arg2,response);
        else return Result.fail("登录类型错误");
    }

    @GetMapping("/email/code")
    public Result getEmailCode(@RequestParam("email")String email){
        if(Objects.isNull(email))return Result.fail("手机号不能为空");
        return userService.getEmailCode(email);
    }
}
