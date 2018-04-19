package com.atits.dao;

import com.atits.entity.Laboratory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zys
 */
@Repository
public class LaboratoryDao {

    @Autowired
    SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    /**
     * 添加一个Laboratory
     * @param laboratory
     */
    public void save(Laboratory laboratory){
        getSession().save(laboratory);
    }

    /**
     * 根据id删除一个Laboratory
     * @param id
     */
    public void deletById(Integer id){
        Laboratory laboratory=findById(id);
        getSession().delete(laboratory);
    }

    /**
     * 根据id数组批量删除Laboratory
     * @param idList
     */
    public void deletByIds(List<Integer> idList){
        getSession().createQuery("delete from t_laboratory where id in:idList").setParameterList("idList",idList).executeUpdate();
    }

    /**
     * 更新一个Laboratory
     * @param laboratory
     */
    public void update(Laboratory laboratory){
        getSession().update(laboratory);
    }

    /**
     *查找所有Laboratory
     */
    public List<Laboratory> findAll(){
        String hql="from t_laboratory";
        return getSession().createQuery(hql).list();
    }

    /**
     * 根据id查找一个Laboratory
     * @param id
     * @return
     */
    public Laboratory findById(Integer id){
        String hql="from t_laboratory where id=:id";
        return (Laboratory) getSession().createQuery(hql).setParameter("id",id).uniqueResult();
    }

}
