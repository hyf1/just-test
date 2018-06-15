package com.hyf.springboot.justtest.service;

import com.hyf.springboot.justtest.dao.UserRepository;
import com.hyf.springboot.justtest.entity.User;
import com.hyf.springboot.justtest.entity.UserGroup;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "user")
public class UserService {
    @Resource
    UserRepository userRepository;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<User> getUserList() {
        return userRepository.findUserByDeleted(false);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public User register(@RequestBody User user) {
        userRepository.saveAndFlush(user);
        return user;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") Integer id) {
        return userRepository.findById(id).get();


    }

    @RequestMapping(path = "byId/{id}", method = RequestMethod.DELETE)
    public void deleteUserById(@PathVariable("id") Integer id) {
        User user = userRepository.findById(id).get();
        user.setDeleted(true);
        userRepository.save(user);

    }



    @RequestMapping(path = "{id}/group", method = RequestMethod.GET)
    public Set<UserGroup> getUserGroupById(@PathVariable("id") Integer id) {
        User user = userRepository.findById(id).get();
       return  user.getGroups();

    }

    @RequestMapping(path = "/{username}", method = RequestMethod.DELETE)
    public void deleteUserByName(@PathVariable("username") String username) {
        userRepository.deleteUserByUsername(username);
    }


}
