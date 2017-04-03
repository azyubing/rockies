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
				<span style="color: #669FC7;">系统管理</span>
			</li>
			<li>
				<span style="color: #669FC7;">后台用户管理</span>
			</li>
		</ul>
		<!-- /section:basics/content.searchbox -->
	</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">后台用户管理</h3>
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
									<label class=""> 用户帐号： </label>
									<input type="text" class="form-control " id="loginName" placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
								</div>
								<div class="col-md-4">
									<div class="input-group" >
										<span class="">
											<button type="button" id="search" class="btn btn-purple btn-sm">
												查询
												<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
											</button>&nbsp;&nbsp;&nbsp;
											<button id="addBtn" type="button" class="btn btn-sm btn-info">
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
        	$('#updateMenu').find("input[name='loginInfo.lid']").val(row.lid);
        	$('#updateMenu').find("input[name='loginInfo.loginName']").val(row.login_name);
        	$('#updateMenu').find("input[name='oldloginName']").val(row.login_name);
        	$('#updateMenu').find("input[name='loginInfo.password']").val(row.pw);
        	$('#updateMenu').find("input[name='loginInfo.nickName']").val(row.nick_name);
        	$('#updateMenu').find("input[name='loginInfo.email']").val(row.email);
        	$('#updateMenu').find("input[name='loginInfo.telephoneno']").val(row.telephoneno);
        	$('#updateMenu').find("input[name='loginInfo.lstatus']").val(row.l_status);
        	$('#updateMenu').find("input[name='loginInfo.isSuppliers']").removeAttr("checked");
        	if(row.isSuppliers==0){
        		$('#updateMenu').find("input[name='loginInfo.isSuppliers']").eq(0).prop("checked",true);
        		$('#updateMenu').find("select").eq(1).parent().show();
        		$('#updateMenu').find("select").eq(1).find("option[value='"+row.supplierId+"'] ").attr("selected", "selected");
        	}else{
	        	$('#updateMenu').find("input[name='loginInfo.isSuppliers']").eq(1).prop("checked",true);
        		$('#updateMenu').find("select").eq(1).parent().hide();
        	}
        	$("#updateMenu").find("select").eq(0).find("option[value='"+row.role_id+"'] ").attr("selected", "selected");
			$('#updateMenu').modal('show');
        },
        'click #deleteBtn': function (e, value, row, index) {
        	var lid = row.lid;
        	var name = row.nick_name;
			$.messager.confirm("提示", "是否确认删除:"+name, function() {
				shCircleLoaderLoading();
				shCircleLoaderDestroy();
				deleteRow(lid);
			 });
        }
    };

function queryParams(params) {
	var param={
			loginName:$("#loginName").val(),
			offset: params.offset,
			limit: params.limit
	};
	  return param;
}

