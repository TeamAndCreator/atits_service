package com.atits.dao;

import com.atits.entity.Dynamic;
import com.atits.entity.Files;
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
public class DynamicDao {
    @Autowired
    SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    /**
     * 添加一个dynamic
     * @param dynamic
     */
    public void save(Dynamic dynamic){
        getSession().save(dynamic);
    }

    /**
     * 根据id删除一个Dynamic
     * @param id
     */
    public void deleteById(Integer id){
        Dynamic dynamic=findById(id);
        getSession().delete(dynamic);
    }

    /**
     * 根据id数组批量删除Dynamic
     * @param idList
     */
    public void deleteByIds(List<Integer> idList){
        getSession().createQuery("delete from Dynamic where id in:idList").setParameterList("idList",idList).executeUpdate();
    }

    /**
     * 更新一个Dynamic
     * @param dynamic
     */
    public void update(Dynamic dynamic){
        getSession().update(dynamic);
    }

    /**
     *查找所有Dynamic
     */
    public List<Dynamic> findAll(){
        String hql="from Dynamic ";
        return getSession().createQuery(hql).list();
    }

    /**
     * 根据id查找一个Dynamic
     * @param id
     * @return
     */
    public Dynamic findById(Integer id){
        String hql="from Dynamic where id=:id";
        return (Dynamic) getSession().createQuery(hql).setParameter("id",id).uniqueResult();
    }
    /**
     * 根据activity的id查找其files
     * 用于更新文件
     */
    public Set<Files> getFiles(Integer id){
        String hql="select a.files from Dynamic as a where a.id=:id";
        List list= getSession().createQuery(hql).setParameter("id",id).list();
        Set<Files> filesSet=new HashSet(list);
        return filesSet;
    }
}
