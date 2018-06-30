package com.atits.service;

import com.atits.dao.SubTaskDao;
import com.atits.entity.SubTask;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class SubTaskService {

    @Resource
    private SubTaskDao subTaskDao;

    public List<SubTask> findAll(){return subTaskDao.findAll();}
    public SubTask findById(Integer id){return subTaskDao.findById(id);}
    public List<SubTask> findByBearerId(Integer bearerId){return subTaskDao.findByBearerId(bearerId);}

    public void save(SubTask subTask){subTaskDao.save(subTask);}
    public void update(SubTask subTask){subTaskDao.update(subTask);}

    public void deleteById(Integer id){subTaskDao.deleteById(id);}
    public void deleteByIds(List<Integer> idList){subTaskDao.deleteByIds(idList);}


}
