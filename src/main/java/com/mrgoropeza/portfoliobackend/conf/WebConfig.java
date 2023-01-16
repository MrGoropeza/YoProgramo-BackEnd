package com.mrgoropeza.portfoliobackend.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@CrossOrigin
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/login")
            .allowedOrigins("*")
            .allowedMethods("*")
            .exposedHeaders("*");

        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .exposedHeaders("*");
    }
}