package com.atits.controller;

import com.atits.entity.Msg;
import com.atits.entity.Regulation;
import com.atits.service.RegulationService;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Api(description = "规章制度")
@RequestMapping(value = "regulation")
public class RegulationController {
    @Resource
    private RegulationService regulationService;

    @ResponseBody
    @ApiOperation(value = "增加一个规章制度")
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
    public Msg save(Regulation regulation){
        try {
            regulationService.save(regulation);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e);
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id删除一个规章制度")
    @ApiImplicitParam(name = "id",value = "需要删除的规章制度id",paramType = "query")
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public Msg delete(Integer id){
        try {
            regulationService.deleteById(id);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e);
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id数组批量删除Regulation")
    @RequestMapping(value = "deleteByIds",method = RequestMethod.DELETE)
    public Msg deleteByIds(@ApiParam(name = "idList",value = "需删除规章制度的id数组")@RequestParam List<Integer> idList){
        try {
            regulationService.deleteByIds(idList);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e);
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新一个Regulation")
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
    public Msg update(Regulation regulation){
        try {
            regulationService.update(regulation);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e);
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id查找一个Regulation")
    @ApiImplicitParam(name = "id",value = "要查找的规章制度的id",paramType = "query",dataType = "long")
    @RequestMapping(value = "findById",method = RequestMethod.GET)
    public Msg findById(Integer id){
        try {
            return Msg.success().add("regulation",regulationService.findById(id));
        }catch (Exception e){
            return Msg.fail(e);
        }
    }

    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    @ApiOperation(value = "查找所有的规章制度",notes = "查找所有的规章制度")
    @ResponseBody
    public Msg findAll(){
        try {
            List<Regulation> regulations=regulationService.findAll();
            return Msg.success().add("regulations",regulations);
        }catch (Exception e){
            return Msg.fail(e);
        }
    }
}
