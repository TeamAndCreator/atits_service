package com.atits.service;

import com.atits.dao.TestManageDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.awt.*;
import java.util.List;

@Transactional
@Service
public class TestManageService {

    @Resource
    private TestManageDao testManageDao;

    public List findAll(){return testManageDao.findAll();}

    public List insertAuto(){return testManageDao.insertAuto();}

}
