package com.sandbox.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vehicle {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private long id;
	private String name;
	private int odometer;
	private String status;
	private BigDecimal lat;
	private BigDecimal lng;
	private Date timestamp;
	private String currentDriver;
	private String chassisNumber;
	
	public Vehicle() {}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Vehicle(String name) {
		this.name = name;
	}

	public String toString()
	{
		return this.name + " odometer "  + this.odometer + " last seen at " + this.timestamp + " at " + this.lat + "," + this.lng;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public BigDecimal getLng() {
		return lng;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOdometer() {
		return odometer;
	}

	public void setOdometer(int odometer) {
		this.odometer = odometer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCurrentDriver() {
		return currentDriver;
	}

	public void setCurrentDriver(String currentDriver) {
		this.currentDriver = currentDriver;
	}
	
	public String getChassisNumber() {
		return chassisNumber;
	}
	
	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}
}
