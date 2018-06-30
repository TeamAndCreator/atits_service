package com.atits.dao;

import com.atits.entity.SubTask;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubTaskDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession(){return sessionFactory.getCurrentSession();}

    public List<SubTask> findAll(){
        String hql = "from SubTask";
        return getSession().createQuery(hql).list();
    }
    public SubTask findById(Integer id){
        String hql = "from SubTask where id=:id";
        return (SubTask) getSession().createQuery(hql).setParameter("id",id).uniqueResult();
    }
    //三级用户子任务显示
    public List<SubTask> findByBearerId(Integer bearerId){
        String hql = "from SubTask where bearer.id=:bearerId";
        return getSession().createQuery(hql).setParameter("bearerId",bearerId).list();
    }

    public void save(SubTask subTask){getSession().save(subTask);}
    public void update(SubTask subTask){getSession().update(subTask);}

    public void deleteById(Integer id){
        SubTask subTask = findById(id);
        getSession().delete(subTask);
    }
    public void deleteByIds(List<Integer> idList){
        for (int i=0;i<idList.size();i++){
            SubTask subTask = findById(idList.get(i));
            getSession().delete(subTask);
        }
    }
}
