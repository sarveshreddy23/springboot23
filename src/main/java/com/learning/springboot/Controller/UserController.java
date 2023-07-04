package com.learning.springboot.Controller;

import com.learning.springboot.model.UserModel;
import com.learning.springboot.repository.UserRepository;
import com.learning.springboot.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager manager;
    @PostMapping("/authenticate")
    public String authenticate(@RequestBody UserModel userModel){
        System.out.println("insie authenticate metho");
        Authentication auth= manager.authenticate(new UsernamePasswordAuthenticationToken(userModel.getUserName(),userModel.getPassword()));
        System.out.println("Verifying auth");
        if(auth.isAuthenticated()){
            System.out.println("auth successfull");
            UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(userModel.getUserName())
                    .password(userModel.getPassword()).build();
             return jwtUtil.generateToken(userDetails);
        }
        throw new BadCredentialsException("unknownUser");

    }



}
