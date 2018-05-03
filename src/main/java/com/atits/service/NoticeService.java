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

}
