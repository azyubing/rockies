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
				<span style="color: #669FC7;">娱乐价格管理</span>
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
					    <h3 class="panel-title">娱乐信息：</h3>
					  </div>
					  <div class="panel-body">
					  		<div class="row">
					  			<div class="col-md-4"><label>娱乐名称：</label>${res.pname }</div>
					  			<div class="col-md-4"><label>娱乐英文名：</label>${res.pname_en }</div>
					  			<div class="col-md-4"><label>娱乐编号：</label>${res.pid }</div>
					  			<input type="hidden" id="rowStartTime" value="<fmt:formatDate value="${res.startdate}" type="date" pattern="yyyy-MM-dd"/>" /> 
					  			<input type="hidden" id="rowEndTime" value="<fmt:formatDate value="${res.enddate}" type="date" pattern="yyyy-MM-dd"/>" /> 
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
							<form action="<%=basePath%>ent/updateEntPriceAdmin.html" id="updateForm" name="updateForm" method="post">
								<div class="form-group " >
									<label class="control-label" for="form-input-readonly" style="border-bottom: 1px dotted #478fca;">
										<h4>娱乐价格概览</h4>
									</label> 
								</div>
								<div class="row">
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开始时间：</label>
										<div style="display: inline-block; width: 50%;">
											<div class="input-group "  >
												<input id="starttime" name="starttime" value="${row.starttime }" onblur="checkStartTimeRepeat()" class="form-control date-picker" type="text"  />
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
												<input id="enddate" name="enddate" class="form-control date-picker" type="text" value="${row.enddate }"/>
												<span class="input-group-addon">
													<i class="fa fa-calendar bigger-110"></i>
												</span>
											</div>
										</div>
									</div>
									<!-- 
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结束时间：</label>
										<input id="enddate" name="enddate" value="${row.enddate }" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
										<span style="color: red;">*输入的时间格式如:09:30</span>
									</div>
									 -->
								</div>
								<br>
								<div class="row">
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价格项： </label>
										<input id="priceItem" name="priceItem" value="${row.priceItem }" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价格项分组： </label>
										<select id="priceGroup" name="priceGroup" class="form-control" style="display: inline-block; width: 50%;"/>  
											<option value="0" <c:if test="${row.priceGroup eq '0' }">selected="selected"</c:if>>主价格项</option>
										<!-- 	<option value="1" <c:if test="${row.priceGroup eq '1' }">selected="selected"</c:if>>附加价格项</option>-->
						                </select>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;预订数量： </label>
										<input id="maxCnt" name="maxCnt" value="${row.maxCnt }" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;娱乐排序： </label>
										<input id="orderNum" name="orderNum" value="${row.orderNum }" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;娱乐价格： </label>
										<input id="price" name="price" value="${row.price }" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;预付款： </label>
										<input id="prepay" name="prepay" value="${row.prepay }" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;可参与人数： </label>
										<div style="display: inline-block;   position: relative; top: 8px;">
											<input id="peopleCnt" name="peopleCnt" value="${row.peopleCnt }" type="text" class="input-mini spinner3" value="0" />
										</div>
									</div>
									<div class="col-md-6">
										&nbsp;
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-12">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span style="color: red;">*可参与人数说明：辅助价格项设定如：加床 等需要参与行程行程人数计算请设定人数； 辅助价格项设定如：早餐 等不参与行程人数计算则设定为0;</span>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-12">
										<label class="" > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;娱乐价格描述： </label>
										<textarea id="description" name="description" class="form-control" rows="3"  style="width: 83%;margin-left: 25px;"> ${row.description }</textarea>
									</div>
								</div>
								<br>
								<input type="hidden" id="pid" name="pid" value="${row.pid }" />
								<input type="hidden" id="menu" name="menu" value="${menu }" />
								<input type="hidden" id="id" name="id" value="${row.id }"/>"
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
			if(isNull($("#starttime").val())){
				alert("请输入价格有效开始时间");
				return;
			}
			if(isNull($("#enddate").val())){
				alert("请输入价格有效结束时间");
				return;
			}
			var rowStartTime = $("#rowStartTime").val();
			var startTime = $("#starttime").val();
			var rowEndTime = $("#rowEndTime").val();
			var endTime = $("#enddate").val();
			
			var startTimeArray = startTime.split('-');
			if(startTimeArray[2].length ==1){
				startTimeArray[2] = '0'+startTimeArray[2];
			}
			if(startTimeArray[1].length ==1){
				startTimeArray[1] = '0'+startTimeArray[1];
			}
			var endTimeArray = endTime.split('-');
			if(endTimeArray[2].length ==1){
				endTimeArray[2] = '0'+endTimeArray[2];
			}
			if(endTimeArray[1].length ==1){
				endTimeArray[1] = '0'+endTimeArray[1];
			}
			
			startTime = startTimeArray[0]+'-'+startTimeArray[1]+'-'+startTimeArray[2];
			endTime = endTimeArray[0]+'-'+endTimeArray[1]+'-'+endTimeArray[2];
			
			if(rowStartTime > startTime || rowEndTime < endTime){
				alert("设定价格有效时间不在商品有效期间范围"+rowStartTime+"至"+rowEndTime+"内:");
				return false;
			}
			if(isNull($("#priceItem").val())){
				alert("请输入价格项");
				return;
			}
			
			if(isNull($("#maxCnt").val())){
				alert("请设定可预订数");
				return;
			}
			
			if(isNaN($("#maxCnt").val())){
				alert("可预订数必须为半角数字");
				return;
			}
			if($("#peopleCnt").val() == 0){
				alert("请设定可参与人数");
				return;
			}
			if(isNull($("#price").val())){
				alert("请输入价格");
				return;
			}
			
			if(isNaN($("#price").val())){
				alert("价格必须为半角数字");
				return;
			}
			
			if(isNull($("#prepay").val())){
				alert("请输入预付款");
				return;
			}
			
			if(isNaN($("#prepay").val())){
				alert("预付款必须为半角数字");
				return;
			}
			
			var msg = "可参与人数将计入行程人数，确定将参与人数设定为："+$("#peopleCnt").val()+"吗？"

			if(!confirm(msg)){
				return false;
			}
			
			shCircleLoaderLoading();
			$("#updateForm").submit();
			shCircleLoaderDestroy();
		});

		
	});

	
</script>	
<script type="text/javascript">
	$(function() { 
		
		$('.spinner3').ace_spinner({
			value:0,min:0,max:10,step:1, 
			on_sides: true, 
			icon_up:'ace-icon fa fa-plus smaller-75', 
			icon_down:'ace-icon fa fa-minus smaller-75', 
			btn_up_class:'btn-success' , 
			btn_down_class:'btn-danger'
		});
		getMenu();
		
	});
</script>
</body>
</html>