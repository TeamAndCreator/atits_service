package com.atits.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthenticatingRealm;


public class MyRealm extends AuthenticatingRealm {

    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken =(UsernamePasswordToken) token;
        String username=usernamePasswordToken.getUsername();

        return null;
    }
}
