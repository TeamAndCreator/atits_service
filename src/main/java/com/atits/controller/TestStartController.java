package com.atits.controller;


import com.atits.entity.Msg;
import com.atits.entity.Role;
import com.atits.entity.TestStart;
import com.atits.entity.User;
import com.atits.service.TestStartService;
import com.atits.service.UserService;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Api(description = "考评启动")
@RequestMapping(value = "evaluate")
public class TestStartController {

    @Resource
    private UserService userService;
    @Resource
    private TestStartService testStartService;
    /*
    显示所有可能参加考评的人员（前端从中挑选出此次参评人员）
    从User表中导入体系内部人员、农委以及外聘人员
    通过体系id、角色查找
     */

    @ApiOperation(value = "通过体系ID、角色ID来查找参评人员")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "system.id",value = "体系ID",paramType = "query",dataType = "Integer"),
//            @ApiImplicitParam(name = "roles.id",value = "角色ID",paramType = "query",dataType = "Integer")
//    })
    @RequestMapping(value = "import_persons",method = RequestMethod.GET)
    @ResponseBody
    public Msg importPersons(Integer sysId, Integer roleId){
        try {
            if (sysId !=null && roleId!=null){
                List<User> users = userService.findTestPer(sysId,roleId);//sysId=1 时未显示人员
                //异常：两者必须同时输入
                //user为空时也不代表失败，只能说没有人员。这里是同时输入两个值即处理成功，并不一定存在，需要容错处理
                return Msg.success().add("users",users);
            }else
                return Msg.fail("均不能为空！");
        }catch(Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "获取启动表的所有记录")
    @RequestMapping(value = "start_list",method = RequestMethod.GET)
    @ResponseBody
    public Msg findAll(){
        try{
            List<TestStart> testStarts = testStartService.findAll();
            return Msg.success().add("testStarts",testStarts);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }


}
