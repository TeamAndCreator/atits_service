package com.atits.controller;


import com.atits.entity.Files;
import com.atits.entity.Msg;
import com.atits.entity.SubTask;
import com.atits.entity.TaskProgress;
import com.atits.service.FilesService;
import com.atits.service.SubTaskService;
import com.atits.service.TaskProgressService;
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
@Api(description = "工作进展")
@RequestMapping(value = "taskProgress")
public class TaskProgressController {

    @Resource
    private TaskProgressService taskProgressService;
    @Resource
    private FilesService filesService;
    @Resource
    private SubTaskService subTaskService;

    @ResponseBody
    @ApiOperation(value = "添加一项工作进展")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title",value = "工作进展标题",paramType = "query",dataType = "String",required = true),
//            @ApiImplicitParam(name = "subTask.id",value = "所属子任务Id",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "state",value = "默认状态为0（1-通过 2-不通过 0-审核中）",paramType = "query",dataType = "Integer")
    })
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Msg save(TaskProgress taskProgress, MultipartFile[] multipartFiles){
        try{
            String date= GetTimeUtil.getDate();
            String time=GetTimeUtil.getTime();
//            if (!multipartFiles[0].isEmpty()){
//                Set<Files> filesSet=filesService.fileSave(multipartFiles,"工作进展",taskProgress.getBearer().getSystem().getId(),taskProgress.getBearer().getId(),date,time);
//                taskProgress.setFiles(filesSet);
//            }
//            taskProgress.setSubTask(subTaskService.findById(taskProgress.getSubTask().getId()));
            taskProgress.setTime(time);
            taskProgress.setDate(date);
            taskProgress.setState(0);//初始状态添加后为审核中
            taskProgressService.save(taskProgress);
            return Msg.success().add("taskProgress",taskProgress);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新一项工作进展")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "工作进展id（已存在的）",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "title",value = "工作进展标题",paramType = "query",dataType = "String",required = true),
//            @ApiImplicitParam(name = "subTask.id",value = "所属子任务Id",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "state",value = "默认状态为0（1-通过 2-不通过 0-审核中）",paramType = "query",dataType = "Integer")
    })
    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public Msg update(TaskProgress taskProgress, MultipartFile[] multipartFiles){
        try{
            String date= GetTimeUtil.getDate();
            String time=GetTimeUtil.getTime();
//            if (!multipartFiles[0].isEmpty()){
//                Set<Files> filesSet=filesService.fileSave(multipartFiles,"工作进展",taskProgress .getBearer().getSystem().getId(),taskProgress.getBearer().getId(),date,time);
//                taskProgress .setFiles(filesSet);
//            }
            taskProgress .setTime(time);
            taskProgress .setDate(date);
            taskProgress.setState(0);//重新提交后需要重新审核
            taskProgressService.update(taskProgress);
            return Msg.success().add("taskProgress",taskProgress);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据工作进展Id删除一项工作进展")
    @RequestMapping(value = "deleteById",method = RequestMethod.DELETE)
    public Msg deleteById(@RequestParam Integer id){
        try{
            taskProgressService.deleteById(id);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据多个Id批量删除工作进展，需依次输入id时以英文逗号隔开")
    @RequestMapping(value = "deleteByIds",method = RequestMethod.DELETE)
    public Msg deleteByIds(@RequestParam List<Integer> idList){
        try{
            taskProgressService.deleteByIds(idList);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "查询所有的工作进展")
    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    public Msg findAll(){
        try{
            List<TaskProgress> taskProgresses = taskProgressService.findAll();
            return Msg.success().add("taskProgresses",taskProgresses);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id查询一项工作进展")
    @RequestMapping(value = "findById",method = RequestMethod.GET)
    public Msg findById(@RequestParam Integer id){
        try{
            TaskProgress taskProgress = taskProgressService.findById(id);
            return Msg.success().add("taskProgress",taskProgress);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据承担人bearerId查找其所承担的子任务的所有工作进展")
    @RequestMapping(value = "findByBearerId",method = RequestMethod.GET)
    public Msg findByBearerId(@RequestParam Integer bearerId){
        try{
            List<TaskProgress> taskProgresses = taskProgressService.findByBearerId(bearerId);
            return Msg.success().add("taskProgresses",taskProgresses);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }


}
