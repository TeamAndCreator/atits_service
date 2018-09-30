package com.atits.service;

import com.atits.dao.LaboratoryDao;
import com.atits.entity.Laboratory;
import com.atits.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class LaboratoryService {
    @Resource
    private LaboratoryDao laboratoryDao;

    /**
     * 添加一个Laboratory
     *
     * @param laboratory
     */
    public void save(Laboratory laboratory) {
        laboratoryDao.save(laboratory);
    }


    /**
     * 通过id删除一个Laboratory
     *
     * @param id
     */
    public void deleteById(Integer id) {
        laboratoryDao.deleteById(id);
    }

    /**
     * 根据id数组批量删除Laboratory
     *
     * @param idList
     */
    public void deleteByIds(List<Integer> idList) {
        for (int id:idList){
            laboratoryDao.deleteLabId(id);
        }
        laboratoryDao.deleteByIds(idList);
    }

    /**
     * 更新一个Laboratory
     *
     * @param laboratory
     */
    public void update(Laboratory laboratory) {
        laboratoryDao.update(laboratory);
    }


    /**
     * 根据id查找一个Laboratory
     *
     * @param id
     */
    public Laboratory findById(Integer id) {
        return laboratoryDao.findById(id);
    }

    /**
     * 查找所有Laboratory
     */
    public List<Laboratory> findAll() {
        return laboratoryDao.findAll();
    }

    /**
     * 查找所有的lab(id,labName,company,system.systemName,time,date,state)及主任
     */
    public List findAll1(int systemId) {
        List<Map> laboratories = new ArrayList<>();
        List<Laboratory> laboratoryList;
        if (systemId == 1) {
            laboratoryList = laboratoryDao.findAll2();
        } else {
            laboratoryList = laboratoryDao.findAllInSystem2(systemId);
        }
        for (Laboratory laboratory:laboratoryList){
            List<String> zr=laboratoryDao.findUserInRole(laboratory.getId(),5);
            Map temp=new HashMap();
            temp.put("laboratory",laboratory);
            temp.put("zr",zr);
            laboratories.add(temp);
        }
        return laboratories;
    }


    /**
     * 分页
     */
    public List findPage(int startRow, int pageSize) {
        return laboratoryDao.findPage(startRow, pageSize);
    }

    /**
     * 获取某个体系的所有研究室id,name
     */
    public List findAllInSystem(int systemId) {
        return laboratoryDao.findAllInSystem(systemId);
    }

    /**
     * 获取某个研究室具有某个权限的所有user
     */
    public List findUserInRole(int laboratoryId, int roleId) {
        return laboratoryDao.findUserInRole(laboratoryId, roleId);
    }

    /**
     * 获取个数
     */
    public int getCount() {
        return laboratoryDao.getCount();
    }

    /**
     * 更新状态（激活）
     */
    public void updateState(int labId){
        laboratoryDao.updateState(labId);
    }

    /**
     * 获取某个研究室具有某个权限的所有user
     */
    public List findUserInRole2(int laboratoryId, int roleId) {
        return laboratoryDao.findUserInRole2(laboratoryId, roleId);
    }
}

