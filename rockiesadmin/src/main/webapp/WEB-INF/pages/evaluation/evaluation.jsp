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
<style type="text/css">
.treeview .list-group{margin: 0px 0px 10px;}
</style>
</head>
<body>

<div class="main-content" style="margin-top: 60px;">

<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="hr hr8 hr-double"></div>
		<div class="widget-box">
			<div class="widget-header">
				<h4 class="widget-title">评论详情</h4>
				<div class="widget-toolbar">
					<a href="#" data-action="collapse">
						<i class="ace-icon fa fa-chevron-up"></i>
					</a>
	
					<a href="#" data-action="close">
						<i class="ace-icon fa fa-times"></i>
					</a>
				</div>
				<div class="widget-body">
					<div class="widget-main">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group" >
									<label class="col-md-4 control-label no-padding-right " for="form-field-1"> 商品名：${cMap.productName } </label>
									<label class="col-md-6 control-label" for="form-field-1" style="text-align: left;"> 客户账号：${cMap.userAccount }</label>
									<div class="col-md-2"></div>
								</div>
								<div class="form-group" >
									<label class="col-md-4 control-label no-padding-right " for="form-field-1"> 商铺编码：${cMap.storeCode }</label>
									<label class="col-md-6 control-label" for="form-field-1" style="text-align: left;"> 客户ID：${cMap.userId }</label>
									<div class="col-md-2"></div>
								</div>
								<div class="form-group" >
									<label class="col-md-4 control-label no-padding-right " for="form-field-1"> 商圈编码：${cMap.site }</label>
									<label class="col-md-4 control-label" for="form-field-1" style="text-align: left;"> 评论时间：${cMap.createTime }</label>
								</div>
								<div class="form-group" >
									<div class="col-md-12">&nbsp;</div>
								</div>
								<div class="form-group" >
									<div class="col-md-12">&nbsp;</div>
								</div>
								<div class="form-group" >
									<label class="col-md-8 control-label" for="form-field-1"> 评论状态：<c:if test="${cMap.details[0].status==1 }">已通过商圈批准</c:if><c:if test="${cMap.details[0].status!=1 }">未通过商圈批准</c:if></label>
									<div class="col-md-4"></div>
								</div>
								<div class="form-group" >
									<div class="col-md-12">
										<div class="widget-header">
											<h4 class="widget-title lighter smaller">
												<i class="ace-icon fa fa-comment blue"></i>
												咨询内容
											</h4>
										</div>
										<div class="dialogs">
											<div class="itemdiv dialogdiv">
												<c:forEach var="item" items="${cMap.details}" varStatus="statusIdx">
												<div class="itemdiv dialogdiv">
													<div class="user">
														<img alt="Alexa's Avatar" src="../assets/avatars/avatar1.png" />
													</div>
	
													<div class="body">
														<div class="time">
															<i class="ace-icon fa fa-clock-o"></i>
															<span class="green">4 sec</span>
														</div>
	
														<div class="name">
															<a href="#">${item.userAccount }</a>
														</div>
														<div class="text">
															<pre>${item.content }</pre>
														</div>
														<div class="tools">
															<a href="#" class="btn btn-minier btn-info">
																<i class="icon-only ace-icon fa fa-share"></i>
															</a>
														</div>
													</div>
												</div>
											</c:forEach>
											</div>
										</div>
										<!-- /section:pages/dashboard.conversations -->
										<form action="<%=basePath%>addStoreConsulting.html" method="post" id="addConsultingForm">
											<div class="form-actions">
												<div class="input-group">
													<input type="hidden" value="${cMap.details[0].detailId }" name="parentDetailId"/>
													<input type="hidden" value="${cMap.reviewId }" name="reviewId"/>
													<input type="hidden" value="${cMap.productId }" name="productId"/>
													<input placeholder="Type your message here ..." type="text" class="form-control" name="sendMsg" />
													<span class="input-group-btn">
														<button class="btn btn-sm btn-info no-radius" type="button" onclick="sendEva();">
															<i class="ace-icon fa fa-share"></i>
															Send
														</button>
													</span>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div><!-- col-xs-12 -->
						</div>
					</div><!-- widget-main -->
				</div><!-- widget-body -->
			</div>
		</div>
	</div><!-- /.page-content-area -->
</div><!-- /.page-content -->
<div>


</div>
</div><!-- /.main-content -->
<script type="text/javascript">
function sendEva(){
	$('#addConsultingForm').submit();
}
	
</script>	
<script type="text/javascript">
	$(function() { 
		getMenu();
		
	});
</script>
</body>
</html>