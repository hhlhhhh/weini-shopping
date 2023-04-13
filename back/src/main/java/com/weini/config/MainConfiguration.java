package com.weini.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Configuration
public class MainConfiguration {

    @Resource
    RedisTemplate<Object,Object> template;


    @PostConstruct
    void init(){
        RedisMybatisCache.setTemplate(template);
    }
}
