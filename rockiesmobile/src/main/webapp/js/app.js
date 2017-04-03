//baidu统计
var _hmt = _hmt || [];
(function() {
  	var hm = document.createElement("script");
  	hm.src = "//hm.baidu.com/hm.js?51a2ab6887e5a74baa9497e3e16bc7fa";
  	var s = document.getElementsByTagName("script")[0]; 
  	s.parentNode.insertBefore(hm, s);
})();

var baseUrl = "";

(function($) {
  	$(function() {
  		
  		$(window).smoothScroll({speed:10});
  		
		//公共AJAX参数设置
		cmnAjaxInit();
		
		//检测登录状态
		testLoginStatus();

  		//storage支持性及初始化
  		storageIsSupport();
	    
	    //表单-表单控件内容清除
	    if($(".fieldClear").length > 0){
	    	$(".fieldClear").siblings(".am-form-field").each(function(){
	    		$(this).val() == "" ? $(this).siblings(".fieldClear").hide() : $(this).siblings(".fieldClear").show();
	    	})
	    	$(".fieldClear").siblings(".am-form-field").keyup(function(){
	    		$(this).val() == "" ? $(this).siblings(".fieldClear").hide() : $(this).siblings(".fieldClear").show();
	    	}).change(function(){
	    		$(this).val() == "" ? $(this).siblings(".fieldClear").hide() : $(this).siblings(".fieldClear").show();
	    	})    
	    	$(".fieldClear").click(function(){
	    		var _this = $(this);
	    		_this.hide();
	    		_this.siblings(".am-form-field").val("").removeClass("am-field-valid am-active").focus();
	    		_this.parents(".am-form-group").removeClass("am-form-success");
	    	})
	    }

		//搜索条件view渲染
		if($(".hdSearch").length > 0){
			viewSearch();
		}  
	    
	    //下拉选项view渲染
	    if($(".dropdownWrap").length > 0){
			viewDropdown();
		}

		//筛选条件面板view渲染
		if($("#filtrateWrap").length > 0){
			viewFiltrate();
		}  
	    
	    //筛选条件面板下拉框遮罩
	    $('.am-dropdown').on('opened.dropdown.amui', function (e) {
    		$("#dropdownMask").show();
      		//$("body").addClass("pageHidden");
	    });
	    $('.am-dropdown').on('closed.dropdown.amui', function (e) {
	      	$("#dropdownMask").hide();
	      	//$("body").removeClass("pageHidden");
	      	//筛选条件值还原设置为当前使用的参数值
	      	if($(".filtrateList").length > 0){
	      		$(".filtrateList").each(function(){
	      			$(this).find("a[href='"+waterFallData[$(this).data("name")]+"']").tabClass("am-active");
			    })
	      	}
	    });
	    $("#dropdownMask").on('click', function() {
	    	$('.am-dropdown').dropdown('close');
	    });
	    
	    //个人中心-获取验证码
	    $("#getCaptcha").click(function(){
	    	getCaptcha($(this));         
      	})
		
		//订单操作-删除订单
	    $(".orderList, .orderDetail").delegate(".orderDelete","click",function(){
	    	modal_confirm("你，确定要删除该订单吗？");
	    	modal_confirm_handle_order(this);
	    	return false;
	    })
	    //订单操作-取消订单
	    $(".orderList, .orderDetail").delegate(".orderCancel","click",function(){
	    	modal_confirm("你，确定要取消该订单吗？");
	    	modal_confirm_handle_order(this);
	    	return false;
	    })
	    //订单操作-申请退款
	    $(".orderList, .orderDetail").delegate(".orderRefund","click",function(){
	    	$('#modal_orderRefund').modal({});
	    	return false;
	    })
	   	
  	});
})(jQuery);

//公共AJAX参数设置
function cmnAjaxInit(){
	$.ajaxSetup({
     	dataType : "json",
     	cache : false,
     	error: function(XMLHttpRequest, textStatus, errorThrown) {
     		self ? self.ajaxLock = false : "";
     		$(".formLoading").remove();
    	 	modal_alert(textStatus);
     	}
	});
}

