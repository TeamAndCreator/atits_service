package com.atits.controller;

import com.atits.entity.Msg;
import com.atits.entity.System;
import com.atits.entity.User;
import com.atits.service.SystemService;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Api(description = "体系")
@RequestMapping(value = "system")
public class SystemController {

    @Resource
    private SystemService systemService;

    @ResponseBody
    @ApiOperation(value = "添加一个体系", notes = "添加一个体系")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "systemName", value = "体系名称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "content", value = "体系描述", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "overView", value = "overView", dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Msg save(System system) {
        try {
            systemService.save(system);
            return Msg.success();
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }

    }

    @ResponseBody
    @ApiOperation(value = "获取所有体系的id、名称、首席 、副首席")
    @RequestMapping(value = "findAll2", method = RequestMethod.GET)
    public Msg findAll2() {
        try {
            List<System> systems = systemService.findAll1();
            List<Map> list=new ArrayList<Map>();
            for (System system : systems) {
                Map map = new HashMap();
                List<User> sx = systemService.findUserInRole(system.getId(), 3);
                List<User> fsx = systemService.findUserInRole(system.getId(), 4);
                map.put("systemId",system.getId());
                map.put("systemName",system.getSystemName());
                map.put("sx",sx);
                map.put("fsx",fsx);
                list.add(map);
            }
            return Msg.success().add("list",list);
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }


    @ResponseBody
    @ApiOperation(value = "通过id删除一个体系对象", notes = "通过id删除一个体系对象")
    @ApiImplicitParam(name = "id", value = "体系id", paramType = "query", dataType = "long")
    @RequestMapping(value = "deleteById", method = RequestMethod.DELETE)
    public Msg deleteById(Integer id) {
        try {
            systemService.deleteById(id);
            return Msg.success();
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "根据id数组批量删除system", notes = "根据id数组批量删除system")
    @RequestMapping(value = "deleteByIds", method = RequestMethod.DELETE)
    public Msg deleteByIds(@ApiParam(name = "idList", value = "id数组") @RequestParam List<Integer> idList) {
        try {
            systemService.deleteByIds(idList);
            return Msg.success();
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "体系id", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "systemName", value = "体系名称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "content", value = "体系描述", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "overView", value = "overView", dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Msg update(System system) {
        try {
            systemService.update(system);
            return Msg.success();
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }


    @ResponseBody
    @ApiOperation(value = "查找所有的体系", notes = "查找所有的体系")
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public Msg findAll() {
        try {
            return Msg.success().add("systems", systemService.findAll());
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }


    @ResponseBody
    @ApiOperation(value = "通过id获取一个体系对象", notes = "通过id获取一个体系对象")
    @ApiImplicitParam(name = "id", value = "体系id", paramType = "query", dataType = "long")
    @RequestMapping(value = "findById", method = RequestMethod.GET)
    public Msg findById(Integer id) {
        try {
            System system = systemService.findById(id);
            return Msg.success().add("system", system);
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "分页查找若干个体系", notes = "分页查找若干个体系")
    @RequestMapping(value = "findPage", method = RequestMethod.GET)
    public Msg findPage(Integer page/*第几页*/) {
        try {
            //声明每页显示的个数
            final int pageSize = 10;
            //获取总个数
            int count = systemService.getCount();
            //声明页数
            int pageTime;
            //计算页数
            if (count % pageSize == 0) {
                pageTime = count / pageSize;
            } else {
                pageTime = count / pageSize + 1;
            }
            //获取该页所显示的名称，和对应id
            List systems = systemService.findPage((page - 1) * pageSize, pageSize);
            //返回该页所需显示的名称和id，及总页数
            return Msg.success().add("systems", systems).add("pageTime", pageTime);
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "获取所有体系名称及id")
    @RequestMapping(value = "findAll1", method = RequestMethod.GET)
    public Msg findAll1() {
        try {
            List systems = systemService.findAll1();
            systems.remove(0);
            return Msg.success().add("systems", systems);
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "获取某个体系里的首席，副首席，岗位专家，综合实验站站长")
    @RequestMapping(value = "findUsers", method = RequestMethod.GET)
    public Msg findUsers(Integer systemId) {
        try {
            List chief = systemService.findUserInRole(systemId, 3);
            List sub_chief = systemService.findUserInRole(systemId, 4);
            List research_director=systemService.findUserInRole(systemId,5);
            List job_expert = systemService.findUserInRole(systemId, 6);
            List station_master = systemService.findUserInRole(systemId, 7);
            return Msg.success().add("chief", chief).add("sub_chief", sub_chief).add("research_director",research_director).add("job_expert", job_expert).add("station_master", station_master);
        } catch (Exception e) {
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "修改content")
    @RequestMapping(value = "content_change",method = RequestMethod.PUT)
    public Msg content_change(int systemId,String content){
        try {
            systemService.content_change(systemId,content);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "修改overView")
    @RequestMapping(value = "overView_change",method = RequestMethod.PUT)
    public Msg overView_change(int systemId,String overView){
        try {
            systemService.overView_change(systemId,overView);
            return Msg.success();
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }

}
