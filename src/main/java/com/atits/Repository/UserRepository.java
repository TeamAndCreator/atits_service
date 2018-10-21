package com.atits.Repository;

import com.atits.entity.Role;
import com.atits.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("from User where userName=:username")
    User findByUserName(String username);

    @Query("select u.roles from User as u where u.userName=:username")
    List<Role> findRoleById(String username);



}
