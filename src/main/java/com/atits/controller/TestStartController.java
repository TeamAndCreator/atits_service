package com.atits.controller;


import com.atits.entity.Msg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(description = "考评")
@RequestMapping(value = "kaopin")
public class TestStartController {

    @ApiOperation(value = "考评启动")
    @RequestMapping(value = "/start",method = RequestMethod.GET)
    @ResponseBody
    public Msg start(){


        return Msg.fail("");
    }


}
