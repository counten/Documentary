package com.swu.cjyong.conf;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

import javax.servlet.MultipartConfigElement;

@Configuration
public class FileUploadConfig {
    @Value("${fileupload.storelocation}")
    private String fileLocation;

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation(fileLocation);
        return factory.createMultipartConfig();
    }
}
