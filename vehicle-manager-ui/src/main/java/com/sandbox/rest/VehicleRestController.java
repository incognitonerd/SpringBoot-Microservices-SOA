package com.sandbox.rest;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.sandbox.data.VehicleRepository;
import com.sandbox.domain.Vehicle;
import com.sandbox.models.VehicleList;

@RestController
@RequestMapping("/")
public class VehicleRestController {
	private static final Logger LOGGER = LoggerFactory.getLogger(VehicleRestController.class);
	
	@Autowired
	private VehicleRepository data;
	
	@RequestMapping("/vehicles")
	public VehicleList allVehicles(){
		if(LOGGER.isDebugEnabled()) 
			LOGGER.debug("Entered allVehicles");
		List<Vehicle> all = data.findAll();
		if(LOGGER.isDebugEnabled()) 
			LOGGER.debug("Exited allVehicles");
		return new VehicleList(all);
	}
	
	@RequestMapping(value = "/vehicles", method = RequestMethod.POST)
	public ResponseEntity<Vehicle> createANewVehicle(@RequestBody Vehicle vehicle){
		if(LOGGER.isDebugEnabled()) 
			LOGGER.debug("Entered createANewVehicle");
		data.save(vehicle);
		
		if(LOGGER.isDebugEnabled()) 
			LOGGER.debug("Exited createANewVehicle");
		return new ResponseEntity<Vehicle>(vehicle, HttpStatus.CREATED);
	}
}