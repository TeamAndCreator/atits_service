package com.atits.service;

import com.atits.dao.TaskProgressDao;
import com.atits.entity.TaskProgress;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class TaskProgressService {

    @Resource
    private TaskProgressDao taskProgressDao;

    public List<TaskProgress> findAll(){return taskProgressDao.findAll();}
    public TaskProgress findById(Integer id){return taskProgressDao.findById(id);}
    public List<TaskProgress> findByBearerId(Integer bearerId){return taskProgressDao.findByBearerId(bearerId);}

    public void save(TaskProgress taskProgress ){taskProgressDao.save(taskProgress);}
    public void update(TaskProgress taskProgress){taskProgressDao.update(taskProgress);}

    public void delete(TaskProgress taskProgress){taskProgressDao.delete(taskProgress);}
    public void deleteById(Integer id){taskProgressDao.deleteById(id);}
    public void deleteByIds(List<Integer> idList){taskProgressDao.deleteByIds(idList);}

    /**
     * 获取自己的工作进展
     */
    public List findAll1(int userId){
        return taskProgressDao.findAll1(userId);
    }

    /**
     * 获取本体系的工作进展
     */
    public List findAll2(int systemId){
        return taskProgressDao.findAll2(systemId);
    }

    /**
     * 修改状态从0（待审核）到1（通过）或2（未通过）
     */
    public void updateState(int id,int state){
        taskProgressDao.updateState(id,state);
    }

    /**
     * 根据subTaskId查询任务进展
     */
    public List findBySubTaskId(int subTaskId){
        return taskProgressDao.findBySubTaskId(subTaskId);
    }
}
