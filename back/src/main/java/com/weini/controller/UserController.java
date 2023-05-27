package com.weini.controller;

import com.weini.POJO.DTO.UserDTO;
import com.weini.POJO.Do.User;
import com.weini.common.annotation.WeiniPermissionAnnotation;
import com.weini.common.response.Result;
import com.weini.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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

    @RequiresAuthentication
    @PutMapping("/charge")
    public Result chargeMoney(@RequestParam String id,@RequestParam Integer money){
        return userService.chargeMoney(id,money);
    }

    @PutMapping("/update")
    public Result updateUserMes(@RequestBody UserDTO userDTO){
        return userService.updateUserMes(userDTO);
    }

    @PutMapping("/register/merchant")
    public Result registerToMerchant(@RequestParam("id") String id){
        return userService.registerToMerchant(id);
    }

    @DeleteMapping("/del/{id}")
    public Result deleteUser(@PathVariable("id")String id){
        return userService.deleteUser(id);
    }


    @PutMapping("/pwd/{type}")
    public Result updatePassword(@PathVariable("type")String type,
                                 @RequestParam String arg1,
                                 @RequestParam String arg2,
                                 @RequestParam String arg3,
                                 HttpServletResponse response){
        if("password".equals(type)){
            return userService.changePasswordByOldPwd(arg1,arg2,arg3,response);
        }else if("email".equals(type)){
            return userService.changePasswordByEmail(arg1,arg2,arg3,response);
        }
        return Result.fail("参数错误！");
    }

    @WeiniPermissionAnnotation(param = "user")
    @GetMapping("/test")
    public String test(@RequestBody User user){
        return "555";
    }

}
