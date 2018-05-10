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
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @ApiOperation(value = "通过体系ID、角色ID来筛选出参评人员，再从中挑选出此次的参评人员")//通过体系ID、角色ID来查找参评人员
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "year",value = "考评年份",paramType = "query",required = true,dataType = "String"),
//            @ApiImplicitParam(name = "address",value = "考评地址",paramType = "query",required = true,dataType = "String")
//    })
    @RequestMapping(value = "import_persons",method = RequestMethod.GET)
    @ResponseBody
    public Msg importPersons(Integer sysId, Integer roleId){
        try {
            if (sysId !=null && roleId!=null){
                List<User> users = userService.findTestPer(sysId,roleId);//返回是一个仅含有用户名的数组
                return Msg.success().add("users",users);
            }else
                return Msg.fail("均不能为空！");
        }catch(Exception e){
            return Msg.fail(e.getMessage());
        }
    }

/*
未完成
 */
    @ApiOperation(value = "添加考评记录--》未完成！！！")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "address",value = "考评地址",paramType = "query",dataType = "String"),
        @ApiImplicitParam(name = "year",value = "考评年份",paramType = "query",dataType = "String")
})
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Msg save(){
        //year:当前年份的第二年  address 手动输入     state初始化为1：“启动考评”  date：自动获取插入时间
        //实现把页面输入的数据插入表中
        //考评人员、年份、地址要怎么获取到表中？当考评人员为空时不可添加
        //当选中要添加的考评人员后，实现以下的自动插入
        try{
            TestStart testStart = new TestStart();
            if (testStart.getUsers()!= null){
                testStart.setState(1);//初始状态：“启动考评”
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                testStart.setDate(simpleDateFormat.format(date));
//            testStart.setYear(date.);
                testStartService.save(testStart);
                return Msg.success().add("testStarts",testStartService.findAll());
            }else {
                return Msg.fail("未添加考评人员！");
            }
        }catch (Exception e){
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
