package com.ago.custom.CustomInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PreDestroy;

/**
 * @ClassName:CustomInterceptorConfig
 * @Describe:
 * @Data:2020/5/2614:46
 * @Author:Ago
 * @Version 1.0
 */
@Configuration
public class CustomInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new OldLoginInterceptor()).addPathPatterns("/interceptor/oldLogin");
    }
}
