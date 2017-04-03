<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>麦味管理平台系统</title>
    <link rel="shortcut icon" href="<%=basePath%>assets/img/favicon.png">
    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="<%=basePath%>assets/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>assets/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>assets/css/colorbox.css" />
    <!-- text fonts -->
    <link rel="stylesheet" href="<%=basePath%>assets/css/ace-fonts.css"/>
    <!-- ace styles -->
    <link rel="stylesheet" href="<%=basePath%>assets/css/ace.min.css"
          id="main-ace-style"/>
    <!--[if lte IE 9]>
    <link rel="stylesheet" href="<%=basePath%>assets/css/ace-part2.min.css"/>
    <![endif]-->
    <link rel="stylesheet" href="<%=basePath%>assets/css/ace-skins.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>assets/css/ace-rtl.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>assets/css/chosen.css"/>
    <link rel="stylesheet" href="<%=basePath%>assets/css/datepicker.css"/>
    <link rel="stylesheet" href="<%=basePath%>assets/css/daterangepicker.css"/>
    <link rel="stylesheet" href="<%=basePath%>assets/css/bootstrap-table.css"/>
    <link rel="stylesheet" href="<%=basePath%>assets/css/bootstrap-treeview.css"/>
    <link rel="stylesheet" href="<%=basePath%>assets/css/ace.onpage-help.css" />
    <link rel="stylesheet" href="<%=basePath%>assets/css/jquery.shCircleLoader.css" />
    <link rel="stylesheet" href="<%=basePath%>assets/js/umeditor/themes/default/css/umeditor.css" />
    <!--[if lte IE 9]>
    <link rel="stylesheet" href="<%=basePath%>assets/css/ace-ie.min.css"/>
    <![endif]-->

    <!--[if lt IE 9]>
    <script src="<%=basePath%>assets/js/html5shiv.js"></script>
    <script src="<%=basePath%>assets/js/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
        body, h1, h2, h3, h4, div, a, p, span, input, label {
            font-family: "microsoft yahei";
        }

        #right-bar {
            display: block;
            height: 60px;
        }

        .navbar {
            min-height: 60px;
        }

        #right-bar p {
            margin: 0px;
            text-align: left;
            height: 20px;
            line-height: 18px;
        }

        #li-blue {
            height: 60px;
        }

        .btn-group > .btn.btn-sm > .caret {
            margin-top: 0px !important;
        }

        .margin-left-20 {
            margin-left: 20px;
        }
    </style>
    <sitemesh:write property='head'/>
</head>
<body class="no-skin">
<div id="mask" style="width: 100%;height: 100%; background-color: #fff; filter:alpha(opacity=50);-moz-opacity:0.5;-khtml-opacity: 0.5;
	opacity: 0.5; position: absolute; z-index: 1497; display: none;">
</div>
<div id="shclDefault" style="width: 100px; height: 100px; display: none; position: absolute;top:48%;left: 48%; z-index: 1499; " ></div>

