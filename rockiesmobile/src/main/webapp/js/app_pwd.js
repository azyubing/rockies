$(document).ready(function() {
	paramVerify();
})

//参数验证
function paramVerify(){
	var uinfo = store.get("mw_uinfo");
    if(uinfo){
    	history.replaceState({foo:"bar"}, "", "index.html");
 		location.reload();
    }
    else{
    	eventBind();
    }
}

//操作事件绑定
function eventBind(){
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
		    	$.ajax({
			     	url : baseUrl+"mobile/resetPassword.do",
			     	type : "post",
			     	data : formData,
			     	success: function(data) {
			    	 	if(data.status == 'success'){
			    	 		location.href = "userLogin.html";
			    	 	}
			    	 	else{
			    	 		modal_alert(data.message);
			    	 	}
			     	}
				});
		    }
		    return false;
	  	}
	});
}
