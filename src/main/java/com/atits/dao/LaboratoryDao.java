package com.atits.dao;

import com.atits.entity.Laboratory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class LaboratoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * 添加一个Laboratory
     * @param laboratory
     */
    public void save(Laboratory laboratory){
        getSession().save(laboratory);
    }

    /**
     * 查找所有Laboratory
     * @return
     */
    public List<Laboratory> findAll(){
        String hql="from t_laboratory ";
        return getSession().createQuery(hql).list();
    }

}
