package com.weini.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weini.POJO.Do.User;

public interface UserMapper extends BaseMapper<User> {

    public int chargeMoney(String id,int money);

}
