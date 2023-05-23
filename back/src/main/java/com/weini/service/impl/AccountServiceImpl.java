package com.weini.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weini.POJO.Do.User;
import com.weini.common.exception.MissedParameterException;
import com.weini.common.exception.ParameterErrorException;
import com.weini.common.response.Result;
import com.weini.common.response.State;
import com.weini.mapper.UserMapper;
import com.weini.service.AccountService;
import com.weini.shiro.JwtToken;
import com.weini.utils.JwtFactory;
import com.weini.utils.RandomId;
import com.weini.utils.RegVerify;
import com.weini.utils.SendEmail;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class AccountServiceImpl extends ServiceImpl<UserMapper, User> implements AccountService {

    @Resource
    UserMapper userMapper;

    @Resource
    RedisTemplate<Object,Object> template;

    @Autowired
    SendEmail sendEmail;

    @Override
    public Result loginByEmail(String email, String code, HttpServletResponse response) {

        User user = userMapper.selectOne(new QueryWrapper<User>().eq("email", email));
        if(Objects.isNull(user))return Result.fail(State.ERROROPERATE,"邮箱未注册");       //防止获取验证码未注册

        Object redisCode = template.opsForValue().get("LoginVerify:code:"+email);

        if(Objects.isNull(redisCode))return Result.fail(State.ERR,"验证码错误");       //判断是否存在验证码

        String verify = (String) redisCode;
        if(!verify.equals(code))return Result.fail(State.ERROROPERATE,"验证码错误");        //验证码错误

        //登录操作
        Subject subject = SecurityUtils.getSubject();

        template.delete("LoginVerify:code:"+email);     //删除登录邮箱验证码

        String jwt = JwtFactory.create(user);
        response.setHeader("Authorization",jwt);
        response.setHeader("Access-control-Expose-Headers","Authorization");
        subject.login(new JwtToken(jwt));

        template.opsForValue().set(user.getId() + "-jwt",jwt,1, TimeUnit.DAYS);  //将jwt放入缓存,缓存1天

        return Result.succ("登录成功");
    }

    //验证邮箱是否注册
    public boolean verifyUserEmail(String email) {
        return userMapper.exists(new QueryWrapper<User>().eq("email", email));
    }

    @Override
    public Result sendEmailCode(String email, String type) {
        if(!RegVerify.verifyEmail(email)) return Result.fail("邮箱格式错误！");
        Optional<String> optional = Optional.of(type);
        char[] flag = new char[1];  //判断完成情况，0未开始，1开始，2发送

        optional.filter("login"::equals).ifPresent(e->{
            if(!verifyUserEmail(email)) throw ParameterErrorException.Builder("邮箱未注册！");
            flag[0]=1;
            sendEmail.loginMail(email);
            flag[0]=2;
        });
        optional.filter(e->flag[0]==0&&"register".equals(e)).ifPresent(e->{
            if(verifyUserEmail(email)) throw ParameterErrorException.Builder("邮箱已注册！");
            flag[0]=1;
            sendEmail.registerMail(email);
            flag[0]=2;
        });
        optional.filter(e->flag[0]==0&&"password".equals(e)).ifPresent(e->{
            if(!verifyUserEmail(email)) throw ParameterErrorException.Builder("邮箱未注册！");
            flag[0]=1;
            sendEmail.updatePasswordMail(email);
            flag[0]=2;
        });

        if (flag[0] == 2) return Result.succ("发送成功！");
        return Result.fail(State.ERR,"参数错误！");
    }

    @Override
    public User getUserById(String id) {
        return userMapper.selectById(id);
    }


    @Override
    public Result register(User user, String code, HttpServletRequest req,HttpServletResponse rep) {

        Object o = template.opsForValue().get("RegisterVerify:code:" + user.getEmail());
        assert o!=null:"验证码未发送或已过期！";

        String codeCache = (String)o;
        if(!codeCache.equals(code))return Result.fail(State.ERR,"邮箱验证码错误！");

        Optional<User> optional = Optional.of(user);

        optional.ifPresentOrElse(u -> {
            //判空处理
            optional.map(User::getNickname).filter(RegVerify::verifyNickname)
                    .orElseThrow(() -> MissedParameterException.Builder("昵称格式错误"));

            optional.map(User::getPhone).filter(RegVerify::verifyPhone)
                    .orElseThrow(() -> MissedParameterException.Builder("手机号格式错误！"));

            optional.map(User::getEmail).filter(RegVerify::verifyEmail)
                    .orElseThrow(() -> MissedParameterException.Builder("邮箱格式错误！"));

            optional.map(User::getPhone).filter(e->!userMapper.exists(new QueryWrapper<User>().eq("phone", user.getPhone())))
                    .orElseThrow(() -> ParameterErrorException.Builder("手机号已注册！"));
        }, () -> {
            throw MissedParameterException.Builder("参数意外缺失！");
        });

        String localUrl = req.getRequestURL().toString().replace(req.getRequestURI(),"");
        user.setId(RandomId.idConstruct())      //初始化用户信息
                .setBalance(0)
                .setAvatar(localUrl+"/image/default-avatar.png")
                .setRole("user")
                .setSex("保密")
                .setSignature("")
                .setDel_flag("0");

        int res = userMapper.insert(user);
        if(res != 1)return Result.fail(State.ERROROPERATE,"新增用户失败！");

        template.delete("RegisterVerify:code:" + user.getEmail());  //删除邮箱验证码的缓存

        String jwt = JwtFactory.create(user);   //登录凭证
        rep.setHeader("login-auth",jwt);
        rep.setHeader("Access-control-Expose-Headers","login-auth");

        template.opsForValue().set(user.getId() + "-login",jwt,1, TimeUnit.DAYS);  //将jwt放入缓存,缓存1天

        return Result.succ(user);
    }

    @Override
    public Result loginByPassword(String phone,String password,HttpServletRequest request,HttpServletResponse response) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("phone", phone));
        if(Objects.isNull(user))return Result.fail(State.ERRORPROCESS,"手机号未注册");       //防止手机号未注册

        String loginAuth = request.getHeader("login-auth");
        boolean loginFlag = false;
        if(loginAuth!=null){        //获取免密码登录凭证
            Object o = template.opsForValue().get(user.getId() + "-login");
            if(o!=null){
                String authCache = (String) o;
                if(authCache.equals(loginAuth)){
                    loginFlag = true;
                    template.delete(user.getId() + "-login");        //删除登录凭证
                }
            }
        }

        if(!loginFlag&&!user.getPassword().equals(password))return Result.fail("手机号或密码错误");     //如果密码验证和登录凭证验证都失败，返回失败。

        //登录操作
        Subject subject = SecurityUtils.getSubject();

        String jwt = JwtFactory.create(user);
        response.setHeader("Authorization",jwt);
        response.setHeader("Access-control-Expose-Headers","Authorization");
        subject.login(new JwtToken(jwt));

        template.opsForValue().set(user.getId() + "-jwt",jwt,1, TimeUnit.DAYS);  //将jwt放入缓存,缓存1天

        return Result.succ("登录成功");
    }
}
