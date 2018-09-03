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
import io.swagger.models.auth.In;
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
            @ApiImplicitParam(name = "bearer.id", value = "承担者ID", paramType = "query", dataType = "Integer", required = true),
            @ApiImplicitParam(name = "title", value = "工作进展标题", paramType = "query", dataType = "String", required = true),
            @ApiImplicitParam(name = "state", value = "默认状态为0（1-通过 2-不通过 0-审核中）", paramType = "query", dataType = "Integer")
    })
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Msg save(TaskProgress taskProgress, MultipartFile[] multipartFiles) {
        try {
            String date = GetTimeUtil.getDate();
            String time = GetTimeUtil.getTime();
            if (multipartFiles.length != 0) {//ajax发过来没有文件时可以不用执行
                if (!multipartFiles[0].isEmpty()) {//form发过来没有文件时可以不用执行
                    Set<Files> filesSet = filesService.fileSave(multipartFiles, "工作进展", taskProgress.getSubTask().getBearer().getSystem().getId(), taskProgress.getSubTask().getBearer().getId(), date, time);
                    taskProgress.setFiles(filesSet);
                }
            }
            taskProgress.setTime(time);
            taskProgress.setDate(date);
            taskProgressService.save(taskProgress);
            return Msg.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新一项工作进展")
    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public Msg update(TaskProgress taskProgress, MultipartFile[] multipartFiles) {
        try {
            String date = GetTimeUtil.getDate();
            String time = GetTimeUtil.getTime();
            //查出原文件并删除
            Set<Files> oldFilesSet = taskProgressService.getFiles(taskProgress.getId());
            filesService.deleteDoubleFiles(oldFilesSet);
            if (multipartFiles.length != 0) {//ajax发过来没有文件时可以不用执行
                if (!multipartFiles[0].isEmpty()) {//form发过来没有文件时可以不用执行
                    Set<Files> newFilesSet = filesService.fileSave(multipartFiles, "工作进展", taskProgress.getSubTask().getBearer().getSystem().getId(), taskProgress.getSubTask().getBearer().getId(), date, time);
                    taskProgress.setFiles(newFilesSet);
                }
            }
            taskProgress.setDate(date);
            taskProgress.setTime(time);
            taskProgressService.update(taskProgress);
            return Msg.success();
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }


    @ResponseBody
    @ApiOperation(value = "根据多个Id批量删除工作进展，需依次输入id时以英文逗号隔开")
    @RequestMapping(value = "deleteByIds", method = RequestMethod.DELETE)
    public Msg deleteByIds(@RequestParam List<Integer> idList) {
        try {
            for (Integer id : idList) {
                TaskProgress taskProgress = taskProgressService.findById(id);
                Set<Files> filesSet = taskProgress.getFiles();
                filesService.deleteFiles(filesSet);
            }
            taskProgressService.deleteByIds(idList);
            return Msg.success();
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "查询所有的工作进展")
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public Msg findAll() {
        try {
            List<TaskProgress> taskProgresses = taskProgressService.findAll();
            return Msg.success().add("taskProgresses", taskProgresses);
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id查询一项工作进展")
    @RequestMapping(value = "findById", method = RequestMethod.GET)
    public Msg findById(@RequestParam Integer id) {
        try {
            TaskProgress taskProgress = taskProgressService.findById(id);
            return Msg.success().add("taskProgress", taskProgress);
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据承担人bearerId查找其所承担的子任务的所有工作进展")
    @RequestMapping(value = "findByBearerId", method = RequestMethod.GET)
    public Msg findByBearerId(@RequestParam Integer bearerId) {
        try {
            List<TaskProgress> taskProgresses = taskProgressService.findAll1(bearerId);
            return Msg.success().add("taskProgresses", taskProgresses);
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "查询本体系的工作进展（用于首席）")
    @RequestMapping(value = "findBySystemId", method = RequestMethod.GET)
    public Msg findBySystemId(int systemId) {
        try {
            List<TaskProgress> taskProgresses = taskProgressService.findAll2(systemId);
            return Msg.success().add("taskProgresses", taskProgresses);
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "修改状态从0（待审核）到1（通过）或2（未通过）")
    @RequestMapping(value = "updateState", method = RequestMethod.PUT)
    public Msg updateState(int id, int state) {
        try {
            taskProgressService.updateState(id, state);
            return Msg.success();
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }


}
