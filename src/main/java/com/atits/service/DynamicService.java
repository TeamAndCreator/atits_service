package com.atits.service;

import com.atits.dao.DynamicDao;
import com.atits.dao.UserDao;
import com.atits.entity.Dynamic;
import com.atits.entity.Files;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class DynamicService {
    @Resource
    private DynamicDao dynamicDao;

    @Resource
    private UserDao userDao;

    /**
     * 添加一个Dynamic
     * @param dynamic
     */
    public void save(Dynamic dynamic){
        dynamicDao.save(dynamic);
    }


    /**
     * 通过id删除一个Dynamic
     * @param id
     */
    public void deleteById(Integer id){
        dynamicDao.deleteById(id);
    }

    /**
     * 根据id数组批量删除Dynamic
     * @param idList
     */
    public void deleteByIds(List<Integer> idList) {
        for (Integer id : idList) {
            dynamicDao.deleteById(id);
        }
    }

    /**
     * 更新一个Dynamic
     * @param dynamic
     */
    public void update(Dynamic dynamic){
        dynamicDao.update(dynamic);
    }


    /**
     * 根据id查找一个Dynamic
     * @param id
     */
    public Dynamic findById(Integer id){
        return dynamicDao.findById(id);
    }

    /**
     *查找所有Dynamic
     */
    public List<Dynamic> findAll(){
        return dynamicDao.findAll();
    }

    /**
     * 查找Dynamic中的文件
     * @param id
     * @return
     */
    public Set<Files> getFiles(Integer id){return dynamicDao.getFiles(id);}

    /**
     * 获取体系办需要看到的
     */
    public List findForTXB(){
        List<Dynamic> dynamics=dynamicDao.findAll2();
        return dynamics;
    }

    /**
     * 获取首席需要看到的
     */
    public List findForSX(int systemId){
        List<Dynamic> dynamics=dynamicDao.findAll3(systemId);//获取本体系未审核，未通过，通过的
        List<Dynamic> dynamics1=dynamicDao.findAll4();//获取所有展示的
        dynamics.addAll(dynamics1);
        return dynamics;
    }

    /**
     * 获取体系办、首席之外的人需要看的
     */
    public List findFor(int userId){
        List<Dynamic> dynamics=dynamicDao.findAll4();//获取所有展示的
        int systemId=userDao.findById(userId).getSystem().getId();
        List<Dynamic> dynamics1=dynamicDao.findAll5(systemId);//获取本体系通过的
        List<Dynamic> dynamics2=dynamicDao.findAll6(userId);//获取本人未审核或未通过的
        dynamics2.addAll(dynamics1);
        dynamics2.addAll(dynamics);
        return dynamics2;
    }


    /**
     * 分页
     */
    public List findPage(int startRow,int pageSize){
        return dynamicDao.findPage(startRow,pageSize);
    }

    /**
     * 获取所有id，title，date
     * @return
     */
    public List findAll1(){
        return dynamicDao.findAll1();
    }

    /**
     * 获取个数
     */
    public int getCount(){
        return dynamicDao.getCount();
    }

    /**
     * 修改状态
     */
    public void updateState(int id,int state){
        dynamicDao.updateState(id, state);
    }


}
