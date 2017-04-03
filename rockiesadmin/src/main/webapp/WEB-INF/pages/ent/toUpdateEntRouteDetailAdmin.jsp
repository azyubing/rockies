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
				<span style="color: #669FC7;">娱乐管理</span>
			</li>
			<li>
				<span style="color: #669FC7;">娱乐行程详细管理</span>
			</li>
			<li>
				<span style="color: #669FC7;">娱乐行程详细修改</span>
			</li>
		</ul>
		<!-- /section:basics/content.searchbox -->
</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">娱乐行程详细修改</h3>
				<div class="panel panel-default">
					  <div class="panel-heading">
					    <h3 class="panel-title">娱乐行程信息：</h3>
					  </div>
					  <div class="panel-body">
					  		<div class="row">
					  			<div class="col-md-4"><label>娱乐名称：</label>${res.pname }</div>
					  			<div class="col-md-4"><label>娱乐英文名：</label>${res.pname_en }</div>
					  			<div class="col-md-4"><label>娱乐编号：</label>${res.pid }</div>
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
							<form action="<%=basePath%>ent/updateEntRouteDetailAdmin.html" id="updateForm" name="addForm" method="post">
								<div class="form-group " >
									<label class="control-label" for="form-input-readonly" style="border-bottom: 1px dotted #478fca;">
										<h4>娱乐行程概览</h4>
									</label> 
								</div>
								<input id="pday" name="pday" type="hidden" class="form-control " value="1"/>
								<!-- 
								<div class="row">
									<div class="col-md-6">
										<label class=""> 日（第X日）： </label>
										<input id="pday" name="pday" value="${row.pday }" readonly="readonly" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
									<div class="col-md-6">&nbsp;</div>
								</div>
								 -->
								<br>
								<div class="row">
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;项目名称： </label>
										<input id="title" name="title" value="${row.title }" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;项目英文名： </label>
										<input id="titleEn" name="titleEn" value="${row.titleEn }" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开始时间：</label>
										<input id="newStarttime" name="newStarttime" value="${row.starttime }" onblur="checkStartTimeRepeat()" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
										<input id="starttime" name="starttime" type="hidden" value="${row.starttime }"  />
										<span style="color: red;">*输入的时间格式如:09:30</span>
									</div>
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结束时间：</label>
										<input id="endtime" name="endtime" value="${row.endtime }" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
										<span style="color: red;">*输入的时间格式如:09:30</span>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类型： </label>
										<select id="itype" name="itype" class="form-control" style="display: inline-block; width: 50%;"/>  
											<option value="" <c:if test="${row.itype eq '' }">selected="selected"</c:if>>--请选择--</option>
											<option value="0" <c:if test="${row.itype eq 0 }">selected="selected"</c:if>>交通</option>
											<option value="1" <c:if test="${row.itype eq 1 }">selected="selected"</c:if>>住宿</option>
											<option value="2" <c:if test="${row.itype eq 2 }">selected="selected"</c:if>>别墅</option>
											<option value="3" <c:if test="${row.itype eq 3 }">selected="selected"</c:if>>娱乐</option>
						                </select>
									</div>
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;排序： </label>
										<input id="orderNum" name="orderNum" value="${row.orderNum }" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-12">
										<label class="" > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;描述： </label>
										<textarea id="description" name="description" class="form-control" rows="3"  style="width: 83%;margin-left: 25px;">${row.description }</textarea>
									</div>
								</div>
								<br>
								<input type="hidden" id="pid" name="pid" value="${row.pid }" />
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
											<button id="updateBtn" type="button" class="btn btn-sm btn-info">
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
		
		$("#updateBtn").click(function(){
			if($("#title").val() == null || $("#title").val().trim() == ""){
				alert("请输入项目名称");
				return false;
			}
			/* if($("#titleEn").val() == null || $("#titleEn").val().trim() == ""){
				alert("请输入项目英文名！");
				return false;
			} */
			if($("#starttime").val() == null || $("#starttime").val().trim() == "" || $("#starttime").val().indexOf(':') != 2){
				alert("请输入项目格式为“09:00”的开始时间！");
				return false;
			}
			
			if($("#endtime").val() != null && $("#endtime").val().trim() != "" && $("#endtime").val().indexOf(':') != 2){
				alert("请输入项目格式为“22:59”的结束时间！！");
				return false;
			}
			if(isNull($("#itype").val())){
				alert("请选择娱乐类型");
				return;
			}
			shCircleLoaderLoading();
			$("#updateForm").submit();
			shCircleLoaderDestroy();
		});
		
		
	});

	function checkStartTimeRepeat(){
		shCircleLoaderLoading();
		$.ajax({
			type:"GET",
			url:"<%=basePath%>ent/checkEntRouteDetailTimeRepeat.html",
			data:{'pid': $("#pid").val(),'pday': $("#pday").val(),'starttime': $("#newStarttime").val() },
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