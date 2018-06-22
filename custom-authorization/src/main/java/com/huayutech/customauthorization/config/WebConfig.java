package com.huayutech.customauthorization.config;

import com.huayutech.customauthorization.security.RbacAccessDecisionManager;
import com.huayutech.customauthorization.security.RbacSecurityMetadataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
public class WebConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    SecuredTargetProperties securedTargetProperties;

    @Bean
    RbacAccessDecisionManager rbacAccessDecisionManager() {
        return new RbacAccessDecisionManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O filterSecurityInterceptor) {

                filterSecurityInterceptor.setAccessDecisionManager(rbacAccessDecisionManager());

                RbacSecurityMetadataSource metadataSource =new RbacSecurityMetadataSource();
                metadataSource.setSecuredTargets(securedTargetProperties.getTargets());
                filterSecurityInterceptor.setSecurityMetadataSource(metadataSource);

                return filterSecurityInterceptor;
            }
        });
    }
}
