package com.atits.service;

import com.atits.dao.TestManageDao;
import com.atits.dao.TestScoreDao;
import com.atits.dao.TestWeightDao;
import com.atits.dao.UserDao;
import com.atits.entity.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.awt.*;
import java.lang.System;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class TestManageService {

    @Resource
    private TestManageDao testManageDao;

    @Resource
    private UserDao userDao;

    @Resource
    private TestScoreDao testScoreDao;

    @Resource
    private TestWeightDao testWeightDao;

    public void addManage(TestStart testStart){
        TestWeight testWeight=testWeightDao.findByTestStartId(testStart.getId());
        double a=testWeight.getA()/100;
        double b=testWeight.getB()/100;
        double c=testWeight.getC()/100;
        double d=testWeight.getD()/100;
        double e=testWeight.getE()/100;
        double f=testWeight.getF()/100;
        double g=testWeight.getG()/100;
        double h=testWeight.getH()/100;
        List<User> externals = userDao.findExternal();//所有外聘人员
        Set<User> users = testStart.getUsers();//此次考评的所有人员
        List<User> externalInUsers = new ArrayList<>();//在此次考评中的外聘人员
        for (User external : externals) {
            for (User user : users) {
                if (user.getId() == external.getId()) {
                    externalInUsers.add(user);
                }
            }
        }
        users.removeAll(externalInUsers);//此次考评的所有人员减去外聘人员
        for (User user:users){
            List<TestScore> testScores=testScoreDao.findByEvaluationed(user.getId(),testStart.getId());
            double finish_sum=0;
            int sum_a=0;
            int pv_a=0;
            int sum_b=0;
            int pv_b=0;
            int sum_c=0;
            int pv_c=0;
            int sum_d=0;
            int pv_d=0;
            int sum_e=0;
            int pv_e=0;
            int sum_f=0;
            int pv_f=0;
            int sum_g=0;
            int pv_g=0;
            int sum_h=0;
            int pv_h=0;
            for (TestScore testScore:testScores){
                if (testScore.getTime()==null)
                    continue;
                if (testScore.getTestType().equals("a")){
                    sum_a+=testScore.getSum();
                    pv_a+=1;
                }
                else if (testScore.getTestType().equals("b")){
                    sum_b+=testScore.getSum();
                    pv_b+=1;
                }
                else if (testScore.getTestType().equals("c")){
                    sum_c+=testScore.getSum();
                    pv_c+=1;
                }
                else if (testScore.getTestType().equals("d")){
                    sum_d+=testScore.getSum();
                    pv_d+=1;
                }
                else if (testScore.getTestType().equals("e")){
                    sum_e+=testScore.getSum();
                    pv_e+=1;
                }
                else if (testScore.getTestType().equals("f")){
                    sum_f+=testScore.getSum();
                    pv_f+=1;
                }
                else if (testScore.getTestType().equals("g")){
                    sum_g+=testScore.getSum();
                    pv_g+=1;
                }
                else if (testScore.getTestType().equals("h")){
                    sum_h+=testScore.getSum();
                    pv_h+=1;
                }
            }
            if (pv_a!=0){
                finish_sum+=sum_a*a/pv_a;
            }
            if (pv_b!=0){
                finish_sum+=sum_b*b/pv_b;
            }
            if (pv_c!=0){
                finish_sum+=sum_c*c/pv_c;
            }
            if (pv_d!=0){
                finish_sum+=sum_d*d/pv_d;
            }
            if (pv_e!=0){
                finish_sum+=sum_e*e/pv_e;
            }
            if (pv_f!=0){
                finish_sum+=sum_f*f/pv_f;
            }
            if (pv_g!=0){
                finish_sum+=sum_g*g/pv_g;
            }
            if (pv_h!=0){
                finish_sum+=sum_h*h/pv_h;
            }
            testManageDao.save(new TestManage(testStart,user,finish_sum));
        }
    }

    /**
     * 删除一个testStart中的所有testManage
     * @param
     */
    public void deleteByTestStart(int testStartId){
        testManageDao.deleteByTestStart(testStartId);
    }

    /**
     * 查找一个用户的所有评分
     * @param userId
     * @return
     */
    public List findOwn(int userId){
        return testManageDao.findOwn(userId);
    }

    /**
     * 查找一个体系所有的评分
     * @param systemId
     * @return
     */
    public List findSystemTestManage(int systemId){
        return testManageDao.findSystemTestManage(systemId);
    }
}
