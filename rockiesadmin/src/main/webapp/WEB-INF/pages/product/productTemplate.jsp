<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>    
<%-- 基础信息 --%>
<div class="form-group col-md-12" >
	<label class="control-label" for="form-input-readonly" style="border-bottom: 1px dotted #478fca;">
		<h4>${pTypeName }基础信息</h4>
		<input id="pTypeCode" value="${pTypeCode }" type="hidden"/>
	</label> 
</div>
<div class="row">
	<div class="col-md-6">
		<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${pTypeName }名称： </label>
		<input id="pname" name="pname" value="${row.pname }" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"  />
	</div>
	<div class="col-md-6">
		<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${pTypeName }英文名： </label>
		<input id="pname_en" name="pname_en" value="${row.pname_en }" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
	</div>
</div>
<br>
<div class="row">
	<div class="col-md-6">
		<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;所属大洲： </label>
		<select id="continent" name="continent" class="form-control" style="display: inline-block; width: 50%;"/>  
			<option value="" <c:if test="${row.continent eq '' }"> selected="selected"</c:if>>-请选择-</option>
			<c:forEach var="item" items="${cityList}" varStatus="statusIdx">
				<option value="${item.id}" <c:if test="${row.continent eq item.id }"> selected="selected"</c:if>>${item.cityName}</option>
			</c:forEach>
              </select>
	</div>
	<div class="col-md-6">
		<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;所属国家： </label>
		<input type="hidden" id="countrySelected" value="${row.country }"  />
		<select id="country" name="country" class="form-control" style="display: inline-block; width: 50%;"/>  
			<option value="" >-请选择-</option>
        </select>
	</div>
</div>
<br>
<div class="row">
	<div class="col-md-6">
		<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;所属城市： </label>
		<input type="hidden" id="citySelected" value="${row.city }"  />
		<select id="city" name="city" class="form-control" style="display: inline-block; width: 50%;"/>  
			<option value="" >-请选择-</option>
              </select>
	</div>
	<div class="col-md-6">
		<label class=""> &nbsp;&nbsp;&nbsp;${pTypeName }详细地址： </label>
		<input id="map_address" name="map_address" value="${row.map_address }" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
	</div>
	
</div>
<br>
<div class="row">
	<div class="col-md-6">
		<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${pTypeName }纬度： </label>
		<input id="map_lat" name="map_lat" value="${row.map_lat }" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
		<a href="http://www.google.cn/maps/" target="_blank" alt="哈哈">点击获取经纬度</a>
	</div>
	<div class="col-md-6">
		<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${pTypeName }经度： </label>
		<input id="map_lng" name="map_lng" value="${row.map_lng }" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
	</div>
</div>
<br>
<div class="row">
	<div class="col-md-6">
		<label class=""> &nbsp;&nbsp;&nbsp;${pTypeName }供应商： </label>
		<select id="supplier_no" name="supplier_no" class="form-control" style="display: inline-block; width: 50%;"/>  
			<option value="">-请选择-</option>
			<c:forEach var="item" items="${suppList}" varStatus="statusIdx">
				<option value="${item.sid}" <c:if test="${row.supplier_no eq item.sid }"> selected="selected"</c:if>>${item.s_name}</option>
			</c:forEach>
              </select>
	</div>
	<div class="col-md-6">
		<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${pTypeName }状态： </label>
		<select id="pstatus" name="pstatus" class="form-control" style="display: inline-block; width: 50%;"/>  
			<option value="0" <c:if test="${row.pstatus eq '0' }"> selected="selected"</c:if>>未上架</option>
			<option value="1" <c:if test="${row.pstatus eq '1' }"> selected="selected"</c:if>>上架</option>
			<option value="2" <c:if test="${row.pstatus eq '2' }"> selected="selected"</c:if>>暂停</option>
              </select>
	</div>
</div>
<br>
<div class="row">
	<div class="col-md-6">
		<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开始时间：</label>
		<div style="display: inline-block; width: 50%;">
			<div class="input-group "  >
				<input id="startdate" name="startdate" value="<fmt:formatDate value="${row.startdate }" type="date"/>" class="form-control date-picker" type="text"  />
				<span class="input-group-addon">
					<i class="fa fa-calendar bigger-110"></i>
				</span>
			</div>
		</div>
	</div>
	<div class="col-md-6">
		<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结束时间：</label>
		<div style="display: inline-block; width: 50%;">
			<div class="input-group ">
				<input id="enddate" name="enddate" value="<fmt:formatDate value="${row.enddate }" type="date"/>" class="form-control date-picker" type="text" />
				<span class="input-group-addon">
					<i class="fa fa-calendar bigger-110"></i>
				</span>
			</div>
		</div>
	</div>
