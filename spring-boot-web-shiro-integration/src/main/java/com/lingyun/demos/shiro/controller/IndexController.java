package com.lingyun.demos.shiro.controller;

import com.lingyun.demos.shiro.entity.User;
import com.lingyun.demos.shiro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

@Controller
public class IndexController {


    @RequestMapping("/")
    public String userIndex(){
        return "forward:/index.html";
    }
}
