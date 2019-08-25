package com.sandbox;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaServer
@ComponentScan(value = "com.sandbox")
public class ServicesRegistryApplication {
	public static void main(String[] args){
		SpringApplication.run(ServicesRegistryApplication.class, args);
	}
}