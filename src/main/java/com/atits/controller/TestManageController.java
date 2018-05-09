package com.atits.controller;

import com.atits.entity.Msg;
import com.atits.entity.TestManage;
import com.atits.service.TestManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.objenesis.instantiator.sun.MagicInstantiator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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

    @ApiOperation(value = "启动后，自动导入考评记录")
    @RequestMapping(value = "import_evaluations",method = RequestMethod.GET)
    @ResponseBody
    public Msg importAuto(){
        try{
            List<TestManage> testManages = testManageService.insertAuto();
            return Msg.success().add("testManages",testManages);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }


}
