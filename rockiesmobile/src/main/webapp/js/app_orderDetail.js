$(document).ready(function() {
	renderView();
});

//视图渲染
function renderView(){
	var orderNo = getOrderNo();
	//根据orderNo获取订单详细信息
	$.ajax({
    	url : baseUrl+"mobile/myorder.do",
        type : "get",
        data : {
        	orderNo : orderNo
        },
        success : function(data) {
       		if(data.status == 'success'){
       			if(data.body){
       				data.body.routeJson = JSON.parse(data.body.routeJson);
	       			data.body.routeJson.order_data = JSON.parse(data.body.routeJson.order_data);
	       			var renderData = data.body;
		    	 	var render = template.compile(tmpl_orderDetail);
					var html = render(renderData);
		    	 	$("#render_orderDetail").html(html);
       			}
       			else{
       				modal_alert(data.message || "对不起，不存在此订单","javascript:history.back();")
       			}
    	 	}
			else if(data.status == 'LoginFailed'){
	       		store.remove("mw_uinfo");
       			modal_alert("用户未登录或者登陆已过期，请重新登陆!","userLogin.html")
       		}
       		else{
	            modal_alert(data.message || "对不起，不存在此订单","javascript:history.back();")
       		}
        }
    });
    //根据orderNo获取订单的随行人数据
    $.ajax({
    	url : baseUrl+"mobile/TravelPartner.do",
        type : "get",
        data : {
        	orderNo : orderNo
        },
        success : function(data) {
       		if(data.status == 'success'){
       			var renderData = data;
	    	 	var render = template.compile(tmpl_orderDetailTvr);
				var html = render(renderData);
	    	 	$("#render_orderDetailTvr").html(html);
    	 	}
			else if(data.status == 'LoginFailed'){
	       		store.remove("mw_uinfo");
       			modal_alert("用户未登录或者登陆已过期，请重新登陆!","userLogin.html");
       		}
       		else{
	            modal_alert(data.message || "对不起，不存在此订单","javascript:history.back();")
       		}
        }
    });
}

//orderno获取
function getOrderNo(){
    var orderNo = getUrlParam("orderNo");
	return orderNo;
}