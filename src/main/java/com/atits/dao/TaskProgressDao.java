package com.atits.dao;

import com.atits.entity.TaskProgress;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class TaskProgressDao {

    @Autowired
    SessionFactory sessionFactory;

    private Session getSession(){return sessionFactory.getCurrentSession();}


    public List<TaskProgress> findAll(){
        String hql = "from TaskProgress ";
        return getSession().createQuery(hql).list();
    }

    public TaskProgress findById(Integer id){
        String hql = "from TaskProgress where id=:id";
        return (TaskProgress) getSession().createQuery(hql).setParameter("id",id).uniqueResult();
    }

    //三级用户子任务显示
    public List<TaskProgress> findByBearerId(Integer bearerId){
        String hql = "from TaskProgress where subTask.bearer.id=:bearerId";
        return getSession().createQuery(hql).setParameter("bearerId",bearerId).list();
    }
    public void save(TaskProgress taskProgress){
        getSession().save(taskProgress);
    }

    public void update(TaskProgress taskProgress){
        getSession().update(taskProgress);
    }

    public void deleteById(Integer id){
        TaskProgress taskProgress = findById(id);
        getSession().delete(taskProgress);
    }

    public void deleteByIds(List<Integer> idList){
        for (int i=0; i<idList.size(); i++){
            TaskProgress taskProgress = findById(idList.get(i));
            getSession().delete(taskProgress);
        }
    }



}
