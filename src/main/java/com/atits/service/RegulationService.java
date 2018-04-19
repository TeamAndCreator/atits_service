package com.atits.service;

import com.atits.dao.RegulationDao;
import com.atits.entity.Regulation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class RegulationService {
    @Resource
    private RegulationDao regulationDao;

    /**
     * 添加一个Regulation
     * @param regulation
     */
    public void save(Regulation regulation){
        regulationDao.save(regulation);
    }


    /**
     * 通过id删除一个Regulation
     * @param id
     */
    public void deletById(Integer id){
        regulationDao.deletById(id);
    }

    /**
     * 根据id数组批量删除Regulation
     * @param idList
     */
    public void deletByIds(List<Integer> idList){
        regulationDao.deletByIds(idList);
    }

    /**
     * 更新一个Regulation
     * @param regulation
     */
    public void update(Regulation regulation){
        regulationDao.update(regulation);
    }


    /**
     * 根据id查找一个Regulation
     * @param id
     */
    public Regulation findById(Integer id){
        return regulationDao.findById(id);
    }

    /**
     *查找所有Regulation
     */
    public List<Regulation> findAll(){
        return regulationDao.findAll();
    }
}
