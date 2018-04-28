package com.atits.dao;

import com.atits.entity.Regulation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zys
 */
@Repository
public class RegulationDao {
    @Autowired
    SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    /**
     * 添加一个Regulation
     * @param regulation
     */
    public void save(Regulation regulation){
        getSession().save(regulation);
    }

    /**
     * 根据id删除一个Regulation
     * @param id
     */
    public void deleteById(Integer id){
        Regulation regulation=findById(id);
        getSession().delete(regulation);
    }

    /**
     * 更新一个Regulation
     * @param regulation
     */
    public void update(Regulation regulation){
        getSession().update(regulation);
    }

    /**
     *查找所有Regulation
     */
    public List<Regulation> findAll(){
        String hql="from Regulation ";
        return getSession().createQuery(hql).list();
    }

    /**
     * 根据id查找一个Regulation
     * @param id
     * @return
     */
    public Regulation findById(Integer id){
        String hql="from Regulation where id=:id";
        return (Regulation) getSession().createQuery(hql).setParameter("id",id).uniqueResult();
    }
}
