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

<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
	<h4>整体销售统计</h4>
		<div class="row">
			<div class="col-md-12">
				<div class="widget-body">
					<div class="widget-main no-padding">
						<table class="table table-bordered table-striped" style="border-width: 1px;">
							<thead class="thin-border-bottom">
								<tr>
									<th><i class="ace-icon fa fa-caret-right blue"></i>累计完成订单数量</th>
									<th ><i class="ace-icon fa fa-caret-right blue"></i>待审核订单数</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><b id="totalOrder" class="green" style="font-size: 20px; font-weight: 900;">${orderCompleteCount }</b></td>
									<td><b id="PendingOrder" class="orange" style="font-size: 20px; font-weight: 900;">${orderDealCount }</b></td>
								</tr>
							</tbody>
						</table>
					</div><!-- /.widget-main -->
				</div>							
			</div>
		</div><!-- /.row -->
			<div class="hr hr8 "></div>
	<h4>用户信息统计</h4>
		<div class="row">
			<div class="col-md-12">
				<div class="widget-body">
					<div class="widget-main no-padding">
						<table class="table table-bordered table-striped" style="border-width: 1px;">
							<thead class="thin-border-bottom">
								<tr>
									<th><i class="ace-icon fa fa-caret-right blue"></i>累计客户数量</th>
									
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><b id="totalUser" class="blue" style="font-size: 20px; font-weight: 900;">${customerCount }</b></td>
									
								</tr>
							</tbody>
						</table>
					</div><!-- /.widget-main -->
				</div>							
			</div>
		</div><!-- /.row -->
			<div class="hr hr8 "></div>
	<h4>供应商信息统计</h4>
		<div class="row">
			<div class="col-md-12">
				<div class="widget-body">
					<div class="widget-main no-padding">
						<table class="table table-bordered table-striped" style="border-width: 1px;">
							<thead class="thin-border-bottom">
								<tr>
									<th><i class="ace-icon fa fa-caret-right blue"></i>累计供应商数量</th>
									
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><b id="totalStore" class="purple" style="font-size: 20px; font-weight: 900;">${supplierCount }</b></td>
									
								</tr>
							</tbody>
						</table>
					</div><!-- /.widget-main -->
				</div>							
			</div>
		</div><!-- /.row -->
	</div><!-- /.page-content-area -->
</div><!-- /.page-content -->
</div><!-- /.main-content -->
<script type="text/javascript">
$(function() {
	
});
</script>
</body>
</html>
