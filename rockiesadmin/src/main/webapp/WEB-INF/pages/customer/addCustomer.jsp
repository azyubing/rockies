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
				<span style="color: #669FC7;">客户管理</span>
			</li>
			<li>
				<span style="color: #669FC7;">新增客户</span>
			</li>
		</ul>
		<!-- /section:basics/content.searchbox -->
	</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">客户新增</h3>
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
							<form class="" name="addForm" id="addForm">
								<div class="form-group col-md-12" >
									<label class="control-label" for="form-input-readonly">
										<h4>客户基本信息</h4>
									</label> 
								</div>
								<div class="row">
									<div class="col-md-4">
										<label class=""> &nbsp;&nbsp;&nbsp;客户用户名： </label>
										<input type="text" class="form-control " name="customerName" placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
									<div class="col-md-4">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;真实姓名： </label>
										<input type="text" class="form-control " name="realName" placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
									<div class="col-md-4">&nbsp;</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-4">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;常用邮箱： </label>
										<input type="text" class="form-control " placeholder="请输入内容" name="email" style="display: inline-block; width: 50%;"/>
									</div>
									<div class="col-md-4">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;手机电话： </label>
										<input type="text" class="form-control " placeholder="请输入内容" name="tel" style="display: inline-block; width: 50%;"/>
									</div>
									<div class="col-md-4">&nbsp;</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-4">
										 <label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;出生日期： </label> <input class="form-control date-picker" id="" name="birthday"
												type="text" style="display: inline-block; width: 50%;" /><span
												class="input-group-addon"
												style="display: inline-block; width: 10%; height: 34px;margin-top: -2px;">
												<i class="fa fa-calendar bigger-110"></i>
											</span>
									</div>
									<div class="col-md-4">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;身份证号： </label>
										<input type="text" class="form-control " name="idCard" placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
									<div class="col-md-4">&nbsp;</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-4">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;护照号： </label>
										<input type="text" class="form-control " name="passportNumber" placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
									<div class="col-md-4">
										<label class=""> &nbsp;&nbsp;&nbsp;护照签发地： </label>
										<select class="form-control" id="country" name="countryId"  onchange="ajaxRequestData(this,'province');" style="display: inline-block; width: 15%;">  
											<option value="">-请选择-</option>
											<c:forEach items="${cityList }" var="city">
												<option value="${city.id }">${city.cityName }</option>
											</c:forEach>
						                </select>
										<select class="form-control" id="province" name="provinceId"  onchange="ajaxRequestData(this,'city');" style="display: inline-block; width: 15%;">  
											<option value="">-请选择-</option>
						                </select>
										<select class="form-control" id="city" name="cityId" style="display: inline-block; width: 15%;" >  
											<option value="">-请选择-</option>
						                </select>
										<!-- <input type="text" class="form-control " name="countryId" placeholder="请输入内容" style="display: inline-block; width: 15%;"/>
										<input type="text" class="form-control " name="provinceId" placeholder="请输入内容" style="display: inline-block; width: 15%;"/>
										<input type="text" class="form-control " name="cityId" placeholder="请输入内容" style="display: inline-block; width: 15%;"/> -->
									</div>
									<div class="col-md-4">&nbsp;</div>
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
			var customerName = $('#addForm').find("input[name='customerName']").val().trim();
			var realName = $('#addForm').find("input[name='realName']").val().trim();
			var email = $('#addForm').find("input[name='email']").val().trim();
			var tel = $('#addForm').find("input[name='tel']").val().trim();
			var birthday = $('#addForm').find("input[name='birthday']").val();
			var idCard = $('#addForm').find("input[name='idCard']").val().trim();
			var countryId = $('#countryId').val();
			var provinceId = $('#provinceId').val();
			var cityId = $('#city').val();
			if(countryId==null||countryId==''||provinceId==null||provinceId==''||cityId==null||cityId==''){
				alert("请选择 护照签发地");
				return false;
			}
			if(isNull(customerName)){
				alert("请输入客户用户名");
				return false;
			}
			if(isNull(realName)){
				alert("请输入真实姓名");
				return false;
			}
			if(isNull(email)){
				alert("请输入常用邮箱");
				return false;
			}
			if(!checkEmail(email)){
				return false;
			}
			if(!checkMobile(tel)){
				alert("请输入正确手机电话");
				return false;
			}
			if(isNull(birthday)){
				alert("请填写出生日期");
				return false;
			}
			if(isNull(idCard)){
				alert("请填写 身份证号");
				return false;
			}
			submitForm();
		});
		
		$("#returnBtn").click(function(){
			setTimeout(function(){
				window.location.href="<%=basePath%>customer/customerAdmin.html?menu=${menu}";
			},500);
		});
		
		$('#addMenu').on('hide.bs.modal', function () {
			
		});
		
		$('#updateMenu').on('hide.bs.modal', function () {
			
		});
		
		
	});

	function submitForm(){
		$.ajax({    
            type:'post',        
            url:'<%=basePath%>customer/saveCustomer.html',    
            data:$('#addForm').serialize(),    
            cache:false,    
            dataType:'text',    
            success:function(data){
            	if(data=='success'){
            		alert("添加成功");
            		window.location.href="<%=basePath%>customer/customerAdmin.html?menu=${menu}";
            	}
            }    
        });
	}
	
	//获取数据
	function ajaxRequestData(obj,selectId) {
		var parentId = $(obj).val();
		if(parentId==null||parentId==''){
			$("#"+selectId).html('<option value="">请选择</option>');
			return false;
		}
		$.ajax({
			type : "post",
			url : "<%=basePath%>city/ajaxGetCityList.html",
			data : {
				"parentId" : parentId
			},
			cache : false,
			dataType : 'json',
			beforeSend : function() {
			},
			success : function(data) {
				changeSelect(data,selectId,parentId);
			}
		});
	}
	
	//改变下拉框
	function changeSelect(data,selectId,parentId){
		var json = data.rows;
		var html = '<option value="">请选择</option>';
		for(var i = 0;i<json.length;i++){
			var selected='';
			if(parentId==json[i].id){
				selected = 'selected="selected"';
			}
			html = html+'<option value="'+json[i].id+'"'+selected+' code="'+json[i].cityCode+'">'+json[i].cityName+'</option>';
		}
		$("#"+selectId).html(html);
	}

</script>	

<script type="text/javascript">
	$(function() { 
		getMenu();
		
	});
</script>
</body>
</html>