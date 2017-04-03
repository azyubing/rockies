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
<title>Insert title here</title>

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
				<span style="color: #669FC7;">加价设定</span>
			</li>
			<li>
				<span style="color: #669FC7;">加价新增</span>
			</li>
		</ul>
		<!-- /section:basics/content.searchbox -->
	</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
		<div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">加价新增</h3>
				<div class="panel panel-default">
					  <div class="panel-heading">
					    <h3 class="panel-title">产品信息：</h3>
					  </div>
					  <div class="panel-body">
					  		<div class="row">
					  			<div class="col-md-4"><label>产品名称：</label>${row.pname }</div>
					  			<div class="col-md-4"><label>产品英文名：</label>${row.pname_en }</div>
					  			<div class="col-md-4"><label>产品编号：</label>${row.pid }</div>
					  		</div>
					  </div>
				</div>
			</div>
		</div>
        <div class="row">
			<div class="col-md-12">
				<div class="widget-box">
					
					<div class="widget-body">
						<div class="widget-main">
							<form action="<%=basePath%>extraCost/addExtraCostAdmin.html" id="addForm" name="addForm" method="post">
								<%-- 加价信息 --%>
								<div class="form-group col-md-12" >
									<label class="control-label" for="form-input-readonly" style="border-bottom: 1px dotted #478fca;">
										<h4>起始时间</h4>
									</label> 
								</div>
								<div class="row">
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开始时间：</label>
										<div style="display: inline-block; width: 50%;">
											<div class="input-group "  >
												<input id="startdate" name="startdate" class="form-control date-picker" type="text"  />
												<span class="input-group-addon">
													<i class="fa fa-calendar bigger-110"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结束时间：</label>
										<div style="display: inline-block; width: 50%;">
											<div class="input-group ">
												<input id="enddate" name="enddate" class="form-control date-picker" type="text" />
												<span class="input-group-addon">
													<i class="fa fa-calendar bigger-110"></i>
												</span>
											</div>
										</div>
									</div>
								</div>
								<br>
								<%-- 计算方式 --%>
								<div class="form-group col-md-12" >
									<label class="control-label" for="form-input-readonly" style="border-bottom: 1px dotted #478fca;">
										<h4>计算方式 </h4>
									</label> 
								</div>
								<div class="row">
									<div class="col-md-6">
										<label class=""> 加价计算方式： </label>
										<select id="extraType" name="extraType" class="form-control" style="display: inline-block; width: 50%;"/>  
											<option value="">-请选择-</option>
											<option value="0">按总价加价比例</option>
											<option value="1">按加价XXX元/每人</option>
											<option value="2">按照加价绝对金额</option>
						                </select>
									</div>
									<div class="col-md-6">&nbsp;</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;总价加价率： </label>
										<input id="extraRate" name="extraRate" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
									<div class="col-md-6">&nbsp;</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-6">
										<label class=""> 每人加价金额： </label>
										<input id="extraApiece" name="extraApiece" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
									<div class="col-md-6">&nbsp;</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-6">
										<label class=""> 加价绝对金额： </label>
										<input id="extraAmounts" name="extraAmounts" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
									<div class="col-md-6">&nbsp;</div>
								</div>
								<br>
								<%-- <div id="extraExtraType"></div> --%>
								<input type="hidden" id="pid" name="pid" value="${row.pid }" />
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
<div>

</div>
</div><!-- /.main-content -->
<script type="text/javascript">
	
	
	$(function() { 
		
		$("#addBtn").click(function(){
			if(isNull($("#startdate").val())){
				alert("请选择加价开始时间");
				return;
			}
			if(isNull($("#enddate").val())){
				alert("请选择加价结束时间");
				return;
			}
			if(isNull($("#extraType").val())){
				alert("请选择加价类型");
				return;
			}
			
			shCircleLoaderLoading();
			$("#addForm").submit();
			shCircleLoaderDestroy();
		});
		
		$("#returnBtn").click(function(){
			setTimeout(function(){
				window.location.href='<%=basePath%>extraCost/extraCostAdmin.html?menu=${menu}&pid=${row.pid}';
			},500);
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
<script type="text/javascript">
function extraExtraType(){
	var extraType = $("#extraType").val();
	var extraExtraType = $("#extraExtraType");
	extraExtraType.empty();
	
	if(extraType == '0'){
		extraExtraType.append(
			'<div class="row">'+
			'	<div class="col-md-6">'+
			'		<label class=""> &nbsp;&nbsp;&nbsp;总价加价率： </label>'+
			'		<input id="extraRate" name="extraRate" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>'+
			'	</div>'+
			'	<div class="col-md-6">&nbsp;</div>'+
			'</div>'+
			'<br>'
		);
	}else if(extraType == '1'){
		extraExtraType.append(
				'<div class="row">'+
				'	<div class="col-md-6">'+
				'		<label class=""> 每人加价金额： </label>'+
				'		<input id="extraApiece" name="extraApiece" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>'+
				'	</div>'+
				'	<div class="col-md-6">&nbsp;</div>'+
				'</div>'+
				'<br>'
			);
	}else if(extraType == '2'){
		extraExtraType.append(
				'<div class="row">'+
				'	<div class="col-md-6">'+
				'		<label class=""> 加价绝对金额： </label>'+
				'		<input id="extraAmounts" name="extraAmounts" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>'+
				'	</div>'+
				'	<div class="col-md-6">&nbsp;</div>'+
				'</div>'+
				'<br>'
			);
	}else{
		extraExtraType.empty();
	}
	
	
}
</script>
<script type="text/javascript">
	$(function() { 
		getMenu();
		
	});
</script>
</body>
</html>