//检测登录状态
function testLoginStatus(){
	$.ajax({
		url : baseUrl + "mobile/loginStatus.do",
		type : "get",
		success : function(data) {
			if(data.status == 'success'){
				store.set("mw_uinfo",data.body);
				if($('#loginStatus').length > 0){
					render_loginStatus(store.get("mw_uinfo") || new Object());
				}
			}
    	 	else if(data.status == 'LoginFailed'){
	       		store.remove("mw_uinfo");
       		}
			else{
				store.remove("mw_uinfo");
			}
		}
	});
}

//搜索条件view渲染
function viewSearch(){
	var optionsParamArray = location.href.slice(location.href.indexOf("?",-1)+1).split("&");
	var optionsParam = new Array();
	$.each(optionsParamArray, function(index, value) {
		var obj = new Object();
		var ary = value.split("=");
	  	obj["key"] = ary[0];
	  	obj["value"] = ary[1];
	  	optionsParam.push(obj);
	});
	$.each(optionsParam, function(key, value) {
		if(value.key == "searchKeyWords"){
			$("#searchField").val(decodeURI(decodeURI(value.value)));
		}
	})
}

//下拉选项view渲染
function viewDropdown(){
	var optionsDropdown;
	var render;
 	var html;
 	var id;
	var optionsRender = function(){
		var optionsParamArray = location.href.slice(location.href.indexOf("?",-1)+1).split("&");
		var optionsParam = new Array();
		$.each(optionsParamArray, function(index, value) {
			var obj = new Object();
			var ary = value.split("=");
		  	obj["key"] = ary[0];
		  	obj["value"] = ary[1];
		  	optionsParam.push(obj);
		});
		$.each(optionsDropdown, function(key, value) {
			var optName = key;
			var optArray = value;
			var isSame = false;
			$.each(optionsParam, function(index, value) {
				var selKey = value.key;
				var selValue = value.value;
				if(optName.toLowerCase() == selKey+"options"){
					$.each(optArray, function(index, value) {
						if(selValue == value.key){
							value.selected = true;
							isSame = true;
						}
					});
				}
			});
			!isSame ? optArray[0].selected = true : "";
		});
		var tmplArray = [
			{"tmpl":tmpl_dropdown_theme,"id":"dropdown_theme"},
			{"tmpl":tmpl_dropdown_destination,"id":"dropdown_dest"},
			{"tmpl":tmpl_dropdown_services,"id":"dropdown_serve"},
			{"tmpl":tmpl_dropdown_orderby,"id":"dropdown_sort"}
		];
		$.each(tmplArray, function(index, value) {
			render = template.compile(value.tmpl);
			html = render(optionsDropdown);
			id = value.id;
    	 	document.getElementById(id).innerHTML = html;
    	 	$('.paramCur[for="'+id+'"]').text($("#"+id).find("li.am-active a").text());
		})
	 	window.cxtWf = waterFall();
	}
	if(storageIsSupport()){
		optionsDropdown = store.get('mw_optionsDropdown');
		if(optionsDropdown){
			optionsRender();
		}
		else{
			$.ajax({
		     	url : baseUrl+"mobile/productSearchOptions.do",
		     	type : "get",
		     	success : function(data) {
		    	 	if (data.status == 'success') {
		    	 		optionsDropdown = data.body;
		    	 		store.set('mw_optionsDropdown',optionsDropdown);
						optionsRender();
		    	 	}
		    	 	else{
		    	 		modal_alert(data.message);
		    	 	}
		     	}
			});
		}
	}
}

