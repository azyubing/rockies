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
		</ul>
		<!-- /section:basics/content.searchbox -->
	</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">目的地管理</h3>
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
									<label class=""> 目的地名称： </label>
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
	        '<a class="edit margin-left-20" href="javascript:void(0)" title="set" id="productBtn">',
	            '<i class="glyphicon glyphicon-edit"></i> 设定商品',
	        '</a>',
	        '<a class="edit margin-left-20" href="javascript:void(0)" title="Edit" id="detailBtn">',
	            '<i class="glyphicon glyphicon-edit"></i> 修改',
	        '</a>',
	        '<a class="edit margin-left-20" href="javascript:void(0)" title="delete" id="deleteBtn">',
	            '<i class="glyphicon glyphicon-edit"></i> 删除',
	        '</a>'
	    ].join('');
}


window.operateEvents = {
		'click #productBtn': function (e, value, row, index) {
			var id = row.id;
        	setTimeout(function(){
				window.location.href="<%=basePath%>destination/destinationSetProduct.html?menu=${menu}&id="+id;
			},500);
        },
        'click #detailBtn': function (e, value, row, index) {
        	$("#updateMenu").find("input[name='id']").val(row.id);
        	$("#updateMenu").find("input[name='sequence']").val(row.sequence);
        	$('#updateMenu').find("#updateContinent").val(row.continentId);
        	/* var x = row.x;
        	var y = row.x; */
        	var x = row.map_lng;
        	var y = row.map_lat;
        	if(x!=null&&x!=''&&x!='undefined'){
	        	document.getElementById('updatemapLng').value = x;
	    		document.getElementById('updatemapLat').value = y;
	        	$("#updatemapPosition").css({"left":x+"px","top":y+"px","display":"block"});
        		
        	}
        	shouwDetail(row.continentId,row.countryId);
			$('#updateMenu').modal('show');
        },
        'click #deleteBtn': function (e, value, row, index) {
        	var id = row.id;
        	var name= row.countryName;
			$.messager.confirm("提示", "是否确认删除:"+name, function() {
				shCircleLoaderLoading();
				shCircleLoaderDestroy();
				deleteForm(id);
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
    	
    		var url = "<%=basePath%>destination/destinationAdminSearchList.html";
    		
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
                    field: 'sequence',
                    title: '序号',
                    align: 'center',
                    valign: 'middle'
                },{
                    field: 'continentName',
                    title: '所属洲',
                    align: 'center',
                    valign: 'middle'
                },{
                    field: 'countryName',
                    title: '国家名',
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
			$('#addMenu').modal('show');
		});
		
		$("#returnBtn").click(function(){
			setTimeout(function(){
				window.location.href="<%=basePath%>media/mediaAdmin.html?menu=${menu}";
			},500);
		});
		
		$('#addMenu').on('hide.bs.modal', function () {
			
		});
		
		$('#updateMenu').on('hide.bs.modal', function () {
			
		});
		$("#search").click(function(){
			$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>destination/destinationAdminSearchList.html"});
		});

		$(document).keydown(function(event){
			if(event.keyCode == "13"){
				$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>destination/destinationAdminSearchList.html"});
			}
    	});
		
		
	});

	function submitForm(){
		var continentId = $("#addMenuForm").find("select[name='continentId']").val();
		var countryId = $("#addMenuForm").find("select[name='countryId']").val();
		var sequen = $("#addMenuForm").find("input[name='sequence']").val();
		
		if(continentId == null || continentId == "" ){
			alert("请选择大洲");
			return;
		}
		
		
		if(countryId == null || countryId == "" ){
			alert("请选择国家");
			return;
		}
		
		if(sequen == null || sequen == "" ){
			alert("请输入序号");
			return;
		}
	
		if(isNaN(sequen)){
			alert("请输入数字序号");
			return;
		}
		var mapLng = $("#addMenuForm").find("input[name='mapLng']").val();
		if(mapLng == null || mapLng == ""){
			alert("请双击地图位置获取对应位置点");
			return;
		}
		$.ajax({    
            type:'post',        
            url:'<%=basePath%>destination/saveOrUpdateDestinationAdmin.html',    
            data:$('#addMenuForm').serialize(),    
            cache:false,    
            dataType:'text',    
            success:function(data){
            	if(data=='success'){
            		alert("添加成功");
            		$('#addMenu').modal('hide');
            		$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>destination/destinationAdminSearchList.html"});
            	}
            }    
        });
	}
	
	function updatesubmitForm(){
		var continentId = $("#updateMenuForm").find("select[name='continentId']").val();
		var countryId = $("#updateMenuForm").find("select[name='countryId']").val();
		var sequen = $("#updateMenuForm").find("input[name='sequence']").val();
		if(continentId == null || continentId == "" ){
			alert("请选择大洲");
			return;
		}
		
		
		if(countryId == null || countryId == "" ){
			alert("请选择国家");
			return;
		}
		
		if(sequen == null || sequen == "" ){
			alert("请输入序号");
			return;
		}
	
		if(isNaN(sequen)){
			alert("请输入数字序号");
			return;
		}
		var mapLng = $("#updateMenuForm").find("input[name='mapLng']").val();
		if(mapLng == null || mapLng == ""){
			alert("请双击地图位置获取对应位置点");
			return;
		}
		$.ajax({    
            type:'post',        
            url:'<%=basePath%>destination/saveOrUpdateDestinationAdmin.html',    
            data:$('#updateMenuForm').serialize(),    
            cache:false,    
            dataType:'text',    
            success:function(data){
            	if(data=='success'){
            		alert("修改成功");
            		$('#updateMenu').modal('hide');
            		$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>destination/destinationAdminSearchList.html"});
            	}
            }    
        });
	}
	
	function deleteForm(id){
		$.ajax({    
            type:'post',        
            url:'<%=basePath%>destination/deleteDestinationAdmin.html',    
            data:{"id":id},    
            cache:false,    
            dataType:'text',    
            success:function(data){
            	if(data=='success'){
            		alert("删除成功");
            		$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>destination/destinationAdminSearchList.html"});
            	}
            }    
        });
	}
	//获取数据
	function ajaxRequestData(obj,selectId) {
		var parentId = $(obj).val();
		$.ajax({
			type : "post",
			url : "<%=basePath%>city/ajaxGetCityList.html",
			data : {
				"parentId" : parentId
			},
			cache : false,
			dataType : 'json',
			beforeSend : function() {
			},
			success : function(data) {
				changeSelect(data,selectId,parentId);
			}
		});
	}
	
	//改变下拉框
	function changeSelect(data,selectId,parentId){
		var json = data.rows;
		var html = '<option value="">请选择</option>';
		for(var i = 0;i<json.length;i++){
			var selected='';
			if(parentId==json[i].id){
				selected = 'selected="selected"';
			}
			html = html+'<option value="'+json[i].id+'"'+selected+' code="'+json[i].cityCode+'">'+json[i].cityName+'</option>';
		}
		$("#"+selectId).html(html);
	}
	
	function shouwDetail(parentId,id){
		$.ajax({
			type : "post",
			url : "<%=basePath%>city/ajaxGetCityList.html",
			data : {
				"parentId" : parentId
			},
			cache : false,
			dataType : 'json',
			beforeSend : function() {
			},
			success : function(data) {
				var selectId = "updateCountry";
				changeSelect(data,selectId,id);
			}
		});
	}
	
	function getMapCoordinate(mev){

		var px = mev.layerX;
	 	var py = mev.layerY;
		var x = (px-13);
		var y = (py+462+6); 
		var isIE=!!window.ActiveXObject; 
		if(isIE) {
			x = (px-4);
			y = (py+462-8);
		}
		$("#mapPosition").css({"left":x+"px","top":y+"px","display":"block"});
		document.getElementById('mapLng').value = x;
		document.getElementById('mapLat').value = y;
	  }
	function getUpdateMapCoordinate(mev){

		var px = mev.layerX;
	 	var py = mev.layerY;
		var x = (px-13);
		var y = (py+462+6); 
		var isIE=!!window.ActiveXObject; 
		if(isIE) {
			x = (px-4);
			y = (py+462-8);
		}
		$("#updatemapPosition").css({"left":x+"px","top":y+"px","display":"block"});
		document.getElementById('updatemapLng').value = x;
		document.getElementById('updatemapLat').value = y;
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
					<form role="form" action="<%=basePath%>addSiteMenu.html" method="post" id="addMenuForm">
						<div class="form-group">
							<label>所属洲：</label> 
							<select class="form-control" id="addContinent" name="continentId"  onchange="ajaxRequestData(this,'addCountry');">  
								<option value="">-请选择-</option>
								<c:forEach items="${cityList }" var="city">
									<option value="${city.id }">${city.cityName }</option>
								</c:forEach>
			                </select>
						</div>
						<div class="form-group">
							<label>国家名 ：</label> 
							<select class="form-control" id="addCountry" name="countryId" >  
								<option value="">-请选择-</option>
			                </select>
						</div>
						<div class="form-group">
							<label>序号 ：</label> 
							<input class="form-control" name="sequence" id="sequen" type="text">
						</div>
						<div class="form-group">
							<label>纬度 ：</label> 
							<input class="form-control" id="mapLng" name="mapLng" type="text">
						</div>
						<div class="form-group">
							<label>经度 ：</label> 
							<input class="form-control" id="mapLat" name="mapLat" type="text">
						</div>
					</form>
				</div>
				<div>       
					<img  src="<%=basePath%>assets/img/c1.png" style="width: 26px;height: 26px;position: absolute ;;top:462px;left:0px;z-index: 999999;display: none;" id="mapPosition">&nbsp;</img>
					<img alt="" src="<%=basePath%>assets/img/map.png" id="map" ondblclick="getMapCoordinate(event)" style="position: relative;">
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
					<input type="hidden" name="id">
						<div class="form-group">
							<label>所属洲：</label> 
							<select class="form-control" id="updateContinent" name="continentId" onchange="ajaxRequestData(this,'updateCountry');">  
								<option value="">-请选择-</option>
								<c:forEach items="${cityList }" var="city">
									<option value="${city.id }">${city.cityName }</option>
								</c:forEach>
			                </select>
						</div>
						<div class="form-group">
							<label>国家名 ：</label> 
							<select class="form-control" id="updateCountry" name="countryId">  
								<option value="">-请选择-</option>
			                </select>
						</div>
						<div class="form-group">
							<label>序号 ：</label> 
							<input class="form-control"  name="sequence" type="text">
						</div>
						<div class="form-group">
							<label>纬度 ：</label> 
							<input class="form-control" id="updatemapLng" name="mapLng" type="text">
						</div>
						<div class="form-group">
							<label>经度 ：</label> 
							<input class="form-control" id="updatemapLat" name="mapLat" type="text">
						</div>
					</form>
				</div>
				<div>       
					<img  src="<%=basePath%>assets/img/c1.png" style="width: 26px;height: 26px;position: absolute ;;top:462px;left:0px;z-index: 999999;" id="updatemapPosition">&nbsp;</img>
					<img alt="" src="<%=basePath%>assets/img/map.png" id="upatemap" ondblclick="getUpdateMapCoordinate(event)" style="position: relative;">
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