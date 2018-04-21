package com.atits.dao;

import com.atits.entity.System;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zys
 */
@Repository
public class SystemDao {

    @Autowired
     SessionFactory sessionFactory;

    private Session getSession(){ return sessionFactory.getCurrentSession();}


    /**
     * 添加一个system
     * @param system
     */
    public void save(System system){
        getSession().save(system);
    }

    /**
     * 根据id删除一个system
     * @param id
     */
    public void deleteById(Integer id){
        System system=findById(id);
        getSession().delete(system);
    }

    /**
     * 根据id数组批量删除system
     * @param idList
     */
    public void deleteByIds(List<Integer> idList){
        getSession().createQuery("delete from System where id in:idList").setParameterList("idList",idList).executeUpdate();
    }

    /**
     * 更新一个system
     * @param system
     */
    public void update(System system){
        getSession().update(system);
    }


    /**
     * 查找所有的system
     * @return
     */
    public List<System> findAll(){
        String hql="from System ";
        return getSession().createQuery(hql).list();
    }

    /**
     * 根据id查找一个system
     * @param id
     * @return
     */
    public System findById(Integer id){
        String hql="from System where id=:id";
        return (System) getSession().createQuery(hql).setParameter("id",id).uniqueResult();
    }

}
