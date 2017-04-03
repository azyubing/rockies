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
							<form action="<%=basePath%>setpack/updatePackageAdmin.html" id="updateForm" name="updateForm" method="post">
								<%-- 基础信息 --%>
								<c:import url="../product/productTemplate.jsp"></c:import>
								<%-- 扩展信息 --%>
								<div class="form-group col-md-12" >
									<label class="control-label" for="form-input-readonly" style="border-bottom: 1px dotted #478fca;">
										<h4>${pTypeName }扩展信息</h4>
									</label> 
								</div>
								<div class="row">
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;住宿等级： </label>
										<select id="staylvl" name="staylvl" class="form-control"  style="display: inline-block; width: 50%;"/>  
											<option value="" <c:if test="${row.staylvl eq '' }"> selected="selected"</c:if>>-请选择-</option>
											<option value="1" <c:if test="${row.staylvl eq 1 }"> selected="selected"</c:if>>特色酒店</option>
											<option value="3" <c:if test="${row.staylvl eq 3 }"> selected="selected"</c:if>>三星</option>
											<option value="4" <c:if test="${row.staylvl eq 4 }"> selected="selected"</c:if>>四星</option>
											<option value="5" <c:if test="${row.staylvl eq 5 }"> selected="selected"</c:if>>五星</option>
											<option value="6" <c:if test="${row.staylvl eq 6 }"> selected="selected"</c:if>>奢华型</option>
											<option value="7" <c:if test="${row.staylvl eq 7 }"> selected="selected"</c:if>>私家别墅</option>
											<option value="8" <c:if test="${row.staylvl eq 8 }"> selected="selected"</c:if>>民宿</option>
											<option value="99" <c:if test="${row.staylvl eq 99 }"> selected="selected"</c:if>>无住宿</option>
						                </select>
									</div>
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${pTypeName }性质： </label>
										<select id="property" name="property" class="form-control"  style="display: inline-block; width: 50%;"/>  
											<option value="" <c:if test="${row.property eq '' }"> selected="selected"</c:if>>-请选择-</option>
											<option value="1" <c:if test="${row.property eq '1' }"> selected="selected"</c:if>>一地</option>
											<option value="2" <c:if test="${row.property eq '2' }"> selected="selected"</c:if>>多地</option>
						                </select>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;包含天数 ： </label>	
										<div style="display: inline-block;   position: relative; top: 8px;">
											<input id="daycnt" name="daycnt" value="${row.daycnt }" type="text" class="input-mini spinner3" value="0" />
										</div>
									</div>
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;人数 ： </label>
										<div style="display: inline-block;   position: relative; top: 8px;">
											<input id="peoplecnt" name="peoplecnt" value="${row.peoplecnt }" type="text" class="input-mini spinner3" value="0" />
										</div>
									</div>
								</div>
								<br>
								<script src="<%=basePath%>assets/js/umeditor/umeditor.js"></script>
								<script src="<%=basePath%>assets/js/umeditor/umeditor.config.js"></script>
								<input type="hidden" id="mainImgurl" name="list_img"  />
								<input type="hidden" id="attachedImgurl" name="size_img1"   />
								<input type="hidden" id="homeAndListPageImgurl" name="size_img2" />
								<input type="hidden" id="ptype" name="ptype" value="${row.ptype }" />
								<input type="hidden" id="pid" name="pid" value="${row.pid }"/>
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
<div>

</div>
</div><!-- /.main-content -->
<script type="text/javascript">
	
	
	$(function() { 
		
		$("#updateBtn").click(function(){
			
			var isNullVal = "不能为空";
			if(isNull($("#pname").val())){
				alert("${pTypeName }名称"+isNullVal);
				return;
			}
			/*
			if(isNull($("#pname_en").val())){
				alert("${pTypeName }英文名"+isNullVal);
				return;
			}
			*/
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
			if(isNull($("#property").val())){
				alert("请选择套餐性质");
				return;
			}
			if($("#daycnt").val() == 0){
				alert("请设定包含天数");
				return;
			}
			if($("#peoplecnt").val() == 0){
				alert("请设定包含人数");
				return;
			}
			shCircleLoaderLoading();
			$("#updateForm").submit();
			shCircleLoaderDestroy();
		});
		
		$("#returnBtn").click(function(){
			setTimeout(function(){
				window.location.href="<%=basePath%>setpack/packageAdmin.html?menu=${menu}";
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