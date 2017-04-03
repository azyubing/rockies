$(document).ready(
		function() {
			paramVerify();
			// for payment
			var chargeData = getUrlParam("chargeData");
			if (null != chargeData) {
				var xhr = new XMLHttpRequest();
				xhr.open("POST", baseUrl + "mobile/afterCharging.do", true);
				xhr.setRequestHeader("Content-type", "application/json");
				xhr.send();
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4 && xhr.status == 200) {
						console.log(xhr.responseText);
						pingpp.createPayment(xhr.responseText, function(result,
								err) {
							if (result == "success") {
								window.location.href = baseUrl + "mobile/paySuccess.do";
								// baseUrl +
								// "mobile/myorder.do";
							} else if (result == "fail") {
								alert("支付失败!");
								console.log(err.msg);
								console.log(err.extra);
								$(".formLoading").remove();
							}
						});
					}
				}
			}
		})

// 参数验证
function paramVerify() {
	var orderNo = getOrderNo();
	if (!orderNo) {
		history.replaceState({
			foo : "bar"
		}, "", "index.html");
		modal_alert("订单参数错误", "javascript:location.reload();")
	} else {
		renderView();
	}
}

// orderno获取
function getOrderNo() {
	var orderNo = getUrlParam("orderNo");
	return orderNo;
}

var c;
// 页面渲染
function renderView() {
	var orderNo = getOrderNo();
	// 根据orderNo获取订单详细信息
	$.ajax({
		url : baseUrl + "mobile/myorder.do",
		type : "get",
		data : {
			orderNo : orderNo
		},
		success : function(data) {
			if (data.status == 'success') {
				data.body.routeJson = JSON.parse(data.body.routeJson);
				data.body.routeJson.order_data = JSON
						.parse(data.body.routeJson.order_data);
				var renderData = data.body;
				var render = template.compile(tmpl_orderPay);
				var html = render(renderData);
				$("#render_orderPay").html(html);
				eventBind();
			} else if (data.status == 'LoginFailed') {
				store.remove("mw_uinfo");
				modal_alert("用户未登录或者登陆已过期，请重新登陆!", "userLogin.html");
			} else {
				modal_alert(data.message || "对不起，不存在此订单")
			}
		}
	});
}

// 事件绑定
function eventBind() {
	$("#submitBtn")
			.click(
					function() {
						var paytype = $("[name='paytype']:checked").val();
						var orderNo = getOrderNo();
						var amount = parseFloat($("#payAmount").val());
						$("body")
								.append(
										'<div class="formLoading"><div class="spnr"><i class="am-icon-spinner am-icon-pulse"></i><p class="text">支付中</p></div></div>')
						if (paytype == "1") {
							window.location.href = "mobile/beforeWXPay.do?channel="
									+ "wx_pub"
									+ "&amount="
									+ amount
									+ "&orderNo=" + orderNo;
							// pay('wx_pub', orderNo, amount);
						} else if (paytype == "2") {
							// window.location.href = "mobile/beforeWXPay";
							pay('alipay_wap', orderNo, amount);
						} else if (paytype == "3") {
							location.href = "orderOverOffline.html";
						} else {
							modal_alert("支付方式错误")
						}
						return false;
					})
}

function pay(channel, orderNo, amount) {
	var action = "requestpay.do?channel=" + channel + "&amount=" + amount
			+ "&orderNo=" + orderNo;
	var xhr = new XMLHttpRequest();
	xhr.open("POST", baseUrl + action, true);
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log(xhr.responseText);
			pingpp.createPayment(xhr.responseText, function(result, err) {
				if (result == "success") {
					window.location.href = baseUrl + "mobile/paySuccess.do";// baseUrl
					// // +
					// "mobile/myorder.do";
				} else if (result == "fail") {
					alert("支付失败!");
					console.log(err.msg);
					console.log(err.extra);
					$(".formLoading").remove();
				}
			});
		}
	}

	// $.ajax({
	// url : baseUrl + "requestpay.do",
	// type : "post",
	// data : {
	// channel : channel,
	// orderNo : orderNo,
	// amount : amount
	// },
	// success : function(data) {
	// if (data.status == 'charge_order_no_used') {
	// modal_alert(data.message);
	// return;
	// } else if (data.status == 'success') {
	// if ("wx_pub_qr" == channel) {
	// var retobj = data.body;
	// var pic = $("#wx_pub_qr"); // wx_pub_qr
	// // $("#wxModal").modal('show');
	// pic.src = baseUrl + 'qr_code.do?code_url='
	// + retobj.wx_pub_qr;
	// id = retobj.id;
	// c = self.setInterval('checkPaySts(id, orderNo)', 2000);
	// $.ajax({
	// type : "POST",
	// url : baseUrl + "/checkpay" + ".do",
	// data : "id=" + payid + "&time=" + new Date().getTime(),
	// success : function(rtmsg) {
	// if (rtmsg == 'true') {
	// window.clearInterval(c);
	// window.location.href = baseUrl
	// + "paystatus.do?id=" + orderNo;
	// }
	// }
	// });
	// } else if ("alipay_pc_direct" == channel) {
	// pingppPc.createPayment(data.body.charge, function(result,
	// err) {
	// $(".formLoading").remove();
	// modal_alert('系统繁忙。请稍后再试！');
	// });
	// }
	//
	// var pic = document.getElementById("wx_qr");
	// pic.src = baseUrl + '/qr_code.do?code_url=' + retobj.wx_pub_qr;
	// // 'weixin://wxpay/bizpayurl?pr\u003dAEZyaAr';
	// } else if (data.status == 'charge_order_no_used') {
	// $(".formLoading").remove();
	// modal_alert("您当前的行程单支付状态确认中，请不要重复支付！");
	// } else {
	// $(".formLoading").remove();
	// modal_alert(data.message)
	// }
	// }
	// });
}

function checkPaySts(payid, orderNo) {
	$.ajax({
		url : baseUrl + "/checkpay.do",
		type : "post",
		data : {
			id : payid,
			time : new Date().getTime()
		},
		success : function(data) {
			if (data.status == 'success') {
				if (data.body.payStatus == 'true') {
					window.clearInterval(c);
					window.location.href = baseUrl + "paystatus.do?id="
							+ orderNo;
				}
			}
		}
	});
}