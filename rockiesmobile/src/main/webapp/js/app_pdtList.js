//分页数据加载_数据请求
function waterFallAjax(){
	loader = this;
	var ajaxParam = $.extend(loader.ajaxParam,{
		page : loader.ajaxPage
	})
    $.ajax({
    	url : baseUrl+"mobile/products.do",
        type : "get",
        data : ajaxParam,
        beforeSend : function() {
    		$(".resultNull").remove();
            loader.loadElm.addClass("loading").show().find(".text").text("努力加载中...");
        },
        success : function(data) {
       		if (data.status == 'success') {
	    	 	loader.loadDataHandle(data.body,tmpl_product);
    	 	}
       		else{
	            loader.listElm.hide().after('<p class="resultNull">'+(data.message || "对不起，没有查找到相关数据")+'</p>');
       		}
        }
    });
}