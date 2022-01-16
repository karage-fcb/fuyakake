package com.uhablog.fuyakake.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    
    /**
     * 
     */
    public void addViewControllers(ViewControllerRegistry registry) {
        // ログイン画面
        registry.addViewController("/login").setViewName("login");
        // ユーザー登録画面
        registry.addViewController("/signup").setViewName("signup");
    }
}