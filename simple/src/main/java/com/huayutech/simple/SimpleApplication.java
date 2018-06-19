package com.huayutech.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@SpringBootApplication
public class SimpleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleApplication.class, args);
    }

    @GetMapping("/userinfo")
    protected Principal doUserInfo(Principal principal) {
        return principal;
    }

    @GetMapping("/admin/userinfo")
    protected Principal doGetAdminUserInfo(Principal principal) {
        return principal;
    }
}
