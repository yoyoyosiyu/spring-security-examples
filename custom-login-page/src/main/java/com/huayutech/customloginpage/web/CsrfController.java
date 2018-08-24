package com.huayutech.customloginpage.web;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CsrfController {

    @GetMapping("/csrf")
    @CrossOrigin(value = "http://localhost:9000", allowCredentials = "true")
    public CsrfToken  csrfToken(CsrfToken csrfToken) {
        return csrfToken;
    }

}
