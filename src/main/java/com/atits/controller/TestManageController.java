package com.atits.controller;

import com.atits.entity.Msg;
import com.atits.entity.TestManage;
import com.atits.entity.TestStart;
import com.atits.entity.User;
import com.atits.service.TestManageService;
import com.atits.service.TestStartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.objenesis.instantiator.sun.MagicInstantiator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
@Api(description = "考评管理")
@RequestMapping(value = "evaluate")
public class TestManageController {
    @Resource
    private TestManageService testManageService;
    @Resource
    private TestStartService testStartService;

    /*
    1.考评状态：启动后，考评中时、记录导入（增:根据状态添加进来）
    2.考评状态：考评结束状态时、记录自动消失（删）
    3.改：不可修改---仅能进行考评，也就是打分
    4.查：考评中
     */

    /*
    启动表中，state = 2 的记录——导入管理表
     */

    @ApiOperation(value = "获取需要考评的所有记录")
    @RequestMapping(value = "manage_list",method = RequestMethod.GET)
    @ResponseBody
    public Msg findAll(){
        try{
            List<TestManage> testManages = testManageService.findAll();
            return Msg.success().add("testManages",testManages);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }



    @ApiOperation(value = "启动后，自动导入考评记录")
    @RequestMapping(value = "manage_save",method = RequestMethod.POST)
    @ResponseBody
    public Msg save(){
        try{
                /*
                导入启动表中的考评人员，并且自动分成考评人与被考评人
                 */
                List<TestStart> testStarts = testStartService.findByState(2);//考评状态 ：1、启动考评，2、考评开始，3、考评结束
                for (TestStart testStart:testStarts){

                    Set<User> users = testStart.getUsers();//获取到启动表中的考评人员

                    //①农委或外聘专家对体系人员考评
                    for (User user1 : users) {
                        for (User user2 : users) {
                            if (user1.getSystem() == null & user2.getSystem() != null) {
                                    List<TestManage> testManages = testManageService.findAll();
                                    int i;
                                    for (i=0;i<testManages.size();i++){
                                        if (testManages.get(i).getExaminer() == user1 & testManages.get(i).getExamedner()==user2){
                                            break;
                                        }
                                    }
                                    if (i>=testManages.size()){//说明没有相同记录，则进行保存
                                        TestManage testManage = new TestManage();
                                        testManage.setDate(testStart.getDate());
                                        testManage.setYear(testStart.getYear());
                                        testManage.setState(0);//初始化默认为考评中
                                        testManage.setExaminer(user1);
                                        testManage.setExamedner(user2);
                                        testManageService.save(testManage);
                                    }
//                                    else {
//                                        continue;
//                                    }
                            }
                        }
                    }
                    //②体系内互评
                    for (User user11 : users){
                        for (User user22 :users){
                            if(user11.getSystem()!= null & user22.getSystem() != null){
                                if (user11.equals(user22))
                                    continue;
                                List<TestManage> testManages = testManageService.findAll();
                                int j;
                                for (j=0;j<testManages.size();j++){
                                    if (testManages.get(j).getExaminer() == user11 & testManages.get(j).getExamedner()==user22){
                                        break;
                                    }
                                }
                                if (j>=testManages.size()){
                                    TestManage testManage = new TestManage();
                                    testManage.setDate(testStart.getDate());
                                    testManage.setYear(testStart.getYear());
                                    testManage.setState(0);
                                    testManage.setExaminer(user11);
                                    testManage.setExamedner(user22);
                                    testManageService.save(testManage);
                                }
                            }
                        }
                    }

                }
            return Msg.success().add("testManage",testManageService.findAll());
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }


}
