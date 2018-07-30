package com.atits.controller;

import com.atits.entity.Msg;
import com.atits.entity.TestWeight;
import com.atits.service.TestWeightService;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
@Api(description = "考评权重设置")
@RequestMapping(value = "testweight")
public class TestWeightController {
    @Resource
    private TestWeightService testWeightService;

    @ResponseBody
    @ApiOperation(value = "更新一个")
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public Msg update(TestWeight testWeight){
        try {
            testWeightService.update(testWeight);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }


}
