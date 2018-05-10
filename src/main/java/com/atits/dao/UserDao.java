package com.atits.dao;

import com.atits.entity.Role;
import com.atits.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

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


    //根据体系id和角色id来获取该体系与该角色的所有用户
    public List<User> findTestPer(int sysId, int roleId){
        String hql = "select distinct new User(u.id,u.userName) from User as u inner join u.roles as r where r.id =:roleId or u.system.id =:sysId";
        return getSession().createQuery(hql).setParameter("sysId",sysId).setParameter("roleId",roleId).list();
    }
//    public List<User> findBySysId(int sysId){
//        String hql = "from User where system.id =: sysId";
//        return getSession().createQuery(hql).setParameter("sysId",sysId).list();
//    }
//    public List<User> findByRoleId(int roleId){
//        String hql = "select distinct new User(u.id,u.userName) from User as u inner join u.roles as r where r.id =:roleId";
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
