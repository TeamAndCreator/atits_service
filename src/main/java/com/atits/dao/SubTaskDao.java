package com.atits.dao;

import com.atits.entity.Files;
import com.atits.entity.SubTask;
import com.atits.entity.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class SubTaskDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession(){return sessionFactory.getCurrentSession();}

    public List<SubTask> findAll(){
        String hql = "from SubTask";
        return getSession().createQuery(hql).list();
    }

    /**
     * 获取所有本体系(用过父任务的systemId筛选)的子任务(用于首席)
     * @return
     */
    public List findBySystemId(int systemId){
        String hql="select new SubTask (id,bearer.id,bearer.profile.name,title,date,startTime,endTime,fatherTask.id,fatherTask.title)from SubTask where fatherTask.system.id=:systemId";
        return getSession().createQuery(hql).setParameter("systemId",systemId).list();
    }

    /**
     * 获取此人所有的子任务
     * @return
     */
    public List findByBearerId(int bearerId){
        String hql="select new SubTask (id,bearer.id,bearer.profile.name,title,date,startTime,endTime,fatherTask.id,fatherTask.title)from SubTask where bearer.id=:bearerId";
        return getSession().createQuery(hql).setParameter("bearerId",bearerId).list();
    }

    public SubTask findById(Integer id){
        String hql = "from SubTask where id=:id";
        return (SubTask) getSession().createQuery(hql).setParameter("id",id).uniqueResult();
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

    /**
     * 根据task.id获取subtask的idlist
     */
    public List findIdList(int taskId){
        String hql="select id from SubTask where fatherTask.id=:taskId";
        return getSession().createQuery(hql).setParameter("taskId",taskId).list();
    }

    /**
     * 获取文件list，用于更新
     */
    public Set<Files> getFiles(int id){
        String hql="select st.files from SubTask as st where st.id=:id";
        List list= getSession().createQuery(hql).setParameter("id",id).list();
        Set<Files> filesSet=new HashSet(list);
        return filesSet;
    }


}
