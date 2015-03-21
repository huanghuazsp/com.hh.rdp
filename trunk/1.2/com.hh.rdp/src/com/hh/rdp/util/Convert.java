package com.hh.rdp.util;


public class Convert {
	/**
	 * 对象转字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String toString(Object object) {
		return object == null ? "" : object.toString();
	}
	public static Long toLong(Object object) {
		if (Check.isNumber(object)) {
			return Long.valueOf(object.toString());
		}
		return 0l;
	}
}
