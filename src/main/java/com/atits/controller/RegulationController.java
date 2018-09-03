package com.atits.controller;

import com.atits.entity.Files;
import com.atits.entity.Msg;
import com.atits.entity.Regulation;
import com.atits.service.FilesService;
import com.atits.service.RegulationService;
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
@Api(description = "规章制度")
@RequestMapping(value = "regulation")
public class RegulationController {

    @Resource
    private RegulationService regulationService;

    @Resource
    private FilesService filesService;

    @ResponseBody
    @ApiOperation(value = "增加一个规章制度")
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
    public Msg save(Regulation regulation, MultipartFile[] multipartFiles) {
        try {
            String date = GetTimeUtil.getDate();
            String time = GetTimeUtil.getTime();
            if (multipartFiles.length != 0) {//ajax发过来没有文件时可以不用执行
                if (!multipartFiles[0].isEmpty()) {//form发过来没有文件时可以不用执行
                    Set<Files> filesSet = filesService.fileSave(multipartFiles, "规章制度", regulation.getSystem().getId(), regulation.getUser().getId(), date, time);
                    regulation.setFiles(filesSet);
                }
            }
            regulation.setDate(date);
            regulation.setTime(time);
            regulationService.save(regulation);
            return Msg.success();
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id删除一个规章制度")
    @ApiImplicitParam(name = "id", value = "需要删除的规章制度id", paramType = "query")
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public Msg delete(Integer id) {
        try {
            Regulation regulation = regulationService.findById(id);
            Set<Files> filesSet = regulation.getFiles();
            filesService.deleteFiles(filesSet);
            regulationService.deleteById(id);
            return Msg.success();
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id数组批量删除Regulation")
    @RequestMapping(value = "deleteByIds", method = RequestMethod.DELETE)
    public Msg deleteByIds(@ApiParam(name = "idList", value = "需删除规章制度的id数组") @RequestParam List<Integer> idList) {
        try {
            for (Integer id : idList) {
                Regulation regulation = regulationService.findById(id);
                Set<Files> filesSet = regulation.getFiles();
                filesService.deleteFiles(filesSet);
            }
            regulationService.deleteByIds(idList);
            return Msg.success();
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新一个Regulation")
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
    public Msg update(Regulation regulation, MultipartFile[] multipartFiles) {
        try {
            String date = GetTimeUtil.getDate();
            String time = GetTimeUtil.getTime();
            //查出原文件并删除
            Set<Files> oldFilesSet = regulationService.getFiles(regulation.getId());
            filesService.deleteDoubleFiles(oldFilesSet);
            if (multipartFiles.length != 0) {//ajax发过来没有文件时可以不用执行
                if (!multipartFiles[0].isEmpty()) {//form发过来没有文件时可以不用执行
                    Set<Files> newFilesSet = filesService.fileSave(multipartFiles, "规章制度", regulation.getSystem().getId(), regulation.getUser().getId(), date, time);
                    regulation.setFiles(newFilesSet);
                }
            }
            regulation.setDate(date);
            regulation.setTime(time);
            regulationService.update(regulation);
            return Msg.success();
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id查找一个Regulation")
    @ApiImplicitParam(name = "id", value = "要查找的规章制度的id", paramType = "query", dataType = "long")
    @RequestMapping(value = "findById", method = RequestMethod.GET)
    public Msg findById(Integer id) {
        try {
            return Msg.success().add("regulation", regulationService.findById(id));
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ApiOperation(value = "查找所有的规章制度", notes = "查找所有的规章制度")
    @ResponseBody
    public Msg findAll() {
        try {
            List<Regulation> regulations = regulationService.findAll();
            return Msg.success().add("regulations", regulations);
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "分页查找若干个规章制度", notes = "分页查找若干个规章制度")
    @RequestMapping(value = "findPage", method = RequestMethod.GET)
    public Msg findPage(Integer page/*第几页*/) {
        try {
            //声明每页显示的个数
            final int pageSize = 10;
            //获取总个数
            int count = regulationService.getCount();
            //声明页数
            int pageTime;
            //计算页数
            if (count % pageSize == 0) {
                pageTime = count / pageSize;
            } else {
                pageTime = count / pageSize + 1;
            }
            //获取该页所显示的名称，和对应id
            List regulations = regulationService.findPage((page - 1) * pageSize, pageSize);
            //返回该页所需显示的名称和id，及总页数
            return Msg.success().add("regulations", regulations).add("pageTime", pageTime);
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "获取所有id，title，date")
    @RequestMapping(value = "findAll1", method = RequestMethod.GET)
    public Msg findAll1() {
        try {
            List regulations = regulationService.findAll1();
            return Msg.success().add("regulations", regulations);
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }

    }

    @ResponseBody
    @ApiOperation(value = "获取体系办需要的规章制度")
    @RequestMapping(value = "findForTXB", method = RequestMethod.GET)
    public Msg findForTXB() {
        try {
            List<Regulation> txb = regulationService.findAll3();//获取体系办的规章制度
            List<Regulation> others = regulationService.findAll4();//获取除体系办之外的所有规章制度
            return Msg.success().add("txb", txb).add("others", others);
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "获取首席需要的规章制度")
    @RequestMapping(value = "findForSX", method = RequestMethod.GET)
    public Msg findForSX(int systemId) {
        try {
            List<Regulation> txb = regulationService.findAll3();//获取体系办的规章制度
            List<Regulation> others = regulationService.findAll5(systemId);//获取本体的规章制度
            List<Regulation> others1 = regulationService.findAll6(systemId);//获取除本体系、体系办之外的，所有通过的规章制度
            others.addAll(others1);
            return Msg.success().add("txb", txb).add("others", others);
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }


    @ResponseBody
    @ApiOperation(value = "获取体系办、首席之外的人需要的规章制度")
    @RequestMapping(value = "findFor", method = RequestMethod.GET)
    public Msg findFor(int systemId) {
        try {
            List<Regulation> txb = regulationService.findAll3();//获取体系办的规章制度
            List<Regulation> others = regulationService.findAll7(systemId);//获取本体系所有通过的规章制度
            return Msg.success().add("txb", txb).add("others", others);
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新状态")
    @RequestMapping(value = "updateState", method = RequestMethod.PUT)
    public Msg updateState(int id, int state) {
        try {
            regulationService.updateState(id, state);
            return Msg.success();
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }


}








