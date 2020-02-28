package com.t248.zjl.thymeleaf.FileRecv;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean registrationBean=new FilterRegistrationBean();
        registrationBean.setFilter((Filter) new AuthorizationFilter());
        registrationBean.setName("AuthorizationFilter");
        registrationBean.addUrlPatterns("/main");
        registrationBean.setOrder(5);
        return registrationBean;
    }
}
