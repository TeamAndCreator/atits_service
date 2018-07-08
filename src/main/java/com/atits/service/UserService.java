package com.atits.service;

import com.atits.dao.UserDao;
import com.atits.entity.Role;
import com.atits.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

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

    public User findById(Integer userId){return userDao.findById(userId);}

    public User findByUserName(String username){
        return userDao.findByUserName(username);
    }

    public List<User> findTestPer(int sysId,int roleId){
        return userDao.findTestPer(sysId,roleId);
    }

    public List findRoleById(String userName){
        return userDao.findRoleById(userName);
    }
    /**
     *添加一个user
     */
    public void save(User user){
        userDao.save(user);
    }

    public void update(User user){userDao.update(user);}

    public void deleteById(Integer userId){userDao.deleteById(userId);}

    public void deleteByIds(List<Integer> idList){userDao.deleteByIds(idList);}



}
