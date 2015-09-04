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
@Table(name = "T_STORE_STAT")
public class StoreStat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "STORE_ID")
	private Long storeId;
	
	@Column(name = "CLICK_COUNT")
	private Long clickCount; 
	
 
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_TIME") 
	private Date updateTime=new Date(); 

	public StoreStat() {
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


 

	public Long getStoreId() {
		return storeId;
	}


	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}


	public Long getClickCount() {
		return clickCount;
	}


	public void setClickCount(Long clickCount) {
		this.clickCount = clickCount;
	}


	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

 
	 
}
