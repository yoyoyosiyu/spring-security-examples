package com.huayutech.customloginpage.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @GetMapping("/userinfo")
    public Principal doGetUserInfo(Principal principal) {
        return principal;
    }

}
