package com.salon.auth;

import com.salon.common.core.EnableException;
import com.salon.common.redis.annotation.EnableRedisRepository;
import com.salon.common.rocketmq.template.RocketMqTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
@EnableRedisRepository
@EnableException
@MapperScan(basePackages = "com.salon.auth.mapper")
@Import(RocketMqTemplate.class)
public class AuthServerApp {
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(AuthServerApp.class);
		application.run(args);
	}

}
