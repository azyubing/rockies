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
			<li>
				<span style="color: #669FC7;">联系人管理</span>
			</li>
		</ul>
		<!-- /section:basics/content.searchbox -->
	</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">联系人管理</h3>
				
					<div class="panel panel-default">
					  <div class="panel-heading">
					    <h3 class="panel-title">供应商信息：</h3>
					  </div>
					  <div class="panel-body">
					  		<div class="row">
					  			<div class="col-md-4"><label>供应商名称：</label>${supplier.s_name }</div>
					  			<div class="col-md-4"><label>供应商所属国家：</label>${supplier.cityName }</div>
					  			<div class="col-md-4"><label>供应商编号：</label>${supplier.sid }</div>
					  		</div>
					 
					  </div>
					</div>
				
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
							<form class="">
								<div class="row">
									
									<div class="col-md-12">
										<div class="input-group" >
											<span class="">
												
												<button id="addBtn" type="button" class="btn btn-sm btn-info">
													新增联系人
													<i class="ace-icon fa fa-plus icon-on-right bigger-110"></i>
												</button>&nbsp;&nbsp;&nbsp;
												<button id="returnBtn" type="button" class="btn btn-green btn-sm">
													返回
													<i class="ace-icon fa fa-arrow-left icon-on-right bigger-110"></i>
												</button>&nbsp;&nbsp;&nbsp;
											</span>
										</div>
									</div>
									
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="table-header" style="margin-top: 20px;">&nbsp;</div>
				<table id="tableInfo"></table>
			</div>
		</div>
	</div><!-- /.page-content-area -->
</div><!-- /.page-content -->

