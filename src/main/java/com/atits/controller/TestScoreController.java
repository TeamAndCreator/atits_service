package com.atits.controller;

import com.atits.entity.Msg;
import com.atits.entity.TestManage;
import com.atits.entity.TestScore;
import com.atits.service.TestManageService;
import com.atits.service.TestScoreService;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@Api(description = "考评打分")
@RequestMapping(value = "testscore")
public class TestScoreController {
    @Resource
    private TestScoreService testScoreService;

    public TestScoreService getTestScoreService() {
        return testScoreService;
    }

    public void setTestScoreService(TestScoreService testScoreService) {
        this.testScoreService = testScoreService;
    }


    /**
     * 保存方法：save方法
     *
     * @return
     * @paramt_test_manage
     */

    @ApiOperation(value = "添加一条打分记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "打分表id，自增长模式",paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "A1",value = "第一项打分",paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "A2",value = "第二项打分",paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "A3",value = "第三项打分",paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "A4",value = "第四项打分",paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "A5",value = "第五项打分",paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "A6",value = "第六项打分",paramType = "query",dataType = "int")
    })
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Msg save(TestScore testScore){
        try{
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            testScore.setTime(simpleDateFormat.format(date));
            int sum = testScore.getA1()+testScore.getA2()+testScore.getA3()+testScore.getA4()+testScore.getA5()+testScore.getA6();
            testScore.setSum(sum);
            testScoreService.save(testScore);
            return Msg.success().add("testScore",testScore);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "根据id更新一条打分记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "输入需更新记录的id",paramType = "query",dataType = "int",required = true),
            @ApiImplicitParam(name = "A1",value = "第一项打分",paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "A2",value = "第二项打分",paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "A3",value = "第三项打分",paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "A4",value = "第四项打分",paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "A5",value = "第五项打分",paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "A6",value = "第六项打分",paramType = "query",dataType = "int")
    })
    @RequestMapping(value = "update",method = RequestMethod.PUT)
    @ResponseBody
    public Msg update(TestScore testScore){
        try{
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            testScore.setTime(simpleDateFormat.format(date));
            int sum = testScore.getA1()+testScore.getA2()+testScore.getA3()+testScore.getA4()+testScore.getA5()+testScore.getA6();
            testScore.setSum(sum);
            testScoreService.update(testScore);
            return Msg.success().add("testScore",testScore);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    /**
     * 删除方法：delete方法
     * @param
     * @return
     */

    @ApiOperation(value = "根据id删除一条打分记录")
    @RequestMapping(value = "deleteById",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteById(@RequestParam Integer id){
        try{
            testScoreService.deleteById(id);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "根据idList批量删除打分记录")
    @RequestMapping(value = "deleteByIds",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteByIds(
            @ApiParam(name = "idList",value = "需删除启动记录的id数组，依次以英文逗号隔开输入")@RequestParam List<Integer> idList) {
        try{
            testScoreService.deleteByIds(idList);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    /**
     * 根据testmanageId查询testscores的方法：findAll
     *
     * @param
     * @return
     */
    @ApiOperation(value = "查询所有的打分记录")
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseBody
    public Msg findAll(){
        try{
            List<TestScore> testScores = testScoreService.findAll();
            return Msg.success().add("testScores",testScores);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "根据id查询一条打分记录")
    @RequestMapping(value = "findById",method = RequestMethod.GET)
    @ResponseBody
    public Msg findById(Integer id){
        try{
            TestScore testScore = testScoreService.findById(id);
            return Msg.success().add("testScore",testScore);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }


}
