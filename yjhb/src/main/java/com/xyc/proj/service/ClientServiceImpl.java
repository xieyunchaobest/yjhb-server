package com.xyc.proj.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyc.proj.entity.ElectricCar;
import com.xyc.proj.entity.Order;
import com.xyc.proj.entity.Store;
import com.xyc.proj.entity.StoreStat;
import com.xyc.proj.entity.User;
import com.xyc.proj.entity.Version;
import com.xyc.proj.repository.CarRepository;
import com.xyc.proj.repository.ConfigRepository;
import com.xyc.proj.repository.OrderRepository;
import com.xyc.proj.repository.StoreRepository;
import com.xyc.proj.repository.StoreStatRepository;
import com.xyc.proj.repository.UserRepository;
import com.xyc.proj.repository.VersionRepository;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    StoreRepository storeRepository;
    @Autowired
    CarRepository carRepository;

    @Autowired
    OrderRepository orderRepository;
    
    @Autowired
    StoreStatRepository storeStatRepository;
    
    @Autowired
    VersionRepository versionRepository;
    
	
	@Autowired
    ConfigRepository configRepository;
    
    
	@Override
	public void saveUser(User u) {
		userRepository.save(u);
	}
 
	@Override
	public List getUserListByMobileNoAndAuthCode(String mobileNo,String authCode) {
		return userRepository.findUserByMobileAndAuthCode(mobileNo, authCode);
	}
	
	public List findStoreBySts() {
		List storeList=storeRepository.findBySts("A");
		return storeList;
	}
	
	public List findByTradeTypeAndSts(String tradeType,String sts) {
		return carRepository.findByTradeTypeAndSts(tradeType,sts);
	}
	
	public List findCarBySts(String sts) {
		return carRepository.findBySts(sts);
	}
	
	public List findOrderByMobleAndState(String mobile,String sts) {
		List orderList=orderRepository.findByMobileAndSts(mobile, sts);
		List resList=new ArrayList();
		if(orderList!=null && orderList.size()>0) {
			for(int i=0;i<orderList.size();i++) {
				Object[] obj=(Object[])orderList.get(i);
				Order o=(Order)obj[0];
				ElectricCar car=(ElectricCar)obj[1];
				Store store=(Store)obj[2];
				Map m=new HashMap();
				m.put("item_img", car.getImgAddr());
				m.put("item_model", car.getModel());
				m.put("item_date", o.getPayTime());
				m.put("item_mdmc", store.getStoreName());
				m.put("item_fee", o.getTotalFee());
				m.put("item_id", o.getId());
				resList.add(m);
			}
		}
		return resList;
	}
	
	public void updateStoreStatistic(Long storeId) {
		StoreStat store=storeStatRepository.findOne(storeId);
		if(store==null ||store.getId()==0) {
			StoreStat ss=new StoreStat();
			ss.setClickCount(1l);
			ss.setStoreId(storeId);
			storeStatRepository.save(ss);
		}else {
			Long clickCount=store.getClickCount();
			store.setClickCount(clickCount+1l);
			storeStatRepository.save(store);
		}
	}
	
	
	public Version findVersionByPfType(String pfType) {
		return versionRepository.findByPfType(pfType);
	}
	
	public void createOrder(Order o) {
		orderRepository.save(o);
	 }
	
	public void updateOrder(Order order) {
		Order o=orderRepository.findByOutTradeNo(order.getOutTradeNo());
		o.setPayTime(new Date());
		o.setTradeNo(order.getTradeNo());
		orderRepository.save(o);
	}
	
	 public List getConfigList() {
		 return configRepository.findAll();
	 }
	 
	 
	public Order getOrderInfo(Long id) {
		 return orderRepository.findOne(id);
	 }
	
}
