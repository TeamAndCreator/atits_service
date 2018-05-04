package com.atits.dao;

import com.atits.entity.Files;
import com.atits.entity.Regulation;
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

    /**
     * 根据Regulation的id查找其files
     * 用于更新文件
     */
    public Set<Files> getFiles(Integer id){
        String hql="select a.files from Regulation as a where a.id=:id";
        List list= getSession().createQuery(hql).setParameter("id",id).list();
        Set<Files> filesSet=new HashSet(list);
        return filesSet;
    }

    /**
     * 分页
     */
    public List findPage(int startRow,int pageSize){
        String hql="select new Regulation (id,title)from Regulation order by id";
        return getSession().createQuery(hql).setFirstResult(startRow).setMaxResults(pageSize).list();
    }

    /**
     * 获取个数
     */
    public int getCount(){
        String hql="select count(*) from Regulation ";
        Long temp=(long)getSession().createQuery(hql).uniqueResult();
        int count=temp.intValue();
        return count;
    }
}
