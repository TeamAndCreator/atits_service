package com.atits.controller;

import com.atits.entity.Activity;
import com.atits.entity.Files;
import com.atits.entity.Msg;
import com.atits.service.ActivityService;
import com.atits.service.FilesService;
import com.atits.utils.GetTimeUtil;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Controller
@Api(description = "重大活动")
@RequestMapping(value = "activity")
public class ActivityController {
    @Resource
    private ActivityService activityService;

    @Resource
    private FilesService filesService;

    @ResponseBody
    @ApiOperation(value = "增加一个重大活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", paramType = "query"),
            @ApiImplicitParam(name = "title", value = "标题", paramType = "query"),
            @ApiImplicitParam(name = "content", value = "描述", paramType = "query"),
            @ApiImplicitParam(name = "time", value = "时间", paramType = "query"),
            @ApiImplicitParam(name = "system.id", value = "所属体系id", paramType = "query"),
            @ApiImplicitParam(name = "user.id", value = "编辑人id", paramType = "query"),
            @ApiImplicitParam(name = "state", value = "状态", paramType = "query")
    })
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Msg save(Activity activity, MultipartFile[] multipartFiles) {
        try {
            String date = GetTimeUtil.getDate();
            String time = GetTimeUtil.getTime();
            if (!multipartFiles[0].isEmpty()) {
                Set<Files> filesSet = filesService.fileSave(multipartFiles, "重大活动", activity.getSystem().getId(), activity.getUser().getId(), date, time);
                activity.setFiles(filesSet);
            }
            activity.setDate(date);
            activity.setTime(time);
            activityService.save(activity);
            return Msg.success();
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id删除一个重大活动")
    @ApiImplicitParam(name = "id", value = "需要删除的重大活动id", paramType = "query")
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public Msg delete(Integer id) {
        try {
            Activity activity = activityService.findById(id);
            Set<Files> filesSet = activity.getFiles();
            filesService.deleteFiles(filesSet);
            activityService.deleteById(id);
            return Msg.success();
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id数组批量删除Activity")
    @RequestMapping(value = "deleteByIds", method = RequestMethod.DELETE)
    public Msg deleteByIds(@ApiParam(name = "idList", value = "需删除重大活动的id数组") @RequestParam List<Integer> idList) {
        try {
            for (Integer id : idList) {
                Activity activity = activityService.findById(id);
                Set<Files> filesSet = activity.getFiles();
                filesService.deleteFiles(filesSet);
            }
            activityService.deleteByIds(idList);
            return Msg.success();
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新一个Activity")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", paramType = "query"),
            @ApiImplicitParam(name = "title", value = "标题", paramType = "query"),
            @ApiImplicitParam(name = "content", value = "描述", paramType = "query"),
            @ApiImplicitParam(name = "time", value = "时间", paramType = "query"),
            @ApiImplicitParam(name = "system.id", value = "所属体系id", paramType = "query"),
            @ApiImplicitParam(name = "user.id", value = "编辑人id", paramType = "query"),
            @ApiImplicitParam(name = "state", value = "状态", paramType = "query")
    })
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Msg update(Activity activity, MultipartFile[] multipartFiles) {
        try {
            String date = GetTimeUtil.getDate();
            String time = GetTimeUtil.getTime();
            //查出原文件并删除
            Set<Files> oldFilesSet = activityService.getFiles(activity.getId());
            filesService.deleteDoubleFiles(oldFilesSet);
            if (!multipartFiles[0].isEmpty()) {
                Set<Files> newFilesSet = filesService.fileSave(multipartFiles, "重大活动", activity.getSystem().getId(), activity.getUser().getId(), date, time);
                activity.setFiles(newFilesSet);
            }
            activity.setDate(date);
            activity.setTime(time);
            activityService.update(activity);
            return Msg.success();
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id查找一个Activity")
    @ApiImplicitParam(name = "id", value = "要查找的重大活动的id", paramType = "query", dataType = "long")
    @RequestMapping(value = "findById", method = RequestMethod.GET)
    public Msg findById(Integer id) {
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser.hasRole("chief")){

            try {
                return Msg.success().add("activity", activityService.findById(id));
            } catch (Exception e) {
                return Msg.fail(e.getMessage());
            }
        }
        return Msg.fail("没有权限");
    }

    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ApiOperation(value = "查找所有的重大活动", notes = "查找所有的重大活动")
    @ResponseBody
    public Msg findAll() {
        try {
            List<Activity> Activitys = activityService.findAll();
            return Msg.success().add("Activitys", Activitys);
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "分页查找若干个重大活动", notes = "分页查找若干个重大活动")
    @RequestMapping(value = "findPage", method = RequestMethod.GET)
    public Msg findPage(Integer page/*第几页*/) {
        try {
            //声明每页显示的个数
            final int pageSize = 10;
            //获取总个数
            int count = activityService.getCount();
            //声明页数
            int pageTime;
            //计算页数
            if (count % pageSize == 0) {
                pageTime = count / pageSize;
            } else {
                pageTime = count / pageSize + 1;
            }
            //获取该页所显示的名称，和对应id
            List activitys = activityService.findPage((page - 1) * pageSize, pageSize);
            //返回该页所需显示的名称和id，及总页数
            return Msg.success().add("activitys", activitys).add("pageTime", pageTime);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }

    }

    @ResponseBody
    @ApiOperation(value = "获取所有id，title，date")
    @RequestMapping(value = "findAll1",method = RequestMethod.GET)
    public Msg findAll1(){
        try {
            List activitys=activityService.findAll1();
            return Msg.success().add("activitys",activitys);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }

    }

    @ResponseBody
    @ApiOperation(value = "获取所有的id，标题，发布者，发布时间，状态")
    @RequestMapping(value = "findAll2",method = RequestMethod.GET)
    @RequiresRoles({"nongWei"})
    public Msg findAll2(){
        try {
            List activitys=activityService.findAll2();
            return Msg.success().add("activitys",activitys);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }


}