//筛选条件面板view渲染
function viewFiltrate(){
	var optionsFiltrate;
	var render;
 	var html;
 	var id;
	var optionsFiltrateRender = function(){
		var tmplArray = [
			{"tmpl":tmpl_filtrate_serve,"id":"filtrate_serve"},
			{"tmpl":tmpl_filtrate_dest,"id":"filtrate_dest"}
		];
		$.each(tmplArray, function(index, value) {
			render = template.compile(value.tmpl);
			html = render(optionsFiltrate);
			id = value.id;
    	 	document.getElementById(id).innerHTML = html;
		})
	 	window.cxtWf = waterFall();
	}
	if(storageIsSupport()){
		optionsFiltrate = store.get('mw_optionsFiltrate');
		if(optionsFiltrate){
			optionsFiltrateRender();
		}
		else{
			$.ajax({
		     	url : baseUrl+"mobile/myfavoritesSearchOptions.do",
		     	type : "get",
		     	success : function(data) {
		    	 	if (data.status == 'success') {
		    	 		optionsFiltrate = data.body;
		    	 		store.set('mw_optionsFiltrate',optionsFiltrate);
						optionsFiltrateRender();
		    	 	}
		    	 	else{
		    	 		modal_alert(data.message);
		    	 	}
		     	}
			});
		}
	}
}

//分页数据加载
function waterFall(){
	self = this;
	self.config = function(){
		self.paramInit();
		this.setBind();
        self.loadData();
	}
	self.paramInit = function(){
        self.listElm = $("#waterFall");
        self.loadElm = $("#pageLoading");
        self.loadBtn = $("#pageLoadBtn");
        self.ajaxType = "get";
        self.ajaxDateType = "json";
        self.ajaxParam = self.getAjaxParam();
        self.ajaxCache = false;
        self.ajaxLock = true;
        self.ajaxPage = 1;
        self.listElm.empty();
    }
	self.setBind = function(){
		//下滑分页加载
		if(self.loadElm.length > 0){
        	$(window).bind('scroll',self.scrollReload);
		}
		//点击分页加载
		if(self.loadBtn.length > 0){
        	$("#pageLoadBtn").click(function(){
		        if(!self.ajaxLock){
		            self.ajaxLock = true;
	                self.ajaxPage++;
	                self.loadData();
		        }
        	})
		}
		//搜索关键字切换
        $("#searchBtn").click(function(){
        	if($("#searchField").val() != waterFallData[$("#searchField").data("name")]){
        		$(window).smoothScroll({speed:10});
	        	self.paramInit();
	    		self.loadData();
	        	$('.am-dropdown').dropdown('close');
        	}
    		return false;
        })
        //下拉列表项切换
        $(".am-dropdown-content").delegate("li","click",function(){
        	if(!self.ajaxLock){
        		if(!$(this).hasClass("am-active")){
	        		$(this).tabClass("am-active");
		        	$(".paramCur[for='"+$(this).parent().attr("id")+"']").text($(this).find("a").text());
		        	$(window).smoothScroll({speed:10});
		        	self.paramInit();
		    		self.loadData();
	        	}
	        	$('.am-dropdown').dropdown('close');
        	}
        	return false;
        })
        //更多筛选条件切换
        $(".filtrateList").delegate("a","click",function(){
        	if(!self.ajaxLock){
        		$(this).tabClass("am-active");
        	}
        	return false;
        })
        $("#filtrateBtn").click(function(){
        	if(!self.ajaxLock){
        		$(".filtrateList").each(function(){
	      			if($(this).find("a.am-active").attr("href") != waterFallData[$(this).data("name")]){
	    				$(window).smoothScroll({speed:10});  
			        	self.paramInit();
		    			self.loadData();
	        			return false;      	
	      			}
			    })	
	        	$('.am-dropdown').dropdown('close');
        	}
        })
        //订单选项卡切换
        $("#orderTab").delegate("li","click",function(){
        	if(!self.ajaxLock){
        		if(!$(this).hasClass("am-active")){
	        		$(this).tabClass("am-active");
	        		$(window).smoothScroll({speed:10});
		    		self.paramInit();
		    		self.loadData();
	        	}
        	}
        	return false;
        })    
	}
    self.getAjaxParam = function (){
    	if(!window.waterFallData){
    		window.waterFallData = new Object();
    	}
    	$(".dropdownSelect").each(function(){
	    	waterFallData[$(this).data("name")] = $(this).find("li.am-active>a").attr("href");
	    })
		$(".filtrateList").each(function(){
	    	waterFallData[$(this).data("name")] = $(this).find("a.am-active").attr("href");
	    })
		if($("#searchField").length > 0){
			waterFallData[$("#searchField").data("name")] = $("#searchField").val();
		}
		if($("#orderTab").length > 0){
			waterFallData[$("#orderTab").data("name")] = $("#orderTab>li.am-active>a").attr("href");
		}
		waterFallData.pageSize = 5;
		return waterFallData;
    }
    self.scrollReload = function() {
        var wh = parseInt($(window).height());
        var ws = parseInt($(window).scrollTop());
        var dh = document.body.scrollHeight;
        if( dh - 90 <= wh + ws && !self.ajaxLock){
            self.ajaxLock = true;
            setTimeout(function(){
                self.ajaxPage++;
                self.loadData();
            },1000);
        }
    }
    self.loadData = waterFallAjax;
    self.loadDataHandle = function(data,tmpl,msg){
    	$(".resultNull").remove();
	    if((!data.list || data.list.length <= 0) && self.ajaxPage <= 1){
	    	self.listElm.hide().after('<p class="resultNull">'+(msg || data.message || "对不起，没有查找到相关数据")+'</p>');
	    }
	    else{
	    	var render = template.compile(tmpl);
    	 	var html = render(data);
	        self.listElm.append(html).show();
	        self.listElm.find('.lazy').not('.lazyLoaded').addClass("lazyLoaded").lazyload({
				effect : 'fadeIn'
			});
	    }
	    if(listElm.children().length < data.total){
	        self.loadElm.removeClass("loading").show().find(".text").text("上拉有更多信息");
	        self.loadBtn.removeClass("loading").show().find(".text").text("点击加载更多");
	    }
	    else{
	        $(window).unbind('scroll',self.scrollReload);
	        $("#pageLoadBtn").unbind("click");
	        if((!data.list || data.list.length <= 0) && self.ajaxPage <= 1){
	        	self.loadElm.hide();
	        	self.loadBtn.hide();
	        }
	        else{
	        	self.loadElm.removeClass("loading").show().find(".text").text("没有更多了");
	        	self.loadBtn.removeClass("loading").show().find(".text").text("没有更多了");
	            setTimeout(function(){
	                self.loadElm.hide();
	        		self.loadBtn.hide();
	            },3000);
	        }
	    }
	    self.ajaxLock = false;
    }
    self.config();
    return self;
}

