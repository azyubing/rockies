$(document).ready(function() {
	paramVerify();
})

//参数验证
function paramVerify(){
    var orderinfo = store.get("mw_orderinfo");
    if(!orderinfo){
    	history.replaceState({foo:"bar"}, "", "index.html");
    	modal_alert("数据异常或数据已过期","javascript:location.reload();")  
    }
    else{
    	eventBind();
    	renderView();
    }
}

//页面渲染
function renderView(){
	var orderinfo = store.get("mw_orderinfo");
	if(typeof orderinfo.order_data == "string"){
		orderinfo.order_data = JSON.parse(orderinfo.order_data);
		store.set("mw_orderinfo",orderinfo);
	}
	var renderData = orderinfo;
	var render = template.compile(tmpl_orderConfirm);
	var html = render(renderData);
	$('#render_orderConfirm').html(html);
	$("#feeTotal").text(orderinfo.order_amount);
}

//事件绑定
function eventBind(){
	//生成订单
	$('#submitBtn').click(function(){
		var dom = this;
		var orderinfo = store.get("mw_orderinfo");
		orderinfo.order_data = JSON.stringify(orderinfo.order_data);
		orderinfo.jsonString = JSON.stringify(orderinfo);
		$.ajax({
			url : baseUrl+"mobile/myorder.do",
			type : "post",
			data : orderinfo,
			success : function(data) {
				if(data.status == 'success'){
//					orderinfo.orderNo = data.body.orderNo;
//					orderinfo.status = data.body.status;
//					store.set("mw_orderinfo",orderinfo);
					store.remove("mw_orderinfo");
					modal_alert("订单已生成，可到【我的订单】中查看或继续添加随行人",dom.href+"?orderNo="+data.body.orderNo)
				}
				else if(data.status == 'LoginFailed'){
	       			store.remove("mw_uinfo");
	       			modal_alert("用户未登录或者登陆已过期，请重新登陆!","userLogin.html");
	       		}
				else{
					modal_alert(data.message);
				}
			}
		});
		return false;
	});
}
