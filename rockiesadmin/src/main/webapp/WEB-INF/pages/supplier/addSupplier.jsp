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
				<span style="color: #669FC7;">供应商管理</span>
			</li>
			<li>
				<span style="color: #669FC7;">供应商列表</span>
			</li>
		</ul>
		<!-- /section:basics/content.searchbox -->
	</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">供应商新增</h3>
				<div class="widget-box">
					<!-- <div class="widget-header widget-header-small">
						<h5 class="widget-title lighter">供应商新增</h5>
						<div class="widget-toolbar">
							<a href="#" data-action="collapse">
								<i class="ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
					</div> -->

					<div class="widget-body">
						<div class="widget-main">
							<form action="<%=basePath%>supplier/addOrUpdateSupplier.html" id="saveOrUpdate" name="saveOrUpdate" method="post">
							<input name="menu" type="hidden" value="${menu }">
							<input name="sid" type="hidden" value="${supplier.sid==null?0:supplier.sid }">
							<input name="del_flg" type="hidden" value="${supplier.del_flg==null?1:supplier.del_flg }">
								<div class="form-group col-md-12" >
									<label class="control-label" for="form-input-readonly">
										<h4>供应商基本信息</h4>
									</label> 
								</div>
								<div class="row">
									<div class="col-md-4">
										<label class=""> 供应商名称： </label>
										<input type="text" name="s_name" id="s_name" value="${supplier.s_name }" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
									<div class="col-md-4">
										<label class=""> 供应商状态： </label>
										<select class="form-control" id="s_status" name="s_status" style="display: inline-block; width: 50%;">  
											<option value="">-请选择-</option>
											
											<option value="0" <c:if test="${supplier.s_status==0 }">selected</c:if>>使用中</option>
											<option value="1" <c:if test="${supplier.s_status==1 }">selected</c:if>>暂停</option>
											<option value="2" <c:if test="${supplier.s_status==2 }">selected</c:if>>撤销</option>
						                </select>
									</div>
									<div class="col-md-4"><label class=""> 供应商国家： </label>
										<select class="form-control" id="s_country" name="s_country" style="display: inline-block; width: 50%;">  
											<option value="">-请选择-</option>
											<c:forEach var="city" items="${cityList}">
												<option value="${city.id}" 
													<c:if test="${supplier.s_country==city.id}">selected</c:if>
												>${city.cityName}</option>
											</c:forEach>
						                </select>
						                </div>
								</div>
								<br>
							
								<div class="row">
									<div class="col-md-12">
										<label class="" style=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注： </label>
										<textarea class="form-control"  name="s_remark" id="form-field-8" placeholder="Default Text" style="display: inline-block; width: 86%;">${supplier.s_remark }</textarea>
									</div>
									
								</div>
								<br>
								
								
							
								<div class="form-group col-md-12" ><div class="hr hr8 hr-double"></div></div>
								<div class="col-md-12" >
									<label class="control-label" for="form-input-readonly">
										<h4>会计属性</h4>
									</label> 
								</div>
								<div class="row">
									<div class="col-md-4">
										<label class=""> &nbsp;结算币种： </label>
										<input type="text" value="${supplier.currency_type }" name="currency_type" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
									<div class="col-md-4">
										<label class=""> &nbsp;&nbsp;&nbsp;付款方式： </label>
										<select class="form-control" id="addCourseStatus" name="pay_type" style="display: inline-block; width: 50%;">  
											<option value="">-请选择-</option>
											<c:forEach var="pay" items="${payList}">
												<option value="${pay.pid}" 
													<c:if test="${supplier.pay_type==pay.pid}">selected</c:if>
												>${pay.payName}</option>
											</c:forEach>
						                </select>
									</div>
									<div class="col-md-4"><label class=""> &nbsp;付款账号： </label>
										<input type="text" value="${supplier.pay_account }" name="pay_account" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
								</div>
								<br>
							
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
							</form>
							
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
			submitForm();
		});
		
		$("#returnBtn").click(function(){
			setTimeout(function(){
				window.location.href="<%=basePath%>supplier/supplierAdmin.html?menu=${menu}";
			},500);
		});
		
		$('#addMenu').on('hide.bs.modal', function () {
			
		});
		
		$('#updateMenu').on('hide.bs.modal', function () {
			
		});
		
		
	});

	function submitForm(){
		var s_name = $("#s_name").val().trim();
		if(s_name==null||s_name==''){
			alert("供应商名称不能为空");
			return false;
		}
		var s_status = $("#s_status").val();
		if(s_status==null||s_status==''){
			alert("请选择供应商状态");
			return false;
		}
		var s_country = $("#s_country").val();
		if(s_country==null||s_country==''){
			alert("请选择供应商国家");
			return false;
		} 
		$("#saveOrUpdate").submit();
	}
	
</script>	

<script type="text/javascript">
	$(function() { 
		getMenu();
		
	});
</script>
</body>
</html>