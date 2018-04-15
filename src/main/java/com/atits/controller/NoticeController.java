package com.atits.controller;

import com.atits.entity.Msg;
import com.atits.entity.Notice;
import com.atits.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Api(description = "通知公告")
@RequestMapping(value = "notice")
public class NoticeController {

    @Resource
    private NoticeService noticeService;

    @RequestMapping(value = "noticeSave",method = RequestMethod.POST)
    @ApiOperation(value = "增加一个通知公告",notes = "增加一个通知公告")
    @ApiImplicitParam(name = "notice",value = "表单输入的一个通知公告",dataType = "Notice")
    public Msg save(Notice notice){
        try {
            noticeService.save(notice);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e);
        }
    }

    @RequestMapping(value = "noticeDelet",method = RequestMethod.DELETE)
    @ApiOperation(value = "删除一个通知公告",notes = "删除一个通知公告")
    @ApiImplicitParam(name = "notice",value = "表单输入的通知公告",dataType = "notice")
    @ResponseBody
    public Msg delet(Notice notice){
        try {
            noticeService.delet(notice);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e);
        }
    }

    @RequestMapping(value = "noticeFindAll",method = RequestMethod.GET)
    @ApiOperation(value = "查找所有的通知公告",notes = "查找所有的通知公告")
    @ResponseBody
    public Msg findAll(){
        try {
            List<Notice> notices=noticeService.findAll();
            return Msg.success().add("notices",notices);
        }catch (Exception e){
            return Msg.fail(e);
        }
    }


}
