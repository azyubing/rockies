<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath =
            request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
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

    <!-- /section:basics/content.breadcrumbs -->
    <div class="page-content">
        <div class="page-content-area">
            <div class="row">
                <div class="col-md-12">
                    <h3 class="header smaller lighter blue">角色基本信息</h3>

                    <div class="table-header" style="margin-top: 20px;">角色基本信息</div>
                    <input type="hidden" value=${role} id="role">
                    <table id="tableInfo"
                           class="table table-bordered table-hover table-striped tablesorter">
                        <tr>
                            <td>角色名称：</td>
                            <td><input type="text" readonly="readonly" id="roleName"
                                       value="${roleInfo.roleName}"></td>
                            <%--<td>描述说明：</td>--%>
                            <%--<td><input type="text" readonly="readonly"--%>
                                       <%--value="${role==1?'平台管理员':(role==2?'商圈管理员':'商户管理员')}"></td>--%>
                            <td>角色英文名称：</td>
                            <td><input type="text" readonly="readonly"
                                       value="${roleInfo.roleNameEn}"></td>
                            <%-- <td>角色类型：</td>
                            <td><input type="text" readonly="readonly"
                                       value="${roleType}"></td> --%>
                        </tr>
                    </table>
                    <div class="table-header" style="margin-top: 20px;">页面访问权限</div>
                    <div id="tree">
                    	<table id="permissionInfo">
                    		<tr>
	                    		<th>菜单&nbsp;&nbsp;&nbsp;&nbsp;</th>
	                    		<th>选择&nbsp;&nbsp;&nbsp;&nbsp;</th>
	                    		<th>是否需要权限认证</th> 		
                    		</tr>
                    		<%-- <c:forEach items="${user_loginInfoVO.permissionInfoVOList }" var="permissionInfoVO">
                    			<tr>
									<input type="hidden" value="${permissionInfoVO.pid}" name="pid"/>
									<td>${permissionInfoVO.permissionName}</td>
									<td pid="${permissionInfoVO.pid}"><input type="checkbox" onclick="changeParent(this)"
										<c:forEach items="${rolePermissionList }" var="rolePermission">
											<c:if test="${rolePermission.pid == permissionInfoVO.pid}">
												checked="checked"
											</c:if>
										</c:forEach>
									/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td>
										<input type="checkbox" 
											<c:forEach items="${rolePermissionList }" var="rolePermission">
												<c:if test="${rolePermission.pid == permissionInfoVO.pid && rolePermission.isCheck==0}">
													checked="checked"
												</c:if>
											</c:forEach>
										/>&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
								</tr>
								<c:if test="${permissionInfoVO.childPermissionInfoList!=null}">
									<c:forEach var="permissionInfo" items="${permissionInfoVO.childPermissionInfoList}" varStatus="statusIdx">
										<tr>
											<input type="hidden" value="${permissionInfo.pid}" name="pid"/>
											<td>&nbsp;&nbsp;&nbsp;&nbsp;${permissionInfo.permissionName }&nbsp;&nbsp;&nbsp;&nbsp;</td>
											<td parentPid="${permissionInfo.parentPid}"><input type="checkbox" onclick="changeChild(this)" 
													<c:forEach items="${rolePermissionList }" var="rolePermission">
														<c:if test="${rolePermission.pid== permissionInfoVO.pid}">
															checked="checked"
														</c:if>
													</c:forEach>
											/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
											<td><input type="checkbox" 
												<c:forEach items="${rolePermissionList }" var="rolePermission">
													<c:if test="${rolePermission.pid==permissionInfo.pid && rolePermission.isCheck==0}">
														checked="checked"
													</c:if>
												</c:forEach>
											/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
										</tr>
									</c:forEach>
								</c:if>
                    		</c:forEach> --%>
							<c:forEach var="permissionInfoVO" items="${permissionInfoVOList}" >
								<tr>
									<input type="hidden" value="${permissionInfoVO.pid}" name="pid"/>
									<td>${permissionInfoVO.permissionName}</td>
									<td pid="${permissionInfoVO.pid}"><input type="checkbox" onclick="changeParent(this)"
										<c:forEach items="${rolePermissionList }" var="rolePermission">
											<c:if test="${rolePermission.pid == permissionInfoVO.pid}">
												checked="checked"
											</c:if>
										</c:forEach>
									/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td>
										<input type="checkbox" 
											<c:forEach items="${rolePermissionList }" var="rolePermission">
												<c:if test="${rolePermission.pid == permissionInfoVO.pid && rolePermission.isCheck==0}">
													checked="checked"
												</c:if>
											</c:forEach>
										/>&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
								</tr>
								<c:if test="${permissionInfoVO.childPermissionInfoList!=null}">
									<c:forEach var="permissionInfo" items="${permissionInfoVO.childPermissionInfoList}">
										<tr>
											<input type="hidden" value="${permissionInfo.pid}" name="pid"/>
											<td>&nbsp;&nbsp;&nbsp;&nbsp;${permissionInfo.permissionName }&nbsp;&nbsp;&nbsp;&nbsp;</td>
											<td parentPid="${permissionInfo.parentPid}"><input type="checkbox" onclick="changeChild(this)" 
													<c:forEach items="${rolePermissionList }" var="rolePermission">
														<c:if test="${rolePermission.pid == permissionInfo.pid}">
															checked="checked"
														</c:if>
													</c:forEach>
											/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
											<td><input type="checkbox" 
												<c:forEach items="${rolePermissionList }" var="rolePermission">
													<c:if test="${rolePermission.pid==permissionInfo.pid && rolePermission.isCheck==0}">
														checked="checked"
													</c:if>
												</c:forEach>
											/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
										</tr>
									</c:forEach>
								</c:if>
							</c:forEach>
                    	</table>
                    </div>
                    <div>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <div class="input-group" >
							<span class="">
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
        <!-- /.page-content-area -->
    </div>
    <form action="" method="post" id="addForm"></form>
    <!-- /.page-content -->
    <script type="text/javascript">

        $(function () {
        	$("#addBtn").click(function(){
        		var tr = $("#permissionInfo").find("tr");
        		var list = '';
        		for(var i=0;i<tr.length;i++){
        			if($(tr).eq(i).find("td").eq(1).find("input").is(':checked')){
        				var rolePermission={};
        				var pid = $(tr).eq(i).find("input[name='pid']").val();
        				var ischeck=1;
        				if($(tr).eq(i).find("td").eq(2).find("input").is(':checked')){
        					ischeck=0;
        				}
        				var s = pid+","+ischeck+","+'${roleInfo.roleId}';
        				list = list+s+"=";
        			}
        		}
        		$.ajax({    
                    type:'post',        
                    url:'<%=basePath%>role/saveRolePermission.html',    
                    data:{"rolePermissionList":list},    
                    cache:false,    
                    dataType:'text',    
                    success:function(data){
                    	if(data=='success'){
                    		alert("添加成功");
		    				window.location.href="<%=basePath%>role/roleAdmin.html?menu=${menu}";
                    	}
                    }    
                });
        		
    		});
        	
        	$("#returnBtn").click(function(){
    			setTimeout(function(){
    				window.location.href="<%=basePath%>role/roleAdmin.html?menu=${menu}";
    			},500);
    		});
        });
        
        function changeParent(obj){
        	var pid = $(obj).parent().attr("pid");
        	var isChecked = $(obj).val();
        	if($(obj).is(':checked')){
        		var tr = $("#permissionInfo").find("tr");
        		for(var i = 0;i<tr.length;i++){
        			var td = $(tr).eq(i).find("td");
        			for(var j = 0;j<td.length;j++){
        				if($(td).eq(j).attr("parentPid")==pid){
                			$(td).eq(j).find("input").prop("checked",true);
                		}
            		}
        		}
        	}else{
        		var tr = $("#permissionInfo").find("tr");
        		for(var i = 0;i<tr.length;i++){
        			var td = $(tr).eq(i).find("td");
        			for(var j = 0;j<td.length;j++){
        				if($(td).eq(j).attr("parentPid")==pid){
                			$(td).eq(j).find("input").prop("checked",false);
                		}
            		}
        		}
        	}
        }
        
		function changeChild(obj){
        	var parentId = $(obj).parent().attr("parentPid");
        	var isChecked = $(obj).val();
        	
       		var tr = $("#permissionInfo").find("tr");
        	if($(obj).is(':checked')){
        		for(var i = 0;i<tr.length;i++){
        			var td = $(tr).eq(i).find("td");
        			for(var j = 0;j<td.length;j++){
        				if($(td).eq(j).attr("pid")==parentId){
                			$(td).eq(j).find("input").prop("checked",true);
                		}
            		}
        		}
        	}else{
        		var flag = false;
        		for(var i = 0;i<tr.length;i++){
        			var td = $(tr).eq(i).find("td");
        			for(var j = 0;j<td.length;j++){
	        			if($(td).eq(j).attr("parentPid")==parentId){
		            		if($(td).eq(j).find("input").is(':checked')){
		            			flag = true;
		            		}
	        			}
        			}
        		}
        		if(!flag){
        			for(var i = 0;i<tr.length;i++){
            			var td = $(tr).eq(i).find("td");
            			for(var j = 0;j<td.length;j++){
	            			if($(td).eq(j).attr("pid")==parentId){
	            				$(td).eq(j).find("input").prop("checked",false);
	            			}
            			}
            		}
        		}
        	}
        }
    </script>
    <div>

    </div>
</div>
<!-- /.main-content -->
<script type="text/javascript">
	$(function() { 
		getMenu();
		
	});
</script>
</body>
</html>
