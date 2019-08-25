package com.sandbox.services;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sandbox.controllers.Position;

@FeignClient(name = "vehicle-position-tracking")
public interface RemotePositionServiceCalls {
	// Feign automatically converts this to a rest call
	@RequestMapping(method = RequestMethod.GET, value = "/vehicles/{name}")
	public Position getPosition(@PathVariable("name") String name);
}