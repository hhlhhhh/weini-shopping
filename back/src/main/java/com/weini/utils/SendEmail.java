package com.weini.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class SendEmail {

    @Resource
    JavaMailSender sender;

    @Resource
    RedisTemplate<Object,Object> template;

    @Value("${weini.email.from}")
    String from;

    public void registerMail(String to){
        int verification=RandomId.verifyCode();
        String content="尊敬的用户您好，您的注册验证码是"+verification+"验证码5分钟内有效。如不是本人操作，请忽略此邮件！";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("【weini-mail】注册验证码");
        message.setText(content);
        message.setTo(to);
        message.setFrom(from);
        String code=""+verification;
        template.opsForValue().set("RegisterVerify:code:"+to,code);
        template.expire("RegisterVerify:code:"+to,5, TimeUnit.MINUTES);
        sender.send(message);
    }

    public void loginMail(String to){
        int verification=RandomId.verifyCode();
        String content="尊敬的用户您好，您的登录验证码是"+verification+"验证码5分钟内有效。如不是本人操作，请忽略此邮件！";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("【weini-mail】登录验证码");
        message.setText(content);
        message.setTo(to);
        message.setFrom(from);
        String code=""+verification;
        template.opsForValue().set("LoginVerify:code:"+to,code);
        template.expire("LoginVerify:code:"+to,5, TimeUnit.MINUTES);
        sender.send(message);
    }

}
