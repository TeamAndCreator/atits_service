package com.atits.controller;

import com.atits.entity.User;
import com.atits.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class LoginController {
    @Resource
    private UserService userService;


    //    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            } catch (AuthenticationException ae) {
                System.out.println("登录出错啦！:" + ae.getMessage());
                return "unauthorized";
            }
        }
        return "home";

    }


    @RequestMapping(value = "user", method = RequestMethod.GET)
    @ResponseBody
    public List<User> findAll() {
        List<User> users = userService.findAll();
        return users;
    }

}



