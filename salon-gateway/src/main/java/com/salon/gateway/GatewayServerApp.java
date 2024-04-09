package com.salon.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServerApp {
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(GatewayServerApp.class);
		application.run(args);
	}
}
