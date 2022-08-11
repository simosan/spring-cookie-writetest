package dev.sim.cokitest.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import dev.sim.cokitest.model.Dep;
import dev.sim.cokitest.model.Role;

@Service
public class SimWriteHeader {
	// ROLEリスト文字列
	private String cookiestr = "";
	
	public void writeHeader(HttpServletResponse res, List<Dep> role)
	{
		// List（Depテーブルの値）を"Key"="Value" "Key"="Value"〜にする
		for (Dep d : role) {
			for (Role r : d.getRolelist()) {
				cookiestr = cookiestr + "ROLE=" + r.getRolename() + " ";
			}
			// 末尾の空白を削除
			cookiestr = cookiestr.substring(0, cookiestr.length() - 1);
		}
		
		// Header追加
		res.addHeader("X_ROLE", cookiestr);
	}
}
