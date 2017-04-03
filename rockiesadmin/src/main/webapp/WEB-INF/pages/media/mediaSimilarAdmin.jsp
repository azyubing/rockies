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
				<span style="color: #669FC7;">视频管理</span>
			</li>
			<li>
				<span style="color: #669FC7;">相似套餐管理</span>
			</li>
		</ul>
		<!-- /section:basics/content.searchbox -->
	</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">相似套餐管理</h3>
				<div class="panel panel-default">
					  <div class="panel-heading">
					    <h3 class="panel-title">视频内容：</h3>
					  </div>
					  <div class="panel-body">
					  		<div class="row">
					  			<div class="col-md-6"><label>视频名称：</label>${media.name }</div>
					  			<div class="col-md-6"><label>视频编号：</label>${media.id }</div>
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
							<div class="row">
								<div class="col-md-12">
									<div class="input-group" >
										<span class="">
											&nbsp;&nbsp;&nbsp;
											<button id="returnBtn" type="button" class="btn btn-green btn-sm">
												返回
												<i class="ace-icon fa fa-arrow-left icon-on-right bigger-110"></i>
											</button>&nbsp;&nbsp;&nbsp;
											<button id="addBtn" type="button" class="btn btn-sm btn-info">
												新增相似套餐
												<i class="ace-icon fa fa-plus icon-on-right bigger-110"></i>
											</button>&nbsp;&nbsp;&nbsp;
										</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<h3 class="header smaller lighter blue">已设定的商品</h3>
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
	        
	        '<a class="edit margin-left-20" href="javascript:void(0)" title="Edit" id="deleteBtn">',
	            '<i class="glyphicon glyphicon-edit"></i> 删除',
	        '</a>'
	    ].join('');
}


window.operateEvents = {
        'click #detailBtn': function (e, value, row, index) {
			$('#updateMenu').modal('show');
        },
        'click #deleteBtn': function (e, value, row, index) {
        	var name = row.pname;
        	var id = row.id;
			$.messager.confirm("提示", "是否确认删除:"+name, function() {
				shCircleLoaderLoading();
				shCircleLoaderDestroy();
				deleteFrom(id);
			 });
        }
    };

function queryParams(params) {
	var param={
			id:'${media.id }',
			offset: params.offset,
			limit: params.limit
	};
	  return param;
}

function doSearch(){
    	
    		var url = "<%=basePath%>media/mediaSimilarAdminSearchList.html";
    		
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
                    field: 'id',
                    title: '套餐编号',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'pname',
                    title: '套餐名称',
                    align: 'center',
                    valign: 'middle'
                }, {
		            field: 'pstatus',
		            title: '状态',
		            align: 'center',
		            valign: 'middle',
	                formatter:function(value,row,index){
	                	if(value == 0){
		                	return '<span class="label label-sm label-success arrowed">未上架</span>';
	                	}else if(value == 1){
	                		return '<span class="label label-sm label-warning arrowed">已上架</span>';
	                	}else{
	                		return '<span class="label label-sm label-info arrowed">暂停</span>';
	                	}
	                }
		        }, {
		            field: 'startdate',
		            title: '开始时间',
		            align: 'center',
		            valign: 'middle',
	                formatter:function(value,row,index){
	                	return value.substring(0,10)
	                }
		        }, {
		            field: 'enddate',
		            title: '结束时间',
		            align: 'center',
		            valign: 'middle',
	                formatter:function(value,row,index){
	                	return value.substring(0,10)
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

		$("#addBtn").click(function(){
			setTimeout(function(){
				window.location.href="<%=basePath%>media/mediaProductAdmin.html?menu=${menu}&id=${media.id}";
			},500);
		});
		
		$("#returnBtn").click(function(){
			setTimeout(function(){
				window.history.go(-1);
			},500);
		});
		
	});
	
	function deleteFrom(id){
		$.ajax({    
            type:'post',        
            url:'<%=basePath%>/media/deleteMediaProduct.html',    
            data:{"id":id},    
            cache:false,    
            dataType:'text',    
            success:function(data){
            	if(data=='success'){
            		alert("删除成功");
            		$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>media/mediaSimilarAdminSearchList.html"});
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