</div><!-- /.main-content -->

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
					<form action="<%=basePath%>supplier/saveOrUpdateSuppliersContractor.html" method="post" id="saveOrUpdateSuppliersContractor">
						<input type="hidden" name="sid" value="${supplier.sid }"/>
						<input type="hidden" name="cid" value="0"/>
						<input type="hidden" name="menu" value="${menu }"/>
						<div class="form-group">
							<label>联系人名：</label> 
							<input class="form-control"  name="c_name" type="text">
						</div>
						<div class="form-group">
							<label>电话号码：</label> 
							<input class="form-control"  name="c_tel" type="text">
						</div>
						<div class="form-group">
							<label>手机号码：</label> 
							<input class="form-control"  name="c_mobile_phone" type="text">
						</div>
						<div class="form-group">
							<label>E-Mail：</label> 
							<input class="form-control"  name="c_email" type="text">
						</div>
						<div class="form-group">
							<label>传真号码：</label> 
							<input class="form-control"  name="c_fax" type="text">
						</div>
						<div class="form-group">
							<label>通讯地址：</label> 
							<input class="form-control"  name="c_address" type="text">
						</div>
						<div class="form-group">
							<label>主要联系人：</label> 
							是：<input type="radio" name="is_primary" id="is_primary" value="1"/>
							否：<input type="radio" name="is_primary" id="no_primary" value="0" checked="checked"/>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button class="btn btn-info" type="button" id="" onclick="updatesubmitForm();">
						<i class="ace-icon fa fa-check bigger-110"></i>
						保存
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

	function operateFormatter(value, row, index) { 
		return [
		        '<a class="edit margin-left-20 detailBtn" href="javascript:void(0)" title="Edit">',
		            '<i class="glyphicon glyphicon-edit"></i> 修改',
		        '</a>',
		        '<a class="edit margin-left-20 deleteBtn" href="javascript:void(0)" title="Edit" >',
		            '<i class="glyphicon glyphicon-edit"></i> 删除',
		        '</a>'
		    ].join('');
	}


	window.operateEvents = {
	        'click .detailBtn': function (e, value, row, index) {
	        	var cid = row.cid;
	        	$.ajax({    
	                type:'post',        
	                url:'<%=basePath%>supplier/editSuppliersContractor.html',    
	                data:{"cid":cid},    
	                cache:false,    
	                dataType:'json',    
	                success:function(data){
	                	$("#saveOrUpdateSuppliersContractor").find("input[name='cid']").val(data.cid);
	                	$("#saveOrUpdateSuppliersContractor").find("input[name='c_name']").val(data.c_name);
	                	$("#saveOrUpdateSuppliersContractor").find("input[name='c_tel']").val(data.c_tel);
	                	$("#saveOrUpdateSuppliersContractor").find("input[name='c_mobile_phone']").val(data.c_mobile_phone);
	                	$("#saveOrUpdateSuppliersContractor").find("input[name='c_email']").val(data.c_email);
	                	$("#saveOrUpdateSuppliersContractor").find("input[name='c_fax']").val(data.c_fax);
	                	$("#saveOrUpdateSuppliersContractor").find("input[name='c_address']").val(data.c_address);
	                	if(data.is_primary==0){
	                		$("input[type=radio][value=0]").attr("checked",'checked');
	                	}else if(data.is_primary==1){
	                		$("input[type=radio][value=1]").attr("checked",'checked');
	                	}
			        	$('#addMenu').modal('show');
	                }    
	            });
	        	
	        	
	        },
	        'click .addContractor': function (e, value, row, index) {
	        	setTimeout(function(){
					window.location.href="<%=basePath%>supplier/contractorAdmin.html?menu=${menu}";
				},500);
	        },
	        'click .deleteBtn': function (e, value, row, index) {
		        	var cid = row.cid;
		        	var name = $(tr).find("td").eq(1).html();
				$.messager.confirm("提示", "是否确认删除:"+name, function() {
					shCircleLoaderLoading();
					shCircleLoaderDestroy();
					deletesubmitForm(cid);
				 });
	        }
	    };

	function queryParams(params) {
		var param={
				sid:'${sid}',
				menu:'${menu}',
				offset: params.offset,
				limit: params.limit
		};
		  return param;
	}

	function doSearch(){
	    	var url = "<%=basePath%>supplier/suppliersContractorSearch.html";
	    		
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
	                    field: 'cid',
	                    title: '编号',
	                    align: 'center',
	                    valign: 'middle'
	                }, {
	                    field: 'c_name',
	                    title: '联系人',
	                    align: 'center',
	                    valign: 'middle'
	                },{
	                    field: 'c_email',
	                    title: '常用邮箱',
	                    align: 'center',
	                    valign: 'middle'
	                }, {
	                    field: 'c_tel',
	                    title: '电话',
	                    align: 'center',
	                    valign: 'middle'
	                }, {
	                    field: 'c_mobile_phone',
	                    title: '手机',
	                    align: 'center',
	                    valign: 'middle'
	                },  {
	                    field: 'is_primary',
	                    title: '主要联系人',
	                    align: 'center',
	                    valign: 'middle',
	                    formatter: function (value,row,index){
	                    	if(row.is_primary==1){
	                    		return '是';
	                    	}else if(row.is_primary==0){
	                    		return '否';
	                    	}
	                    }
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
			

			$("#returnBtn").click(function(){
				setTimeout(function(){
					window.history.go(-1);
				},500);
			});
			
			$("#addBtn").click(function(){
				$("#saveOrUpdateSuppliersContractor").find("input[name='cid']").val("0");
            	$("#saveOrUpdateSuppliersContractor").find("input[name='c_name']").val("");
            	$("#saveOrUpdateSuppliersContractor").find("input[name='c_tel']").val("");
            	$("#saveOrUpdateSuppliersContractor").find("input[name='c_mobile_phone']").val("");
            	$("#saveOrUpdateSuppliersContractor").find("input[name='c_email']").val("");
            	$("#saveOrUpdateSuppliersContractor").find("input[name='c_fax']").val("");
            	$("#saveOrUpdateSuppliersContractor").find("input[name='c_address']").val("");
            	$("input[type=radio][value=0]").attr("checked",'checked');
				$('#addMenu').modal('show');
			});
			
			
			$('#addMenu').on('hide.bs.modal', function () {
				
			});
			
			$('#updateMenu').on('hide.bs.modal', function () {
				
			});
			
			
		});

		function submitForm(){
			
		}
		
		function check(obj){
			if(isNull($(obj).find("input[name='c_name']").val())){
				alert("联系人名名称不能为空");
				$(obj).find("input[name='c_name']").focus();
				return false;
			}
			if(!checkMobile($(obj).find("input[name='c_mobile_phone']").val())){
				alert("手机号码 输入错误");
				$(obj).find("input[name='c_mobile_phone']").focus();
				return false;
			}
			if(!isEmail($(obj).find("input[name='c_email']").val())){
				alert("邮箱格式输入错误");
				$(obj).find("input[name='c_email']").focus();
				return false;
			}
			return true;
		}
		
		function updatesubmitForm(){
			if(!check($('#saveOrUpdateSuppliersContractor'))){
				return false;
			}
			$.ajax({    
                type:'post',        
                url:'<%=basePath%>supplier/saveOrUpdateSuppliersContractor.html',    
                data:$('#saveOrUpdateSuppliersContractor').serialize(),    
                dataType:'text',    
                success:function(data){
                	var cid = $("#saveOrUpdateSuppliersContractor").find("input[name='cid']").val();
                	if(data=='success'){
                		if(cid!=0){
                			alert("修改成功");
                		}else{
	                		alert("添加成功");
                		}
                		$('#addMenu').modal('hide');
                		$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>supplier/suppliersContractorSearch.html"});
                	}
                }    
            });
		}
		
		function deletesubmitForm(cid){
			$.ajax({
                type:'post',        
                url:'<%=basePath%>supplier/deleteSuppliersContractor.html',    
                data:{"cid":cid},    
                dataType:'text',    
                success:function(data){
                	if(data=='success'){
                		alert("删除成功");
                		$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>supplier/suppliersContractorSearch.html"});
                	}
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