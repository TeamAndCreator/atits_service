package com.atits.service;

import com.atits.dao.UserDao;
import com.atits.entity.Role;
import com.atits.entity.User;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
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

    public List findUsersBySystemId(int systemId){
        List<User> users;
        if (systemId==1){
            users=userDao.findUserAll();
            List<User> externals=userDao.findExternal();
            users.addAll(externals);
        }else {
            users=userDao.findUsersBySystemId(systemId);
        }
        for (User user:users){
            Set<Role> roles=new HashSet<>(userDao.findRoleById2(user.getId()));
            user.setRoles(roles);
        }
        return users;
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
            for (int i=ids.size()-1;i>=0;i--){
                users.remove(ids.get(i).intValue());
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

    public void changePassword(int userId,String password){
        userDao.changePassword(userId,password);
    }

    public int verifyPassword(int userId,String password){
        Object md5Password = new SimpleHash("MD5", password, null, 1);
        password=String.valueOf(md5Password);
        String password1=userDao.findPassword(userId);
        if (password.equals(password1)){
            return 1;
        }else{
            return 0;
        }
    }

    public void update(User user){userDao.update(user);}

    public void deleteById(Integer userId){userDao.deleteById(userId);}

    public void deleteByIds(List<Integer> idList){userDao.deleteByIds(idList);}

    public void updateState(int userId){
        userDao.updateState(userId);
    }

    public List findUserInSystem(int systemId){
        return userDao.findUserInSystem(systemId);
    }

    public String findPassWord(int userId){
        return userDao.findPassword(userId);
    }
}
