package com.atits.controller;

import com.atits.entity.Msg;
import com.atits.entity.Station;
import com.atits.service.StationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Api(description = "实验站")
@RequestMapping(value = "station")
public class StationController {
    @Resource
    private StationService stationService;

    @ApiOperation(value="获取所有试验站详细信息")
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseBody
    public Msg findAll() {
        try {
            List<Station> stations = stationService.findAll();
            return Msg.success().add("Station",stations);
        }catch (Exception e){
            return Msg.fail(e);
        }
    }
}
