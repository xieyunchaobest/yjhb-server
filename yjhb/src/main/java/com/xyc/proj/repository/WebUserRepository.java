package com.xyc.proj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xyc.proj.entity.WebUser;



public interface WebUserRepository extends JpaRepository<WebUser, Long> {

	WebUser findByUserNameAndPassword(String uname,String pwd);
	 
}
