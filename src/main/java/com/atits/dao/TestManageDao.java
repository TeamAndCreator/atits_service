package com.atits.dao;

import com.atits.entity.TestManage;
import com.atits.entity.User;
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

    public void save(TestManage testManage){
        getSession().save(testManage);
    }

    /**
     * 删除一个testStart中的所有testManage
     * @param testStartId
     */
    public void deleteByTestStart(int testStartId){
        String hql="delete from TestManage as t where t.testStart.id=:testStartId";
        getSession().createQuery(hql).setParameter("testStartId",testStartId).executeUpdate();
    }

    /**
     * 查找一个用户的所有评分
     * @param userId
     * @return
     */
    public List findOwn(int userId){
        String hql="select new TestManage(tm.id, tm.testStart.year, tm.testStart.system.id, tm.testStart.system.systemName, tm.testStart.date, tm.testStart.address, tm.scoreUser.profile.name, tm.sum) from TestManage as tm where tm.scoreUser.id=:userId";
        return getSession().createQuery(hql).setParameter("userId",userId).list();
    }

    /**
     * 查找一个体系所有的评分
     * @param systemId
     * @return
     */
    public List findSystemTestManage(int systemId){
        String hql="select new TestManage(tm.id, tm.testStart.year, tm.testStart.system.id, tm.testStart.system.systemName, tm.testStart.date, tm.testStart.address, tm.scoreUser.profile.name, tm.sum) from TestManage as tm where tm.testStart.system.id=:systemId";
        return getSession().createQuery(hql).setParameter("systemId",systemId).list();
    }
}
