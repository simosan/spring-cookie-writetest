package dev.sim.cokitest.service;

import java.util.Base64;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;
import dev.sim.cokitest.model.Dep;
import dev.sim.cokitest.model.Role;

/**
 * SQLiteのデータはもともとUTF8なのでいちいち変換処理はいれてない。
 * 
 */
public class SimWriteCookieUtf8Impl implements SimWriteCookie {
	// ROLEリスト文字列
	private String cookiestr = "";

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
		// ROLEリスト文字列をbase64に変換
		cookiestr = Base64.getEncoder().encodeToString(cookiestr.getBytes());

		// Cookie生成
		/// Cookie生存時間は2時間
		/// セキュア属性は無効化（HTTPサイトでもCookieがわたるようにする）
		/// Samesite（同一ドメイン名）制限は少しゆるめ（CSRF攻撃には対応できる）
		ResponseCookie cookie = ResponseCookie.from("USER_INFO2", cookiestr)
				.httpOnly(true)
				.maxAge(2 * 60 * 60)
				.secure(false)
				.sameSite("Lax")
				.build();
		// Cookie追加
		res.addHeader("Set-Cookie", cookie.toString());
	}
}
