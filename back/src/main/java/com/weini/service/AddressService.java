package com.weini.service;


import com.weini.POJO.Do.Address;
import com.weini.common.response.Result;

import javax.servlet.http.HttpServletRequest;

public interface AddressService {

    public Result getAllUserAddress(HttpServletRequest request);

    public Result addUserAddress(Address address,HttpServletRequest request);

    public Result deleteUserAddress(String id,HttpServletRequest request);
}
