package com.example.demo.config;

import com.example.demo.config.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 拦截路径
        // excludePathPatterns 不拦截路径
        registry.addInterceptor(loginInterceptor).addPathPatterns("/user/**").excludePathPatterns("/user/login.do","/user/reg.do","/html/login.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }
}
