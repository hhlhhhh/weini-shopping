package com.weini.service.impl;

import com.auth0.jwt.interfaces.Claim;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.weini.POJO.Do.Address;
import com.weini.common.exception.ExecutionFailureException;
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

        if("1".equals(address.getState())){
            int cRes = addressMapper.cancelDefaultAddress();
            if(cRes == 0){
                throw ExecutionFailureException.Builder("新增地址失败！不能更改默认地址！");
            }
        }
        address.setUser_id(id);
        address.setId(RandomId.idConstruct());  //生成地址唯一id

        addressMapper.insert(address);

        return Result.succ("删除成功！");
    }

    @Override
    public Result deleteUserAddress(String id, HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String userId = JwtFactory.getMes(authorization).get("id").asString();

        addressMapper.delete(new QueryWrapper<Address>().eq("user_id",userId).eq("id",id));
        return Result.succ("删除成功！");
    }
}
