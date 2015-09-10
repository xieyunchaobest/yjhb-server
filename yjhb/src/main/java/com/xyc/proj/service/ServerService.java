package com.xyc.proj.service;

import java.util.List;
import java.util.Map;

import com.xyc.proj.entity.Config;
import com.xyc.proj.entity.ElectricCar;
import com.xyc.proj.entity.Store;
import com.xyc.proj.entity.WebUser;
import com.xyc.proj.utility.PageView;

public interface ServerService {
	public PageView getOrderPage(Map<String, Object> paramMap);

	public void saveStore(Store store);

	public Store getStore(Long storeId);

	public void deleteStore(Store store);

	public List statStore();

	ElectricCar getCar(Long carId);
	
	void saveCar(ElectricCar car);
	
	void deleteCar(ElectricCar car);
	
	Config getConfig(Long configId);
	
	void saveConfig(Config config);
	
	WebUser getWebUser(String uname,String pwd);
}
