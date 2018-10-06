package com.ischoolbar.programmer.util;

import java.util.Date;

public class SnGenerateUtil {
	public static String generateSn(int clazzId) {
		String sn = "";
		sn = DateFormatUtil.getFormatDate(new Date(), "yyyyMMdd");
		return sn; 
	}
}
