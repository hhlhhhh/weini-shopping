package com.weini.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weini.POJO.Do.User;

public interface UserMapper extends BaseMapper<User> {

    public int chargeMoney(String id,int money);    //充值

    public int registerToMerchant(String id);       //注册商户

    public int updatePassword(String id,String password);     //修改密码
}
