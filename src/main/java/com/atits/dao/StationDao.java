package com.atits.dao;

import com.atits.entity.Station;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author zys
 */
@Repository
public class StationDao {

    @Autowired
    SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    /**
     * 添加一个station
     * @param station
     */
    public void save(Station station){
        getSession().save(station);
    }

    /**
     * 根据id删除一个station
     * @param id
     */
    public void deleteById(Integer id){
        Station station=findById(id);
        getSession().delete(station);
    }

    /**
     * 根据id数组批量删除station
     * @param idList
     */
    public void deleteByIds(List<Integer> idList){
        getSession().createQuery("delete from Station where id in:idList").setParameterList("idList",idList).executeUpdate();
    }

    /**
     * 更新一个station
     * @param station
     */
    public void update(Station station){
        getSession().update(station);
    }

    /**
     *查找所有station
     */
    public List<Station> findAll(){
        String hql="select new Station (id,staName,system.systemName,state)from Station ";
        return getSession().createQuery(hql).list();
    }

    /**
     * 根据id查找一个station
     * @param id
     * @return
     */
    public Station findById(Integer id){
        String hql="select new Station (id,staName,content,company,system.systemName,time,date,state)from Station where id=:id";
        return (Station) getSession().createQuery(hql).setParameter("id",id).uniqueResult();
    }

    /**
     * 分页
     */
    public List findPage(int startRow,int pageSize){
        String hql="select new Station (id,staName)from Station order by id";
        return getSession().createQuery(hql).setFirstResult(startRow).setMaxResults(pageSize).list();
    }

    /**
     * 获取某个体系的所有实验站id,name
     * @return
     */
    public List findAllInSystem(int systemId){
        String hql="select new Station (id,staName)from Station where system.id=:systemId";
        return getSession().createQuery(hql).setParameter("systemId",systemId).list();
    }

    /**
     * 获取某个体系中的所有sta(id,staName,company,system.systemName,time,date,state)
     */
    public List findAllInSystem2(int systemId){
        String hql="select new Station (id,staName,company,system.systemName,time,date,state) from Station where system.id=:systemId";
        return getSession().createQuery(hql).setParameter("systemId",systemId).list();
    }

    /**
     * 获取所有sta(id,staName,company,system.systemName,time,date,state)
     */
    public List findAll2(){
        String hql="select new Station (id,staName,company,system.systemName,time,date,state) from Station ";
        return getSession().createQuery(hql).list();
    }

    /**
     * 获取某个实验站具有某个权限的所有user
     */
    public List findUserInRole(int stationId,int roleId){
        String hql="SELECT p.name from User u join u.roles r,Profile p where u.station.id=:stationId and r.id=:roleId and u.profile=p ";
        return getSession().createQuery(hql).setParameter("stationId",stationId).setParameter("roleId",roleId).list();
    }

    /**
     * 获取个数
     */
    public int getCount(){
        String hql="select count(*) from Station ";
        Long temp=(long)getSession().createQuery(hql).uniqueResult();
        int count=temp.intValue();
        return count;
    }

    /**
     * 删除某个体系中的所有实验站
     */
    public void deleteBySystemId(int systemId){
        String hql="delete from Station where system.id=:systemId";
        getSession().createQuery(hql).setParameter("systemId",systemId).executeUpdate();
    }

    /**
     * 改变状态（激活）
     */
    public void updateState(int staId){
        String hql="update Station set state=1 where id=:staId";
        getSession().createQuery(hql).setParameter("staId",staId).executeUpdate();
    }

    /**
     * 将某个研究室内所有人员的sta.id设为0（用于删除sta）
     */
    public void deleteStaId(int staId){
        String hql="update User set station =null where station.id=:staId";
        getSession().createQuery(hql).setParameter("staId",staId).executeUpdate();
    }

    /**
     * 获取某个实验站具有某个权限的所有user
     */
    public List findUserInRole2(int stationId,int roleId){
        String hql="SELECT new User (u.id,p.name) from User u join u.roles r,Profile p where u.station.id=:stationId and r.id=:roleId and u.profile=p ";
        return getSession().createQuery(hql).setParameter("stationId",stationId).setParameter("roleId",roleId).list();
    }
}
