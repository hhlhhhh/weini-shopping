package com.weini.shiro;


import com.auth0.jwt.interfaces.Claim;
import com.weini.POJO.Do.User;
import com.weini.service.UserService;
import com.weini.utils.JwtFactory;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
public class AccountRealm extends AuthorizingRealm{

    @Autowired
    UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    //授权器
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        AccountProfile primaryPrincipal =(AccountProfile) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole(primaryPrincipal.getRole());
        return info;
    }

    //认证器
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        JwtToken jwtToken = (JwtToken) authenticationToken;
        String jwt = (String) jwtToken.getCredentials();
        Map<String, Claim> mes = JwtFactory.getMes(jwt);

        String id = mes.get("id").asString();   //获取用户id
        User user = userService.getUserById(id);  //通过id查询用户

        if(Objects.isNull(user.getId()))throw new UnknownAccountException("用户不存在");

        AccountProfile accountProfile =new AccountProfile();
        BeanUtils.copyProperties(user,accountProfile);

        return new SimpleAuthenticationInfo(accountProfile,jwtToken.getCredentials(),getName());
    }
}
