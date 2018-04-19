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
     * 添加一个system
     * @param system
     */
    public void save(System system){
        systemDao.save(system);
    }


    /**
     * 通过id删除一个system
     * @param id
     */
    public void deletById(Integer id){
        systemDao.deletById(id);
    }

    /**
     * 根据id数组批量删除system
     * @param idList
     */
    public void deletByIds(List<Integer> idList){
        systemDao.deletByIds(idList);
    }

    /**
     * 更新一个system
     * @param system
     */
    public void update(System system){
        systemDao.update(system);
    }


    /**
     * 根据id查找一个system
     * @param id
     */
    public System findById(Integer id){
        return systemDao.findById(id);
    }

    /**
     *查找所有system
     */
    public List<System> findAll(){
        return systemDao.findAll();
    }
}
