package com.atits.Repository;

import com.atits.entity.Role;
import com.atits.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserName(String username);

    String findUserNameById(Integer id);

    List<Role> findRoleByUserName(String username);

    List<Role> findRoleById(String username);
}
