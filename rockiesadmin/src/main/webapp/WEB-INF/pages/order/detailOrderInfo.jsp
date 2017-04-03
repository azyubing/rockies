<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

.trbgcolor{
background-color: red;
}

</style>


</head>
<body>

	<div class="main-content" style="margin-top: 60px;">
		<div class="breadcrumbs breadcrumbs-fixed" id="breadcrumbs">
			<ul class="breadcrumb">
				<li>
					<span style="color: #669FC7;">订单管理</span>
				</li>
				<li>
					<span style="color: #669FC7;">订单列表</span>
				</li>
				<li>
					<span style="color: #669FC7;">订单详情</span>
				</li>
			</ul>
			<!-- /section:basics/content.searchbox -->
		</div>
		<div class="page-content">
			<div class="page-content-area">
				<div class="hr hr8 hr-double"></div>
				<div class="row">
					<form class="form-inline" role="form" action="<%=basePath %>order/detailOrderInfo.html" method="post" id="searchForm">
						<input type="hidden" name="menu" value="${menu }">
						<input type="hidden" name="status" value="${status }">
						<div class="col-lg-10">
							<%-- <div class="form-group col-lg-3">
								<label>商户名：</label> <select id="store" name="store">
									<c:forEach var="item" items="${storeList }">
										<option value="${item.store }">${item.name }</option>
									</c:forEach>
								</select>
							</div> --%>
							<div class="form-group col-lg-4">
								<c:if test="${data!=null }">&nbsp;&nbsp;&nbsp;
								<button id="backBtn" onclick="history.go(-1);" type="button" class="btn btn-success">
									<i class="fa"></i> 返回
								</button></c:if>
								
							</div>
						</div>
					</form>
				</div>
				<br> <label>订单：${routeOrder.orderNo}</label>
				<div class="hr hr8 hr-double"></div>

				<div class="row">
					<div class="col-xs-12">
						<div class="widget-box">
							<div class="widget-body">
								<div class="widget-main">
									<div class="row">
										<div class="col-xs-6">
											<div class="widget-header ">
												<h4 class="widget-title">订单基本信息</h4>
											</div>
											<div class="hidden-sm hidden-xs action-buttons">
												<label for="form-field-8">下单时间：
													<span id="orderCreatedAt"><fmt:formatDate value="${routeOrder.ctdt}" pattern="yyyy-MM-dd"/>  </span>
												</label>
											</div>
											<div>
												<label>订单状态：
													<c:if test="${routeOrder.status=='1' }">待预付</c:if>
													<c:if test="${routeOrder.status=='2' }">已预付（确认中）</c:if>
													<c:if test="${routeOrder.status=='3' }">待付尾款</c:if>
													<c:if test="${routeOrder.status=='4' }">待出行</c:if>
													<c:if test="${routeOrder.status=='5' }">已出行</c:if>
													<c:if test="${routeOrder.status=='6' }">出行中</c:if>
												</label>
											</div>
											<div class="hidden-sm hidden-xs action-buttons">
												<label for="form-field-8">行程日期：
													<span id="orderCreatedAt"><fmt:formatDate value="${routeOrder.startdate}" pattern="yyyy-MM-dd HH:mm"/> </span>
												</label>
											</div>
											<div class="hidden-sm hidden-xs action-buttons">
												<label for="form-field-8">订单编号：
													<span id="orderCreatedAt">${routeOrder.orderNo }  </span>
												</label>
											</div>
											<div class="hidden-sm hidden-xs action-buttons">
												<label for="form-field-8">订单名称：
													<span id="orderCreatedAt">${routeOrder.orderNm } </span>
												</label>
											</div>
											<div class="hidden-sm hidden-xs action-buttons">
												<label for="form-field-8">订单原始金额：
													<span id="orderCreatedAt">${routeOrder.oldAmount}</span> 
												</label>
											</div>
											<c:if test="${routeOrder.status=='1' }">
												<div class="hidden-sm hidden-xs action-buttons">
													<label for="form-field-8">订单金额：
														<input id="amount" type="text" value="${routeOrder.amount}"/> 
														<input id="orderCreatedAt" type="button" onclick="updateRouteAmount()" value="确定"/> 
													</label>
												</div>
											</c:if>
											<c:if test="${routeOrder.status!='1' }">
												<div class="hidden-sm hidden-xs action-buttons">
													<label for="form-field-8">订单金额：
													<span id="orderCreatedAt">${routeOrder.amount}</span> 
													</label>
												</div>
											</c:if>
											<div class="hidden-sm hidden-xs action-buttons">
												<label for="form-field-8">订单优惠金额：
													<span id="orderCreatedAt">
													<fmt:formatNumber type="number"  groupingUsed="false" value="${routeOrder.oldAmount-routeOrder.amount}" />
													</span> 
												</label>
											</div>
										</div>
										<div class="col-xs-6">
											<div class="widget-header ">
												<h4 class="widget-title">客户信息</h4>
											</div>
											<div class="hidden-sm hidden-xs action-buttons">
												<label>客户姓名：${user.username }</label>
											</div>
											<div>
												<label>真实姓名：${user.realName }</label>
											</div>
											<div>
												<label>出生日期：<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/></label>
											</div>
											<div>
												<label>客户邮件：${user.email }</label>
											</div>
											<div>
												<label>客户电话：${user.tel }</label>
											</div>
											<div>
												<label>身份证：${user.idCard }</label>
											</div>
											<div>
												<label>护照号：${user.passportNumber }</label>
											</div>
											<div>
												<label>护照签发地：${country.cityName }-${province.cityName }-${city.cityName }</label>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xs-12">
						<div class="widget-box">
							<div class="widget-header">
								<h4 class="widget-title">订单列表</h4>
							</div>
							<div class="widget-body">
								<div class="widget-main">
									<div class="row">
										<div class="col-xs-12">
											<div class="table-responsive">
												<table
													class="table table-bordered table-hover table-striped tablesorter"
													id="selectCommodityTable">
													<thead>
														<tr>
															<th>商品名<i class="fa fa-sort"></i></th>
															<th>到达日期 <i class="fa fa-sort"></i></th>
															<th>结束日期 <i class="fa fa-sort"></i></th>
															<th>单价(RMB) <i class="fa fa-sort"></i></th>
															<th>总人数 <i class="fa fa-sort"></i></th>
															<th>合计 <i class="fa fa-sort"></i></th>
															<th>供应商付款状态 <i class="fa fa-sort"></i></th>
															<th>操作 <i class="fa fa-sort"></i></th>
														</tr>
													</thead>
													<tbody>
													<c:forEach items="${rows}" var="row">
														<tr >
															<td>${row.pname }</td>
															<td><fmt:formatDate value="${row.startdate }" type="both" pattern="yyyy-MM-dd"/></td>
															<td><fmt:formatDate value="${row.enddate }" type="both" pattern="yyyy-MM-dd"/></td>
															<td>${row.unitPrice}</td>
															<td>${row.peoplecnt }</td>
															<td>${row.amount }</td>
															<td>
																<c:if test="${row.payImg==null|| empty row.payImg }">未付款</c:if>
																<c:if test="${row.payImg!=null&& !empty row.payImg }">已付款</c:if>
																<a class="green " href="#" onclick="suppliersPayMenu('${row.orderNo}','${row.runingNo}','${row.pid}','${row.payImg }')">
																	<i class="ace-icon fa fa-pencil bigger-130"></i>付款凭证
																</a>
																
																<c:if test="${row.confirmStatus=='1'}">未确认</c:if>
																<c:if test="${row.confirmStatus=='0' }">已确认</c:if>
																<a class="green " href="#" onclick="suppliersConfirmMenu('${row.orderNo}','${row.runingNo}','${row.pid}','${row.confirmImg }')">
																	<i class="ace-icon fa fa-pencil bigger-130"></i>确认凭证
																</a>
															</td>
															<td>
																<a class="blue" href="javascript:void(0)" title="Edit" onclick="getProuctOrderDetail(this,'${row.orderNo }','${row.pid}','${row.runingNo}');">
														            <i class="ace-icon fa fa-search-plus bigger-130"></i> 订单详细
														        </a>	
																<%-- <a class="blue" href="javascript:void(0)" title="Edit" onclick="getOrderCusInfo('${row.orderNo }');">
														            <i class="ace-icon fa fa-search-plus bigger-130"></i> 预订人详情
														        </a> --%>	
															</td>
														</tr>
													</c:forEach>
														<tr>
															<td></td>
															<td>小计</td>
															<td>${routeOrder.amount}</td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xs-12">
						<div class="widget-box">
							<div class="widget-header">
								<h4 class="widget-title">订单详情</h4>
							</div>
							<div class="widget-body">
								<div class="widget-main">
									<div class="row">
										<div class="col-xs-12">
											<table class="table table-bordered table-hover table-striped tablesorter"
												id="selectCommodityTable">
												<thead>
													<tr>
														<th>行程单编号<i class="fa fa-sort"></i></th>
														<th>价格项 <i class="fa fa-sort"></i></th>
														<th>产品编号<i class="fa fa-sort"></i></th>
														<th>产品类型<i class="fa fa-sort"></i></th>
														<th>预订数 <i class="fa fa-sort"></i></th>
														<th>单价 <i class="fa fa-sort"></i></th>
														<th>单价人数 <i class="fa fa-sort"></i></th>
														<th>总人数 <i class="fa fa-sort"></i></th>
														<th>总计价格 <i class="fa fa-sort"></i></th>
													</tr>
												</thead>
												<tbody id="productOrderDetail">
												</tbody>
											</table>
										</div>
										<div class="row">
											<div class="col-md-12" id="mainImgPreview">
												<div class="col-xs-12">
												</div>
											</div>
										</div>
										<br>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xs-12">
						<div class="widget-box">
							<div class="widget-header">
								<h4 class="widget-title">预订人详情</h4>
							</div>
							<div class="widget-body">
								<div class="widget-main">
									<c:if test="${adultCount>0 }">
									成人${adultCount}位
										<c:forEach items="${adultList }" var="adult">
											<div class="hidden-sm hidden-xs action-buttons">
												<label>客户姓名：${adult.customerName }</label>
												<%--  <a class="green"
													onclick="customerConfirmMenu('${adult.bid}','${adult.orderNo }','${adult.cusNo }','${adult.imgPath }','${adult.type }')" href="#"><i
													class="ace-icon fa fa-pencil bigger-130"></i>客户确认凭证</a> --%>
											</div>
											<div>
												<label>真实姓名：${adult.realName }</label>
											</div>
											<div>
												<label>出生日期：${adult.birthday }</label>
											</div>
											<div>
												<label>证件类型：
												<c:if test="${adult.cardType=='1' }">身份证</c:if>
												<c:if test="${adult.cardType=='2' }">护照</c:if>
												<c:if test="${adult.cardType=='3' }">军人证</c:if>
												<c:if test="${adult.cardType=='4' }">回乡证</c:if>
												<c:if test="${adult.cardType=='5' }">台胞证</c:if>
												<c:if test="${adult.cardType=='6' }">港澳通行证</c:if>
												<c:if test="${adult.cardType=='7' }">户口簿</c:if>
												<c:if test="${adult.cardType=='8' }">出生证明</c:if>
												<c:if test="${adult.cardType=='9' }">其他</c:if>
												</label>
											</div>											<div>
												<label>证件号码：${adult.cardNumber }</label>
											</div>
										
										</c:forEach>
									</c:if>
									<c:if test="${childCount>0 }">
									儿童${childCount}位
										<c:forEach items="${childList }" var="child">
											<div class="hidden-sm hidden-xs action-buttons">
												<label>客户姓名：${child.customerName }</label>
												 <%-- <a class="green"
													onclick="customerConfirmMenu('${child.bid}','${child.orderNo }','${child.cusNo }','${child.imgPath }','${child.type }')" " href="#"><i
													class="ace-icon fa fa-pencil bigger-130"></i>客户确认凭证</a> --%>
											</div>
											<div>
												<label>真实姓名：${child.realName }</label>
											</div>
											<div>
												<label>出生日期：${child.birthday }</label>
											</div>
											<div>
												<label>证件类型：
												<c:if test="${child.cardType=='1' }">身份证</c:if>
												<c:if test="${child.cardType=='2' }">护照</c:if>
												<c:if test="${child.cardType=='3' }">军人证</c:if>
												<c:if test="${child.cardType=='4' }">回乡证</c:if>
												<c:if test="${child.cardType=='5' }">台胞证</c:if>
												<c:if test="${child.cardType=='6' }">港澳通行证</c:if>
												<c:if test="${child.cardType=='7' }">户口簿</c:if>
												<c:if test="${child.cardType=='8' }">出生证明</c:if>
												<c:if test="${child.cardType=='9' }">其他</c:if>
												</label>
											</div>
											<div>
												<label>证件号码：${child.cardNumber }</label>
											</div>
										
										</c:forEach>
									</c:if>
									<c:if test="${babyCount>0 }">
									婴儿${babyCount}位
										<c:forEach items="${babyList }" var="adult">
											<div class="hidden-sm hidden-xs action-buttons">
												<label>客户姓名：${adult.customerName }</label> 
												<%-- <a class="green"
													onclick="customerConfirmMenu('${adult.bid}','${adult.orderNo }','${adult.cusNo }','${adult.imgPath }','${adult.type }')" href="#"><i
													class="ace-icon fa fa-pencil bigger-130"></i>客户确认凭证</a> --%>
											</div>
											<div>
												<label>真实姓名：${adult.realName }</label>
											</div>
											<div>
												<label>出生日期：${adult.birthday }</label>
											</div>
											<div>
												<label>证件类型：
												<c:if test="${adult.cardType=='1' }">身份证</c:if>
												<c:if test="${adult.cardType=='2' }">护照</c:if>
												<c:if test="${adult.cardType=='3' }">军人证</c:if>
												<c:if test="${adult.cardType=='4' }">回乡证</c:if>
												<c:if test="${adult.cardType=='5' }">台胞证</c:if>
												<c:if test="${adult.cardType=='6' }">港澳通行证</c:if>
												<c:if test="${adult.cardType=='7' }">户口簿</c:if>
												<c:if test="${adult.cardType=='8' }">出生证明</c:if>
												<c:if test="${adult.cardType=='9' }">其他</c:if>
												</label>
											</div>
											<div>
												<label>证件号码：${adult.cardNumber }</label>
											</div>
										
										</c:forEach>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /.page-content-area -->
			</div>
			<!-- /.page-content -->
		</div>
		<!-- /.main-content -->
	</div>
	<script type="text/javascript">
		$(function() { 
			
			// 图片上传
			$(".mainImgBtn").click(function(){
				var form = $(this).parents("form");
				var imgpath = $(form).find("input[name='uploadList']");
				var formId = $(form).attr("id");
				if(isNull(imgpath.val())){
					alert("请选择图片");
					return;
				}
				shCircleLoaderLoading();
				$(form).ajaxSubmit({
	                url:'<%=basePath%>img/uploadOrderFileList.html',
	                type:'POST',
	                dataType:'json',
	                success: function(json){
	                	shCircleLoaderDestroy();
	                	if(formId=='mainImgFileForm'){
		                	$(form).prev().find("input[name='confirmImg']").val(json.data);
		                	$("#imgName").show().attr("src",json.data);
		                	alert('上传成功');
	                	}else{
		                	$(form).prev().find("input[name='payImg']").val(json.data);
		                	$("#imgPayName").show().attr("src",json.data);
		                	alert('上传成功');
	                	}
	                } 
	            });
			});
			// 图片上传
			$("#mainCusImgBtn").click(function(){
				var form = $(this).parents("form");
				var imgpath = $(form).find("input[name='uploadList']");
				if(isNull(imgpath.val())){
					alert("请选择图片");
					return;
				}
				shCircleLoaderLoading();
				$(form).ajaxSubmit({
	                url:'<%=basePath%>img/uploadOrderFileList.html',
	                type:'POST',
	                dataType:'json',
	                success: function(json){
	                	shCircleLoaderDestroy();
	                	$(form).prev().find("input[name='imgPath']").val(json.data);
	                } 
	            });
			});
			
		});
			function updateRouteAmount(){
				var amount = $("#amount").val().trim();
				if(isNaN(amount)){
					alert("请输入正确金额");
				}
				var orderNo = '${routeOrder.orderNo}'
				$.ajax({    
		            type:'post',        
		            url:'<%=basePath%>order/updateRouteAmount.html',    
		            data:{"orderNo":orderNo,"amount":amount},    
		            cache:false,    
		            dataType:'text',    
		            success:function(json){
		            	if(json=='success'){
			            	window.location.href="<%=basePath%>order/detailOrderInfo.html?menu=${menu}&status=${status}&orderNo="+orderNo;
		            	}
		            }    
		        });
			}
			
			function getProuctOrderDetail(obj,orderNo,pid,runingNo){
				$(obj).parents("tbody").find("tr").removeClass("trbgcolor");
				$(obj).parents("tr").addClass("trbgcolor");
				$.ajax({    
		            type:'post',        
		            url:'<%=basePath%>order/getProductOrderDetail.html',    
		            data:{"orderNo":orderNo,"pid":pid,"runingNo":runingNo},    
		            cache:false,    
		            dataType:'json',    
		            success:function(json){
		            	var html = ''
		            	var count = 0;
		            	var data = json.rows;
		            	for(var i=0;i<data.length;i++){
		            		count = count+data[i].amount;
		            		var priceItem = data[i].priceItem;
		            		var room_type_name = data[i].room_type_name;
		            		if(room_type_name!=null && room_type_name!=''){
		            			priceItem = room_type_name+":"+data[i].priceItem;
		            		}
		            		var ptype = data[i].ptype;
		            		if(ptype=='0'){
		            			ptype='套餐';
		            		}else if(ptype=='1'){
		            			ptype='酒店';
		            		}else if(ptype=='2'){
		            			ptype='别墅';
		            		}else if(ptype=='3'){
		            			ptype='交通';
		            		}else if(ptype=='4'){
		            			ptype='娱乐';
		            		}
		            		
		            		html = html+'<tr>'
		            				+'<td>'+data[i].orderNo+'</td>'
		            				+'<td>'+priceItem+'</td>'
		            				+'<td>'+data[i].pid+'</td>'
		            				+'<td>'+ptype+'</td>'
		            				+'<td>'+data[i].ordercnt+'</td>'
		            				+'<td>'+data[i].unitPrice+'</td>'
		            				+'<td>'+data[i].peoplecnt/data[i].ordercnt+'</td>'
		            				+'<td>'+data[i].peoplecnt+'</td>'
		            				+'<td>'+data[i].amount+'</td>'
		            				+'</tr>';
		            	}
		            	html = html+'<tr><td></td><td>小计</td><td>'+count+'</td></tr>';
		            	$("#productOrderDetail").html(html);
		            }    
		        });
			}
			
			function getOrderCusInfo(orderNo){
				$.ajax({    
		            type:'post',        
		            url:'<%=basePath%>order/getOrderCusInfo.html',    
		            data:{"orderNo":orderNo},    
		            cache:false,    
		            dataType:'json',    
		            success:function(json){
		            	var html = ''
		            	var data = json.rows;
		            	for(var i=0;i<data.length;i++){
		            		html = html +'<div class="col-xs-12">'
		            		+'<div class="hidden-sm hidden-xs action-buttons">'
		            		+'<label>客户姓名：'+data[i].customerName+'</label>'
		            		+'<a class="green" onclick="customerConfirmMenu('+"'"+data[i].bid+"','"+orderNo+"','"+data[i].cusNo+"','"+data[i].imgPath+"','"+data[i].type+"'"+')" href="#"><i class="ace-icon fa fa-pencil bigger-130"></i>客户确认凭证</a>'
		            		+'</div>'
		            		+'<div><label>真实姓名：'+data[i].realName+'</label></div>'
		            		+'<div><label>出生日期：'+data[i].birthday+'</label></div>'
		            		+'<div><label>客户邮件：'+data[i].email+'</label></div>'
		            		+'<div><label>客户电话：'+data[i].tel+'</label></div>'
		            		+'<div><label>身份证：'+data[i].idCard+'</label></div>'
		            		+'<div><label>护照号：'+data[i].passportNumber+'</label></div>'
		            		+'<div><label>护照签发地：'+data[i].passportIssue+'</label></div>'
		            		+'</div>';
		            	}
		            	$("#customers").html(html);
		            }    
		        });
			}
			function getOrderCusInfo(orderNo){
				$.ajax({    
		            type:'post',        
		            url:'<%=basePath%>order/getOrderCusInfo.html',    
		            data:{"orderNo":orderNo},    
		            cache:false,    
		            dataType:'json',    
		            success:function(json){
		            	var html = ''
		            	var data = json.rows;
		            	for(var i=0;i<data.length;i++){
		            		html = html +'<div class="col-xs-12">'
		            		+'<div class="hidden-sm hidden-xs action-buttons">'
		            		+'<label>客户姓名：'+data[i].customerName+'</label>'
		            		/* +'<a class="green" onclick="customerConfirmMenu('+"'"+data[i].bid+"','"+orderNo+"','"+data[i].cusNo+"','"+data[i].imgPath+"','"+data[i].type+"'"+')" href="#"><i class="ace-icon fa fa-pencil bigger-130"></i>客户确认凭证</a>' */
		            		+'</div>'
		            		+'<div><label>真实姓名：'+data[i].realName+'</label></div>'
		            		+'<div><label>出生日期：'+data[i].birthday+'</label></div>'
		            		+'<div><label>客户邮件：'+data[i].email+'</label></div>'
		            		+'<div><label>客户电话：'+data[i].tel+'</label></div>'
		            		+'<div><label>身份证：'+data[i].idCard+'</label></div>'
		            		+'<div><label>护照号：'+data[i].passportNumber+'</label></div>'
		            		+'<div><label>护照签发地：'+data[i].passportIssue+'</label></div>'
		            		+'</div>';
		            	}
		            	$("#customers").html(html);
		            }    
		        });
			}
			
			function customerConfirmMenu(id,orderNo,cusNo,imgPath,type){
				$('.customerConfirmMenu').find("input[name='id']").val(id);
				$('.customerConfirmMenu').find("input[name='orderNo']").val(orderNo);
				$('.customerConfirmMenu').find("input[name='cusNo']").val(cusNo);
				$('.customerConfirmMenu').find("input[name='type']").val(type);
				if(imgPath==null||imgPath==''||imgPath==undefined||imgPath=='undefined'){
					$('.customerConfirmMenu').find("input[name='imgPath']").val('');
					$('.customerConfirmMenu').find("input[name='uploadList']").val('');
					$("#imgCusName").attr("src",'').hide();
				}else{
					$('.customerConfirmMenu').find("input[name='imgPath']").val(imgPath);
					$("#imgCusName").attr("src",imgPath).show();
				}
				$('.customerConfirmMenu').modal('show');
			}
			
			function suppliersConfirmMenu(orderNo,runingNo,pid,confirmImg){
				$("#suppliersConfirmMenu").find("input[name='orderNo']").val(orderNo);
				$("#suppliersConfirmMenu").find("input[name='runingNo']").val(runingNo);
				$("#suppliersConfirmMenu").find("input[name='pid']").val(pid);
				if(confirmImg==null||confirmImg==''){
					$("#suppliersConfirmMenu").find("input[name='confirmImg']").val('');
					$("#suppliersConfirmMenu").find("input[name='uploadList']").val('');
					$("#imgName").hide().attr("src",'');
				}else{
					$("#suppliersConfirmMenu").find("input[name='confirmImg']").val(confirmImg);
					$("#imgName").show().attr("src",confirmImg);
					
				}
				$('.suppliersConfirmMenu').modal('show');
			}
			//供应商付款凭证
			function suppliersPayMenu(orderNo,runingNo,pid,payImg){
				$("#suppliersPayMenu").find("input[name='orderNo']").val(orderNo);
				$("#suppliersPayMenu").find("input[name='runingNo']").val(runingNo);
				$("#suppliersPayMenu").find("input[name='pid']").val(pid);
				if(payImg==null||payImg==''){
					$("#suppliersPayMenu").find("input[name='payImg']").val('');
					$("#suppliersPayMenu").find("input[name='uploadList']").val('');
					$("#imgPayName").hide().attr("src",'');
				}else{
					$("#suppliersPayMenu").find("input[name='payImg']").val(payImg);
					$("#imgPayName").show().attr("src",payImg);
					
				}
				$('.suppliersPayMenu').modal('show');
			}
			
			function submitForm(){
				var confirmImg = $("#suppliersConfirmMenu").find("input[name='confirmImg']").val();
				if(confirmImg==null||confirmImg==''||confirmImg==undefined){
					alert("请先上传图片");
					return false;
				}
				$.ajax({    
		            type:'post',        
		            url:'<%=basePath%>order/updateProductOrder.html',    
		            data:$('#addMenuForm').serialize(),    
		            cache:false,    
		            dataType:'text',    
		            success:function(data){
		            	if(data=='success'){
		            		alert("添加成功");
		            		$('.suppliersConfirmMenu').modal('hide');
		            		window.location.href="<%=basePath%>order/detailOrderInfo.html?menu=${menu}&status=${status}&orderNo=${routeOrder.orderNo}";
		            	}
		            }    
		        });
			}
			//付款凭证
			function submitPayForm(){
				var payImg = $("#suppliersPayMenu").find("input[name='payImg']").val();
				if(payImg==null||payImg==''||payImg==undefined){
					alert("请先上传图片");
					return false;
				}
				$.ajax({    
		            type:'post',        
		            url:'<%=basePath%>order/updateProductOrder.html',    
		            data:$('#addPayMenuForm').serialize(),    
		            cache:false,    
		            dataType:'text',    
		            success:function(data){
		            	if(data=='success'){
		            		alert("添加成功");
		            		$('.suppliersPayMenu').modal('hide');
		            		window.location.href="<%=basePath%>order/detailOrderInfo.html?menu=${menu}&status=${status}&orderNo=${routeOrder.orderNo}";
		            	}
		            }    
		        });
			}
			
			function submitCusForm(){
				var imgPath = $("#addCusMenuForm").find("input[name='imgPath']").val();
				if(imgPath==null||imgPath==''||imgPath==undefined){
					alert("请先上传图片");
					return false;
				}
				var orderNo = $('#addCusMenuForm').find("input[name='orderNo']").val();
				$.ajax({    
		            type:'post',        
		            url:'<%=basePath%>order/updateCusOrder.html',    
		            data:$('#addCusMenuForm').serialize(),    
		            cache:false,    
		            dataType:'text',    
		            success:function(data){
		            	if(data=='success'){
		            		alert("添加成功");
		            		$('.customerConfirmMenu').modal('hide');
		            		getOrderCusInfo(orderNo);
		            	}
		            }    
		        });
			}
	
	</script>
