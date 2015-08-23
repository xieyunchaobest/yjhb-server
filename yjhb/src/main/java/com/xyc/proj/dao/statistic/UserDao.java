package com.xyc.proj.dao.statistic;

import java.util.List;
import java.util.Map;

import com.xyc.proj.entity.SysUser;
import com.xyc.proj.utility.PageView;

public interface UserDao{
//	public User get(Integer id);
//	
//	public void save(User user);
//	
//	public void delete(User obj);
	
	public Long getCount();
	
	public PageView getPageList(Map<String, Object> map);
	
	public List getFuncNodeList(SysUser user);
}
