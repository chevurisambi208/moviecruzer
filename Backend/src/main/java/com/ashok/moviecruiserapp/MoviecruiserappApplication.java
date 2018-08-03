package com.ashok.moviecruiserapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import com.ashok.moviecruiserapp.config.JwtFilter;

@SpringBootApplication
public class MoviecruiserappApplication {
	

	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/api/*");

		return registrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(MoviecruiserappApplication.class, args);
		System.out.println("Welcome to Moviecruiser Application");;
	}
}

