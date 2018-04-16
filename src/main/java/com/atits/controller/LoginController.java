package com.atits.controller;

import com.atits.entity.Msg;
import com.atits.entity.User;
import com.atits.service.NoticeService;
import com.atits.service.StationService;
import com.atits.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Map;

@Controller
@RequestMapping(value = "login")
@Api(description = "登录接口")
public class LoginController {
    @Resource
    private UserService userService;

    @ResponseBody
    @ApiOperation(value="根据用户名和密码进行登录")
    @ApiImplicitParam(name = "username", value = "表单输入的用户名", required = true, dataType = "字符串")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam("user") String user) throws Exception{
        Subject currentUser = SecurityUtils.getSubject();
        Map<String,String> map;
        ObjectMapper objectMapper = new ObjectMapper();
        map=objectMapper.readValue(user, Map.class);
        String userName=map.get("username");
        String password=map.get("password");
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
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


    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value = "user", method = RequestMethod.GET)
    @ResponseBody
    public Msg findAll() {
        try {
            List<User> users = userService.findAll();
            return Msg.success().add("users",users);
        }catch(Exception e){
            return Msg.fail(e);
        }
    }





}



