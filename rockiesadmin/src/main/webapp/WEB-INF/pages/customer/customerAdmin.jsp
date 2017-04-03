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
				<span style="color: #669FC7;">客户列表</span>
			</li>
		</ul>
		<!-- /section:basics/content.searchbox -->
	</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">客户列表</h3>
				<div class="widget-box">
					<div class="widget-header widget-header-small">
						<h5 class="widget-title lighter">操作</h5>
						<div class="widget-toolbar">
							<a href="#" data-action="collapse">
								<i class="ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
					</div>

					<div class="widget-body">
						<div class="widget-main">
							<div class="row">
								<div class="col-md-4">
									<label class=""> 客户用户名： </label>
									<input type="text" class="form-control " id="customerName" placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
								</div>
								<div class="col-md-4">
									<div class="input-group" >
										<span class="">
											<button type="button" id="search" class="btn btn-purple btn-sm">
												查询
												<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
											</button>&nbsp;&nbsp;&nbsp;
											<button id="addCusBtn" type="button" class="btn btn-info btn-sm">
												新增
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
				<div class="table-header" style="margin-top: 20px;">&nbsp;</div>
				<table id="tableInfo"></table>
			</div>
		</div>
	</div><!-- /.page-content-area -->
</div><!-- /.page-content -->
<div>

</div>
</div><!-- /.main-content -->
<script type="text/javascript">
function operateFormatter(value, row, index) { 
	return [
	        '<a class="edit margin-left-20" href="javascript:void(0)" title="Edit" id="detailBtn">',
	            '<i class="glyphicon glyphicon-edit"></i> 修改',
	        '</a>',
	        '<a class="edit margin-left-20" href="javascript:void(0)" title="Edit" id="deleteBtn">',
	            '<i class="glyphicon glyphicon-edit"></i> 删除',
	        '</a>'
	    ].join('');
}


window.operateEvents = {
        'click #detailBtn': function (e, value, row, index) {
        	$('#updateMenu').find("input[name='id']").val(row.id);
        	$('#updateMenu').find("input[name='customerName']").val(row.customerName);
        	$('#updateMenu').find("input[name='realName']").val(row.realName);
        	$('#updateMenu').find("input[name='email']").val(row.email);
        	$('#updateMenu').find("input[name='tel']").val(row.tel);
        	$('#birthday').datepicker('update',row.birthday.substring(0,10));
        	$('#updateMenu').find("input[name='idCard']").val(row.idCard);
        	$('#updateMenu').find("input[name='passportNumber']").val(row.passportNumber);
        	var countryId = row.countryId;
        	var provinceId = row.provinceId;
        	var cityId = row.cityId;
        	$("#province").html('<option value="">请选择</option>');
        	$("#city").html('<option value="">请选择</option>');
        	$("#country option[value='"+countryId+"']").attr("selected", "selected");
        	if(cityId==null||cityId==''){
        		$('#updateMenu').modal('show');
        		return false;
        	}
        	if(countryId==null||countryId==''){
    			$("#province").html('<option value="">请选择</option>');
    			return false;
    		}
        	$.ajax({
    			type : "post",
    			url : "<%=basePath%>city/ajaxGetCityList.html",
    			data : {
    				"parentId" : countryId
    			},
    			cache : false,
    			dataType : 'json',
    			beforeSend : function() {
    			},
    			success : function(data) {
    				changeSelect(data,"province",countryId);
    				$("#province option[value='"+provinceId+"']").attr("selected", "selected");
    				if(provinceId==null||provinceId==''){
    	    			$("#city").html('<option value="">请选择</option>');
    	    			return false;
    	    		}
    	        	$.ajax({
    	    			type : "post",
    	    			url : "<%=basePath%>city/ajaxGetCityList.html",
    	    			data : {
    	    				"parentId" : provinceId
    	    			},
    	    			cache : false,
    	    			dataType : 'json',
    	    			beforeSend : function() {
    	    			},
    	    			success : function(data) {
    	    				changeSelect(data,"city",provinceId);
    	    				$("#city option[value='"+cityId+"']").attr("selected", "selected");
							$('#updateMenu').modal('show');
    	    			}
    	    		});
    			}
    		});
        	
        	
        	
        	
        	
        	
        },
        'click #deleteBtn': function (e, value, row, index) {
        	var id = row.id;
        	var name = row.customerName;
			$.messager.confirm("提示", "是否确认删除:"+name, function() {
				shCircleLoaderLoading();
				shCircleLoaderDestroy();
				deleteRow(id);
			 });
        }
    };

function queryParams(params) {
	var param={
			customerName:$("#customerName").val(),
			offset: params.offset,
			limit: params.limit
	};
	  return param;
}

