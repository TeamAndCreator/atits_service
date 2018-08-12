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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@Api(description = "工作任务")
@RequestMapping(value = "task")
public class TaskController {

    @Resource
    private TaskService taskService;
    @Resource
    private SubTaskService subTaskService;
    @Resource
    private FilesService filesService;
    @Resource
    private TaskProgressService taskProgressService;

    @ResponseBody
    @ApiOperation(value = "查找所有的工作任务")
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public Msg findAll() {
        try {
            List<Task> tasks = taskService.findAll();
            return Msg.success().add("tasks", tasks);
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据任务Id查找该工作任务")
    @RequestMapping(value = "findById", method = RequestMethod.GET)
    public Msg findById(@RequestParam Integer id) {
        try {
            Task task = taskService.findById(id);
            return Msg.success().add("task", task);
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据体系id查找该体系的所有任务")
    @RequestMapping(value = "findBySysId", method = RequestMethod.GET)
    public Msg findBySysId(@RequestParam Integer sysId) {
        try {
            List<Task> tasks = taskService.findBySysId(sysId);
            return Msg.success().add("tasks", tasks);
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "添加一个工作任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "任务名称", paramType = "query", dataType = "String", required = true),
            @ApiImplicitParam(name = "system.id", value = "任务所属体系", paramType = "query", dataType = "Integer", required = true),
            @ApiImplicitParam(name = "user.id", value = "所选体系首席（根据所选体系自动匹配为该体系首席）", dataType = "Integer", paramType = "query", required = true)
    })
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Msg save(Task task, MultipartFile[] multipartFiles) {
        try {
            String date = GetTimeUtil.getDate();
            String time = GetTimeUtil.getTime();
            if (multipartFiles.length != 0) {//ajax发过来没有文件时可以不用执行
                if (!multipartFiles[0].isEmpty()) {//form发过来没有文件时可以不用执行
                    Set<Files> filesSet = filesService.fileSave(multipartFiles, "工作任务", task.getSystem().getId(), task.getUser().getId(), date, time);
                    task.setFiles(filesSet);
                }
            }
            task.setTime(time);
            task.setDate(date);
            taskService.save(task);
            return Msg.success().add("task", task);
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据任务Id删除一个工作任务")
    @RequestMapping(value = "deleteById", method = RequestMethod.DELETE)
    public Msg deleteById(@RequestParam Integer id) {
        try {
            taskService.deleteById(id);
            return Msg.success();
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据idList批量删除工作任务，需依次输入id时以英文逗号隔开")
    @RequestMapping(value = "deleteByIds", method = RequestMethod.DELETE)
    public Msg deleteByIds(@RequestParam List<Integer> idList) {
        try {
            for (Integer id1:idList){
                List<Integer> subTaskids=subTaskService.findIdList(id1);
                for (Integer id : subTaskids){
                    List<TaskProgress> taskProgresses=taskProgressService.findBySubTaskId(id);
                    for (TaskProgress taskProgress:taskProgresses){
                        Set<Files> filesSet = taskProgress.getFiles();
                        filesService.deleteFiles(filesSet);
                        taskProgressService.delete(taskProgress);
                    }
                }
                for (Integer id : subTaskids) {//删除subtask
                    SubTask subTask = subTaskService.findById(id);
                    Set<Files> filesSet = subTask.getFiles();
                    filesService.deleteFiles(filesSet);
                }
                subTaskService.deleteByIds(subTaskids);
            }
            for (Integer id : idList) {
                Task task = taskService.findById(id);
                Set<Files> filesSet = task.getFiles();
                filesService.deleteFiles(filesSet);
            }
            taskService.deleteByIds(idList);
            return Msg.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail(e.getMessage());
        }
    }


}
