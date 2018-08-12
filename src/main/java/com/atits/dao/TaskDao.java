package com.atits.dao;

import com.atits.entity.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDao {
    @Autowired
    SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public List<Task> findAll(){
        String hql = "select new Task (id,user.id,user.profile.name,system.id,system.systemName,title,date,stratTime,endTime)from Task ";
        return getSession().createQuery(hql).list();
    }

    public Task findById(Integer id){
        String hql = "from Task where id=:id";
        return (Task) getSession().createQuery(hql).setParameter("id",id).uniqueResult();
    }

    public List<Task> findBySysId(Integer sysId){
        String hql = "select new Task (id,user.id,user.profile.name,system.id,system.systemName,title,date,stratTime,endTime)from Task where system.id =:sysId";
        return getSession().createQuery(hql).setParameter("sysId",sysId).list();
    }

    public void save(Task task){
               getSession().save(task);
    }

    public void update(Task task){
        getSession().update(task);
    }

    public void deleteById(Integer id){
        Task task = findById(id);
        getSession().delete(task);
    }

    public void deleteByIds(List<Integer> idList){
        for (int i=0; i<idList.size(); i++){
                  Task task = findById(idList.get(i));
                  getSession().delete(task);
        }
    }


}
