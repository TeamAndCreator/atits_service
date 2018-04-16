package com.atits.service;

import com.atits.dao.SystemDao;
import com.atits.entity.System;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class SystemService {
    @Resource
    private SystemDao systemDao;


    /**
     *查找所有system
     */
    public List<System> findAll(){
        return systemDao.findAll();
    }

    public void save(System system){
        systemDao.save(system);
    }
}
