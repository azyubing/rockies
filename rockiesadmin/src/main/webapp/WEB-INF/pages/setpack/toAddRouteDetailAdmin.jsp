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
				<span style="color: #669FC7;">套餐管理</span>
			</li>
			<li>
				<span style="color: #669FC7;">套餐行程管理</span>
			</li>
			<li>
				<span style="color: #669FC7;">套餐行程详细管理</span>
			</li>
			<li>
				<span style="color: #669FC7;">套餐行程详细新增</span>
			</li>
		</ul>
		<!-- /section:basics/content.searchbox -->
</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">套餐行程详细新增</h3>
				<div class="panel panel-default">
					  <div class="panel-heading">
					    <h3 class="panel-title">套餐行程信息：</h3>
					  </div>
					  <div class="panel-body">
					  		<div class="row">
					  			<div class="col-md-4"><label>套餐行程日：</label>第${row.pday }天</div>
					  			<div class="col-md-4"><label>套餐行程纬度：</label>${row.mapLat }</div>
					  			<div class="col-md-4"><label>套餐行程经度：</label>${row.mapLng }</div>
					  		</div>
					  </div>
				</div>
			</div>
		</div>
		
		<!-- roomPrice -->
		<div class="row">
			<div class="col-md-12">
				<div class="widget-box">
					
					<div class="widget-body">
						<div class="widget-main">
							<form action="<%=basePath%>setpack/addRouteDetailAdmin.html" id="addForm" name="addForm" method="post">
								<div class="form-group " >
									<label class="control-label" for="form-input-readonly" style="border-bottom: 1px dotted #478fca;">
										<h4>套餐行程概览(第${row.pday }天)</h4>
									</label> 
								</div>
								<div class="row">
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;项目名称： </label>
										<input id="title" name="title" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;项目英文名： </label>
										<input id="titleEn" name="titleEn" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开始时间：</label>
										<input id="starttime" name="starttime" onblur="checkStartTimeRepeat()" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
										<span style="color: red;">*输入的时间格式如:09:30</span>
									</div>
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结束时间：</label>
										<input id="endtime" name="endtime" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
										<span style="color: red;">*输入的时间格式如:09:30</span>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类型： </label>
										<select id="itype" name="itype" class="form-control" style="display: inline-block; width: 50%;"/>  
											<option value="">--请选择--</option>
											<option value="0">交通</option>
											<option value="1">住宿</option>
											<option value="2">别墅</option>
											<option value="3">娱乐</option>
						                </select>
									</div>
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;排序： </label>
										<input id="orderNum" name="orderNum" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-12">
										<label class="" > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;描述： </label>
										<textarea id="description" name="description" class="form-control" rows="3"  style="width: 83%;margin-left: 25px;"></textarea>
									</div>
								</div>
								<br>
								<input type="hidden" id="pid" name="pid" value="${row.pid }" />
								<input type="hidden" id="pday" name="pday" value="${row.pday }" />
								<input type="hidden" id="menu" name="menu" value="${menu }" />
							</form>
							
							<div class="form-group col-md-12" ><div class="hr hr8 hr-double"></div></div>
							<br>
							<div class="row">
								<div class="col-md-4">&nbsp;</div>
								<div class="col-md-4">
									<div class="input-group" >
										<span class="">
											<button id="returnBtn" type="button" class="btn btn-green btn-sm">
												返回
												<i class="ace-icon fa fa-arrow-left icon-on-right bigger-110"></i>
											</button>&nbsp;&nbsp;&nbsp;
											<button id="addBtn" type="button" class="btn btn-sm btn-info">
												保存
												<i class="ace-icon fa fa-plus icon-on-right bigger-110"></i>
											</button>&nbsp;&nbsp;&nbsp;
										</span>
									</div>
								</div>
								<div class="col-md-4">&nbsp;</div>
							</div>
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

	$(function() { 
		
		$("#returnBtn").click(function(){
			setTimeout(function(){
				window.history.go(-1);
			},500);
		});
		
		$("#addBtn").click(function(){
			if(!inputCheck()){
				return;
			}
			shCircleLoaderLoading();
			$("#addForm").submit();
			shCircleLoaderDestroy();
		});
		
		
	});
	
	function inputCheck(){
		if($("#title").val() == null || $("#title").val().trim() == ""){
			alert("请输入项目名称");
			return false;
		}
		if($("#titleEn").val() == null || $("#titleEn").val().trim() == ""){
			alert("请输入项目英文名！");
			return false;
		}
		if($("#starttime").val() == null || $("#starttime").val().trim() == "" || $("#starttime").val().indexOf(':') != 2){
			alert("请输入项目格式为“09:00”的开始时间！");
			return false;
		}
		
		if($("#endtime").val() != null && $("#endtime").val().trim() != "" && $("#endtime").val().indexOf(':') != 2){
			alert("请输入项目格式为“22:59”的结束时间！！");
			return false;
		}
		return true;
	}

	function checkStartTimeRepeat(){
		shCircleLoaderLoading();
		$.ajax({
			type:"GET",
			url:"<%=basePath%>setpack/checkStartTimeRepeat.html",
			data:{'pid': $("#pid").val(),'pday': $("#pday").val(),'starttime': $("#starttime").val() },
			dataType: "html",
			success: function(json){
				var result =jQuery.parseJSON(json);
				if(result != null){
					alert("开始时间已存在请重新输入");
					shCircleLoaderDestroy();
					return;
				}
				shCircleLoaderDestroy();
			}
		});
	}

	
	
</script>	
<script type="text/javascript">
	$(function() { 
		getMenu();
		
	});
</script>
</body>
</html>