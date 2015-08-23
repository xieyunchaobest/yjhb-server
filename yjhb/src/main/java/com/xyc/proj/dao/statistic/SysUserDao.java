package com.xyc.proj.dao.statistic;

import java.util.List;
import java.util.Map;

import com.xyc.proj.entity.SysUser;
import com.xyc.proj.utility.PageView;

public interface SysUserDao {
	public List getFuncNodeList(SysUser user);
	
	public PageView findSysUser(Map paramMap);
	
}
