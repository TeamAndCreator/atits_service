package com.atits.controller;

import com.atits.entity.Dynamic;
import com.atits.entity.Msg;
import com.atits.service.DynamicService;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Api(description = "体系动态")
@RequestMapping(value = "dynamic")
public class DynamicController {
    @Resource
    private DynamicService dynamicService;

    @ResponseBody
    @ApiOperation(value = "增加一个体系动态")
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
    public Msg save(Dynamic dynamic){
        try {
            dynamicService.save(dynamic);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e);
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id删除一个体系动态")
    @ApiImplicitParam(name = "id",value = "需要删除的体系动态id",paramType = "query")
    @RequestMapping(value = "delet",method = RequestMethod.DELETE)
    public Msg delet(Integer id){
        try {
            dynamicService.deletById(id);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e);
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id数组批量删除Dynamic")
    @RequestMapping(value = "deletbyids",method = RequestMethod.DELETE)
    public Msg deletByIds(@ApiParam(name = "idList",value = "需删除体系动态的id数组")@RequestParam List<Integer> idList){
        try {
            dynamicService.deletByIds(idList);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e);
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新一个Dynamic")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id",paramType = "query"),
            @ApiImplicitParam(name = "title",value = "标题",paramType = "query"),
            @ApiImplicitParam(name = "content",value = "描述",paramType = "query"),
            @ApiImplicitParam(name = "time",value = "时间",paramType = "query"),
            @ApiImplicitParam(name = "system.id",value = "所属体系id",paramType = "query"),
            @ApiImplicitParam(name = "user.id",value = "编辑人id",paramType = "query"),
            @ApiImplicitParam(name = "state",value = "状态",paramType = "query")
    })
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public Msg update(Dynamic dynamic){
        try {
            dynamicService.update(dynamic);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e);
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id查找一个Dynamic")
    @ApiImplicitParam(name = "id",value = "要查找的体系动态的id",paramType = "query",dataType = "long")
    @RequestMapping(value = "findbyid",method = RequestMethod.GET)
    public Msg findById(Integer id){
        try {
            return Msg.success().add("dynamic",dynamicService.findById(id));
        }catch (Exception e){
            return Msg.fail(e);
        }
    }

    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    @ApiOperation(value = "查找所有的体系动态",notes = "查找所有的体系动态")
    @ResponseBody
    public Msg findAll(){
        try {
            List<Dynamic> dynamics=dynamicService.findAll();
            return Msg.success().add("dynamics",dynamics);
        }catch (Exception e){
            return Msg.fail(e);
        }
    }
}
