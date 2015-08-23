package com.xyc.proj.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

/**
 * 一个处理MyBatis的sql查询返回结果类型的handler类
 * @author tongshulian
 * @since 2015.7.14
 *
 */
public class MapResultHandler implements ResultHandler{
	private final List<Map<String, Object>> mappedResults = new ArrayList<Map<String, Object>>(); 
	private Long count = new Long(0);
	           
    @Override  
    public void handleResult(ResultContext context) { 
    	try{
    		@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) context.getResultObject();   
    		mappedResults.add(map);
    	} catch(Exception e){
    		count = Long.valueOf(context.getResultObject().toString());
    	}
    }   
       
    public List<Map<String, Object>> getMappedResults() {   
        return mappedResults;   
    }   

    public Long getCount(){
    	return count;
    }
}
