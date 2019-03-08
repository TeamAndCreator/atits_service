package com.atits.service;

import com.atits.dao.SystemDao;
import com.atits.dao.TestScoreDao;
import com.atits.dao.UserDao;
import com.atits.entity.Role;
import com.atits.entity.System;
import com.atits.entity.TestScore;
import com.atits.entity.TestStart;
import com.atits.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class TestScoreService {
    @Resource
    private TestScoreDao testScoreDao;

    @Resource
    private UserDao userDao;

    @Resource
    private SystemDao systemDao;

    public void addTestScore1(TestStart testStart) {
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
        for (User user : users) {//循环每个被考评人员
            int systemId = user.getSystem().getId();
            boolean sx = false;//true为首席，false为副首席
            for (Role role : user.getRoles()) {//判断用户为首席还是副首席
                if (role.getId() == 3) {
                    sx = true;
                    break;
                }
                if (role.getId() == 4) {
                    sx = false;
                    break;
                }
            }
            if (sx == true) {//给首席打分
                List<User> users0 = systemDao.findUserInRole2(1, 1);//体系办
                List<User> users1 = systemDao.findUserInRole2(systemId, 4);//副首席
                List<User> users2 = systemDao.findUserInRole2(systemId, 6);//岗位专家
                List<User> users3 = systemDao.findUserInRole2(systemId, 7);//实验站站长
                List<Integer> ids = new ArrayList<>();//存放打分人的id，为了解决多角色重复打分
                for (User user1 : users0) {//体系办to首席
                    if (!ids.contains(user1.getId())) {
                        ids.add(user1.getId());
                        testScoreDao.addScore(new TestScore(testStart, user, 1,user1, "a"));
                    }
                }
                for (User user1 : externalInUsers) {//外聘专家to首席
                    if (!ids.contains(user1.getId())) {
                        ids.add(user1.getId());
                        testScoreDao.addScore(new TestScore(testStart, user,1, user1, "c"));
                    }
                }
                for (User user1 : users1) {//副首席to首席
                    if (!ids.contains(user1.getId())) {
                        ids.add(user1.getId());
                        testScoreDao.addScore(new TestScore(testStart, user,1, user1, "b"));
                    }
                }
                for (User user1 : users2) {//岗位专家to首席
                    if (user1.getId() != user.getId()) {//去除自己打分
                        if (!ids.contains(user1.getId())) {
                            ids.add(user1.getId());
                            testScoreDao.addScore(new TestScore(testStart, user,1, user1, "b"));
                        }
                    }
                }
                for (User user1 : users3) {//实验站站长to首席
                    if (user1.getId() != user.getId()) {
                        if (!ids.contains(user1.getId())) {
                            ids.add(user1.getId());
                            testScoreDao.addScore(new TestScore(testStart, user,1, user1, "b"));
                        }
                    }
                }


            } else {//给副首席打分
                List<User> users0 = systemDao.findUserInRole2(1, 1);//体系办
                List<User> users1 = systemDao.findUserInRole2(systemId, 3);//首席
                List<User> users2 = systemDao.findUserInRole2(systemId, 6);//岗位专家
                List<User> users3 = systemDao.findUserInRole2(systemId, 7);//实验站站长
                List<Integer> ids = new ArrayList<>();//存放打分人的id，为了解决多角色重复打分
                for (User user1 : users0) {//体系办to副首席
                    if (!ids.contains(user1.getId())) {
                        ids.add(user1.getId());
                        testScoreDao.addScore(new TestScore(testStart, user,2, user1, "d"));
                    }
                }
                for (User user1 : externalInUsers) {//外聘专家to副首席
                    if (!ids.contains(user1.getId())) {
                        ids.add(user1.getId());
                        testScoreDao.addScore(new TestScore(testStart, user, 2,user1, "d"));
                    }
                }
                for (User user1 : users1) {//首席to副首席
                    if (!ids.contains(user1.getId())) {
                        ids.add(user1.getId());
                        testScoreDao.addScore(new TestScore(testStart, user, 2,user1, "e"));
                    }
                }
                for (User user1 : users2) {//岗位专家to副首席
                    if (user1.getId() != user.getId()) {//去除自己打分
                        if (!ids.contains(user1.getId())) {
                            ids.add(user1.getId());
                            testScoreDao.addScore(new TestScore(testStart, user, 2,user1, "f"));
                        }
                    }
                }
                for (User user1 : users3) {//实验站站长to副首席
                    if (user1.getId() != user.getId()) {
                        if (!ids.contains(user1.getId())) {
                            ids.add(user1.getId());
                            testScoreDao.addScore(new TestScore(testStart, user, 2,user1, "f"));
                        }
                    }
                }
            }


        }
    }

    //用于首席启动考评
    public void addTestScore(TestStart testStart) {
        //查出首席和副首席
        List<User> sxs = userDao.findTestPer3(testStart.getSystem().getId());
        Set<User> users = testStart.getUsers();
        for (User user : users) {
            Set<Role> roles=user.getRoles();
            int role1=0;
            for (Role role : user.getRoles()) {//判断用户为岗位专家还是实验站站长还是研究室主任
                if (role.getId() == 5) {
                    role1 = 5;
                    break;
                }
                if (role.getId() == 6) {
                    role1 = 3;
                }
                if (role.getId() == 7) {
                    role1 = 4;
                    break;
                }
            }
            List<Integer> ids = new ArrayList<>();//存放打分人的id，为了解决多角色重复打分
            for (User sx : sxs) {//首席、副首席to岗位专家、实验站站长、研究室主任
                if (!ids.contains(sx.getId())) {
                    ids.add(sx.getId());
                    TestScore testScore = new TestScore(testStart, user,role1, sx, "g");
                    testScoreDao.addScore(testScore);
                }
            }
            for (User user1 : users) {//岗位专家、实验站站长to岗位专家、实验站站长、研究室主任
                if (!user1.equals(user)) {
                    if (!ids.contains(user1.getId())) {
                        ids.add(user1.getId());
                        TestScore testScore = new TestScore(testStart, user,role1, user1, "h");
                        testScoreDao.addScore(testScore);
                    }
                }
            }
        }
    }

    public void deleteScore(int testStartId) {
        testScoreDao.deleteScore(testStartId);
    }

    /**
     * 根据打分人id查找
     * @return
     */
    public List findByEvaluation(Integer evaluationId){
        List<TestScore> testScores=testScoreDao.findByEvaluation(evaluationId);
        List<TestScore> testScores1=new ArrayList<>();
        for (TestScore testScore:testScores){
            TestScore temp=new TestScore();
            System system=new System(testScore.getTestStart().getSystem().getId(),testScore.getTestStart().getSystem().getSystemName());
            TestStart testStart=new TestStart();
            testStart.setSystem(system);
            testStart.setAddress(testScore.getTestStart().getAddress());
            testStart.setYear(testScore.getTestStart().getYear());
            testStart.setDate(testScore.getTestStart().getDate());
            testStart.setState(testScore.getTestStart().getState());
            User user=new User(testScore.getEvaluation().getId(),testScore.getEvaluation().getProfile().getName());
            User user1=new User(testScore.getEvaluationed().getId(),testScore.getEvaluationed().getProfile().getName());
            temp.setId(testScore.getId());
            temp.setA1(testScore.getA1());
            temp.setA2(testScore.getA2());
            temp.setA3(testScore.getA3());
            temp.setA4(testScore.getA4());
            temp.setA5(testScore.getA5());
            temp.setA6(testScore.getA6());
            temp.setRole(testScore.getRole());
            temp.setTestType(testScore.getTestType());
            temp.setTime(testScore.getTime());
            temp.setTestStart(testStart);
            temp.setEvaluation(user);
            temp.setEvaluationed(user1);
            testScores1.add(temp);
        }



        return testScores1;
    }

    /**
     * 打分
     */
    public void score(int id,int a1,int a2,int a3,int a4,int a5,int a6,int sum,String time){
        testScoreDao.score(id, a1, a2, a3, a4, a5, a6, sum,time);
    }

    /**
     * 根据id查找
     */
    public TestScore findById(int id){
        return testScoreDao.findById(id);
    }

}
