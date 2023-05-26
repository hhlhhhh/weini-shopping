package com.weini.service;

import com.weini.POJO.Do.User;
import com.weini.common.response.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AccountService {

    Result loginByPassword(String phone,String password,HttpServletRequest request,HttpServletResponse response);   //密码登录

    Result loginByEmail(String email, String code, HttpServletResponse response);   //邮箱登录

    Result sendEmailCode(String email,String type);     //发送邮箱验证码

    User getUserById(String id);

    Result register(User user, String code, HttpServletRequest req,HttpServletResponse rep);    //注册
}
