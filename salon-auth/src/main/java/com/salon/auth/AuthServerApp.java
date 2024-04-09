package com.salon.auth;

import com.salon.common.core.EnableException;
import com.salon.common.redis.annotation.EnableRedisRepository;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableRedisRepository
@EnableException
@MapperScan(basePackages = "com.salon.auth.mapper")
public class AuthServerApp {
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(AuthServerApp.class);
		application.run(args);
	}

}
