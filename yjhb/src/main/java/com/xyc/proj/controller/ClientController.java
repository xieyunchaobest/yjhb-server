/**
 * All rights, including trade secret rights, reserved.
 */
package com.xyc.proj.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.xyc.proj.apliay.AlipayNotify;
import com.xyc.proj.entity.Order;
import com.xyc.proj.entity.Question;
import com.xyc.proj.entity.User;
import com.xyc.proj.entity.Version;
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
		//mobileNo="18611298927";
		String randomCode=String.valueOf((int)(Math.random()*9000+1000));
		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init(properties.getSmsurl(), properties.getSmsport());// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount(properties.getSmsaccountId(), properties.getSmsaccountToken());// 初始化主帐号名称和主帐号令牌
		restAPI.setAppId(properties.getSmsappid());// 初始化应用ID
		String templeId=properties.getSmstemplateId();
		result = restAPI.sendTemplateSMS(mobileNo, templeId,new String[]{randomCode});
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
				res.result="获取校验码失败！";
			}
			
		}else {
			res.resultCode=0;
			res.result="获取校验码失败！";
		} 
		return res;
	}

	@RequestMapping("/client/getLatestUserByMobile")
	@ResponseBody 
	public Result getLatestUserByMobile( 
	        Model model,HttpSession Session,HttpServletRequest request) {
		com.alibaba.fastjson.JSONObject json=Tools.getJSON(request);
		String mobileNo=StringUtil.getStringInJson(json,"mobileNo");
		String authCode=StringUtil.getStringInJson(json,"authCode");
		//mobileNo="18611298927";
		Result res=new Result();
		List ulist=clientService.getUserListByMobileNoAndAuthCode(mobileNo,authCode);
		if(ulist!=null && ulist.size()>0) {
			User u=(User)ulist.get(0);
			res.result=u;
		}else {
			res.resultCode=0;
			res.result="登录失败，请检查输入！";
		}
		
		return res;
	}
	
	
	@RequestMapping("/client/getLocalCache")
	@ResponseBody 
	public Result getLocalCache( 
	        Model model,HttpSession Session,HttpServletRequest request) {
		Result res=new Result();
		List storeList=null;
		Map resMap=new HashMap();
		List carList=null;
		List configList=null;
		try {
			storeList=clientService.findStoreBySts();
			configList=clientService.getConfigList();
			//carList=clientService.findCarBySts("A");
			resMap.put("storeList", storeList);
			resMap.put("configList", configList);
			//resMap.put("carList", carList);
		}catch(Exception e) {
			e.printStackTrace();
			res.resultCode=0;
		}
		
		res.result=resMap;
		return res;
	}
	
	
	@RequestMapping("/client/getCarsByStore")
	@ResponseBody 
	public Result getCarsByStore( 
	        Model model,HttpSession Session,HttpServletRequest request) {
		Result res=new Result();
		List carList=null;
		com.alibaba.fastjson.JSONObject json=Tools.getJSON(request);
		String storeId=json.getString("storeId");
		String tradeType=json.getString("tradeType");
		if("3".equals(tradeType) ||"2".equals(tradeType)) {
			tradeType="R";
		}else if("4".equals(tradeType)) {
			tradeType="B";
		}
		try {
			carList=clientService.findByTradeTypeAndSts(tradeType,"A");
			res.result=carList;
		}catch(Exception e) {
			e.printStackTrace();
			res.resultCode=0;
		}
		
		return res;
	}
	
	@RequestMapping("/client/getOrderList")
	@ResponseBody 
	public Result findOrderByMobleAndState( 
	        Model model,HttpSession Session,HttpServletRequest request) {
		com.alibaba.fastjson.JSONObject json=Tools.getJSON(request);
		String mobileNo=json.getString("mobileNo");
		Result res=new Result();
		List orderList=null;
		List carList=null;
		try {
			orderList=clientService.findOrderByMobleAndState(mobileNo, "A");
		}catch(Exception e) {
			e.printStackTrace();
			res.resultCode=0;
		}
		res.result=orderList;
		return res;
	
	}
	
	
	@RequestMapping("/client/getStoreStatInfo")
	@ResponseBody 
	public Result getStoreStatInfo( 
	        Model model,HttpSession Session,HttpServletRequest request) {
		com.alibaba.fastjson.JSONObject json=Tools.getJSON(request);
		String storeId=json.getString("storeId");
		Result res=new Result();
		List orderList=null;
		List carList=null;
		try {
			clientService.updateStoreStatistic(Long.parseLong(storeId));
		}catch(Exception e) {
			e.printStackTrace();
			res.resultCode=0;
		}
		res.result=orderList;
		return res;
	}
	
	@RequestMapping("/client/checkVersion")
	@ResponseBody 
	public Result findVersionByPfType( 
	        Model model,HttpSession Session,HttpServletRequest request) {
		com.alibaba.fastjson.JSONObject json=Tools.getJSON(request);
		String pfType=json.getString("pfType");
		Result res=new Result();
		try {
			Version v=clientService.findVersionByPfType(pfType);
			res.result=v;
		}catch(Exception e) {
			e.printStackTrace();
			res.resultCode=0;
		}
		return res;
	}
	
	@RequestMapping("/client/alipayGateway")
	@ResponseBody 
	public Result alipayGateway( 
	        Model model,HttpSession Session,HttpServletRequest request) {
		Result res=new Result();
		StringBuffer sb = new StringBuffer("");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(ServletInputStream) request.getInputStream(), "UTF-8"));
			
			String temp;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			br.close();
			System.out.println("info================================="+sb);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}
	
 
	
	@RequestMapping(value="/client/alipayNotify", method = RequestMethod.POST)
	public void alipayNotify( 
	        Model model,HttpSession Session,HttpServletRequest request,
	        HttpServletResponse response) {
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
				System.out.println(name+"=="+valueStr);
			}
			
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			try {
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); 
			}
			params.put(name, valueStr);
		}
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号
		String out_trade_no="";
		//支付宝交易号
		String trade_no="" ;
		//交易状态
		String trade_status="";
		try {
			out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("out_trade_no:"+out_trade_no);
		System.out.println("trade_no:"+trade_no);
		System.out.println("trade_status:"+trade_status);

		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		PrintWriter out=null;
		 try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Order o=new Order();
		o.setOutTradeNo(out_trade_no);
		o.setTradeNo(trade_no);
		clientService.updateOrder(o);
		out.print("success");
//		if(AlipayNotify.verify(params)){//验证成功
//			//////////////////////////////////////////////////////////////////////////////////////////
//			//请在这里加上商户的业务逻辑程序代码
//			Order o=new Order();
//			o.setOutTradeNo(out_trade_no);
//			o.setTradeNo(trade_no);
//			clientService.updateOrder(o);
//			System.out.println("verify:success");
//
//			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
//			
//			//判断是否在商户网站中已经做过了这次通知返回的处理
//				//如果没有做过处理，那么执行商户的业务程序
//				//如果有做过处理，那么不执行商户的业务程序
//			 return "success"; //请不要修改或删除
//			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
//			//////////////////////////////////////////////////////////////////////////////////////////
//		}else{//验证失败
//			System.out.println("verify:failer");
//			 return "fail"; 
//			//out.println("fail");
//		}
		
	}
	
	@RequestMapping("/client/createOrder")
	@ResponseBody 
	public Result createOrder( 
	        Model model,HttpSession Session,HttpServletRequest request) {
		Result res=new Result();
		try {
			Order o=new Order();
			com.alibaba.fastjson.JSONObject json=Tools.getJSON(request);
			
			String tradeType=json.getString("tradeType");
			String outTradeNo=json.getString("out_trade_no");
			String mobileNo=json.getString("mobileNo");
			Integer carId=json.getInteger("carId");
			Integer storeId=json.getInteger("storeId");
			Double totalFee=json.getDouble("totalFee");
			String carModel=json.getString("carModel");
			String getStoreName=json.getString("getStoreName");
			String rentTime=json.getString("rentTime");
			String returnStoreName=json.getString("returnStoreName");
			String returnTime=json.getString("returnTime");
			Double sxf=json.getDouble("sxf");
			Double syf=json.getDouble("syf");
			Double sinfee=json.getDouble("sinfee");
			Integer useTime=json.getInteger("useTime");
			Double ydhcf=json.getDouble("ydhcf");
			String  address=json.getString("address");
			String  uname=json.getString("uname");
			Order order=clientService.findOrderByOutTradeNo(outTradeNo);
			if(order==null || order.getId()<=0) {
				o.setOutTradeNo(outTradeNo);
				o.setMobileNo(mobileNo);;
				o.setCarId(carId);
				o.setStoreId(storeId);
				o.setTotalFee(totalFee);
				o.setTradeType(tradeType);
				o.setState("A");
				o.setCarModel(carModel);
				o.setGetStoreName(getStoreName);
				o.setRentTime(rentTime);
				o.setReturnStoreName(returnStoreName);
				o.setReturnTime(returnTime);
				o.setSxf(sxf);
				o.setSinFee(sinfee);
				o.setUseTime(useTime);
				o.setYdhcf(ydhcf);
				o.setAddress(address);
				o.setSyf(syf);
				o.setUname(uname);
				clientService.createOrder(o);
			}else {
				
			}
			
			
		}catch(Exception e) {
			res.resultCode=0;
			res.result="创建订单失败";
			e.printStackTrace();
		}
	
		return res;
	}
	
	@RequestMapping("/client/getOrderInfo")
	@ResponseBody 
	public Result getOrderInfo( 
	        Model model,HttpSession Session,HttpServletRequest request) {
		com.alibaba.fastjson.JSONObject json=Tools.getJSON(request);
		String orderId=json.getString("orderId");
		Result res=new Result();
		try {
			Order o=clientService.getOrderInfo(Long.parseLong(orderId));
			res.result=o;
		}catch(Exception e) {
			res.resultCode=0;
		}
		
		return res;
	}
	
	
	@RequestMapping(value ="/client/showQuestion",method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody 
	public Result showQuestion( Model model, 
			HttpServletRequest request) {
		Result result=new Result();
		Question question=clientService.getQuestion();
		model.addAttribute("questions", question);
		result.result=question;
		return result;
	}
	
	
}
