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

import com.xyc.proj.utility.DateUtil;


@Entity
@Table(name = "T_ORDER")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "TRADE_NO")
	private String tradeNo;
	
	@Column(name = "OUT_TRADE_NO")
	private String outTradeNo;
	
	@Column(name = "MOBILE_NO")
	private String mobileNo;
	
	@Column(name = "TOTAL_FEE")
	private Double totalFee;
	
	@Column(name = "DISCOUNT")
	private Integer discount;
	
	@Column(name = "STATE")
	private String state;
	
	
	@Column(name = "TRADE_TYPE")
	private String tradeType;
	
	@Column(name = "CAR_ID")
	private Long carId;
	
	@Column(name = "STORE_ID")
	private Long storeId;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME") 
	private Date createdTime=new Date();
	 
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PAY_TIME") 
	private Date payTime;
	

	public Order() {
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getMobileNo() {
		return mobileNo;
	}


	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}


	public Date getCreatedTime() {
		return createdTime;
	}


	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}


 

	public String getOutTradeNo() {
		return outTradeNo;
	}


	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}


	public String getTradeNo() {
		return tradeNo;
	}


	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

 

	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getTradeType() {
		return tradeType;
	}


	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}


	public String getPayTime() {
		return DateUtil.date2Str(payTime);
	}


	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}


	public Double getTotalFee() {
		return totalFee;
	}


	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}


	public Integer getDiscount() {
		return discount;
	}


	public void setDiscount(Integer discount) {
		this.discount = discount;
	}


	public Long getCarId() {
		return carId;
	}


	public void setCarId(Long carId) {
		this.carId = carId;
	}


	public Long getStoreId() {
		return storeId;
	}


	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

 
	 
}
