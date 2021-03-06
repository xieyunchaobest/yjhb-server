/**
 * All rights, including trade secret rights, reserved.
 */
package com.xyc.proj.controller;

import java.io.File;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xyc.proj.entity.Config;
import com.xyc.proj.entity.ElectricCar;
import com.xyc.proj.entity.Question;
import com.xyc.proj.entity.Store;
import com.xyc.proj.entity.WebUser;
import com.xyc.proj.global.Constants;
import com.xyc.proj.service.ClientService;
import com.xyc.proj.service.ServerService;
import com.xyc.proj.utility.CharacterEncodingFilter;
import com.xyc.proj.utility.DateUtil;
import com.xyc.proj.utility.PageView;
import com.xyc.proj.utility.Properties;
import com.xyc.proj.utility.Result;
import com.xyc.proj.utility.StringUtil;

@Controller
public class WebController {

	@Autowired
	private ServerService serverService;
	@Autowired
	private ClientService clientService;
	
	@Autowired
	Properties properties;  
	
	@RequestMapping("/index.html")
	public String index(Model model,HttpSession Session,HttpServletRequest request) {
		return "index";
	}
	
	@RequestMapping("/login.html")
	public String loginInit(Model model,HttpSession Session,HttpServletRequest request) {
		return "login";
	}
	
	
	@RequestMapping("/login")
	public String login(Model model,HttpSession Session,HttpServletRequest request) {
		String userName=request.getParameter("name");
		String password=request.getParameter("password");
		WebUser u=serverService.getWebUser(userName, password);
		if(u==null) {
			model.addAttribute("error", "用户名或密码错误！");
			return "forward:/login.html";
		}else {
			
		}
		return "redirect:/index.html";
	}
	
	
	@RequestMapping("/server/queryOrder")
	public String queryOrder(Model model,HttpSession Session,HttpServletRequest request,
			 @RequestParam(value = "startTime", required = false) String startTime,
	            @RequestParam(value = "endTime", required = false) String endTime,
			  @RequestParam(value = "currentPageNum", required = false, defaultValue = "1") Integer currentPageNum) {
		Map parmMap=new HashMap();
		if(StringUtil.isBlank(startTime) && StringUtil.isBlank(endTime)) {
        	startTime=endTime=DateUtil.getYesterday();
        }
		parmMap.put("startTime", startTime);
		parmMap.put("endTime", endTime);
		parmMap.put(Constants.CURRENT_PAGENUM, currentPageNum);
		PageView pv=serverService.getOrderPage(parmMap);
		List list=pv.getResultList();
		if(list!=null) {
			for(int i=0;i<list.size();i++) {
				Map m=(Map)list.get(i);
				if(!m.containsKey("PAY_TIME")) {
					m.put("PAY_TIME", null);
				}
				if(!m.containsKey("TRADE_NO")) {
					m.put("TRADE_NO", null);
				}
				if(!m.containsKey("ADDRESS")) {
					m.put("ADDRESS", null);
				}
			}
		}
	    model.addAttribute("pageView", pv);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		return "queryOrder";
	}
	
	
	@RequestMapping("/server/queryStore")
	public String queryStore(Model model,HttpSession Session,HttpServletRequest request) {
		List storeList=clientService.findStoreBySts();
		model.addAttribute("storeList", storeList);
		return "queryStore";
	}
	
	@RequestMapping("/server/updateStoreInit")
	public String updateStoreInt(Model model,HttpSession Session,HttpServletRequest request,
			@RequestParam(value = "storeId", required = false) String storeId ) {
		Long sid=Long.parseLong(storeId);
		Store store=new Store();
		if (sid != null && sid > 0l) {
			store = serverService.getStore(sid);
		}
		model.addAttribute("store",store);
		return "updateStore";
	}
	
	@RequestMapping("/server/updateStore")
	@ResponseBody
	public Result updateStore(Model model,HttpSession Session,HttpServletRequest request,
			@ModelAttribute Store store,
			@RequestParam(value = "storeId", required = false) Long storeId ) {
		Result result=new   Result();
		try {
			if(storeId!=null && storeId>0l) {
				store.setId(storeId);
			}
			serverService.saveStore(store);
		}catch( Exception e){
			result.resultCode=0;
			e.printStackTrace();
		}
		
	  return result;
	}

	@RequestMapping(value ="/server/deleteStore",method = {RequestMethod.POST})
	public String deleteStore( Model model,
			@RequestParam(value = "storeId", required = false) Long storeId,
			HttpServletRequest request,
			@ModelAttribute Store store) {
		store.setId(storeId);
		serverService.deleteStore(store);
		return "forward:/server/queryStore";
	}
	
	@RequestMapping(value ="/server/storeStat",method = {RequestMethod.GET})
	public String storeStat( Model model,
			HttpServletRequest request ) {
		List statList=serverService.statStore();
		model.addAttribute("statList", statList);
		return "statStore";
	}
	
	@RequestMapping(value ="/server/queryCar",method = {RequestMethod.GET,RequestMethod.POST})
	public String queryCar( Model model,
			HttpServletRequest request ) {
		List carList=clientService.findCarBySts("A");
		model.addAttribute("carList", carList);
		return "queryCar";
	}
	
	
	
	
	@RequestMapping("/server/updateCarInit")
	public String updateCarInt(Model model,HttpSession Session,HttpServletRequest request,
			@RequestParam(value = "carId", required = false) String carId ) {
		Long sid=Long.parseLong(carId);
		ElectricCar car=new ElectricCar();
		if (sid != null && sid > 0l) {
			car = serverService.getCar(sid);
		}
		model.addAttribute("car",car);
		return "updateCar";
	}
	
