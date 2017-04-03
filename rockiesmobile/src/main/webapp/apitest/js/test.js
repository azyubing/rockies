var baseUrl = "..";
// var baseUrl = "http://localhost:8080/imyway";

function testSearchKeyWords() {
	$.ajax({
		url : baseUrl + "/mobile/products" + ".do",
		type : "GET",
		dataType : "json",
		cache : false,
		data : {
			searchKeyWords : $("#keywords").val(),
			page : 1,
			pageSize : 5
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data.body))
				console.log(data.body)
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


function testLogin() {
	$.ajax({
		url : baseUrl + "/mobile/login" + ".do",
		type : "POST",
		dataType : "json",
		cache : false,
		data : {
			loginName : "junking1983",
			password : "123456"
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data.body))
				console.log(data.body)
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

function testLoginStatus() {
	$.ajax({
		url : baseUrl + "/mobile/loginStatus" + ".do",
		type : "GET",
		dataType : "json",
		cache : false,
		data : {
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data.body))
				console.log(data.body)
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

function testLoginout() {
	$.ajax({
		url : baseUrl + "/mobile/logout" + ".do",
		type : "POST",
		dataType : "json",
		cache : false,
		data : {
			loginName : "junking1983"
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testUpdateUserName() {
	$.ajax({
		url : baseUrl + "/mobile/username" + ".do",
		type : "POST",
		dataType : "json",
		cache : false,
		data : {
			loginName : "junking1984"
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data.body))
				console.log(data.body)
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

function testSendTelVerifyCode() {
	$.ajax({
		url : baseUrl + "/mobile/sendTelVerifyCode" + ".do",
		type : "POST",
		dataType : "json",
		cache : false,
		data : {
			tel : "13604249841"
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testUpdateCellPhoneNumber() {
	$.ajax({
		url : baseUrl + "/mobile/cellPhoneNumber" + ".do",
		type : "POST",
		dataType : "json",
		cache : false,
		data : {
			cellPhoneNumber : "13604249841",
			validateCode : $("#validateCode").val()
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testUpdatePassword() {
	$.ajax({
		url : baseUrl + "/mobile/password" + ".do",
		type : "POST",
		dataType : "json",
		cache : false,
		data : {
			password : "123"
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testResetPassword() {
	$.ajax({
		url : baseUrl + "/mobile/resetPassword" + ".do",
		type : "POST",
		dataType : "json",
		cache : false,
		data : {
			password : "234",
			telNum : "13604249841",
			validateCode : $("#validateCode").val()
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testRegister() {
	$.ajax({
		url : baseUrl + "/mobile/user" + ".do",
		type : "POST",
		dataType : "json",
		cache : false,
		data : {
			username : "test1234",
			password : "234",
			telNum : "13604249841",
			validateCode : $("#validateCode").val()
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testMyFavoritesSearchOptions() {
	$.ajax({
		url : baseUrl + "/mobile/myfavoritesSearchOptions" + ".do",
		type : "GET",
		dataType : "json",
		cache : false,
		data : {

		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testMyFavorites() {
	$.ajax({
		url : baseUrl + "/mobile/myfavorites" + ".do",
		type : "GET",
		dataType : "json",
		cache : false,
		data : {
			page : 1,
			pageSize : 5
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testAddMyFavorite() {
	$.ajax({
		url : baseUrl + "/mobile/myfavorites" + ".do",
		type : "POST",
		dataType : "json",
		cache : false,
		data : {
			pid : "P0000000000000000041"
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testDelMyFavorite() {
	$.ajax({
		url : baseUrl + "/mobile/delmyfavorites" + ".do",
		type : "POST",
		dataType : "json",
		cache : false,
		data : {
			pid : "P0000000000000000041"
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testMyOrder() {
	$.ajax({
		url : baseUrl + "/mobile/myorders" + ".do",
		type : "POST",
		dataType : "json",
		cache : false,
		data : {
			dates : "2016-08-12, 2016-08-13, 2016-08-14",
			pid : "P0000000000000000041",
			pname : "莱媚别墅",
			adults : 3,
			children : 2,
			amount : 45600.00,
			userName : "wangqiang",
			telNum : "13604249841",
			email : "supersonic888@tom.com"
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testGetHotDesinations() {
	$.ajax({
		url : baseUrl + "/mobile/hotDestinations" + ".do",
		type : "GET",
		dataType : "json",
		cache : false,
		data : {
			page : 1,
			pageSize : 5
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testGetDesinations() {
	$.ajax({
		url : baseUrl + "/mobile/destinations" + ".do",
		type : "GET",
		dataType : "json",
		cache : false,
		data : {
			page : 1,
			pageSize : 5
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testGetOrdersSearchOptions() {
	$.ajax({
		url : baseUrl + "/mobile/orderSearchOptions" + ".do",
		type : "GET",
		dataType : "json",
		cache : false,
		data : {

		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testGetMyOrders() {
	$.ajax({
		url : baseUrl + "/mobile/myorders" + ".do",
		type : "GET",
		dataType : "json",
		cache : false,
		data : {
			page : 1,
			pageSize : 5
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testDelMyOrder() {
	$.ajax({
		url : baseUrl + "/mobile/delmyorder" + ".do",
		type : "POST",
		dataType : "json",
		cache : false,
		data : {
			orderNo : "PO0000000000000000003"
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testGetHotelPriceCalendar() {
	$.ajax({
		url : baseUrl + "/mobile/hotelPriceCalendar" + ".do",
		type : "GET",
		dataType : "json",
		cache : false,
		data : {
			pid : "P0000000000000000583",
			date : "2016-07-01"
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testGetHotelRoomTypes() {
	$.ajax({
		url : baseUrl + "/mobile/hotelRoomOptions" + ".do",
		type : "GET",
		dataType : "json",
		cache : false,
		data : {
			pid : "P0000000000000000574"
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testGetHotelRoomTypesPrice() {
	$.ajax({
		url : baseUrl + "/mobile/hotelRoomOptionPrice" + ".do",
		type : "GET",
		dataType : "json",
		cache : false,
		data : {
			pid : "P0000000000000000574",
			roomTypeId : "RT000000000000000171",
			date : "2016-07-12"
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testGetPackagePriceCalendar() {
	$.ajax({
		url : baseUrl + "/mobile/packagePriceCalendar" + ".do",
		type : "GET",
		dataType : "json",
		cache : false,
		data : {
			pid : "P0000000000000000424",
			date : "2016-08-01"
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testGetPackageOptions() {
	$.ajax({
		url : baseUrl + "/mobile/packageOptions" + ".do",
		type : "GET",
		dataType : "json",
		cache : false,
		data : {
			pid : "P0000000000000000424",
			date : "2016-08-02"
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testGetVillaRoomTypes() {
	$.ajax({
		url : baseUrl + "/mobile/villaRoomOptions" + ".do",
		type : "GET",
		dataType : "json",
		cache : false,
		data : {
			pid : "P0000000000000000048",
			date : "2016-08-29"
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testGetVillaPriceCalendar() {
	$.ajax({
		url : baseUrl + "/mobile/villaPriceCalendar" + ".do",
		type : "GET",
		dataType : "json",
		cache : false,
		data : {
			pid : "P0000000000000000048",
			date : "2016-07-01"
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testGetEntertainmentPriceCalendar() {
	$.ajax({
		url : baseUrl + "/mobile/entertainmentPriceCalendar" + ".do",
		type : "GET",
		dataType : "json",
		cache : false,
		data : {
			pid : "P0000000000000000552",
			date : "2016-07-01"
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testGetEntertainmentOptions() {
	$.ajax({
		url : baseUrl + "/mobile/entertainmentOptions" + ".do",
		type : "GET",
		dataType : "json",
		cache : false,
		data : {
			pid : "P0000000000000000552",
			date : "2016-07-29"
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testGetCarPriceDetail() {
	$.ajax({
		url : baseUrl + "/mobile/carPriceDetail" + ".do",
		type : "GET",
		dataType : "json",
		cache : false,
		data : {
			pid : "P0000000000000000655",
			time : "09:00"
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testGetVillaPriceDetail() {
	$.ajax({
		url : baseUrl + "/mobile/villaPriceDetail" + ".do",
		type : "GET",
		dataType : "json",
		cache : false,
		data : {
			pid : "P0000000000000000051",
			date : "2016-08-15"
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testGetAllThemes() {
	$.ajax({
		url : baseUrl + "/mobile/themes" + ".do",
		type : "GET",
		dataType : "json",
		cache : false,
		data : {

		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testGetAllThemes() {
	$.ajax({
		url : baseUrl + "/mobile/themes" + ".do",
		type : "GET",
		dataType : "json",
		cache : false,
		data : {

		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testGetRecommendedThemeProducts() {
	$.ajax({
		url : baseUrl + "/mobile/recommendThemeProducts" + ".do",
		type : "GET",
		dataType : "json",
		cache : false,
		data : {

		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testGetProducts() {
	$.ajax({
		url : baseUrl + "/mobile/recommendThemeProducts" + ".do",
		type : "GET",
		dataType : "json",
		cache : false,
		data : {
			destination : "hotDesinations",
			service : "allServices",
			orderby : "topAttention",
			page : 1,
			pageSize : 5
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testGetHotServices() {
	$.ajax({
		url : baseUrl + "/mobile/hotSingleServices" + ".do",
		type : "GET",
		dataType : "json",
		cache : false,
		data : {
			page : 1,
			pageSize : 2
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testGetProductSearchOptions() {
	$.ajax({
		url : baseUrl + "/mobile/productSearchOptions" + ".do",
		type : "GET",
		dataType : "json",
		cache : false,
		data : {

		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testGetProductDetail() {
	$.ajax({
		url : baseUrl + "/mobile/productDetail" + ".do",
		type : "GET",
		dataType : "json",
		cache : false,
		data : {
			pid : "P0000000000000000041"
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testGetProductRouteDayInfo() {
	$.ajax({
		url : baseUrl + "/mobile/productRouteInfo" + ".do",
		type : "GET",
		dataType : "json",
		cache : false,
		data : {
			pid : "P0000000000000000442"
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testGetProductComments() {
	$.ajax({
		url : baseUrl + "/mobile/productComments" + ".do",
		type : "GET",
		dataType : "json",
		cache : false,
		data : {
			pid : "P0000000000000000047",
			page : 1,
			pageSize : 5
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testAddProductComments() {
	$.ajax({
		url : baseUrl + "/mobile/productComments" + ".do",
		type : "POST",
		dataType : "json",
		cache : false,
		data : {
			pid : "P0000000000000000005",
			comments : "住得很爽啊！"
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testGetTravelPartners() {
	$.ajax({
		url : baseUrl + "/mobile/TravelPartner" + ".do",
		type : "GET",
		dataType : "json",
		cache : false,
		data : {
			orderNo : "PO0000000000000000114"
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

/**
 * @param realName ：
 *            随行人真实姓名
 * @param customerName ：
 *            真实姓名拼音
 * @param cardType
 *            ：证件类型 <option value="1" <c:if test="${orderCusInfo.cardType=='1'
 *            }">selected="selected"</c:if>>身份证</option> <option value="2"
 *            <c:if test="${orderCusInfo.cardType=='2' }">selected="selected"</c:if>>护照</option>
 *            <option value="3" <c:if test="${orderCusInfo.cardType=='3'
 *            }">selected="selected"</c:if>>军人证</option> <option value="4"
 *            <c:if test="${orderCusInfo.cardType=='4' }">selected="selected"</c:if>>回乡证</option>
 *            <option value="5" <c:if test="${orderCusInfo.cardType=='5'
 *            }">selected="selected"</c:if>>台胞证</option> <option value="6"
 *            <c:if test="${orderCusInfo.cardType=='6' }">selected="selected"</c:if>>港澳通行证</option>
 *            <option value="7" <c:if test="${orderCusInfo.cardType=='7'
 *            }">selected="selected"</c:if>>户口簿</option> <option value="8"
 *            <c:if test="${orderCusInfo.cardType=='8' }">selected="selected"</c:if>>出生证明</option>
 *            <option value="9" <c:if test="${orderCusInfo.cardType=='9'
 *            }">selected="selected"</c:if>>其他</option>
 * @param birthday
 *            ：出生日期
 * @param cardNumber
 *            ：证件号码
 */
function testSaveTravelPartner() {
	$.ajax({
		url : baseUrl + "/mobile/saveTravelPartner" + ".do",
		type : "POST",
		dataType : "json",
		cache : false,
		data : {
			id : 0,// 如果为0，表示是insert，新同行人。如果是具体的id，那么就是修改
			orderNo : "PO0000000000000000114",
			realName : "wanglaoda",
			customerName : "wanglaoda",
			cardType : "1",
			birthday : "2016-02-21",
			cardNumber : "23010319490206061X"
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

/**
 * @param realName ：
 *            随行人真实姓名
 * @param customerName ：
 *            真实姓名拼音
 * @param cardType
 *            ：证件类型 <option value="1" <c:if test="${orderCusInfo.cardType=='1'
 *            }">selected="selected"</c:if>>身份证</option> <option value="2"
 *            <c:if test="${orderCusInfo.cardType=='2' }">selected="selected"</c:if>>护照</option>
 *            <option value="3" <c:if test="${orderCusInfo.cardType=='3'
 *            }">selected="selected"</c:if>>军人证</option> <option value="4"
 *            <c:if test="${orderCusInfo.cardType=='4' }">selected="selected"</c:if>>回乡证</option>
 *            <option value="5" <c:if test="${orderCusInfo.cardType=='5'
 *            }">selected="selected"</c:if>>台胞证</option> <option value="6"
 *            <c:if test="${orderCusInfo.cardType=='6' }">selected="selected"</c:if>>港澳通行证</option>
 *            <option value="7" <c:if test="${orderCusInfo.cardType=='7'
 *            }">selected="selected"</c:if>>户口簿</option> <option value="8"
 *            <c:if test="${orderCusInfo.cardType=='8' }">selected="selected"</c:if>>出生证明</option>
 *            <option value="9" <c:if test="${orderCusInfo.cardType=='9'
 *            }">selected="selected"</c:if>>其他</option>
 * @param birthday
 *            ：出生日期
 * @param cardNumber
 *            ：证件号码
 */
function testUdateTravelPartner() {
	$.ajax({
		url : baseUrl + "/mobile/saveTravelPartner" + ".do",
		type : "POST",
		dataType : "json",
		cache : false,
		data : {
			id : 63,// 如果为0，表示是insert，新同行人。如果是具体的id，那么就是修改
			orderNo : "PO0000000000000000114",
			realName : "wanglaoda5",
			customerName : "wanglaoda5",
			cardType : "1",
			birthday : "2016-02-21",
			cardNumber : "23010319490206061X"
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testDeleteTravelPartner() {
	$.ajax({
		url : baseUrl + "/mobile/deleteTravelPartner" + ".do",
		type : "POST",
		dataType : "json",
		cache : false,
		data : {
			orderNo : "PO0000000000000000114",
			id : 62
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testVillaOrder() {
	var requestBody = {
		time_start : "2016-08-30", // 入住日期
		time_end : "2016-09-01", // 离开日期，此字段只有别墅提供
		time_days : 3, // 天数，此字段只有别墅提供
		order_data : JSON.stringify([ {
			id : 1258,
			tid : "001", // ID
			tname : "成人", // 文字
			num : 1, // 数量
			extrabedNum : 1,// 加床数量，只是针对别墅
			price : 1299
		// 单价
		} ]),
		order_amount : 10000, // 合计费用
		linkman_name : "姜涛", // 联系人姓名
		linkman_phone : "18899988888", // 联系人手机
		linkman_email : "jiangtao@163.com", // 联系人邮箱
		remark : "这里是备注内容...",
		pdt_id : "pid000000000000242",
		pdt_name : "【泰国 苏梅岛】 西拉别墅",
		pdt_type : "2",
		pdt_address : "泰国",
		pdt_pic : "http://www.imyway.cn/images/14623554115151.png"
	};
	requestBody["jsonString"] = JSON.stringify(requestBody);
	$.ajax({
		url : baseUrl + "/mobile/myorder" + ".do",
		type : "POST",
		dataType : "json",
		cache : false,
		data : requestBody,
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testTrafficOrder() {
	var requestBody = {
		time_start : "2015-11-30", // 入住日期
		time_end : "2015-11-30", // 离开日期，此字段只有别墅提供
		time_days : 1, // 天数，此字段只有别墅提供
		order_data : JSON.stringify([ {
			id : 14,
			tid : "001", // ID
			tname : "海狮", // 文字
			num : 1, // 数量
			extrabedNum : 1,// 加床数量，只是针对别墅
			price : 3500
		// 单价
		} ]),
		order_amount : 3500, // 合计费用
		linkman_name : "姜涛", // 联系人姓名
		linkman_phone : "18899988888", // 联系人手机
		linkman_email : "jiangtao@163.com", // 联系人邮箱
		remark : "这里是备注内容...",
		pdt_id : "P0000000000000000072",
		pdt_name : "日本东京包车",
		pdt_type : "3",
		pdt_address : "日本东京",
		pdt_pic : "http://www.imyway.cn/images/144792280127414478313070081.jpg"
	};
	requestBody["jsonString"] = JSON.stringify(requestBody);
	$.ajax({
		url : baseUrl + "/mobile/myorder" + ".do",
		type : "POST",
		dataType : "json",
		cache : false,
		data : requestBody,
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testPackageOrder() {
	var requestBody = {
		time_start : "2015-12-02", // 入住日期
		time_end : "2015-12-07", // 离开日期，此字段只有别墅提供
		time_days : 6, // 天数，此字段只有别墅提供
		order_data : JSON.stringify([ {
			id : 7,
			tid : "001", // ID
			tname : "两大两小", // 文字
			num : 1, // 数量
			extrabedNum : 0,// 加床数量，只是针对别墅
			price : 15000
		// 单价
		} ]),
		order_amount : 15000, // 合计费用
		linkman_name : "姜涛", // 联系人姓名
		linkman_phone : "18899988888", // 联系人手机
		linkman_email : "jiangtao@163.com", // 联系人邮箱
		remark : "这里是备注内容...",
		pdt_id : "P0000000000000000055",
		pdt_name : "日本东京亲子6天4晚",
		pdt_type : "0",
		pdt_address : "日本東京",
		pdt_pic : "http://www.imyway.cn/images/1451541388698211.jpg"
	};
	requestBody["jsonString"] = JSON.stringify(requestBody);
	$.ajax({
		url : baseUrl + "/mobile/myorder" + ".do",
		type : "POST",
		dataType : "json",
		cache : false,
		data : requestBody,
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testHotelOrder() {
	// var requestBody = {
	// time_start : "2016-08-25", // 入住日期
	// time_end : "2016-08-25", // 离开日期，此字段只有别墅提供
	// time_days : 1, // 天数，此字段只有别墅提供
	// order_data : JSON.stringify([ {
	// id : 105,
	// tid : "001", // ID
	// tname : "成人", // 文字
	// num : 1, // 数量
	// extrabedNum : 1,// 加床数量，只是针对别墅
	// price : 3960
	// // 单价
	// } ]),
	// order_amount : 3960, // 合计费用
	// linkman_name : "姜涛", // 联系人姓名
	// linkman_phone : "18899988888", // 联系人手机
	// linkman_email : "jiangtao@163.com", // 联系人邮箱
	// remark : "这里是备注内容...",
	// pdt_id : "P0000000000000000603",
	// pdt_name : "【亲子】沙巴 婆罗洲潜水马布度假村 ",
	// pdt_type : "1",
	// pdt_address : "泰国",
	// pdt_pic : "http://www.imyway.cn/images/145587332742901.jpg"
	// };
	var requestBody = {
		time_start : "2016-9-17",
		time_end : "2016-9-17",
		time_days : 1,
		pdt_id : "P0000000000000000574",
		pdt_name : "【蜜月】马尔代夫 卓美亚德瓦纳芙希岛 4晚",
		pdt_type : "1",
		pdt_address : "马尔代夫 7星",
		pdt_pic : "http://www.imyway.cn/images/146303146444117.jpg",
		order_amount : 58350,
		order_data : JSON.stringify([ {
			id : "80",
			tname : "海滩活力别墅",
			num : 1,
			price : 19450
		}, {
			id : "81",
			tname : "2晚Beach Revive沙屋 + 2晚Ocean Revive水屋",
			num : 2,
			price : 19450
		} ]),
		linkman_name : "姜涛",
		linkman_phone : "18698899999",
		linkman_email : "13800313@qq.com",
		remark : "酒店测试 09-05 1次"
	}

	requestBody["jsonString"] = JSON.stringify(requestBody);
	$.ajax({
		url : baseUrl + "/mobile/myorder" + ".do",
		type : "POST",
		dataType : "json",
		cache : false,
		data : requestBody,
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testEntertainmentOrder() {
	var requestBody = {
		time_start : "2015-11-28", // 入住日期
		time_end : "2015-11-28", // 离开日期，此字段只有别墅提供
		time_days : 1, // 天数，此字段只有别墅提供
		order_data : JSON.stringify([ {
			id : 3,
			tid : "001", // ID
			tname : "/人", // 文字
			num : 1, // 数量
			extrabedNum : 1,// 加床数量，只是针对别墅
			price : 999
		// 单价
		} ]),
		order_amount : 999, // 合计费用
		linkman_name : "姜涛", // 联系人姓名
		linkman_phone : "18899988888", // 联系人手机
		linkman_email : "jiangtao@163.com", // 联系人邮箱
		remark : "这里是备注内容...",
		pdt_id : "P0000000000000000056",
		pdt_name : "巴厘岛蓝梦岛一日游",
		pdt_type : "4",
		pdt_address : "印度尼西亚巴厘岛",
		pdt_pic : "http://www.imyway.cn/images/14470632533621390_D_img_1436469355393.jpg"
	};
	requestBody["jsonString"] = JSON.stringify(requestBody);
	$.ajax({
		url : baseUrl + "/mobile/myorder" + ".do",
		type : "POST",
		dataType : "json",
		cache : false,
		data : requestBody,
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function testGetOrder() {
	$.ajax({
		url : baseUrl + "/mobile/myorder" + ".do",
		type : "GET",
		dataType : "json",
		cache : false,
		data : {
			orderNo : "PO0000000000000000164"
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

/**
 * status = 5已完成
 */
function testCancelOrder() {
	$.ajax({
		url : baseUrl + "/mobile/cancelOrder" + ".do",
		type : "POST",
		dataType : "json",
		cache : false,
		data : {
			orderNo : "PO0000000000000000115"
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data.body)
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

function setImage(obj) {
	var path = URL.createObjectURL(obj.files[0]);
	$("img").attr("src", path);
}

function fileloadon() {
	$("#_fileForm").submit(function() {
		$(this).ajaxSubmit({
			type : 'POST',
			url : baseUrl + "/img/uploadFile.do",
			dataType : 'json',
			success : function(data) {
				if (data.status == 'success') {
					$("#result").html(JSON.stringify(data))
					var pics = data.body;
					$("#picpath").val(pics[0]);
					console.log(data.body)
				} else {
					$("#result").html(data.message)
					console.log(data.message);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$("#result").html("Error: <br/>" + textStatus)
			}
		});
		return false;
	});
	$("#_fileForm").submit();
}

function uploadThemeIcon() {
	$.ajax({
		url : baseUrl + "/mobile/themeIcon" + ".do",
		type : "POST",
		dataType : "json",
		cache : false,
		data : {
			id : $("#pid").val(),
			icon : $("#picpath").val()
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data)
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

function uploadCityPic() {
	$.ajax({
		url : baseUrl + "/mobile/cityPic" + ".do",
		type : "POST",
		dataType : "json",
		cache : false,
		data : {
			id : $("#pid").val(),
			icon : $("#picpath").val()
		},
		success : function(data) {
			if (data.status == 'success') {
				$("#result").html(JSON.stringify(data))
				console.log(data)
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

var c;
function pay(channel, orderNo, amount) {
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
					$.ajax({
						type : "POST",
						url : baseUrl + "/checkpay" + ".do",
						data : "id=" + payid + "&time=" + new Date().getTime(),
						success : function(rtmsg) {
							if (rtmsg == 'true') {
								// window.clearInterval(c);
								window.location.href = baseUrl
										+ "/paystatus.do?id=" + orderNo;
							}
						}
					});
				} else if ("alipay_pc_direct" == channel) {
					pingppPc.createPayment(data.body.charge, function(result,
							err) {
						alert('系统繁忙。请稍后再试！');
					});
				}

				var pic = document.getElementById("wx_qr");
				pic.src = baseUrl + '/qr_code.do?code_url=' + retobj.wx_pub_qr;
				// 'weixin://wxpay/bizpayurl?pr\u003dAEZyaAr';
				$("#result").html(JSON.stringify(data))
				console.log(data)
			} else if (data.status == 'charge_order_no_used') {
				alert("您当前的行程单支付状态确认中，请不要重复支付！");
			} else {
				$("#result").html(data.message)
				console.log(data.message);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$("#result").html("Error: <br/>" + textStatus)
		}
	});

	// window.clearInterval(c);
	// if (!isNaN(amount) && amount !="") {
	// var pay_url = baseUrl + "/requestpay" + ".do";
	// var xhr = new XMLHttpRequest();
	// xhr.open("POST", pay_url, true);
	// xhr.setRequestHeader("Content-type", "application/json");
	// xhr.send({
	// channel: channel,
	// amount: amount,
	// orderNo: orderNo
	// });
	//
	// xhr.onreadystatechange = function () {
	//        	
	// if (xhr.readyState == 4 && xhr.status == 200) {
	// // console.log(xhr.responseText);
	// if("wx_pub_qr" == channel){
	// if(xhr.responseText == "charge_order_no_used"){
	// alert("您当前的行程单支付状态确认中，请不要重复支付！");
	// return;
	// }
	// var retobj = JSON.parse(xhr.responseText);
	// var pic = document.getElementById("wx_qr"); // wx_pub_qr
	// $("#wxModal").modal('show');
	// // alert(retobj.id);
	// pic.src = baseUrl + '/qr_code.do?code_url=' + retobj.wx_pub_qr;
	// if(xhr.responseText != null && xhr.responseText != ""){
	// id = retobj.id
	// c = self.setInterval('checkPaySts(id, orderNo)',2000);//
	// 每1秒执行一次checkPaySts方法
	// }
	// }else if("alipay_pc_direct" == channel){
	// pingppPc.createPayment(xhr.responseText, function(result, err) {
	// alert('系统繁忙。请稍后再试！');
	// });
	// }
	// }
	// }
	// }
}

function weixinPayPic() {
	var pic = document.getElementById("wx_qr");
	pic.src = baseUrl + '/qr_code.do?code_url='
			+ 'weixin://wxpay/bizpayurl?pr\u003dAEZyaAr';
}

function checkPaySts(payid, orderNo) {

}

function webchatLogin() {

}

function weixinPayPic() {
	var pic = document.getElementById("wx_qr");
	pic.src = baseUrl + '/qr_code.do?code_url='
			+ 'weixin://wxpay/bizpayurl?pr\u003dAEZyaAr';
}

function webchatPay() {
	pay('wx_pub_qr', 'PO0000000000000000076', 5998);
}

function aliPay() {
	pay('alipay_pc_direct', 'PO0000000000000000076', 5998);
}