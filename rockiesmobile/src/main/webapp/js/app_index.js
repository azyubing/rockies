$(document).ready(function() {
	//首页-搜索框
    $('#searchOpen').on('click', function() {
    	$(".searchBox .mask").height($(window).height());
      	$(".searchBox").toggle();
    });
    $('.searchBox .mask').on('click', function() {
    	$(".searchBox").hide();
    });
    $("#searchSubmit").on('click', function() {
    	var searchKeyWords = $("#searchField").val();
    	if(searchKeyWords != ""){
    		$('#searchField').val("");
    		location.href = "pdtList.html?searchKeyWords="+encodeURI(encodeURI(searchKeyWords));
    	}
    	else{
    		location.href = "pdtList.html";
    	}
    	return false;
    });
	//头部登录头像
	render_loginStatus(store.get("mw_uinfo") || new Object());

	$.ajax({
		url: baseUrl+"mobile/banners.do",
		type: "get",
		success: function(data) {
			if (data.status == 'success') {
				var render = template.compile(tmpl_banners);
				var html = render(data);
				$('#indexBnrSlide').html(html);
					$('#indexBnr').flexslider();
			}
		}
	});



	//精选主题旅行
	$.ajax({
     	url: baseUrl+"mobile/recommendThemeProducts.do",
     	type: "get",
		data: {
     		page : 1,
     		pageSize : 5
     	},
     	success: function(data) {
    	 	if (data.status == 'success') {
	    	 	var render = template.compile(tmpl_hotThemes);
				var html = render(data);
 				$('#themeList').html(html);
	    	 	$('#themeList .lazy').not('.lazyLoaded').addClass("lazyLoaded").lazyload({
					effect : 'fadeIn'
				});
    	 	}
     	}
	});
	//精选热门目的地
	$.ajax({
     	url: baseUrl+"mobile/hotDestinations.do",
     	type: "get",
		data: {
     		page : 1,
     		pageSize : 3
     	},
     	success: function(data) {
    	 	if (data.status == 'success') {
	    	 	var render = template.compile(tmpl_hotDest);
				var html = render(data);
 				$('#destList').html(html);
	    	 	$('#destList .lazy').not('.lazyLoaded').addClass("lazyLoaded").lazyload({
					effect : 'fadeIn'
				});
    	 	}
     	}
	});
	//热门单项服务
	$.ajax({
     	url: baseUrl+"mobile/hotSingleServices.do",
     	type: "get",
     	data: {
     		page : 1,
     		pageSize : 2
     	},
     	success: function(data) {
    	 	if (data.status == 'success') {
	    	 	var render = template.compile(tmpl_hotService);
				var html = render(data);
 				$('#serveList').html(html);
	    	 	$('#serveList .lazy').not('.lazyLoaded').addClass("lazyLoaded").lazyload({
					effect : 'fadeIn'
				});
    	 	}
     	}
	});
});

//渲染头部登录状态
function render_loginStatus(uinfo){
	var render = template.compile(tmpl_loginStatus);
	var html = render(uinfo);
 	$('#loginStatus').html(html);
}
