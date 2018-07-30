package com.atits.service;

import com.atits.dao.UserDao;
import com.atits.entity.Role;
import com.atits.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    public List<User> findTestPer(int sysId){
        List<User> users;
        if (sysId==1){
            users=userDao.findTestPer2();
        }else {
            users=userDao.findTestPer(sysId);
            List<User> tempUsers=userDao.findTestPer2();
            List<Integer> ids=new ArrayList<Integer>();
            for (int i=0;i<users.size();i++){
                for(User user1:tempUsers){
                    if (user1.getId()==users.get(i).getId()){
                        ids.add(i);
                    }
                }
            }
            for (int id:ids){
                users.remove(id);
            }
        }
        return users;
    }

    public List findExternal(){
        return userDao.findExternal();
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
