package com.atits.service;

import com.atits.dao.TestStartDao;
import com.atits.entity.TestManage;
import com.atits.entity.TestStart;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class TestStartService {
    @Resource
    private TestStartDao testStartDao;

    public List findAll(){return testStartDao.findAll();}

    public void save(TestStart testStart){testStartDao.save(testStart);}

}
