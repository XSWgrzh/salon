package com.salon.admin;

import com.salon.annotation.EnableSecurity;
import com.salon.common.core.EnableException;
import com.salon.common.log.annotation.EnableOperateLog;
import com.salon.common.redis.annotation.EnableRedisRepository;
import com.salon.common.rocketmq.template.RocketMqTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;


//@SpringBootApplication(scanBasePackages = "com.salon")
@SpringBootApplication
@EnableDiscoveryClient
@EnableRedisRepository
@EnableOperateLog
@EnableException
@EnableSecurity
@Import(RocketMqTemplate.class)
public class AdminServerApp {
	public static void main(String[] args) {
		// SpringSecurity 子线程读取父线程的上下文
//		System.setProperty(SecurityContextHolder.SYSTEM_PROPERTY, SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
		SpringApplication application = new SpringApplication(AdminServerApp.class);
		application.run(args);
	}

}
