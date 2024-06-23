/*
 * Excel Download
 */
package com.oneblocks.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

	public static String getBeforeDate(int cnt) {
		SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
		Date beforeDate = new Date(new Date().getTime()+(1000*60*60*24*-cnt));
		String beforeDay = format.format(beforeDate);
		return beforeDay;
	}
	
}
