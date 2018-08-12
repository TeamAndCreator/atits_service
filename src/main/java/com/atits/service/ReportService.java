package com.atits.service;

import com.atits.dao.ReportDao;
import com.atits.entity.Files;
import com.atits.entity.Report;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

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

    /**
     * 查找Report中的文件
     * @param id
     * @return
     */
    public Set<Files> getFiles(Integer id){return reportDao.getFiles(id);}


    /**
     * 获取所有的id，体系名称，标题，发布者，发布时间，状态(用于省体系办)
     * @return
     */
    public List findForTXB(){
        return reportDao.findAll3();
    }

    /**
     * 获取所有通过的、本体系未通过或未审核的report（用于首席）
     * @param systemId
     * @return
     */
    public List findForSX(int systemId){
        List<Report> reports=reportDao.findAll4();//获取所有通过的report
        List<Report> reports1=reportDao.findAll5(systemId);//获取本体系未通过或未审核的report
        reports1.addAll(reports);//合并
        return reports1;
    }

    /**
     * 获取所有通过的report（用于除体系办和首席外的人）
     * @return
     */
    public List findFor(int systemId){
        List<Report> reports=reportDao.findAll6(systemId);
        return reports;
    }

    /**
     * 分页
     */
    public List findPage(int startRow,int pageSize){
        return reportDao.findPage(startRow,pageSize);
    }

    /**
     * 获取所有id，title，date
     * @return
     */
    public List findAll1(){
        return reportDao.findAll1();
    }

    /**
     * 获取个数
     */
    public int getCount(){
        return reportDao.getCount();
    }

    /**
     * 修改状态从0（待审核）到1（通过）或2（未通过）
     */
    public void updateState(int id,int state){
        reportDao.updateState(id,state);
    }
}
