package com.learning.springboot.service;

import com.learning.springboot.entity.User;
import com.learning.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.Objects;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("CustomUseretailsService : loadUserByUsername : username: "+username);
        User user = userRepository.findByUsername(username);

       if(!Objects.nonNull(user))
           throw new UsernameNotFoundException("UserNameNotfoun");
        return new CustomUserDetails(user);
    }
}
