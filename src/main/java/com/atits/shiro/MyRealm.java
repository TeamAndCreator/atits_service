package com.atits.shiro;

import com.atits.dao.UserDao;
import com.atits.entity.Role;
import com.atits.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MyRealm extends AuthorizingRealm {

    @Resource
    private UserDao userDao;

    //用于认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken =(UsernamePasswordToken) token;
        String username=usernamePasswordToken.getUsername();
        User user = userDao.findByUserName(username);
        SimpleAuthenticationInfo info = null;

        if( user != null && user.getState() == 1) {
            info = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());
            return info;
        }
        return null;
    }

    //用于授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 根据用户名查询当前用户拥有的角色
        List<Role> roles = userDao.findRoleById(username);
        Set<String> roleNames = new HashSet<String>();
        for (Role role:roles) {
            roleNames.add(role.getName());
        }
        // 将角色名称提供给info
        authorizationInfo.setRoles(roleNames);
        return authorizationInfo;
    }
}
