package com.atits.service;

import com.atits.dao.HarvestDao;
import com.atits.entity.Files;
import com.atits.entity.Harvest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class HarvestService {
    @Resource
    private HarvestDao harvestDao;

    /**
     * 添加一个Harvest
     * @param harvest
     */
    public void save(Harvest harvest){
        harvestDao.save(harvest);
    }


    /**
     * 通过id删除一个Harvest
     * @param id
     */
    public void deleteById(Integer id){
        harvestDao.deleteById(id);
    }

    /**
     * 根据id数组批量删除Harvest
     * @param idList
     */
    public void deleteByIds(List<Integer> idList){
        for (Integer id:idList){
            harvestDao.deleteById(id);
        }
    }

    /**
     * 更新一个Harvest
     * @param harvest
     */
    public void update(Harvest harvest){
        harvestDao.update(harvest);
    }


    /**
     * 根据id查找一个Harvest
     * @param id
     */
    public Harvest findById(Integer id){
        return harvestDao.findById(id);
    }

    /**
     *查找所有Harvest
     */
    public List<Harvest> findAll(){
        return harvestDao.findAll();
    }

    /**
     * 查找Dynamic中的文件
     * @param id
     * @return
     */
    public Set<Files> getFiles(Integer id){return harvestDao.getFiles(id);}

}
