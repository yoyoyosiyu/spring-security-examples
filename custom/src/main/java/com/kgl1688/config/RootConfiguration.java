package com.kgl1688.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:security.xml")
@ComponentScan
public class RootConfiguration {
}
