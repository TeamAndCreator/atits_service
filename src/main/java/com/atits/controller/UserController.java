package com.atits.controller;

import com.atits.entity.Msg;
import com.atits.entity.Role;
import com.atits.entity.User;
import com.atits.service.RoleService;
import com.atits.service.UserService;
import com.atits.utils.GetTimeUtil;
import io.swagger.annotations.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Controller;
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
@Api(description = "用户")
@RequestMapping(value = "user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;

    @ApiOperation(value="获取所有用户")
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseBody
    public Msg findAll() {
        try {
            List<User> users = userService.findAll();
            return Msg.success().add("users",users);
        }catch(Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "获取某个体系的所有人员")
    @RequestMapping(value = "findUsersBySystemId",method = RequestMethod.GET)
    public Msg findUsersBySystemId(int systemId){
        try {
            List<User> users=userService.findUsersBySystemId(systemId);
            return Msg.success().add("users",users);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "根据用户userId获取用户信息")
    @RequestMapping(value = "findById",method = RequestMethod.GET)
    @ResponseBody
    public Msg findById(@ApiParam(value = "用户id",required = true) @RequestParam Integer userId){
        try{
            User user = userService.findById(userId);
            return Msg.success().add("user",user);
        }catch(Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "根据用户用户名userName获取用户信息")
    @RequestMapping(value = "findByUserName",method = RequestMethod.GET)
    @ResponseBody
    public Msg findByUserName(@ApiParam(value = "用户名",required = true)@RequestParam String userName){
        try {
            User user = userService.findByUserName(userName);
            return Msg.success().add("user",user);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "保存一个用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName",value = "用户名",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "password",value = "用户密码",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "system.id",value = "用户所属体系id",paramType = "query",dataType = "Integer"),
            @ApiImplicitParam(name = "laboratory.id",value = "所属功能研究室id",paramType = "query",dataType = "Integer"),
            @ApiImplicitParam(name = "station.id",value = "所属综合试验站id",paramType = "query",dataType = "Integer")
    })
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Msg save(User user,
                      @ApiParam(value = "给用户分配的角色Id",required = true) @RequestParam List<Integer> roleIdList){
        try{
            User findByUserName = this.userService.findByUserName(user.getUserName());
            if (findByUserName != null) {
                return Msg.fail("用户名已存在！请重建用户名");
            }
            Object password = new SimpleHash("MD5", user.getPassword(), null, 1);
            user.setPassword(String.valueOf(password));
            Date time = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            user.setCreateTime(dateFormat.format(time));
            user.setState(2);//重置为未激活状态
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
            userService.save(user);
            return Msg.success().add("user",user);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "更新一个用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "需要修改的用户的id",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "userName",value = "用户名",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "password",value = "用户密码",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "system.id",value = "用户所属体系id",paramType = "query",dataType = "Integer"),
            @ApiImplicitParam(name = "laboratory.id",value = "所属功能研究室id",paramType = "query",dataType = "Integer"),
            @ApiImplicitParam(name = "station.id",value = "所属综合试验站id",paramType = "query",dataType = "Integer")
    })
    @RequestMapping(value = "update",method = RequestMethod.PUT)
    @ResponseBody
    public Msg update(User user,
                      @ApiParam(value = "给用户分配的角色Id",required = true) @RequestParam List<Integer> roleIdList){
        try{
            User user1 = userService.findById(user.getId());
            if (user1 == null)
                return Msg.fail("不存在该用户，请重新输入正确的用户id！");
            Object password = new SimpleHash("MD5", user.getPassword(), null, 1);
            user.setPassword(String.valueOf(password));
            Date time = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            user.setCreateTime(dateFormat.format(time));
            user.setState(2);//重置为未激活状态
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
            userService.update(user);
            return Msg.success().add("user",user);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "根据用户uerId删除一个用户")
    @RequestMapping(value = "deleteById",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteById(@ApiParam(value = "用户id",required = true) @RequestParam Integer userId){
        try{
            userService.deleteById(userId);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "批量删除用户")
    @RequestMapping(value = "deleteByIds",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteByIds(@ApiParam(value = "所有用户id，以英文逗号隔开依次输入",required = true)
                            @RequestParam List<Integer> userIds){
        try {
            userService.deleteByIds(userIds);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新激活状态")
    @RequestMapping(value = "updateState",method = RequestMethod.PUT)
    public Msg updateState(int userId){
        try {
            userService.updateState(userId);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }
}
