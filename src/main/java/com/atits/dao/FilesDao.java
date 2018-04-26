package com.atits.dao;

import com.atits.entity.Files;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YXX
 * @Date 2017年6月20日
 * @类型 filesDao
 */
@Repository
public class FilesDao {
    // 实例化：私有实体类，类的实例化
    private Files files;// 实例

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {// 获取session
        return sessionFactory.getCurrentSession();
    }

    public Files findById(Integer id){
        String hql="from Files where id=:id";
        return (Files) getSession().createQuery(hql).setParameter("id",id).uniqueResult();
    }


    public void deleteById(Integer id){
        Files files=findById(id);
        getSession().delete(files);
    }

}
