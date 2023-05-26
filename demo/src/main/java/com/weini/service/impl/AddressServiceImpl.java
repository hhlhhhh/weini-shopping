package com.weini.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weini.POJO.Do.Address;
import com.weini.common.response.Result;
import com.weini.common.response.State;
import com.weini.mapper.AddressMapper;
import com.weini.service.AddressService;
import com.weini.utils.JwtFactory;
import com.weini.utils.RandomId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    AddressMapper addressMapper;

    @Override
    public Result getAllUserAddress(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String id = JwtFactory.getMes(authorization).get("id").asString();  //获取用户id

        List<Address> addressList = addressMapper.selectList(new QueryWrapper<Address>().eq("user_id", id));

        return Result.succ(addressList);
    }

    @Transactional
    @Override
    public Result addUserAddress(Address address, HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String id = JwtFactory.getMes(authorization).get("id").asString();
        int cRes = 1;

        if("1".equals(address.getState())){
            addressMapper.cancelDefaultAddress();
            if(addressMapper.selectOne(new QueryWrapper<Address>().eq("state","1").eq("user_id",id))!=null){    //判断是否取消默认地址是否成功
                cRes = 0;
                address.setState("0");
            }
        }

        address.setUser_id(id);
        address.setId(RandomId.idConstruct());  //生成地址唯一id

        int res = addressMapper.insert(address);
        if(res==0){
            return Result.fail(State.ERROROPERATE,"添加失败！");
        }
        if(cRes == 0){
            Result.fail(State.ERROROPERATE,"设置默认地址失败！");    //添加地址成功但是默认地址设置失败。
        }
        return Result.succ("添加地址成功！");
    }

    @Override
    public Result deleteUserAddress(String id, HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String userId = JwtFactory.getMes(authorization).get("id").asString();

        int res = addressMapper.delete(new QueryWrapper<Address>().eq("user_id", userId).eq("id", id));
        if(res==0){
            return Result.fail(State.ERROROPERATE,"删除地址失败！");
        }
        return Result.succ("删除地址成功！");
    }

    @Override
    public Result getAddressDetail(String id,HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String userId = JwtFactory.getMes(authorization).get("id").asString();

        Address address = addressMapper.selectOne(new QueryWrapper<Address>().eq("id", id).eq("user_id", userId));

        return Result.succ(address);
    }

    @Transactional
    @Override
    public Result setDefaultAddress(String id,HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String userId = JwtFactory.getMes(authorization).get("id").asString();

        addressMapper.cancelDefaultAddress();

        addressMapper.setDefaultAddress(id);

        return null;
    }


}
