package com.sandbox.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sandbox.controllers.Position;
import com.sandbox.data.VehicleRepository;
import com.sandbox.domain.Vehicle;

@Service
public class PositionLocatorService {
	@Autowired
	private VehicleRepository vehicleRepo;
	
	@Autowired
	private RemotePositionServiceCalls remoteServiceCalls;
	
	@HystrixCommand(fallbackMethod = "handleGetPositionDown")
	public Position getPosition(String name){
		Position position = remoteServiceCalls.getPosition(name);
		position.setIsCurrent(true);
		
		return position;
	}
	
	public Position handleGetPositionDown(String name){
		System.out.println("handleGetPositionDown fallback is being used");
		Position pos = new Position();
		pos.setIsCurrent(false);
		
		Vehicle vehicle = vehicleRepo.findByName(name);
		pos.setLat(vehicle.getLat());
		pos.setLng(vehicle.getLng());
		pos.setTimestamp(vehicle.getTimestamp());
		
		return pos;
	}
}
