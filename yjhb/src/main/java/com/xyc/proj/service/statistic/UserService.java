/**
 * All rights, including trade secret rights, reserved.
 */
package com.xyc.proj.service.statistic;

import java.util.List;
import java.util.Map;

import com.xyc.proj.entity.SysUser;
import com.xyc.proj.utility.PageView;

public interface UserService {
//	public User getUser(long id);
//	
	
	public Long getCount();
	
	public PageView getMapList(Map<String, Object> map);
	
//	public void saveTestTransaction();
	
	public List getFuncNodeList(SysUser user);
}
