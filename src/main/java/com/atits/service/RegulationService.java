package com.atits.service;

import com.atits.dao.RegulationDao;
import com.atits.entity.Files;
import com.atits.entity.Regulation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

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
    public void deleteById(Integer id){
        regulationDao.deleteById(id);
    }

    /**
     * 根据id数组批量删除Regulation
     * @param idList
     */
    public void deleteByIds(List<Integer> idList){
        for (Integer id:idList){
            regulationDao.deleteById(id);
        }
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

    /**
     * 查找Regulation中的文件
     * @param id
     * @return
     */
    public Set<Files> getFiles(Integer id){return regulationDao.getFiles(id);}

    /**
     * 分页
     */
    public List findPage(int startRow,int pageSize){
        return regulationDao.findPage(startRow,pageSize);
    }

    /**
     * 获取所有id，title，date
     * @return
     */
    public List findAll1(){
        return regulationDao.findAll1();
    }

    /**
     * 获取个数
     */
    public int getCount(){
        return regulationDao.getCount();
    }

}
