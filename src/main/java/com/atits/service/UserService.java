package com.atits.service;

import com.atits.Repository.UserRepository;
import com.atits.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserService {

    private UserRepository userRepository;

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

    public User findById(Integer id){

        Optional<User> userOptional = userRepository.findById(id);
        User user=new User();
        if (userOptional.isPresent()) {
            user= userOptional.get();
        } else {
            // handle not found, return null or throw
            System.out.println("no exit!");
        }
        return user;
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);

    }


    public String findUserNameById(Integer id){
        return userRepository.findUserNameById(id);
    }

    public void save(User user){
        userRepository.save(user);
    }


}
