package com.atits.controller;

import com.atits.entity.Harvest;
import com.atits.entity.Msg;
import com.atits.service.HarvestService;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Api(description = "重大成果")
@RequestMapping(value = "harvest")
public class HarvestController {
    @Resource
    private HarvestService harvestService;

    @ResponseBody
    @ApiOperation(value = "增加一个重大成果")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id",paramType = "query"),
            @ApiImplicitParam(name = "title",value = "标题",paramType = "query"),
            @ApiImplicitParam(name = "content",value = "描述",paramType = "query"),
            @ApiImplicitParam(name = "time",value = "时间",paramType = "query"),
            @ApiImplicitParam(name = "system.id",value = "所属体系id",paramType = "query"),
            @ApiImplicitParam(name = "user.id",value = "编辑人id",paramType = "query"),
            @ApiImplicitParam(name = "state",value = "状态",paramType = "query")
    })
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Msg save(Harvest harvest){
        try {
            harvestService.save(harvest);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e);
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id删除一个重大成果")
    @ApiImplicitParam(name = "id",value = "需要删除的重大成果id",paramType = "query")
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public Msg delete(Integer id){
        try {
            harvestService.deleteById(id);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e);
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id数组批量删除Harvest")
    @RequestMapping(value = "deleteByIds",method = RequestMethod.DELETE)
    public Msg deleteByIds(@ApiParam(name = "idList",value = "需删除重大成果的id数组")@RequestParam List<Integer> idList){
        try {
            harvestService.deleteByIds(idList);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e);
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新一个Harvest")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id",paramType = "query"),
            @ApiImplicitParam(name = "title",value = "标题",paramType = "query"),
            @ApiImplicitParam(name = "content",value = "描述",paramType = "query"),
            @ApiImplicitParam(name = "time",value = "时间",paramType = "query"),
            @ApiImplicitParam(name = "system.id",value = "所属体系id",paramType = "query"),
            @ApiImplicitParam(name = "user.id",value = "编辑人id",paramType = "query"),
            @ApiImplicitParam(name = "state",value = "状态",paramType = "query")
    })
    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public Msg update(Harvest harvest){
        try {
            harvestService.update(harvest);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e);
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id查找一个Harvest")
    @ApiImplicitParam(name = "id",value = "要查找的重大成果的id",paramType = "query",dataType = "long")
    @RequestMapping(value = "findById",method = RequestMethod.GET)
    public Msg findById(Integer id){
        try {
            return Msg.success().add("harvest",harvestService.findById(id));
        }catch (Exception e){
            return Msg.fail(e);
        }
    }

    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    @ApiOperation(value = "查找所有的重大成果",notes = "查找所有的重大成果")
    @ResponseBody
    public Msg findAll(){
        try {
            List<Harvest> harvests=harvestService.findAll();
            return Msg.success().add("harvests",harvests);
        }catch (Exception e){
            return Msg.fail(e);
        }
    }
}