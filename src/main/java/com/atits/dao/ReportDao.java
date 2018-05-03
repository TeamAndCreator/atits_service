package com.atits.dao;

import com.atits.entity.Files;
import com.atits.entity.Report;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
     * 更新一个Report
     * @param report
     */
    public void update(Report report){
        getSession().update(report);
    }

    /**
     *查找所有Report
     */
    public List findAll(){
        String hql="from Report ";
        return getSession().createQuery(hql).list();
    }

    /**
     * 根据id查找一个Report
     * @param id
     * @return
     */
    public Report findById(Integer id){
        String hql="from Report where id=:id";
        return (Report) getSession().createQuery(hql).setParameter("id",id).uniqueResult();
    }

    /**
     * 根据Report的id查找其files
     * 用于更新文件
     */
    public Set<Files> getFiles(Integer id){
        String hql="select a.files from Report as a where a.id=:id";
        List list= getSession().createQuery(hql).setParameter("id",id).list();
        Set<Files> filesSet=new HashSet(list);
        return filesSet;
    }
}
