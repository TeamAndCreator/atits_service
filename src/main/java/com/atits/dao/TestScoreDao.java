package com.atits.dao;

import com.atits.entity.TestScore;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestScoreDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {//获取session
        return sessionFactory.getCurrentSession();
    }

    /**
     * 增加一个testScore
     * @param testScore
     */
    public void addScore(TestScore testScore){
        getSession().save(testScore);
    }

    /**
     * 删除一个testStart中的所有testScore
     */
    public void deleteScore(int testStartId){
        String hql="delete from TestScore as t where t.testStart.id=:testStartId";
        getSession().createQuery(hql).setParameter("testStartId",testStartId).executeUpdate();
    }

    /**
     * 根据打分人id查找
     * @return
     */
    public List findByEvaluation(Integer evaluationId){
        String hql="from TestScore as t where t.evaluation.id=:evaluationId";
        return getSession().createQuery(hql).setParameter("evaluationId",evaluationId).list();
    }

    /**
     * 根据得分人id和testStart.id查找
     * @return
     */
    public List findByEvaluationed(Integer evaluationedId,int testStartId){
        String hql="select t from TestScore t join t.testStart ts where t.evaluationed.id=:evaluationedId and ts.id =:testStartId";
        return getSession().createQuery(hql).setParameter("evaluationedId",evaluationedId).setParameter("testStartId",testStartId).list();
    }

    /**
     * 打分
     */
    public void score(int id,int a1,int a2,int a3,int a4,int a5,int a6,int sums,String time){
        String hql="update TestScore as ts set ts.A1=:a1,ts.A2=:a2,ts.A3=:a3,ts.A4=:a4,ts.A5=:a5,ts.A6=:a6,ts.sum=:sums,ts.time=:time where ts.id=:id";
        getSession().createQuery(hql).setParameter("id",id).setParameter("time",time).setParameter("sums",sums).setParameter("a1",a1).setParameter("a2",a2).setParameter("a3",a3).setParameter("a4",a4).setParameter("a5",a5).setParameter("a6",a6).executeUpdate();
    }



}
