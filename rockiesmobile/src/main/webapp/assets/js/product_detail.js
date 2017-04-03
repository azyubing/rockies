/**
 *  custom JS
 */

(function($){
  $.fn.extend({
    setScroll:function(opt){
      if($(this).length<1 || !opt.startTop){return;}
      var isIE6 = !-[1,]&&!window.XMLHttpRequest,
    //    Timmer= null,
        $this = $(this),$win = $(window),
        startTop = opt.startTop;
      /*初始化top位置*/
      if(opt.defaultTop){$this.css('top',opt.defaultTop);}

      /*开始滚动和回复原样的位置*/
      $win.bind('scroll',function(){
    //    if(Timmer){clearTimeout(Timmer);}
  //      Timmer = setTimeout(function(){
          if($win.scrollTop()>=startTop){/*开始执行事件*/
            if(isIE6){
              $this.css({'position':'absolute','top':$win.scrollTop()});
            }else{
            var ww = $this.width();	
              $this.css({'position':'fixed','top':0,"width":ww+"px"});
            }
          }else{
        	  $this.css({'position':'relative','top':"-60px",'width':"100%","z-index":"999"});
          }
  //      },1);
        
      });
    }
  });
})(jQuery);





function initSlides(divId){
	$('.'+divId).slidesjs({
		  width: 824,
		  height: 312,
        navigation: {effect: "fade"},
        pagination: false, 
        effect: {fade: {speed: 350,crossfade: true}}
	  });
}

function setTopHeight(){
	//set center width
	var ww = $(window).width();
	var wwScale =640;
	if(ww>1180 && ww<1600){
		wwScale = (ww-540);
	}else if(ww>=1600){
		wwScale = 1060;
	}
	if(isExpend){
		$(".center_content").css("width",(wwScale+190)+"px");		
	}else{
		$(".center_content").css("width",wwScale+"px");				
	}
//	$(".center_content").css("width",wwScale+"px");		
	var centerHeight = $(".center_content").height();
	$(".left_content>.trade_list").css("height",(centerHeight-80)+"px");
	$(".left_content>.trade_list>.l_items").css("height",(centerHeight-80)+"px");
	
	//set slides height keep 2:1 
	var slideswidth = $("#myCarousel>.carousel-inner>.item").width();
	$("#myCarousel>.carousel-inner").find("img").css({"height":slideswidth/2+"px"});
	if(ww>= 1900){
		//villa hotel
		var ptype = $("#myCarousel").data("type");
		if(ptype=='villa' || ptype =='hotel'){
			$("#myCarousel>.carousel-inner").find("img").css({"height":slideswidth/2+"px", "width":(slideswidth-1)+"px"});				
		}
	}
	//set navigation
	$(".slidesjs-navigation").addClass("opacity-hide");
	var navTop = -($("#myCarousel>.carousel-inner").height()/2+25);
	$(".slidesjs-navigation").css("top",navTop+"px");
	
	// set hotel room type img keep 2:1
	var slideswidth = $("#main_img_1").width();
	$("#main_img_1").css("height",slideswidth/2+"px");
}

