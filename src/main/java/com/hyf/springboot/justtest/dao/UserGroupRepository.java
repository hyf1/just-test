package com.hyf.springboot.justtest.dao;


import com.hyf.springboot.justtest.entity.UserGroup;
import com.hyf.springboot.justtest.entity.UserPrivilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupRepository extends JpaRepository<UserGroup,Integer> {

}

