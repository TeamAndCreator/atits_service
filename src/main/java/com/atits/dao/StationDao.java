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
        String hql="from Station ";
        return getSession().createQuery(hql).list();
    }

    /**
     * 根据id查找一个station
     * @param id
     * @return
     */
    public Station findById(Integer id){
        String hql="from Station where id=:id";
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
}
