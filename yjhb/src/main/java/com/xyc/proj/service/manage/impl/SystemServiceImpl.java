package com.xyc.proj.service.manage.impl;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyc.proj.dao.statistic.SysUserDao;
import com.xyc.proj.entity.SysUser;
import com.xyc.proj.entity.manage.SysRoleUser;
import com.xyc.proj.entity.statistic.TreeNode;
import com.xyc.proj.repository.system.SysRoleRepository;
import com.xyc.proj.repository.system.SysRoleUserRepository;
import com.xyc.proj.repository.system.SysUserRepository;
import com.xyc.proj.service.manage.SystemService;
import com.xyc.proj.utility.PageView;
import com.xyc.proj.utility.PasswordUtil;

@Service
public class SystemServiceImpl implements SystemService{
	
	@Autowired
	private SysUserDao sysUserDao;
	
	@Autowired
	private SysUserRepository sysUserRepository;
	@Autowired
	private SysRoleRepository sysRoleRepository;
	@Autowired
	SysRoleUserRepository sysRoleUserRepository;
	
	private List getTreeNodeList(List funcNodeList) {
		List treeList=new ArrayList();
		if(funcNodeList!=null) {
			for(int i=0;i<funcNodeList.size();i++) {
				Map m=(Map)funcNodeList.get(i);
				Integer id=(Integer)m.get("id");
				String name=(String)m.get("name");
				Integer seq=(Integer)m.get("seq");
				Integer pid=(Integer)m.get("pid");
				String type=(String)m.get("type");
				Integer depth=(Integer)m.get("depth");
				String code=(String)m.get("code");
				String url=(String)m.get("url");
				
				TreeNode tree=new TreeNode();
				tree.setId(id);
				tree.setName(name);
				tree.setPid(pid);
				tree.setDepth(depth);
				tree.setUrl(url);
				
				treeList.add(tree);
			}
		}
		return treeList;
	}
	public List getFuncNodeList(SysUser user){
		List funcNodeList= sysUserDao.getFuncNodeList(user);
		List treeList=getTreeNodeList(funcNodeList);
		for(int i=0;i<treeList.size();i++) {
			TreeNode tree=(TreeNode)treeList.get(i);
			setSubMenu(tree,treeList);
		}
		
		List resList=new ArrayList();
		for(int i=0;i<treeList.size();i++) {
			TreeNode t=(TreeNode)treeList.get(i);
			if(t.getDepth()==1) {
				resList.add(t);
			}
		}
		
		return resList;
	}
	
	public void setSubMenu(TreeNode pnode,List treeNodeList) {
		List children=new ArrayList();
		if(treeNodeList!=null) {
			for(int i=0;i<treeNodeList.size();i++) {
				TreeNode tree=(TreeNode)treeNodeList.get(i);
				int pid=tree.getPid();
				if(pnode.getId()==pid) {
					children.add(tree);
					pnode.setChildren(children);
				}
			}
		}
	}
	
	
	@Override
	public SysUser updateSysUser(SysUser user) throws NoSuchAlgorithmException {
		String pwd=PasswordUtil.getMD5Str(user.getPassword());
		user.setPassword(pwd);
		SysUser u=sysUserRepository.save(user);
		return u;
	}
	
	
	public void updateSysRoleAndUser(SysUser user,Long roleId) throws NoSuchAlgorithmException {
		updateSysUser(user);
		updateSysRoleUser(user,roleId);
	}
	public void updateSysRoleUser(SysUser user,Long roleId) {
		if(user!=null && user.getId()>0) {
			Long uid=user.getId();
			SysRoleUser sru=sysRoleUserRepository.findByUserId(uid);
			if(sru!=null) {
				sysRoleUserRepository.delete(sru);
			}
			SysRoleUser ru=new SysRoleUser();
			ru.setRoleId(roleId);
			ru.setUserId(uid);
			sysRoleUserRepository.save(ru);
		}
		
	}
	
	@Override
	public SysUser getSysUser(Long id) {
		SysUser user=sysUserRepository.findOne(id);
		return user;
	}
	
	@Override
	public PageView findSysUser(Map paramMap) {
		return sysUserDao.findSysUser(paramMap);
	}
	
	@Override
	public List getSysRoles() {
		return sysRoleRepository.findAll();
	}
	
	public Long getSysRoleByUserId(long userId) {
		return sysRoleRepository.getSysRoleByUserId(userId);
	}
	
	public void deleteSysUser(Long userId) {
		sysUserRepository.delete(userId);
	}
	
	public SysUser login(SysUser user) throws NoSuchAlgorithmException {
		String password=PasswordUtil.getMD5Str(user.getPassword());
		user.setPassword(password);
		SysUser u=sysUserRepository.findByUserNameAndPassword(user.getUserName(),user.getPassword());
		return u;
	}
	
}
