package dev.sim.cokitest.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class SimGetCookie {
	
	private String val = "";
	
	public String getCookie(HttpServletRequest req) {
				
		Cookie[] cookies = req.getCookies();
		
		if (cookies != null){
			for (int i = 0 ; i < cookies.length ; i++){
		    	if (cookies[i].getName().equals("AKROLE-PARAMETER")){
		    		val = cookies[i].getValue();
		    	}
		    }
		}
		//　先頭の/を削除
		val = val.replace("/", "");
		return val;
	}

}
