package com.dita.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = { "com.dita.myapp.domain" }) // 엔티티 경로
@ComponentScan(basePackages = { "com.dita.myapp" }) // 전체 스캔 범위
public class MyspringApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyspringApplication.class, args);
	}

}
