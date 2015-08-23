package com.xyc.proj.dao.statistic.impl;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xyc.proj.dao.base.MyBatisBaseDao;
import com.xyc.proj.dao.statistic.SysUserDao;
import com.xyc.proj.entity.SysUser;
import com.xyc.proj.utility.PageView;

@Component
public class SysUserDaoImpl<T extends com.xyc.proj.entity.SysUser> extends MyBatisBaseDao<SysUser> implements SysUserDao{
	
	@Autowired
	public SysUserDaoImpl(DataSource dataSource) {
		super(dataSource);
	}
	
	public List getFuncNodeList(SysUser user) {
		return super.getMapList(user,"getFuncNodeListByUser");
	}

	@Override
	public PageView findSysUser(Map paramMap) {
		return super.getPageList(paramMap);
	}
	
	
}
