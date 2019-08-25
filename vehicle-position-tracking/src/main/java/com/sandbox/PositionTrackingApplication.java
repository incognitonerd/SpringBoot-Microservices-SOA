package com.sandbox;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.sandbox")
@EnableDiscoveryClient
public class PositionTrackingApplication {
	public static void main(String[] args){
		SpringApplication.run(PositionTrackingApplication.class, args);
	}
}
