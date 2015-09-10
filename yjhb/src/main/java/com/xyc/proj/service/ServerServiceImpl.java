package com.xyc.proj.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyc.proj.dao.OrderDao;
import com.xyc.proj.dao.StoreDao;
import com.xyc.proj.entity.Config;
import com.xyc.proj.entity.ElectricCar;
import com.xyc.proj.entity.Store;
import com.xyc.proj.entity.WebUser;
import com.xyc.proj.repository.CarRepository;
import com.xyc.proj.repository.ConfigRepository;
import com.xyc.proj.repository.StoreRepository;
import com.xyc.proj.repository.WebUserRepository;
import com.xyc.proj.utility.PageView;

@Service
public class ServerServiceImpl implements ServerService {
	@Autowired
	OrderDao orderDao;
	@Autowired
	StoreRepository storeRepository;
	
	@Autowired
	StoreDao storeDao;
	
	@Autowired
    CarRepository carRepository;
	
	@Autowired
    ConfigRepository configRepository;
	
	@Autowired
	WebUserRepository webUserRepository;
	
	@Override
	  public PageView getOrderPage(Map<String, Object> paramMap) {
		PageView pgView = orderDao.getOrderPage(paramMap);
		return pgView;
	}

	public void saveStore(Store store) {
		storeRepository.save(store);
	}

	public Store getStore(Long storeId) {
		return storeRepository.findOne(storeId);
	}
	
	 public void deleteStore(Store store) {
		 Store s=storeRepository.findOne(store.getId());
		 s.setSts("P");
		 storeRepository.save(s);
	 }
	 
	 public List statStore() {
		 return storeDao.getStoreStat();
	 }
	 
	 public  ElectricCar getCar(Long carId) {
		 return carRepository.findOne(carId);
	 }
	 
	 
	public  void saveCar(ElectricCar car) {
		carRepository.save(car);
	 }
	 
	
	public void deleteCar(ElectricCar car) {
		carRepository.delete(car);
	}
	
	
	public Config getConfig(Long configId) {
		return configRepository.findOne(configId);
	}
	
	
	public void saveConfig(Config config) {
		 configRepository.save(config);
	}
	
	
	
	public WebUser getWebUser(String uname,String pwd) {
		return webUserRepository.findByUserNameAndPassword(uname, pwd);
	}
}
