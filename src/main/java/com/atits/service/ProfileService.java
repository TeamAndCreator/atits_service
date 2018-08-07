package com.atits.service;

import com.atits.dao.ProfileDao;
import com.atits.entity.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional
@Service
public class ProfileService {

    @Resource
    private ProfileDao profileDao;


    /**
     * 根据id获取一个profile
     */
    public Profile findById(int id){
        return profileDao.findById(id);
    }

    /**
     * 更新一个profile
     */
    public void update(Profile profile){
        profileDao.update(profile);
    }
}
