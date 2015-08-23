package com.xyc.proj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyc.proj.entity.User;
import com.xyc.proj.repository.UserRepository;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    UserRepository userRepository;

	@Override
	public void saveUser(User u) {
		userRepository.save(u);
	}
 
	@Override
	public List getUserListByMobileNo(String mobileNo) {
		return userRepository.findUserByMobile(mobileNo);
	}
}
