package com.xyc.proj.dao.statistic;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xyc.proj.dao.OrderDao;
import com.xyc.proj.dao.base.MyBatisBaseDao;
import com.xyc.proj.entity.Order;
import com.xyc.proj.entity.SysUser;
import com.xyc.proj.utility.PageView;

@Component
public class OrderDaoImpl<T extends com.xyc.proj.entity.Order> extends MyBatisBaseDao<Order> implements OrderDao{
	
	@Autowired
	public OrderDaoImpl(DataSource dataSource) {
		super(dataSource);
	}
	
	 
	
	  public PageView getOrderPage(Map<String, Object> paramMap) {
	        return getEntityPageList(paramMap, "getOrderPageCount",
	                "getOrderPage");
	    }
 
	
}
