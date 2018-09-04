package com.atits.dao;

import com.atits.entity.System;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zys
 */
@Repository
public class SystemDao {

    @Autowired
     SessionFactory sessionFactory;

    private Session getSession(){ return sessionFactory.getCurrentSession();}


    /**
     * 添加一个system
     * @param system
     */
    public void save(System system){
        getSession().save(system);
    }

    /**
     * 根据id删除一个system
     * @param id
     */
    public void deleteById(Integer id){
        System system=findById(id);
        getSession().delete(system);
    }

    /**
     * 根据id数组批量删除system
     * @param idList
     */
    public void deleteByIds(List<Integer> idList){
        getSession().createQuery("delete from System where id in:idList").setParameterList("idList",idList).executeUpdate();
    }

    /**
     * 更新一个system
     * @param system
     */
    public void update(System system){
        getSession().update(system);
    }


    /**
     * 查找所有的system
     * @return
     */
    public List<System> findAll(){
        String hql="from System ";
        return getSession().createQuery(hql).list();
    }

    /**
     * 根据id查找一个system
     * @param id
     * @return
     */
    public System findById(Integer id){
        String hql="from System where id=:id";
        return (System) getSession().createQuery(hql).setParameter("id",id).uniqueResult();
    }

    /**
     * 分页
     */
    public List findPage(int startRow,int pageSize){
        String hql="select new System(id,systemName) from System order by id";
        return getSession().createQuery(hql).setFirstResult(startRow).setMaxResults(pageSize).list();
    }

    /**
     * 获取所有体系名称及id
     */
    public List findAll1(){
        String hql="select new System (id,systemName) from System ";
        return getSession().createQuery(hql).list();
    }

    /**
     * 获取某个体系里具有某个权限的所有人
     */
    public List findUserInRole(int systemId,int roleId){
        String hql="select p.name from User u join u.roles r,Profile p where u.system.id=:systemId and r.id=:roleId and u.profile=p";
        return getSession().createQuery(hql).setParameter("systemId",systemId).setParameter("roleId",roleId).list();
    }

    /**
     * 获取某个体系里具有某个权限的所有人
     */
    public List findUserInRole2(int systemId,int roleId){
        String hql="select new User (u.id,p.name) from User u join u.roles r,Profile p where u.system.id=:systemId and r.id=:roleId and u.profile=p";
        return getSession().createQuery(hql).setParameter("systemId",systemId).setParameter("roleId",roleId).list();
    }


    /**
     * 获取个数
     */
    public int getCount(){
        String hql="select count(*) from System";
        Long temp=(long)getSession().createQuery(hql).uniqueResult();
        int count=temp.intValue();
        return count;
    }

    /**
     * 修改content
     */
    public void content_change(int systemId,String content){
        String hql="update System set content=:content where id=:systemId";
        getSession().createQuery(hql).setParameter("content",content).setParameter("systemId",systemId).executeUpdate();
    }

    /**
     * 修改overView
     */
    public void overView_change(int systemId,String overView){
        String hql="update System set overView=:overView where id=:systemId";
        getSession().createQuery(hql).setParameter("overView",overView).setParameter("systemId",systemId).executeUpdate();
    }
}
