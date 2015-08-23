package com.xyc.proj.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TestUrlInterceptor implements HandlerInterceptor {
	
	public TestUrlInterceptor(){
		System.out.println("--------------- TestUrlInterceptor initialize -------------");
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		
//		if(request.getRequestURI().equals("/error")){
//			System.out.println("-------------- input error path ----------------");
//			//request.getRequestDispatcher("/invalidPage");
//			response.sendRedirect("/invalidPage");
//		}
//		System.out.println("-------------- TestUrlInterceptor work -----------------" + request.getRequestURI());
//		
		return true;
	}
	
	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		System.out.println("-------------- TestUrlInterceptor post url -----------------");
	}
	
	/**
	 * This implementation is empty.
	 */
	@Override
	public void afterCompletion(
			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("-------------- TestUrlInterceptor completion -----------------");
		if(ex != null){
			throw ex;
		}
	}
}