//订单confirm回调处理
function modal_confirm_handle_order(dom){
	$('#modal_confirm').modal({
		relatedTarget: dom,
        onConfirm: function(options) {	
        	var $link = $(this.relatedTarget);
        	var $wrap = $link.parents(".orderItem");
        	var submitData = {
        		orderNo : $link.data("orderno")
        	}
        	$.ajax({
        		url : baseUrl+""+$link.attr("href"),
			    type : "post",
			    data : submitData,
			    success : function(data) {  
			    	if(data.status == 'success'){
			    		//成功取消订单
		       			if($link.hasClass("orderCancel")){
//							$wrap.find(".orderOperate").html('<a class="btn btnH" disabled="disabled">关闭交易</a>');
//				    		$wrap.find(".state").text("交易取消");
							$wrap.find(".orderOperate").html('<a class="btn btnH orderDelete" data-orderno="'+submitData.orderNo+'" href="/mobile/delmyorder.do">删除订单</a><a class="btn btnS" href="pdtDetail.html?pid='+$wrap.data("pid")+'#anchor_review">去评价</a>');
				    		$wrap.find(".state").text("已完成");
						}
			    		//成功删除订单
						else if($link.hasClass("orderDelete")){
							if($("#render_orderDetail").length > 0){ //订单详情页
								location.href = "mineOrders.html";
							}
							else{ //订单列表页
								$wrap.remove();
								if($(".orderList").height() < $(window).height() - 200){
					       			loader.loadData();
					       		}
							}
						}
			          	$('#modal_confirm').modal('close');
		    	 	}
		       		else if(data.status == 'LoginFailed'){
		       			modal_alert("用户未登录或者登陆已过期，请重新登陆!","userLogin.html")
		       		}
		       		else{
			            modal_alert(data.message)
		       		}	        
			    }
			})
        },
        onCancel: function() {
          	$('#modal_confirm').modal('close');
        }
  	});
}

