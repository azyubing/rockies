<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>    
<!DOCTYPE html>
<html>
<head>
<link href='<%=basePath%>assets/fullcalender/fullcalendar.min.css' rel='stylesheet' />

</head>
<body>

<div class="main-content" style="margin-top: 60px;">
<div class="breadcrumbs breadcrumbs-fixed" id="breadcrumbs">
		<ul class="breadcrumb">
			<li>
				<span style="color: #669FC7;">商品管理</span>
			</li>
			<li>
				<span style="color: #669FC7;">酒店管理</span>
			</li>
			<li>
				<span style="color: #669FC7;">房型列表</span>
			</li>
			<li>
				<span style="color: #669FC7;">价格设定</span>
			</li>
		</ul>
		<!-- /section:basics/content.searchbox -->
</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">价格设定</h3>
				<div class="panel panel-default">
					  <div class="panel-heading">
					    <h3 class="panel-title">酒店信息：</h3>
					  </div>
					  <div class="panel-body">
					  		<div class="row">
					  			<div class="col-md-3"><label>酒店名称：</label>酒店123</div>
					  			<div class="col-md-3"><label>酒店英文名：</label>酒店英文123</div>
					  			<div class="col-md-3"><label>酒店编号：</label>酒店编123</div>
					  			<div class="col-md-3"><label>房型名字：</label>房型名字13</div>
					  		</div>
					  		<hr>
					  		<div style="text-align: center;">
					  			<button class="btn" id="returnBtn"><i class="fa fa-reply"></i> 返回</button>
					  		</div>
					  </div>
				</div>
			</div>
		</div>
		
		<!-- discount -->
		<div class="row">
		  <div class="col-md-12">
			<div class="panel panel-default">
				  <div class="panel-heading">
				    <h3 class="panel-title">阶梯折率：</h3>
				  </div>
				  <div class="panel-body" style="text-align: center;">
				  		<div class="row">
				  			<div class="col-md-12">
								超过<input class="form-control" style="width: 10%;display: inline-block;"  value="3"/>人时，
								折率为<input class="form-control" style="width: 10%;display: inline-block;" value="3.00"/>%
								&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-danger btn-xs"><i class="fa fa-times"></i> 删除</button>
							</div>
						</div><br>
						<div class="row">
				  			<div class="col-md-12">
								超过<input class="form-control" style="width: 10%;display: inline-block;"  value="3"/>人时，
								折率为<input class="form-control" style="width: 10%;display: inline-block;" value="3.00"/>%
								&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-danger btn-xs"><i class="fa fa-times"></i> 删除</button>
							</div>
						</div><br>
						<div class="row">
				  			<div class="col-md-12">
								超过<input class="form-control" style="width: 10%;display: inline-block;"  value="3"/>人时，
								折率为<input class="form-control" style="width: 10%;display: inline-block;" value="3.00"/>%
								&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-danger btn-xs"><i class="fa fa-times"></i> 删除</button>
							</div>
						</div><br>
						<div class="row">
				  			<div class="col-md-12">
								超过<input class="form-control" style="width: 10%;display: inline-block;"  value="3"/>人时，
								折率为<input class="form-control" style="width: 10%;display: inline-block;" value="3.00"/>%
								&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-danger btn-xs"><i class="fa fa-times"></i> 删除</button>
							</div>
						</div><br>
						
						<div style="text-align: center;margin-top: 20px;">
					  			<button class="btn btn-success" id="returnBtn"><i class="fa "></i> 添加</button>&nbsp;&nbsp;
					  			<button class="btn btn-info" id="returnBtn"><i class="fa "></i> 保存</button>
					  	</div>
				  </div>
			</div>
		  </div>
		</div>
		
		<!-- price -->
		<div class="row">
		  <div class="col-md-12">
			<div class="panel panel-default">
				  <div class="panel-heading">
				    <h3 class="panel-title">设置报价<font style="color: #E37578;">(重叠部分按最大值报价)</font>：</h3>
				  </div>
				  <div class="panel-body" style="text-align: center;">
				  		<div class="row" >
				  			<div class="col-md-12">
								<input class="form-control" style="width: 10%;display: inline-block;"  value="3"/>至
								<input class="form-control" style="width: 10%;display: inline-block;" value="3.00"/>
								价格<input class="form-control" style="width: 10%;display: inline-block;" value="3.00"/>
								&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-danger btn-xs"><i class="fa fa-times"></i> 删除</button>
							</div>
						</div><br>
						<div class="row">
				  			<div class="col-md-12">
								<input class="form-control" style="width: 10%;display: inline-block;"  value="3"/>至
								<input class="form-control" style="width: 10%;display: inline-block;" value="3.00"/>
								价格<input class="form-control" style="width: 10%;display: inline-block;" value="3.00"/>
								&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-danger btn-xs"><i class="fa fa-times"></i> 删除</button>
							</div>
						</div><br>
						<div class="row">
				  			<div class="col-md-12">
								<input class="form-control" style="width: 10%;display: inline-block;"  value="3"/>至
								<input class="form-control" style="width: 10%;display: inline-block;" value="3.00"/>
								价格<input class="form-control" style="width: 10%;display: inline-block;" value="3.00"/>
								&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-danger btn-xs"><i class="fa fa-times"></i> 删除</button>
							</div>
						</div><br>
						<div class="row">
				  			<div class="col-md-12">
								<input class="form-control" style="width: 10%;display: inline-block;"  value="3"/>至
								<input class="form-control" style="width: 10%;display: inline-block;" value="3.00"/>
								价格<input class="form-control" style="width: 10%;display: inline-block;" value="3.00"/>
								&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-danger btn-xs"><i class="fa fa-times"></i> 删除</button>
							</div>
						</div><br>
						
						<div style="text-align: center;margin-top: 20px;">
					  			<button class="btn btn-success" id="returnBtn"><i class="fa "></i> 添加</button>&nbsp;&nbsp;
					  			<button class="btn btn-info" id="returnBtn"><i class="fa "></i> 保存</button>
					  	</div>
					  	
				  		<hr>
				  		<div class="row">
				  			<div class="col-md-12"><label>时间：</label>
				  				<input type="radio" name="selectTimeZone" value="2" checked="checked"/>&nbsp;<label>2个月</label>&nbsp;&nbsp;&nbsp;&nbsp;
				  				<input type="radio" name="selectTimeZone" value="4"/>&nbsp;<label>4个月</label>&nbsp;&nbsp;&nbsp;&nbsp;
				  				<input type="radio" name="selectTimeZone" value="8"/>&nbsp;<label>8个月</label>&nbsp;&nbsp;&nbsp;&nbsp;
				  				<input type="radio" name="selectTimeZone" value="12"/>&nbsp;<label>1年</label>
				  			</div>
				  		</div>
				  		<hr>
				  		<div id="calender_content" style="text-align: left;">
				  			<label style="margin-left: 20px;">报价详情：</label>
				  			<div id="calender_container" style="text-align: center;"></div>
				  		</div>
				  </div>
			</div>
		  </div>
		</div>
		
	</div><!-- /.page-content-area -->
