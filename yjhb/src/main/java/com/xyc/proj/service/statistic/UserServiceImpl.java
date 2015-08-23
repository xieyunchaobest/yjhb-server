/**
 * All rights, including trade secret rights, reserved.
 */
package com.xyc.proj.service.statistic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyc.proj.dao.statistic.AdInfoDao;
import com.xyc.proj.dao.statistic.UserDao;
import com.xyc.proj.entity.SysUser;
import com.xyc.proj.entity.statistic.TreeNode;
import com.xyc.proj.utility.PageView;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AdInfoDao adInfoDao;
	
//	public User getUser(long id){
//		System.out.println("-----------getUserId:" + id + "[service start]");
//		return userDao.get(Long.valueOf(id).intValue());
//	}
//	
//	public void save(String sonySelectId){
//		User user = new User();
//		user.setSonySelectId(sonySelectId);
//		userDao.save(user);
//	}
	
	public Long getCount(){
		return userDao.getCount();
	}
	
	public PageView getMapList(Map<String, Object> map){
		return userDao.getPageList(map);
	}
	
//	@Transactional
//	public void saveTestTransaction(){
//		AdInfo adInfo = new AdInfo();
//		adInfo.setName("testdata");
//		adInfo.setExpiryDate("2015-07-17");
//		adInfoDao.save(adInfo);
//		
//		User user = new User();
//		user.setSonySelectId("testData7.17");
//		userDao.save(user);
//	}
	
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
				
				TreeNode tree=new TreeNode();
				tree.setId(id);
				tree.setName(name);
				tree.setPid(pid);
				tree.setDepth(depth);
				
				treeList.add(tree);
			}
		}
		return treeList;
	}
	public List getFuncNodeList(SysUser user){
		List funcNodeList= userDao.getFuncNodeList(user);
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
}
