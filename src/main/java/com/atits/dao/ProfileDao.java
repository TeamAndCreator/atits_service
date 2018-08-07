package com.atits.dao;

import com.atits.entity.Profile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileDao {
    @Autowired
    SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    /**
     * 根据userid获取profile
     */
    public Profile findById(int id){
        String hql="from Profile where id=:id";
        return (Profile) getSession().createQuery(hql).setParameter("id",id).uniqueResult();
    }

    /**
     * 更新一个profile
     */
    public void update(Profile profile){
        getSession().update(profile);
    }


}
