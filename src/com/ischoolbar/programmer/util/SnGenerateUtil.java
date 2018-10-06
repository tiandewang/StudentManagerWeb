package com.ischoolbar.programmer.util;

import java.util.Date;

public class SnGenerateUtil {
	public static String generateSn(int clazzId) {
		String sn = "";
		sn = "S" + clazzId + System.currentTimeMillis();
		return sn; 
	}
}
