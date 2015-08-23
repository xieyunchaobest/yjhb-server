/** 使用 
	 1. form的id必须以orderByForm开头如：orderByForm1,orderByFormUser等
	 	form下必须有<input type="hidden" id="orderByStr" name="orderByStr" th:value="${orderByStr}"/>
	 2。 表格table必须有表头，表头元素必须使用th
	 3。th标签中必须包含span标签，span标签的order值为要排序的数据库字段columnName
	   后台必须有orderBy字段的处理（其中orderByStr=columnName,desc|asc）
	   model.addAttribute("orderByStr", request.getParameter("orderByStr"));
*/
/*<![CDATA[*/
$(function(){
	/*------------------------------排序组件处理流程-------------------------------------------------------*/
	var form = $("form[id^=orderByForm]");
	
	// 初始化样式
	var strs = $("#orderByStr").val().split(";");
	$("th > span.glyphicon").each(function(){
		if(strs && strs.length >= 1){
			var i = 0;
			for(;i <= strs.length; i++){
				if(strs[i] && strs[i] != "" && strs[i].indexOf(this.id) != -1){
					var strs1 = strs[i].split(",");
					if(strs1[1] == "asc"){
						$(this).removeClass("glyphicon-arrow-up");
						$(this).addClass("glyphicon glyphicon-arrow-down")
							.css({"cursor":"pointer","padding-right":"3px"});
					} 
					if(strs1[1] == "desc"){
						$(this).removeClass("glyphicon-arrow-down");
						$(this).addClass("glyphicon glyphicon-arrow-up")
							.css({"cursor":"pointer","padding-right":"3px"});
					} 
				} 
			}
			if(i < strs.length - 1){
				$(this).removeClass("glyphicon-arrow-down");
				$(this).addClass("glyphicon glyphicon-arrow-up")
					.css({"cursor":"pointer","padding-right":"3px"});
			}
		} else {
			$(this).removeClass("glyphicon-arrow-down");
			$(this).addClass("glyphicon glyphicon-arrow-up")
				.css({"cursor":"pointer","padding-right":"3px"});
		}
	});
	
	// 点击排序按钮事件处理
	$("th > span.glyphicon").click(function(){
		var classes = $(this).attr("class").split(" ");
		var tmp = $("#orderByStr").val();
		var obj = $("#orderByStr");
		var columnName = $(this).attr("order");
		for(var i in classes){
			if(classes[i] == "glyphicon-arrow-up"){
				var order = columnName + ",asc";
				// 1。没有排序信息时加上排序，
				// 2。有排序信息且包含包含该排序列时更改排序方式，
				// 3。有排序信息，但不包含该排序列时加上该排序信息
				if(tmp && tmp.indexOf(columnName) != -1){
					tmp = tmp.replace(columnName + ",desc", order);
					
				}else{
					tmp = order;
				}
				/*else{
					tmp += ";" + order;
				}*/
				obj.val(tmp);
				form.submit();
			} else if(classes[i] == "glyphicon-arrow-down"){
				var order = columnName + ",desc";
				// 1。没有排序信息时加上排序，
				// 2。有排序信息且包含包含该排序列时更改排序方式，
				// 3。有排序信息，但不包含该排序列时加上该排序信息
				if(tmp.indexOf(columnName) != -1){
					tmp = tmp.replace(columnName + ",asc", order);
				} else{
					tmp = order;
				}
				/*else{
					tmp += ";" + order;
				}*/
				obj.val(tmp);
				form.submit();
			}else{
				continue;
			}
		}			
	});
});
/*]]>*/