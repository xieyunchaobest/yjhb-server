/**
 * All rights, including trade secret rights, reserved.
 */
package com.xyc.proj.dao.statistic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xyc.proj.dao.base.MyBatisBaseDao;
import com.xyc.proj.entity.SysUser;
import com.xyc.proj.utility.PageView;

@Component
public class UserDaoImpl<T extends com.xyc.proj.entity.SysUser> extends MyBatisBaseDao<SysUser> implements UserDao {
	
	@Autowired
	public UserDaoImpl(DataSource dataSource) {
		super(dataSource);
	}

//	@Override
//	public User get(Integer id) {
//		System.out.println("-----------getUserId:" + id + "[dao start]");
//		User user = null;
//		try{
//			user = super.get(id);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return user;
//	}
//
//	@Override
//	public void save(User user) {
//		// TODO Auto-generated method stub
//		super.save(user);
//	}
//
//	@Override
//	public void delete(com.sonychina.backend.entity.statistic.User obj) {
//		// TODO Auto-generated method stub
//		
//	}
	
	public Long getCount(){
		return super.getCount(new HashMap());
	}
	
	public PageView getPageList(Map<String, Object> map){
		return super.getPageList(map);
	}
	
	public List getFuncNodeList(SysUser user) {
		return super.getMapList(user,"getFuncNodeListByUser");
	}
}
