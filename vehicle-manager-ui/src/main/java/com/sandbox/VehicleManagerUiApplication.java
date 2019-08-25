package com.sandbox;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.sandbox")
@EnableCircuitBreaker
@EnableFeignClients
@EnableDiscoveryClient
public class VehicleManagerUiApplication {
	public static void main(String[] args){
		SpringApplication.run(VehicleManagerUiApplication.class, args);
	}
}