package com.ahsan.file.app.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@Profile("dev")
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**").allowedOrigins("http://localhost:4200")
                        .allowedMethods("PUT", "DELETE", "POST", "GET")
                        .allowedHeaders("Access-Control-Allow-Headers","Access-Control-Allow-Origin", "Content-Type",
                                "Access-Control-Expose-Headers", "Authorization", "SuperAdmin")
                        .exposedHeaders("Authorization")
                        .allowCredentials(false).maxAge(3600);
            }
        };
    }


}
