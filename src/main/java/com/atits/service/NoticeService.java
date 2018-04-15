package com.atits.service;

import com.atits.dao.NoticeDao;
import com.atits.entity.Notice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zys
 */
@Transactional
@Service
public class NoticeService {

    @Resource
    private NoticeDao noticeDao;

    /**
     * 增加一个通知公告
     */
    public void save(Notice notice){
        noticeDao.save(notice);
    }

    /**
     * 删除一个通知公告
     */
    public void delet(Notice notice){
        noticeDao.delet(notice);
    }

    /**
     * 查找所有通知公告
     */
    public List<Notice> findAll(){
        return noticeDao.findAll();
    }

}
