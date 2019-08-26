package com.sandbox.controllers;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.sandbox.data.VehicleRepository;
import com.sandbox.domain.Vehicle;
import com.sandbox.models.Position;
import com.sandbox.services.PositionLocatorService;

@Controller
@Transactional
@RequestMapping("/website/vehicles")
public class VehicleController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VehicleController.class);
	
	@Autowired
	private VehicleRepository vehicleRepo;
	@Autowired
	private PositionLocatorService pls;

	@Value("${googlemaps.key}")
	private String googleMapsKey;
	
	@RequestMapping(value = "/newVehicle.html", method = RequestMethod.POST)
	public String newVehicle(Vehicle vehicle){
		if(LOGGER.isDebugEnabled()) 
			LOGGER.debug("Entered newVehicle");
		
		vehicleRepo.save(vehicle);
		
		if(LOGGER.isDebugEnabled()) 
			LOGGER.debug("Exited newVehicle");
		return "redirect:/website/vehicles/list.html";
	}
	
	@RequestMapping(value = "/deleteVehicle.html", method = RequestMethod.POST)
	public String deleteVehicle(@RequestParam Long id){
		if(LOGGER.isDebugEnabled()) 
			LOGGER.debug("Entered deleteVehicle");
		
		vehicleRepo.deleteById(id);
		
		if(LOGGER.isDebugEnabled()) 
			LOGGER.debug("Exited deleteVehicle");
		return "redirect:/website/vehicles/list.html";
	}
	
	@RequestMapping(value = "/newVehicle.html", method = RequestMethod.GET)
	public ModelAndView renderNewVehicleForm(){
		if(LOGGER.isDebugEnabled()) 
			LOGGER.debug("Entered renderNewVehicleForm");
		
		Vehicle newVehicle = new Vehicle();
		
		if(LOGGER.isDebugEnabled()) 
			LOGGER.debug("Exited renderNewVehicleForm");
		return new ModelAndView("newVehicle", "form", newVehicle);
	}
	
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView vehicles(){
		if(LOGGER.isDebugEnabled()) 
			LOGGER.debug("Entered vehicles");
		
		List<Vehicle> allVehicles = vehicleRepo.findAll();
		
		if(LOGGER.isDebugEnabled()) 
			LOGGER.debug("Exited vehicles");
		return new ModelAndView("allVehicles", "vehicles", allVehicles);
	}
	
	@RequestMapping(value = "/vehicle/{name}")
	public ModelAndView showVehicleByName(@PathVariable("name") String name){
		if(LOGGER.isDebugEnabled()) 
			LOGGER.debug("Entered showVehicleByName");
		
		Vehicle vehicle = vehicleRepo.findByName(name);
		
		Position pos = pls.getPosition(name);
		pos.setGoogleMapsKey(googleMapsKey);
		
		// if successful, update h2 Db
		if(pos.getIsCurrent()){
			LOGGER.info("Successful GetPosition! Vehicle Name - " +name);
			
			if(vehicle == null) vehicle = new Vehicle();
			vehicle.setLat(pos.getLat());
			vehicle.setLng(pos.getLng());
			vehicle.setTimestamp(pos.getTimestamp());
		}

		Map<String, Object> model = new HashMap<>();
		model.put("vehicle", vehicle);
		model.put("position", pos);
		// model.put("port", service.getPort());
		if(LOGGER.isDebugEnabled()) 
			LOGGER.debug("Exited showVehicleByName");
		return new ModelAndView("vehicleInfo", "model", model);
	}
}
