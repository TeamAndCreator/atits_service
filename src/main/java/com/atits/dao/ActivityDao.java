package com.atits.dao;


import com.atits.entity.Activity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zys
 */
@Repository
public class ActivityDao {

    @Autowired
    SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    /**
     * 添加一个Activity
     * @param activity
     */
    public void save(Activity activity){
        getSession().save(activity);
    }

    /**
     * 根据id删除一个Activity
     * @param id
     */
    public void deleteById(Integer id){
        Activity activity=findById(id);
        getSession().delete(activity);
    }

    /**
     * 根据id数组批量删除Activity
     * @param idList
     */
    public void deleteByIds(List<Integer> idList){
        getSession().createQuery("delete from Activity where id in:idList").setParameterList("idList",idList).executeUpdate();
    }

    /**
     * 更新一个Activity
     * @param activity
     */
    public void update(Activity activity){
        getSession().update(activity);
    }

    /**
     *查找所有Activity
     */
    public List<Activity> findAll(){
        String hql="from Activity ";
        return getSession().createQuery(hql).list();
    }

    /**
     * 根据id查找一个Activity
     * @param id
     * @return
     */
    public Activity findById(Integer id){
        String hql="from Activity where id=:id";
        return (Activity) getSession().createQuery(hql).setParameter("id",id).uniqueResult();
    }
}
