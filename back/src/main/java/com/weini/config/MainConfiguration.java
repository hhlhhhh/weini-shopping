package com.weini.config;

import com.auth0.jwt.interfaces.Claim;
import com.weini.utils.JwtFactory;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Configuration
public class MainConfiguration {

    @Resource
    RedisTemplate<Object,Object> template;

    @PostConstruct
    void init(){
        RedisMybatisCache.setTemplate(template);
    }

    //解决aop与shiro不一起使用的bug
    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }
}
