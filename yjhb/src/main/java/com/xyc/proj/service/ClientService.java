package com.xyc.proj.service;

import java.util.List;

import com.xyc.proj.entity.Order;
import com.xyc.proj.entity.User;
import com.xyc.proj.entity.Version;

public interface ClientService {

	void saveUser(User u );
	
	List getUserListByMobileNoAndAuthCode(String mobileNo,String authCode);
	
	List findStoreBySts();
	
	List findByTradeTypeAndSts(String tradeType,String sts);
	
	List findCarBySts(String sts);
	
	List findOrderByMobleAndState(String mobile,String sts);
	
	public void updateStoreStatistic(Long storeId) ;
	
	 Version findVersionByPfType(String pfType);
	 
	 void createOrder(Order o);
	 
	 void updateOrder(Order o);
}
