package com.lingyun.demos.shiro.controller;

import com.lingyun.demos.shiro.entity.User;
import com.lingyun.demos.shiro.repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @GetMapping("/login")
    public String userLogin(){
        return "forward:/user/login.html";
    }
    @PostMapping("/login")
    public String userDoLogin(@RequestParam User user){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            //4、登录，即身份验证
            subject.login(token);
            return loginSuccess();
        } catch (AuthenticationException e) {
            //5、身份验证失败
            return userUnauthorized();
        }
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
