package com.t248.zjl.thymeleaf.FileRecv;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class InterceptorConfig implements WebMvcConfigurer {
    private String[] excludePathPatterns;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        String[] pathPatterns={"/**"};
        String[] excludePathPatterns={"/login","/regist","/statics/**"};
        registry.addInterceptor(new AuthorizationInterceptor())
                .addPathPatterns(pathPatterns)
                .excludePathPatterns(excludePathPatterns);
    }
}
