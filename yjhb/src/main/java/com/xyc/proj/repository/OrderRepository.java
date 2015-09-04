package com.xyc.proj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xyc.proj.entity.Order;



public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("select o,e,s from Order o,ElectricCar e ,Store s where o.carId=e.id and o.storeId=s.id and o.mobileNo=:mobileNo and o.state=:sts")
	List findByMobileAndSts(@Param("mobileNo")  String mobileNo,@Param("sts")  String sts);
	 
	Order findByOutTradeNo(String outTradeNo);
}
