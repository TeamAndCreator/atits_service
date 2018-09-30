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
     *查找所有Laboratory的id,name,systemName,state
     */
    public List<Laboratory> findAll(){
        String hql="SELECT new Laboratory (id,labName,system.systemName,state)from Laboratory ";
        return getSession().createQuery(hql).list();
    }

    /**
     * 根据id查找一个Laboratory,system只包括systemName
     * @param id
     * @return
     */
    public Laboratory findById(Integer id){
        String hql="select new Laboratory (id,labName,content,company,system.systemName,time,date,state)from Laboratory  where id=:id";
        return (Laboratory) getSession().createQuery(hql).setParameter("id",id).uniqueResult();
    }

    /**
     * 获取某个体系中的所有lab(id,labName,company,system.systemName,time,date,state)
     */
    public List findAllInSystem2(int systemId){
        String hql="select new Laboratory(id,labName,company,system.systemName,time,date,state) from Laboratory where system.id=:systemId";
        return getSession().createQuery(hql).setParameter("systemId",systemId).list();
    }

    /**
     * 获取所有lab(id,labName,company,system.systemName,time,date,state)
     */
    public List findAll2(){
        String hql="select new Laboratory(id,labName,company,system.systemName,time,date,state) from Laboratory";
        return getSession().createQuery(hql).list();
    }


    /**
     * 分页
     */
    public List findPage(int startRow,int pageSize){
        String hql="select new Laboratory (id,labName)from Laboratory order by id";
        return getSession().createQuery(hql).setFirstResult(startRow).setMaxResults(pageSize).list();
    }

    /**
     * 获取某个研究室具有某个权限的所有user
     */
    public List findUserInRole(int laboratoryId,int roleId){
        String hql="SELECT p.name from User u join u.roles r,Profile p where u.laboratory.id=:laboratoryId and r.id=:roleId and u.profile=p ";
        return getSession().createQuery(hql).setParameter("laboratoryId",laboratoryId).setParameter("roleId",roleId).list();
    }

    /**
     * 获取某个体系的所有研究室id,name
     * @return
     */
    public List findAllInSystem(int systemId){
        String hql="select new Laboratory (id,labName)from Laboratory where system.id=:systemId";
        return getSession().createQuery(hql).setParameter("systemId",systemId).list();
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

    /**
     * 删除某个体系中所有的研究室
     */
    public void deleteBySystemId(int systemId){
        String hql="delete from Laboratory where system.id=:systemId";
        getSession().createQuery(hql).setParameter("systemId",systemId).executeUpdate();
    }

    /**
     * 改变状态（激活）
     */
    public void updateState(int labId){
        String hql="update Laboratory set state=1 where id=:labId";
        getSession().createQuery(hql).setParameter("labId",labId).executeUpdate();
    }

    /**
     * 将某个研究室内所有人员的lab.id设为0（用于删除lab）
     */
    public void deleteLabId(int labId){
        String hql="update User set laboratory=null where laboratory.id=:labId";
        getSession().createQuery(hql).setParameter("labId",labId).executeUpdate();
    }

    /**
     * 获取某个研究室具有某个权限的所有user
     */
    public List findUserInRole2(int laboratoryId,int roleId){
        String hql="SELECT new User (u.id,p.name) from User u join u.roles r,Profile p where u.laboratory.id=:laboratoryId and r.id=:roleId and u.profile=p ";
        return getSession().createQuery(hql).setParameter("laboratoryId",laboratoryId).setParameter("roleId",roleId).list();
    }
}
