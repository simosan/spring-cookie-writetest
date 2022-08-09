package dev.sim.cokitest.service;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class SimOktaAttributeServiceUtf8 implements SimOktaAttributeService{
	
	public String getuid(Map<String,Object> mapper) {
		String uid = null;
		for(Map.Entry<String, Object> entry : mapper.entrySet()) {
			if(entry.getKey().equals("empid")) {
				uid = entry.getValue().toString();
				break;
			}
		}
		return uid;
	}
}
