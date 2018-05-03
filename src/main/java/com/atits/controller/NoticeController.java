package com.atits.controller;

import com.atits.entity.Files;
import com.atits.entity.Msg;
import com.atits.entity.Notice;
import com.atits.service.FilesService;
import com.atits.service.NoticeService;
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
@Api(description = "通知公告")
@RequestMapping(value = "notice")
public class NoticeController {

    @Resource
    private NoticeService noticeService;

    @Resource
    private FilesService filesService;

    @ResponseBody
    @ApiOperation(value = "增加一个通知公告")
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
    public Msg save(Notice notice, MultipartFile[] multipartFiles){
        try {
            String date= GetTimeUtil.getDate();
            String time=GetTimeUtil.getTime();
            if (!multipartFiles[0].isEmpty()){
                Set<Files> filesSet=filesService.fileSave(multipartFiles,"通知公告",notice.getSystem().getId(),notice.getUser().getId(),date,time);
                notice.setFiles(filesSet);
            }
            notice.setDate(date);
            notice.setTime(time);
            noticeService.save(notice);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id删除一个通知公告")
    @ApiImplicitParam(name = "id",value = "需要删除的通知公告id",paramType = "query")
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public Msg delete(Integer id){
        try {
            Notice notice=noticeService.findById(id);
            Set<Files> filesSet=notice.getFiles();
            filesService.deleteFiles(filesSet);
            noticeService.deleteById(id);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id数组批量删除notice")
    @RequestMapping(value = "deleteByIds",method = RequestMethod.DELETE)
    public Msg deleteByIds(@ApiParam(name = "idList",value = "需删除通知公告的id数组")@RequestParam List<Integer> idList){
        try {
            for (Integer id:idList){
                Notice notice=noticeService.findById(id);
                Set<Files> filesSet=notice.getFiles();
                filesService.deleteFiles(filesSet);
            }
            noticeService.deleteByIds(idList);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新一个notice")
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
    public Msg update(Notice notice,MultipartFile[] multipartFiles){
        try {
            String date=GetTimeUtil.getDate();
            String time=GetTimeUtil.getTime();
            //查出原文件并删除
            Set<Files> oldFilesSet=noticeService.getFiles(notice.getId());
            filesService.deleteDoubleFiles(oldFilesSet);
            if (!multipartFiles[0].isEmpty()){
                Set<Files> newFilesSet=filesService.fileSave(multipartFiles,"通知公告",notice.getSystem().getId(),notice.getUser().getId(),date,time);
                notice.setFiles(newFilesSet);
            }
            notice.setDate(date);
            notice.setTime(time);
            noticeService.update(notice);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id查找一个notice")
    @ApiImplicitParam(name = "id",value = "要查找的通知公告的id",paramType = "query",dataType = "long")
    @RequestMapping(value = "findById",method = RequestMethod.GET)
    public Msg findById(Integer id){
        try {
            return Msg.success().add("notice",noticeService.findById(id));
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    @ApiOperation(value = "查找所有的通知公告",notes = "查找所有的通知公告")
    @ResponseBody
    public Msg findAll(){
        try {
            List<Notice> notices=noticeService.findAll();
            return Msg.success().add("notices",notices);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }
}
