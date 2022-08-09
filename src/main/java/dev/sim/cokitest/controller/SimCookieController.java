package dev.sim.cokitest.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.sim.cokitest.model.Dep;
import dev.sim.cokitest.service.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class SimCookieController {

	@Autowired
	private final SimDepAndRoleFromDb dbservice;
	@Autowired
	private final SimGetCookie cookie;
	
	@RequestMapping("/")
	public void redirectWithCookieAndHeader(HttpServletRequest request,
			                                HttpServletResponse response,
			                                Principal principal,
			                                OAuth2AuthenticationToken authentication) {
		// 手前のHTTPサーバで仕込んだCookie（AKROLE-PARAMETER)からROLE情報を取得する
		String role = cookie.getCookie(request);
		
		// Okta認証、Oktaからattributesに設定されたid（Oktaのカスタム属性employeeid）を取得する
		SimOktaAttributeService oktaattrservice = new SimOktaAttributeServiceUtf8();
		SimOktaAuthAndAttributes oktaattrbutes = new SimOktaAuthAndAttributes(oktaattrservice);
		String uid = oktaattrbutes.getOktauid(authentication);

		// DBからユーザIDに紐づいた情報を取得する
		List<Dep> uWithRolelist = dbservice.selectUid(uid);
		// HTTP responseを介してCookieに対して、uWithRolelist（Role情報）を書き込む
		//// UTF-8版
		SimWriteCookie swcutf8 = new SimWriteCookieUtf8Impl();
		swcutf8.writeCookie(response, uWithRolelist);
		//// SJIS版
		SimWriteCookie swcsjis = new SimWriteCookieSJISImpl();
		swcsjis.writeCookie(response, uWithRolelist);
		
		// DBからroleに紐づいたRedirectURLを取得する
		String url = dbservice.selectRedirectUrl(role);
		// リダイレクト先のURLに繊維
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