<!-- #section:basics/navbar.layout -->
<div id="navbar" class="navbar navbar-default navbar-fixed-top">
    <div class="navbar-container" id="navbar-container">
        <!-- #section:basics/sidebar.mobile.toggle -->
        <button type="button" class="navbar-toggle menu-toggler pull-left"
                id="menu-toggler">
            <span class="sr-only">Toggle sidebar</span> <span class="icon-bar"></span>
            <span class="icon-bar"></span> <span class="icon-bar"></span>
        </button>
        <!-- /section:basics/sidebar.mobile.toggle -->
        <div class="navbar-header pull-left" style="min-width: 320px;">
            <!-- #section:basics/navbar.layout.brand -->
            <a href="home.html" class="navbar-brand" style="margin-top: 8px;">
                <i class="fa fa-cogs"></i> 麦味管理平台
                <c:if test="${loginInfo.site > 0 }">
                	.${loginInfo.siteInfo.name }
                	<input type="hidden" id="site" value="${loginInfo.site}" >
                	<c:if test="${loginInfo.store > 0 }">
                		.${loginInfo.storeInfo.name }
                		<input type="hidden" id="store" value="${loginInfo.store}" >
            		</c:if>
            	</c:if>
            </a>
            <!-- /section:basics/navbar.layout.brand -->
            <!-- #section:basics/navbar.toggle -->
            <!-- /section:basics/navbar.toggle -->
        </div>
        <!-- #section:basics/navbar.dropdown -->
        <div class="navbar-buttons navbar-header pull-right"
             role="navigation">
            <ul class="nav ace-nav">
                <li class="light-blue" id="li-blue"><a data-toggle="dropdown"
                                                       href="#" class="dropdown-toggle"
                                                       id="right-bar">
                     <p style="height: 10px;">&nbsp;</p>
                    <p>
                        <i class="ace-icon fa fa-caret-down" style="position: relative;top:10px;"></i>&nbsp;&nbsp;&nbsp;
                        <small>管理员账号：&nbsp;${user_loginInfoVO.loginInfo.loginName }</small>
                    </p>
                    <p>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <small>管理员角色：&nbsp;${user_loginInfoVO.roleName }</small>
                    </p>
                   
                </a>
                    <ul
                            class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li><a class="edit margin-left-20" href="javascript:void(0)" title="Edit" onclick="updatePasswordBtn()"> <i class="ace-icon fa fa-user"></i>
                            修改密码
                        </a></li>
                        <li class="divider"></li>
                        <li><a class="edit margin-left-20" href="<%=basePath%>/loginOut.html"> <i
                                class="ace-icon fa fa-power-off"></i> 注销
                        </a></li>
                    </ul>
                </li>
                <!-- /section:basics/navbar.user_menu -->
            </ul>
        </div>
    </div>
    <!-- /.navbar-container -->
</div>



<!-- update password 修改密码 -->
<div class="modal fade" id="updateAdminPassword" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">修改密码 </h4>
                </div>
                <div class="modal-body">
                    <form class="form" role="form" action=""
                          method="post"
                          id="updateadminpassword">
                        

                        <div class="form-group">
                            <label>新密码</label> <input class="form-control" id="newAdminPassword" type="password"                                            
                                                     name="newAdminPassword" placeholder="请输入新密码">
                        </div>                       
						
                        <div class="form-group">
                            <label>确认密码</label> <input class="form-control" id="reNewAdminPassword" type="password" 
                                                       name="reNewAdminPassword" placeholder="请再次输入新密码">
                        </div>
						
                      
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button id="updatSubmit" type="button" class="btn btn-primary">提交</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal -->
    </div>
<!-- basic scripts -->

<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='<%=basePath%>assets/js/jquery.min.js'>" + "<" + "/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='<%=basePath%>assets/js/jquery1x.min.js'>" + "<" + "/script>");
</script>
<![endif]-->
<script type="text/javascript">
    if ('ontouchstart' in document.documentElement) document.write("<script src='<%=basePath%>assets/js/jquery.mobile.custom.min.js'>"
            + "<" + "/script>");
</script>
<script src="<%=basePath%>assets/js/bootstrap.min.js"></script>

<!-- page specific plugin scripts -->
<script src="<%=basePath%>assets/js/jquery.form.js"></script>
<!--[if lte IE 8]>
<script src="<%=basePath%>assets/js/excanvas.min.js"></script>
<![endif]-->
<script src="<%=basePath%>assets/js/jquery-ui.custom.min.js"></script>
<script src="<%=basePath%>assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="<%=basePath%>assets/js/jquery.sparkline.min.js"></script>
<script src="<%=basePath%>assets/js/flot/jquery.flot.min.js"></script>
<script src="<%=basePath%>assets/js/flot/jquery.flot.pie.min.js"></script>
<script src="<%=basePath%>assets/js/flot/jquery.flot.resize.min.js"></script>

