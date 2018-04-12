package com.atits.service;

import com.atits.dao.LaboratoryDao;
import com.atits.entity.Laboratory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class LaboratoryService {

    @Resource
    private LaboratoryDao laboratoryDao;

    /**
     *添加一个laboratory
     */
    public void save(Laboratory laboratory){
        laboratoryDao.save(laboratory);
    }

    /**
     *查找所有laboratory
     */
    public List<Laboratory> findAll(){
        return laboratoryDao.findAll();
    }




}

