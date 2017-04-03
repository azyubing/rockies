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
	$("#form-verify-msg").find("[name]").each(function(i,item){
		var name = item.name;
		$(item).val(orderinfo[name]);
	})
	$("#feeTotal").text(orderinfo.order_amount);
}

//事件绑定
function eventBind(){
	//下一步
	$('#form-verify-msg').validator({
	    onValid: function(validity) {
	      	formOnValid(validity,this);
	    },
	    onInValid: function(validity) {
	      	formOnInValid(validity,this);
	    },
	    submit: function() {
		    var formValidity = this.isFormValid();
		    var form = $(this.$element.context);
		    var formData = formFieldPackage(form.find("[name]"));
		    var formAction = form.attr("action");
		    if(formValidity){
		    	var orderinfo = store.get("mw_orderinfo");
		    	$.extend(true,orderinfo,formData);
		    	store.set("mw_orderinfo",orderinfo);
				location.href = formAction;
		    }
		    return false;
	  	}
	});
}
