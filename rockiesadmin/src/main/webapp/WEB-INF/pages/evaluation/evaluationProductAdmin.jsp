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
				<span style="color: #669FC7;">评论管理</span>
			</li>
			<li>
				<span style="color: #669FC7;">评论新增</span>
			</li>
		</ul>
		<!-- /section:basics/content.searchbox -->
	</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">评论新增</h3>
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
									<label class=""> 商品名称： </label>
									<input type="text" class="form-control " id="pname" placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
								</div>
								<div class="col-md-4">
									<label class=""> 商品类别： </label>
									<select class="form-control" id="productType" name="courseStatus" style="display: inline-block; width: 50%;">  
										<option value="">-请选择-</option>
										<option value="0">套餐</option>
										<option value="1">酒店</option>
										<option value="2">别墅</option>
										<option value="3">交通</option>
										<option value="4">娱乐</option>
					                </select>
								</div>
								<div class="col-md-4">&nbsp;</div>
							</div>
							<br>
							<div class="row">
								<div class="col-md-4">
									<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;供应商： </label>
									<input type="text" class="form-control " id="suppler" placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
								</div>
								<div class="col-md-4">
									<label class=""> 所属洲： </label>
									<select class="form-control" id="continent" onchange="getCountry(this);" name="courseStatus" style="display: inline-block; width: 50%;">  
										<option value="">-请选择-</option>
										<c:forEach var="city" items="${cityList }">
											<option value="${city.id }">${city.cityName }</option>
										</c:forEach>
					                </select>
								</div>
								<script type="text/javascript">
									function getCountry(obj){
										$.ajax({
											type : "post",
											url : "<%=basePath%>city/ajaxGetCityList.html",
											data : {"parentId" : $(obj).val()},
											cache : false,
											dataType : 'json',
											success : function(data) {
												var html = '<option value="">-请选择-</option>';
												for(var i = 0;i<data.length;i++){
													html = html +'<option value="'+data[i].id+'">'+data[i].cityName+'</option>';
												}
												$("#country").html(html);
											}
										});
									}
								</script>
								
								<div class="col-md-4">
									<label class=""> 所属国家： </label>
									<select class="form-control" id="country" name="courseStatus" style="display: inline-block; width: 50%;">  
										<option value="">-请选择-</option>
					                </select>
								</div>
								<div class="col-md-4">
									<div class="input-group" >
										<span class="">
											<button type="button" id="search" class="btn btn-purple btn-sm">
												查询
												<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
											</button>&nbsp;&nbsp;&nbsp;
											<button id="returnBtn" type="button" class="btn btn-sm">
												返回
												<i class="ace-icon fa fa-arrow-left icon-on-right bigger-110"></i>
											</button>&nbsp;&nbsp;&nbsp;
										</span>
									</div>
								</div>
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
	        '<a class="edit margin-left-20" href="javascript:void(0)" title="Edit" id="addEvaluationBtn">',
	            '<i class="glyphicon glyphicon-edit"></i> 新增评论',
	        '</a>'
	    ].join('');
}


window.operateEvents = {
        'click #addEvaluationBtn': function (e, value, row, index) {
        	var productId = row.pid;
        	var productName = row.pname; 
        	$('#addMenu').find("input[name='productId']").val(productId);
        	$('#productName').val(productName);
        	$('#addMenu').modal('show');
        }
    
};
function queryParams(params) {
	var param={
			pname:$("#pname").val(),
			supplier_no:$("#suppler").val(),
			ptype:$("#productType").val(),
			country:$("#country").val(),
			continent:$("#continent").val(),
			offset: params.offset,
			limit: params.limit
	};
	  return param;
}

function doSearch(){
    	
var url = "<%=basePath%>product/productAdminSearchList.html";
	
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
		            field: 'pid',
		            title: '分类编号',
		            align: 'center',
		            valign: 'middle'
		        }, {
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
			window.history.go(-1);
		});
		
		$("#search").click(function(){
			$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>product/productAdminSearchList.html"});
		});
		$(document).keydown(function(event){
			if(event.keyCode == "13"){
				$('#tableInfo').bootstrapTable("refresh",{url: "<%=basePath%>product/productAdminSearchList.html"});
			}
    	});
	});

	function submitForm(){
		var core = $('#addMenuForm').find("input[name='core']").val();
		var content = $('#addMenuForm').find("textarea[name='content']").val();
		if(isNull(core)){
			alert("请打分");
			return false;
		}
		if(core.length>1||!isInteger(core)){
			alert("请输入0-9的整数");
			return false;
		}
		if(isNull(content)){
			alert("请填写评论内容");
			return false;
		}
		
		
		$.ajax({    
            type:'post',        
            url:'<%=basePath%>evaluation/saveOrUpdateEvaluation.html',    
            data:$("#addMenuForm").serialize(),    
            cache:false,    
            dataType:'text', 
            success:function(data){
            	if(data=='success'){
            		alert("添加成功");
            		$('#addMenu').modal('hide');
            		window.location.href="<%=basePath%>evaluation/evaluationAdmin.html?menu=${menu}";
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
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">新增</h4>
				</div>
				<div class="modal-body">
					<form role="form" action="" method="post" id="addMenuForm">
						<input type="hidden" name="productId">
						<div class="form-group">
							<label>选择的商品 ：</label> 
							<input class="form-control" id="productName" type="text" readonly="readonly">
						</div>
						<div class="form-group">
							<label>用户用户名 ：</label> 
							<input class="form-control" name="customerName" type="text">
						</div>
						<div class="form-group">
							<label>账号 ：</label> 
							<input class="form-control" name="accountnumber" type="text">
						</div>
						<div class="form-group">
							<label>打分 ：</label> 
							<input class="form-control"  name="core" type="text">
						</div>
						<div class="form-group">
							<label>评论内容 ：</label> 
							<textarea class="form-control" name="content" id="form-field-8" placeholder="Default Text" ></textarea>
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
<script type="text/javascript">
	$(function() { 
		getMenu();
		
	});
</script>
</body>
</html>