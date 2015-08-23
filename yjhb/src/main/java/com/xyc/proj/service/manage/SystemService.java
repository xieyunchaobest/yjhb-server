package com.xyc.proj.service.manage;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import com.xyc.proj.entity.SysUser;
import com.xyc.proj.utility.PageView;

public interface SystemService {
	public List getFuncNodeList(SysUser user);
	public SysUser updateSysUser(SysUser user)  throws NoSuchAlgorithmException;
	
	public SysUser getSysUser(Long id);
	public PageView findSysUser(Map paramMap);
	
	public List getSysRoles();
	
	public Long getSysRoleByUserId(long userId);
	
	public void deleteSysUser(Long userId);
	
	public SysUser login(SysUser user)  throws NoSuchAlgorithmException ;
	public void updateSysRoleAndUser(SysUser user,Long roleId)  throws NoSuchAlgorithmException;
}
