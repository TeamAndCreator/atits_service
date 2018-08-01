package com.atits.controller;

import com.atits.entity.Msg;
import com.atits.entity.TestManage;
import com.atits.entity.TestStart;
import com.atits.entity.User;
import com.atits.service.TestManageService;
import com.atits.service.TestStartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.objenesis.instantiator.sun.MagicInstantiator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
@Api(description = "考评管理")
@RequestMapping(value = "testmanage")
public class TestManageController {
    @Resource
    private TestManageService testManageService;
    @Resource
    private TestStartService testStartService;

    @ResponseBody
    @ApiOperation(value = "查找一个用户的所有评分")
    @RequestMapping(value = "findOwn",method = RequestMethod.GET)
    public Msg findOwn(int userId){
        try {
            List<TestManage> testManages=testManageService.findOwn(userId);
            return Msg.success().add("testManages",testManages);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "查找一个体系所有评分")
    @RequestMapping(value = "findSystemTestManage",method = RequestMethod.GET)
    public Msg findSystemTestManage(int systemId){
        try {
            List<TestManage> testManages=testManageService.findSystemTestManage(systemId);
            return Msg.success().add("testManages",testManages);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

}
