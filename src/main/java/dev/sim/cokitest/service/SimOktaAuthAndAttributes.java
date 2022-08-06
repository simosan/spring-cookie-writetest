package dev.sim.cokitest.service;

import javax.servlet.http.HttpServletResponse;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class SimOktaAuthAndAttributes {
	
	private SimOktaAttributeService oktaattr;

	public SimOktaAuthAndAttributes(SimOktaAttributeService oktaattr) {
		this.oktaattr = oktaattr;
	}

	public String getOktauid(HttpServletResponse res, OAuth2AuthenticationToken auth) {

		Map<String,Object> m = auth.getPrincipal().getAttributes();
		String uid = oktaattr.getuid(m);

		return uid;

	}
}