</div><!-- /.page-content -->

</div><!-- /.main-content -->


<script src='<%=basePath%>assets/fullcalender/fullcalendar.min.js'></script>
<script src='<%=basePath%>assets/fullcalender/zh-cn.js'></script>

<script type="text/javascript">
var eventJson1 = [{
				title: '3475.00',
				start: '2015-01-11',
				end: '2015-07-13',
				backgroundColor:"red"
			},
			{
				title: '4475.00',
				start: '2015-01-12',
				end: '2015-01-15',
				backgroundColor:"blue"
				
			}]; //设置日历价格范围，从后台取值。 json格式

function renderCalendar(containerId,defaultDateVal,eventJson) {
	$('#'+containerId).fullCalendar({
		header:{left:   'title', center: '', right:  ''},
		height: 500,
		defaultDate: defaultDateVal,
		buttonIcons: false, // show the prev/next text
		weekNumbers: false,
		editable: false,
		eventLimit: true, // allow "more" link when too many events
		events:eventJson
	});
}

	$(function() { 
		//set calender
		var lengthVal = 2; //设置产生日历的个数，根据客户选择获得值。4个月就为4
		
		for(var i=1;i<=lengthVal;i++){
			var containerId = "container_"+i ;
			var d = new Date();
		    var nowYear = d.getFullYear();
		    if(i>9){
				var firstDay = nowYear+'-'+i+'-01';		    	
		    }else{
				var firstDay = nowYear+'-0'+i+'-01';
		    }
			var htmlVar = '<div id="'+containerId+'" style="width:45%;display:inline-block;margin-right:20px;margin-top:20px;" class="c_containers" ></div>';
			$("#calender_container").append(htmlVar);
			renderCalendar(containerId,firstDay,eventJson1);  
		}
		//set calender end
		
		//$('.c_containers').fullCalendar('destroy');
	
		
		
		$("#returnBtn").click(function(){
			window.history.go(-1);
		});
		
		$("#addBtn").click(function(){
			window.location.href = '<%=basePath%>hotel/toAddHotelAdmin.html?menu=${menu}';
		});
		
		$("#seach").click(function(){
			$('#tableInfo').bootstrapTable('refresh',{url: "<%=basePath%>hotel/hotelAdminSearchList.html"});
		});
		
		$('#addMenu').on('hide.bs.modal', function () {
			
		});
		
		$('#updateMenu').on('hide.bs.modal', function () {
			
		});
		
		
	});

	function submitForm(){
		
	}
	
	function updatesubmitForm(){
		
	}

	
	
</script>	

</body>
</html>