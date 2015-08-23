package com.xyc.proj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xyc.proj.entity.User;



public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u from User u where u.mobileNo = ?1 order by createdTime desc") 
	List findUserByMobile(String mobileNo);
}
