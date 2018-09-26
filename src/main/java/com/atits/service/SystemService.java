package com.atits.service;

import com.atits.dao.LaboratoryDao;
import com.atits.dao.StationDao;
import com.atits.dao.SystemDao;
import com.atits.dao.UserDao;
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

    @Resource
    private UserDao userDao;

    @Resource
    private LaboratoryDao laboratoryDao;

    @Resource
    private StationDao stationDao;

    /**
     * 添加一个system
     *
     * @param system
     */
    public void save(System system) {
        systemDao.save(system);
    }


    /**
     * 通过id删除一个system
     *
     * @param id
     */
    public void deleteById(Integer id) {
        systemDao.deleteById(id);
    }

    /**
     * 根据id数组批量删除system
     *
     * @param idList
     */
    public void deleteByIds(List<Integer> idList) {
        for (int id : idList) {
            userDao.deleteBySystemId(id);
            laboratoryDao.deleteBySystemId(id);
            stationDao.deleteBySystemId(id);
        }
        systemDao.deleteByIds(idList);
    }

    /**
     * 更新一个system
     *
     * @param system
     */
    public void update(System system) {
        systemDao.update(system);
    }


    /**
     * 根据id查找一个system
     *
     * @param id
     */
    public System findById(Integer id) {
        return systemDao.findById(id);
    }

    /**
     * 查找所有system
     */
    public List<System> findAll() {
        return systemDao.findAll();
    }

    /**
     * 分页
     */
    public List findPage(int startRow, int pageSize) {
        return systemDao.findPage(startRow, pageSize);
    }

    /**
     * 获取所有体系名称及id
     */
    public List findAll1() {
        return systemDao.findAll1();
    }

    /**
     * 获取某个体系里具有某个权限的所有人
     */
    public List findUserInRole(int systemId, int roleId) {
        return systemDao.findUserInRole(systemId, roleId);
    }

    /**
     * 获取某个体系里具有某个权限的所有人
     */
    public List findUserInRole2(int systemId, int roleId) {
        return systemDao.findUserInRole2(systemId, roleId);
    }

    /**
     * 获取个数
     */
    public int getCount() {
        return systemDao.getCount();
    }

    /**
     * 修改content
     */
    public void content_change(int systemId,String content){
        systemDao.content_change(systemId,content);
    }

    /**
     * 修改overView
     */
    public void overView_change(int systemId,String overView){
        systemDao.overView_change(systemId,overView);
    }
}
