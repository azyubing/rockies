$(document).ready(function() {
	paramVerify();
})

//参数验证
function paramVerify(){
	var pid = getPid();
    if(!pid){
    	history.replaceState({foo:"bar"}, "", "index.html");
    	modal_alert("不存在此商品","index.html")  	
    }
    else{
    	eventBind();
    	renderView();
    }
}

//操作事件绑定
function eventBind(){
	//进入购买页
	$("#detailBar").delegate("#pdtBook","click",function(){
		var uinfo = store.get("mw_uinfo");
		if(uinfo){
			store.remove("mw_orderinfo");
			location.href = this.href;
		}
		else{
			location.href = "userLogin.html?backurl="+getBackUrl();
		}
		return false;
	})
	//添加收藏/取消收藏
	$("#collectBtn").click(function(){
		if($(this).hasClass("am-disabled")){
			return false;
		}
		var pid = getPid();
		if($(this).hasClass("collected")){
			collectCancel(pid);
		}
		else{
    		collectAdd(pid);
		}
  	})
	//评价
	$("#reviewSubmit").click(function(){
		var form = document.getElementById("formReview");
		if(checkForm(form)){
			var formData = formFieldPackage($(form).find("[name]"));
			$.extend(true,formData,{
		    	pid : getPid()
		   })
			$.ajax({
		     	url: baseUrl+"mobile/productComments.do",
		     	type: "post",
		     	data: formData,
		     	success: function(data) {
		    	 	if(data.status == 'success'){
		    	 		var renderData = {list:[data.body]};
		    	 		var render = template.compile(tmpl_productEval);
			    	 	var html = render(renderData);
				        self.listElm.prepend(html);
				        self.listElm.find('.lazy').not('.lazyLoaded').addClass("lazyLoaded").lazyload({
							effect : 'fadeIn'
						});
		    	 	}
		    	 	else if(data.status == 'LoginFailed'){
			       		store.remove("mw_uinfo");
		       			modal_alert("用户未登录或者登陆已过期，请重新登陆!","userLogin.html");
		       		}
		       		else{
			            modal_alert(data.message);
		       		}
		    	 	form.reset();
		       		$(".formLoading").remove();
		     	}
			});
		}
	})
	//分享配置
	$('#sharePanel').share({
		url                 : 'index.html', // 网址，默认使用 window.location.href
		source              : '', // 来源（QQ空间会用到）, 默认读取head标签：<meta name="site" content="http://overtrue" />
		title               : '麦味旅行，全球家庭主题定制旅行第一品牌', // 标题，默认读取 document.title 或者 <meta name="title" content="share.js" />
		description         : '全球家庭主题旅行平台，最好的爱是陪伴', // 描述, 默认读取head标签：<meta name="description" content="PHP弱类型的实现原理分析" />
		image               : 'images/shareImg.jpg', // 图片, 默认取网页中第一个img标签
		sites               : ['qq', 'qzone', 'weibo', 'tencent', 'douban'], // 启用的站点
		disabled            : ['google', 'facebook', 'twitter'], // 禁用的站点
		wechatQrcodeTitle   : "微信扫一扫：分享", // 微信二维码提示文字
		wechatQrcodeHelper  : '<p>微信里点“发现”，扫一下</p><p>二维码便可将本文分享至朋友圈。</p>'
	});
}


