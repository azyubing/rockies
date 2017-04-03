
function setTopHeight(){

	//set center width
	var ww = $(window).width();
	var wwScale =940;
	if(ww>1180 && ww<1600){
		wwScale = (ww-240);
	}else if(ww>=1600){
		wwScale = 1360;
	}
	wwScale -= 23;
	if(isExpend){
		$(".center_content").css("width",(wwScale+190)+"px");		
	}else{
		$(".center_content").css("width",wwScale+"px");				
	}
	
	var centerHeight = $(".center_content").height();
	$(".left_content>.trade_list").css("height",(centerHeight-80)+"px");
	$(".left_content>.trade_list>.l_items").css("height",(centerHeight-80)+"px");

}

$(function(){

	//left
	$("#expendBtn").click(function(){
	  var isroute = false;  
	 if($("#left_header_open").is(":hidden") && $("#left_header_close").is(":hidden")){
		 isroute = true;
	 } 
	  
	 if($("#l_login_open").is(":hidden")){
		 $("#l_login_open").show();
		 $("#l_login_close").hide();
		 isExpend = false;
	 }else{
		 $("#l_login_open").hide();
		 $("#l_login_close").show();
		 isExpend = true;
	 }
	
	 if(!isroute){	 
		 if($("#left_header_open").is(":hidden")){
			 $("#left_header_open").show();
			 $("#left_header_close").hide();
			 isExpend = false;
		 }else{
			 $("#left_header_open").hide();
			 $("#left_header_close").show();
			 isExpend = true;
		 }
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
			$("#close_man_pic").remove();
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
			var manimg = $("#l_header_content>.p_img").attr("src");
			$("#createBtn").parent().append('<div id="close_man_pic" style="border: 1px solid #e3e3e3;width: 43px;margin-left: 4px;margin-top:10px;"><img src="'+manimg+'" style="width:40px;height:40px;border:3px solid #fff" ></div>');	
			isExpend = true;
			setTopHeight();
			 $(".p_img_style").css({
				 "width":$(".col").width()+"px",
				 "height":$(".col").width()/2+"px"
			 });
		}
		
	});
});

