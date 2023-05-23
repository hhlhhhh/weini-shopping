package com.weini.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weini.POJO.DTO.UserDTO;
import com.weini.POJO.Do.User;
import com.weini.common.exception.ParameterErrorException;
import com.weini.common.response.Result;
import com.weini.common.response.State;
import com.weini.mapper.UserMapper;
import com.weini.service.UserService;
import com.weini.utils.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    RedisTemplate<Object,Object> template;

    @Autowired
    SendEmail sendEmail;

    @Resource
    UserMapper userMapper;


    @Override
    public Result getUserMesById(String id) {

        User user = userMapper.selectById(id);      //获取用户信息

        if(user==null){
            return Result.fail(State.ERRORPROCESS,"没有此用户！");
        }
        UserDTO userDTO = new UserDTO();
        BeanUtil.copyProperties(user,userDTO);

        return Result.succ(userDTO);
    }

    @Override
    public Result getUserList(Integer page, Integer pageSize) {
        if(page<1||pageSize<1)throw ParameterErrorException.Builder("参数应为整数！");

        Page<User> pageWrapper = new Page<>(page,pageSize,true);

        Page<User> userPage = userMapper.selectPage(pageWrapper, new QueryWrapper<>());
        if(userPage.getCurrent()!=page){        //如果查不到返回空
            return Result.succ(new ArrayList<>());
        }
        return Result.succ(userPage);
    }

    @Override
    public Result updateUserMes(UserDTO userDTO) {



        return null;
    }

    @Override
    public Result chargeMoney(String id, Integer money) {
        return null;
    }

    @Override
    public Result registerToMerchant(String id) {
        return null;
    }

    @Override
    public Result deleteUser(String id) {
        return null;
    }

    @Override
    public Result changePassword(String id, String password) {
        return null;
    }
}
