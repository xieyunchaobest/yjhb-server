package com.xyc.proj.dao.statistic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xyc.proj.dao.StoreDao;
import com.xyc.proj.dao.base.MyBatisBaseDao;
import com.xyc.proj.entity.Store;

@Component
public class StoreDaoImpl<T extends com.xyc.proj.entity.Order> extends MyBatisBaseDao<Store> implements StoreDao{
	
	@Autowired
	public StoreDaoImpl(DataSource dataSource) {
		super(dataSource);
	}
	
 
	public List getStoreStat() {
		return super.getMapList(new HashMap(), "statStore");
	}
 
	
}