function doSearch(){
    	
    		var url = "<%=basePath%>customer/customerAdminSearchList.html";
    		
            $('#tableInfo').bootstrapTable({
                method: 'get',
                url: url,
		        cache: false,
		        height: 450,
		        striped: true,
		        pagination: true,
		        sidePagination: 'server',
		        queryParams: queryParams,
		        pageNumber: 1,
		        pageSize: 10,
		        pageList: [10, 20],
		        minimumCountColumns: 2,
                columns: [
                {
                    field: 'customerName',
                    title: '客户用户名',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'realName',
                    title: '真实姓名',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'email',
                    title: '常用邮箱',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'tel',
                    title: '手机电话',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'birthday',
                    title: '出生日期',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'idCard',
                    title: '身份证号',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'passportNumber',
                    title: '护照号',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'cityName',
                    title: '护照签发地',
                    align: 'center',
                    valign: 'middle',
                    formatter:function (value,row,index){
                    	return row.countryName+row.provinceName+row.cityName;
                    }
                }, {
                    field: 'ctdt',
                    title: '注册时间',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'operate',
                    title: '操作',
                    align: 'center',
                    valign: 'middle',
                    clickToSelect: false,
                    formatter: operateFormatter,
                    events: operateEvents
                }]
            });
    }


	$(function() { 
		$(window).resize(function () {$('#tableInfo').bootstrapTable('resetView'); });
		doSearch();
		
		$('#addCusBtn').click(function(){
			window.location.href='<%=basePath%>customer/addCustomer.html?menu=${menu}';
		});
		
		$('#updateMenu').on('hide.bs.modal', function () {
			
		});
		$("#search").click(function(){
			$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>customer/customerAdminSearchList.html"});
		});

		$(document).keydown(function(event){
			if(event.keyCode == "13"){
				$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>customer/customerAdminSearchList.html"});
			}
    	});
	});

	
	function updatesubmitForm(){
		var customerName = $('#updateMenu').find("input[name='customerName']").val().trim();
		var realName = $('#updateMenu').find("input[name='realName']").val().trim();
		var email = $('#updateMenu').find("input[name='email']").val().trim();
		var tel = $('#updateMenu').find("input[name='tel']").val().trim();
		var birthday = $('#updateMenu').find("input[name='birthday']").val();
		var idCard = $('#updateMenu').find("input[name='idCard']").val().trim();
		var countryId = $('#country').val();
		var provinceId = $('#province').val();
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
		
		$.ajax({    
            type:'post',        
            url:'<%=basePath%>/customer/updateCustomer.html',    
            data:$('#updateMenuForm').serialize(),    
            cache:false,    
            dataType:'text',    
            success:function(data){
            	if(data=='success'){
            		alert("修改成功");
            		$('#updateMenu').modal('hide');
            		$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>customer/customerAdminSearchList.html"});
            	}
            }    
        });
	}
	
	//删除
	function deleteRow(id){
		$.ajax({    
            type:'post',        
            url:'<%=basePath%>customer/deleteCustomer.html',    
            data:{"id":id},    
            cache:false,    
            dataType:'text',    
            success:function(data){
            	if(data=='success'){
            		alert("删除成功");
            		$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>customer/customerAdminSearchList.html"});
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
<div class="row" >	
	<!-- add -->
	<div class="modal fade" id="updateMenu" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">修改</h4>
				</div>
				<div class="modal-body">
					<form role="form" action="" method="post" id="updateMenuForm">
					<input name="id" type="hidden">
						<div class="form-group">
							<label>客户用户名：</label> 
							<input class="form-control"  name="customerName" type="text">
						</div>
						<div class="form-group">
							<label>真实姓名：</label> 
							<input class="form-control"  name="realName" type="text">
						</div>
						<div class="form-group">
							<label>常用邮箱：</label> 
							<input class="form-control"  name="email" type="text">
						</div>
						<div class="form-group">
							<label>手机电话：</label> 
							<input class="form-control"  name="tel" type="text">
						</div>
						<div class="form-group">
						 	<label>出生日期： </label> 
							<div class="input-group "  >
								<input id="birthday" name="birthday" class="form-control date-picker" type="text"  />
								<span class="input-group-addon">
									<i class="fa fa-calendar bigger-110"></i>
								</span>
							</div>
						</div>
						<div class="form-group">
							<label>身份证号：</label> 
							<input class="form-control"  name="idCard" type="text">
						</div>
						<div class="form-group">
							<label>护照号：</label> 
							<input class="form-control"  name="passportNumber" type="text">
						</div>
						<div class="form-group">
							<label>护照签发地：</label> 
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
						</div>
						
					</form>
				</div>
				<div class="modal-footer">
					<button class="btn btn-info" type="button" id="addBtn" onclick="updatesubmitForm();">
						<i class="ace-icon fa fa-check bigger-110"></i>
						修改
					</button>
					&nbsp; &nbsp; &nbsp;
					<button class="btn" type="button" class="btn btn-default" data-dismiss="modal">
						<i class="ace-icon fa fa-undo bigger-110"></i>
						关闭
					</button>
				</div>
				<div class="modal-body" id="updateaddMsg" style="color: red;"></div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</div>	
<script type="text/javascript">

	$(function() { 
		$('.datepicker').remove();
		$('.date-picker').datepicker({
        	format: 'yyyy-mm-dd',
            autoclose: true,
            todayHighlight: true
        }).next().on(ace.click_event, function(){
			$(this).prev().focus();
		});
		
		getMenu();
		
	});
</script>
</body>
</html>