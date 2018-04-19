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
    public void deletById(Integer id){
        Station station=findById(id);
        getSession().delete(station);
    }

    /**
     * 根据id数组批量删除station
     * @param idList
     */
    public void deletByIds(List<Integer> idList){
        getSession().createQuery("delete from t_station where id in:idList").setParameterList("idList",idList).executeUpdate();
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
        String hql="from t_station";
        return getSession().createQuery(hql).list();
    }

    /**
     * 根据id查找一个station
     * @param id
     * @return
     */
    public Station findById(Integer id){
        String hql="from t_station where id=:id";
        return (Station) getSession().createQuery(hql).setParameter("id",id).uniqueResult();
    }


}
