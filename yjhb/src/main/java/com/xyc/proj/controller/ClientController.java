/**
 * All rights, including trade secret rights, reserved.
 */
package com.xyc.proj.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.xyc.proj.entity.User;
import com.xyc.proj.service.ClientService;
import com.xyc.proj.utility.Properties;
import com.xyc.proj.utility.Result;
import com.xyc.proj.utility.StringUtil;
import com.xyc.proj.utility.Tools;

@Controller
public class ClientController {
 
	@Autowired
	Properties properties;  
	@Autowired
	ClientService clientService;
	
	@RequestMapping("/client/getAuthCode")
	@ResponseBody
	public Result getAuthCode( 
	        Model model,HttpSession Session,HttpServletRequest request) {
		Result res=new Result();
		HashMap<String, Object> result = null;
		com.alibaba.fastjson.JSONObject json=Tools.getJSON(request);
		String mobileNo=StringUtil.getStringInJson(json,"mobileNo");
		mobileNo="18611298927";
		String randomCode=String.valueOf((int)(Math.random()*9000+1000));
		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init(properties.getSmsurl(), properties.getSmsport());// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount(properties.getSmsaccountId(), properties.getSmsaccountToken());// 初始化主帐号名称和主帐号令牌
		restAPI.setAppId(properties.getSmsappid());// 初始化应用ID
		String templeId=properties.getSmstemplateId();
		templeId="1";
		result = restAPI.sendTemplateSMS(mobileNo, templeId,new String[]{randomCode,"1"});
		if("000000".equals(result.get("statusCode"))) {
			User u=new User();
			u.setAuthCode(randomCode);
			u.setMobileNo(mobileNo);
			u.setCreatedTime(new Date());
			try {
				clientService.saveUser(u);
			}catch(Exception e) {
				e.printStackTrace();
				res.resultCode=0;
			}
			
		}else {
			res.resultCode=0;
		}
		return res;
	}

	@RequestMapping("/client/getLatestUserByMobile")
	@ResponseBody 
	public Result getLatestUserByMobile( 
	        Model model,HttpSession Session,HttpServletRequest request) {
		com.alibaba.fastjson.JSONObject json=Tools.getJSON(request);
		String mobileNo=StringUtil.getStringInJson(json,"mobileNo");
		mobileNo="18611298927";
		Result res=new Result();
		List ulist=clientService.getUserListByMobileNo(mobileNo);
		if(ulist!=null && ulist.size()>0) {
			User u=(User)ulist.get(0);
			res.result=u;
		}else {
			res.resultCode=0;
		}
		
		return res;
	}
}
