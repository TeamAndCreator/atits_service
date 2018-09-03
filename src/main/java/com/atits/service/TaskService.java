package com.atits.service;

import com.atits.dao.TaskDao;
import com.atits.entity.Files;
import com.atits.entity.Task;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

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

    /**
     * 查找Task中的文件
     * @param id
     * @return
     */
    public Set<Files> getFiles(Integer id){return taskDao.getFiles(id);}

}
