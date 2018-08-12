package com.atits.service;

import com.atits.dao.NoticeDao;
import com.atits.entity.Files;
import com.atits.entity.Notice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class NoticeService {
    @Resource
    private NoticeDao noticeDao;

    /**
     * 添加一个Notice
     * @param notice
     */
    public void save(Notice notice){
        noticeDao.save(notice);
    }


    /**
     * 通过id删除一个Notice
     * @param id
     */
    public void deleteById(Integer id){
        noticeDao.deleteById(id);
    }

    /**
     * 根据id数组批量删除Notice
     * @param idList
     */
    public void deleteByIds(List<Integer> idList) {
        for (Integer id : idList) {
            noticeDao.deleteById(id);
        }
    }

    /**
     * 更新一个Notice
     * @param notice
     */
    public void update(Notice notice){
        noticeDao.update(notice);
    }


    /**
     * 根据id查找一个Notice
     * @param id
     */
    public Notice findById(Integer id){
        return noticeDao.findById(id);
    }

    /**
     *查找所有Notice
     */
    public List<Notice> findAll(){
        return noticeDao.findAll();
    }

    /**
     * 查找Notice中的文件
     * @param id
     * @return
     */
    public Set<Files> getFiles(Integer id){return noticeDao.getFiles(id);}

    /**
     * 获取所有的id，体系名称，标题，发布者，发布时间，状态(用于省体系办)
     * @return
     */
    public List findForTXB(){
        return noticeDao.findAll3();
    }

    /**
     * 获取所有通过的、本体系未通过或未审核的harvest（用于首席）
     * @param systemId
     * @return
     */
    public List findForSX(int systemId){
        List<Notice> notices=noticeDao.findAll4();//获取所有通过的harvest
        List<Notice> notices1=noticeDao.findAll5(systemId);//获取本体系未通过或未审核的harvest
        notices1.addAll(notices);//合并
        return notices1;
    }

    /**
     * 获取本体系和体系办所有通过的notices（用于除体系办和首席外的人）
     * @return
     */
    public List findFor(int systemId){
        List<Notice> notices=noticeDao.findAll6(systemId);
        return notices;
    }

    /**
     * 分页
     */
    public List findPage(int startRow,int pageSize){
        return noticeDao.findPage(startRow,pageSize);
    }

    /**
     * 获取所有id，title，date
     * @return
     */
    public List findAll1(){
        return noticeDao.findAll1();
    }

    /**
     * 获取个数
     */
    public int getCount(){
        return noticeDao.getCount();
    }

    /**
     * 修改状态从0（待审核）到1（通过）或2（未通过）
     */
    public void updateState(int id,int state){
        noticeDao.updateState(id,state);
    }
}
