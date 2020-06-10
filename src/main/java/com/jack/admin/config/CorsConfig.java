package com.jack.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置
 *
 * @author crazyjack262
 * @date 2020-06-09 15:09
 */
@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                /** * 至少需要addMapping *** */
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("PUT", "DELETE", "GET", "POST", "OPTIONS", "HEAD")
                        .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Authorization", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers")
                        .allowCredentials(true)// 是否带上cookie
                        .maxAge(3600).exposedHeaders("access-control-allow-headers", "access-control-allow-methods", "access-control-allow-origin",
                        "access-Control-allow-credentials", "access-control-max-age", "X-Frame-Options");
            }
        };
    }
}
