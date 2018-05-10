package com.atits.dao;

import com.atits.entity.TestManage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestManageDao {
    @Autowired
    private SessionFactory sessionFactory;
    private Session getSession(){return sessionFactory.getCurrentSession();}

    public List findAll(){
        String hql = "from TestManage";
        return getSession().createQuery(hql).list();
    }
/*
 testStart:state=2表示考评已经开始，此时TestManage.state=0表示未考评
 */
    public List insertAuto(){//还需要插入考评人员
        String hql = "insert into TestManage(year,date) select t.year,t.date from TestStart t where t.state = 2";
        return getSession().createQuery(hql).list();
    }

    public void save(TestManage testManage){
        getSession().save(testManage);
    }

}
