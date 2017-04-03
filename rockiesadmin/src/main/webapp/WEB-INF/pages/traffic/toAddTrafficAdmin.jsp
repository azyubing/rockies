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
				<span style="color: #669FC7;">${pTypeName }管理</span>
			</li>
			<li>
				<span style="color: #669FC7;">${pTypeName }新增</span>
			</li>
		</ul>
		<!-- /section:basics/content.searchbox -->
	</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">${pTypeName }新增</h3>
				<div class="widget-box">
					
					<div class="widget-body">
						<div class="widget-main">
							<form action="<%=basePath%>traffic/addTrafficAdmin.html" id="addForm" name="addForm" method="post">
								<%-- 基础信息 --%>
								<c:import url="../product/productTemplate.jsp"></c:import>
								<%-- 扩展信息 --%>
								<div class="form-group col-md-12" >
									<label class="control-label" for="form-input-readonly" style="border-bottom: 1px dotted #478fca;">
										<h4>${pTypeName }扩展信息</h4>
									</label> 
								</div>
								<!-- 
								<div class="row">
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;起点： </label>
										<input id="startplace" name="startplace" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;终点： </label>
										<input id="endplace" name="endplace" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
								</div>
								 -->
								<br>
								<div class="row">
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;交通类型： </label>
										<select id="traffic_type" name="traffic_type" class="form-control" style="display: inline-block; width: 50%;"/>  
											<option value="" >-请选择-</option>
											<option value="0" >包车</option>
										<!-- 	<option value="1" >班车</option> -->
								        </select>
									</div>
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用车等级： </label>
										<select id="carlvl" name="carlvl" class="form-control" style="display: inline-block; width: 50%;"/>  
											<option value="" >-请选择-</option>
											<option value="0" >豪华</option>
											<option value="1" >舒适</option>
											<option value="2" >普通</option>
								        </select>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;车型： </label>
										<select id="cartype" name="cartype" class="form-control" style="display: inline-block; width: 50%;"/>  
											<option value="" >-请选择-</option>
											<option value="0" >4人以下</option>
											<option value="1" >5-7人坐</option>
											<option value="3" >7-20人坐</option>
											<option value="4" >20人以上</option>
								        </select>
									</div>
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;可坐人数 ： </label>
										<div style="display: inline-block;   position: relative; top: 8px;">
											<input id="peoplecnt" name="peoplecnt" type="text" class="input-mini spinner3" value="0" />
										</div>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;交通手段： </label>
										<select id="traff_kind" name="traff_kind" class="form-control" style="display: inline-block; width: 50%;"/>  
											<option value="" >-请选择-</option>
											<option value="0">车</option>
											<option value="1">船</option>
											<option value="2">飞机</option>
								        </select>
									</div>
								</div>
								<br>
								<!-- 
								<div class="row">
									<div class="col-md-12">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;出发点地址 ： </label>
										<input id="map_address_start" name="map_address_start" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;出发点经度： </label>
										<input id="map_start_lng" name="map_start_lng" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;出发点纬度： </label>
										<input id="map_start_lat" name="map_start_lat" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-12">
										<label class=""> &nbsp;&nbsp;&nbsp;终点具体位置 ： </label>
										<input id="map_address_end" name="map_address_end" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;终点经度： </label>
										<input id="map_end_lng" name="map_end_lng" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;终点纬度： </label>
										<input id="map_end_lat" name="map_end_lat" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
								</div>
								-->
								<br>
								<script src="<%=basePath%>assets/js/umeditor/umeditor.js"></script>
								<script src="<%=basePath%>assets/js/umeditor/umeditor.config.js"></script>
								<input type="hidden" id="mainImgurl" name="list_img" />
								<input type="hidden" id="attachedImgurl" name="size_img1" />
								<input type="hidden" id="homeAndListPageImgurl" name="size_img2" />
								<input type="hidden" id="ptype" name="ptype" value="3" />
								<input type="hidden" id="menu" name="menu" value="${menu }" />
							</form>
							<%-- 基础信息 --%>
							<c:import url="../product/productMainImgTemplate.jsp"></c:import>
							<c:import url="../product/productAttachedImgTemplate.jsp"></c:import>
							<c:import url="../product/productHomeAndListPageImgTemplate.jsp"></c:import>
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
			var isNullVal = "不能为空";
			if(isNull($("#pname").val())){
				alert("${pTypeName }名称"+isNullVal);
				return;
			}
			/*
			if(isNull($("#pname_en").val())){
				alert("${pTypeName }英文名"+isNullVal);
				return;
			}*/
			if(isNull($("#continent").val())){
				alert("所属大洲"+isNullVal);
				return;
			}
			if(isNull($("#country").val())){
				alert("所属国家"+isNullVal);
				return;
			}
			if(isNull($("#city").val())){
				alert("所属城市"+isNullVal);
				return;
			}
			if(isNull($("#map_address").val())){
				alert("${pTypeName }详细地址"+isNullVal);
				return;
			}
			if(isNull($("#map_lat").val())){
				alert("${pTypeName }纬度"+isNullVal);
				return;
			}
			if(isNull($("#map_lng").val())){
				alert("${pTypeName }经度"+isNullVal);
				return;
			}
			if(isNull($("#supplier_no").val())){
				alert("${pTypeName }供应商"+isNullVal);
				return;
			}
			if(isNull($("#pstatus").val())){
				alert("${pTypeName }状态"+isNullVal);
				return;
			}
			if(isNull($("#startdate").val())){
				alert("开始时间"+isNullVal);
				return;
			}
			if(isNull($("#enddate").val())){
				alert("结束时间"+isNullVal);
				return;
			}
			if(isNull($("#start_price").val())){
				alert("起点价格"+isNullVal);
				return;
			}
			/*
			if(isNull($("#fromcity").val())){
				alert("出发城市"+isNullVal);
				return;
			}*/
			if(isNull($("#confirm_time").val())){
				alert("确认时间(小时)"+isNullVal);
				return;
			}
			if(isNull($("#service_fee_rate").val())){
				alert("服务费比例"+isNullVal);
				return;
			}
			if(isNull($("#tax_rate").val())){
				alert("税费比例"+isNullVal);
				return;
			}
			if(isNaN($("#start_price").val())){
				alert("起点价格必须为半角数字");
				return;
			}
			if(isNaN($("#confirm_time").val())){
				alert("确认时间必须为半角数字");
				return;
			}
			if(isNaN($("#service_fee_rate").val())){
				alert("服务费比例必须为数字");
				return;
			}
			if(isNaN($("#tax_rate").val())){
				alert("税费比例必须为数字");
				return;
			}
			/*
			if(isNull($("#tags").val())){
				alert("标签"+isNullVal);
				return;
			}*/
			
			if(isNull($("#traffic_type").val())){
				alert("请选择交通类型");
				return;
			}
			
			if(isNull($("#carlvl").val())){
				alert("请选择用车等级");
				return;
			}
			
			if(isNull($("#cartype").val())){
				alert("请选择车型");
				return;
			}
			
			if($("#peoplecnt").val() == 0){
				alert("请设定可乘坐人数");
				return;
			}
			
			if(isNull($("#traff_kind").val())){
				alert("请选定交通手段");
				return;
			}

			shCircleLoaderLoading();
			$("#addForm").submit();
			shCircleLoaderDestroy();
		});
		
		$("#returnBtn").click(function(){
			setTimeout(function(){
				window.location.href='<%=basePath%>traffic/trafficAdmin.html?menu=${menu}';
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

function spinner3(){
	$('.spinner3').ace_spinner({
		value:0,min:0,max:10,step:1, 
		on_sides: true, 
		icon_up:'ace-icon fa fa-plus smaller-75', 
		icon_down:'ace-icon fa fa-minus smaller-75', 
		btn_up_class:'btn-success' , 
		btn_down_class:'btn-danger'
	});
}
</script>
<script type="text/javascript">
	$(function() { 
		spinner3();
		getMenu();
		
	});
</script>
</body>
</html>