package com.xyc.proj.service;

import java.util.List;

import com.xyc.proj.entity.User;

public interface ClientService {

	void saveUser(User u );
	
	List getUserListByMobileNo(String mobileNo);
}
