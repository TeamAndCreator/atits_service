package com.atits.service;

import com.atits.dao.TestStartDao;
import com.atits.entity.TestManage;
import com.atits.entity.TestStart;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class TestStartService {
    @Resource
    private TestStartDao testStartDao;

    public List findAll(){return testStartDao.findAll();}

    public void save(TestStart testStart){testStartDao.save(testStart);}

    public List<TestStart> findByState(int state){return testStartDao.findByState(state);}

    public void deleteByIds(List<Integer> idList){testStartDao.deleteByIds(idList);}

    public void deleteById(Integer id){testStartDao.deleteById(id);}

    //只查出id和所属体系id和考评人员
    public TestStart findById(Integer id){
        TestStart testStart=testStartDao.findById(id);
        List users=testStartDao.findUsers(id);
        testStart.setUsers(new HashSet<>(users));
        return testStart;
    }

    public void update(TestStart testStart){testStartDao.update(testStart);}

    public void updateState(int id,int state){
        testStartDao.updateState(id,state);
    }
}
