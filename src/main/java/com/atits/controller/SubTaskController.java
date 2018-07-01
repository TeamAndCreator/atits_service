package com.atits.controller;

import com.atits.entity.Files;
import com.atits.entity.Msg;
import com.atits.entity.SubTask;
import com.atits.entity.Task;
import com.atits.service.FilesService;
import com.atits.service.SubTaskService;
import com.atits.service.TaskService;
import com.atits.utils.GetTimeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
@Api(description = "子任务")
@RequestMapping(value = "subTask")
public class SubTaskController {

    @Resource
    private SubTaskService subTaskService;
    @Resource
    private TaskService taskService;
    @Resource
    private FilesService filesService;

    @ResponseBody
    @ApiOperation(value = "添加一个子任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title",value = "子任务标题",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "bearer.id",value = "承担者ID",paramType = "query",dataType = "Integer",required = true),
//            @ApiImplicitParam(name = "fatherTask.id",value = "所属体系任务ID（已存在的承担人的本体系任务）",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "startTime",value = "子任务开始时间（格式：yyyy-mm-dd）",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "endTime",value = "子任务结束时间（格式：yyyy-mm-dd）",paramType = "query",dataType = "String")
    })
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Msg save(SubTask subTask, MultipartFile[] multipartFiles){
        try{
            String date= GetTimeUtil.getDate();
            String time=GetTimeUtil.getTime();
//            if (!multipartFiles[0].isEmpty()){
//                Set<Files> filesSet=filesService.fileSave(multipartFiles,"体系任务",subTask.getBearer().getSystem().getId(),subTask.getBearer().getId(),date,time);
//                subTask.setFiles(filesSet);
//            }
            subTask.setTime(time);
            subTask.setDate(date);
            subTaskService.save(subTask);
            return Msg.success().add("subTask",subTask);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新一个子任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "子任务id（已存在的）",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "title",value = "子任务标题",paramType = "query",dataType = "Integer"),
            @ApiImplicitParam(name = "bearer.id",value = "承担者ID",paramType = "query",dataType = "Integer",required = true),
//            @ApiImplicitParam(name = "fatherTask.id",value = "所属体系任务ID（已存在的承担人的本体系任务）",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "startTime",value = "子任务开始时间（格式：yyyy-mm-dd）",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "endTime",value = "子任务结束时间（格式：yyyy-mm-dd）",paramType = "query",dataType = "String")
    })
    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public Msg update(SubTask subTask, MultipartFile[] multipartFiles){
        try{
            String date= GetTimeUtil.getDate();
            String time=GetTimeUtil.getTime();
//            if (!multipartFiles[0].isEmpty()){
//                Set<Files> filesSet=filesService.fileSave(multipartFiles,"体系任务",subTask.getBearer().getSystem().getId(),subTask.getBearer().getId(),date,time);
//                subTask.setFiles(filesSet);
//            }
            subTask.setTime(time);
            subTask.setDate(date);
            subTaskService.update(subTask);
            return Msg.success().add("subTask",subTask);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据子任务id删除一个子任务")
    @RequestMapping(value = "deleteById",method = RequestMethod.DELETE)
    public Msg deleteById(@RequestParam Integer id){
        try{
            subTaskService.deleteById(id);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据多个id批量删除子任务，需依次输入id时以英文逗号隔开")
    @RequestMapping(value = "deleteByIds",method = RequestMethod.DELETE)
    public Msg deleteByIds(@RequestParam List<Integer> idList){
        try{
            subTaskService.deleteByIds(idList);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "查询所有的子任务")
    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    public Msg findAll(){
        try{
            List<SubTask> subTasks = subTaskService.findAll();
            return Msg.success().add("subTasks",subTasks);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id查找一个子任务")
    @RequestMapping(value = "findById",method = RequestMethod.GET)
    public Msg findById(@RequestParam Integer id){
        try{
            SubTask subTask = subTaskService.findById(id);
            return Msg.success().add("subTask",subTask);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据承担人bearerId查找其所承担的子任务")
    @RequestMapping(value = "findByBearerId",method = RequestMethod.GET)
    public Msg findByBearerId(@RequestParam Integer bearerId){
        try{
            List<SubTask> subTasks = subTaskService.findByBearerId(bearerId);
            return Msg.success().add("subTasks",subTasks);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }


}
