package com.atits.controller;

import com.atits.entity.Msg;
import com.atits.entity.Report;
import com.atits.service.ReportService;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Api(description = "重大文件")
@RequestMapping(value = "report")
public class ReportController {
    @Resource
    private ReportService reportService;

    @ResponseBody
    @ApiOperation(value = "增加一个重大文件")
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
    public Msg save(Report report){
        try {
            reportService.save(report);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e);
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id删除一个重大文件")
    @ApiImplicitParam(name = "id",value = "需要删除的重大文件id",paramType = "query")
    @RequestMapping(value = "delet",method = RequestMethod.DELETE)
    public Msg delet(Integer id){
        try {
            reportService.deletById(id);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e);
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id数组批量删除Report")
    @RequestMapping(value = "deletbyids",method = RequestMethod.DELETE)
    public Msg deletByIds(@ApiParam(name = "idList",value = "需删除重大文件的id数组")@RequestParam List<Integer> idList){
        try {
            reportService.deletByIds(idList);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e);
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新一个Report")
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
    public Msg update(Report report){
        try {
            reportService.update(report);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e);
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id查找一个Report")
    @ApiImplicitParam(name = "id",value = "要查找的重大文件的id",paramType = "query",dataType = "long")
    @RequestMapping(value = "findbyid",method = RequestMethod.GET)
    public Msg findById(Integer id){
        try {
            return Msg.success().add("report",reportService.findById(id));
        }catch (Exception e){
            return Msg.fail(e);
        }
    }

    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    @ApiOperation(value = "查找所有的重大文件",notes = "查找所有的重大文件")
    @ResponseBody
    public Msg findAll(){
        try {
            List<Report> reports=reportService.findAll();
            return Msg.success().add("reports",reports);
        }catch (Exception e){
            return Msg.fail(e);
        }
    }
}
