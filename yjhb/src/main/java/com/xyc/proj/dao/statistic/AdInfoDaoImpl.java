package com.xyc.proj.dao.statistic;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xyc.proj.dao.base.MyBatisBaseDao;
import com.xyc.proj.entity.statistic.AdInfo;

@Component
public class AdInfoDaoImpl<T extends AdInfo> extends MyBatisBaseDao<AdInfo> implements AdInfoDao{

	@Autowired
	public AdInfoDaoImpl(DataSource dataSource) {
		super(dataSource);
	}
	
	public void save(AdInfo adInfo){
		super.save(adInfo);
	}
	
}
