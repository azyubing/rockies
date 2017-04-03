$(document).ready(function() {
	
	//mineMenu页面渲染
	if($("#mineMenu").length > 0){
		mineMenu();
	}
	
	//mineInfo页面渲染
	if($("#mineInfo").length > 0){
		mineInfo();
	}
	
	//修改头像
	if($("#mineInfo").length > 0){
		mineEditPortrait();
	}
	
	//修改用户名
	if($("#mineEditName").length > 0){
		mineEditName();
	}
	
	//修改绑定手机号
	if($("#mineEditPhone").length > 0){
		mineEditPhone();
	}
	
	//修改密码
	if($("#mineEditPwd").length > 0){
		mineEditPwd();
	}
	
});

//mienMenu页面渲染
function mineMenu(){
	var uinfo = store.get("mw_uinfo") || new Object();
	var render = template.compile(tmpl_mineMenu);
	var html = render(uinfo);
	$('#mineMenu').html(html);
	//退出登录
	$("#mineMenu").delegate("#logout","click",function(){
		$.ajax({
	     	url : baseUrl+"mobile/logout.do",
	     	type : "post",
	     	data : {
	     		loginName : uinfo.username
	     	},
	     	success : function(data) {
    	 		store.remove("mw_uinfo");
    	 		location.href = "userLogin.html";
	     	}
		});
	})
}

//mineInfo页面渲染
function mineInfo(){
	var uinfo = store.get("mw_uinfo");
	var render = template.compile(tmpl_mineInfo);
	var html = render(uinfo);
 	$('#mineInfo').html(html);
}

//修改头像
function mineEditPortrait(){
	$('#mineInfo').delegate("#picFile","change",function(){
		fileloadon(URL.createObjectURL(this.files[0]));
	})
}
function fileloadon(path) {
	$("#portraitForm").submit(function() {
		$(this).ajaxSubmit({
			url : baseUrl+"img/uploadFile.do",
			type : 'post',
			success : function(data) {
				if(data.status == 'success') {
					var uinfo = store.get("mw_uinfo");
					uinfo.photo = data.body[0];
					$.ajax({
						url : baseUrl+"mobile/photo.do",
						type : "post",
						data : {
							photoUrl : uinfo.photo
						},
						success : function(data) {
							if(data.status == 'success'){
								store.set("mw_uinfo",uinfo);
								$("#picPreview").css({"background-image":"url("+path+")"});
								modal_alert(data.message);
							}
							else if(data.status == 'LoginFailed'){
								store.remove("mw_uinfo");
								modal_alert("用户未登录或者登陆已过期，请重新登陆!", "userLogin.html");
							}
							else{
								modal_alert(data.message);
							}
						}
					});
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
	$("#portraitForm").submit();
}

//修改用户名
function mineEditName(){
	var uinfo = store.get("mw_uinfo")
	var loginName = uinfo.realName;
	if(loginName){
		$("#loginName").val(loginName).siblings(".fieldClear").show();
	}
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
			     	url : baseUrl+"mobile/username.do",
			     	type : "post",
			     	data : formData,
			     	success : function(data) {
			    	 	if(data.status == 'success'){
		    	 			store.set("mw_uinfo",data.body);
		    	 			location.href = "mineInfo.html";
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
		    return false;
	  	}
	});
}

//修改绑定手机号
function mineEditPhone(){
	var uinfo = store.get("mw_uinfo")
	var phone = uinfo.tel;
	if(phone){
		$("#phone").val(phone).siblings(".fieldClear").show();
	}
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
			     	url : baseUrl+"mobile/cellPhoneNumber.do",
			     	type : "post",
			     	data : formData,
			     	success : function(data) {
			    	 	if(data.status == 'success'){
		    	 			store.set("mw_uinfo",data.body);
		    	 			location.href = "mineInfo.html";
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
		    return false;
	  	}
	});
}


//修改密码
function mineEditPwd(){
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
			     	url : baseUrl+"mobile/password.do",
			     	type : "post",
			     	data : formData,
			     	success : function(data) {
			    	 	if(data.status == 'success'){
		    	 			store.set("mw_uinfo",data.body);
		    	 			location.href = "mineInfo.html";
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
		    return false;
	  	}
	});
}
