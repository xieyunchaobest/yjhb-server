package com.xyc.proj.dao.base;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;

import com.xyc.proj.global.Constants;
import com.xyc.proj.utility.GenericsUtils;
import com.xyc.proj.utility.MapResultHandler;
import com.xyc.proj.utility.MyBatisUtil;
import com.xyc.proj.utility.PageView;

public class MyBatisBaseDao<T>{
    private Class<T> type;
    private SqlSession session;
    
    @SuppressWarnings("unchecked")
	public MyBatisBaseDao(DataSource dataSource){
		type = (Class<T>) GenericsUtils.getActualReflectArgumentClass(this.getClass());
		System.out.println("------------- BaseMybatisDao initialize--------------------------");
		System.out.println("------------- T:" + type.toString());
		try {
			MyBatisUtil myBatisUtil = MyBatisUtil.getInstance(dataSource);
			session = myBatisUtil.getSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
    private String getMethodPath(String methodType){
        return getMethodPath(methodType, "");
    }
    
    private String getMethodPath(String methodType, String methodSuffix){
        return Constants.MYBATIS_MAPPER_PRIX + methodType + type.getSimpleName() + methodSuffix;
    }
    
    public void save(T obj) {
        session.insert(getMethodPath("save"), obj);
    }

    public void delete(T obj) {
        session.delete(getMethodPath("delete"), obj);
    }

    public void update(T obj) {
        session.update(getMethodPath("update"), obj);
        //HashMap<String,Object> map = null;
    }

    public T get(Integer id) {
        return session.selectOne(getMethodPath("get"),id);
    }
    
    public List<T> getList(T entity){
    	return session.selectList(getMethodPath("get", "List"), entity);
    }
    
    public List<T> getListByAnyObject(Object entity){
    	return session.selectList(getMethodPath("get", "List"), entity);
    }
    
    /**
     * 
     * @param entity
     * @param selectId：mapper。xml文件中<select>标签ID
     * @return
     */
    public List<T> getList(T entity, String selectId){
    	return session.selectList(selectId, entity);
    }
    
    public List<T> getListByAnyObject(Object entity, String selectId){
    	return session.selectList(selectId, entity);
    }
    
    public List<Map<String, Object>> getMapList(Map<String, Object> map){
    	MapResultHandler mh = new MapResultHandler();
    	session.select(getMethodPath("get", "MapList"), map, mh);
    	return mh.getMappedResults();
    }
    
    /**
     * 
     * @param map
     * @param selectId：mapper。xml文件中<select>标签ID
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getMapList(Map<String, Object> map, String selectId){
    	MapResultHandler mh = new MapResultHandler();
    	session.select(selectId, map, mh);
    	return mh.getMappedResults();
    }
    
    public List<Map<String, Object>> getMapList(T entity){
    	MapResultHandler mh = new MapResultHandler();
    	session.select(getMethodPath("get", "MapList"), entity, mh);
    	return mh.getMappedResults();
    }

    public List<Map<String, Object>> getMapList(T entity,String queryName){
    	MapResultHandler mh = new MapResultHandler();
    	session.select(queryName, entity, mh);
    	return mh.getMappedResults();
    }

    public Long getCount(Map<String, Object> pm){
    	MapResultHandler mh = new MapResultHandler();
    	session.select(getMethodPath("get", "Count"),pm, mh);
    	return mh.getCount();
    }
    
    /**
     * 
     * @param pm
     * @param selectId：mapper。xml文件中<select>标签ID
     * @return Long
     */
    public Long getCount(Map<String,Object> pm, String selectId){
    	MapResultHandler mh = new MapResultHandler();
    	session.select(selectId,pm, mh);
    	return mh.getCount();
    }
    
    /**
     * map 中必须包含 key:currentPageNum 且其值不能为空, 页面显示的记录数不是10必须包含key:pageShowCnt
     * 且其值不能为空
     * @param map
     * @return PageView
     */
    public PageView getPageList(Map<String, Object> map){
    	
    	if(map == null || map.get("currentPageNum") == null){
    		return null;
    	} else{
    		PageView page = null;
    		Integer pageNum = Integer.valueOf(map.get("currentPageNum").toString());
    		if(map.get("pageShowCnt") == null){
    			page = new PageView(pageNum);
    		} else {
    			Integer showCnt = Integer.valueOf(map.get("pageShowCnt").toString());
    			page = new PageView(pageNum, showCnt);
    		}
    		map.put("start", page.getStart());
    		map.put("end", page.getCurrentMaxCnt());
    		//System.out.println("-----------start:" + map.get("start"));
    		//System.out.println("-----------start:" + map.get("maxCnt"));
    		MapResultHandler mh = new MapResultHandler();
    		page.setTotalRecord(this.getCount(map));
        	session.select(getMethodPath("get", "MapPageList"), map, mh);
        	page.setResultList(mh.getMappedResults());
        	
        	return page;
    	}
    }
    
    /**
     * map 中必须包含 key:currentPageNum 且其值不能为空, 页面显示的记录数不是10必须包含key:pageShowCnt
     * 且其值不能为空
     * @param map
     * @param selectConutId, mapper.xml文件中<select>标签Id, 查询总记录数的sql语句
     * @param selectPageListId, mapper.xml文件中<select>标签Id,查询分页后数据列表的sql语句
     * @return
     */
    public PageView getPageList(Map<String, Object> map, String selectConutId, String selectPageListId){
    	
    	if(map == null || map.get("currentPageNum") == null){
    		return null;
    	} else{
    		PageView page = null;
    		Integer pageNum = Integer.valueOf(map.get("currentPageNum").toString());
    		if(map.get("pageShowCnt") == null){
    			page = new PageView(pageNum);
    		} else {
    			Integer showCnt = Integer.valueOf(map.get("pageShowCnt").toString());
    			page = new PageView(pageNum, showCnt);
    		}
    		map.put("start", page.getStart());
    		map.put("end", page.getCurrentMaxCnt());
    		//System.out.println("-----------start:" + map.get("start"));
    		//System.out.println("-----------start:" + map.get("maxCnt"));
    		MapResultHandler mh = new MapResultHandler();
    		page.setTotalRecord(this.getCount(map, selectConutId));
        	session.select(selectPageListId, map, mh);
        	page.setResultList(mh.getMappedResults());
        	
        	return page;
    	}
    }
    
    /**
     * map 中必须包含 key:currentPageNum 且其值不能为空, 页面显示的记录数不是10必须包含key:pageShowCnt
     * 且其值不能为空
     * @param map
     * @param selectConutId, mapper.xml文件中<select>标签Id, 查询总记录数的sql语句
     * @param selectPageListId, mapper.xml文件中<select>标签Id,查询分页后数据列表的sql语句
     * @return
     */
    public PageView getEntityPageList(Map<String, Object> map, String selectConutId, String selectPageListId){
    	
    	if(map == null || map.get("currentPageNum") == null){
    		return null;
    	} else{
    		PageView page = null;
    		Integer pageNum = Integer.valueOf(map.get("currentPageNum").toString());
    		if(map.get("pageShowCnt") == null){
    			page = new PageView(pageNum);
    		} else {
    			Integer showCnt = Integer.valueOf(map.get("pageShowCnt").toString());
    			page = new PageView(pageNum, showCnt);
    		}
    		map.put("start", page.getStart());
    		map.put("end", page.getCurrentMaxCnt());
    		//System.out.println("-----------start:" + map.get("start"));
    		//System.out.println("-----------start:" + map.get("maxCnt"));
    		page.setTotalRecord(this.getCount(map, selectConutId));
        	page.setResultList(session.selectList(selectPageListId, map));
        	
        	return page;
    	}
    }
    
    /**
     * map 中必须包含 key:currentPageNum 且其值不能为空, 页面显示的记录数不是10必须包含key:pageShowCnt
     * 且其值不能为空
     * @param map
     * @return PageView
     */
    public PageView getEntityPageList(Map<String, Object> map){
    	
    	if(map == null || map.get("currentPageNum") == null){
    		return null;
    	} else{
    		PageView page = null;
    		Integer pageNum = Integer.valueOf(map.get("currentPageNum").toString());
    		if(map.get("pageShowCnt") == null){
    			page = new PageView(pageNum);
    		} else {
    			Integer showCnt = Integer.valueOf(map.get("pageShowCnt").toString());
    			page = new PageView(pageNum, showCnt);
    		}
    		map.put("start", page.getStart());
    		map.put("end", page.getCurrentMaxCnt());
    		//System.out.println("-----------start:" + map.get("start"));
    		//System.out.println("-----------start:" + map.get("maxCnt"));
    		page.setTotalRecord(this.getCount(map));
        	page.setResultList(session.selectList(getMethodPath("get", "PageList"), map));
        	
        	return page;
    	}
    }
    
    protected SqlSession getSession(){
        return session;
    }
}
