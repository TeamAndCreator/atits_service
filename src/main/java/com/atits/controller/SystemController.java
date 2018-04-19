package com.atits.controller;

import com.atits.entity.Msg;
import com.atits.entity.System;
import com.atits.service.SystemService;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Api(description = "体系")
@RequestMapping(value = "system")
public class SystemController {

    @Resource
    private SystemService systemService;

    @ResponseBody
    @ApiOperation(value = "添加一个体系",notes = "添加一个体系")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "systemName",value = "体系名称",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "content",value = "体系描述",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "overView",value = "overView",dataType = "String",paramType = "query")
    })
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Msg save(System system){
        try{
            systemService.save(system);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e);
        }

    }

    @ResponseBody
    @ApiOperation(value = "通过id删除一个体系对象",notes = "通过id删除一个体系对象")
    @ApiImplicitParam(name = "id",value = "体系id",paramType = "query",dataType = "long")
    @RequestMapping(value = "deletbyid",method = RequestMethod.DELETE)
    public Msg deletById(Integer id){
        try {
            systemService.deletById(id);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e);
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id数组批量删除system",notes = "根据id数组批量删除system")
    @RequestMapping(value = "deletbyids",method = RequestMethod.DELETE)
    public Msg deletByIds(@ApiParam(name = "idList",value = "id数组")@RequestParam List<Integer> idList){
        try {
            systemService.deletByIds(idList);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e);
        }
    }

    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "体系id",dataType = "long",paramType = "query"),
            @ApiImplicitParam(name = "systemName",value = "体系名称",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "content",value = "体系描述",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "overView",value = "overView",dataType = "String",paramType = "query")
    })
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public Msg update(System system){
        try {
            systemService.update(system);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e);
        }
    }


    @ResponseBody
    @ApiOperation(value = "查找所有的体系",notes = "查找所有的体系")
    @RequestMapping(value = "findall",method = RequestMethod.GET)
    public Msg findAll(){
        try {
            return Msg.success().add("systems",systemService.findAll());
        }catch (Exception e){
            return Msg.fail(e);
        }
    }


    @ResponseBody
    @ApiOperation(value = "通过id获取一个体系对象",notes = "通过id获取一个体系对象")
    @ApiImplicitParam(name = "id",value = "体系id",paramType = "query",dataType = "long")
    @RequestMapping(value = "findbyid",method = RequestMethod.GET)
    public Msg findById(Integer id){
        try {
            System system=systemService.findById(id);
            return Msg.success().add("system",system);
        }catch (Exception e){
            return Msg.fail(e);
        }
    }


}
