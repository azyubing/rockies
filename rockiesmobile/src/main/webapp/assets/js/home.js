/**
 * global custom JS
 */

function initCounter() {
    jQuery('.counter').counterUp({
        delay: 10,
        time: 1000
    });
};

function changeDesc2(jqObj){
	setTimeout(function(){
		var themeId = jqObj.data("tid");
		var itema = $("#"+themeId+">.slidesjs-pagination>.slidesjs-pagination-item>a");
		for(var i=0;i<itema.length;i++){
			if($(itema[i]).hasClass("active")){
				changeDesc($(itema[i]));
			}		
		}		
	},300);
}

function changeDesc(jqObj){
	var elemid = "tp_"+jqObj.data("tpid");
	var themeid = jqObj.data("themeid");
	$(".c_"+themeid).hide();
	$("#"+elemid).show();
}


function initSlides(divId){
	$('.'+divId).slidesjs({
		width: 940,height: 470,preload: true,
        navigation: {effect: "fade"},
        pagination: {active: true,effect: "fade",type: "hover"}, 
        effect: {fade: {speed: 350,crossfade: true}}
	  });
}

function setThemeTopHeight(){
	if($("#banner-bar>.banner-searchbar>span").hasClass("active")){
		$("#banner-bar>.banner-searchbar>span>.fa-caret-up").removeClass("fa-caret-up").addClass("fa-caret-down")
		.css("color","#ccc").parent().removeClass("active");
		
		var bannerHeight = $("#banner_video").height();
		$("#b_search_tip").css({top:(bannerHeight-270)+'px',opacity:0});
	}
	

	var div1=$(".slidesjs-container");
	var div2=$(".theme-content");
	var extendVal = 0;
	var explorer = window.navigator.userAgent ;
	//ie 
	if (explorer.indexOf("MSIE 8.0") >= 0) {
		var dm = document.documentMode; 
		if (dm==8){
			extendVal = 1;
		}
	}
	//Safari
	else if(explorer.indexOf("Safari") >= 0){
			extendVal = 1;
	}
	
	$(".theme-bottom").css('height',(div1.height()*0.33+extendVal)+'px');
	$(".theme-bottom").css('width',($(".theme-top").width()+85)+'px');
	$(".theme-top").css('height',div1.height()*0.67+'px');
	//垂直居中
	div2.css("top", (div1.height()*0.67/2-div2.height()/2)+"px");
	//
	var navTop = -(div1.height()/2+25);
	$(".slidesjs-navigation").css("top",navTop+"px");
	div1.each(function(){
		var parentId = $(this).parent().attr("id");   
		var htmlStrLeft =  "<img id=\""+parentId+"_pre\" src=\"assets/img/left_btn.png\"   class=\"opacity-hide\" alt=\"上一页\">";
		var htmlStrRight = "<img id=\""+parentId+"_next\" src=\"assets/img/right_btn.png\" class=\"opacity-hide\" alt=\"下一页\">";
		$("#"+parentId+">.slidesjs-previous").empty().addClass("left-btn opacity-hide");
		$("#"+parentId+">.slidesjs-next").empty().addClass("right-btn opacity-hide");	
	});
	
	$('#banner_video').carousel({interval: 3000});
	//set next/pre hover 
	$(".slidesjs-container,.slidesjs-navigation").hover(
		function(){
			$(".slidesjs-navigation").removeClass("opacity-hide").addClass("opacity-harf-show");
		},
		function(){
			$(".slidesjs-navigation").removeClass("opacity-harf-show").addClass("opacity-hide");
		}
	);
		
	
	var wh = $(window).height();
	//if(wh<=650){
	//	$("#banner_video,#tmp_img").css({"height":$("#banner_video").height()+"px", "width":$(document).width()+"px"});		
	//}else{	
	//	$("#banner_video,#tmp_img").css({"height":$("#banner_video").height()+"px", "width":$(document).width()+"px"});		
	//}
	
	//set banner
	var scaleH=$("#banner_video").height()/535;
	var wscale = "100%";
	if($("#banner").width()<=1180){
		wscale = "1180px";
	}

	$("#b_title").css("width",wscale);
	$("#banner-bar").css("width",wscale);
	$("#b_search_tip").css("width",wscale);
	
	if($("#banner_video").height()>633){
		$("#b_title").css("top",scaleH*210+"px");
		$("#banner-bar").css("top",scaleH*550+"px")	
	}else{
		$("#b_title").css("top",scaleH*230+"px");
		$("#banner-bar").css("top",scaleH*570+"px")		
	}
	
	//set header
	$("#main_content").removeClass().addClass("content1");
	$(".navbar-custom").removeClass().addClass("navbar-custom1");
	$(".sub-navbar").removeClass().addClass("sub-navbar1");	
}


//对Date的扩展，将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
//例子： 
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function (fmt) { //author: meizz 
 var o = {
     "M+": this.getMonth() + 1, //月份 
     "d+": this.getDate(), //日 
     "h+": this.getHours(), //小时 
     "m+": this.getMinutes(), //分 
     "s+": this.getSeconds(), //秒 
     "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
     "S": this.getMilliseconds() //毫秒 
 };
 if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
 for (var k in o)
 if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
 return fmt;
}