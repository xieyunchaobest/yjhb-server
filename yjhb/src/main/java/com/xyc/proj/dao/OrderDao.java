package com.xyc.proj.dao;

import java.util.List;
import java.util.Map;

import com.xyc.proj.entity.SysUser;
import com.xyc.proj.utility.PageView;

public interface OrderDao{

	  public PageView getOrderPage(Map<String, Object> paramMap);
}
