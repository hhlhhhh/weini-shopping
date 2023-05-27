package com.weini.service;


import com.weini.POJO.Do.Address;
import com.weini.common.response.Result;

import javax.servlet.http.HttpServletRequest;

public interface AddressService {

    public Result getAllUserAddress(String id);    //获取地址列表

    public Result addUserAddress(Address address);   //添加地址

    public Result deleteUserAddress(Address address);  //删除地址

    public Result getAddressDetail(Address address);  //获取地址详情

    public Result setDefaultAddress(Address address);     //设置默认地址

}
