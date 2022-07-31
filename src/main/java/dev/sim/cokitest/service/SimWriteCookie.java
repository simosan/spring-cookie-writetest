package dev.sim.cokitest.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import dev.sim.cokitest.model.Dep;

public interface SimWriteCookie {
	
	public void writeCookie(HttpServletResponse res, List<Dep> role);
	
}
