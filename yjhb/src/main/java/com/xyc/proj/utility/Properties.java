package com.xyc.proj.utility;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "config")
public class Properties {
	private String smsurl;
	private String smsport;
	private String smsaccountId;
	private String smsaccountToken;
	private String smsappid;
	private String smstemplateId;
	
	
	private String fileuploadpath;
	private String serveraddress;
	
	public String getSmsurl() {
		return smsurl;
	}
	public void setSmsurl(String smsurl) {
		this.smsurl = smsurl;
	}
	public String getSmsport() {
		return smsport;
	}
	public void setSmsport(String smsport) {
		this.smsport = smsport;
	}
	public String getSmsaccountId() {
		return smsaccountId;
	}
	public void setSmsaccountId(String smsaccountId) {
		this.smsaccountId = smsaccountId;
	}
	public String getSmsaccountToken() {
		return smsaccountToken;
	}
	public void setSmsaccountToken(String smsaccountToken) {
		this.smsaccountToken = smsaccountToken;
	}
	public String getSmsappid() {
		return smsappid;
	}
	public void setSmsappid(String smsappid) {
		this.smsappid = smsappid;
	}
	public String getSmstemplateId() {
		return smstemplateId;
	}
	public void setSmstemplateId(String smstemplateId) {
		this.smstemplateId = smstemplateId;
	}
	public String getFileuploadpath() {
		return fileuploadpath;
	}
	public void setFileuploadpath(String fileuploadpath) {
		this.fileuploadpath = fileuploadpath;
	}
	public String getServeraddress() {
		return serveraddress;
	}
	public void setServeraddress(String serveraddress) {
		this.serveraddress = serveraddress;
	}
	
	
	

}
