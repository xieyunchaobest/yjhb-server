package com.xyc.proj.entity.manage;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xyc.proj.utility.CustomDateSerializer;

@Entity
@Table(name = "pn_banner")
public class Banner {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "name")
	private String name;
	@Column(name = "icon")
	private String icon;
	@Column(name = "bannericon")
	private String bannericon;
	@Column(name = "type")
	private int type;
	@Column(name = "contentid")
	private String contentid;
	
	@Column(name = "updatetime")
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date updateTime;

	@Column(name = "platformtype")
	private String platformtype;
	
	public Banner() {
	}

	public Banner(Banner banner) {
		this.id = banner.getId();
		this.name = banner.getName();
		this.icon = banner.getIcon();
		this.bannericon = banner.getBannericon();
		this.type = banner.getType();
		this.contentid = banner.getContentid();
		this.updateTime = banner.getUpdateTime();
		this.platformtype = banner.getPlatformtype();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getBannericon() {
		return bannericon;
	}

	public void setBannericon(String bannericon) {
		this.bannericon = bannericon;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContentid() {
		return contentid;
	}

	public void setContentid(String contentid) {
		this.contentid = contentid;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getPlatformtype() {
		return platformtype;
	}

	public void setPlatformtype(String platformtype) {
		this.platformtype = platformtype;
	}
	
}