$(function(){
	
	/*
	$("#expendBtn").click(function(){
		var ele = $(".th2");
		if(ele.is(":hidden")){
			$(this).find("img").attr("src","../assets/img/UI/expend_l.png");
			$(".left_content").css("width","240px");
			$(".th2,.t2").show();
			$("#price_style").show();
			$("#add_style").css({"width":"220px","font-size":"12px","line-height": "32px","margin-left":"10px"}).text("添加一天行程");
			$("#createBtn").css({"width":"220px","margin-left":"9px","height":"40px","line-height":"40px"}).empty().text("生成行程单");
			isExpend = false;
			$("#man_icon").remove();
			setTopHeight();
		}else{
			var icon = $(".angleDown").find("i");
			if(icon.hasClass("fa-angle-up")){
				icon.removeClass("fa-angle-up");
				icon.addClass("fa-angle-down");		
				$("#"+ $(".angleDown").attr("id")+"_date").css({"background-image":"none",
					"border-bottom":"1px solid #E5E5E5"});
				$("#"+ $(".angleDown").attr("id")+"_date>.t1_p1").css("color","#666");
				$("#"+ $(".angleDown").attr("id")+"_date>.t1_p2").css("color","#999");
				$("#"+ $(".angleDown").attr("id")+"_detail").hide();
			}
			
			
			$(this).find("img").attr("src","../assets/img/UI/expend_r.png");
			$(".left_content").css("width","50px");
			$(".th2,.t2").hide();
			$("#price_style").hide();
			$("#add_style").css({"width":"40px","font-size":"32px","margin-left":"6px","line-height": "24px"}).text("+");
			$("#createBtn").css({"width":"40px","margin-left":"6px","height":"32px","line-height":"26px"}).empty().append("<img src='../assets/img/UI/book_icon.png'>");
			isExpend = true;
			var manPic = $(".th2>.p_img");
			$(".left_content").append('<a href="#" id="man_icon" style="border:1px solid #eee;display:block;width:40px;margin:5px 0 0 5px;">'+
					'<img src="'+manPic.attr("src")+'" style="width:40px;height:40px;border:3px solid #fff" ></a>');
			setTopHeight();
		}*/
	
	//left
	$("#expendBtn").click(function(){
	 if($("#l_login_open").is(":hidden")){
		 $("#l_login_open").show();
		 $("#l_login_close").hide();
		 isExpend = false;
	 }else{
		 $("#l_login_open").hide();
		 $("#l_login_close").show();
		 isExpend = true;
	 }
		
	 if($("#left_header_open").is(":hidden")){
		 $("#left_header_open").show();
		 $("#left_header_close").hide();
		 isExpend = false;
	 }else{
		 $("#left_header_open").hide();
		 $("#left_header_close").show();
		 isExpend = true;
	 }
	 
		var ele = $(".th2");
		if(ele.is(":hidden")){
			$(this).find("img").attr("src","../assets/img/UI/expend_l.png");
			$(".left_content").css("width","240px");
			$(".th2,.t2").show();
			$("#price_style").show();
			$("#add_style").css({"width":"220px","font-size":"12px","line-height": "32px","margin-left":"10px"}).text("添加一天行程");
			$("#createBtn").css({"width":"220px","margin-left":"9px","height":"40px","line-height":"40px"}).empty().text("生成行程单");
			isExpend = false;
			$("#man_icon").remove();
			setTopHeight();
			 $(".p_img_style").css({
				 "width":$(".col").width()+"px",
				 "height":$(".col").width()/2+"px"
			 });
		}else{
	
			var icon = $(".angleDown").find("i");
			if(icon.hasClass("fa-angle-up")){
				icon.removeClass("fa-angle-up");
				icon.addClass("fa-angle-down");		
				$("#"+ $(".angleDown").attr("id")+"_date").css({"background-image":"none",
					"border-bottom":"1px solid #E5E5E5"});
				$("#"+ $(".angleDown").attr("id")+"_date>.t1_p1").css("color","#666");
				$("#"+ $(".angleDown").attr("id")+"_date>.t1_p2").css("color","#999");
				$("#"+ $(".angleDown").attr("id")+"_detail").hide();
			}

			$(this).find("img").attr("src","../assets/img/UI/expend_r.png");
			$(".left_content").css("width","50px");
			$(".th2,.t2").hide();
			$("#price_style").hide();
			$("#add_style").css({"width":"40px","font-size":"32px","margin-left":"6px","line-height": "24px"}).text("+");
			$("#createBtn").css({"width":"40px","margin-left":"6px","height":"32px","line-height":"26px"}).empty().append("<img src='../assets/img/UI/book_icon.png'>");
			isExpend = true;
		
			
			setTopHeight();
			 $(".p_img_style").css({
				 "width":$(".col").width()+"px",
				 "height":$(".col").width()/2+"px"
			 });
		}
		
	});
});

//轮播图懒加载
function lazyContainer(searchNode){
	$(searchNode).find(".active").find("img.lazy-carousel").each(function(){
		
		 var imgSrc = $(this).attr("data-original");
    	  if(imgSrc){
    		  $(this).attr("src",imgSrc);
    	  }
	});
}
$("#myCarousel").bind("slid.bs.carousel",function(){
	lazyContainer(this);
});
lazyContainer("#myCarousel");
