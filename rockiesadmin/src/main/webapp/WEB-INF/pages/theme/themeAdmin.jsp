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
				<span style="color: #669FC7;">首页管理</span>
			</li>
			<li>
				<span style="color: #669FC7;">主题管理</span>
			</li>
		</ul>
		<!-- /section:basics/content.searchbox -->
	</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">主题管理</h3>
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
									<label class=""> 主题名称： </label>
									<input type="text" class="form-control " id="selectName" placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
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
	        '<a class="edit margin-left-20" href="javascript:void(0)" title="Edit" id="catalogBtn">',
	            '<i class="glyphicon glyphicon-edit"></i> 设定分类',
	        '</a>',
	        '<a class="edit margin-left-20" href="javascript:void(0)" title="Edit" id="detailBtn">',
	            '<i class="glyphicon glyphicon-edit"></i> 修改',
	        '</a>',
	        '<a class="edit margin-left-20" href="javascript:void(0)" title="Edit" id="deleteBtn">',
	            '<i class="glyphicon glyphicon-edit"></i> 删除',
	        '</a>'
	    ].join('');
}


window.operateEvents = {
        'click #catalogBtn': function (e, value, row, index) {
        	setTimeout(function(){
        		var param = "id="+row.id+"&menu=${menu}";
				window.location.href="<%=basePath%>theme/themeCatalogAdmin.html?"+param;
			},500);
        },
        'click #detailBtn': function (e, value, row, index) {
        	$("#updateForm").find("input[name='id']").val(row.id);
        	$("#updateForm").find("input[name='name']").val(row.name);
			$("#updateForm").find("input[name='isHome']").val(row.isHome);
			$("#updateForm").find("input[name='isMore']").val(row.isMore);
			$("#updateForm").find("input[name='name_en']").val(row.name_en);
			$("#updateForm").find("input[name='description']").val(row.description);
			if(row.isHome==0){
				$("#updateForm").find("input[name='homeShowOrder']").val(row.homeShowOrder);
				$("#updatehomeShowOrder").show();
				$("#updatemoreShowOrder").hide();
				$("#updateForm").find("select option[value='"+row.isHome+"'] ").attr("selected", "selected");
			}else{
				$("#updateForm").find("input[name='moreShowOrder']").val(row.moreShowOrder);
				$("#updatehomeShowOrder").hide();
				$("#updatemoreShowOrder").show();
				$("#updateForm").find("select option[value='"+row.isHome+"'] ").attr("selected", "selected");
			}
        	
			$('#updateMenu').modal('show');
        },
        'click #deleteBtn': function (e, value, row, index) {
        	var id= row.id;
        	var name=row.name;
			$.messager.confirm("提示", "是否确认删除:"+name, function() {
				shCircleLoaderLoading();
				shCircleLoaderDestroy();
				deleteRow(id);
			 });
        }
    };

function queryParams(params) {
	var param={
			name: $("#selectName").val(),
			offset: params.offset,
			limit: params.limit
	};
	  return param;
}

