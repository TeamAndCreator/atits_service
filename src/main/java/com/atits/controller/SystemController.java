package com.atits.controller;

import com.atits.entity.Msg;
import com.atits.entity.System;
import com.atits.service.SystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@Api(description = "体系")
@RequestMapping(value = "system")
public class SystemController {

    @Resource
    private SystemService systemService;

    @ResponseBody
    @ApiOperation(value = "添加一个体系",notes = "添加一个体系")
    @ApiImplicitParam(name = "system",value = "从表单输入的一个体系",dataType = "SystemByJson")
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Msg save(@RequestParam("system") String json){
            JSONObject  jsonObject = JSONObject.fromObject(json);
            System system=(System)JSONObject.toBean(jsonObject,System.class);
        try{
            systemService.save(system);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e);
        }

    }


}
