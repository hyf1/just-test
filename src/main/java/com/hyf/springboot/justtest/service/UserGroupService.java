package com.hyf.springboot.justtest.service;

import com.hyf.springboot.justtest.dao.UserGroupRepository;
import com.hyf.springboot.justtest.entity.UserGroup;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.annotation.Resource;

@RestController
@RequestMapping(path ="group")
public class UserGroupService {
    @Resource
    UserGroupRepository userGroupRepository;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<UserGroup> getUserGroupList() {
        return userGroupRepository.findAll();
    }


    @RequestMapping(path = "", method = RequestMethod.POST)
    public UserGroup register(@RequestBody UserGroup userGroup) {
        userGroupRepository.saveAndFlush(userGroup);
        return userGroup;
    }

    @RequestMapping(path = "/{id}/groups", method = RequestMethod.GET)
    public UserGroup getUserById(@PathVariable("id") Integer id) {
        return userGroupRepository.findById(id).get();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteUserGroupById(@PathVariable("id") Integer id) {
        userGroupRepository.deleteById(id);
    }



}

