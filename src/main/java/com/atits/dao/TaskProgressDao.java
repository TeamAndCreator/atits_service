package com.atits.dao;

import com.atits.entity.Files;
import com.atits.entity.TaskProgress;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void delete(TaskProgress taskProgress){
        getSession().delete(taskProgress);
    }

    public void deleteByIds(List<Integer> idList){
        for (int i=0; i<idList.size(); i++){
            TaskProgress taskProgress = findById(idList.get(i));
            getSession().delete(taskProgress);
        }
    }

    /**
     * 获取自己的工作进展
     */
    public List findAll1(int userId){
        String hql="select new TaskProgress (id,subTask.id,subTask.title,subTask.bearer.id,subTask.bearer.profile.name,title,content,date,state)from TaskProgress where subTask.bearer.id=:id";
        return getSession().createQuery(hql).setParameter("id",userId).list();
    }

    /**
     * 获取本体系的工作进展
     */
    public List findAll2(int systemId){
        String hql="select new TaskProgress (id,subTask.id,subTask.title,subTask.bearer.id,subTask.bearer.profile.name,title,content,date,state)from TaskProgress where subTask.bearer.system.id=:id";
        return getSession().createQuery(hql).setParameter("id",systemId).list();
    }

    /**
     * 修改状态从0（待审核）到1（通过）或2（未通过）
     */
    public void updateState(int id,int state){
        String hql="update TaskProgress set state=:state where id=:id";
        getSession().createQuery(hql).setParameter("state",state).setParameter("id",id).executeUpdate();
    }

    /**
     * 根据subTaskId查询任务进展
     */
    public List findBySubTaskId(int subTaskId){
        String hql="from TaskProgress where subTask.id=:id";
        return getSession().createQuery(hql).setParameter("id",subTaskId).list();
    }

    /**
     * 根据TaskProgress的id查找其files
     * 用于更新文件
     */
    public Set<Files> getFiles(Integer id){
        String hql="select tg.files from TaskProgress as tg where tg.id=:id";
        List list= getSession().createQuery(hql).setParameter("id",id).list();
        Set<Files> filesSet=new HashSet(list);
        return filesSet;
    }


}
