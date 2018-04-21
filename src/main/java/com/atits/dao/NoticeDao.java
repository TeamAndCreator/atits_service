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
     * 根据id数组批量删除Notice
     * @param idList
     */
    public void deleteByIds(List<Integer> idList){
        getSession().createQuery("delete from Notice where id in:idList").setParameterList("idList",idList).executeUpdate();
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



}
