package com.atits.service;

import com.atits.dao.DynamicDao;
import com.atits.entity.Dynamic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class DynamicService {
    @Resource
    private DynamicDao dynamicDao;

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
    public void deleteByIds(List<Integer> idList){
        dynamicDao.deleteByIds(idList);
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

}
