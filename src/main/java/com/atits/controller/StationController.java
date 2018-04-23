package com.atits.controller;

import com.atits.entity.Msg;
import com.atits.entity.Station;
import com.atits.service.StationService;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Api(description = "实验站")
@RequestMapping(value = "station")
public class StationController {
    @Resource
    private StationService stationService;

    @ResponseBody
    @ApiOperation(value = "添加一个实验站")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "staName",value = "实验站名称",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "content",value = "实验站描述",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "company",value = "实验站依托单位",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "time",value = "实验站注册时间",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "state",value = "实验站状态",paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "system.id",value = "实验站所属体系id",paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "system.systemName",value = "实验站所属体系名称",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "system.content",value = "实验站所属体系描述",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "system.overView",value = "实验站所属体系overView",paramType = "query",dataType = "String")
    })
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Msg save(Station station){
        try {
            stationService.save(station);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e);
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id删除一个实验站")
    @ApiImplicitParam(name = "id",value = "需删除实验站的id",paramType = "query",dataType = "long")
    @RequestMapping(value = "deleteById",method = RequestMethod.DELETE)
    public Msg deleteById(Integer id){
        try {
            stationService.deleteById(id);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e);
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id数组批量删除station")
    @RequestMapping(value = "deleteByIds",method = RequestMethod.DELETE)
    public Msg deleteByIds(@ApiParam(name = "idList",value = "需删除实验站的id数组")@RequestParam List<Integer> idList){
        try {
            stationService.deleteByIds(idList);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e);
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新一个station")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "需更新的实验站id",paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "staName",value = "实验站名称",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "content",value = "实验站描述",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "company",value = "实验站依托单位",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "time",value = "实验站注册时间",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "state",value = "实验站状态",paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "system.id",value = "实验站所属体系id",paramType = "query",dataType = "long"),
            @ApiImplicitParam(name = "system.systemName",value = "实验站所属体系名称",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "system.content",value = "实验站所属体系描述",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "system.overView",value = "实验站所属体系overView",paramType = "query",dataType = "String")
    })
    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public Msg update(Station station){
        try {
            stationService.update(station);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e);
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id查找一个station")
    @ApiImplicitParam(name = "id",value = "要查找的实验站id",paramType = "query",dataType = "long")
    @RequestMapping(value = "findById",method = RequestMethod.GET)
    public Msg findById(Integer id){
        try {
            return Msg.success().add("station",stationService.findById(id));
        }catch (Exception e){
            return Msg.fail(e);
        }
    }


    @ResponseBody
    @ApiOperation(value="获取所有实验站详细信息")
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public Msg findAll() {
        try {
            List<Station> stations = stationService.findAll();
            return Msg.success().add("Station",stations);
        }catch (Exception e){
            return Msg.fail(e);
        }
    }
}
