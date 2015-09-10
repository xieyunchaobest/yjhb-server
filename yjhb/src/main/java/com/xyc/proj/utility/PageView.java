package com.xyc.proj.utility;

import java.util.List;
import java.util.Map;

public class PageView {
	private long totalRecord = 0;
	private int totalPageCnt = 0;
	private int pageShowCnt = 0;//page Size
	private int currentMaxCnt = 0; 
	private int currentPageNum = 0;
	private List<?> resultList = null;
	private long start = 0;
	
	public int getCurrentMaxCnt() {
		currentMaxCnt = pageShowCnt * currentPageNum;
		return currentMaxCnt;
	}

	
	
	
	public PageView(int currentPageNum){
		this(currentPageNum, 10);
	}
	
	public int getPageShowCnt() {
		return pageShowCnt;
	}

	public PageView(int currentPageNum, int pageShowCnt){
		this.currentPageNum = currentPageNum;
		if(currentPageNum == 0){
			this.currentPageNum = 1;
		}
		this.pageShowCnt = pageShowCnt;
	}

	
	public long getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
	}

	public List<?> getResultList() {
		return resultList;
	}

	public void setResultList(List<?> resultList) {
		this.resultList = resultList;
	}


	public int getTotalPageCnt() {
		double tmp = (double)totalRecord / pageShowCnt;
		//System.out.println("float tmp :" + tmp);
		totalPageCnt = (int) Math.ceil(tmp);
		return totalPageCnt;
	}

	public long getStart() {
		
		if(currentPageNum == 0){
			currentPageNum = 1;
		}		
		start = (currentPageNum - 1) * pageShowCnt;
		
		return start;
	}
	
	
	
	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public static void main(String[] str){
		PageView page = new PageView(1);
		
		page.setTotalRecord(132);
		System.out.println("start:" + page.getStart());
		System.out.println("total page count :" + page.getTotalPageCnt());
	}
}
