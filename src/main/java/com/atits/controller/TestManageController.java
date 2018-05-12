package com.atits.controller;

import com.atits.entity.Msg;
import com.atits.entity.TestManage;
import com.atits.service.TestManageService;
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

@Controller
@Api(description = "考评管理")
@RequestMapping(value = "evaluate")
public class TestManageController {
    @Resource
    private TestManageService testManageService;

    /*
    1.考评状态：启动后，考评中时、记录导入（增:根据状态添加进来）
    2.考评状态：考评结束状态时、记录自动消失（删）
    3.改：不可修改---仅能进行考评，也就是打分
    4.查：考评中
     */

    /*
    启动表中，state = 2 的记录——导入管理表
     */

    @ApiOperation(value = "获取需要考评的所有记录")
    @RequestMapping(value = "manage_list",method = RequestMethod.GET)
    @ResponseBody
    public Msg findAll(){
        try{
            List<TestManage> testManages = testManageService.findAll();
            return Msg.success().add("testManages",testManages);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }


    /*
    实现自动导入---？

     */
    @ApiOperation(value = "启动后，自动导入考评记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "考评管理ID",paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "date",value = "考评日期（自动获取当前系统时间）",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "year",value = "考评年份（与启动表中年份相同）",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "examiner",value = "评分人（暂时别输）",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "examedner",value = "被评分人（暂时别输）",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "state",value = "考评状态（0：未考评,1：已考评）",paramType = "query",dataType = "int",required = true)
    })
    @RequestMapping(value = "manage_save",method = RequestMethod.POST)
    @ResponseBody
    public Msg save(TestManage testManage){
        try{
//            List<TestManage> testManages = testManageService.insertAuto();//插入年份、考评人员 考评人员怎么插入？
//                testManage.setState(0);//默认为未考评状态
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                testManage.setDate(simpleDateFormat.format(date));
                testManageService.save(testManage);
            return Msg.success().add("testManage",testManage);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }


}
