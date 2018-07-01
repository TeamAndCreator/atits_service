package com.atits.controller;

import com.atits.entity.Msg;
import com.atits.entity.Task;
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

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@Api(description = "体系任务")
@RequestMapping(value = "task")
public class TaskController {

    @Resource
    private TaskService taskService;

    @ResponseBody
    @ApiOperation(value = "查找所有的工作任务")
    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    public Msg findAll(){
        try{
            List<Task> tasks = taskService.findAll();
            return Msg.success().add("tasks",tasks);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据任务Id查找该工作任务")
    @RequestMapping(value = "findById",method = RequestMethod.GET)
    public Msg findById(@RequestParam Integer id){
        try{
            Task task = taskService.findById(id);
            return Msg.success().add("task",task);
        } catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据体系id查找该体系的所有任务")
    @RequestMapping(value = "findBySysId",method = RequestMethod.GET)
    public Msg findBySysId(@RequestParam Integer sysId){
        try{
            List<Task> tasks = taskService.findBySysId(sysId);
            return Msg.success().add("tasks",tasks);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "添加一个工作任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title",value = "任务名称",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "system.id",value = "任务所属体系",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "user.id",value = "所选体系首席（根据所选体系自动匹配为该体系首席）",dataType = "Integer",paramType = "query",required = true)
    })
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Msg save(Task task){
        try{
            String date= GetTimeUtil.getDate();
            String time=GetTimeUtil.getTime();
//            if (!multipartFiles[0].isEmpty()){
//                Set<Files> filesSet=filesService.fileSave(multipartFiles,"工作进展",taskProgress.getBearer().getSystem().getId(),taskProgress.getBearer().getId(),date,time);
//                taskProgress.setFiles(filesSet);
//            }
//            taskProgress.setSubTask(subTaskService.findById(taskProgress.getSubTask().getId()));
            task.setTime(time);
            task.setDate(date);
            taskService.save(task);
            return Msg.success().add("task",task);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新（修改）一个工作任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "任务id（已存在的）",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "title",value = "任务名称",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name = "system.id",value = "任务所属体系",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "user.id",value = "所选体系首席（根据所选体系自动匹配为该体系首席）",dataType = "Integer",paramType = "query",required = true)
    })
    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public Msg update(Task task){
        try{
            String date= GetTimeUtil.getDate();
            String time=GetTimeUtil.getTime();
//            if (!multipartFiles[0].isEmpty()){
//                Set<Files> filesSet=filesService.fileSave(multipartFiles,"工作进展",taskProgress.getBearer().getSystem().getId(),taskProgress.getBearer().getId(),date,time);
//                taskProgress.setFiles(filesSet);
//            }
//            taskProgress.setSubTask(subTaskService.findById(taskProgress.getSubTask().getId()));
            task.setTime(time);
            task.setDate(date);
                taskService.update(task);
                return Msg.success().add("task",task);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据任务Id删除一个工作任务")
    @RequestMapping(value = "deleteById",method = RequestMethod.DELETE)
    public Msg deleteById(@RequestParam Integer id){
        try{
            taskService.deleteById(id);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据idList批量删除工作任务，需依次输入id时以英文逗号隔开")
    @RequestMapping(value = "deleteByIds",method = RequestMethod.DELETE)
    public Msg deleteByIds(@RequestParam List<Integer> idList){
        try{
            taskService.deleteByIds(idList);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }


}
