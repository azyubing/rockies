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
			<li>
				<span style="color: #669FC7;">设定分类</span>
			</li>
			<li>
				<span style="color: #669FC7;">选择商品</span>
			</li>
		</ul>
		<!-- /section:basics/content.searchbox -->
	</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">选择商品</h3>
				<div class="panel panel-default">
					  <div class="panel-heading">
					    <h3 class="panel-title">主题内容：</h3>
					  </div>
					  <div class="panel-body">
					  		<div class="row">
					  			<div class="col-md-4"><label>主题名称：</label>${themeCatalogVO.themeName }</div>
					  			<div class="col-md-4"><label>主题编号：</label>${themeCatalogVO.themeId}</div>
					  			<div class="col-md-4"><label>分类名称：</label>${themeCatalogVO.name }</div>
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
								<div class="col-md-4">
									<label style="position: relative; top: -12px;"> 去掉已选商品： </label>
									<input type="checkbox" class="form-control " onclick="distinctResarch(this)" style="display: inline-block; width: 15px;"/>
									<input type="hidden" id="distinctFlg" value="0" >
									<input type="hidden" id="themeCatalogId" value="${themeCatalogVO.id}" >
									<input type="hidden" id="themeId" value="${themeCatalogVO.themeId}" >
									
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
								function distinctResarch(obj){
									if( $(obj).hasClass("checkboxFlg")){
										$("#distinctFlg").val(0);
										$(obj).removeClass("checkboxFlg");
										}else{
										$("#distinctFlg").val(1);	
										$(obj).addClass("checkboxFlg");
									}
									$('#tableInfo').bootstrapTable('refresh',{url: "<%=basePath%>product/productAdminSearchList.html"});
								}
								
									function getCountry(obj){
										$.ajax({
											type : "post",
											url : "<%=basePath%>city/ajaxGetCityList.html",
											data : {"parentId" : $(obj).val()},
											cache : false,
											dataType : 'json',
											success : function(data) {
												var html = '<option value="">-请选择-</option>';
												var resdata = data.rows;
												for(var i = 0;i<resdata.length;i++){
													html = html +'<option value="'+resdata[i].id+'">'+resdata[i].cityName+'</option>';
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
											<button id="seachBtn" type="button" class="btn btn-purple btn-sm">
												查询
												<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
											</button>&nbsp;&nbsp;&nbsp;
											<button id="returnBtn" type="button" class="btn btn-sm">
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

function queryParams(params) {
	var param={
			pname:$("#pname").val(),
			suppler_no:$("#suppler").val(),
			ptype:$("#productType").val(),
			country:$("#country").val(),
			continent:$("#continent").val(),
			distinctFlg :$("#distinctFlg").val(),
			themeCatalogId :$("#themeCatalogId").val(),
			themeId :$("#themeId").val(),
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
		        }]
            });
    }


	$(function() { 
		$(window).resize(function () {$('#tableInfo').bootstrapTable('resetView'); });
		doSearch();
		
		$("#seachBtn").click(function(){
			$('#tableInfo').bootstrapTable('refresh',{url: "<%=basePath%>product/productAdminSearchList.html"});
		});

		$(document).keydown(function(event){
			if(event.keyCode == "13"){
				$('#tableInfo').bootstrapTable("refresh",{url: "<%=basePath%>product/productAdminSearchList.html"});
			}
    	});
		$("#addBtn").click(function(){
			var selects = $('#tableInfo').bootstrapTable('getSelections');
            var ids = $.map(selects, function (row) {
                return row.pid;
            });
            var pnames = $.map(selects, function (row) {
                return row.pname;
            });
            if(ids==null||ids==''){
            	alert("请选择产品");
            	return false;
            }
            console.log(ids);
            $.messager.confirm("提示", "是否确认保存:"+pnames, function() {
				shCircleLoaderLoading();
				shCircleLoaderDestroy();
				$.ajax({
		            type:'post',        
		            url:'<%=basePath%>theme/checkProduct.html',    
		            data:{"ids":ids.toString(),"themeCatalogId":'${themeCatalogVO.id}',"menu":'${menu}',"themeId":'${themeCatalogVO.themeId}'},    
		            cache:false,    
		            dataType:'text',    
		            success:function(data){
		            	if(data=='success'){
		            		$.ajax({
		    		            type:'post',        
		    		            url:'<%=basePath%>theme/saveProduct.html',    
		    		            data:{"ids":ids.toString(),"themeCatalogId":'${themeCatalogVO.id}',"menu":'${menu}',"themeId":'${themeCatalogVO.themeId}'},    
		    		            cache:false,    
		    		            dataType:'text',    
		    		            success:function(data){
		    		            	if(data=='success'){
		    		            		alert("添加成功");
		    		            		window.location.href= '<%=basePath%>theme/themeSetProduct.html?menu=${menu}&id=${themeCatalogVO.id}';
		    		            	}
		    		            }    
		    		        });
		            	}else{
		            		alert(data);
		            	}
		            }    
		        });
			 });
		});

		$("#returnBtn").click(function(){
			window.history.go(-1);
		});
		
	});

	function submitForm(){
		
	}

</script>	
<script type="text/javascript">
	$(function() { 
		getMenu();
		
	});
</script>
</body>
</html>