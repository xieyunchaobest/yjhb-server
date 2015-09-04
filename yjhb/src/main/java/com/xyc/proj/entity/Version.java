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
@Table(name = "T_VERSION")
public class Version {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "VERSION_NUM")
	private String versionNum;
	
	@Column(name = "PUBLISH_PATH")
	private String publishPath;
	
	@Column(name = "VERSION_DESC")
	private String versionDesc;
	
	@Column(name = "PF_TYPE")
	private String pfType;
	
	@Column(name = "REMARKS")
	private String remarks;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PUBLISH_DATE") 
	private Date publishDate;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

 

	public String getVersionNum() {
		return versionNum;
	}


	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}


	public String getPublishPath() {
		return publishPath;
	}


	public void setPublishPath(String publishPath) {
		this.publishPath = publishPath;
	}


	public String getVersionDesc() {
		return versionDesc;
	}


	public void setVersionDesc(String versionDesc) {
		this.versionDesc = versionDesc;
	}


	public String getPfType() {
		return pfType;
	}


	public void setPfType(String pfType) {
		this.pfType = pfType;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public Date getPublishDate() {
		return publishDate;
	}


	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	 
	
	
}
