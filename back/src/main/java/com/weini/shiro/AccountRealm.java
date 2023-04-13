package com.weini.shiro;


import com.weini.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AccountRealm extends AuthorizingRealm{

    @Autowired
    UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        AccountProfile primaryPrincipal =(AccountProfile) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole(primaryPrincipal.getAuthority());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        JwtToken jwtToken = (JwtToken) authenticationToken;
//        String userId  =  JwtFactory.getMes((String) jwtToken.getPrincipal()).get("userId").asString();
//        User user = userService.getById(userId);
//
//        if(user==null){
//            throw new UnknownAccountException("账户不存在");
//        }else if(Objects.equals(user.getDelFlag(), "1")){
//            throw new LockedAccountException("账户已被封禁");
//        }
//
        AccountProfile accountProfile =new AccountProfile();
//
//        BeanUtils.copyProperties(user,accountProfile);

        return new SimpleAuthenticationInfo(accountProfile,jwtToken.getCredentials(),getName());
    }
}
