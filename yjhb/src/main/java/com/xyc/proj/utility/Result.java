/**
 * All rights, including trade secret rights, reserved.
 */

package com.xyc.proj.utility;

import java.util.List;

public class Result<T> {
	public int resultCode = 1; // 0失败1成功 
	public T result;// 结果
}

class ListResult<T> {
	public int resultCode = 1; // 0失败1成功
	public List<T> resultList;// 结果列表
}

class PageResult<T> extends ListResult<T> {
	public long totalCount = 0; // 记录总数
}