<!-- ace scripts -->
<script src="<%=basePath%>assets/js/ace.min.js"></script>
<script src="<%=basePath%>assets/js/ace-elements.min.js"></script>
<script src="<%=basePath%>assets/js/bootstrap-treeview.js"></script>
<script src="<%=basePath%>assets/js/fuelux/fuelux.spinner.min.js"></script>


<script src="<%=basePath%>assets/js/chosen.jquery.min.js"></script>
<script src="<%=basePath%>assets/js/fuelux/fuelux.spinner.min.js"></script>
<script src="<%=basePath%>assets/js/date-time/bootstrap-datepicker.js"></script>
<script src="<%=basePath%>assets/js/date-time/bootstrap-timepicker.min.js"></script>
<script src="<%=basePath%>assets/js/date-time/moment.min.js"></script>
<script src="<%=basePath%>assets/js/date-time/daterangepicker.min.js"></script>
<script src="<%=basePath%>assets/js/date-time/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath%>assets/js/bootstrap-colorpicker.min.js"></script>
<script src="<%=basePath%>assets/js/jquery.knob.min.js"></script>
<script src="<%=basePath%>assets/js/jquery.autosize.min.js"></script>
<script src="<%=basePath%>assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
<script src="<%=basePath%>assets/js/jquery.maskedinput.min.js"></script>
<script src="<%=basePath%>assets/js/jquery.messager.js"></script>
<script src="<%=basePath%>assets/js/jquery.dialog.js"></script>
<script src="<%=basePath%>assets/js/bootstrap-tag.min.js"></script>
<script src="<%=basePath%>assets/js/typeahead.jquery.min.js"></script>

<script type="text/javascript"> ace.vars['base'] = '..'; </script>
<script src="<%=basePath%>assets/js/ace/elements.onpage-help.js"></script>
<script src="<%=basePath%>assets/js/ace/ace.onpage-help.js"></script>

<script src="<%=basePath%>assets/js/jquery.dataTables.min.js"></script>
<script src="<%=basePath%>assets/js/jquery.dataTables.bootstrap.js"></script>

<script src="<%=basePath%>assets/js/bootstrap-table.min.js"></script>
<%-- <script src="<%=basePath%>assets/js/bootstrap-table.js"></script> --%>

<script src="<%=basePath%>assets/js/fuelux/fuelux.wizard.min.js"></script>
<script src="<%=basePath%>assets/js/jquery.colorbox-min.js"></script>
<script src="<%=basePath%>assets/js/ace/elements.onpage-help.js"></script>


	
<script src="<%=basePath%>assets/js/bootstrap-wysiwyg.min.js"></script>
<script src="<%=basePath%>assets/js/jquery.hotkeys.min.js"></script>

<script src="<%=basePath%>assets/js/jquery.shCircleLoader-min.js"></script>

<script src="<%=basePath%>assets/js/javascriptCheck.js"></script>


