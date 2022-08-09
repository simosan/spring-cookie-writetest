package dev.sim.cokitest.service;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import dev.sim.cokitest.model.Dep;
import dev.sim.cokitest.model.Role;

/**
 * DBから取得したデータをSJISに変換
 * 
 */
public class SimWriteCookieSJISImpl implements SimWriteCookie {
	// ROLEリスト文字列
	private String cookiestr = "";
	byte[] cookiebyte;
	private String chgcookiestr = "";
	
	public void writeCookie(HttpServletResponse res, List<Dep> role) {

		// List（Depテーブルの値）を"Key"="Value" "Key"="Value"〜にする
		for (Dep d : role) {
			for (Role r : d.getRolelist()) {
				cookiestr = cookiestr + "ROLE=" + r.getRolename() + " ";
			}
			// 末尾の空白を削除
			cookiestr = cookiestr.substring(0, cookiestr.length() - 1);
			// 末尾に#を追記
			cookiestr = cookiestr + "#";
		}
		// SJISに変換
		try {
			cookiebyte = cookiestr.getBytes("SJIS");
			chgcookiestr = new String(cookiebyte, "SJIS");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		// ROLEリスト文字列をbase64に変換
		chgcookiestr = Base64.getEncoder().encodeToString(chgcookiestr.getBytes());

		// Cookie生成
		Cookie cookie = new Cookie("USER_INFO", chgcookiestr);
		// Cookieの残存期間（秒数）-> 3時間
		cookie.setMaxAge(3 * 60 * 60);
		// Cookie追加
		res.addCookie(cookie);
	}
}
