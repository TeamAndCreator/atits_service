package com.atits.controller;

import com.atits.entity.Station;
import com.atits.entity.User;
import com.atits.entity.Msg;
import com.atits.service.StationService;
import com.atits.service.UserService;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Api(value = "product", description = "登录接口")
public class LoginController {
    @Resource
    private UserService userService;

    @Resource
    private StationService stationService;


//    @ApiOperation(value="根据用户名和密码进行登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true)
    }
    )
    @ApiResponses({
            @ApiResponse(code= 201 ,message = "已创建"),
            @ApiResponse(code = 400, message = "请求参数填写错误 "),
            @ApiResponse(code= 401 ,message = "访问被拒绝"),
            @ApiResponse(code= 403 ,message = "禁止访问"),
            @ApiResponse(code= 404 ,message = "请求路径没有或页面跳转路径错误")
    })
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public Msg login(@RequestParam("username") String username, @RequestParam("password") String password) {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            } catch (AuthenticationException ae) {
                System.out.println("登录出错啦！:" + ae.getMessage());
                return Msg.fail();
            }
        }
        return Msg.success();
    }


    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value = "user", method = RequestMethod.GET)
    @ResponseBody
    public List<User> findAll() {
        List<User> users = userService.findAll();
        return users;
    }


    @ApiOperation(value="获取所有试验站详细信息")
    @RequestMapping(value = "station", method = RequestMethod.GET)
    @ResponseBody
    public List<Station> findAll2() {
        List<Station> stations = stationService.findAll();
        return stations;
    }

}