	@RequestMapping(value="/server/updateCar", method = {RequestMethod.POST})
	public String updateCar(Model model,HttpSession Session,HttpServletRequest request,
			@RequestParam(value = "afile", required = false) MultipartFile file,
			@RequestParam(value = "tradeType", required = false) String tradeType,
			@RequestParam(value = "model", required = false) String md,
			@RequestParam(value = "price", required = false) String price,
			@RequestParam(value = "kmCount", required = false) String kmCount,
			@RequestParam(value = "carId", required = false) Long carId,
			@RequestParam(value = "priceM", required = false) Double priceM,
			@RequestParam(value = "priceE", required = false) Double priceE,
			@RequestParam(value = "topPrice", required = false) Double topPrice) {
		String fileName=String.valueOf(new Date().getTime());
		Result result=new   Result();
		String fname=file.getOriginalFilename();
		String fileEnd="";
		String imgpath=properties.getFileuploadpath();
		if(!StringUtil.isBlank(fname)) {
			fileEnd=fname.substring(fname.indexOf("."));
		}
		try {
		ElectricCar car=new ElectricCar();
		 
			if(carId!=null && carId>0l) {
				car=serverService.getCar(carId);
				String imgName=car.getImgAddr();
				
				if(!StringUtil.isBlank(fileEnd)) {
					File targetFile = new File(imgpath, imgName);
					file.transferTo(targetFile);
				}
			}else {
				String fillwholename=fileName+fileEnd;
				File targetFile = new File(imgpath, fillwholename);
				file.transferTo(targetFile);
				car.setImgAddr(fillwholename);
			}
			car.setModel(md);
			car.setTradeType(tradeType);
			car.setPrice(Double.parseDouble(price));
			car.setKmCount(Integer.parseInt(kmCount));
			
			car.setPriceE(priceE);
			car.setPriceM(priceM);
			car.setTopPrice(topPrice);
			serverService.saveCar(car);
		}catch( Exception e){
			result.resultCode=0;
			e.printStackTrace();
		}
		
	  
		return "redirect:/server/queryCar";
	}
	
	
	@RequestMapping(value ="/server/deleteCar",method = {RequestMethod.POST})
	public String deleteStore( Model model,
			@RequestParam(value = "carId", required = false) Long carId,
			HttpServletRequest request,
			@ModelAttribute ElectricCar car) {
		car.setId(carId);
		serverService.deleteCar(car);
		return "redirect:/server/queryCar";
	}
	
	
	@RequestMapping(value ="/server/showConfig",method = {RequestMethod.GET, RequestMethod.POST})
	public String showConfig( Model model, 
			HttpServletRequest request) {
		Config c1=serverService.getConfig(1l);
		Config c2=serverService.getConfig(2l);
		Config c3=serverService.getConfig(3l);
		Config c4=serverService.getConfig(4l);
		model.addAttribute("sxf", c1.getConfigValue());
		model.addAttribute("ydhcf", c2.getConfigValue());
		model.addAttribute("zwfjx", c3.getConfigValue());
		model.addAttribute("zcsjkd", c4.getConfigValue());
		return "configsetting";
	}
	
	@RequestMapping(value ="/server/updateConfig",method = {RequestMethod.GET, RequestMethod.POST})
	public String updateConfig( Model model, 
			HttpServletRequest request) {
		String sxf=request.getParameter("sxf");
		String ydhcf=request.getParameter("ydhcf");
		String zwfjx=request.getParameter("zwfjx");
		String zcsjkd=request.getParameter("zcsjkd");
		
		Config cc1=serverService.getConfig(1l);
		cc1.setConfigValue(sxf);
		
		Config cc2=serverService.getConfig(2l);
		cc2.setConfigValue(ydhcf);
		
		Config cc3=serverService.getConfig(3l);
		cc3.setConfigValue(zwfjx);
		
		Config cc4=serverService.getConfig(4l);
		cc4.setConfigValue(zcsjkd);
		
		serverService.saveConfig(cc1);
		serverService.saveConfig(cc2);
		serverService.saveConfig(cc3);
		serverService.saveConfig(cc4);
		
		
		return "redirect:/server/showConfig";
	}
	
	
	@RequestMapping(value ="/server/desc",method = {RequestMethod.GET, RequestMethod.POST})
	public String desc( Model model, 
			HttpServletRequest request) {
		return "descript";
	}
	
	
	
	@RequestMapping(value ="/test",method = {RequestMethod.GET, RequestMethod.POST})
	public String test( Model model, 
			HttpServletRequest request) {
		return "test";
	}
	
	@RequestMapping(value ="/server/question",method = {RequestMethod.GET, RequestMethod.POST})
	public String showQuestion( Model model, 
			HttpServletRequest request) {
		Result result=new Result();
		Question question=clientService.getQuestion();
		model.addAttribute("questions", question);
		result.result=question;
		return "question";
	}
	
	@RequestMapping(value ="/server/updateQuestion",method = {RequestMethod.GET, RequestMethod.POST})
	public String updateQuestion( Model model, 
			HttpServletRequest request) {
		String qid=request.getParameter("qid");
		String contentsString=request.getParameter("contents");
		Question q=new Question();
		q.setId(Long.parseLong(qid));
		q.setContents(contentsString);
		clientService.updateQuesion(q);
		return "redirect:/server/question";
	}
	
	
	@Bean
    public FilterRegistrationBean encodingFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CharacterEncodingFilter());
        registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        return registration;
    }

}
