package com.atits.controller;

import com.atits.entity.Msg;
import com.atits.entity.User;
import com.atits.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(description = "用户")
@RequestMapping(value = "user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "获取所有用户")
    @GetMapping(value = "findAll")
    public Msg findAll() {
        try {
            List<User> users = userService.findAll();
            return Msg.success().add("users", users);
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }

    }

    @ApiOperation(value = "通过ID获取用户")
    @GetMapping(value = "find_by_id")
    public Msg findById() {
        long id=147;
        try {
            User user = userService.fingById(id);
            return Msg.success().add("user", user);
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }

    }

}
