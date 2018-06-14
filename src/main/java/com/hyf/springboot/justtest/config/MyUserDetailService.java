package com.hyf.springboot.justtest.config;

import com.hyf.springboot.justtest.dao.UserRepository;
import com.hyf.springboot.justtest.entity.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class MyUserDetailService implements UserDetailsService {
    @Resource
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User userInDb=userRepository.findUserByUsernameAndDeleted(s,false);

        if(userInDb==null)
            throw new UsernameNotFoundException("user dose not exists");

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return org.springframework.security.core.userdetails.User
                .withUsername(userInDb.getUsername())
                .password(  userInDb.getPassword())
                .authorities(authorities)
                .build();
    }
}
