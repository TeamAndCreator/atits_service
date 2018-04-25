package com.atits.controller;

import com.atits.entity.Station;
import com.atits.entity.User;
import com.atits.entity.Msg;
import com.atits.service.StationService;
import com.atits.service.UserService;
import com.atits.entity.Msg;
import com.atits.entity.User;
import com.atits.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@Api(description = "登录接口")
@RequestMapping(value = "login")
public class LoginController {


    @ApiOperation(value = "根据用户名和密码进行登录")
    @ApiResponses({
            @ApiResponse(code = 201, message = "已创建"),
            @ApiResponse(code = 400, message = "请求参数填写错误 "),
            @ApiResponse(code = 401, message = "访问被拒绝"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径错误")
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", required = true)
    })
    public Msg login(User user) {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
//            String hashAlgorithmName = "MD5";
//            Object credentials = user.getPassword();
//            Object salt = null;
//            int hashIterations = 1;
//            Object result = new SimpleHash(hashAlgorithmName, credentials, null, hashIterations);
//            System.out.println(result);
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
            token.setRememberMe(true);

            try {
                currentUser.login(token);
            } catch (UnknownAccountException ua) {
                System.out.println("未知账户！（提示：若已成功注册，请联系管理员查看用户是否已激活。）：" + ua.getMessage());
                return Msg.fail(ua);
            } catch (IncorrectCredentialsException ice) {
                System.out.println("错误的凭证！：" + ice.getMessage());
                return Msg.fail(ice);
            } catch (LockedAccountException lae) {
                System.out.println("账户已锁定！：" + lae.getMessage());
                return Msg.fail(lae);
            } catch (ExcessiveAttemptsException eae) {
                System.out.println("错误次数过多！：" + eae.getMessage());
                return Msg.fail(eae);
            } catch (AuthenticationException ae) {
                System.out.println("验证未通过！:" + ae.getMessage());
                return Msg.fail();
            }
        }
        return Msg.success();
    }

    @ApiOperation(value = "用户退出登录")
    @ApiResponses({
            @ApiResponse(code = 201, message = "已创建"),
            @ApiResponse(code = 400, message = "请求参数填写错误 "),
            @ApiResponse(code = 401, message = "访问被拒绝"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径错误")
    })
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public Msg logout() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {

            try {
                currentUser.logout();
            } catch (Exception e) {
                return Msg.fail(e);
            }
        }
        return Msg.success();
    }

}



