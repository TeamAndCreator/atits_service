package com.atits.service;


import com.atits.dao.TestWeightDao;
import com.atits.entity.TestWeight;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class TestWeightService {

    @Resource
    private TestWeightDao testWeightDao;



    public void save(TestWeight testWeight) {
        testWeightDao.save(testWeight);
    }
    public void update(TestWeight testWeight){testWeightDao.update(testWeight);}


    public void deletes(List<Integer> idList) {
        testWeightDao.deletes(idList);
    }
    public void delete(Integer id){testWeightDao.delete(id);}

    public List<TestWeight> findAll(){return testWeightDao.findAll();}

    public TestWeight findById(Integer id){return testWeightDao.findById(id);}

}
