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
				<span style="color: #669FC7;">目的地管理</span>
			</li>
			<li>
				<span style="color: #669FC7;">设定商品</span>
			</li>
		</ul>
		<!-- /section:basics/content.searchbox -->
	</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">设定商品</h3>
				<div class="panel panel-default">
					  <div class="panel-heading">
					    <h3 class="panel-title">热门城市内容：</h3>
					  </div>
					  <div class="panel-body">
					  		<div class="row">
					  			<div class="col-md-4"><label>城市名称：</label>${destinationVO.countryName }</div>
					  			<div class="col-md-4"><label>所属大洲：</label>${destinationVO.continentName }</div>
					  			<div class="col-md-4"><label>城市编号：</label>${destinationVO.id }</div>
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
									<span class="">
										&nbsp;&nbsp;&nbsp;
										<button id="returnBtn" type="button" class="btn btn-sm">
											返回
											<i class="ace-icon fa fa-arrow-left icon-on-right bigger-110"></i>
										</button>&nbsp;&nbsp;&nbsp;
										<button id="addBtn" type="button" class="btn btn-sm btn-info">
											添加商品
											<i class="ace-icon fa fa-plus icon-on-right bigger-110"></i>
										</button>&nbsp;&nbsp;&nbsp;
									</span>
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
       
        'click #deleteBtn': function (e, value, row, index) {
        	var name = row.pname;
        	var id = row.id;
			$.messager.confirm("提示", "是否确认删除:"+name, function() {
				shCircleLoaderLoading();
				shCircleLoaderDestroy();
				deleteForm(id);
			 });
        }
    };

function queryParams(params) {
	var param={
			id:'${destinationVO.id }',
			offset: params.offset,
			limit: params.limit
	};
	  return param;
}

function doSearch(){
    	
    		var url = "<%=basePath%>destination/destinationProductAdminSearcheList.html";
    		
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
                    field: 'pname',
                    title: '商品名称',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'ptype',
                    title: '商品类别',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 's_name',
                    title: '供应商',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'cityName',
                    title: '所属国家',
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
		        },{
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
			window.location.href= '<%=basePath%>destination/destinationProductAdmin.html?menu=${menu}&id=${destinationVO.id }';
		});

		$("#returnBtn").click(function(){
			window.history.go(-1);
		});
		
	});

	function deleteForm(id){
		$.ajax({    
            type:'post',        
            url:'<%=basePath%>destination/deleteDestinationProduct.html',    
            data:{"id":id},    
            cache:false,    
            dataType:'text',    
            success:function(data){
            	if(data=='success'){
            		alert("删除成功");
            		$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>destination/destinationProductAdminSearcheList.html"});
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