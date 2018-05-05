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
    public void deleteById(Integer id){
        Laboratory laboratory=findById(id);
        getSession().delete(laboratory);
    }

    /**
     * 根据id数组批量删除Laboratory
     * @param idList
     */
    public void deleteByIds(List<Integer> idList){
        getSession().createQuery("delete from Laboratory where id in:idList").setParameterList("idList",idList).executeUpdate();
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
        String hql="from Laboratory ";
        return getSession().createQuery(hql).list();
    }

    /**
     * 根据id查找一个Laboratory
     * @param id
     * @return
     */
    public Laboratory findById(Integer id){
        String hql="from Laboratory where id=:id";
        return (Laboratory) getSession().createQuery(hql).setParameter("id",id).uniqueResult();
    }

    /**
     * 分页
     */
    public List findPage(int startRow,int pageSize){
        String hql="select new Laboratory (id,labName)from Laboratory order by id";
        return getSession().createQuery(hql).setFirstResult(startRow).setMaxResults(pageSize).list();
    }

    /**
     * 获取个数
     */
    public int getCount(){
        String hql="select count(*) from Laboratory ";
        Long temp=(long)getSession().createQuery(hql).uniqueResult();
        int count=temp.intValue();
        return count;
    }
}
