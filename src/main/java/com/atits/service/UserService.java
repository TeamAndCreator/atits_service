package com.atits.service;

import com.atits.Repository.UserRepository;
import com.atits.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     *查找所有user
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User fingById(long id){
        return userRepository.getOne(id);
    }


}
