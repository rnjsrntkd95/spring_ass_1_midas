package com.epkorea.backoffice.core;

import com.epkorea.backoffice.interceptor.SessionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor())
                .addPathPatterns("/*")
                .excludePathPatterns("/")
                .excludePathPatterns("/error")
                .excludePathPatterns("/user/login");
    }
}
