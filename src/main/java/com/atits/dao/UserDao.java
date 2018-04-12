package com.atits.dao;

import com.atits.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {//获取session
        return sessionFactory.getCurrentSession();
    }

    /**
     *查找所有user
     */
    public List<User> findAll() {
        System.out.println(sessionFactory);
        String hql = "from t_user";
        List list = getSession().createQuery(hql).list();
        return list;
    }

    /**
     *添加一个user
     */
    public void save(User user){
        getSession().save(user);
    }







}
