package com.codejava;	

import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
//@ComponentScan(basePackages = "com.codejava")
public class ProductManagerApplication implements WebMvcConfigurer {
	
	public static void main(String[] args) { 
		SpringApplication.run(ProductManagerApplication.class, args);
		 
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // Register resource handler for images
        registry.addResourceHandler("/image/**").addResourceLocations("/WEB-INF/image/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
    }

}
