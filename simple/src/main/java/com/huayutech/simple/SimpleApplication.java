package com.huayutech.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.security.Principal;
import java.util.*;

@RestController
@SpringBootApplication
public class SimpleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleApplication.class, args);
    }


    @Autowired
    RequestMappingHandlerMapping requestMappingHandlerMapping;

    @GetMapping("/")
    protected List doGetIndex() {


        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();

        List<String> mappings = new ArrayList<String>();

        for(Map.Entry<RequestMappingInfo, HandlerMethod> entry: handlerMethods.entrySet()) {
            RequestMappingInfo requestMappingInfo = entry.getKey();
            mappings.add(requestMappingInfo.toString());
        }

        return mappings;
    }

    @PostMapping("/")
    protected void doPostIndex() {

    }


    @GetMapping("/userinfo")
    protected Principal doUserInfo(Principal principal) {
        return principal;
    }

    @GetMapping("/admin/userinfo")
    protected Principal doGetAdminUserInfo(Principal principal) {
        return principal;
    }

    @GetMapping("/ant_path_matcher")
    protected boolean doAntPathPattern(@RequestParam(name="pattern") String pattern, @RequestParam(name="path") String path) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();

        return antPathMatcher.match(pattern, path);
    }

    @GetMapping("/template_variables")
    protected Map doTemplateVariables(@RequestParam(name="pattern") String pattern, @RequestParam(name="path") String path) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();

        return antPathMatcher.extractUriTemplateVariables(pattern, path);
    }
}
