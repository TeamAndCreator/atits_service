package com.atits.controller;

import com.atits.entity.Msg;
import com.atits.entity.TestManage;
import com.atits.entity.TestScore;
import com.atits.service.TestManageService;
import com.atits.service.TestScoreService;
import com.atits.utils.GetTimeUtil;
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

    @ResponseBody
    @ApiOperation(value = "根据user.id获取其所有testScore")
    @RequestMapping(value = "findByEvaluation",method = RequestMethod.GET)
    public Msg findByEvaluation(Integer evaluationId){
        try {
            List<TestScore> testScores=testScoreService.findByEvaluation(evaluationId);
            return Msg.success().add("testScores",testScores);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }


    @ResponseBody
    @ApiOperation(value = "根据id获取打分项")
    @GetMapping(value = "findById")
    public Msg findById(int id){
        try {
            TestScore testScore=testScoreService.findById(id);
            return Msg.success().add("testScore",testScore);
        }catch (Exception e){
            e.printStackTrace();
            return Msg.fail(e.getMessage());
        }
    }


    @ResponseBody
    @ApiOperation(value = "打分")
    @RequestMapping(value = "score",method = RequestMethod.PUT)
    public Msg score(int id,double A1,double A2,double A3,double A4,double A5,double A6,double A7){
        try {
            System.out.println("id="+id);
            System.out.println("a1"+A1);
            System.out.println("a2"+A2);
            System.out.println("a3"+A3);
            System.out.println("a4"+A4);
            System.out.println("a5"+A5);
            System.out.println("a6"+A6);
            System.out.println("a7"+A7);
            String time=GetTimeUtil.getTime();
            double sum=A1+A2+A3+A4+A5+A6+A7;
            testScoreService.score(id,A1,A2,A3,A4,A5,A6,A7,sum,time);
            return Msg.success();
        }catch (Exception e){
            e.printStackTrace();
            return Msg.fail(e.getMessage());
        }

    }



}
