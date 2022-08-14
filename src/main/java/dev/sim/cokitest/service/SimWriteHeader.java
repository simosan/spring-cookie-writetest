package dev.sim.cokitest.service;

import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public class SimWriteHeader {
	
	public void writeHeader(HttpServletResponse res, String role)
	{		
		// Header追加
		res.addHeader("X-ROLE", role);
	}
}
