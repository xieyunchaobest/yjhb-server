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

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xyc.proj.utility.CustomDateSerializer;

@Entity
@Table(name = "T_STORE")
public class Store {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "STORE_NAME")
	private String storeName;
	
	@Column(name = "ADDRESS")
	private String address; 
	
	
	@Column(name = "LONGITUDE")
	private Double longitude;
	
	@Column(name = "LATITUDE")
	private Double latitude;
	
	@Column(name = "IS_OPEN")
	private String isOpen;
	
	@Column(name = "STS")
	private String sts="A";
	
	
	 
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME") 
	private Date createTime=new Date();
	 
	@Column(name = "BUS_ROUTE")
	private String busRoute;
	
	

	public Store() {
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getStoreName() {
		return storeName;
	}


	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Double getLongitude() {
		return longitude;
	}


	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}


	public Double getLatitude() {
		return latitude;
	}


	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}


	public String getSts() {
		return sts;
	}


	public void setSts(String sts) {
		this.sts = sts;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getBusRoute() {
		return busRoute;
	}


	public void setBusRoute(String busRoute) {
		this.busRoute = busRoute;
	}


	public String getIsOpen() {
		return isOpen;
	}


	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}


 
 

 
	 
}
