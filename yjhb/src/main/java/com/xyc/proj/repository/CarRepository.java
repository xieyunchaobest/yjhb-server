package com.xyc.proj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xyc.proj.entity.ElectricCar;



public interface CarRepository extends JpaRepository<ElectricCar, Long> {

	List findByTradeTypeAndSts(String tradeType,String sts);
	
	List findBySts(String sts);
}
