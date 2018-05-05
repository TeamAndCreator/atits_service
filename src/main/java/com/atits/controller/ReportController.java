package com.atits.controller;

import com.atits.entity.Report;
import com.atits.entity.Files;
import com.atits.entity.Msg;
import com.atits.service.ReportService;
import com.atits.service.FilesService;
import com.atits.utils.GetTimeUtil;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Controller
@Api(description = "重大文件")
@RequestMapping(value = "report")
public class ReportController {

    @Resource
    private ReportService reportService;

    @Resource
    private FilesService filesService;

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
    public Msg save(Report report, MultipartFile[] multipartFiles){
        try {
            String date= GetTimeUtil.getDate();
            String time=GetTimeUtil.getTime();
            if (!multipartFiles[0].isEmpty()){
                Set<Files> filesSet=filesService.fileSave(multipartFiles,"重大文件",report.getSystem().getId(),report.getUser().getId(),date,time);
                report.setFiles(filesSet);
            }
            report.setDate(date);
            report.setTime(time);
            reportService.save(report);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id删除一个重大文件")
    @ApiImplicitParam(name = "id",value = "需要删除的重大文件id",paramType = "query")
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public Msg delete(Integer id){
        try {
            Report report=reportService.findById(id);
            Set<Files> filesSet=report.getFiles();
            filesService.deleteFiles(filesSet);
            reportService.deleteById(id);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id数组批量删除Report")
    @RequestMapping(value = "deleteByIds",method = RequestMethod.DELETE)
    public Msg deleteByIds(@ApiParam(name = "idList",value = "需删除重大文件的id数组")@RequestParam List<Integer> idList){
        try {
            for (Integer id:idList){
                Report report=reportService.findById(id);
                Set<Files> filesSet=report.getFiles();
                filesService.deleteFiles(filesSet);
            }
            reportService.deleteByIds(idList);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
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
    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public Msg update(Report report,MultipartFile[] multipartFiles){
        try {
            String date=GetTimeUtil.getDate();
            String time=GetTimeUtil.getTime();
            //查出原文件并删除
            Set<Files> oldFilesSet=reportService.getFiles(report.getId());
            filesService.deleteDoubleFiles(oldFilesSet);
            if (!multipartFiles[0].isEmpty()){
                Set<Files> newFilesSet=filesService.fileSave(multipartFiles,"重大文件",report.getSystem().getId(),report.getUser().getId(),date,time);
                report.setFiles(newFilesSet);
            }
            report.setDate(date);
            report.setTime(time);
            reportService.update(report);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id查找一个Report")
    @ApiImplicitParam(name = "id",value = "要查找的重大文件的id",paramType = "query",dataType = "long")
    @RequestMapping(value = "findById",method = RequestMethod.GET)
    public Msg findById(Integer id){
        try {
            return Msg.success().add("report",reportService.findById(id));
        }catch (Exception e){
            return Msg.fail(e.getMessage());
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
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "分页查找若干个重大文件",notes = "分页查找若干个重大文件")
    @RequestMapping(value = "findPage",method = RequestMethod.GET)
    public Msg findPage(Integer page/*第几页*/){
        try {
            //声明每页显示的个数
            final int pageSize=10;
            //获取总个数
            int count=reportService.getCount();
            //声明页数
            int pageTime;
            //计算页数
            if(count%pageSize==0){
                pageTime=count/pageSize;
            }else{
                pageTime=count/pageSize+1;
            }
            //获取该页所显示的名称，和对应id
            List reports=reportService.findPage((page-1)*pageSize,pageSize);
            //返回该页所需显示的名称和id，及总页数
            return Msg.success().add("reports",reports).add("pageTime",pageTime);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "获取所有id，title，date")
    @RequestMapping(value = "findAll1",method = RequestMethod.GET)
    public Msg findAll1(){
        try {
            List reports=reportService.findAll1();
            return Msg.success().add("reports",reports);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }

    }
}
