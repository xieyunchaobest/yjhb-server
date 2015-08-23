/**
 * All rights, including trade secret rights, reserved.
 */
package com.xyc.proj.utility;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogHelper {
	
	public static final String PAGE_HOME = "首页";
	public static final String PAGE_MYMUSIC = "我的音乐";
	public static final String PAGE_CART = "购物车";
	public static final String PAGE_ORDER_CONFIRM = "订单确认";
	public static final String PAGE_PAY_ERROR = "支付问题";
	public static final String PAGE_ALBUMS = "所有专辑";
	public static final String PAGE_ARTIST = "艺术家详情";
	public static final String PAGE_ALBUM = "专辑详情";
	
	
	public static final String EXTRAS_KEY_ALBUMCATEGORY = "AlbumCategory";
	public static final String EXTRAS_KEY_ORDERRULE = "OrderRule";
	public static final String EXTRAS_KEY_PAGENUM = "PageNum";
	public static final String EXTRAS_KEY_ARTIST = "Artist";
	public static final String EXTRAS_KEY_ALBUM = "Album";
	public static final String EXTRAS_KEY_REFERRER = "Referrer";
	
//	public static final String EXTRAS_KEY_TAB = "Tab";
	
	private static final String TYPE_PV = "pvtype";
	private static final String TYPE_DL = "dltype";
	private static final String TYPE_PL = "pytype";
	private static final Logger s_PVLogger = LoggerFactory.getLogger(TYPE_PV);
	private static final Logger s_DLLogger = LoggerFactory.getLogger(TYPE_DL);
	private static final Logger s_PLLogger = LoggerFactory.getLogger(TYPE_PL);
	public static void writePvLog(String page) {
		s_PVLogger.info("PageName=" + page);
	}

	public static void writePvLog(String page, Map<String, String> extras) {
		StringBuilder sb = new StringBuilder();
		for (String key : extras.keySet()) {
			if (sb.length() > 0) {
				sb.append(",");
			}
			sb.append(key);
			sb.append("=");
			sb.append(extras.get(key));
		}
		String logInfo = sb.length() <= 0 ? "PageName=" + page : "PageName=" + page + "," + sb.toString();
		s_PVLogger.info(logInfo);
	}

	public static void writeDownloadLog(long trackId) {
		s_DLLogger.info("TrackID=" + trackId);
	}
	
	public static void writePlayerLog(String info) {
		s_PLLogger.info(info);
	}
}