<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">

    <!-- #section:basics/sidebar -->
    <div id="sidebar" class="sidebar sidebar-fixed responsive">

        <%-- <div class="sidebar-shortcuts" id="sidebar-shortcuts">
            <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
                <img src="<%=basePath%>assets/logo.png">
                <!-- /section:basics/sidebar.layout.shortcuts -->
            </div>
        </div> --%>
        <!-- /.sidebar-shortcuts -->

        <ul class="nav nav-list">
			<li id="menu_home">
           		<a href="<%=basePath%>home.html"> 
           			<i class="menu-icon fa fa-home"></i> 
           			<span class="menu-text">首页 </span>
				</a> 
				<b class="arrow"></b>
			</li>
			<c:forEach items="${user_loginInfoVO.permissionInfoVOList }" var="permissionInfoVO">
				<li class="" menuid="${permissionInfoVO.pid }">
					<a href="#" class="dropdown-toggle"> 
						<i class="menu-icon fa fa-caret-right"></i> 
						<span class="menu-text">${permissionInfoVO.permissionName } </span> 
						<b class="arrow fa fa-angle-down"></b>
					</a> 
					<b class="arrow"></b>
					<ul class="submenu">
						<c:forEach items="${permissionInfoVO.childPermissionInfoList}" var="p">
							<li class="" menuid="${p.pid}" pmenuid="${p.parentPid }">
								<%-- <c:if test='${fn:contains(p.permissionUrl,"?")}'>
									<a href="<%=basePath%>${p.permissionUrl}&menu=${p.pid}"> 
										<i class="menu-icon fa fa-caret-right"></i> ${p.permissionName }
									</a> 
								</c:if>
								<c:if test='${fn:contains(p.permissionUrl,"?")}'>
									<a href="<%=basePath%>${p.permissionUrl}?menu=${p.pid}"> 
										<i class="menu-icon fa fa-caret-right"></i> ${p.permissionName }
									</a> 
								</c:if> --%>
								<c:choose>
									<c:when test='${fn:contains(p.permissionUrl,"?")}'>
										<a href="<%=basePath%>${p.permissionUrl}&menu=${p.pid}"> 
											<i class="menu-icon fa fa-caret-right"></i> ${p.permissionName }
										</a> 
									</c:when>
									<c:otherwise>
										<a href="<%=basePath%>${p.permissionUrl}?menu=${p.pid}"> 
											<i class="menu-icon fa fa-caret-right"></i> ${p.permissionName }
										</a> 
									</c:otherwise>
								
								</c:choose>
								<%-- <c:if test="${fn:contains(p.permissionUrl,'?')}">
									<a href="<%=basePath%>${p.permissionUrl}?menu=${p.pid}"> 
										<i class="menu-icon fa fa-caret-right"></i> ${p.permissionName }
									</a> 
								</c:if>
								<a href="<%=basePath%>${p.permissionUrl}?menu=${p.pid}"> 
									<i class="menu-icon fa fa-caret-right"></i> ${p.permissionName }
								</a>  --%>
								<b class="arrow"></b>
							</li>
						</c:forEach>
					</ul>
				</li>
			</c:forEach>
			
			<%-- <li class="" menuid="0_1">
				<a href="#" class="dropdown-toggle"> 
					<i class="menu-icon fa fa-caret-right"></i> 
					<span class="menu-text">商品管理 </span> 
					<b class="arrow fa fa-angle-down"></b>
				</a> 
				<b class="arrow"></b>
				<ul class="submenu">
					<li class="" menuid="0_1_1">
						<a href="<%=basePath%>setpack/packageAdmin.html?menu=0_1_1"> 
							<i class="menu-icon fa fa-caret-right"></i> 套餐管理
						</a> 
						<b class="arrow"></b>
					</li>
					<li class=""  menuid="0_1_2">
						<a href="<%=basePath%>hotel/hotelAdmin.html?menu=0_1_2"> 
							<i class="menu-icon fa fa-caret-right"></i> 酒店管理
						</a> 
						<b class="arrow"></b>
					</li>
					<li class=""  menuid="0_1_3">
						<a href="<%=basePath%>traffic/trafficAdmin.html?menu=0_1_3"> 
							<i class="menu-icon fa fa-caret-right"></i> 交通管理
						</a> 
						<b class="arrow"></b>
					</li>
					<li class=""  menuid="0_1_4">
						<a href="<%=basePath%>ent/entAdmin.html?menu=0_1_4">
							<i class="menu-icon fa fa-caret-right"></i> 娱乐管理
						</a> 
						<b class="arrow"></b>
					</li>
					<li class=""  menuid="0_1_5">
						<a href="<%=basePath%>villa/villaAdmin.html?menu=0_1_5">
							<i class="menu-icon fa fa-caret-right"></i> 别墅管理
						</a> 
						<b class="arrow"></b>
					</li>
				</ul>
			</li>
			<li class="" menuid="0_2">
				<a href="#" class="dropdown-toggle"> 
					<i class="menu-icon fa fa-caret-right"></i> 
					<span class="menu-text">首页管理 </span> 
					<b class="arrow fa fa-angle-down"></b>
				</a> 
				<b class="arrow"></b>
				<ul class="submenu">
					<li class="" menuid="0_2_1">
						<a href="<%=basePath%>banner/bannerAdmin.html?menu=0_2_1"> 
							<i class="menu-icon fa fa-caret-right"></i> banner管理
						</a> 
						<b class="arrow"></b>
					</li>
					<li class="" menuid="0_2_2">
						<a href="<%=basePath%>theme/themeAdmin.html?menu=0_2_2"> 
							<i class="menu-icon fa fa-caret-right"></i> 主题管理
						</a> 
						<b class="arrow"></b>
					</li>
					<li class="" menuid="0_2_3">
						<a href="<%=basePath%>media/mediaAdmin.html?menu=0_2_3">
							<i class="menu-icon fa fa-caret-right"></i> 视频管理
						</a> 
						<b class="arrow"></b>
					</li>
					<li class="" menuid="0_2_4">
						<a href="<%=basePath%>destination/destinationAdmin.html?menu=0_2_4">
							<i class="menu-icon fa fa-caret-right"></i> 目的地管理
						</a> 
						<b class="arrow"></b>
					</li>
				</ul>
			</li>
			<li class="" menuid="0_3">
				<a href="#" class="dropdown-toggle"> 
					<i class="menu-icon fa fa-caret-right"></i> 
					<span class="menu-text">订单管理 </span> 
					<b class="arrow fa fa-angle-down"></b>
				</a> 
				<b class="arrow"></b>
				<ul class="submenu">
					<li class="" menuid="0_3_1">
						<a href="<%=basePath%>order/orderAdmin.html?menu=0_3_1"> 
							<i class="menu-icon fa fa-caret-right"></i> 订单列表
						</a> 
						<b class="arrow"></b>
					</li>
					<li class="">
						<a href="<%=basePath%>order/orderAdmin.html?menu=0_3_1&status=1"> 
							<i class="menu-icon fa fa-caret-right"></i> 待预付
						</a> 
						<b class="arrow"></b>
					</li>
					<li class="">
						<a href="<%=basePath%>order/orderAdmin.html?menu=0_3_1&status=2"> 
							<i class="menu-icon fa fa-caret-right"></i> 已预付（确认中）
						</a> 
						<b class="arrow"></b>
					</li>
					<li class="">
						<a href="<%=basePath%>order/orderAdmin.html?menu=0_3_1&status=3">
							<i class="menu-icon fa fa-caret-right"></i> 待付尾款
						</a> 
						<b class="arrow"></b>
					</li>
					<li class="">
						<a href="<%=basePath%>order/orderAdmin.html?menu=0_3_1&status=4">
							<i class="menu-icon fa fa-caret-right"></i> 待出行
						</a> 
						<b class="arrow"></b>
					</li>
					<li class="">
						<a href="<%=basePath%>order/orderAdmin.html?menu=0_3_1&status=5">
							<i class="menu-icon fa fa-caret-right"></i> 已出行
						</a> 
						<b class="arrow"></b>
					</li>
				</ul>
			</li>
			<li class="" menuid="0_4">
				<a href="#" class="dropdown-toggle"> 
					<i class="menu-icon fa fa-caret-right"></i> 
					<span class="menu-text">客户管理 </span> 
					<b class="arrow fa fa-angle-down"></b>
				</a> 
				<b class="arrow"></b>
				<ul class="submenu">
					<li class="" menuid="0_4_1">
						<a href="<%=basePath%>customer/customerAdmin.html?menu=0_4_1"> 
							<i class="menu-icon fa fa-caret-right"></i> 客户列表
						</a> 
						<b class="arrow"></b>
					</li>
					
				</ul>
			</li>
			<li class="" menuid="0_5">
				<a href="#" class="dropdown-toggle"> 
					<i class="menu-icon fa fa-caret-right"></i> 
					<span class="menu-text">供应商管理 </span> 
					<b class="arrow fa fa-angle-down"></b>
				</a> 
				<b class="arrow"></b>
				<ul class="submenu">
					<li class="" menuid="0_5_1">
						<a href="<%=basePath%>supplier/supplierAdmin.html?menu=0_5_1"> 
							<i class="menu-icon fa fa-caret-right"></i> 供应商列表
						</a> 
						<b class="arrow"></b>
					</li>
				</ul>
			</li>
			<li class="" menuid="0_6">
				<a href="#" class="dropdown-toggle"> 
					<i class="menu-icon fa fa-caret-right"></i> 
					<span class="menu-text">评论管理 </span> 
					<b class="arrow fa fa-angle-down"></b>
				</a> 
				<b class="arrow"></b>
				<ul class="submenu">
					<li class="" menuid="0_6_1">
						<a href="<%=basePath%>evaluation/evaluationAdmin.html?menu=0_6_1"> 
							<i class="menu-icon fa fa-caret-right"></i> 客户评论
						</a> 
						<b class="arrow"></b>
					</li>
				</ul>
			</li>
			<li class="" menuid="0_7">
				<a href="#" class="dropdown-toggle"> 
					<i class="menu-icon fa fa-caret-right"></i> 
					<span class="menu-text">系统管理 </span> 
					<b class="arrow fa fa-angle-down"></b>
				</a> 
				<b class="arrow"></b>
				<ul class="submenu">
					<li class="" menuid="0_7_1">
						<a href="<%=basePath%>user/userAdmin.html?menu=0_7_1"> 
							<i class="menu-icon fa fa-caret-right"></i> 后台用户列表
						</a> 
						<b class="arrow"></b>
					</li>
					<li class="" menuid="0_7_2">
						<a href="<%=basePath%>role/roleAdmin.html?menu=0_7_2"> 
							<i class="menu-icon fa fa-caret-right"></i> 角色列表
						</a> 
						<b class="arrow"></b>
					</li>
					<li class="" menuid="0_7_3">
						<a href="<%=basePath%>city/cityAdmin.html?menu=0_7_3"> 
							<i class="menu-icon fa fa-caret-right"></i> 城市管理
						</a> 
						<b class="arrow"></b>
					</li> --%>
				</ul>
			</li>
        </ul>
        <!-- /.nav-list -->
    </div>
    <sitemesh:write property='body'/>
    <a href="#" id="btn-scroll-up"
       class="btn-scroll-up btn btn-sm btn-inverse"> <i
            class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div>
