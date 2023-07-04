package com.learning.springboot.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping(value="/hello", method = RequestMethod.GET)
    public String helloWorld(){
        return "Welcome to spring boot sarvesh";
    }
}
