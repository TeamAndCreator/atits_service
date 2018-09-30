package com.atits.controller;

import com.atits.entity.Msg;
import com.atits.entity.Station;
import com.atits.service.StationService;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
            return Msg.fail(e.getMessage());
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
            return Msg.fail(e.getMessage());
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
            e.printStackTrace();
            return Msg.fail(e.getMessage());
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
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id查找一个station及综合实验站站长")
    @ApiImplicitParam(name = "id",value = "要查找的实验站id",paramType = "query",dataType = "long")
    @RequestMapping(value = "findById",method = RequestMethod.GET)
    public Msg findById(Integer id){
        try {
            Station station=stationService.findById(id);
            List station_master=stationService.findUserInRole(id,7);
            return Msg.success().add("station",station).add("station_master",station_master);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }


    @ResponseBody
    @ApiOperation(value="获取所有实验站的id，name，systemName,state")
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public Msg findAll() {
        try {
            List<Station> stations = stationService.findAll();
            return Msg.success().add("Station",stations);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据体系，返回相应的sta集合")
    @RequestMapping(value = "findAll1",method = RequestMethod.GET)
    public Msg findAll1(int systemId){
        try {
            List stations=stationService.findAll1(systemId);
            return Msg.success().add("stations",stations);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "分页查找若干个实验站",notes = "分页查找若干个实验站")
    @RequestMapping(value = "findPage",method = RequestMethod.GET)
    public Msg findPage(Integer page/*第几页*/){
        try {
            //声明每页显示的个数
            final int pageSize=10;
            //获取总个数
            int count=stationService.getCount();
            //声明页数
            int pageTime;
            //计算页数
            if(count%pageSize==0){
                pageTime=count/pageSize;
            }else{
                pageTime=count/pageSize+1;
            }
            //获取该页所显示的名称，和对应id
            List stations=stationService.findPage((page-1)*pageSize,pageSize);
            //返回该页所需显示的名称和id，及总页数
            return Msg.success().add("stations",stations).add("pageTime",pageTime);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "获取某个体系的所有实验站id,name")
    @RequestMapping(value = "findAllInSystem",method = RequestMethod.GET)
    public Msg findAllInSystem(Integer systemId){
        try {
            List stations=stationService.findAllInSystem(systemId);
            return Msg.success().add("stations",stations);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新状态（激活）")
    @RequestMapping(value = "updateState",method = RequestMethod.PUT)
    public Msg updateState(int staId){
        try {
            stationService.updateState(staId);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "获取实验站中具有某个权限的人")
    @GetMapping(value = "findUserInRole2")
    public Msg findUserInRole2(int stationId, int roleId){
        try {
            List users=stationService.findUserInRole2(stationId, roleId);
            return Msg.success().add("users",users);
        }catch (Exception e){
            e.printStackTrace();
            return Msg.fail(e.getMessage());
        }
    }

}
