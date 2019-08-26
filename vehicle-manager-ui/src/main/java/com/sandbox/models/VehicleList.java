package com.sandbox.models;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.sandbox.constants.CommonConstants;
import com.sandbox.domain.Vehicle;

@XmlRootElement(name=CommonConstants.VEHICLES)
public class VehicleList
{
	private List<Vehicle> vehicles;
	
	public VehicleList() {}
	
	public VehicleList(List<Vehicle> vehicles){
		this.vehicles = vehicles;
	}

	@XmlElement(name=CommonConstants.VEHICLE)
	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
}