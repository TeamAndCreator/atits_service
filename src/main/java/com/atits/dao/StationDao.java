package com.atits.dao;

import com.atits.entity.Station;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
     *查找所有station
     */
    public List<Station> findAll(){
        String hql="from t_station";
        return getSession().createQuery(hql).list();
    }


}
