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
				<h3 class="header smaller lighter blue">供应商列表</h3>
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
									<label class=""> 供应商名称： </label>
									<input type="text" id="s_name" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
								</div>
								<div class="col-md-4">
									<div class="input-group" >
										<span class="">
											<button type="button" id="select" class="btn btn-purple btn-sm">
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
	        '<a class="edit margin-left-20 addContractor" href="javascript:void(0)" title="Edit">',
            '<i class="glyphicon glyphicon-edit"></i> 联系人管理',
        	'</a>',
	        '<a class="edit margin-left-20 detailBtn" href="javascript:void(0)" title="Edit">',
	            '<i class="glyphicon glyphicon-edit"></i> 修改',
	        '</a>',
	        '<a class="edit margin-left-20 deleteBtn" href="javascript:void(0)" title="Edit">',
	            '<i class="glyphicon glyphicon-edit"></i> 删除',
	        '</a>'
	    ].join('');
}


window.operateEvents = {
        'click .detailBtn': function (e, value, row, index) {
        	var sid = row.sid;
        	var parm = "sid="+sid+"&menu=${menu}";
        	window.location.href="<%=basePath%>supplier/addSupplier.html?"+parm;
        },
        'click .addContractor': function (e, value, row, index) {
        	var sid = row.sid;
        	var parm = "sid="+sid+"&menu=${menu}";
        	window.location.href="<%=basePath%>supplier/contractorAdmin.html?"+parm;
        },
        'click .deleteBtn': function (e, value, row, index) {
       		var sid = row.sid;
       		var name = row.s_name;
			$.messager.confirm("提示", "是否确认删除:"+name, function() {
	        	var parm = "sid="+sid+"&del_flg=1&menu=${menu}";
	        	window.location.href="<%=basePath%>supplier/addOrUpdateSupplier.html?"+parm;
				shCircleLoaderLoading();
				shCircleLoaderDestroy();
				
			 });
        }
    };

function queryParams(params) {
	var param={
			s_name:$("#s_name").val(),
			offset: params.offset,
			limit: params.limit
	};
	  return param;
}

function doSearch(){
    		var url = "<%=basePath%>supplier/supplierAdminSearchList.html";
    		
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
                    field: 'sid',
                    title: '编号',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 's_name',
                    title: '名称',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'c_name',
                    title: '联系人',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'c_email',
                    title: '常用邮箱',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'payName',
                    title: '支付方式',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'pay_account',
                    title: '支付账号',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'status',
                    title: '状态',
                    align: 'center',
                    valign: 'middle',
                    formatter:function(value,row,index){
                    	if(row.s_status==0){
	                  		return '使用中';
	                 	}else if(row.s_status==1){
	                 		return '暂停';
	                   	}else if(row.s_status==2){
	                   		return '撤销';
                    	}
                    }
                }, {
                    field: 'cityName',
                    title: '国家',
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
			setTimeout(function(){
				window.location.href="<%=basePath%>supplier/addSupplier.html?menu=${menu}";
			},500);
		});
		
		$("#select").click(function(){
			$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>supplier/supplierAdminSearchList.html"});
		});

		$(document).keydown(function(event){
			if(event.keyCode == "13"){
				$('#tableInfo').bootstrapTable("refresh",{url: "<%=basePath%>supplier/supplierAdminSearchList.html"});
			}
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
	$(function() { 
		getMenu();
		
	});
</script>
</body>
</html>