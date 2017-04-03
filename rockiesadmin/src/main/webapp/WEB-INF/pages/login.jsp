<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>麦味管理平台系统</title>
<link rel="shortcut icon" href="<%=basePath%>assets/img/favicon.png">
<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="<%=basePath%>assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/font-awesome.min.css" />
<!-- text fonts -->
<link rel="stylesheet" href="<%=basePath%>assets/css/ace-fonts.css" />
<!-- ace styles -->
<link rel="stylesheet" href="<%=basePath%>assets/css/ace.min.css" />
<!--[if lte IE 9]>
	<link rel="stylesheet" href="<%=basePath%>assets/css/ace-part2.min.css" />
<![endif]-->
<link rel="stylesheet" href="<%=basePath%>assets/css/ace-rtl.min.css" />
<!--[if lte IE 9]>
  <link rel="stylesheet" href="<%=basePath%>assets/css/ace-ie.min.css" />
<![endif]-->
<link rel="stylesheet" href="<%=basePath%>assets/css/ace.onpage-help.css" />
<!--[if lt IE 9]>
<script src="<%=basePath%>assets/js/html5shiv.js"></script>
<script src="<%=basePath%>assets/js/respond.min.js"></script>
<![endif]-->
<style type="text/css">
body,h1,h2,h3,h4,div,a,p,span,input,label{font-family: "microsoft yahei";}
</style>
</head>
<body class="login-layout light-login">
<div class="main-container" style="margin-top: 50px;">
	<div class="main-content">
		<div class="row">
			<div class="col-sm-10 col-sm-offset-1">
				<div class="login-container">
					<div class="center">
						<h1>
							<i class="ace-icon fa fa-rocket blue"></i>
							<span class="red">麦味</span>
							<span class="blue" id="id-text2" class="grey">管理平台系统V1.0</span>
							<span class="blue" id="id-text2" class="grey">&nbsp;</span>
						</h1>
					</div>
					<div class="space-6"></div>
					<div class="position-relative">
						<div id="login-box" class="login-box visible widget-box no-border">
							<div class="widget-body">
								<div class="widget-main">
									<h4 class="header blue lighter bigger">
										<i class="ace-icon fa fa-coffee green"></i>
										请输入您的信息
									</h4>
									<div class="space-6"></div>
									<form action="login.html" method="post">
										<fieldset>
											<label class="block clearfix">
												<span class="block input-icon input-icon-right">
													<input type="text" name="loginName" class="form-control" placeholder="用户名" />
													<i class="ace-icon fa fa-user"></i>
												</span>
											</label>

											<label class="block clearfix" style="margin-top: 10px;">
												<span class="block input-icon input-icon-right">
													<input type="password" name="password" class="form-control" placeholder="密码" />
													<i class="ace-icon fa fa-lock"></i>
												</span>
											</label>
											
										<div class="space"></div>
											<div class="clearfix">
												<div>
													
													<button type="submit" class="width-35 pull-right btn btn-sm btn-primary clearfix">
													<i class="ace-icon fa fa-key "></i>
													<span class="bigger-110">登陆</span>
													</button>
												</div>
											</div>
										<div class="space-4"></div>
										</fieldset>
									</form><!-- /.widget-main -->
								 </div>
								 <div id="msg" >
								 	<font color="red">
								 		<c:choose>
								 			<c:when test="${resMap.err eq '500001'}">用户名密码错误,请重新输入</c:when>
								 			<c:when test="${resMap.err eq '500010'}">用户名或密码不能为空</c:when>
								 		</c:choose>
								 	</font>
								 </div>
							</div><!-- /.widget-body -->
						</div><!-- /.login-box -->
					</div><!-- /.position-relative -->
				</div>
			</div><!-- /.col -->
		</div><!-- /.row -->
	</div><!-- /.main-content -->
</div><!-- /.main-container -->
<!-- basic scripts -->
<!--[if !IE]> -->
<script type="text/javascript">
	window.jQuery || document.write("<script src='<%=basePath%>assets/js/jquery.min.js'>"+"<"+"/script>");
</script>

<!-- <![endif]-->
<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='<%=basePath%>assets/js/jquery1x.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
<script type="text/javascript">
	if('ontouchstart' in document.documentElement) document.write("<script src='<%=basePath%>assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
</body>
</html>
