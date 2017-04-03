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
				<span style="color: #669FC7;">商品订单管理</span>
			</li>
			<li>
				<span style="color: #669FC7;">供应商订单列表</span>
			</li>
		</ul>
		<!-- /section:basics/content.searchbox -->
	</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">供应商订单列表</h3>
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
									<div class="col-md-4">
										<label class=""> 订单编号： </label>
										<input type="text" class="form-control " id="orderNoSearch" placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
									<div class="col-md-4">
										<label class=""> 确认状态： </label>
										<select id="confirmStatusSearch">
											<option value="">请选择</option>
											<option value="1">未确认</option>
											<option value="0">已确认</option>
										</select>
									</div>
									<div class="col-md-4">
										<label class=""> 确认时间： </label>
										<select id="confirmTimeSearch">
											<option value="">请选择</option>
											<option value="0">即时确认</option>
											<option value="12">12小时确认</option>
											<option value="48">48小时确认</option>
										</select>
									</div>
									<div class="col-md-4">
										<label class=""> 剩余确认时间小于（小时）： </label>
										<input type="text" class="form-control " id="SurplusTimeSearch" maxlength="4" placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
									<div class="col-md-4">
										<div class="input-group" >
											<span class="">
												<button type="button" id="search" class="btn btn-purple btn-sm">
													查询
													<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
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
	            '<i class="glyphicon glyphicon-edit"></i> 查看',
	        '</a>'
	    ].join('');
    
}


window.operateEvents = {
        'click #detailBtn': function (e, value, row, index) {
        	var orderNo = row.order_no;
        	var pid = row.pid;
        	var runingNo = row.runing_no;
        	var p = "&orderNo="+orderNo+"&pid="+pid+"&runingNo="+runingNo;
        	setTimeout(function(){
				window.location.href="<%=basePath%>order/detailSuppliersOrderInfo.html?menu=${menu}"+p;
			},500);
        }
    };

function queryParams(params) {
	var orderNoSearch =$("#orderNoSearch").val();
	var confirmStatusSearch =$("#confirmStatusSearch").val();
	var confirmTimeSearch =$("#confirmTimeSearch").val();
	var surplusTimeSearch =$("#SurplusTimeSearch").val();
	var param={
			orderNo:orderNoSearch,
			confirmStatus:confirmStatusSearch,
			confirmTime:confirmTimeSearch,
			surplusTime:surplusTimeSearch,
			offset: params.offset,
			limit: params.limit
	};
	  return param;
}

function doSearch(){
    	
    		var url = "<%=basePath%>order/suppliersOrderSearchList.html";
    		
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
                    field: 'order_no',
                    title: '订单编号',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'pid',
                    title: '产品编号',
                    align: 'center',
                    valign: 'middle'
                }, {
	                field: 'pname',
	                title: '产品名称',
	                align: 'center',
	                valign: 'middle'
	            }, {
	                field: 'ctdt',
	                title: '下单时间',
	                align: 'center',
	                valign: 'middle'
	            }, {
	                field: 'confirm_status',
	                title: '确认状态',
	                align: 'center',
	                valign: 'middle',
	                formatter:function (value,row,index){
                    	if(row.confirm_status=='0'){
                    		return '已确认';
                    	}else if(row.confirm_status=='1'){
                    		return '未确认';
                    	}
                    }
	            }, {
	                field: 'confirm_time',
	                title: '确认时间',
	                align: 'center',
	                valign: 'middle'
	            },{
	                field: 'surplusTime',
	                title: '剩余确认时间',
	                align: 'center',
	                valign: 'middle',
	                formatter:function (value,row,index){
	                	var status = row.status;
                    	if(status>0){
                    		return '<span>'+value+'</span>';
                    	}else if(status<0){
                    		return '<span style="color:red;">'+value+'</span>';
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

		$("#addBtn").click(function(){
			$('#addMenu').modal('show');
		});
		
		$("#search").click(function(){
			var surplusTimeSearch =$("#SurplusTimeSearch").val().trim();
			if(!isNull(surplusTimeSearch)){
				if(!isNumber(surplusTimeSearch)){
					alert("剩余确认时间 只能输入正整数");
					return false;
				}
			}
			$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>order/suppliersOrderSearchList.html"});
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