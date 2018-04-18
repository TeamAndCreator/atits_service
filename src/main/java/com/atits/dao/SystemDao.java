package com.atits.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SystemDao {

    @Autowired
     SessionFactory sessionFactory;

    private Session getSession(){ return sessionFactory.getCurrentSession();}

    public List<System> findAll(){
        String hql="from t_system ";
        return getSession().createQuery(hql).list();
    }

}
