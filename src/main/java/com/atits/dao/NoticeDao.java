package com.atits.dao;

import com.atits.entity.Notice;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zys
 */
@Repository
public class NoticeDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * 增加一个通知公告
     */
    public void save(Notice notice){
        getSession().save(notice);
    }

    /**
     * 删除一个通知公告
     */
    public void delet(Notice notice){
        getSession().delete(notice);
    }

    /**
     * 查找所有的通知公告
     */
    public List<Notice> findAll(){
        String hql="from t_notice ";
        List<Notice> list=getSession().createQuery("from t_notice ").list();
        return list;
    }



}
