package com.atits.controller;

import com.atits.entity.Msg;
import com.atits.entity.User;
import com.atits.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "login")
@Api(description = "登录接口")
public class LoginController {
    @Resource
    private UserService userService;

    @ResponseBody
    @ApiOperation(value="根据用户名和密码进行登录")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Msg login(User user){
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassword());
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            } catch (AuthenticationException ae) {
                System.out.println("登录出错啦！:" + ae.getMessage());
                return Msg.fail(ae);
            }
        }
        return Msg.success();

    }




}



