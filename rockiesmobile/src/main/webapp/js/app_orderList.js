$(function(){
	window.cxtWf = waterFall();
})

//分页数据加载_数据请求
function waterFallAjax(){
	loader = this;
	var ajaxParam = $.extend(loader.ajaxParam,{
		page : loader.ajaxPage
	})
    $.ajax({
    	url : baseUrl+"mobile/myorders.do",
        type : "get",
        data : ajaxParam,
        beforeSend : function() {
    		$(".resultNull").remove();
            loader.loadElm.addClass("loading").show().find(".text").text("努力加载中...");
        },
        success : function(data) {
       		if(data.status == 'success'){
       			$.each(data.body.list,function(i,item){
       				if(item.routeJson){
       					item.routeJson = JSON.parse(item.routeJson);
       					item.routeJson.order_data = JSON.parse(item.routeJson.order_data);
       				}
       			})
	    	 	loader.loadDataHandle(data.body,tmpl_order);
    	 	}
       		else if(data.status == 'LoginFailed'){
       			loader.loadElm.remove();
	       		store.remove("mw_uinfo");
       			modal_alert("用户未登录或者登陆已过期，请重新登陆!","userLogin.html");
       		}
       		else{
	            loader.listElm.hide().after('<p class="resultNull">'+(data.message || "对不起，没有查找到相关数据")+'</p>');
       		}
        }
    });
}