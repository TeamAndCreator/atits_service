package com.atits.controller;


import com.atits.entity.Msg;
import com.atits.entity.Role;
import com.atits.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Api(description = "角色管理")
@RequestMapping(value = "role")
public class RoleController {


    @Resource
    private RoleService roleService;


    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ApiOperation(value = "查找所有的角色", notes = "查找所有的角色")
    @ResponseBody
    public Msg findAll() {
        try {
            List roles = roleService.findAll();
            return Msg.success().add("roles", roles);
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "根据角色id查找角色")
    @RequestMapping(value = "findById",method = RequestMethod.GET)
    @ResponseBody
    public Msg findById(@RequestParam Integer roleId){
        try{
            Role role = roleService.findById(roleId);
            return Msg.success().add("role",role);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }


}
