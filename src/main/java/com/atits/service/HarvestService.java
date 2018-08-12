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


    /**
     * 获取所有的id，体系名称，标题，发布者，发布时间，状态(用于省体系办)
     * @return
     */
    public List findForTXB(){
        return harvestDao.findAll3();
    }

    /**
     * 获取所有通过的、本体系未通过或未审核的harvest（用于首席）
     * @param systemId
     * @return
     */
    public List findForSX(int systemId){
        List<Harvest> harvests=harvestDao.findAll4();//获取所有通过的harvest
        List<Harvest> harvests1=harvestDao.findAll5(systemId);//获取本体系未通过或未审核的harvest
        harvests1.addAll(harvests);//合并
        return harvests1;
    }

    /**
     * 获取本体系和体系办所有通过的harvest（用于除体系办和首席外的人）
     * @return
     */
    public List findFor(int systemId){
        List<Harvest> harvests=harvestDao.findAll6(systemId);
        return harvests;
    }


    /**
     * 分页
     */
    public List findPage(int startRow,int pageSize){
        return harvestDao.findPage(startRow,pageSize);
    }

    /**
     * 获取所有id，title，date
     * @return
     */
    public List findAll1(){
        return harvestDao.findAll1();
    }

    /**
     * 获取个数
     */
    public int getCount(){
        return harvestDao.getCount();
    }

    /**
     * 修改状态从0（待审核）到1（通过）或2（未通过）
     */
    public void updateState(int id,int state){
        harvestDao.updateState(id,state);
    }
}
