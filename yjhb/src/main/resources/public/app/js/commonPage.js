/** 使用 
	 1. form的id必须以orderByForm开头如：orderByForm1,orderByFormUser等
	 2。分页必须有以下标签
	 	<nav id="tslCommon-Page">
		  <input type="hidden" name="currentPageNum" th:value="${currentPageNum}"/>
		  <input type="hidden" name="totalPageCnt" th:value="${totalPageCnt}"/>
		  <input type="hidden" id="totalRecord" th:value="${totalRecord}"/>
		</nav>
	3。支持分页后台必须有：currentPageNum和totalPageCnt的处理如：
		model.addAttribute("currentPageNum", pageNum);
		model.addAttribute("totalPageCnt", page.getTotalPageCnt());
		model.addAttribute("totalPageCnt", page.getTotalPageCnt());
	4。分页后台可以获取两个属性currentPageNum和totalPageCnt的值
		request.getParameter("currentPageNum")(###默认分页查询第一次时，其值为空，请注意处理###)
		request.getParameter("totalPageCnt")(###默认分页查询第一次时，其值为空，请注意处理###)
*/
/*<![CDATA[*/
$(function(){
	var form = $("form[id^=orderByForm]");
	
	/*------------------------分页组件处理流程----------------------------------------------*/
	// 页码显示：根据totalPageCnt决定第一次页面加载时显示的页面布局
	var totalPageCnt = parseInt($("input:hidden[name='totalPageCnt']").val(),10);
	var currentPageNum = parseInt($("input:hidden[name='currentPageNum']").val(),10);
	initPageCompotent("tslCommon-Page");
	showPageAtt(totalPageCnt, currentPageNum);
	
	// 页码点击事件，
	// 1。点击页码和高亮页码不一致时移除高亮页码高亮状态，高亮点击的页码,
	// 2。一致时什么也不做
	$("li[id^='pageNum']").click(function(){
		var pageNum = parseInt($(this).text(),10);
		if(pageNum && currentPageNum && pageNum == currentPageNum){
				return;
		}else{
			$("input:hidden[name='currentPageNum']").val(pageNum);
			form.submit();
		}
	});
	
	// 点击上一页
	$("a[aria-label='Previous']").click(function(){
		//var currentPageNum = $("input:hidden[name='currentPageNum']").val();
		if(currentPageNum && currentPageNum <= 1){
			alert("已经是第一页了！");
			return;
		}
		$("input:hidden[name='currentPageNum']").val(currentPageNum - 1);
		form.submit();
	});
	
	// 点击下一页
	$("a[aria-label='Next']").click(function(){
		//var currentPageNum = $("input:hidden[name='currentPageNum']").val();
		if(currentPageNum >= totalPageCnt){
			alert("已经是最后一页！");
			return;
		}
		$("input:hidden[name='currentPageNum']").val(parseInt(currentPageNum,10) + 1);
		form.submit();
	});
	
	// 跳转按钮的处理，输入的页码为当前页码时不做任何处理
	// 1。输入页码大于totalPageCnt时，给个提示
	// 2。输入页码小于1时，给个提示
	// 3。其他情况时，直接提交表单
	$("#goPage").click(function(){
		var inputNum = $("#toPageNum").val();
		if(inputNum > totalPageCnt || inputNum < 1){
			alert("输入的页面不存在，请重新输入！");
			return;
		}
		$("input:hidden[name='currentPageNum']").val(inputNum);
		form.submit();
	});
});

// 上一页、下一页显示
// 1。当当前页码<=1时，上一页是不能点击的
// 2。当前页码>=totalPageCnt时，下一页是不能点击的
// 3。当前页码在>1&&<totalPage时，下一页和上一页都能点击
// 4。当totoalPage=1时，上一页和下一页都不能点击
function showPageAtt(totalPageCnt, currentPageNum){
	if(!totalPageCnt || totalPageCnt == ""){
		totalPageCnt = 0;
	}
	if(totalPageCnt <= 1){
		$("a[aria-label='Previous']").css("disabled", true);
		$("a[aria-label='Next']").css("disabled", true);
	}else{
		if(currentPageNum <= 1){
			$("a[aria-label='Previous']").css("disabled", true);
			$("a[aria-label='Next']").css("disabled", false);
		}else if(currentPageNum >= totalPageCnt){
			$("a[aria-label='Previous']").css("disabled", false);
			$("a[aria-label='Next']").css("disabled", true);
		}else{
			$("a[aria-label='Previous']").css("disabled", false);
			$("a[aria-label='Next']").css("disabled", false);
		}
	}
}

// 页码处理
// 1。页面上currentPageNum为10的倍数显示的最大页码为currentPageNum
// 2。页面上currentPageNum不为10的倍数时显示的最大页码另行计算
function showPageInfo(currentPageNum, totalPageCnt){
	var info = "";
	
	// 初始化当前页数
	if(!currentPageNum || currentPageNum == "" || currentPageNum == 0){
		currentPageNum = 1;
	} 
	
	var max = 0;
	// 总页数>=1时，否则只显示一页
	if(totalPageCnt >= 1){
		if(currentPageNum < 10){
			max = 10;
		}else{
			max = (Math.floor(currentPageNum / 5) + 1) * 5;
		}
		if(max > totalPageCnt){
			max = totalPageCnt;
		}
	} else {
		max = 1;
	}
	
	for(var i=max;i > max - 10 && i > 0; i--){
		if(i == currentPageNum){
			info = '<li id="pageNum' + i + '" class="active"><a href="#" >' + i + '</a></li>' + info;
		} else {
			info = '<li id="pageNum' + i + '"><a href="#" >' + i + '</a></li>' + info;
		}
	}
	return info;
}

function initPageCompotent(id){
	var totalPageCnt = parseInt($("input:hidden[name='totalPageCnt']").val(), 10);
	var currentPageNum = parseInt($("input:hidden[name='currentPageNum']").val(), 10);
	var totalRecord = parseInt($("input:hidden[name='totalRecord']").val(), 10);
	totalPageCnt = (totalPageCnt && totalPageCnt != "") ? totalPageCnt : 0;
	currentPageNum = (currentPageNum && currentPageNum != "") ? currentPageNum : 1;
	var str = '<ul class="pagination" style="padding:0;margin:0">'
		+ '  <li><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>'
		+ showPageInfo(currentPageNum,totalPageCnt)	    
		+ '  <li><a href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>'
		+ '  <li style="padding:10px;">&nbsp;</li>'
		+ '  <li>'
		+ '  	<label>共'+ totalRecord + '条记录，共' + totalPageCnt + '页</label>'
		+ '  	<input size="1.5" id="toPageNum" style="height:30px;"/>'
		+ '  </li>'
		+ '  <li>'
		+ '  	<button id="goPage" type="button" class="btn btn-primary">GO <span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span></button>'
		+ '  </li>'
		+ '</ul>';
	$("#" + id).append(str);
}
/*]]>*/