package com.weini.service;


import com.weini.POJO.Do.Address;
import com.weini.common.response.Result;

import javax.servlet.http.HttpServletRequest;

public interface AddressService {

    public Result getAllUserAddress(HttpServletRequest request);    //获取地址列表

    public Result addUserAddress(Address address,HttpServletRequest request);   //添加地址

    public Result deleteUserAddress(String id,HttpServletRequest request);  //删除地址

    public Result getAddressDetail(String id,HttpServletRequest request);  //获取地址详情

    public Result setDefaultAddress(String id,HttpServletRequest request);     //设置默认地址

}
