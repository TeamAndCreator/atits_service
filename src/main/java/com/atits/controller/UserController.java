package com.atits.controller;

import com.atits.entity.Msg;
import com.atits.entity.User;
import com.atits.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Api(description = "用户")
@RequestMapping(value = "user")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value="获取所有用户")
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
