package com.atits.dao;

import com.atits.entity.Files;
import com.atits.entity.Notice;
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
public class NoticeDao {
    @Autowired
    SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    /**
     * 添加一个Notice
     * @param notice
     */
    public void save(Notice notice){
        getSession().save(notice);
    }

    /**
     * 根据id删除一个Notice
     * @param id
     */
    public void deleteById(Integer id){
        Notice notice=findById(id);
        getSession().delete(notice);
    }

    /**
     * 更新一个Notice
     * @param notice
     */
    public void update(Notice notice){
        getSession().update(notice);
    }

    /**
     *查找所有Notice
     */
    public List<Notice> findAll(){
        String hql="from Notice ";
        return getSession().createQuery(hql).list();
    }

    /**
     * 根据id查找一个Notice
     * @param id
     * @return
     */
    public Notice findById(Integer id){
        String hql="from Notice where id=:id";
        return (Notice) getSession().createQuery(hql).setParameter("id",id).uniqueResult();
    }

    /**
     * 根据Notice的id查找其files
     * 用于更新文件
     */
    public Set<Files> getFiles(Integer id){
        String hql="select a.files from Notice as a where a.id=:id";
        List list= getSession().createQuery(hql).setParameter("id",id).list();
        Set<Files> filesSet=new HashSet(list);
        return filesSet;
    }
}
