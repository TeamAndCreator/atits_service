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
    @ApiOperation(value = "打分")
    @RequestMapping(value = "score",method = RequestMethod.PUT)
    public Msg score(int id,int A1,int A2,int A3,int A4,int A5,int A6){
        try {
            String time=GetTimeUtil.getTime();
            int sum=A1+A2+A3+A4+A5+A6;
            testScoreService.score(id,A1,A2,A3,A4,A5,A6,sum,time);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }

    }



}
