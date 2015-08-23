package com.xyc.proj.utility;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 一个处理泛型继承类的泛型参数的工具类
 * @author tongshulian
 * @since 2015.7.14
 *
 */
public class GenericsUtils {
	/**   
	      * 通过反射,获得定义Class时声明的父类的范型参数的类型.   
	      * 如public BookManager extends GenricManager<Book>   
	      *   
	      * @param clazz The class to introspect   
	      * @return the first generic declaration, or <code>Object.class</code> if cannot be determined   
	      */  
	     public static Class<?> getActualReflectArgumentClass(Class<?> clazz) {  
	         return getActualReflectArgumentClass(clazz, 0);  
	     }  
	   
	     /**   
	      * 通过反射,获得定义Class时声明的父类的范型参数的类型.   
	      * 如public BookManager extends GenricManager<Book>   
	      *   
	      * @param clazz clazz The class to introspect   
	      * @param index the Index of the generic ddeclaration,start from 0.   
	      */  
	     public static Class<?> getActualReflectArgumentClass(Class<?> clazz, int index) throws IndexOutOfBoundsException {  
	   
	         Type genType = clazz.getGenericSuperclass();  
	         System.out.println("--------------Generic Super class type:" + genType.toString());
	   
	         if (!(genType instanceof ParameterizedType)) { 
	        	 if(index == 1){
	        		 return genType.getClass();
	        	 }
	        	 
	        	 return getActualReflectArgumentClass(genType.getClass(),index + 1);
	         }  
	   
	         Type[] params = ((ParameterizedType) genType).getActualTypeArguments();  
	   
	         if (index >= params.length || index < 0) {  
	             return Object.class;  
	         }  
	         if (!(params[index] instanceof Class)) {  
	             return Object.class;  
	         }  
	         return (Class<?>) params[index];  
	     }  

}
