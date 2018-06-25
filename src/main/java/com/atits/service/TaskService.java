package com.atits.service;

import com.atits.dao.TaskDao;
import com.atits.entity.Task;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class TaskService {

    @Resource
    private TaskDao taskDao;

    public List<Task> findAll(){return taskDao.findAll();}

    public Task findById(Integer id){return taskDao.findById(id);}

    public List<Task> findBySysId(Integer sysId){return taskDao.findBySysId(sysId);}

    public void save(Task task){taskDao.save(task);}

    public void update(Task task){taskDao.update(task);}

    public void deleteById(Integer id){taskDao.deleteById(id);}

    public void deleteByIds(List<Integer> idList){taskDao.deleteByIds(idList);}


}
