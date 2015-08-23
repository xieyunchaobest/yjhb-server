/**
 * All rights, including trade secret rights, reserved.
 */
package com.xyc.proj.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xyc.proj.entity.manage.Banner;
import com.xyc.proj.service.manage.BannerService;
import com.xyc.proj.service.statistic.UserService;
import com.xyc.proj.utility.PageView;


@Controller
public class TestController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private BannerService bannerService;
	
	@RequestMapping(value={"/index.html"})
	public String index(HttpServletRequest request, Model model){
//		User user = userService.getUser(1);
//		if(user != null){
//			System.out.println("{user.id:" + user.getId() + ",user.selectId:" + user.getSonySelectId() + "}");
//		}
//		Long count = userService.getCount();
//		if(count != null){
//			System.out.println("----------user count : " + count);
//		}
//		List<Map<String, Object>> maps = userService.getMapList();
//		if(maps != null){
//			Map<String, Object> map = null;
//			Iterator<String> it = null;
//			String key = null;
//			for(int i = 0; i < 3; i++){
//				map = maps.get(i);
//				it =map.keySet().iterator();
//				System.out.print("---userMap[" + i + "]:{");
//				while(it != null && it.hasNext()){
//					key = it.next();
//					System.out.print(key + ":" + map.get(key)  + ",");
//				}
//				System.out.println("}");
//			}
//		}
//		Banner banner = bannerService.findBannerById(Long.valueOf(39));
//		if(banner != null){
//			System.out.println("{banner.id:" + banner.getId()
//						+ ",banner.name:" + banner.getName()
//						+ ",banner.icon:" + banner.getIcon()
//						+ ",banner.bannericon:" + banner.getBannericon()
//						+ "}");
//		}
//		userService.saveTestTransaction();
		Map<String, Object> map = new HashMap<String,Object>();
		String pageNum = request.getParameter("currentPageNum");
		if(pageNum == null || "".equals(pageNum)){
			pageNum = "1";
		}
		map.put("currentPageNum", Integer.valueOf(pageNum));
		PageView page= userService.getMapList(map);
		//System.out.println("-----------currentPageNum:" + map.get("currentPageNum"));
		//System.out.println("-----------totalPageCnt:" + page.getTotalPageCnt());
		model.addAttribute("currentPageNum", pageNum);
		model.addAttribute("orderByStr", request.getParameter("orderByStr"));
		System.out.println("-----------orderByStr:" + request.getParameter("orderByStr"));
		model.addAttribute("totalPageCnt", page.getTotalPageCnt());
		model.addAttribute("maps", page.getResultList());
		return "index";
	}
	
	@RequestMapping("/saveBanner")
	public String saveBanner(HttpServletRequest request){
		Banner banner = new Banner();
		banner.setName(request.getParameter("bannerName"));
		banner.setIcon(request.getParameter("icon"));
		banner.setBannericon(request.getParameter("bannericon"));
		bannerService.saveBanner(banner);
		return "index";
	}
	
	@RequestMapping("/saveInfo")
	public String saveInfo(@RequestParam String sonySelectId){
		if(sonySelectId == null || sonySelectId.equals("")){
			//throw new NullPointerException("please input a value to sonySelectId !!!!");
			sonySelectId = "test00000";
		}
		
		for(int i=0; i<100; i++){
			sonySelectId += i;
//			userService.save(sonySelectId);
		}
		
		return "index";
	}	
	
	@RequestMapping("/invalidPage")
	public String error(){
		System.out.println("------------com in error");
		return "invalidPage";
	}
	
	@RequestMapping("/jqueryValidation")
	public String jqueryValidation(){
		return "jqueryValidationDome";
	}
}
