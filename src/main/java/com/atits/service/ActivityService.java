package com.atits.service;

import com.atits.dao.ActivityDao;
import com.atits.entity.Activity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
    public void deletById(Integer id){
        activityDao.deletById(id);
    }

    /**
     * 根据id数组批量删除Activity
     * @param idList
     */
    public void deletByIds(List<Integer> idList){
        activityDao.deletByIds(idList);
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
}
