/**
 * All rights, including trade secret rights, reserved.
 */
package com.xyc.proj.controller.manage;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xyc.proj.entity.SysUser;
import com.xyc.proj.global.Constants;
import com.xyc.proj.service.manage.SystemService;
import com.xyc.proj.utility.PageView;
import com.xyc.proj.utility.PasswordUtil;
import com.xyc.proj.utility.Result;
import com.xyc.proj.utility.StringUtil;


@Controller
@SessionAttributes("user")
public class SystemController {

	@Autowired
	private SystemService systemService;

//	@RequestMapping("/shwoFuncMenu")
//	public String getFuncNode(HttpServletRequest request, Model model) {
//		SysUser u = new SysUser();
//		u.setId(9l);
//		List funcNodeList = systemService.getFuncNodeList(u);
//		model.addAttribute("funcMenuList", JSON.toJSONString(funcNodeList,
//				SerializerFeature.DisableCircularReferenceDetect));
//		return "test";
//	}
	
//	@RequestMapping("/userMgr")
//	public String getFuncNode( Model model) {
//		System.out.println();
//		return "system/SysUserList";
//	}
//	
	@RequestMapping("/userMgr/updateSysUserInit.html")
	public String addSysUserInit( Model model,
			@RequestParam(value = "optFlag", required = true) String optFlag,
			@RequestParam(value = "sysUserId", required = false) Long sysUserId ) {
		SysUser user=new SysUser();
		Long roleId=null;
		if(Constants.OPT_FLAG_UPDATE.equals(optFlag)) {
			if(sysUserId!=null && sysUserId>0l) {
				user=systemService.getSysUser(sysUserId);
				roleId=systemService.getSysRoleByUserId(sysUserId);
			}
		}
		List sysRoleList=systemService.getSysRoles();
		model.addAttribute("sysUser",user);
		model.addAttribute("sysRoleList", sysRoleList);
		
		model.addAttribute("sysRoleId", roleId);
		return "system/UpdateSysUser";
	}
	
	@ResponseBody  
	@RequestMapping("/userMgr/updateSysUser")
	public Result updateSysUser(@ModelAttribute SysUser user,
			@RequestParam(value = "sysUserId", required = false) Long sysUserId,
			@RequestParam(value = "sysRoleId", required = false) Long sysRoleId) {
		Result result=new   Result();
		try {
			if(sysUserId!=null && sysUserId>0l) {
				user.setId(sysUserId);
			}
			systemService.updateSysRoleAndUser(user,sysRoleId);
		}catch( Exception e){
			result.resultCode=0;
			e.printStackTrace();
		}
		
	  return result;
	}
	
	@RequestMapping("/userMgr")
	public String findSysUser( Model model,@ModelAttribute SysUser user,HttpServletRequest request,
			@RequestParam(value = "currentPageNum", required = false,defaultValue="1") Integer currentPageNum,
			@RequestParam(value = "orderByStr", required = false,defaultValue="id,desc") String orderBy) {
		Map<String, Object> map = new HashMap<String,Object>(); 
		map.put("parm", user);
		map.put(Constants.CURRENT_PAGENUM, currentPageNum);
		map.put(Constants.ORDERBY, StringUtil.formatSortBy(orderBy));
		PageView page= systemService.findSysUser(map);
		model.addAttribute("sysUser", user);
		model.addAttribute("orderBy", orderBy);
		model.addAttribute("pageView", page);
		return "system/SysUserList";
	}
	
	@RequestMapping(value= {"/","/login.html"})
	public String showLogin( Model model,@ModelAttribute SysUser user,HttpServletRequest request) {
		return "login";
	}
	
	@RequestMapping(value="/login",method = {RequestMethod.POST})
	public String login(Model model,@ModelAttribute SysUser user,HttpServletRequest request) {
		String pageLogin="/login.html";
		String pageMain="/manage?fromMenu=manage";
		String page="";
		try {
			SysUser u=systemService.login(user);
			if(u==null) {
				model.addAttribute("error", "User Name or Password Error!");
				page=pageLogin;
			}else {
				page=pageMain;
				model.addAttribute("user", u);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			page=pageLogin;
		}
		return "forward:"+page;
	}
	
	@RequestMapping(value ="/userMgr/delete",method = {RequestMethod.POST})
	public String deleteUser( Model model,
			@RequestParam(value = "userId", required = false) Long userId,
			HttpServletRequest request,
			@ModelAttribute SysUser user) {
		Map m=request.getParameterMap();
		systemService.deleteSysUser(userId);
		model.addAttribute("sysUser", user);  
		return "forward:/userMgr";
	}
	
	@RequestMapping(value="/loginOut",method = {RequestMethod.GET})
	public String loginOut(Model model,@ModelAttribute SysUser user,HttpServletRequest request) {
		String pageLogin="/login.html";
		request.getSession().removeAttribute("user");
		return "redirect:"+pageLogin;
	}
		
	@RequestMapping(value="/userMgr/updatePwdInit",method = {RequestMethod.GET,RequestMethod.POST})
	public String updatePwdInit(Model model,@ModelAttribute SysUser user,HttpServletRequest request) {
		return "system/UpdatePwd";
	}
	
	@RequestMapping(value="/userMgr/updatePwd",method = {RequestMethod.GET,RequestMethod.POST})
	public String updatePwd(Model model,@ModelAttribute SysUser user,HttpServletRequest request) {
		String uid=request.getParameter("sysUserId");
		String newPassword=request.getParameter("newPassword");
		SysUser u = systemService.getSysUser(Long.parseLong(uid));
		u.setPassword(newPassword);
		try {
			systemService.updateSysUser(u);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return "system/UpdatePwd";
	}
	
	@RequestMapping(value="/userMgr/valitePwd",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String validatePwd(Model model,@ModelAttribute SysUser user,HttpServletRequest request) {
		String uid=request.getParameter("sysUserId");
		String orginalPassword=request.getParameter("orginalPassword");
		String newPassword=request.getParameter("newPassword");
		String pwdMD5=systemService.getSysUser(Long.parseLong(uid)).getPassword();
		try {
			if(PasswordUtil.getMD5Str(orginalPassword).equals(pwdMD5)) {
				return "true";
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "false";
		}
		return "false";
	}
	
	
		
}
