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

    public List<User> findAll() {

        return userDao.findAll();
    }


}
