package com.kgl1688.mvc;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @RequestMapping("/")
    @ResponseBody
    public String sayHello() {

        return "hello, world";
    }

    @RequestMapping("/userinfo")
    @ResponseBody
    public String getUserInfo() {

        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();

        return ((User)auth.getPrincipal()).getUsername();
    }

    @RequestMapping("/about")
    @ResponseBody
    public String getAbount() {

        return "My Admin 1.0";
    }
}
