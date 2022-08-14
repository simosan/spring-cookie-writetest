package dev.sim.cokitest.service;

import javax.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

@Service
public class SImWriteRoleParamCookie {

	public void writeCookie(HttpServletResponse res, String role)
	{		
		// リバースプロキシで必要なパラメータであるROLE名をCookieに書き込む。
		// Cookie生成
		/// Cookie生存時間は2時間
		/// セキュア属性は無効化（HTTPサイトでもCookieがわたるようにする）
		/// Samesite（同一ドメイン名）制限は少しゆるめ（CSRF攻撃には対応できる）
		ResponseCookie cookie = ResponseCookie.from("ROLEPARAMETER", role)
				.httpOnly(true)
				.maxAge(2 * 60 * 60)
				.secure(false)
				.sameSite("Lax")
				.build();
		// Cookie追加
		res.addHeader("Set-Cookie", cookie.toString());
	}
}
