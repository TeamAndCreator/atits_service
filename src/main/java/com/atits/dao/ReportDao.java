package com.atits.dao;

import com.atits.entity.Report;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zys
 */
@Repository
public class ReportDao {
    @Autowired
    SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    /**
     * 添加一个report
     * @param report
     */
    public void save(Report report){
        getSession().save(report);
    }

    /**
     * 根据id删除一个Report
     * @param id
     */
    public void deleteById(Integer id){
        Report report=findById(id);
        getSession().delete(report);
    }

    /**
     * 根据id数组批量删除Report
     * @param idList
     */
    public void deleteByIds(List<Integer> idList){
        getSession().createQuery("delete from t_report where id in:idList").setParameterList("idList",idList).executeUpdate();
    }

    /**
     * 更新一个Report
     * @param report
     */
    public void update(Report report){
        getSession().update(report);
    }

    /**
     *查找所有Report
     */
    public List<Report> findAll(){
        String hql="from t_report";
        return getSession().createQuery(hql).list();
    }

    /**
     * 根据id查找一个Report
     * @param id
     * @return
     */
    public Report findById(Integer id){
        String hql="from t_report where id=:id";
        return (Report) getSession().createQuery(hql).setParameter("id",id).uniqueResult();
    }
}
