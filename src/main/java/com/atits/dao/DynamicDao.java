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
     * 根据Dynamic的id查找其files
     * 用于更新文件
     */
    public Set<Files> getFiles(Integer id){
        String hql="select a.files from Dynamic as a where a.id=:id";
        List list= getSession().createQuery(hql).setParameter("id",id).list();
        Set<Files> filesSet=new HashSet(list);
        return filesSet;
    }
    /**
     * 获取所有通过的和展示的（用于体系办）
     */
    public List findAll2(){
        String hql="select new Dynamic (id,system.id,system.systemName,title,user.id,user.profile.name,date,state)from Dynamic where state>1";
        return getSession().createQuery(hql).list();
    }

    /**
     * 获取本体系未审核，未通过，通过的（用于首席）
     */
    public List findAll3(int systemId){
        String hql="select new Dynamic (id,system.id,system.systemName,title,user.id,user.profile.name,date,state) from Dynamic where system.id=:systemId and state<3";
        return getSession().createQuery(hql).setParameter("systemId",systemId).list();
    }

    /**
     * 获取所有展示的（用于除体系办所有人）
     */
    public List findAll4(){
        String hql="select new Dynamic (id,system.id,system.systemName,title,user.id,user.profile.name,date,state) from Dynamic where state=3";
        return getSession().createQuery(hql).list();
    }

    /**
     * 获取本体系通过的
     */
    public List findAll5(int systemId){
        String hql="select new Dynamic (id,system.id,system.systemName,title,user.id,user.profile.name,date,state) from Dynamic where system.id=:systemId and state=2";
        return getSession().createQuery(hql).setParameter("systemId",systemId).list();
    }

    /**
     * 获取本人未审核或未通过的
     */
    public List findAll6(int userId){
        String hql="select new Dynamic (id,system.id,system.systemName,title,user.id,user.profile.name,date,state) from Dynamic where user.id=:userId and (state=0 or state=1)";
        return getSession().createQuery(hql).setParameter("userId",userId).list();
    }

    /**
     * 分页
     */
    public List findPage(int startRow,int pageSize){
        String hql="select new Dynamic(id,title,date) from Dynamic order by id";
        return getSession().createQuery(hql).setFirstResult(startRow).setMaxResults(pageSize).list();
    }

    /**
     * 获取所有id,title,date
     */
    public List findAll1(){
        String hql="select new Dynamic(id,title,date) from Dynamic order by id";
        return getSession().createQuery(hql).list();
    }

    /**
     * 获取个数
     */
    public int getCount(){
        String hql="select count(*) from Dynamic ";
        Long temp=(long)getSession().createQuery(hql).uniqueResult();
        int count=temp.intValue();
        return count;
    }

    /**
     * 修改状态
     */
    public void updateState(int id,int state){
        String hql="update Dynamic set state=:state where id=:id";
        getSession().createQuery(hql).setParameter("state",state).setParameter("id",id).executeUpdate();
    }

    /**
     * 修改
     */
    public void update1(int id,String title,String content){
        String hql="update Dynamic set title=:title,content=:content,state=0 where id=:id";
        getSession().createQuery(hql).setParameter("title",title).setParameter("content",content).setParameter("id",id).executeUpdate();
    }

    /**
     * 体系办修改
     */
    public void updateForaAdmin(int id,String title,String content){
        String hql="update Dynamic set title=:title,content=:content,state=2 where id=:id";
        getSession().createQuery(hql).setParameter("title",title).setParameter("content",content).setParameter("id",id).executeUpdate();
    }
}
