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
	private String tradeNo="";
	
	@Column(name = "OUT_TRADE_NO")
	private String outTradeNo="";
	
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
	private Integer carId;
	
	@Column(name = "STORE_ID")
	private Integer storeId;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME") 
	private Date createdTime=new Date();
	 
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PAY_TIME") 
	private Date payTime;
	
	
	@Column(name = "CAR_MODEL")
	private String carModel;
	
	
	@Column(name = "GET_STORE_NAME")
	private String getStoreName;
	
	@Column(name = "RENT_TIME")
	private String rentTime;
	
	@Column(name = "RETURN_STORE_NAME")
	private String returnStoreName;
	
	@Column(name = "RETURN_TIME")
	private String returnTime;
	
	@Column(name = "SXF")
	private Double sxf;
	
	@Column(name = "SIN_FEE")
	private Double sinFee;
	
	@Column(name = "YDHCF")
	private Double ydhcf;
	
	@Column(name = "USE_TIME")
	private Integer useTime;
	
	@Column(name = "ADDRESS")
	private String address;
	

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
		return DateUtil.Time2Str(payTime);
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


	public Integer getCarId() {
		return carId;
	}


	public void setCarId(Integer carId) {
		this.carId = carId;
	}


	public Integer getStoreId() {
		return storeId;
	}


	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}


	 

	public String getCarModel() {
		return carModel;
	}


	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}


	public String getGetStoreName() {
		return getStoreName;
	}


	public void setGetStoreName(String getStoreName) {
		this.getStoreName = getStoreName;
	}


	public String getRentTime() {
		return rentTime;
	}


	public void setRentTime(String rentTime) {
		this.rentTime = rentTime;
	}


	public String getReturnStoreName() {
		return returnStoreName;
	}


	public void setReturnStoreName(String returnStoreName) {
		this.returnStoreName = returnStoreName;
	}


	public String getReturnTime() {
		return returnTime;
	}


	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}


	public Double getSxf() {
		return sxf;
	}


	public void setSxf(Double sxf) {
		this.sxf = sxf;
	}


 


	public Double getSinFee() {
		return sinFee;
	}


	public void setSinFee(Double sinFee) {
		this.sinFee = sinFee;
	}


	public Integer getUseTime() {
		return useTime;
	}


	public void setUseTime(Integer useTime) {
		this.useTime = useTime;
	}


	public Double getYdhcf() {
		return ydhcf;
	}


	public void setYdhcf(Double ydhcf) {
		this.ydhcf = ydhcf;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}

 
	 
}
