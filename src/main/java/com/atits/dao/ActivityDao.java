package com.atits.dao;


import com.atits.entity.Activity;
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
public class ActivityDao {

    @Autowired
    SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    /**
     * 添加一个Activity
     * @param activity
     */
    public void save(Activity activity){
        getSession().save(activity);
    }

    /**
     * 根据id删除一个Activity
     * @param id
     */
    public void deleteById(Integer id){
        Activity activity=findById(id);
        getSession().delete(activity);
    }

    /**
     * 更新一个Activity
     * @param activity
     */
    public void update(Activity activity){
        getSession().update(activity);
    }

    /**
     *查找所有Activity
     */
    public List<Activity> findAll(){
        String hql="from Activity ";
        return getSession().createQuery(hql).list();
    }

    /**
     * 根据id查找一个Activity
     * @param id
     * @return
     */
    public Activity findById(Integer id){
        String hql="from Activity where id=:id";
        return (Activity) getSession().createQuery(hql).setParameter("id",id).uniqueResult();
    }

    /**
     * 根据activity的id查找其files
     * 用于更新文件
     */
    public Set<Files> getFiles(Integer id){
        String hql="select a.files from Activity as a where a.id=:id";
        List list= getSession().createQuery(hql).setParameter("id",id).list();
        Set<Files> filesSet=new HashSet(list);
        return filesSet;
    }

    /**
     * 分页
     */
    public List findPage(int startRow,int pageSize){
        String hql="select new Activity (id,title,date)from Activity order by id";
        return getSession().createQuery(hql).setFirstResult(startRow).setMaxResults(pageSize).list();
    }

    /**
     * 获取所有id,title,date
     */
    public List findAll1(){
        String hql="select new Activity (id,title,date) from Activity order by id";
        return getSession().createQuery(hql).list();
    }

    /**
     * 获取所有的id，标题，发布者，发布时间，状态
     */
    public List findAll2(){
        String hql="select new Activity (id,title,user.userName,date,state) from Activity order by id";
        return getSession().createQuery(hql).list();
    }

    /**
     * 获取所有的id，体系名称，标题，发布者，发布时间，状态(用于省体系办)
     */
    public List findAll3(){
        String hql="select new Activity (id,system.id,system.systemName,title,user.profile.name,date,state) from Activity order by id";
        return getSession().createQuery(hql).list();
    }

    /**
     * 获取状态为1（通过）的id，体系名称，标题，发布者，发布时间，状态(用于除体系办外所有人)
     */
    public List findAll4(){
        String hql="select new Activity (id,system.id,system.systemName,title,user.profile.name,date,state) from Activity where state=1 order by id";
        return getSession().createQuery(hql).list();
    }

    /**
     * 获取某体系的未通过,或待审核的重大活动（state等于0或2）(用于首席)
     */
    public List findAll5(int systemId){
        String hql="select new Activity (id,system.id,system.systemName,title,user.profile.name,date,state) from Activity where (state=0 or state=2)and system.id=:systemId order by id";
        return getSession().createQuery(hql).setParameter("systemId",systemId).list();
    }

    /**
     * 获取某体系通过的，和体系办通过的
     */
    public List findAll6(int systemId){
        String hql="select new Activity (id,system.id,system.systemName,title,user.profile.name,date,state) from Activity where (system.id=:systemId or system.id=1) and state=1 order by id ";
        return getSession().createQuery(hql).setParameter("systemId",systemId).list();
    }

    /**
     * 获取个数
     */
    public int getCount(){
        String hql="select count(*) from Activity ";
        Long temp=(long)getSession().createQuery(hql).uniqueResult();
        int count=temp.intValue();
        return count;
    }

    /**
     * 修改状态从0（待审核）到1（通过）或2（未通过）
     */
    public void updateState(int id,int state){
        String hql="update Activity set state=:state where id=:id";
        getSession().createQuery(hql).setParameter("state",state).setParameter("id",id).executeUpdate();
    }

}
