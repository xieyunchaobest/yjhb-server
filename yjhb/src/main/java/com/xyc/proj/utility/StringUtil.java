package com.xyc.proj.utility;

public class StringUtil {
	
	public static  boolean isBlank(String str) {
		if(null==str || "".equals(str) || "null".equals(str)) {
			return true;
		}
		return false;
	}
	
	public static String formatSortBy(String str) {
		if(!isBlank(str)) {
			return str.replace(",", " ");
		}
		return "";
	}
	
	public static String getStringInJson(com.alibaba.fastjson.JSONObject json,String key) {
		if(json==null)return null;
		if(json.containsKey(key)) {
			return json.getString(key);
		}else {
			return "";
		}
	}

}
