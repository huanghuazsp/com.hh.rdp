package com.hh.rdp.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class StaticVar {
	public static List<String> refreshViewColumnList = new ArrayList<String>();
	public static String encoding = "UTF-8";
	public static String JAVA_SOURCE_FOLDER = "src/main/java";
	public static String JS_PAGE_SOURCE_FOLDER = "src/main/js";
	public static String JSP_PAGE_SOURCE_FOLDER = "src/main/jsp";
	public static String RESOURCE_PAGE_SOURCE_FOLDER = "src/main/resources";
	public static String ending = ".hhdm";
	
	static {
		Properties properties = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("config/config.properties");
		try {
			properties.load(inputStream);
			JAVA_SOURCE_FOLDER = properties.getProperty("JAVA_SOURCE_FOLDER");
			JS_PAGE_SOURCE_FOLDER = properties.getProperty("JS_PAGE_SOURCE_FOLDER");
			JSP_PAGE_SOURCE_FOLDER = properties.getProperty("JSP_PAGE_SOURCE_FOLDER");
			RESOURCE_PAGE_SOURCE_FOLDER = properties.getProperty("RESOURCE_PAGE_SOURCE_FOLDER");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
