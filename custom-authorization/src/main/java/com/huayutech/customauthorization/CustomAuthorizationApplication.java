package com.huayutech.customauthorization;

import com.huayutech.customauthorization.config.SecuredTargetProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomAuthorizationApplication {



    public static void main(String[] args) {
        SpringApplication.run(CustomAuthorizationApplication.class, args);
    }
}
