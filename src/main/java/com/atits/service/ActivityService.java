package com.atits.service;

import com.atits.dao.ActivityDao;
import com.atits.entity.Activity;
import com.atits.entity.Files;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @author zys
 */
@Transactional
@Service
public class ActivityService {
    @Resource
    private ActivityDao activityDao;

    /**
     * 添加一个Activity
     * @param activity
     */
    public void save(Activity activity){
        activityDao.save(activity);
    }


    /**
     * 通过id删除一个Activity
     * @param id
     */
    public void deleteById(Integer id){
        activityDao.deleteById(id);
    }

    /**
     * 根据id数组批量删除Activity
     * @param idList
     */
    public void deleteByIds(List<Integer> idList) {
        for (Integer id : idList) {
            activityDao.deleteById(id);
        }
    }

    /**
     * 更新一个Activity
     * @param activity
     */
    public void update(Activity activity){
        activityDao.update(activity);
    }


    /**
     * 根据id查找一个Activity
     * @param id
     */
    public Activity findById(Integer id){
        return activityDao.findById(id);
    }

    /**
     *查找所有Activity
     */
    public List<Activity> findAll(){
        return activityDao.findAll();
    }

    /**
     * 查找Activity中的文件
     * @param id
     * @return
     */
    public Set<Files> getFiles(Integer id){return activityDao.getFiles(id);}

    /**
     * 分页
     */
    public List findPage(int startRow,int pageSize){
        return activityDao.findPage(startRow,pageSize);
    }

    /**
     * 获取所有id，title，date
     * @return
     */
    public List findAll1(){
        return activityDao.findAll1();
    }

    /**
     * 获取所有的id，标题，发布者，发布时间，状态
     */
    public List findAll2(){
        return activityDao.findAll2();
    }

    /**
     * 获取所有的id，体系名称，标题，发布者，发布时间，状态(用于省体系办)
     * @return
     */
    public List findForTXB(){
        return activityDao.findAll3();
    }

    /**
     * 获取所有通过的、本体系未通过或未审核的activity（用于首席）
     * @param systemId
     * @return
     */
    public List findForSX(int systemId){
        List<Activity> activities1=activityDao.findAll4();//获取所有通过的activity
        List<Activity> activities2=activityDao.findAll5(systemId);//获取本体系未通过或未审核的activity
        activities2.addAll(activities1);//合并
        return activities2;
    }

    /**
     * 获取本体系和体系办所有通过的activity（用于除体系办和首席外的人）
     * @return
     */
    public List findFor(int systemId){
        List<Activity> activities=activityDao.findAll6(systemId);
        return activities;
    }

    /**
     * 获取个数
     */
    public int getCount(){
        return activityDao.getCount();
    }

    /**
     * 修改状态从0（待审核）到1（通过）或2（未通过）
     */
    public void updateState(int id,int state){
        activityDao.updateState(id,state);
    }

    public void update1(int id,String title,String content){
        activityDao.update1(id, title, content);
    }

}
