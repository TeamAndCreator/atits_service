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
     * 获取体系办所有规章制度
     */
    public List findAll3(){
        String hql="select new Regulation (id,system.id,system.systemName,title,user.profile.name,date,state) from Regulation where system.id=1 order by id";
        return getSession().createQuery(hql).list();
    }

    /**
     * 获取除体系办外，所有规章制度
     */
    public List findAll4(){
        String hql="select new Regulation (id,system.id,system.systemName,title,user.profile.name,date,state) from Regulation where system.id<>1 order by id";
        return getSession().createQuery(hql).list();
    }

    /**
     * 获取本体系的所有规章制度
     */
    public List findAll5(int systemId){
        String hql="select new Regulation (id,system.id,system.systemName,title,user.profile.name,date,state) from Regulation where system.id=:systemId order by id";
        return getSession().createQuery(hql).setParameter("systemId",systemId).list();
    }

    /**
     * 获取除本体系和体系办外，所有通过的规章制度
     */
    public List findAll6(int systemId){
        String hql="select new Regulation (id,system.id,system.systemName,title,user.profile.name,date,state) from Regulation where system.id<>:systemId and system.id<>1 and state=1 order by id ";
        return getSession().createQuery(hql).setParameter("systemId",systemId).list();
    }

    /**
     * 获取本体系所用通过的规章制度
     */
    public List findAll7(int systemId){
        String hql="select new Regulation (id,system.id,system.systemName,title,user.profile.name,date,state) from Regulation where system.id=:systemId and state=1 order by id ";
        return getSession().createQuery(hql).setParameter("systemId",systemId).list();
    }


    /**
     * 分页
     */
    public List findPage(int startRow,int pageSize){
        String hql="select new Regulation (id,title,date)from Regulation order by id";
        return getSession().createQuery(hql).setFirstResult(startRow).setMaxResults(pageSize).list();
    }

    /**
     * 获取所有id,title,date
     */
    public List findAll1(){
        String hql="select new Regulation (id,title,date) from Regulation order by id";
        return getSession().createQuery(hql).list();
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


    /**
     * 修改状态从0（待审核）到1（通过）或2（未通过）
     */
    public void updateState(int id,int state){
        String hql="update Regulation set state=:state where id=:id";
        getSession().createQuery(hql).setParameter("state",state).setParameter("id",id).executeUpdate();
    }

    /**
     * 修改
     */
    public void update1(int id,String title,String content){
        String hql="update Regulation set title=:title,content=:content,state=0 where id=:id";
        getSession().createQuery(hql).setParameter("title",title).setParameter("content",content).setParameter("id",id).executeUpdate();
    }
}
