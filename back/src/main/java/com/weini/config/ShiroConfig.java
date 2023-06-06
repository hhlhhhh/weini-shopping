package com.weini.config;

import com.weini.shiro.AccountRealm;
import com.weini.shiro.JwtFilter;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean("jwt-filter")
    JwtFilter jwtFilter(){
        return new JwtFilter();
    }

    @Bean
    public AccountRealm accountRealm(){
        return new AccountRealm();
    }


    @Bean
    public DefaultWebSecurityManager securityManager() {
        AccountRealm accountRealm = accountRealm();
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(accountRealm);
//        securityManager.setSessionManager(getDefaultWebSessionManager());
        ThreadContext.bind(securityManager);
        return securityManager;
    }
    @Bean
    public ShiroFilterFactoryBean  ShiroFilterFactoryBean() {
       ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
       factoryBean.setSecurityManager(securityManager());
       Map<String,Filter> map = new LinkedHashMap<>();
       map.put("jwt",jwtFilter());
       factoryBean.setFilters(map);
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        filterChainDefinitionMap.put("/account/**", "anon");
        filterChainDefinitionMap.put("/media/**", "anon");
//        filterChainDefinitionMap.put("/account/logout", "logout");

//        factoryBean.setLoginUrl("/account/login-fail");
        filterChainDefinitionMap.put("/**", "jwt");
        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return factoryBean;
    }

}

