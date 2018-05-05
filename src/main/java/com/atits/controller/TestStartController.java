package com.atits.controller;


import com.atits.entity.Msg;
import com.atits.entity.User;
import com.atits.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Api(description = "考评启动")
@RequestMapping(value = "kaopin")
public class TestStartController {

    @Resource
    private UserService userService;
    //从User表中导入体系内部人员、农委以及外聘人员
    //通过体系id、角色查找
    @ApiOperation(value = "导入考评人员")
    @RequestMapping(value = "/start",method = RequestMethod.GET)
    @ResponseBody
    public Msg start(){
        try {

            List<User> users = userService.findBySysId(14);//需要获取到当前用户的体系id
            return Msg.success().add("users",users);
        }catch(Exception e){
            return Msg.fail(e.getMessage());
        }

//        return Msg.fail("");
    }


}
