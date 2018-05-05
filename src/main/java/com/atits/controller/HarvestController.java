package com.atits.controller;

import com.atits.entity.Harvest;
import com.atits.entity.Files;
import com.atits.entity.Msg;
import com.atits.service.HarvestService;
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
@Api(description = "重大成果")
@RequestMapping(value = "harvest")
public class HarvestController {

    @Resource
    private HarvestService harvestService;

    @Resource
    private FilesService filesService;

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
    public Msg save(Harvest harvest, MultipartFile[] multipartFiles){
        try {
            String date= GetTimeUtil.getDate();
            String time=GetTimeUtil.getTime();
            if (!multipartFiles[0].isEmpty()){
                Set<Files> filesSet=filesService.fileSave(multipartFiles,"重大成果",harvest.getSystem().getId(),harvest.getUser().getId(),date,time);
                harvest.setFiles(filesSet);
            }
            harvest.setDate(date);
            harvest.setTime(time);
            harvestService.save(harvest);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id删除一个重大成果")
    @ApiImplicitParam(name = "id",value = "需要删除的重大成果id",paramType = "query")
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public Msg delete(Integer id){
        try {
            Harvest harvest=harvestService.findById(id);
            Set<Files> filesSet=harvest.getFiles();
            filesService.deleteFiles(filesSet);
            harvestService.deleteById(id);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id数组批量删除Harvest")
    @RequestMapping(value = "deleteByIds",method = RequestMethod.DELETE)
    public Msg deleteByIds(@ApiParam(name = "idList",value = "需删除重大成果的id数组")@RequestParam List<Integer> idList){
        try {
            for (Integer id:idList){
                Harvest harvest=harvestService.findById(id);
                Set<Files> filesSet=harvest.getFiles();
                filesService.deleteFiles(filesSet);
            }
            harvestService.deleteByIds(idList);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
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
    public Msg update(Harvest harvest,MultipartFile[] multipartFiles){
        try {
            String date=GetTimeUtil.getDate();
            String time=GetTimeUtil.getTime();
            //查出原文件并删除
            Set<Files> oldFilesSet=harvestService.getFiles(harvest.getId());
            filesService.deleteDoubleFiles(oldFilesSet);
            if (!multipartFiles[0].isEmpty()){
                Set<Files> newFilesSet=filesService.fileSave(multipartFiles,"重大成果",harvest.getSystem().getId(),harvest.getUser().getId(),date,time);
                harvest.setFiles(newFilesSet);
            }
            harvest.setDate(date);
            harvest.setTime(time);
            harvestService.update(harvest);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
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
            return Msg.fail(e.getMessage());
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
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "分页查找若干个重大成果",notes = "分页查找若干个重大成果")
    @RequestMapping(value = "findPage",method = RequestMethod.GET)
    public Msg findPage(Integer page/*第几页*/){
        try {
            //声明每页显示的个数
            final int pageSize = 10;
            //获取总个数
            int count = harvestService.getCount();
            //声明页数
            int pageTime;
            //计算页数
            if (count % pageSize == 0) {
                pageTime = count / pageSize;
            } else {
                pageTime = count / pageSize + 1;
            }
            //获取该页所显示的名称，和对应id
            List harvests = harvestService.findPage((page - 1) * pageSize, pageSize);
            //返回该页所需显示的名称和id，及总页数
            return Msg.success().add("harvests", harvests).add("pageTime", pageTime);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "获取所有id，title，date")
    @RequestMapping(value = "findAll1",method = RequestMethod.GET)
    public Msg findAll1(){
        try {
            List harvests=harvestService.findAll1();
            return Msg.success().add("harvests",harvests);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }

    }
}
