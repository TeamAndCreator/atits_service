package com.atits.controller;

import com.atits.entity.Msg;
import com.atits.entity.TestWeight;
import com.atits.service.TestWeightService;
import io.swagger.annotations.*;
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
@Api(description = "考评权重设置")
@RequestMapping(value = "testweight")
public class TestWeightController {
    @Resource
    private TestWeightService testWeightService;


    @ApiOperation(value = "添加权重设置记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "权重记录id（自增长模式）",paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "year",value = "考评年份",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "nongWei",value = "农委打分权重",paramType = "query",dataType = "double"),
            @ApiImplicitParam(name = "expert",value = "外聘专家打分权重",paramType = "query",dataType = "double"),
            @ApiImplicitParam(name = "subChief_sta_lib",value = "副首席-岗位专家-综合试验站站长打分权重",paramType = "query",dataType = "double"),
            @ApiImplicitParam(name = "sta_lib",value = "岗位专家-综合试验站站长打分权重",paramType = "query",dataType = "double"),
            @ApiImplicitParam(name = "chief",value = "首席打分权重",paramType = "query",dataType = "double"),
            @ApiImplicitParam(name = "nongwei_expert",value = "农委-外聘专家打分权重",paramType = "query",dataType = "double"),
            @ApiImplicitParam(name = "chief_subChief",value = "首席-副首席打分权重",paramType = "query",dataType = "double")
    })
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Msg save(TestWeight testWeight){
        try{
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            testWeight.setTime(simpleDateFormat.format(date));
            testWeightService.save(testWeight);
            return Msg.success().add("testWeight",testWeight);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "根据id更新权重设置记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "权重记录id（自增长模式）",paramType = "query",dataType = "int",required = true),
            @ApiImplicitParam(name = "year",value = "考评年份",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "nongWei",value = "农委打分权重",paramType = "query",dataType = "double"),
            @ApiImplicitParam(name = "expert",value = "外聘专家打分权重",paramType = "query",dataType = "double"),
            @ApiImplicitParam(name = "subChief_sta_lib",value = "副首席-岗位专家-综合试验站站长打分权重",paramType = "query",dataType = "double"),
            @ApiImplicitParam(name = "sta_lib",value = "岗位专家-综合试验站站长打分权重",paramType = "query",dataType = "double"),
            @ApiImplicitParam(name = "chief",value = "首席打分权重",paramType = "query",dataType = "double"),
            @ApiImplicitParam(name = "nongwei_expert",value = "农委-外聘专家打分权重",paramType = "query",dataType = "double"),
            @ApiImplicitParam(name = "chief_subChief",value = "首席-副首席打分权重",paramType = "query",dataType = "double")
    })
    @RequestMapping(value = "update",method = RequestMethod.PUT)
    @ResponseBody
    public Msg update(TestWeight testWeight){
        try{
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            testWeight.setTime(simpleDateFormat.format(date));
            testWeightService.update(testWeight);
            return Msg.success().add("testWeight",testWeight);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }


    @ApiOperation(value = "根据id删除权重记录")
    @RequestMapping(value = "deleteById",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteById(@RequestParam Integer id){
        try{
            testWeightService.delete(id);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "根据idList批量删除权重记录")
    @RequestMapping(value = "deleteByIds",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteByIds(
            @ApiParam(name = "idList",value = "需删除权重记录的id数组,依次以英文逗号间隔输入")@RequestParam List<Integer> idList
    ){
        try {
            testWeightService.deletes(idList);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "获取权重表的所有记录")
    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public Msg findAll(){
        try{
            List<TestWeight> testWeights = testWeightService.findAll();
            return Msg.success().add("testWeights",testWeights);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "根据id查询一条权重记录")
    @RequestMapping(value = "findById",method = RequestMethod.GET)
    @ResponseBody
    public Msg findById(Integer id){
        try{
            TestWeight testWeight = testWeightService.findById(id);
            return Msg.success().add("testWeight",testWeight);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

}