//获取验证码
function getCaptcha(dom){
	var _this = dom; 
	if(_this.hasClass("am-active")){
	    return false;
	}     
	var phone = $("#phone").val().replace(/\s/g, ""); 
	if(typeof(phone) !== 'undefined'){
	    if(phone == ""){
	        modal_alert('请填写您的手机号');
	        return false;
	    } 
	    var regu = /^\s*1\d{10}\s*$/;
	    var re = new RegExp(regu);
	    if (!re.test(phone)) {
	        modal_alert("请输入正确的手机号");
	        return false;
	    }
	} 
	var cd = _this.data("cd")-1;
	_this.addClass("am-active").text(cd+"秒后重发");
	var st = setInterval(function(){
	    --cd;             
	    _this.addClass("am-active").text(cd+"秒后重发");
	    if(cd <= 0){
	        _this.removeClass("am-active").text("获取验证码");
	        clearInterval(st);
	    }
	},1000)
	getCaptcha_ajax(phone);
}

//发送短信获取验证码ajax
function getCaptcha_ajax(tel){
	$.ajax({
	    url : baseUrl+"mobile/sendTelVerifyCode.do",
	    type : "post",
	    data : {
	        tel : tel
	    },
	    success:function(data) {  
	        if(data.status == 'success'){
    	 		
    	 	}
    	 	else{
    	 		modal_alert(data.message);
    	 	}
	    }
	})
}

//设置星级HTML
function setStarHtml(){
	$(".starLv").each(function(){
		if(!$(this).html()){
			var startHtml = "";
			var lv = $(this).data("lv");
			for(var i = 1; i <= 5; i ++){
				if(i <= parseInt(lv)){
					startHtml += '<span class="am-icon-star"><span class="am-icon-star" style="width:100%;"></span></span>';
				}
				else if(i <= parseInt(lv)+1){
					startHtml += '<span class="am-icon-star"><span class="am-icon-star" style="width:'+lv%1*100+'%;"></span></span>';
				}
				else{
					startHtml += '<span class="am-icon-star"><span class="am-icon-star"></span></span>';
				}
			}
			$(this).html(startHtml);
		}		
	})
}

//modal_confirm
function modal_confirm(text){
	if($("#modal_confirm").length <= 0){
		$("body").append('<div class="am-modal am-modal-confirm" tabindex="-1" id="modal_confirm" style="display:block;"><div class="am-modal-dialog"><div class="am-modal-bd">' + (text || '你，确定要执行该操作吗？') + '</div><div class="am-modal-footer"><span class="am-modal-btn" data-am-modal-cancel>取消</span><span class="am-modal-btn" data-am-modal-confirm>确定</span></div></div></div>');
	}
	else{
		$("#modal_confirm").find(".am-modal-bd").html(text || '你，确定要执行该操作吗？');
	}
	setTimeout(function(){
		$("#modal_confirm").addClass("am-modal-active");
	},50)
}

//modal_alert
function modal_alert(text,href){
	if($("#modal_alert").length <= 0){
		var btn = href ? '<a class="am-modal-href" href="'+href+'">确定</a>' : '<span class="am-modal-btn">确定</span>';
		$("body").append('<div class="am-modal am-modal-confirm" tabindex="-1" id="modal_alert" style="display:block;"><div class="am-modal-dialog"><div class="am-modal-bd">' + (text || '你，确定要执行该操作吗？') + '</div><div class="am-modal-footer">'+btn+'</div></div></div>');
	}
	else{
		$("#modal_alert").find(".am-modal-bd").html(text || '你，确定要执行该操作吗？');
	}
	$('#modal_alert').modal();
	setTimeout(function(){
		$("#modal_alert").addClass("am-modal-active");
	},50)
}

