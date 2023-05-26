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

    public static String idConstruct(){     //循环20次生成
        String generate;
        char[] strArr;
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        for (int i = 0; i < 2; i++) {
            strArr = RandomStringGenerator.generate();
            generate = new String(strArr);
            for (int j = 0; j < 20; j++) {
                wrapper.eq(User::getId,generate);
                User user = mapper.selectOne(wrapper);      //查询数据库是否存在同一个id
                if(!Objects.isNull(user))return generate;

                RandomStringGenerator.add(strArr);      //随机改变一位
                generate = new String(strArr);
            }
        }
        return "";      //表示随机生成id失败
    }

    public static int verifyCode(){
        Random random=new Random();
        return random.nextInt(9000)+1000;
    }
}
