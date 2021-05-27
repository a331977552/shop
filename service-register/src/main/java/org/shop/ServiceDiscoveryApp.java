package org.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ServiceDiscoveryApp {

	public static void main(String[] args) {
		SpringApplication.run(ServiceDiscoveryApp.class, args);
	}

}
