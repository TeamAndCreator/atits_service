package com.atits.controller;

import com.atits.entity.Msg;
import com.atits.entity.Role;
import com.atits.entity.User;
import com.atits.service.RoleService;
import com.atits.service.UserService;
import io.swagger.annotations.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@Api(description = "注册接口")
@RequestMapping(value = "register")
public class RegisterController {

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;

    @ApiOperation("用户注册")
    @ApiResponses({
            @ApiResponse(code = 201, message = "已创建"),
            @ApiResponse(code = 400, message = "请求参数填写错误 "),
            @ApiResponse(code = 401, message = "访问被拒绝"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径错误")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName",value = "用户名",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "password",value = "用户密码",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "system.id",value = "用户所属体系id",paramType = "query",dataType = "Integer"),
            @ApiImplicitParam(name = "laboratory.id",value = "所属功能研究室id",paramType = "query",dataType = "Integer"),
            @ApiImplicitParam(name = "station.id",value = "所属综合试验站id",paramType = "query",dataType = "Integer")
    })
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public Msg register(User user, @ApiParam(value = "给用户分配的角色Id",required = true) @RequestParam List<Integer> roleIdList) {
        try {
            User findByUserName = this.userService.findByUserName(user.getUserName());
            if (findByUserName != null) {
                return Msg.fail("用户名已存在！请重建用户名");
            }
            Object password = new SimpleHash("MD5", user.getPassword(), null, 1);
            user.setPassword(String.valueOf(password));
            Date time = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            user.setCreateTime(dateFormat.format(time));
            user.setState(2);//未激活
            List<Role> roles = roleService.findAll();
            int i =0 ;
            for (Role role:roles){
                for (Integer roleId:roleIdList){
                    if (role.getId() == roleId){
                        i++;
                        break;
                    }
                }
            }
            if (i == 0)
                return Msg.fail("不存在该角色！请重新输入正确的角色idList（至少要有一个是正确的才能成功添加）");
            Set<Role> roles1 = new HashSet<>();
            for (Integer roleId:roleIdList){
                roles1.add(roleService.findById(roleId));//若其中有不存在的角色，则忽略添加该角色，其他正确角色正常添加
            }
            user.setRoles(roles1);
            this.userService.save(user);
            return Msg.success().add("user",user);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }
}
