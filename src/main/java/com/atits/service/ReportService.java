package com.atits.service;

import com.atits.dao.ReportDao;
import com.atits.entity.Report;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class ReportService {
    @Resource
    private ReportDao reportDao;

    /**
     * 添加一个Report
     * @param report
     */
    public void save(Report report){
        reportDao.save(report);
    }


    /**
     * 通过id删除一个Report
     * @param id
     */
    public void deleteById(Integer id){
        reportDao.deleteById(id);
    }

    /**
     * 根据id数组批量删除Report
     * @param idList
     */
    public void deleteByIds(List<Integer> idList) {
        for (Integer id : idList) {
            reportDao.deleteById(id);
        }
    }

    /**
     * 更新一个Report
     * @param report
     */
    public void update(Report report){
        reportDao.update(report);
    }


    /**
     * 根据id查找一个Report
     * @param id
     */
    public Report findById(Integer id){
        return reportDao.findById(id);
    }

    /**
     *查找所有Report
     */
    public List<Report> findAll(){
        return reportDao.findAll();
    }
}
