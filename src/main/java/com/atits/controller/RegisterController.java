package com.atits.controller;

import com.atits.entity.Msg;
import com.atits.entity.User;
import com.atits.service.UserService;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@Api(value = "product", description = "注册接口")
public class RegisterController {

    @Resource
    private UserService userService;

    @ApiOperation("用户注册")
    @ApiResponses({
            @ApiResponse(code= 201 ,message = "已创建"),
            @ApiResponse(code = 400, message = "请求参数填写错误 "),
            @ApiResponse(code= 401 ,message = "访问被拒绝"),
            @ApiResponse(code= 403 ,message = "禁止访问"),
            @ApiResponse(code= 404 ,message = "请求路径没有或页面跳转路径错误")
    })
//        @ApiImplicitParams({
//            @ApiImplicitParam(name = "username", value = "用户名", required = true,paramType = "query"),
//            @ApiImplicitParam(name = "password", value = "用户密码", required = true,paramType = "query"),
//            @ApiImplicitParam(name = "system.id",value = "系统ID",required = true,paramType = "query")
//    }
//    )
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public Msg register(User user){
        //用户名唯一
        try{
            User findByUserName = this.userService.findByUserName(user.getUserName());
            if (findByUserName != null){
                System.out.println("用户名已存在！请重建用户名");
                return Msg.fail();
            }
            user.setState(2);//未激活
            try{
                if (user.getPassword() !=null &&  !"".equals(user.getPassword())){
                    String userPwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
                    user.setPassword(userPwd);
                }
                Date time = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                user.setCreateTime(dateFormat.format(time));
//                user.setTime(dateFormat.format(time));
                this.userService.save(user);
            }catch (Exception e){
                System.out.println("保存用户信息异常！");
                e.printStackTrace();
                return Msg.fail();
            }
        }catch (Exception e){
            e.printStackTrace();
            return Msg.fail();
        }
        return Msg.success();
    }
}
