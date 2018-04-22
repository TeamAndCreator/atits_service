package com.atits.shiro;

import com.atits.dao.UserDao;
import com.atits.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import org.apache.commons.codec.binary.Hex;
//import org.apache.commons.codec.binary.StringUtils;

import javax.annotation.Resource;

@Component
public class MyRealm extends AuthenticatingRealm {

    @Resource
    private UserDao userDao;

    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken =(UsernamePasswordToken) token;
        String username=usernamePasswordToken.getUsername();

        User user = userDao.findByUserName(username);
        if( user != null && user.getState() == 1) {
            return new SimpleAuthenticationInfo(user.getId(),user.getPassword(), getName());
        }
//        else
//            if (user.getState() != 1){
//            System.out.println("用户未激活！请联系管理员");
//            return null;
//        }
        else
            return  null;
    }

}