package com.atits.controller;

import com.atits.entity.Station;
import com.atits.entity.User;
import com.atits.entity.Msg;
import com.atits.service.StationService;
import com.atits.service.UserService;
import com.atits.entity.Msg;
import com.atits.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@Api(value = "product", description = "登录接口")
@RequestMapping(value = "login")
public class LoginController {
    @Resource
    private UserService userService;


    @ApiOperation(value="根据用户名和密码进行登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true,paramType = "query"),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true,paramType = "query")
    }
    )
    @ApiResponses({
            @ApiResponse(code= 201 ,message = "已创建"),
            @ApiResponse(code = 400, message = "请求参数填写错误 "),
            @ApiResponse(code= 401 ,message = "访问被拒绝"),
            @ApiResponse(code= 403 ,message = "禁止访问"),
            @ApiResponse(code= 404 ,message = "请求路径没有或页面跳转路径错误")
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Msg login(@RequestParam("username")String username,@RequestParam("password")String password) {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            String passwordMD5 = DigestUtils.md5DigestAsHex(password.getBytes());
            System.out.println(passwordMD5);
            UsernamePasswordToken token = new UsernamePasswordToken(username, passwordMD5);
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            }catch (UnknownAccountException ua){
                System.out.println("未知账户！（提示：若已成功注册，请联系管理员查看用户是否已激活。）："+ua.getMessage());
                return Msg.fail();
            }catch (IncorrectCredentialsException ice){
                System.out.println("错误的凭证！："+ice.getMessage());
                return Msg.fail();
            }catch (LockedAccountException lae){
                System.out.println("账户已锁定！："+lae.getMessage());
                return Msg.fail();
            }catch (ExcessiveAttemptsException eae){
                System.out.println("错误次数过多！："+eae.getMessage());
                return Msg.fail();
            }catch (AuthenticationException ae) {
                System.out.println("验证未通过！:" + ae.getMessage());
                return Msg.fail();
            }
        }
        return Msg.success();
    }

//    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
//    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
//    @RequestMapping(value = "user", method = RequestMethod.GET)
//    @ResponseBody
//    public List<User> findAll() {
//        List<User> users = userService.findAll();
//        return users;
//    }


//    @ApiOperation(value="获取所有试验站详细信息")
//    @RequestMapping(value = "station", method = RequestMethod.GET)
//    @ResponseBody
//    public List<Station> findAll2() {
//        List<Station> stations = stationService.findAll();
//        return stations;
//                return Msg.fail(ua);
//            }catch (IncorrectCredentialsException ice){
//                System.out.println("错误的凭证！："+ice.getMessage());
//                return Msg.fail(ice);
//            }catch (LockedAccountException lae){
//                System.out.println("账户已锁定！："+lae.getMessage());
//                return Msg.fail(lae);
//            }catch (ExcessiveAttemptsException eae){
//                System.out.println("错误次数过多！："+eae.getMessage());
//                return Msg.fail(eae);
//            }catch (AuthenticationException ae) {
//                System.out.println("验证未通过！:" + ae.getMessage());
//                return Msg.fail(ae);
//            }
//        }
//        return Msg.success();
//    }

}



