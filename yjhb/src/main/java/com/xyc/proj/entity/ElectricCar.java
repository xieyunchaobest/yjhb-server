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
@Table(name = "T_ELECTRIC_CAR")
public class ElectricCar {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	 
	
	@Column(name = "MODEL")
	private String model;  
	
	@Column(name = "TRADE_TYPE")
	private String tradeType;
	
	@Column(name = "KM_COUNT")
	private Integer kmCount;
	
	@Column(name = "PRICE")
	private Double price=0d;
	
	@Column(name = "PRICE_M")
	private Double priceM=0d;
	
	@Column(name = "PRICE_E")
	private Double priceE=0d;
	
	@Column(name = "TOP_PRICE")
	private Double topPrice=0d;
	
	
	@Column(name = "STS")
	private String sts="A";
	 
	@Column(name = "IMG_ADDR")
	private String imgAddr;
	
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME") 
	private Date createTime=new Date();

	public ElectricCar() {
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}

 

	public String getTradeType() {
		return tradeType;
	}


	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
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


	public Integer getKmCount() {
		return kmCount;
	}


	public void setKmCount(Integer kmCount) {
		this.kmCount = kmCount;
	}


	public String getImgAddr() {
		return imgAddr;
	}


	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}


	public Double getPriceM() {
		return priceM;
	}


	public void setPriceM(Double priceM) {
		this.priceM = priceM;
	}


	public Double getPriceE() {
		return priceE;
	}


	public void setPriceE(Double priceE) {
		this.priceE = priceE;
	}


	public Double getTopPrice() {
		return topPrice;
	}


	public void setTopPrice(Double topPrice) {
		this.topPrice = topPrice;
	}

 

 
	 
}