</div>
<br>
<div class="row">
	<div class="col-md-6">
		<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;起点价格： </label>
		<input id="start_price" name="start_price" value="<fmt:formatNumber value="${row.start_price}" type="currency" pattern="##.00"/>" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
		<span style="color: red;">*本${pTypeName }预定最低价格。</span>
	</div>
	<!-- 
	<div class="col-md-6">
		<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;预付款： </label>
		<input id="prepay" name="prepay" value="${row.prepay }" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
	</div>
	 -->
</div>
<br>
<div class="row">
	<div class="col-md-6">
		<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;出发城市： </label>
		<input id="fromcity" name="fromcity" value="${row.fromcity }" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
	</div>
	<div class="col-md-6">
		<label class=""> 确认时间(小时)： </label>
		<input id="confirm_time" name="confirm_time" value="${row.confirm_time }" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
	</div>
	<!-- 
	<div class="col-md-6">
		<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;已售数： </label>
		<input id="sale_cnt" name="sale_cnt" value="${row.sale_cnt }" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
	</div> -->
</div>
<br>
<!-- 
<div class="row">
	<div class="col-md-6">
		<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分享数： </label>
		<input id="sharecnt" name="sharecnt" value="${row.sharecnt }" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
	</div>
	<div class="col-md-6">
		<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;收藏数： </label>
		<input id="collection_cnt" name="collection_cnt" value="${row.collection_cnt }" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
	</div>
</div>
<br> -->								
<div class="row">
	<div class="col-md-6">
		<label class=""> &nbsp;&nbsp;&nbsp;服务费比例： </label>
		<input id="service_fee_rate" name="service_fee_rate" value="<fmt:formatNumber value="${row.service_fee_rate }" type="currency" pattern="0.000"/>" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
		<span style="color: red;">*如：7.7%则输入0.077</span>
	</div>
	<div class="col-md-6">
		<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;税费比例： </label>
		<input id="tax_rate" name="tax_rate" value="<fmt:formatNumber value="${row.tax_rate }" type="currency" pattern="0.000"/>" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
		<span style="color: red;">*如：7.7%则输入0.077</span>
	</div>
</div>
<br>							
<div class="row">
	<div class="col-md-12">
		<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;标签： </label>
		<input id="tags" name="tags" value="${row.tags }" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
		<span style="color: red;">*多个标签用|隔开。</span>
	</div>
</div>
<br>
<div class="row">
	<div class="col-md-12">
		<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;视频介绍： </label>
		<input id="video_url" name="video_url" value="<c:out value="${row.video_url}"/>" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
		<span style="color: red;">*输入各个站点的视屏链接,宽高比660:350</span>
	</div>
	
</div>
<br>
<div class="row">
	<div class="col-md-12">
		<label class="" > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${pTypeName }描述： </label>
		<div class="clearfix" style="margin-left: 25px;">
		<input id="pdesc" name="pdesc" type="hidden"/>
		<!--style给定宽度可以影响编辑器的最终宽度-->
		<script type="text/plain" id="pdescEditor" style="height:500px;width: 85%;"></script>
		</div>
		<div class="clearfix" style="margin-left: 25px;" >
			<button id="pdescTemplate" type="button">导入${pTypeName }描述模板</button>
		</div>
	</div>
</div>
<br>
<div class="row">
	<div class="col-md-12">
		<label class="" > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价格&条款： </label>
		<div class="clearfix" style="margin-left: 25px;">
		<input id="price_desc" name="price_desc" type="hidden"/>
		<!--style给定宽度可以影响编辑器的最终宽度-->
		<script type="text/plain" id="pricedescEditor" style="height:500px;width: 85%;"></script>
		</div>
		<div class="clearfix" style="margin-left: 25px;">
			<button id="price_descTemplate" type="button">导入价格&条款模板</button>
		</div>
	</div>
</div>
<br>
<div class="row">
	<div class="col-md-12">
		<label class="" > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注意事项： </label>
		<div class="clearfix" style="margin-left: 25px;">
			<input id="pnote" name="pnote" type="hidden"/>
			<!--style给定宽度可以影响编辑器的最终宽度-->
			<script type="text/plain" id="pnoteEditor" style="height:240px;width: 85%;"></script>
		</div>
		<div class="clearfix" style="margin-left: 25px;">
			<button id="pnoteTemplate" type="button">导入注意事项模板</button>
		</div>
	</div>
</div>


<% 
    request.setAttribute("vEnter1", "\n");
	request.setAttribute("vEnter2", "\t");
	request.setAttribute("vEnter3", "\r");