function doSearch(){
    	
    		var url = "<%=basePath%>user/userAdminSearchList.html";
    		
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
                columns: [{
                    field: 'state',                    
                    checkbox: true
                },{
                    field: 'lid',
                    title: '用户编号',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'login_name',
                    title: '用户帐号',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'nick_name',
                    title: '用户姓名',
                    align: 'center',
                    valign: 'middle'
                }, {
	                field: 'role_name',
	                title: '用户角色',
	                align: 'center',
	                valign: 'middle'
	            }, {
	                field: 'email',
	                title: '用户邮箱',
	                align: 'center',
	                valign: 'middle'
	            }, {
	                field: 'telephoneno',
	                title: '用户电话',
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

		$("#addBtn").click(function(){
        	$('#addMenu').find("input[name='loginInfo.loginName']").val('');
        	$('#addMenu').find("input[name='loginInfo.password']").val('');
        	$('#addMenu').find("input[name='loginInfo.nickName']").val('');
        	$('#addMenu').find("input[name='loginInfo.email']").val('');
        	$('#addMenu').find("input[name='loginInfo.telephoneno']").val('');
        	$("#addMenu").find("select").attr("selected", "");
			$('#addMenu').modal('show');
		});
		$("#search").click(function(){
			$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>user/userAdminSearchList.html"});
		});

		$(document).keydown(function(event){
			if(event.keyCode == "13"){
				$('#tableInfo').bootstrapTable("refresh",{url: "<%=basePath%>user/userAdminSearchList.html"});
			}
    	});
	});

	
	function submitForm(){
		var loginName = $('#addMenuForm').find("input[name='loginInfo.loginName']").val();
		var nickName = $('#addMenuForm').find("input[name='loginInfo.nickName']").val().trim();
		var password = $('#addMenuForm').find("input[name='loginInfo.password']").val();
		var email = $('#addMenuForm').find("input[name='loginInfo.email']").val().trim();
		var telephoneno = $('#addMenuForm').find("input[name='loginInfo.telephoneno']").val().trim();
		var roleId = $('#addMenuForm').find("select[name='roleId']").val();
		if(isNull(loginName)){
			alert("请用户帐号 ");
			return false;
		}
		if(isNull(nickName)){
			alert("请输入用户姓名");
			return false;
		}
		if(isNull(password)){
			alert("请输入用户密码");
			return false;
		}
		if(isNull(email)){
			alert("请输入用户邮箱");
			return false;
		}
		if(!checkEmail(email)){
			return false;
		}
		if(!checkMobile(telephoneno)){
			alert("请输入正确用户电话");
			return false;
		}
		if(isNull(roleId)){
			alert("请选择用户角色");
			return false;
		}
		var loginName = $('#addMenuForm').find("input[name='loginInfo.loginName']").val();
		$.ajax({    
            type:'post',        
            url:'<%=basePath%>user/checkuserAdmin.html',    
            data:{"loginName":loginName},    
            cache:false,    
            dataType:'text',    
            success:function(data){
            	if(data=='true'){
            		$.ajax({    
                        type:'post',        
                        url:'<%=basePath%>user/saveOrUpdateuserAdmin.html',    
                        data:$('#addMenuForm').serialize(),    
                        cache:false,    
                        dataType:'text',    
                        success:function(data){
                        	if(data=='success'){
                        		alert("添加成功");
                        		$('#addMenu').modal('hide');
                        		$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>user/userAdminSearchList.html"});
                        	}
                        }    
                    });
            	}else{
            		alert("用户帐号"+loginName+"已存在");
            		return false;
            	}
            }    
        });
		
	}
	
	function updatesubmitForm(){
		var loginName = $('#updateMenuForm').find("input[name='loginInfo.loginName']").val();
		var nickName = $('#updateMenuForm').find("input[name='loginInfo.nickName']").val().trim();
		var password = $('#updateMenuForm').find("input[name='loginInfo.password']").val();
		var email = $('#updateMenuForm').find("input[name='loginInfo.email']").val().trim();
		var telephoneno = $('#updateMenuForm').find("input[name='loginInfo.telephoneno']").val().trim();
		var roleId = $('#updateMenuForm').find("select[name='roleId']").val();
		if(isNull(loginName)){
			alert("请用户帐号 ");
			return false;
		}
		if(isNull(nickName)){
			alert("请输入用户姓名");
			return false;
		}
		if(isNull(password)){
			alert("请输入用户密码");
			return false;
		}
		if(isNull(email)){
			alert("请输入用户邮箱");
			return false;
		}
		if(!checkEmail(email)){
			return false;
		}
		if(!checkMobile(telephoneno)){
			alert("请输入正确用户电话");
			return false;
		}
		if(isNull(roleId)){
			alert("请选择用户角色");
			return false;
		}
		
		var oldloginName = $('#updateMenuForm').find("input[name='oldloginName']").val().trim();
		if(oldloginName!=loginName){
			$.ajax({    
	            type:'post',        
	            url:'<%=basePath%>user/checkuserAdmin.html',    
	            data:{"loginName":loginName},    
	            cache:false,    
	            dataType:'text',    
	            success:function(data){
	            	if(data=='true'){
	            		$.ajax({    
	                        type:'post',        
	                        url:'<%=basePath%>user/saveOrUpdateuserAdmin.html',    
	                        data:$('#updateMenuForm').serialize(),    
	                        cache:false,    
	                        dataType:'text',    
	                        success:function(data){
	                        	if(data=='success'){
	                        		alert("修改成功");
	                        		$('#updateMenu').modal('hide');
	                        		$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>user/userAdminSearchList.html"});
	                        	}
	                        }    
	                    });
	            	}else{
	            		alert("用户帐号"+loginName+"已存在");
	            		return false;
	            	}
	            }    
	        });
		}else{
			$.ajax({    
                type:'post',        
                url:'<%=basePath%>user/saveOrUpdateuserAdmin.html',    
                data:$('#updateMenuForm').serialize(),    
                cache:false,    
                dataType:'text',    
                success:function(data){
                	if(data=='success'){
                		alert("修改成功");
                		$('#updateMenu').modal('hide');
                		$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>user/userAdminSearchList.html"});
                	}
                }    
            });
		}
	}
	
	function deleteRow(lid){
		$.ajax({    
            type:'post',        
            url:'<%=basePath%>user/deleteuserAdmin.html',    
            data:{"lid":lid},    
            cache:false,    
            dataType:'text',    
            success:function(data){
            	if(data=='success'){
            		alert("删除成功");
            		$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>user/userAdminSearchList.html"});
            	}
            }    
        });
	}
	
	function isSuppliers(obj){
		var isSuppliers = $(obj).val();
		if(isSuppliers==0){
			$(obj).parent().next().show();
		}else{
			$(obj).parent().next().hide();
			$(obj).parent().next().find("select option[value='0'] ").attr("selected", "selected");
		}
	}

</script>	
<div class="row" >	
	<!-- add -->
	<div class="modal fade" id="addMenu" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">新增</h4>
				</div>
				<div class="modal-body">
					<form role="form" action="" method="post" id="addMenuForm">
					<input type="hidden" name="loginInfo.lstatus" value="1">
						<div class="form-group">
							<label>用户帐号 ：</label> 
							<input class="form-control"  name="loginInfo.loginName" type="text">
						</div>
						<div class="form-group">
							<label>用户姓名 ：</label> 
							<input class="form-control"  name="loginInfo.nickName" type="text">
						</div>
						<div class="form-group">
							<label>用户密码：</label> 
							<input class="form-control" name="loginInfo.password" type="password">
						</div>
						<div class="form-group">
							<label>用户角色：</label> 
							<select class="form-control"  name="roleId" placeholder="请输入状态">  
								<option value="0">-请选择-</option>
								<c:forEach items="${roleInfoList }" var="roleInfo">
									<option value="${roleInfo.roleId }">${roleInfo.roleName }</option>
								</c:forEach>
			                </select>
						</div>
						<div class="form-group">
							<label>是否是供应商：</label> 
							是<input  name="loginInfo.isSuppliers" type="radio" value="0" onclick="isSuppliers(this);"/>
							否<input  name="loginInfo.isSuppliers" type="radio" value="1" onclick="isSuppliers(this);"/>
						</div>
						<div class="form-group" style="display: none;">
							<label>供应商：</label> 
							<select class="form-control"  name="loginInfo.supplierId" placeholder="请输入状态">  
								<option value="0">-请选择-</option>
								<c:forEach items="${suppliersBeanList }" var="suppliersBean">
									<option value="${suppliersBean.sid }">${suppliersBean.s_name }</option>
								</c:forEach>
			                </select>
						</div>
						<div class="form-group">
							<label>用户邮箱：</label> 
							<input class="form-control"  name="loginInfo.email" type="text">
						</div>
						<div class="form-group">
							<label>用户电话：</label> 
							<input class="form-control"  name="loginInfo.telephoneno" type="text">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button class="btn btn-info" type="button" id="addBtn" onclick="submitForm();">
						<i class="ace-icon fa fa-check bigger-110"></i>
						增加
					</button>
					&nbsp; &nbsp; &nbsp;
					<button class="btn" type="button" class="btn btn-default" data-dismiss="modal">
						<i class="ace-icon fa fa-undo bigger-110"></i>
						关闭
					</button>
				</div>
				<div class="modal-body" id="addMsg" style="color: red;"></div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</div>	
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
						<input type="hidden" name="loginInfo.lid">
						<input type="hidden" name="loginInfo.lstatus">
						<input type="hidden" name="oldloginName">
						<div class="form-group">
							<label>用户帐号 ：</label> 
							<input class="form-control"  name="loginInfo.loginName" type="text">
						</div>
						<div class="form-group">
							<label>用户姓名 ：</label> 
							<input class="form-control"  name="loginInfo.nickName" type="text">
						</div>
						<div class="form-group">
							<label>用户角色：</label> 
							<select class="form-control"  name="roleId" placeholder="请输入状态">  
								<option value="0">-请选择-</option>
								<c:forEach items="${roleInfoList }" var="roleInfo">
									<option value="${roleInfo.roleId }">${roleInfo.roleName }</option>
								</c:forEach>
			                </select>
						</div>
						<div class="form-group">
							<label>是否是供应商：</label> 
							是<input  name="loginInfo.isSuppliers" type="radio" value="0" onclick="isSuppliers(this);"/>
							否<input  name="loginInfo.isSuppliers" type="radio" value="1" onclick="isSuppliers(this);"/>
						</div>
						<div class="form-group" style="display: none;">
							<label>供应商：</label> 
							<select class="form-control"  name="loginInfo.supplierId" placeholder="请输入状态">  
								<option value="0">-请选择-</option>
								<c:forEach items="${suppliersBeanList }" var="suppliersBean">
									<option value="${suppliersBean.sid }">${suppliersBean.s_name }</option>
								</c:forEach>
			                </select>
						</div>
						<div class="form-group">
							<label>用户邮箱：</label> 
							<input class="form-control"  name="loginInfo.email" type="text">
						</div>
						<div class="form-group">
							<label>用户电话：</label> 
							<input class="form-control"  name="loginInfo.telephoneno" type="text">
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
		getMenu();
		
	});
</script>
</body>
</html>