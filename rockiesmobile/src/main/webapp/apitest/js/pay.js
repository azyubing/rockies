var c;
function pay(channel, orderNo, amount) {
	window.clearInterval(c);
	$.ajax({
		url : baseUrl + "/requestpay" + ".do",
		type : "POST",
		dataType : "json",
		cache : false,
		data : {
			channel : channel,
			amount : amount,
			orderNo : orderNo
		},
		success : function(data) {
			if (data.status == 'charge_order_no_used') {
				alert(data.message);
				return;
			} else if (data.status == 'success') {
				if ("wx_pub_qr" == channel) {
					var retobj = JSON.parse(data.body.charge);
					var pic = document.getElementById("wx_qr"); // wx_pub_qr
					// $("#wxModal").modal('show');
					pic.src = baseUrl + '/qr_code.do?code_url='
							+ retobj.wx_pub_qr;
					id = retobj.id
					c = self.setInterval('checkPaySts(id, orderNo)', 2000);// 每1秒执行一次checkPaySts方法
				} else if ("alipay_pc_direct" == channel) {
					pingppPc.createPayment(data.body.charge, function(result,
							err) {
						alert('系统繁忙。请稍后再试！');
					});
				}
			} else if (data.status == 'charge_order_no_used') {
				alert(data.information);
			} else {
				$("#result").html(data.message)
				console.log(data.message);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$("#result").html("Error: <br/>" + textStatus)
		}
	});
}

function checkPaySts(payid, orderNo) {
	$.ajax({
		type : "POST",
		url : baseUrl + "/checkpay" + ".do",
		data : {
			id : payid,
			time : new Date().getTime()
		},
		success : function(data) {
			if (data.status == 'success') {
				if (data.body.payStatus == 'true') {
					window.clearInterval(c);
					window.location.href = baseUrl + "/paystatus.do?id="
							+ orderNo;
				}
			}
		}
	});
}

function afterPay(orderNo) {
	$.ajax({
		type : "POST",
		url : baseUrl + "/paystatus" + ".do",
		data : {
			id : orderNo
		},
		success : function(data) {
			if (data.status == "paid") {
				alert(data.information);
			} else if (data.status == "success") {
				alert(data.information);
			} else if (data.status == "failed") {
				alert(data.information);
			}
		}
	});
}