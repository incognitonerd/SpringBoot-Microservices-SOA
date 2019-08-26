package com.sandbox.services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sandbox.constants.CommonConstants;
import com.sandbox.data.VehicleRepository;
import com.sandbox.domain.Vehicle;
import com.sandbox.models.Position;
import com.sandbox.rest.VehicleRestController;

@Service
public class PositionLocatorService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PositionLocatorService.class);

	@Autowired
	private VehicleRepository vehicleRepo;
	
	@Autowired
	private RemotePositionServiceCalls remoteServiceCalls;
	
	@HystrixCommand(fallbackMethod = CommonConstants.GET_POSITION_FALLBACKMETHOD)
	public Position getPosition(String name){
		if(LOGGER.isDebugEnabled()) 
			LOGGER.debug("Entered getPosition");
		Position position = remoteServiceCalls.getPosition(name);
		position.setIsCurrent(true);
		
		if(LOGGER.isDebugEnabled()) 
			LOGGER.debug("Exited getPosition");
		return position;
	}
	
	public Position handleGetPositionDown(String name){
		LOGGER.info("Entered handleGetPositionDown - Executing Fallback");
		
		Position pos = new Position();
		pos.setIsCurrent(false);
		
		Vehicle vehicle = vehicleRepo.findByName(name);
		pos.setLat(vehicle.getLat());
		pos.setLng(vehicle.getLng());
		pos.setTimestamp(vehicle.getTimestamp());
		if(LOGGER.isDebugEnabled()) 
			LOGGER.debug("Exited handleGetPositionDown");
		return pos;
	}
}