//视图渲染
function renderView(){
	var pid = getPid();
	//根据pid获取产品详细信息
	$.ajax({
    	url : baseUrl+"mobile/productDetail.do",
        type : "get",
        data : {
        	pid : pid
        },
        success : function(data) {
       		if (data.status == 'success' && data.body.length > 0) {
       			var dataPdt = data.body[0];
       			store.set('mw_pinfo',dataPdt);
       			var render;
	    	 	var html = "";
	    	 	var id;
	    	 	render = template.compile(tmpl_productBnr);
				html = render(dataPdt);
				id = "pdtBnrSlide";
	    	 	document.getElementById(id).innerHTML = html;
	    	 	$('#pdtBnr').flexslider();
	    	 	render = template.compile(tmpl_productBase);
				html = render(dataPdt);
				id = "pdtBase";
	    	 	document.getElementById(id).innerHTML = html;
	    	 	render = template.compile(tmpl_productBook);
				html = render(dataPdt);
				id = "detailBar";
	    	 	document.getElementById(id).innerHTML = html;
				//面板数据
	    	 	document.getElementById("panel_desc").innerHTML = dataPdt.pdesc;
	    	 	document.getElementById("panel_fee").innerHTML = dataPdt.price_desc;
	    	 	document.getElementById("panel_notes").innerHTML = dataPdt.pnote;
	    	 	//是否已收藏
	    	 	dataPdt.marked ? $("#collectBtn").addClass("collected") : $("#collectBtn").removeClass("collected");
    	 	}
       		else{
	            modal_alert(data.message || "对不起，不存在此商品")
       		}
        }
    });
    //根据pid获取行程信息
    if($("#panel_sche").length > 0){
    	$.ajax({
	    	url : baseUrl+"mobile/productRouteInfo.do",
	        type : "get",
	        data : {
	        	pid : pid
	        },
	        success : function(data) {
	       		if(data.status == 'success'){
	       			if(data.body.length > 0){
	       				var render = template.compile(tmpl_productSche);
						var html = render(data);
			    	 	$("#panel_sche").html(html);
			    	 	$("#panel_sche").find('.am-panel-title').click(function(){
			    	 		$(this).parent().siblings(".am-panel-collapse").collapse("toggle");
			    	 	});
	       			}
	       			else{
						$("#panel_sche").parents(".am-accordion-item").remove();       				
	       			}
	    	 	}
	        }
	    });
    }
    //根据pid获取评论列表
    window.cxtWf = waterFall();
    //产品评价-根据哈希值跳转到锚点特定区域
    if(location.href.indexOf("anchor_review") > 0){
    	$(".am-accordion-item").last().addClass("am-active").find(".am-accordion-bd").addClass("am-in");
    	setTimeout(function(){
    		$(window).smoothScroll({speed:10,position:$(document).height() - $(window).height()});
    	},1000)
    }
}

//评论分页数据加载_数据请求
function waterFallAjax(){
	var pid = getPid();
	loader = this;
	var ajaxParam = $.extend(loader.ajaxParam,{
		page : loader.ajaxPage,	
        pid : pid
	})
    $.ajax({
    	url : baseUrl+"mobile/productComments.do",
        type : "get",
        data : ajaxParam,
        beforeSend : function() {
    		$(".resultNull").remove();
            loader.loadBtn.addClass("loading").show().find(".text").text("努力加载中...");
        },
        success : function(data) {
       		if(data.status == 'success'){
	    	 	loader.loadDataHandle(data.body,tmpl_productEval,"暂时没有任何评论");
    	 	}
       		else{
	            loader.listElm.hide().after('<p class="resultNull">'+(data.message || "暂时没有任何评论")+'</p>');
       		}
        }
    });
}

//收藏-添加
function collectAdd(pid){
	$("#collectBtn").addClass("am-disabled");
	$.ajax({
        url : baseUrl+"mobile/myfavorites.do",
        type : "post",
        data : {
        	pid : pid
        },
        success:function(data) {
        	if (data.status == 'success') {
	    	 	$("#collectBtn").addClass("collected");
	    	 	$("#markedTimes").text(parseInt($("#markedTimes").text())+1);
    	 	}
       		else if(data.status == 'LoginFailed'){
	       		store.remove("mw_uinfo");
       			modal_alert("用户未登录或者登陆已过期，请重新登陆!","userLogin.html");
       		}
       		else{
	            modal_alert(data.message);
       		}
    	 	$("#collectBtn").removeClass("am-disabled");
        }
  	})
}

//收藏-取消
function collectCancel(pid){
	$("#collectBtn").addClass("am-disabled");
	$.ajax({
        url : baseUrl+"mobile/delmyfavorites.do",
        type : "post",
        data : {
        	pid : pid
        },
        success:function(data) {
        	if (data.status == 'success') {
	    	 	$("#collectBtn").removeClass("collected");
	    	 	$("#markedTimes").text(parseInt($("#markedTimes").text())-1);
    	 	}
       		else if(data.status == 'LoginFailed'){
	       		store.remove("mw_uinfo");
       			modal_alert("用户未登录或者登陆已过期，请重新登陆!","userLogin.html");
       		}
       		else{
	            modal_alert(data.message);
       		}
    	 	$("#collectBtn").removeClass("am-disabled");
        }
  	})
}

//pid获取
function getPid(){
	var pid = getUrlParam("pid");
	if(pid){
		var idx = pid.indexOf("#");
		if(idx > 0){
			pid = pid.slice(0,idx);
		}
	}
	return pid;
}