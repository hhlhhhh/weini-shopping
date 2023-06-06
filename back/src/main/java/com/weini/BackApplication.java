package com.weini;

import com.weini.shiro.JwtFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@MapperScan("com.weini.mapper")     //数据库操作的包扫描
@EnableTransactionManagement    //开启事务
@SpringBootApplication
public class BackApplication {

    private ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(BackApplication.class, args);
    }
}
