package com.atits.dao;


import com.atits.entity.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDao {

    @Autowired
    SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public List<Role> findAll(){
        String hql="from Role";
        return getSession().createQuery(hql).list();
    }

    public Role findById(Integer roleId){
        String hql = "from Role where id=:roleId";
        return (Role) getSession().createQuery(hql).setParameter("roleId",roleId).uniqueResult();
    }

}
