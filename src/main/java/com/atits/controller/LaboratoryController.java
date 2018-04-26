package com.atits.controller;

import com.atits.entity.Laboratory;
import com.atits.entity.Msg;
import com.atits.service.LaboratoryService;
import com.atits.entity.System;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Api(description = "研究室")
@RequestMapping(value = "laboratory")
public class LaboratoryController {

    @Resource
    private LaboratoryService laboratoryService;

    @ResponseBody
    @ApiOperation(value = "添加一个研究室")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "labName",value = "研究室名称",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "content",value = "研究室描述",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "company",value = "研究室依托单位",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "time",value = "研究室注册时间",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "state",value = "研究室状态",paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "system.id",value = "研究室所属体系id",paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "system.systemName",value = "研究室所属体系名称",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "system.content",value = "研究室所属体系描述",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "system.overView",value = "研究室所属体系overView",paramType = "query",dataType = "String")
    })
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Msg save(Laboratory laboratory){
        try {
            laboratoryService.save(laboratory);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id删除一个研究室")
    @ApiImplicitParam(name = "id",value = "需删除研究室的id",paramType = "query",dataType = "long")
    @RequestMapping(value = "deleteByid",method = RequestMethod.DELETE)
    public Msg deleteById(Integer id){
        try {
            laboratoryService.deleteById(id);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id数组批量删除Laboratory")
    @RequestMapping(value = "deleteByIds",method = RequestMethod.DELETE)
    public Msg deleteByIds(@ApiParam(name = "idList",value = "需删除研究室的id数组")@RequestParam List<Integer> idList){
        try {
            laboratoryService.deleteByIds(idList);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新一个Laboratory")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "需更新的研究室id",paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "labName",value = "研究室名称",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "content",value = "研究室描述",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "company",value = "研究室依托单位",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "time",value = "研究室注册时间",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "state",value = "研究室状态",paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "system.id",value = "研究室所属体系id",paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "system.systemName",value = "研究室所属体系名称",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "system.content",value = "研究室所属体系描述",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "system.overView",value = "研究室所属体系overView",paramType = "query",dataType = "String")
    })
    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public Msg update(Laboratory laboratory){
        try {
            laboratoryService.update(laboratory);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id查找一个Laboratory")
    @ApiImplicitParam(name = "id",value = "要查找的研究室id",paramType = "query",dataType = "long")
    @RequestMapping(value = "findById",method = RequestMethod.GET)
    public Msg findById(Integer id){
        try {
            return Msg.success().add("Laboratory",laboratoryService.findById(id));
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }


    @ResponseBody
    @ApiOperation(value="获取所有研究室详细信息")
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public Msg findAll() {
        try {
            List<Laboratory> laboratorys = laboratoryService.findAll();
            return Msg.success().add("Laboratory",laboratorys);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

}
