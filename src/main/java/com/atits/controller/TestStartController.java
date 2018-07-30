package com.atits.controller;


import com.atits.entity.*;
import com.atits.service.TestStartService;
import com.atits.service.UserService;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.lang.System;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Api(description = "考评启动")
@RequestMapping(value = "teststart")
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

    @ApiOperation(value = "通过体系ID筛选出参评人员（显示在页面），再从中挑选出此次的参评人员(前端挑选后存入数据库)")//通过体系ID、角色ID来查找参评人员
    @RequestMapping(value = "import_persons", method = RequestMethod.GET)
    @ResponseBody
    public Msg importPersons(Integer sysId) {
        try {
            List users;
            if (sysId != null) {
                users = userService.findTestPer(sysId);//返回是一个仅含有用户名的数组
            } else {
                users = userService.findExternal();
            }
            return Msg.success().add("users", users);
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    /*
    尚需完善
     */
    @ApiOperation(value = "添加考评记录,考评人员需要前端选中后保存到数据库中，目前测试采取直接输入数据库方式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "考评ID（自增长模式）", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "address", value = "考评地址", paramType = "query", dataType = "String", required = true),
            @ApiImplicitParam(name = "date", value = "考评时间（自动获取当前系统时间）", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "year", value = "考评年份", paramType = "query", dataType = "String", required = true),
            @ApiImplicitParam(name = "state", value = "考评状态（1:启动考评，2:考评开始，3:考评结束）", paramType = "query", dataType = "List", required = true)
    })
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Msg save(TestStart testStart, Integer[] ids) {
        //当选中要添加的考评人员后，实现以下的自动插入
        try {
            Set<User> users = new HashSet<>();
            for (int i : ids) {
                User user = new User();
                user.setId(i);
                users.add(user);
            }
            testStart.setUsers(users);
            testStartService.save(testStart);
            return Msg.success().add("testStart", testStart);
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "根据id删除启动记录。仅能删除从未启动过的记录（前端页面显示控制）")
    @RequestMapping(value = "deleteById", method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteById(@RequestParam Integer id) {
        try {
            testStartService.deleteById(id);
            return Msg.success();
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "根据id数组批量删除启动记录，依次以英文逗号隔开输入")
    @RequestMapping(value = "deleteByIds", method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteByIds(
            @ApiParam(name = "idList", value = "需删除启动记录的id数组,依次以英文逗号间隔输入") @RequestParam List<Integer> idList) {
        try {
            testStartService.deleteByIds(idList);
            return Msg.success();
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }


    @ApiOperation(value = "更新一个启动记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需更新的启动记录id", paramType = "query", dataType = "int", required = true),
            @ApiImplicitParam(name = "address", value = "考评地址", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "date", value = "考评时间（自动获取当前系统时间）", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "year", value = "考评年份", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "state", value = "考评状态（1:启动考评，2:考评开始，3:考评结束）", paramType = "query", dataType = "List")
    })
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    @ResponseBody
    public Msg update(TestStart testStart) {
        try {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            testStart.setDate(simpleDateFormat.format(date));
            testStartService.update(testStart);
            return Msg.success().add("testStart", testStart);
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "获取启动表的所有记录,users中只包括id和名字")
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseBody
    public Msg findAll() {
        try {
            List<TestStart> tempTestStarts = testStartService.findAll();
            List<TestStart> testStarts = new ArrayList<>();
            for (TestStart testStart : tempTestStarts) {
                testStart.setSystem(new com.atits.entity.System(testStart.getSystem().getId(),testStart.getSystem().getSystemName()));
                Set<User> users = new HashSet();
                for (User user : testStart.getUsers()) {
                    if (user.getSystem()!=null) {
                        users.add(new User(user.getId(), user.getProfile().getName(), user.getSystem().getId()));
                    }else {
                        users.add(new User(user.getId(),user.getProfile().getName()));
                    }
                }
                testStart.setUsers(users);
                testStarts.add(testStart);
            }
            return Msg.success().add("testStarts", testStarts);
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "根据id查询一条考评启动记录")
    @RequestMapping(value = "findById", method = RequestMethod.GET)
    @ResponseBody
    public Msg findById(Integer id) {
        try {
            TestStart testStart = testStartService.findById(id);
            return Msg.success().add("testStart", testStart);
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新状态")
    @RequestMapping(value = "updateState", method = RequestMethod.PUT)
    public Msg updateState(int id, int state) {
        try {
            testStartService.updateState(id, state);
            return Msg.success();
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }


}
