/**
 * All rights, including trade secret rights, reserved.
 */
package com.xyc.proj.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_STORE_CAR")
public class StoreCar {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "STORE_ID")
	private Long storeId;  
	
	@Column(name = "CAR_ID")
	private Long carId;
	
	
	@Column(name = "CAR_AMOUNT")
	private Long carAmount; 
	 
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME") 
	private Date createTime;

	public StoreCar() {
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

  

	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Long getStoreId() {
		return storeId;
	}


	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}


	public Long getCarId() {
		return carId;
	}


	public void setCarId(Long carId) {
		this.carId = carId;
	}


	public Long getCarAmount() {
		return carAmount;
	}


	public void setCarAmount(Long carAmount) {
		this.carAmount = carAmount;
	}


	 

 
	 
}
