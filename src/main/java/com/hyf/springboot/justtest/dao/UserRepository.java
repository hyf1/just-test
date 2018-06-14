package com.hyf.springboot.justtest.dao;

import com.hyf.springboot.justtest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserByUsernameAndDeleted(String username,boolean deleted);

    List<User> findUserByDeleted(boolean deleted);
    @Transactional
    void deleteUserByUsername(String username);

}
