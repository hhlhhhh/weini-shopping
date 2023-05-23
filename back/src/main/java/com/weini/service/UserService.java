package com.weini.service;

import com.weini.POJO.DTO.UserDTO;
import com.weini.common.response.Result;

public interface UserService {

    Result getUserMesById(String id);   //通过id获取用户信息

    Result getUserList(Integer page,Integer pageSize);  //获取用户列表

    Result updateUserMes(UserDTO userDTO);      //更新用户信息

    Result chargeMoney(String id,Integer money);    //充值账户金钱

    Result registerToMerchant(String id);   //注册成商家

    Result deleteUser(String id);   //删除用户

    Result changePassword(String id,String password);   //修改密码

}