//表单验证-带错误提示
function formOnValid(validity,$this){
  	$(validity.field).closest('.am-form-group').find('.am-alert').hide();
}
function formOnInValid(validity,$this){
	var $field = $(validity.field);
	var $group = $field.closest('.am-form-group');
	var $alert = $group.find('.am-alert');
	// 使用自定义的提示信息 或 插件内置的提示信息
	var msg = $field.data('validationMessage') || $this.getValidationMessage(validity);	
	if (!$alert.length) {
		$alert = $('<div class="am-alert am-alert-danger"></div>').hide().
	  	appendTo($group);
	}	
	$alert.html(msg).show();
}

//表单数据对象打包
function formFieldPackage(fields){
	var data = new Object();
	fields.each(function(i,item){
		data[item.name] = item.value;
	})
	return data;
}

//storage支持检测
function storageIsSupport(){
	if(!window.store){
		window.store = $.AMUI.store;
	}
	if(!store.enabled) {
	  	modal_alert('您的浏览器不支持本地存储。请禁用“私人模式”,或升级到一个现代浏览器。');
	  	return false;
	}
	return true;
}

//通用验证
function checkForm(form){
    var returnVal = true;  //表单是否可提交
    var formElm = $(form);  //验证的当前表单  
	if(formElm.find("[type='submit']").val() == "提交中..."){
		return false;
	}
    var submitItems = formElm.find("[name]");  //表单所有提交项

    submitItems.each(function(i,item){
        if($(item).attr("data-tipNull")){  //为必填项
            if($(item).attr("type") == "text" || $(item).attr("type") == "password" || $(item).attr("type") == "hidden" || $(item).attr("type") == "file" || $(item).attr("type") == "number" || $(item)[0].tagName == "SELECT" || $(item)[0].tagName == "TEXTAREA"){
                var itemVal = $(item).val();
                if(!itemVal || itemVal.replace(/\s/g, "") == ""){
                    modal_alert($(item).attr("data-tipNull"));
                    returnVal = false;
                    return false;
                }
            }
            if($(item).attr("type") == "radio" || $(item).attr("type") == "checkbox"){
                if(!formElm.find('[name="'+$(item).attr("name")+'"]:checked').val()){
                    modal_alert($(item).attr("data-tipNull"));
                    returnVal = false;
                    return false;
                }
            }
            if($(item)[0].tagName == "SELECT" && ($(item).val() == "请选择省" || $(item).val() == "请选择市" || $(item).val() == "请选择区")){
                modal_alert($(item).attr("data-tipNull"));
                returnVal = false;
                return false;
            }
        }
        if($(item).attr("data-reg")){  //填写不符合正则规则
            var itemVal = $(item).val().replace(/\s/g, "");
            if(itemVal != "" && itemVal != null){
                var regu = eval($(item).attr("data-reg"));
                var re = new RegExp(regu);
                if (!re.test(itemVal)) {
                    modal_alert($(item).attr("data-tipReg"));
                    returnVal = false;
                    return false;
                }
            }
        }
        //两次输入不一致(仅限一个比较项)
        if(submitItems.filter("[data-same]").length > 0){
            var itemSameVal1 = submitItems.filter("[data-same]").eq(0).val().replace(/\s/g, "");
            var itemSameVal2 = submitItems.filter("[data-same]").eq(1).val().replace(/\s/g, "");    
            if(itemSameVal1 != "" && itemSameVal2 != "" ){  
                if(itemSameVal1 != itemSameVal2){
                    modal_alert(submitItems.filter("[data-same]").eq(1).attr("data-tipSame"));
                    returnVal = false;
                    return false;
                }
            }
        }
    }) 
	
	if(returnVal){
		formElm.find("[type='submit']").val("提交中...");
		$("body").append('<div class="formLoading"><div class="spnr"><i class="am-icon-spinner am-icon-pulse"></i><p class="text">提交中</p></div></div>')
	}

    return returnVal;
}

//获取网页URL参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return decodeURI(r[2]); return null; //返回参数值
}

//获取backUrl参数
function getBackUrl(){
	var pathnameArray = location.pathname.split("/");
	return pathnameArray[pathnameArray.length-1]+location.search;
}

jQuery.fn.extend({
	//添加类名并且清除同类该类名
	tabClass:function(className) {
		return this.each(function(){
			$(this).addClass(className).siblings().removeClass(className)
		})
	}
})