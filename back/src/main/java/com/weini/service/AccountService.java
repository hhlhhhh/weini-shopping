package com.weini.service;

import com.weini.POJO.Do.User;
import com.weini.common.response.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AccountService {

    Result loginByPassword(String phone,String password,HttpServletRequest request,HttpServletResponse response);

    Result loginByEmail(String email, String code, HttpServletResponse response);

    Result getLoginEmailCode(String email);

    Result getRegisterEmailCode(String email);

    User getUserById(String id);    //获取用户信息

    Result register(User user, String code, HttpServletRequest req,HttpServletResponse rep);
}
