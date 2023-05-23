package com.weini.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weini.POJO.DTO.UserDTO;
import com.weini.POJO.Do.User;
import com.weini.common.exception.MissedParameterException;
import com.weini.common.exception.ParameterErrorException;
import com.weini.common.response.Result;
import com.weini.common.response.State;
import com.weini.mapper.UserMapper;
import com.weini.service.UserService;
import com.weini.utils.RegVerify;
import com.weini.utils.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

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

        if(userDTO==null)return Result.fail("id不能为空！");
        boolean exists = userMapper.exists(new QueryWrapper<User>().eq("id",userDTO.getId()));

        if (!exists){
            return Result.fail(State.ERRORPROCESS,"无此用户！");
        }

        Optional<UserDTO> optional = Optional.of(userDTO);
        User user = new User().setId(userDTO.getId());

        optional.map(UserDTO::getNickname).ifPresent(
                e->Optional.of(e).filter(RegVerify::verifyNickname)
                .ifPresentOrElse(user::setNickname,() -> {
                    throw MissedParameterException.Builder("昵称格式错误");
                }));

        optional.map(UserDTO::getAvatar).ifPresent(user::setAvatar);

        optional.map(UserDTO::getSex).ifPresent(
                e-> Optional.of(e).filter(sex->"男".equals(sex)||"女".equals(sex)||"保密".equals(sex))
                .ifPresentOrElse(user::setSex,() -> {
                    throw MissedParameterException.Builder("请正确输入性别！");
                }));

        optional.map(UserDTO::getSignature).ifPresent(
                e->Optional.of(e).filter(signature->signature.length()<=200)
                .ifPresentOrElse(user::setSignature,()->{
                    throw ParameterErrorException.Builder("个性签名不能超过200字！");
                })
        );

        userMapper.updateById(user);

        return Result.succ("个人信息更新成功！");
    }

    @Override
    public Result chargeMoney(String id, Integer money) {
        User user = userMapper.selectById(id);
        if(user==null){
            return Result.fail("无此用户！");
        }
        if (money < 0) return Result.fail("不能充值负数");

        user.setBalance(user.getBalance()+money);
        int res = userMapper.chargeMoney(user.getId(),user.getBalance()); //充值操作
        if(res==0){
            Result.fail(State.ERROROPERATE,"充值失败！");
        }
        return Result.succ("充值成功！");
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