function doSearch(){
    	
    		var url = "<%=basePath%>theme/themeAdminSearchList.html";
    		
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
                    field: 'name',
                    title: '名称',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'name_en',
                    title: '英文名称',
                    align: 'center',
                    valign: 'middle'
                },{
	                field: 'isHome',
	                title: '首页主位展示',
	                align: 'center',
	                valign: 'middle',
	                formatter:function(value,row,index){
	                	if(value==0){
	                		return '是';
	                	}else{
	                		return '否';
	                	}
	                }
	            }, {
	                field: 'homeShowOrder',
	                title: '主位顺序',
	                align: 'center',
	                valign: 'middle'
	            }, {
	                field: 'isMore',
	                title: '更多展示',
	                align: 'center',
	                valign: 'middle',
	                formatter:function(value,row,index){
	                	if(value==0){
	                		return '是';
	                	}else{
	                		return '否';
	                	}
	                }
	            }, {
	                field: 'moreShowOrder',
	                title: '更多展示顺序',
	                align: 'center',
	                valign: 'middle'
	            },  {
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
			$("#addForm").find("input[name='name']").val('');
			$("#addForm").find("input[name='homeShowOrder']").val('');
			$("#addForm").find("input[name='moreShowOrder']").val('');
			$("#addForm").find("input[name='isHome']").val(0);
			$("#addForm").find("input[name='isMore']").val(1);
			$("#addForm").find("input[name='name_en']").val('');
			$("#addForm").find("input[name='description']").val('');
			$("#selIndustyType option[value='1']").attr("selected", "selected");
			$("#addhomeShowOrder").show();
			$("#addmoreShowOrder").hide();
			$('#addMenu').modal('show');
		});
		
		$('#addMenu').on('hide.bs.modal', function () {
			
		});
		
		$('#updateMenu').on('hide.bs.modal', function () {
			
		});
		
		$("#search").click(function(){
			$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>theme/themeAdminSearchList.html"});
		});
		
		$(document).keydown(function(event){
			if(event.keyCode == "13"){
				$('#tableInfo').bootstrapTable("refresh",{url: "<%=basePath%>theme/themeAdminSearchList.html"});
			}
    	});
		
	});

	//添加
	function submitForm(){
		var isHome = $("#addForm").find("input[name='isHome']").val();
		if(isHome==0){//0是1否
			$("#addForm").find("input[name='moreShowOrder']").val(0);
			$("#addForm").find("input[name='isMore']").val(1)
		}else{
			$("#addForm").find("input[name='homeShowOrder']").val(0);
			$("#addForm").find("input[name='isMore']").val(0);
		}
		var moreShowOrder=$("#addForm").find("input[name='moreShowOrder']").val();
		var homeShowOrder=$("#addForm").find("input[name='homeShowOrder']").val();
		if(!isInteger(moreShowOrder)){
			alert("更多展示顺序只能输入正整数");
			return false;
		}
		if(!isInteger(homeShowOrder)){
			alert("主位顺序只能输入正整数");
			return false;
		}
		$.ajax({    
            type:'post',        
            url:'<%=basePath%>theme/saveOrUpdateThemeAdmin.html',    
            data:$('#addForm').serialize(),    
            cache:false,    
            dataType:'text',    
            success:function(data){
            	if(data=='success'){
            		alert("添加成功");
            		$('#addMenu').modal('hide');
            		$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>theme/themeAdminSearchList.html"});
            	}
            }    
        });
	}
	
	function updatesubmitForm(){
		var isHome = $("#updateForm").find("input[name='isHome']").val();
		if(isHome==0){//0是1否
			$("#updateForm").find("input[name='moreShowOrder']").val(0);
			$("#updateForm").find("input[name='isMore']").val(1);
		}else{
			$("#updateForm").find("input[name='homeShowOrder']").val(0);
			$("#updateForm").find("input[name='isMore']").val(0);
		}
		var moreShowOrder=$("#updateForm").find("input[name='moreShowOrder']").val();
		var homeShowOrder=$("#updateForm").find("input[name='homeShowOrder']").val();
		if(!isInteger(moreShowOrder)){
			alert("更多展示顺序只能输入正整数");
			return false;
		}
		if(!isInteger(homeShowOrder)){
			alert("主位顺序只能输入正整数");
			return false;
		}
		
		$.ajax({    
            type:'post',        
            url:'<%=basePath%>theme/saveOrUpdateThemeAdmin.html',    
            data:$('#updateForm').serialize(),    
            cache:false,    
            dataType:'text',    
            success:function(data){
            	if(data=='success'){
            		alert("修改成功");
            		$('#updateMenu').modal('hide');
            		$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>theme/themeAdminSearchList.html"});
            	}
            }    
        });
	}
	
	function showChange(obj){
		var val = $(obj).val();
		if(val == 0){
			$(obj).parent().parent().find("input[name='isHome']").val(0);
			$(obj).parent().parent().find("input[name='isMore']").val(1);
			$(obj).parent().next().show();
			$(obj).parent().next().next().hide();
		}else if(val==1){
			$(obj).parent().parent().find("input[name='isHome']").val(1);
			$(obj).parent().parent().find("input[name='isMore']").val(0);
			$(obj).parent().next().hide();
			$(obj).parent().next().next().show();
		}
	}
	
	//添加
	function deleteRow(id){
		$.ajax({    
            type:'post',        
            url:'<%=basePath%>theme/deleteThemeAdmin.html',    
            data:{"id":id},    
            cache:false,    
            dataType:'text',    
            success:function(data){
            	if(data=='success'){
            		alert("删除成功");
            		$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>theme/themeAdminSearchList.html"});
            	}
            }    
        });
	}

</script>	
<div class="row" >	
	<!-- add -->
	<div class="modal fade" id="addMenu" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">新增</h4>
				</div>
				<div class="modal-body">
					<form  action="" method="post" id="addForm">
					<input type="hidden" name="isHome" value="0"/>
					<input type="hidden" name="isMore" value="1"/>
						<div class="form-group">
							<label>主题名称 ：</label> 
							<input class="form-control" name="name" type="text">
						</div>
						<div class="form-group">
							<label>英文名称：</label> 
							<input class="form-control" name="name_en" type="text">
						</div>
						<div class="form-group">
							<label>主题简介(20字以内)：</label> 
							<input class="form-control" name="description" type="text" maxlength="22">
						</div>
						<div class="form-group">
							<label>选择展示类型：</label> 
							<select class="form-control" name="courseStatus" onchange="showChange(this)">  
								<option value="0">首页主位展示</option>
								<option value="1">首页更多展示</option>
			                </select>
						</div>
						<div class="form-group" id="addhomeShowOrder">
							<label>主位顺序 ：</label> 
							<input class="form-control"  name="homeShowOrder" type="text"> 
						</div>
						<div class="form-group" style="display: none;" id="addmoreShowOrder">
							<label>更多展示顺序：</label> 
							<input class="form-control" name="moreShowOrder" type="text">
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
	<!-- update -->
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
					<form role="form" action="" method="post" id="updateForm">
					<input type="hidden" name="id" />
					<input type="hidden" name="isHome" value="0"/>
					<input type="hidden" name="isMore" value="1"/>
						<div class="form-group">
							<label>主题名称 ：</label> 
							<input class="form-control" name="name" type="text">
						</div>
						<div class="form-group">
							<label>英文名称：</label> 
							<input class="form-control" name="name_en" type="text">
						</div>
						<div class="form-group">
							<label>主题简介(20字以内)：</label> 
							<input class="form-control" name="description" type="text" maxlength="22">
						</div>
						<div class="form-group">
							<label>选择展示类型：</label> 
							<select class="form-control" name="courseStatus" onchange="showChange(this)">  
								<option value="0">首页主位展示</option>
								<option value="1">首页更多展示</option>
			                </select>
						</div>
						<div class="form-group" id="updatehomeShowOrder">
							<label>主位顺序 ：</label> 
							<input class="form-control"  name="homeShowOrder" type="text"> 
						</div>
						<div class="form-group" style="display: none;" id="updatemoreShowOrder">
							<label>更多展示顺序：</label> 
							<input class="form-control" name="moreShowOrder" type="text">
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