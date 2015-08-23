/**
 * All rights, including trade secret rights, reserved.
 */
package com.xyc.proj.entity.manage;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xyc.proj.utility.CustomDateSerializer;

@Entity
@Table(name = "pn_artist",catalog="PlayNow",schema="dbo")
public class Artist {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "introduction")
	private String introduction;
	
	@Column(name = "iconurl")
	private String iconUrl;
	
	@Column(name = "isdeleted")
	@JsonIgnore
	private boolean deleted;
	
	@Version
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedtime")
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date updatedTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdtime", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date createdTime;
	 

	public Artist() {
	}

	public Artist(Artist artist) {
		this.id = artist.getId();
		this.name = artist.getName();
		this.introduction = artist.getIntroduction();
		this.iconUrl = artist.getIconUrl();
		this.deleted = artist.isDeleted();
		this.updatedTime = artist.getUpdatedTime();
		this.createdTime = artist.getCreatedTime();
	}

	public Artist(String name, String iconUrl) {
		this.name = name;
		this.iconUrl = iconUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public long getId() {
		return id;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	 
}
