package com.sandbox.VehicleManagerGlobalConfigs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class VehicleManagerGlobalConfigsApplication {
	public static void main(String[] args) {
		SpringApplication.run(VehicleManagerGlobalConfigsApplication.class, args);
	}
}
