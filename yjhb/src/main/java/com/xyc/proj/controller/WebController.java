/**
 * All rights, including trade secret rights, reserved.
 */
package com.xyc.proj.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xyc.proj.entity.SysUser;
import com.xyc.proj.global.Constants;
import com.xyc.proj.service.manage.SystemService;

@Controller
public class WebController {

	@Autowired
	private SystemService systemService;
	
	@RequestMapping("/web/login")
	public String login(
	        @RequestParam(value = "fromMenu", required = true) String fromMenu,
	        Model model,HttpSession Session) {
		
		return "master";
	}

}
