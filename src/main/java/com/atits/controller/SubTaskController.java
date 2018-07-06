package com.atits.controller;

import com.atits.entity.*;
import com.atits.service.FilesService;
import com.atits.service.SubTaskService;
import com.atits.service.TaskProgressService;
import com.atits.service.TaskService;
import com.atits.utils.GetTimeUtil;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.persistence.ManyToOne;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
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
    private TaskProgressService taskProgressService;
    @Resource
    private FilesService filesService;

    @ResponseBody
    @ApiOperation(value = "添加一个子任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title",value = "子任务标题",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "bearer.id",value = "承担者ID",paramType = "query",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "startTime",value = "子任务开始时间（格式：yyyy-mm-dd）",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "endTime",value = "子任务结束时间（格式：yyyy-mm-dd）",paramType = "query",dataType = "String")
    })
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Msg save(SubTask subTask,@RequestParam Integer fatherTaskId, MultipartFile[] multipartFiles){
        try{
            String date= GetTimeUtil.getDate();
            String time=GetTimeUtil.getTime();
            if (!multipartFiles[0].isEmpty()){
                Set<Files> filesSet=filesService.fileSave(multipartFiles,"体系任务",subTask.getBearer().getSystem().getId(),subTask.getBearer().getId(),date,time);
                subTask.setFiles(filesSet);
            }
            List<Task> tasks = taskService.findAll();
            int i = 0 ;
            for (Task task:tasks){
                if (task.getId() == fatherTaskId){
                    Set<SubTask> subTasks = new HashSet<>();
                    if (task.getSubTasks() != null){//已有至少一个子任务
                        subTasks.addAll(task.getSubTasks());
                    }
                    subTasks.add(subTask);
                    task.setSubTasks(subTasks);
                    i++;
                }
            }
            if (i == 0 ){
                return Msg.fail("没有该工作任务！无法添加其子任务，请重新输入正确的fatherTaskId");
            }
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
            @ApiImplicitParam(name = "startTime",value = "子任务开始时间（格式：yyyy-mm-dd）",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "endTime",value = "子任务结束时间（格式：yyyy-mm-dd）",paramType = "query",dataType = "String")
    })
    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public Msg update(SubTask subTask, @ApiParam(value ="该子任务下的所有工作进展ID(输入中如果存在不是其工作进展，则直接忽略该工作进展id，其他正常添加)",required = true)@RequestParam List<Integer> taskproIds, MultipartFile[] multipartFiles){
        try{
            String date= GetTimeUtil.getDate();
            String time=GetTimeUtil.getTime();
            if (!multipartFiles[0].isEmpty()){
                Set<Files> filesSet=filesService.fileSave(multipartFiles,"体系任务",subTask.getBearer().getSystem().getId(),subTask.getBearer().getId(),date,time);
                subTask.setFiles(filesSet);
            }
            for (Integer taskproId:taskproIds){
                TaskProgress taskProgress = taskProgressService.findById(taskproId);
                Set<TaskProgress> taskProgresses = new HashSet<>();
                if (subTask.getTaskProgresses() != null)
                    taskProgresses.addAll(subTask.getTaskProgresses());
                taskProgresses.add(taskProgress);
                subTask.setTaskProgresses(taskProgresses);
            }
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
            SubTask subTask = subTaskService.findById(id);
            List<Task> tasks = taskService.findAll();
            for (Task task:tasks){
                if (task.getSubTasks().contains(subTask)){
                    task.getSubTasks().remove(subTask);
                    break;
                }
            }
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
            List<Task> tasks = taskService.findAll();
            for (Integer id : idList){
                for (Task task:tasks){
                    SubTask subTask = subTaskService.findById(id);
                    if (task.getSubTasks().contains(subTask)){
                        task.getSubTasks().remove(subTask);
                        break;
                    }
                }
            }
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
