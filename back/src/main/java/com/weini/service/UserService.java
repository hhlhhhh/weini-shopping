package com.weini.service;

import com.weini.POJO.Do.User;
import com.weini.common.response.Result;

import javax.servlet.http.HttpServletResponse;

public interface UserService {

    Result loginByPassword(String phone,String password,HttpServletResponse response);

    Result loginByEmail(String email, String code, HttpServletResponse response);

    Result getEmailCode(String email);

    User getUserById(String id);    //获取用户信息
}
