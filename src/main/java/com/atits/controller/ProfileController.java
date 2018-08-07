package com.atits.controller;

import com.atits.entity.Msg;
import com.atits.entity.Profile;
import com.atits.service.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@Api(description = "个人简历")
@RequestMapping(value = "profile")
public class ProfileController {

    @Resource
    private ProfileService profileService;


    @ResponseBody
    @ApiOperation(value = "根据userid获取profile")
    @RequestMapping(value = "findByUserId",method = RequestMethod.GET)
    public Msg findByUserId(int userId){
        try {
            Profile profile=profileService.findById(userId);
            return Msg.success().add("profile",profile);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新profile")
    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public Msg update(Profile profile){
        try {
            profileService.update(profile);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

}
