package com.weini.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.weini.POJO.Do.Address;
import com.weini.common.exception.MissedParameterException;
import com.weini.common.response.Result;
import com.weini.common.response.State;
import com.weini.mapper.AddressMapper;
import com.weini.service.AddressService;
import com.weini.utils.MyThreadLocal;
import com.weini.utils.RandomId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    AddressMapper addressMapper;

    @Override
    public Result getAllUserAddress(String id) {
        List<Address> addressList = addressMapper.selectList(new QueryWrapper<Address>().eq("user_id", id));
        return Result.succ(addressList);
    }

    @Transactional
    @Override
    public Result addUserAddress(Address address) {
        int cRes = 1;

        if("1".equals(address.getState())){
            addressMapper.cancelDefaultAddress(address.getUser_id());
            if(addressMapper.selectOne(new QueryWrapper<Address>().eq("state","1").eq("user_id",address.getUser_id()))!=null){    //判断是否取消默认地址是否成功
                cRes = 0;
                address.setState("0");
            }
        }

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
    public Result deleteUserAddress(String id) {
        String userId = MyThreadLocal.getVar().getUserId();
        int res = addressMapper.delete(new QueryWrapper<Address>().eq("user_id", userId ).eq("id", id));
        if(res==0){
            return Result.fail(State.ERROROPERATE,"删除地址失败！");
        }
        return Result.succ("删除地址成功！");
    }

    @Override
    public Result getAddressDetail(Address address) {
        if(address.getId()==null){
            throw MissedParameterException.Builder("未指定地址id！");
        }
        Address res = addressMapper.selectOne(new QueryWrapper<Address>().eq("id", address.getId()).eq("user_id", address.getUser_id()));

        return Result.succ(res);
    }

    @Transactional
    @Override
    public Result setDefaultAddress(Address address) {
        if(address.getId()==null){
            throw MissedParameterException.Builder("未指定地址id！");
        }
        String userId = MyThreadLocal.getVar().getUserId();
        addressMapper.cancelDefaultAddress(userId);
        addressMapper.setDefaultAddress(address.getId());

        return Result.succ("默认地址更改成功！");
    }

    @Override
    public Result updateAddress(Address address) {
        String userId = MyThreadLocal.getVar().getUserId();
        address.setUpdate_time(new Date());
        address.setDel_flag("0");
        address.setState("0");
        addressMapper.update(address,new UpdateWrapper<Address>().eq("id",address.getId()).eq("user_id",userId));
        return Result.succ("修改地址成功！");
    }


}
