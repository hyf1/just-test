package com.hyf.springboot.justtest.dao;


import com.hyf.springboot.justtest.entity.UserPrivilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPrivilegeRepository extends JpaRepository<UserPrivilege,Integer> {
}