<div class="row" >	
	<!-- add -->
	<div class="modal fade suppliersConfirmMenu" id="suppliersConfirmMenu" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">供应商确认凭证</h4>
				</div>
				<div class="modal-body">
					<form role="form" action="" method="post" id="addMenuForm">
						<input name="orderNo" type="hidden"/>
						<input name="runingNo" type="hidden"/>
						<input name="pid" type="hidden"/>
						<input name="confirmImg" type="hidden"/>
						<input name="confirmStatus" type="hidden" value="0"/>
					</form>
					<form action="" id="mainImgFileForm" method="post" enctype="multipart/form-data" >	
						<div class="row">
							<div class="col-md-12">
								<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;供应商确认凭证： </label>
								<div class="col-md-12 mainImg" >
									<div class="col-md-4">
										<input type="file" class="id-input-file-2" name="uploadList" style="display: block; border: 1px solid #333;"/>
										<img name="name" id="imgName" style="width: 100px;"/>
									</div>
									<div class="col-md-8">
										<button  type="button" class="btn btn-sm btn-success mainImgBtn" ><i class="fa fa-upload" ></i> 上传 </button>
										<div ></div>
									</div>
								</div>
							</div>
						</div>
						<br>
					</form>
					<div class="row">
						<div class="col-md-12" id="mainImgPreview">
							
						</div>
					</div>
					<br>
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
	<div class="modal fade suppliersPayMenu" id="suppliersPayMenu" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">供应商付款凭证</h4>
				</div>
				<div class="modal-body">
					<form role="form" action="" method="post" id="addPayMenuForm">
						<input name="orderNo" type="hidden"/>
						<input name="runingNo" type="hidden"/>
						<input name="pid" type="hidden"/>
						<input name="payImg" type="hidden"/>
					</form>
					<form action="" id="mainImgFilePayForm" method="post" enctype="multipart/form-data" >	
						<div class="row">
							<div class="col-md-12">
								<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;供应商付款凭证： </label>
								<div class="col-md-12 mainImg" >
									<div class="col-md-4">
										<input type="file" class="id-input-file-2" name="uploadList" style="display: block; border: 1px solid #333;"/>
										<img name="name" id="imgPayName" style="width: 100px;"/>
									</div>
									<div class="col-md-8">
										<button  type="button" class="btn btn-sm btn-success mainImgBtn" ><i class="fa fa-upload" ></i> 上传 </button>
										<div ></div>
									</div>
								</div>
							</div>
						</div>
						<br>
					</form>
					<div class="row">
						<div class="col-md-12" id="mainImgPreview">
							
						</div>
					</div>
					<br>
				</div>
				<div class="modal-footer">
					<button class="btn btn-info" type="button" id="addBtn" onclick="submitPayForm();">
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
	<div class="modal fade customerConfirmMenu"  tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">客户确认凭证</h4>
				</div>
				<div class="modal-body">
					<form role="form" action="" method="post" id="addCusMenuForm">
						<input name="id" type="hidden"/>
						<input name="orderNo" type="hidden"/>
						<input name="type" type="hidden"/>
						<input name="cusNo" type="hidden"/>
						<input name="imgPath" type="hidden"/>
					</form>
					<form action="" id="attachedImgFileForm" method="post" enctype="multipart/form-data" >	
						<div class="row">
							<div class="col-md-12 attachedImg">
								<label class="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;客户确认凭证<span style="color:red">(需要上传宽高比列为2:1的图片，用于详细页面滚动显示)</span>： </label>
								<!-- <div class="col-md-12" style="margin-left: 13px;">
									<input class="id-input-file-2" type="file"  name="uploadList"  />
									<button id="mainImgBtn" type="button" class="btn btn-sm btn-success" ><i class="fa fa-upload" ></i> 上传 </button>
									<div id="attachedImgMsg"></div>
								</div> -->
								<div class="col-md-12 mainImg" >
									<div class="col-md-4">
										<input type="file" class="id-input-file-2" name="uploadList" style="display: block; border: 1px solid #333;"/>
										<img id="imgCusName" style="width: 50px;height: 50px;"/>
									</div>
									<div class="col-md-8">
										<button id="mainCusImgBtn" type="button" class="btn btn-sm btn-success" ><i class="fa fa-upload" ></i> 上传 </button>
										<div ></div>
									</div>
								</div>
							</div>
						</div>
						<br>
					</form>
					<div class="row">
						<div class="col-md-12" id="attachedImgPreview">
						</div>
					</div>
					<br>
				</div>
				<div class="modal-footer">
					<button class="btn btn-info" type="button" id="addBtn" onclick="submitCusForm();">
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
