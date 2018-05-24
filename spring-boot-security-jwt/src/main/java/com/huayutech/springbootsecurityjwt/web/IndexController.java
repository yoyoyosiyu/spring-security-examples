package com.huayutech.springbootsecurityjwt.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    protected String doGet() {
        return "hello, world";
    }
}
