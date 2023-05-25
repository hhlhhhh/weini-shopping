package com.weini.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weini.POJO.Do.Address;

public interface AddressMapper extends BaseMapper<Address> {

    public int cancelDefaultAddress();  //取消默认地址

    public int setDefaultAddress(String id);    //设置默认地址

}
