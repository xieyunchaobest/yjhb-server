package com.xyc.proj.global;

/**
 * 一个全局的字符串资源映射入口
 * @author tongshulian
 * @since 2015.7.14
 *
 */
public interface Constants {
	
	// baseDao中获取mybatis配置文件中mapper的查询语句id的前缀
	public static final String MYBATIS_MAPPER_PRIX = "com.xyc.proj.";
	
	// mybatis配置文件路径
	String MYBATIS_CONFIG_FILE_PATH = "classpath:/mappers/*.xml";
	public static final String MENU_MANAGE = "manage";
	
	
	public static final String MENU_QUERY = "query";
	
	//UPDATE OR ADD
	public static final String OPT_FLAG_ADD="A";
	public static final String OPT_FLAG_UPDATE="U";
	
	public static final int DEFAULT_PAGE_SIZE=15;
	
	public static final String ORDERBY="orderBys";
	public static final String CURRENT_PAGENUM="currentPageNum";
	
}
