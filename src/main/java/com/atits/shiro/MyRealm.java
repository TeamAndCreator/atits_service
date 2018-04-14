package com.atits.shiro;

import com.atits.dao.UserDao;
import com.atits.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MyRealm extends AuthenticatingRealm {

    @Resource
    private UserDao userDao;

    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken =(UsernamePasswordToken) token;
        String username=usernamePasswordToken.getUsername();

        User user = userDao.findUser(username);
        if( user != null ) {
            return new SimpleAuthenticationInfo(user.getId(), user.getPassword(), getName());
        } else {
            return null;
        }

    }
}
