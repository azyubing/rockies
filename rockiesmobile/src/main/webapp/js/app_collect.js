$(document).ready(function() {
	
	//展开取消modal层
	$('.collectList').delegate(".operateBtn","click",function(){
		window.cancelCollectId = $(this).parents("li").data("pid");
		$('#modal-actions').modal();
		return false;
	})
	//取消收藏
    $("#collectCancel").click(function(){
    	collectCancel(window.cancelCollectId);
    	window.cancelCollectId = null;
    })
	
});

//分页数据加载_数据请求
function waterFallAjax(){
	loader = this;
	var ajaxParam = $.extend(loader.ajaxParam,{
		page : loader.ajaxPage
	})
    $.ajax({
    	url : baseUrl+"mobile/myfavorites.do",
        type : "get",
        data : ajaxParam,
        beforeSend : function() {
    		$(".resultNull").remove();
            loader.loadElm.addClass("loading").show().find(".text").text("努力加载中...");
        },
        success : function(data) {
       		if (data.status == 'success') {
	    	 	loader.loadDataHandle(data.body,tmpl_favorite);
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

//收藏-取消
function collectCancel(pid){
	$.ajax({
        url : baseUrl+"mobile/delmyfavorites.do",
        type : "post",
        data : {
        	pid : pid
        },
        success:function(data) {
        	if (data.status == 'success') {
	    	 	$(".collectList>li[data-pid='"+pid+"']").remove();
	       		if($(".collectList").height() < $(window).height() - 120){
	       			cxtWf.loadData();
	       		}
				$('#modal-actions').modal('close');
				modal_alert(data.message)
    	 	}
        	else if(data.status == 'LoginFailed'){
	       		store.remove("mw_uinfo");
       			modal_alert("用户未登录或者登陆已过期，请重新登陆!","userLogin.html");
       		}
        	else{
        		modal_alert(data.message)
        	}
        }
  	})
}