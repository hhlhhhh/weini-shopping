package com.weini.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.weini.POJO.Do.User;
import com.weini.mapper.UserMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Objects;
import java.util.Random;

@Component
public class RandomId {

    @Resource
    UserMapper userMapper;

    private static  UserMapper mapper;

    @PostConstruct
    public void init(){
        mapper = userMapper;
    }

    public static String idConstruct(){
        String strArr = new String(RandomStringGenerator.generate());
        return strArr;
    }

    public static int verifyCode(){
        Random random=new Random();
        return random.nextInt(9000)+1000;
    }
}
