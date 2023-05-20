package com.weini.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weini.POJO.Do.User;
import com.weini.common.response.Result;
import com.weini.mapper.UserMapper;
import com.weini.service.UserService;
import com.weini.shiro.JwtToken;
import com.weini.utils.JwtFactory;
import com.weini.utils.SendEmail;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    UserMapper userMapper;

    @Resource
    RedisTemplate<Object,Object> template;

    @Autowired
    SendEmail sendEmail;

    @Override
    public Result loginByEmail(String email, String code, HttpServletResponse response) {

        User user = userMapper.selectOne(new QueryWrapper<User>().eq("email", email));
        if(Objects.isNull(user))return Result.fail("邮箱未注册");       //防止获取验证码未注册

        Object redisCode = template.opsForValue().get("LoginVerify:code:"+email);

        if(Objects.isNull(redisCode))return Result.fail("验证码错误");       //判断是否存在验证码

        String verify = (String) redisCode;
        if(!verify.equals(code))return Result.fail("验证码错误");        //验证码错误

        //登录操作
        Subject subject = SecurityUtils.getSubject();

        String jwt = JwtFactory.create(user);
        response.setHeader("Authorization",jwt);
        response.setHeader("Access-control-Expose-Headers","Authorization");
        subject.login(new JwtToken(jwt));
        return Result.succ("登录成功");
    }

    @Override
    public Result getEmailCode(String email) {
        sendEmail.loginMail(email);
        return Result.succ("发送成功");
    }

    @Override
    public User getUserById(String id) {
        if(Objects.isNull(id))return User.builder().build();
        return userMapper.selectById(id);
    }

    @Override
    public Result loginByPassword(String phone,String password,HttpServletResponse response) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("phone", phone));
        if(Objects.isNull(user))return Result.fail("手机号未注册");       //防止手机号未注册

        if(!user.getPassword().equals(password))return Result.fail("手机号或密码错误");

        //登录操作
        Subject subject = SecurityUtils.getSubject();

        String jwt = JwtFactory.create(user);
        response.setHeader("Authorization",jwt);
        response.setHeader("Access-control-Expose-Headers","Authorization");
        subject.login(new JwtToken(jwt));

        return Result.succ("登录成功");
    }
}
