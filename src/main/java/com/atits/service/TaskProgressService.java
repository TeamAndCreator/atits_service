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

    public void deleteById(Integer id){taskProgressDao.deleteById(id);}
    public void deleteByIds(List<Integer> idList){taskProgressDao.deleteByIds(idList);}

}
