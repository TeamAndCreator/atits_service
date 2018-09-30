package com.atits.controller;

import com.atits.entity.Laboratory;
import com.atits.entity.Msg;
import com.atits.service.LaboratoryService;
import com.atits.entity.System;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation(value = "根据id查找一个Laboratory及研究室主任，岗位专家")
    @ApiImplicitParam(name = "id",value = "要查找的研究室id",paramType = "query",dataType = "long")
    @RequestMapping(value = "findById",method = RequestMethod.GET)
    public Msg findById(Integer id){
        try {
            Laboratory laboratory=laboratoryService.findById(id);
            List research_director=laboratoryService.findUserInRole(id,6);
            List job_expert=laboratoryService.findUserInRole(id,5);
            return Msg.success().add("laboratory",laboratory).add("research_director",research_director).add("job_expert",job_expert);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }


    @ResponseBody
    @ApiOperation(value="获取所有研究室的id，name，system，state")
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public Msg findAll() {
        try {
            List<Laboratory> laboratorys = laboratoryService.findAll();
            return Msg.success().add("Laboratory",laboratorys);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据体系，返回相应的lab集合")
    @RequestMapping(value = "findAll1",method = RequestMethod.GET)
    public Msg findAll1(int systemId){
        try {
            List laboratories=laboratoryService.findAll1(systemId);
            return Msg.success().add("laboratories",laboratories);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }


    @ResponseBody
    @ApiOperation(value = "分页查找若干个研究室",notes = "分页查找若干个研究室")
    @RequestMapping(value = "findPage",method = RequestMethod.GET)
    public Msg findPage(Integer page/*第几页*/){
        try {
            //声明每页显示的个数
            final int pageSize=10;
            //获取总个数
            int count=laboratoryService.getCount();
            //声明页数
            int pageTime;
            //计算页数
            if(count%pageSize==0){
                pageTime=count/pageSize;
            }else{
                pageTime=count/pageSize+1;
            }
            //获取该页所显示的名称，和对应id
            List laboratorys=laboratoryService.findPage((page-1)*pageSize,pageSize);
            //返回该页所需显示的名称和id，及总页数
            return Msg.success().add("laboratorys",laboratorys).add("pageTime",pageTime);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "获取某个体系的所有研究室id,name")
    @RequestMapping(value = "findAllInSystem",method = RequestMethod.GET)
    public Msg findAllInSystem(Integer systemId){
        try {
            List laboratorys=laboratoryService.findAllInSystem(systemId);
            return Msg.success().add("laboratorys",laboratorys);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新状态（激活）")
    @RequestMapping(value = "updateState",method = RequestMethod.PUT)
    public Msg updateState(int labId){
        try {
            laboratoryService.updateState(labId);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }


    @ResponseBody
    @ApiOperation(value = "获取研究室中具有某个权限的人")
    @GetMapping(value = "findUserInRole2")
    public Msg findUserInRole2(int laboratoryId, int roleId){
        try {
            List users=laboratoryService.findUserInRole2(laboratoryId, roleId);
            return Msg.success().add("users",users);
        }catch (Exception e){
            e.printStackTrace();
            return Msg.fail(e.getMessage());
        }
    }

}













