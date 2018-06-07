package com.atits.dao;


import com.atits.entity.TestWeight;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class TestWeightDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {//获取session
        return sessionFactory.getCurrentSession();
    }


    /*保存和更新方法：id=0 是保存，id≠0是更新*/
    public void save(TestWeight testWeight) {
            getSession().save(testWeight);
    }
    public void update(TestWeight testWeight){getSession().update(testWeight);}

    public void delete(Integer id){
        TestWeight testWeight = findById(id);
        getSession().delete(testWeight);
    }
    public void deletes(List<Integer> idList) {
        for (Integer id : idList) {
            TestWeight testWeight = findById(id);
            //执行为删除方法
            getSession().delete(testWeight);
        }
    }

    public TestWeight findById(Integer id) {
        String hql = "from TestWeight where id =:id";
        return (TestWeight) getSession().createQuery(hql).setParameter("id", id).uniqueResult();
    }
    public List<TestWeight> findAll(){
        String hql = "from TestWeight";
        return getSession().createQuery(hql).list();
    }


}
