package com.sandbox;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MsgProcessor {
	private static final Logger LOGGER = LoggerFactory.getLogger(MsgProcessor.class);
	private static final String POSITION_QUEUE = "positionQueue";
	
	@Autowired
	private Data data;
	
	@JmsListener(destination = POSITION_QUEUE)
	public void processMsgFromQueue(Map<String, String> msg){
		data.updatePosition(msg);
		// LOGGER.info("Received Msg - "+msg);
	}
	
	public Data getData(){
		return data;
	}
	
	public void setData(Data data){
		this.data = data;
	}
}