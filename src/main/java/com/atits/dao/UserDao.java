package com.atits.dao;

import com.atits.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zys
 */
@Repository
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {//获取session
        return sessionFactory.getCurrentSession();
    }

    /**
     * 查找所有user
     */
    public List<User> findAll() {
        String hql = "from t_user";
        List list = getSession().createQuery(hql).list();
        return list;
    }

    /**
     * 添加一个user
     */
    public void save(User user) {
        getSession().save(user);
    }


    public User findUser(String username) {
        String hql = "from t_user where userName=:username";
        return (User) getSession().createQuery(hql).setParameter("username", username).uniqueResult();
    }

    public User findUser(int id) {
        String hql = "from t_user where id=:id";
        return (User) getSession().createQuery(hql).setParameter("id", id).uniqueResult();
    }
}
