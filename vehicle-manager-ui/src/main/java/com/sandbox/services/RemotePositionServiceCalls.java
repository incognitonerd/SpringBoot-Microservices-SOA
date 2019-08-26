package com.sandbox.services;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sandbox.constants.CommonConstants;
import com.sandbox.models.Position;

@FeignClient(name = CommonConstants.VEHICLE_POSITION_TRACKING_EUREKA_INSTANCE)
public interface RemotePositionServiceCalls {
	@RequestMapping(method = RequestMethod.GET, value =CommonConstants.GET_POSITION_ENDPOINT)
	public Position getPosition(@PathVariable("name") String name);
}