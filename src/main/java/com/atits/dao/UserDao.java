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
    public List findAll() {
        String hql = "from User ";
        return getSession().createQuery(hql).list();
    }

    /**
     * 添加一个user
     */
    public void save(User user) {
        getSession().save(user);
    }


    public User findByUserName(String username) {
        String hql = "from User where userName=:username";
        return (User) getSession().createQuery(hql).setParameter("username", username).uniqueResult();
    }

    public User findById(int id) {
        String hql = "from User where id=:id";
        return (User) getSession().createQuery(hql).setParameter("id", id).uniqueResult();
    }

    //获取该体系的所有用户
    public List<User> findBySysId(int sysId){
        String hql = "from User where system.id=:sysId";
        return getSession().createQuery(hql).setParameter("sysId",sysId).list();
    }

    //根据角色id获取该角色的所有用户
//    public List<User> findByRoleId(int roleId){
//        String hql = "from User where roles.id =: roleId";
//        return getSession().createQuery(hql).setParameter("roleId",roleId).list();
//    }

//    public User findByUserName(String username) {
//        String hql = "from User where userName=:username";
//        return (User) getSession().createQuery(hql).setParameter("username", username).uniqueResult();
//    }

//    public User findUser(int id) {
////        String hql = "from User where id=:id";
////        return (User) getSession().createQuery(hql).setParameter("id", id).uniqueResult();
////    }
}
