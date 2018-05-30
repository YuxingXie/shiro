package com.lingyun.demos.shiro.controller;

import com.lingyun.demos.shiro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @RequestMapping("")
    public String userIndex(){
        userRepository.testSelectAll();
        return "forward:/user/index.html";
    }
}
