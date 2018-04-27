package com.atits.dao;

import com.atits.entity.Harvest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zys
 */
@Repository
public class HarvestDao {
    @Autowired
    SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    /**
     * 添加一个harvest
     * @param harvest
     */
    public void save(Harvest harvest){
        getSession().save(harvest);
    }

    /**
     * 根据id删除一个Harvest
     * @param id
     */
    public void deleteById(Integer id){
        Harvest harvest=findById(id);
        getSession().delete(harvest);
    }

    /**
     * 更新一个Harvest
     * @param harvest
     */
    public void update(Harvest harvest){
        getSession().update(harvest);
    }

    /**
     *查找所有Harvest
     */
    public List<Harvest> findAll(){
        String hql="from Harvest ";
        return getSession().createQuery(hql).list();
    }

    /**
     * 根据id查找一个Harvest
     * @param id
     * @return
     */
    public Harvest findById(Integer id){
        String hql="from Harvest where id=:id";
        return (Harvest) getSession().createQuery(hql).setParameter("id",id).uniqueResult();
    }
}
