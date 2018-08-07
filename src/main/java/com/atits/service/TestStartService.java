package com.atits.service;

import com.atits.dao.TestStartDao;
import com.atits.dao.TestWeightDao;
import com.atits.entity.System;
import com.atits.entity.TestManage;
import com.atits.entity.TestStart;
import com.atits.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class TestStartService {
    @Resource
    private TestStartDao testStartDao;
    @Resource
    private TestWeightDao testWeightDao;

    public List findAll() {
        List<TestStart> testStarts = testStartDao.findAll();
        List<TestStart> testStarts1 = new ArrayList<>();
        for (TestStart testStart : testStarts) {
            TestStart testStart1=new TestStart();
            System system = new System(testStart.getSystem().getId(), testStart.getSystem().getSystemName());
            Set<User> users = testStart.getUsers();
            Set<User> users1 = new HashSet<>();
            for (User user : users) {
                User tempUser = new User(user.getId(), user.getProfile().getName());
                if (user.getSystem() != null) {
                    tempUser.setSystem(new System(user.getSystem().getId(), user.getSystem().getSystemName()));
                }
                users1.add(tempUser);
            }
            testStart1.setId(testStart.getId());
            testStart1.setSystem(system);
            testStart1.setState(testStart.getState());
            testStart1.setTestWeight(testStart.getTestWeight());
            testStart1.setYear(testStart.getYear());
            testStart1.setAddress(testStart.getAddress());
            testStart1.setDate(testStart.getDate());
            testStart1.setUsers(users1);
            testStarts1.add(testStart1);
        }
        return testStarts1;
    }

    public void save(TestStart testStart) {
        testStartDao.save(testStart);
    }

    public List<TestStart> findByState(int state) {
        return testStartDao.findByState(state);
    }

    public void deleteByIds(List<Integer> idList){testStartDao.deleteByIds(idList);}

    public void deleteById(Integer id) {
        testStartDao.deleteById(id);
    }

    //只查出id和所属体系id和考评人员
    public TestStart findById(Integer id) {
        TestStart testStart = testStartDao.findById(id);
        List users = testStartDao.findUsers(id);
        testStart.setUsers(new HashSet<>(users));
        return testStart;
    }

    public void update(TestStart testStart) {
        testStartDao.update(testStart);
    }

    public void updateState(int id, int state) {
        testStartDao.updateState(id, state);
    }
}
