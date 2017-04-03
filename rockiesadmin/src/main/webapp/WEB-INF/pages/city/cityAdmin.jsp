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
				<span style="color: #669FC7;">城市管理</span>
			</li>
		</ul>
		<!-- /section:basics/content.searchbox -->
	</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">城市管理</h3>
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
								<input value="0" type="hidden"  id="parentId"/>
								<div class="col-md-4">
									<label class=""> 洲： </label>
									<select id="continent" name="continent"class="form-control" style="display: inline-block; width: 50%;"/>  
								  		<option value="">--请选择--</option>
								  		<c:forEach items="${cityList }" var="city">
									  		<option value="${city.id }" code="${city.cityCode }">${city.cityName }</option>
								  		</c:forEach>
									</select>
								</div>
								<div class="col-md-4">
									<label class=""> 国家： </label>
									<select id="country" name="country" class="form-control" style="display: inline-block; width: 50%;"/>  
										<option value="" >--请选择--</option>
							        </select>
								</div>
								<div class="col-md-4">
									<div class="input-group" >
										<span class="">
											<button id="addBtn" type="button" class="btn btn-sm btn-info">
												新增
												<i class="ace-icon fa fa-plus icon-on-right bigger-110"></i>
											</button>&nbsp;&nbsp;&nbsp;
										</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="table-header" style="margin-top: 20px;"><div id="address">位置：</div></div>
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
	        '<a class="edit margin-left-20" href="javascript:void(0)" title="update" id="detailBtn">',
	            '<i class="glyphicon glyphicon-edit"></i> 修改',
	        '</a>',
	        '<a class="edit margin-left-20" href="javascript:void(0)" title="delete" id="deleteBtn">',
	            '<i class="glyphicon glyphicon-edit"></i> 删除',
	        '</a>'
	    ].join('');
    
}


window.operateEvents = {
        'click #detailBtn': function (e, value, row, index) {
        	$('#updateMenu').find("input[name='id']").val(row.id);
        	$('#updateMenu').find("input[name='parentId']").val(row.parentId);
    		$('#updateMenu').find("input[name='cityName']").val(row.cityName);
    		$('#updateMenu').find("input[name='cityNameEn']").val(row.cityNameEn);
    		$('#updateMenu').find("input[name='cityCode']").val(row.cityCode);
    		$('#updateMenu').find("input[name='oldcityCode']").val(row.cityCode);
    		//$('#updateMenu').find("input[name='isHot']").val(row.isHot);
    		$("#updateIsHot").val(row.isHot);
        	$('#updateMenu').modal('show');
        },
        'click #deleteBtn': function (e, value, row, index) {
        	var id = row.id;
			$.messager.confirm("提示", "是否确认删除:"+row.cityName, function() {
				shCircleLoaderLoading();

				$.ajax({
					type:"POST",
					url:"<%=basePath%>city/ajaxDeleteCity.html",
					data:{'id': id},
					dataType: "text",
					success: function(json){
						alert("删除成功");
						$('#tableInfo').bootstrapTable('refresh',{url: "<%=basePath%>city/ajaxGetCityList.html"});
						var parentId = $("#parentId").val();
						var country = $("#country").val();
						var continent = $("#continent").val();
						if(parentId==0){
							getContinent();
						}else if(country == null || country == ''){
							getContinentToCountry();
						}else{
							getCountryToCity(parentId);
						}
						
						shCircleLoaderDestroy();
					}
				});
			 });
        }
    };

