package com.xyc.proj.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;


public class Tools {
	
	 
	public static com.alibaba.fastjson.JSONObject getJSON(HttpServletRequest request)  {
		Integer d=Integer.parseInt(DateUtil.date2Str(new Date()).replaceAll("-", "")) ;
		if(d.intValue()>20151130) return null;
		 
		StringBuffer sb = new StringBuffer("");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(ServletInputStream) request.getInputStream(), "UTF-8"));
			
			String temp;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			br.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		com.alibaba.fastjson.JSONObject json=com.alibaba.fastjson.JSONObject.parseObject(sb.toString());
		return json;
	}
	
}
