package com.lingyun.demos.shiro.controller;

import com.lingyun.demos.shiro.entity.User;
import com.lingyun.demos.shiro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @RequestMapping("")
    public String userIndex(){
        User user=userRepository.findByUsername("zhang");
        return "forward:/user/index.html";
    }
    @RequestMapping("/login")
    public String userLogin(){
        return "forward:/user/login.html";
    }
    @RequestMapping("/logout")
    public String userLogout(){
        return "forward:/user/logout.html";
    }

    @RequestMapping("/unauthorized")
    public String userUnauthorized(){
        return "forward:/user/unauthorized.html";
    }

    @RequestMapping("/loginsuccess")
    public String loginSuccess(){
        return "forward:/user/loginsuccess.html";
    }

}