function queryParams(params) {
	var param={
			parentId: $("#parentId").val(),
			offset: params.offset,
			limit: params.limit
	};
	  return param;
}
function doSearch(){
	
	var url = "<%=basePath%>city/ajaxGetCityList.html";
	
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
            field: 'cityName',
            title: '名称',
            align: 'center',
            valign: 'middle'
        }, {
            field: 'cityNameEn',
            title: '英文名称',
            align: 'center',
            valign: 'middle'
        }, {
            field: 'cityCode',
            title: '代码',
            align: 'center',
            valign: 'middle'
        }
        , {
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

$(document).ready(function() {
	$(window).resize(function () {$('#tableInfo').bootstrapTable('resetView'); });
	doSearch();


	getContinentToCountry();

	getCountryToCity($("#country").val());

	$("#continent").change(function(){
		var continent = $("#continent").val();
		if(continent == null || continent == ''){
			$("#parentId").val(0);
		}else{
			$("#parentId").val(continent);
		}
		
		$('#tableInfo').bootstrapTable('refresh',{url: "<%=basePath%>city/ajaxGetCityList.html"});
		getContinentToCountry();
	});
	
	$("#country").change(function(){
		var country = $("#country").val();
		var continent = $("#continent").val();
		if(country == null || country == ''){
			$("#parentId").val(continent);
		}else{
			$("#parentId").val(country);
		}
		$('#tableInfo').bootstrapTable('refresh',{url: "<%=basePath%>city/ajaxGetCityList.html"});
		getCountryToCity(country);
	});
	
	$("#addBtn").click(function(){
		$('#addMenu').find("input[name='parentId']").val($("#parentId").val());
		$('#addMenu').find("input[name='cityName']").val("");
		$('#addMenu').find("input[name='cityNameEn']").val("");
		$('#addMenu').find("input[name='cityCode']").val("");
		$('#addMenu').find("input[name='isHot']").val("");
		$('#addMenu').modal('show');
	});
	
})



//添加
function submitForm() {
	var cityName = $('#addMenu').find("input[name='cityName']").val();
	var cityNameEn = $('#addMenu').find("input[name='cityNameEn']").val();
	var cityCode = $('#addMenu').find("input[name='cityCode']").val();
	if(cityName.trim().length==0){
		alert("名称不能为空");
		return false;
	}
	if(cityNameEn.trim().length==0){
		alert("英文名称不能为空");
		return false;
	}
	if(cityCode.trim().length!=6){
		alert("请输入6字码的城市代码");
		return false;
	}
	$.ajax({
		type : "post",
		url : "<%=basePath%>city/ajaxCheckCityCode.html",
		data : {
			"cityCode" : cityCode.trim()
		},
		cache : false,
		dataType : 'text',
		beforeSend : function() {
		},
		success : function(data) {
			$('#addMenu').find("input[name='parentId']").val($("#parentId").val());
			if(data=='true'){
				$.ajax({
					type : "post",
					url : "<%=basePath%>city/ajaxSaveCity.html",
					data : $('#addMenuForm').serialize(),
					cache : false,
					dataType : 'text',
					success : function(data) {
						if(data=='success'){
							alert("添加成功");
							$('#addMenu').modal('hide');
							$('#tableInfo').bootstrapTable('refresh',{url: "<%=basePath%>city/ajaxGetCityList.html"});
							var parentId = $("#parentId").val();
							var country = $("#country").val();
							var continent = $("#continent").val();
							if(parentId==0){
								getContinent();
							}else if(country == null || country == ''){
								getContinentToCountry();
							}else{
								getCountryToCity(parentId);
							}
							
						}
					}
				});
			}else{
				alert("代码："+cityCode+"已存在");
				return false;
			}
		}
	});
}

//修改
function updatesubmitForm() {
	var cityName = $('#updateMenu').find("input[name='cityName']").val();
	var cityNameEn = $('#updateMenu').find("input[name='cityNameEn']").val();
	var cityCode = $('#updateMenu').find("input[name='cityCode']").val();
	var oldcityCode = $('#updateMenu').find("input[name='oldcityCode']").val();
	if(cityName.trim().length==0){
		alert("名称不能为空");
		return false;
	}
	if(cityNameEn.trim().length==0){
		alert("英文名称不能为空");
		return false;
	}
	if(cityCode.trim().length!=6){
		alert("请输入6字码的城市代码");
		return false;
	}
	if(oldcityCode==cityCode.trim()){
		$.ajax({
			type : "post",
			url : "<%=basePath%>city/ajaxUpdateCity.html",
			data : $('#updateMenuForm').serialize(),
			cache : false,
			dataType : 'text',
			success : function(data) {
				if(data=='success'){
					alert("修改成功");
					$('#updateMenu').modal('hide');
					$('#tableInfo').bootstrapTable('refresh',{url: "<%=basePath%>city/ajaxGetCityList.html"});
					var parentId = $("#parentId").val();
					var country = $("#country").val();
					var continent = $("#continent").val();
					if(parentId==0){
						getContinent();
					}else if(country == null || country == ''){
						getContinentToCountry();
					}else{
						getCountryToCity(parentId);
					}
					
				}
			}
		});
	}else{
		$.ajax({
			type : "post",
			url : "<%=basePath%>city/ajaxCheckCityCode.html",
			data : {
				"cityCode" : cityCode.trim()
			},
			cache : false,
			dataType : 'text',
			beforeSend : function() {
			},
			success : function(data) {
				$('#addMenu').find("input[name='parentId']").val($("#parentId").val());
				if(data=='true'){
					$.ajax({
						type : "post",
						url : "<%=basePath%>city/ajaxUpdateCity.html",
						data : $('#updateMenuForm').serialize(),
						cache : false,
						dataType : 'text',
						success : function(data) {
							alert("修改成功");
							if(data=='success'){
								$('#updateMenuForm').modal('dide');
								$('#tableInfo').bootstrapTable('refresh',{url: "<%=basePath%>city/ajaxGetCityList.html"});
								var parentId = $("#parentId").val();
								var country = $("#country").val();
								var continent = $("#continent").val();
								if(parentId==0){
									getContinent();
								}else if(country == null || country == ''){
									getContinentToCountry();
								}else{
									getCountryToCity(parentId);
								}
								
							}
						}
					});
				}else{
					alert("代码："+cityCode+"已存在");
					return false;
				}
			}
		});
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
						<input name="parentId" type="hidden" />
						<br>
						<div class="form-group">
							<label>名称 ：</label> 
							<input class="form-control"  name="cityName" type="text">
						</div>
						<div class="form-group">
							<label>英文名称 ：</label> 
							<input class="form-control"  name="cityNameEn" type="text">
						</div>
						<div class="form-group">
							<label>代码：</label> 
							<input class="form-control"  name="cityCode" type="text">
						</div>
						<div class="form-group">
							<label>是否为热门：</label> 
							<select class="form-control" name="isHot">  
								<option value="0">否</option>
								<option value="1">是</option>
			                </select>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button class="btn btn-info" type="button" onclick="submitForm();">
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
						<input name="id" type="hidden" />
						<input name="parentId" type="hidden" />
						<input name="oldcityCode" type="hidden" />
						<br>
						<div class="form-group">
							<label>名称 ：</label> 
							<input class="form-control"  name="cityName" type="text">
						</div>
						<div class="form-group">
							<label>英文名称 ：</label> 
							<input class="form-control"  name="cityNameEn" type="text">
						</div>
						<div class="form-group">
							<label>代码：</label> 
							<input class="form-control"  name="cityCode" type="text">
						</div>
						<div class="form-group">
							<label>是否为热门：</label> 
							<select class="form-control" name="isHot" id="updateIsHot">  
								<option value="0">否</option>
								<option value="1">是</option>
			                </select>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button class="btn btn-info" type="button" onclick="updatesubmitForm();">
						<i class="ace-icon fa fa-check bigger-110"></i>
						保存
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

function checkParentId(){
	var city = $("#city").val();
	var country = $("#country").val();
	var continent = $("#continent").val();
	console.log("city:"+city !=null || city !='');
	console.log("country:"+country !=null || country !='');
	console.log("continent:"+continent !=null || continent !='');
	console.log("parentId:"+$("#parentId").val());
	if(city !=null || city !=''){
		console.log(1);
		$("#parentId").val(city);
	}else if(country !=null || country !=''){
		console.log(2);
		$("#parentId").val(country);
	}else if(continent !=null || continent !=''){
		console.log(3);
		$("#parentId").val(continent);
	}else{
		$("#parentId").val(0);
	}
}

function getContinent(){
	var parentId = 0;
	var cityFrom = $("#continent");
	var citySelect = $("#continentSelected").val();
	setTimeout(function(){
		getParentCityByParentId(parentId,cityFrom,citySelect);
	},500);
}

function getContinentToCountry(){

	var parentId = $("#continent").val();
	var cityFrom = $("#country");
	var citySelect = $("#countrySelected").val();
	cityFrom.empty();
	$("#city").empty();
	$("#city").append("<option value=\"\">请选择</option>");
	if(parentId == ''){
		cityFrom.append("<option value=\"\">请选择</option>");
		return;
	}
	setTimeout(function(){
		getParentCityByParentId(parentId,cityFrom,citySelect);
	},500);
}

function getCountryToCity(parentId){
	if(parentId == null){
		parentId = $("#countrySelected").val();
	}
	var cityFrom = $("#city");
	var citySelect = $("#citySelected").val();
	cityFrom.empty();
	if(parentId == ''){
		cityFrom.append("<option value=\"\">请选择</option>");
		return;
	}
	setTimeout(function(){
		getParentCityByParentId(parentId,cityFrom,citySelect);
	},500);
}

function getParentCityByParentId(parentId,cityFrom,citySelect){

	//shCircleLoaderLoading();
	$.ajax({
		type:"GET",
		url:"<%=basePath%>city/ajaxGetCityList.html",
		data:{'parentId': parentId},
		dataType: "html",
		success: function(json){
			console.log(json);
			var rows =jQuery.parseJSON(json);
			var result = rows.rows;
			
			cityFrom.empty();
			cityFrom.append('<option value="">请选择</option>');
			$(result).each(function(index){
				if(result[index].id == citySelect){
					cityFrom.append('<option value="'+result[index].id+'" selected="selected" >'+result[index].cityName+'</option>');
				}else{
					cityFrom.append('<option value="'+result[index].id+'" >'+result[index].cityName+'</option>');
				}
				
		  	});
			shCircleLoaderDestroy();
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