<!-- /.main-container -->


<script type="text/javascript">


	
    jQuery(function ($) {
    	
    	
    	//禁止后退键 作用于Firefox、Opera
    	document.onkeypress=banBackSpace;
    	//禁止后退键  作用于IE、Chrome
    	document.onkeydown=banBackSpace;
    	
        var oTable1 = $('#sample-table-2')
            //.wrap("<div class='dataTables_borderWrap' />")   //if you are applying horizontal scrolling (sScrollX)
                .dataTable({
                    bAutoWidth: false,
                    "aoColumns": [
                        {"bSortable": false},
                        null, null, null, null, null,
                        {"bSortable": false}
                    ],
                    "aaSorting": [],

                    //,
                    //"sScrollY": "200px",
                    //"bPaginate": false,

                    //"sScrollX": "100%",
                    //"sScrollXInner": "120%",
                    //"bScrollCollapse": true,
                    //Note: if you are applying horizontal scrolling (sScrollX) on a ".table-bordered"
                    //you may want to wrap the table inside a "div.dataTables_borderWrap" element

                    //"iDisplayLength": 50
                });
        /**
         var tableTools = new $.fn.dataTable.TableTools( oTable1, {
					"sSwfPath": "../../copy_csv_xls_pdf.swf",
			        "buttons": [
			            "copy",
			            "csv",
			            "xls",
						"pdf",
			            "print"
			        ]
			    } );
         $( tableTools.fnContainer() ).insertBefore('#sample-table-2');
         */


        $(document).on('click', 'th input:checkbox', function () {
            var that = this;
            $(this).closest('table').find('tr > td:first-child input:checkbox')
                    .each(function () {
                        this.checked = that.checked;
                        $(this).closest('tr').toggleClass('selected');
                    });
        });


        $('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});
        function tooltip_placement(context, source) {
            var $source = $(source);
            var $parent = $source.closest('table');
            var off1 = $parent.offset();
            var w1 = $parent.width();

            var off2 = $source.offset();
            //var w2 = $source.width();

            if (parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2)) return 'right';
            return 'left';
        }

        
        $('.chosen-select').chosen({allow_single_deselect: true});
        //resize the chosen on window resize

        $(window)
                .off('resize.chosen')
                .on('resize.chosen', function () {
                    $('.chosen-select').each(function () {
                        var $this = $(this);
                        $this.next().css({'width': $this.parent().width()});
                    });
                }).trigger('resize.chosen');


        //fuelux-wizard
        var $validation = false;
        $('#fuelux-wizard')
                .ace_wizard({
                    //step: 2 //optional argument. wizard will jump to step "2" at first
                })
                .on('change', function (e, info) {
                    if (info.step == 1 && $validation) {
                        if (!$('#validation-form').valid()) return false;
                    }
                })
                .on('finished', function (e) {
                    bootbox.dialog({
                        message: "Thank you! Your information was successfully saved!",
                        buttons: {
                            "success": {
                                "label": "OK",
                                "className": "btn-sm btn-primary"
                            }
                        }
                    });
                }).on('stepclick', function (e) {
                    //e.preventDefault();//this will prevent clicking and selecting steps
                });


        //jump to a step
        $('#step-jump').on('click', function () {
            var wizard = $('#fuelux-wizard').data('wizard');
            wizard.currentStep = 3;
            wizard.setState();
        });
        //determine selected step
        //wizard.selectedItem().step

        //datepicker plugin
        //link
        $('.date-picker').datepicker({
        	format: 'yyyy-mm-dd',
            autoclose: true,
            todayHighlight: true
        }).next().on(ace.click_event, function(){
			$(this).prev().focus();
		}) ;
        
        $('#spinner1').ace_spinner({
            value: 0,
            min: 0,
            max: 100,
            step: 10,
            btn_up_class: 'btn-info',
            btn_down_class: 'btn-info'
        }).on('change', function () {
            //alert(this.value)
        });
        $('#spinner2').ace_spinner({
            value: 0,
            min: 0,
            max: 2000,
            step: 100,
            btn_up_class: 'btn-info',
            btn_down_class: 'btn-info'
        }).on('change', function () {
            //alert(this.value)
        });
        $('#spinner3').ace_spinner({
            value: 0,
            min: 0,
            max: 100,
            step: 10,
            btn_up_class: 'btn-info',
            btn_down_class: 'btn-info'
        }).on('change', function () {
            //alert(this.value)
        });
                
        $('#spinner4').ace_spinner({
            value: 0,
            min: 0,
            max: 2000,
            step: 100,
            btn_up_class: 'btn-info',
            btn_down_class: 'btn-info'
        }).on('change', function () {
            //alert(this.value)
        });
                
    

	    $('.id-input-file-2').ace_file_input({
	        no_file: '请选择文件 ...',
	        btn_choose: '选择',
	        btn_change: '修改',
	        droppable: false,
	        onchange: null,
	        thumbnail: false //| true | large
	        //whitelist:'gif|png|jpg|jpeg'
	        //blacklist:'exe|php'
	        //onchange:''
	        //
	    });
	
	    $('.id-input-file-3').ace_file_input({
			style:'well',
			btn_choose:'Drop files here or click to choose',
			btn_change:null,
			no_icon:'ace-icon fa fa-cloud-upload',
			droppable:true,
			thumbnail:'small'//large | fit
			//,icon_remove:null//set null, to hide remove/reset button
			/**,before_change:function(files, dropped) {
				//Check an example below
				//or examples/file-upload.html
				return true;
			}*/
			/**,before_remove : function() {
				return true;
			}*/
			,
			preview_error : function(filename, error_code) {
				//name of the file that failed
				//error_code values
				//1 = 'FILE_LOAD_FAILED',
				//2 = 'IMAGE_LOAD_FAILED',
				//3 = 'THUMBNAIL_FAILED'
				//alert(error_code);
			}
	
		}).on('change', function(){
			//console.log($(this).data('ace_input_files'));
			//console.log($(this).data('ace_input_method'));
		});
	    
    });
