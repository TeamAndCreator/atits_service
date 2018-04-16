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

    public List<System> findAll(){
        String hql="from t_system ";
        return getSession().createQuery(hql).list();
    }

    public void save(System system){
        getSession().save(system);
    }

}
