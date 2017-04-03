
var routeJson = '';
//添加行程
function appendTrafficJson(s,rowsstr,type){
	$.ajax({    
        type:'post',        
        url:'../addRouteStr.html',    
        data:{"str":s,"type":type},    
        cache:false,    
        dataType:'json',    
        success:function(data){
        	window.location.reload();
        	//$("#right_body").html('');
        	//builderRoute(data);
        	//routeJson = data;
        }    
    });
}
//添加行程
function addStartTime(stardt){
	$.ajax({    
        type:'post',        
        url:'../addStartTime.html',    
        data:{"stardt":stardt},    
        cache:false,    
        dataType:'json',    
        success:function(data){
        }    
    });
}
//添加一天行程
function addDateTime(stardt){
	$.ajax({    
        type:'post',        
        url:'../addDateTime.html',    
        data:{"stardt":stardt},    
        cache:false,    
        dataType:'json',    
        success:function(data){
        }    
    });
}


//清空行程
function removeAllRoute(){
	$.ajax({    
        type:'post',        
        url:'../removeAllRouteStr.html',    
        data:{},    
        cache:false,    
        dataType:'json',    
        success:function(data){
        	builderRoute(data);
        	routeJson = data;
        }    
    });
}

//删除行程中的某一项
function removeRouteAttr(type,date,timestamp){
	$.ajax({    
        type:'post',        
        url:'../removeRouteStr.html',    
        data:{"date":date,"type":type,"timestamp":timestamp},    
        cache:false,    
        dataType:'json',    
        success:function(data){
        	$("#right_body").html('');
        	builderRoute(data);
        	routeJson = data;
        }    
    });
}

//有效价格项
function getpriceItemDetail(date){
	var pid = $("#pid").val();
	//类型
	var type=$("#productType").val();
	//时间
	//var date = $(obj).attr("date");
	var url='';
	if(type=='package'){
		url='../product/package_priceItemDetail.html';
	}else if(type=='hotel'){
		url='../product/hotel_priceItemDetail.html';
	}else if(type=='villa'){
		priceItemDetail(date);
		return;
	}else if(type=='ent'){
		url='../product/ent_priceItemDetail.html';
	}else if(type=='traffic'){
		url='../product/traffic_priceItemDetail.html';
	}
	if(url==''){
		return;
	}

	$.ajax({    
        type:'post',        
        url:url,    
        data:{"pid":pid,"date":date},    
        cache:false,    
        dataType:'json',    
        success:function(data){
        	if(type=='package'){
        		priceItemDetail(data,date);
        	}else if(type=='hotel'){
        		priceItemDetail(data,date);
        	}else if(type=='ent'){
        		priceItemDetail(data,date);
        	}else if(type=='traffic'){
        		priceItemDetail(data,date);
        	}
        }    
    });
}

/**
 * 添加评论
 * @param date
 * @param number
 * @returns {Date}
 */
function addEvaluation(pid,userId){
	if(userId==null||userId==''){
		alert("您未登入");
		return false;
	}
	var content = $("#y_remark").find("textarea[name='content']").val().trim();
	if(content.length==0){
		alert("评价内容不能为空");
		return false;
	}
	$.ajax({    
        type:'post',        
        url:"../personal/isCanEvaluation.html",    
        data:{"pid":pid,"userId":userId},
        cache:false,    
        dataType:'text',    
        success:function(data){
        	if(data=='true'){
        		$.ajax({    
        	        type:'post',        
        	        url:"../evaluation/addEvaluation.html",    
        	        data:$("#y_remark").serialize(),
        	        cache:false,    
        	        dataType:'text',    
        	        success:function(data){
        	        	if(data=='success'){
        	        		getEvaluationList(pid);
        	        	}
        	        }    
        	    });
        	}else{
        		alert("填写评论需要体验过当前产品，您还未体验当前产品。");
        		return false;
        	}
        }    
    });
	
	
	
}

/**
 * 评论列表
 * @param date
 * @param number
 * @returns {Date}
 */
function getEvaluationList(pid){
	$.ajax({    
        type:'post',        
        url:"../evaluation/evaluationList.html",    
        data:{"pid":pid},
        cache:false,    
        dataType:'json',    
        success:function(data){
        	var html = '';
        	for(var i=0;i<data.length;i++){
        		var photo = data[i].photo;
        		if(photo==null||photo==''){
        			photo = "<%=basePath%>assets/tmp/hotel_tmp_img/man2.png";
        		}
        		var customerName = data[i].customerName;
        		if(customerName==null||customerName==''){
        			photo = "匿名用户";
        		}
        		
        		html=html+'<div class="d_detail">'
		        		+'<div class="d_img fl">'
		        		+'<img alt="" width="20px;" height="20px;" src="'+photo+'"></div>'
		        		+'<div class="d_name fl">'+customerName+'</div>'
		        		+'<div class="d_time fl">'+data[i].ctdt+'</div>'
		        		+'<div class="d_mark clr">'+data[i].content+'</div>'
        				+'</div>'
        	}
        	$("#evaluationList").html(html);
        }    
    });
}



/**
 * 几天后
 * @param date
 * @param number
 * @returns {Date}
 */
function addDate(date,number){
    var ms = new Date(date);
    ms.setDate(ms.getDate()+parseInt(number)-1)
    return new Date(ms);
}

/**
 * 两日期天数间隔
 * @param startDate
 * @param endDate
 * @returns {Number}
 */
function getDateDiff(startDate, endDate) {
	var startTime = new Date(Date.parse(startDate.replace(/-/g, "/"))).getTime();
	var endTime = new Date(Date.parse(endDate.replace(/-/g, "/"))).getTime();
	var dates = (endTime - startTime ) / (1000 * 60 * 60 * 24);
	return dates;
}

function getDateList(dateList,value1, value2) {
	var getDate = function(str) {
		var tempDate = new Date();
		var list = str.split("-");
		tempDate.setFullYear(list[0]);
		tempDate.setMonth(list[1] - 1);
		tempDate.setDate(list[2]);
		return tempDate;
	}
	var date1 = getDate(value1);
	var date2 = getDate(value2);
	if (date1 > date2) {
		var tempDate = date1;
		date1 = date2;
		date2 = tempDate;
	}
	date1.setDate(date1.getDate() + 1);
	while (!(date1.getFullYear() == date2.getFullYear()&& date1.getMonth() == date2.getMonth() && date1.getDate() == date2.getDate())) {
		dateList.push(date1.getFullYear()+ "-" + (date1.getMonth() + 1) + "-" + date1.getDate());
		date1.setDate(date1.getDate() + 1);
	}
	return dateList;
}