</script>

<script type="text/javascript">
    function getMenu(){
    	var menu = '${menu}';
    	var main = $("#main-container");
    	var thisli = $("li[menuid="+menu+"]");
    	$("li[menuid="+menu+"]").addClass('active');
    	var pmenuid = $(thisli).attr("pmenuid");
    	$("li[menuid="+menu+"]").parents('.hsub').addClass('active open');
    	
    	
    	/* var menu = '${menu}';
    	var menuArray = menu.split("_");
    	var m = "";
    	for(var i = 0 ; i < menuArray.length;i++){
    		
    		if(i == 0){
    			m+=menuArray[i];
    			continue;
    		}else if(i == menuArray.length-1){
    			m+= "_"+menuArray[i];
    			$("li[menuid="+m.trim()+"]").addClass('active');
    		}else{
    			m+= "_"+menuArray[i];
    			$("li[menuid="+m.trim()+"]").addClass('active open');
    		}
    	} */
    }
    
    function shCircleLoaderLoading(){
    	$('#shclDefault').show();
    	$("#mask").show();
    	$('#shclDefault').shCircleLoader();
    }
    
    function shCircleLoaderDestroy(){
		$('#shclDefault').shCircleLoader('destroy');
		$('#shclDefault').hide();
		$("#mask").hide();
    }
    
    function updatePasswordBtn(){
    	$('#updateAdminPassword').modal('show');
    }
    
    $("#updatSubmit").click(function () {
        if ($('#newAdminPassword').val() != $("#reNewAdminPassword").val()) {
            alert("两次密码输入不一致");
            return;
        }
        $.ajax({    
            type:'post',        
            url:'<%=basePath%>user/updatePassword.html',    
            data:{lid:'${user_loginInfoVO.loginInfo.lid }',password: $('#newAdminPassword').val(),lstatus: '${user_loginInfoVO.loginInfo.lstatus }'},    
            cache:false,    
            dataType:'text',    
            success:function(data){
            	if(data=='success'){
	                alert("修改成功");
	            	$('#updateAdminPassword').modal('hide');
	               }
            }    
        });
    });
    
    //处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外
    function banBackSpace(e){   
        var ev = e || window.event;//获取event对象   
        var obj = ev.target || ev.srcElement;//获取事件源   
        var t = obj.type || obj.getAttribute('type');//获取事件源类型  
        //获取作为判断条件的事件类型
        var vReadOnly = obj.getAttribute('readonly');
        //处理null值情况
        vReadOnly = (vReadOnly == "") ? false : vReadOnly;
        //当敲Backspace键时，事件源类型为密码或单行、多行文本的，
        //并且readonly属性为true或enabled属性为false的，则退格键失效
        var flag1=(ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea") 
                    && vReadOnly=="readonly")?true:false;
        //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
        //var flag2=(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea")
                    //?true:false;        
        
        //判断
        //if(flag2){
         //   return false;
        //}
        if(flag1){   
            return false;   
        }   
    }
    
</script>

</body>
</html>
