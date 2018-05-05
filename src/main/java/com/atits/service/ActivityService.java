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
     * 获取个数
     */
    public int getCount(){
        return activityDao.getCount();
    }
}
