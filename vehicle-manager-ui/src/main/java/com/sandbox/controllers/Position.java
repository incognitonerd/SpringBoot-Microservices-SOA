package com.sandbox.controllers;
import java.math.BigDecimal;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@XmlRootElement
public class Position {
	private BigDecimal lat;
	private BigDecimal lng;
	private Date timestamp;
	private Boolean isCurrent;
	private String googleMapsKey;
	
	public BigDecimal getLat(){
		return lat;
	}
	
	public void setLat(BigDecimal lat){
		this.lat = lat;
	}
	
	public BigDecimal getLng(){
		return lng;
	}
	
	public void setLng(BigDecimal lng){
		this.lng = lng;
	}
	
	public Date getTimestamp(){
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp){
		this.timestamp = timestamp;
	}
	
	public Boolean getIsCurrent(){
		return isCurrent;
	}
	
	public void setIsCurrent(Boolean isCurrent){
		this.isCurrent = isCurrent;
	}
	
	public String getGoogleMapsKey(){
		return googleMapsKey;
	}
	
	public void setGoogleMapsKey(String googleMapsKey){
		this.googleMapsKey = googleMapsKey;
	}
}
