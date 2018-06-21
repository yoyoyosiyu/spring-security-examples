package com.huayutech.customauthorization.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class IndexController {

    @Autowired
    RequestMappingHandlerMapping requestMappingHandlerMapping;

    @GetMapping
    private List doGet() {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();

        List<String> mappings = new ArrayList<String>();

        for(Map.Entry<RequestMappingInfo, HandlerMethod> entry: handlerMethods.entrySet()) {
            RequestMappingInfo requestMappingInfo = entry.getKey();
            mappings.add(requestMappingInfo.toString());
        }

        return mappings;
    }

}
