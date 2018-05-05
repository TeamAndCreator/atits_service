package com.atits.service;

import com.atits.dao.UserDao;
import com.atits.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class UserService {

    @Resource
    private UserDao userDao;

    /**
     *查找所有user
     */
    public List<User> findAll() {
        return userDao.findAll();
    }

    public List<User> findBySysId(int sysId){return userDao.findBySysId(sysId);}

    public User findByUserName(String username){
        return userDao.findByUserName(username);
    }
    /**
     *添加一个user
     */
    public void save(User user){
        userDao.save(user);
    }


}
