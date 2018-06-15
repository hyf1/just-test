package com.hyf.springboot.justtest.service;

import com.hyf.springboot.justtest.dao.UserPrivilegeRepository;
import com.hyf.springboot.justtest.entity.UserPrivilege;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.annotation.Resource;

@RestController
@RequestMapping(path="privilege")
public class UserPrivilegeService {
    @Resource
    UserPrivilegeRepository userPrivilegeRepository;

    @RequestMapping(path = "",method = RequestMethod.GET)
    public  List<UserPrivilege> getUserPrivilegeList(){
        return userPrivilegeRepository.findAll();
    }


    @RequestMapping(path = "", method = RequestMethod.POST)
    public UserPrivilege register(@RequestBody UserPrivilege userPrivilege) {
        userPrivilegeRepository.saveAndFlush(userPrivilege);
        return userPrivilege;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public UserPrivilege getUserById(@PathVariable("id") Integer id) {
        return userPrivilegeRepository.findById(id).get();
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteUserPrivilgeById(@PathVariable("id") Integer id) {
        userPrivilegeRepository.deleteById(id);
    }


}

