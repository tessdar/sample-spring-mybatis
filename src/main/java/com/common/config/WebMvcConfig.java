package com.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.common.interceptor.LanguageConfig;
import com.common.interceptor.LoginCheck;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	LanguageConfig languageConfig;

	@Autowired
	LoginCheck loginCheck;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(languageConfig).addPathPatterns("/api/**/**");
		registry.addInterceptor(loginCheck).addPathPatterns("/api/emp/**").excludePathPatterns("/api/auth/**");
	}
	
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("GET", "PUT", "POST", "DELETE", "OPTIONS");
    }
}