%>

		
<br>
<script type="text/javascript">
	
	
	$(function() { 
		
		//注意事项富文本
		var umpnote = UM.getEditor('pnoteEditor');
		umpnote.addListener("blur",function(){
			$("#pnote").val(umpnote.getContent());
    	});
		umpnote.ready(function() {
			<c:set var="tmp" value='${fn:replace(row.pnote, vEnter1, "")}'/>
			<c:set var="tmp" value='${fn:replace(tmp, vEnter2, "")}'/>
			<c:set var="tmp" value='${fn:replace(tmp, vEnter3, "")}'/>
			
			umpnote.setContent('${tmp}');

	    	$("#pnote").val(umpnote.getContent());
		});
		$("#pnoteTemplate").click(function(){
			shCircleLoaderLoading();
			$.ajax({
				type:"GET",
				url:"<%=basePath%>template/productTemplateSearch.html",
				data:{'ptypeCode': 'pnote','ptype':$("#pTypeCode").val()},
				dataType: "html",
				success: function(json){
					var result =jQuery.parseJSON(json);
					umpnote.setContent(result.temp_content);
			    	$("#pnote").val(umpnote.getContent());
					shCircleLoaderDestroy();
				}
			});
		});
		
		//描述富文本
		var umpdesc = UM.getEditor('pdescEditor');
		umpdesc.addListener("blur",function(){
			$("#pdesc").val(umpdesc.getContent());
    	});
		umpdesc.ready(function() {
			<c:set var="tmp" value='${fn:replace(row.pdesc, vEnter1, "")}'/>
			<c:set var="tmp" value='${fn:replace(tmp, vEnter2, "")}'/>
			<c:set var="tmp" value='${fn:replace(tmp, vEnter3, "")}'/>
			umpdesc.setContent('${tmp}');
			$("#pdesc").val(umpdesc.getContent());
		});
		$("#pdescTemplate").click(function(){
			shCircleLoaderLoading();
			$.ajax({
				type:"GET",
				url:"<%=basePath%>template/productTemplateSearch.html",
				data:{'ptypeCode': 'pdesc','ptype':$("#pTypeCode").val()},
				dataType: "html",
				success: function(json){
					var result =jQuery.parseJSON(json);
					umpdesc.setContent(result.temp_content);
					$("#pdesc").val(umpdesc.getContent());
					shCircleLoaderDestroy();
				}
			});
		});
		
		//价格&条款富文本
		var umpricedesc = UM.getEditor('pricedescEditor');
		umpricedesc.addListener("blur",function(){
			$("#price_desc").val(umpricedesc.getContent());
    	});
		umpricedesc.ready(function() {
			<c:set var="tmp" value='${fn:replace(row.price_desc, vEnter1, "")}'/>
			<c:set var="tmp" value='${fn:replace(tmp, vEnter2, "")}'/>
			<c:set var="tmp" value='${fn:replace(tmp, vEnter3, "")}'/>
			umpricedesc.setContent('${tmp}');
			$("#price_desc").val(umpricedesc.getContent());
			
			
		});
		$("#price_descTemplate").click(function(){
			shCircleLoaderLoading();
			$.ajax({
				type:"GET",
				url:"<%=basePath%>template/productTemplateSearch.html",
				data:{'ptypeCode': 'price_desc','ptype':$("#pTypeCode").val()},
				dataType: "html",
				success: function(json){
					var result =jQuery.parseJSON(json);
					umpricedesc.setContent(result.temp_content);
					$("#price_desc").val(umpricedesc.getContent());
					shCircleLoaderDestroy();
				}
			});
		});
		
		
		getContinentToCountry();
		getCountryToCity();

		$("#continent").change(function(){
			getContinentToCountry();
		});
		
		$("#country").change(function(){
			getCountryToCity($("#country").val());
		});
		
	});

</script>	
<script type="text/javascript">

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
	
	shCircleLoaderLoading();
	$.ajax({
		type:"POST",
		url:"<%=basePath%>city/ajaxGetCityList.html",
		data:{'parentId': parentId},
		dataType: "html",
		success: function(json){
			var result =jQuery.parseJSON(json);
			var rows = result.rows
			cityFrom.empty();
			cityFrom.append('<option value="">请选择</option>');
			$(rows).each(function(index){
				if(rows[index].id == citySelect){
					cityFrom.append('<option value="'+rows[index].id+'" selected="selected" >'+rows[index].cityName+'</option>');
				}else{
					cityFrom.append('<option value="'+rows[index].id+'" >'+rows[index].cityName+'</option>');
				}
				
		  	});
			shCircleLoaderDestroy();
		}
	});
}
</script>	