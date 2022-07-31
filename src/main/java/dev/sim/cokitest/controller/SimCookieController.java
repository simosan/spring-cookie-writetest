package dev.sim.cokitest.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import dev.sim.cokitest.model.Dep;
import dev.sim.cokitest.service.SimCookieService;
import dev.sim.cokitest.service.SimWriteCookie;
import dev.sim.cokitest.service.SimWriteCookieUtf8Impl;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class SimCookieController {
	
	@Autowired
	private final SimCookieService service;
	
    @GetMapping("/roles")
    public List<Dep> getRoles(@RequestParam("uid") String uid, 
    		                                       HttpServletResponse response)
    {
    	// DBからユーザIDに紐づいた情報を取得する
        List<Dep> uWithRolelist = service.selectUid(uid);
        // HTTP responseを介してCookieに対して、uWithRolelist（Role情報）を書き込む
        //// UTF-8版
        SimWriteCookie swc = new SimWriteCookieUtf8Impl();
        swc.writeCookie(response, uWithRolelist);
        return uWithRolelist;
    }
}
