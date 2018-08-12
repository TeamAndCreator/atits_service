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
@Api(description = "体系任务")
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
            @ApiImplicitParam(name = "title", value = "子任务标题", paramType = "query", dataType = "Integer", required = true),
            @ApiImplicitParam(name = "bearer.id", value = "承担者ID", paramType = "query", dataType = "Integer", required = true),
            @ApiImplicitParam(name = "startTime", value = "子任务开始时间（格式：yyyy-mm-dd）", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "子任务结束时间（格式：yyyy-mm-dd）", paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Msg save(SubTask subTask, MultipartFile[] multipartFiles) {
        try {
            String date = GetTimeUtil.getDate();
            String time = GetTimeUtil.getTime();
            if (multipartFiles.length != 0) {//ajax发过来没有文件时可以不用执行
                if (!multipartFiles[0].isEmpty()) {//form发过来没有文件时可以不用执行
                    Set<Files> filesSet = filesService.fileSave(multipartFiles, "体系任务", subTask.getBearer().getSystem().getId(), subTask.getBearer().getId(), date, time);
                    subTask.setFiles(filesSet);
                }
            }
            subTask.setTime(time);
            subTask.setDate(date);
            subTaskService.save(subTask);
            return Msg.success().add("subTask", subTask);
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "查询所有的子任务")
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public Msg findAll() {
        try {
            List<SubTask> subTasks = subTaskService.findAll();
            return Msg.success().add("subTasks", subTasks);
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id查找一个子任务")
    @RequestMapping(value = "findById", method = RequestMethod.GET)
    public Msg findById(@RequestParam Integer id) {
        try {
            SubTask subTask = subTaskService.findById(id);
            return Msg.success().add("subTask", subTask);
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "获取所有本体系(用过父任务的systemId筛选)的子任务(用于首席)")
    @RequestMapping(value = "findBySystemId",method = RequestMethod.GET)
    public Msg findBySystemId(int systemId){
        try {
            List<SubTask> subTasks=subTaskService.findBySystemId(systemId);
            return Msg.success().add("subTasks",subTasks);
        }catch (Exception e){
            e.printStackTrace();
            return Msg.fail(e.getMessage());
        }
    }


    @ResponseBody
    @ApiOperation(value = "根据承担人bearerId查找其所承担的子任务")
    @RequestMapping(value = "findByBearerId", method = RequestMethod.GET)
    public Msg findByBearerId(@RequestParam Integer bearerId) {
        try {
            List<SubTask> subTasks = subTaskService.findByBearerId(bearerId);
            return Msg.success().add("subTasks", subTasks);
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "批量删除")
    @RequestMapping(value = "deleteByIds",method = RequestMethod.DELETE)
    public Msg deleteByIds(@RequestParam List<Integer> idList){
        try {
            for (Integer id : idList){
                List<TaskProgress> taskProgresses=taskProgressService.findBySubTaskId(id);
                for (TaskProgress taskProgress:taskProgresses){
                    Set<Files> filesSet = taskProgress.getFiles();
                    filesService.deleteFiles(filesSet);
                    taskProgressService.delete(taskProgress);
                }
            }
            for (Integer id : idList) {//删除subtask
                SubTask subTask = subTaskService.findById(id);
                Set<Files> filesSet = subTask.getFiles();
                filesService.deleteFiles(filesSet);
            }
            subTaskService.deleteByIds(idList);
            return Msg.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail(e.getMessage());
        }
    }


}
