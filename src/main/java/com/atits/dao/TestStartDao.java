package com.atits.dao;

import com.atits.entity.TestStart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestStartDao {

    @Autowired
    private SessionFactory sessionFactory;
    private Session getSession(){return sessionFactory.getCurrentSession();}

    public List findAll(){
        String hql = "from TestStart";
        return getSession().createQuery(hql).list();
    }

    public void save(TestStart testStart){
        getSession().save(testStart);
    }

    public TestStart findById(int id){
        String hql="from TestStart where TestStart.id=:id";
        return (TestStart) getSession().createQuery(hql).setParameter("id",id).uniqueResult();
    }

    public List<TestStart> findByState(int state){
        String hql = "from TestStart where state=:state";
        return getSession().createQuery(hql).setParameter("state",state).list();
    }
}
