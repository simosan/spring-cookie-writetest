package dev.sim.cokitest.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

@Service
public class SImWriteRoleParamCookie {
	
	private static final Integer agetime = 2 * 60 * 60;
	private static final String samesiteattr = "Lax";
	private static String sessId;

	public void writeCookie(HttpServletRequest req, HttpServletResponse res, String role, String uid)
	{		
		// リバースプロキシで必要なパラメータであるROLE名をCookieに書き込む。
		// Cookie生成
		/// Cookie生存時間は2時間
		/// セキュア属性は無効化（HTTPサイトでもCookieがわたるようにする）
		/// Samesite（同一ドメイン名）制限は少しゆるめ（CSRF攻撃には対応できる）
		
		//// SIMSESSIONIDにJSESSIONID+role名を書き込む
		sessId = req.getSession().getId();
		ResponseCookie cookie1 = ResponseCookie.from("SIMSESSIONID", sessId + "-" + role)
				.httpOnly(true)
				.maxAge(agetime)
				.secure(false)
				.sameSite(samesiteattr)
				.build();
		// Cookie追加
		res.addHeader("Set-Cookie", cookie1.toString());
		
		//// EMPLOYEEIDにuidを書き込む
		ResponseCookie cookie2 = ResponseCookie.from("EMPLOYEEID", uid)
				.httpOnly(true)
				.maxAge(agetime)
				.secure(false)
				.sameSite(samesiteattr)
				.build();
		// Cookie追加
		res.addHeader("Set-Cookie", cookie2.toString());		
		
		//// ROLEPARAMETERにrole名を書き込む
		//// クライアントのJavascript（document.cookie）で読み込むため、httponlyはfalseにする
		ResponseCookie cookie3 = ResponseCookie.from("ROLEPARAMETER", role)
				.httpOnly(false)
				.maxAge(agetime)
				.secure(false)
				.sameSite(samesiteattr)
				.build();
		// Cookie追加
		res.addHeader("Set-Cookie", cookie3.toString());
	}
}
