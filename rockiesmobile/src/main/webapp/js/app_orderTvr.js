$(document).ready(function() {
	paramVerify();
})

//参数验证
function paramVerify(){
    var orderNo = getOrderNo();
    if(!orderNo){
    	history.replaceState({foo:"bar"}, "", "index.html");
    	modal_alert("订单参数错误","javascript:location.reload();")  	
    }
    else{
    	eventBind();
    	renderView();
    }
}

//orderno获取
function getOrderNo(){
	var orderinfo = store.get("mw_orderinfo");
    var orderNo = getUrlParam("orderNo");
    !orderNo && orderinfo ? orderNo = orderinfo.orderNo : "";
	return orderNo;
}

//事件绑定
function eventBind(){
	//新增随行人
	$("#tvrAdd").click(function(){
		form_render({});
		form_ajax();
	})
	//修改随行人
	$("#render_orderTvr").delegate(".btnEdit","click",function(){
		form_render($(this).parents(".infoTvr").data("info"));
		form_ajax();
	})
	//删除随行人
	$("#render_orderTvr").delegate(".btnDele","click",function(){
    	var orderNo = getOrderNo();
    	var id = $(this).parents(".infoTvr").data("info").id;
    	modal_confirm("确定要删除此随行人吗？");
    	$('#modal_confirm').modal({
	        onConfirm: function(options) {	
	        	var data = {
					orderNo : orderNo,
					id : id
				}
				tvr_dele(data);
	        },
	        onCancel: function() {
	          	$('#modal_confirm').modal('close');
	        }
	  	});
	})
	//确认提交
	$('#submitBtn').click(function(){
		var dom = this;
		var orderNo = getOrderNo();
		$.ajax({
			url : baseUrl+"mobile/TravelPartner.do",
			type : "get",
			data : {
				orderNo : orderNo
			},
			success : function(data) {
				if(data.status == 'success'){
					if(data.body.length > 0){
						location.href = dom.href+"?orderNo="+orderNo;
					}
					else{
						modal_alert("请先添加随行人")
					}
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

//页面渲染
function renderView(){
    var orderNo = getOrderNo();
	$.ajax({
		url : baseUrl+"mobile/TravelPartner.do",
		type : "get",
		data : {
			orderNo : orderNo
		},
		success : function(data) {
			if(data.status == 'success'){
				var renderData = data;
				$.each(renderData.body, function(key,value) {
					value.jsonstr = JSON.stringify(value)
				})
				var render = template.compile(tmpl_orderTvr);
				var html = render(renderData);
 				$('#render_orderTvr').html(html);
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
}

//渲染随行人表单数据
function form_render(renderData){
	var render = template.compile(tmpl_orderTvrEdit);
	var html = render(renderData);
	$('#render_orderTvrEdit').html(html);
	$('#tvrBirthday').datepicker({
		format : 'yyyy-mm-dd',
		viewMode : 2
	});
}

//保存随行人表单验证
function form_ajax(){
	$('#form-verify-msg').validator({
	    onValid: function(validity) {
	      	formOnValid(validity,this);
	    },
	    onInValid: function(validity) {
	      	formOnInValid(validity,this);
	    },
	    submit: function() {
		    var formValidity = this.isFormValid();
		    if(formValidity){
		    	var form = $(this.$element.context);
		   	 	var formData = formFieldPackage(form.find("[name]"));
		   	 	var orderNo = getOrderNo();
		    	formData.orderNo = orderNo;
		    	formData.id = parseInt(formData.id);
		    	tvr_save(formData);
		    }
		    return false;
	  	}
	});
}

//保存随行人
function tvr_save(data){
	$.ajax({
     	url: baseUrl+"mobile/saveTravelPartner.do",
     	type: "post",
     	data: data,
     	beforeSend:function() {
     		$("body").append('<div class="formLoading"><div class="spnr"><i class="am-icon-spinner am-icon-pulse"></i><p class="text">保存中</p></div></div>')
        },
     	success: function(data) {
    	 	if(data.status == 'success'){
    	 		$('#form-verify-msg')[0].reset();
				$("#render_orderTvrEdit").html("");
    	 		renderView();
    	 	}
    	 	else if(data.status == 'LoginFailed'){
	       		store.remove("mw_uinfo");
       			modal_alert("用户未登录或者登陆已过期，请重新登陆!","userLogin.html");
       		}
			else{
				modal_alert(data.message);
			}
			$(".formLoading").remove();
     	}
	});
}

//删除随行人
function tvr_dele(data){
	$.ajax({
		url : baseUrl+"mobile/deleteTravelPartner.do",
		type : "post",
		data : data,
		beforeSend:function() {
     		$("body").append('<div class="formLoading"><div class="spnr"><i class="am-icon-spinner am-icon-pulse"></i><p class="text">删除中</p></div></div>')
        },
		success : function(data) {
			if(data.status == 'success'){
				renderView();
			}
       		else if(data.status == 'LoginFailed'){
	       		store.remove("mw_uinfo");
       			modal_alert("用户未登录或者登陆已过期，请重新登陆!","userLogin.html");
       		}
			else{
				modal_alert(data.message);
			}
			$(".formLoading").remove();
		}
	});
}