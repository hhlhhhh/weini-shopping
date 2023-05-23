package com.weini.controller;

import com.weini.POJO.Do.User;
import com.weini.common.response.Result;
import com.weini.common.response.State;
import com.weini.service.AccountService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/login/{type}")
    public Result login(@PathVariable("type")String type,
                        @RequestParam("arg1")String arg1,
                        @RequestParam("arg2")String arg2,
                        HttpServletRequest request,
                        HttpServletResponse response){
        if("email".equals(type))return accountService.loginByEmail(arg1,arg2,response);
        else if("phone".equals(type))return accountService.loginByPassword(arg1,arg2,request,response);
        else return Result.fail("登录类型错误");
    }

    @GetMapping("/email/code")
    public Result getEmailCode(@RequestParam("email")String email,@RequestParam String type){
        if(Objects.isNull(email))return Result.fail("邮箱不能为空");
        assert type!=null:"参数缺失！";
        if("login".equals(type))return accountService.getLoginEmailCode(email);
        else if("register".equals(type))return accountService.getRegisterEmailCode(email);
        else return Result.fail(State.ERR,"参数错误！");
    }

    @RequiresAuthentication
    @GetMapping("/test")
    public Result test(){
        return Result.succ("ooo");
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user,@RequestParam String code, HttpServletRequest req,HttpServletResponse rep){
        return accountService.register(user,code,req,rep);
    }
}
