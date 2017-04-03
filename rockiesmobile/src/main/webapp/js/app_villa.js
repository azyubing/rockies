function orderDatePicker(){
	self = this;
	self.config = function(){
		if(self.setVerify()){
			self.setData();
			self.setView();
			self.setBind();
			self.begin();
		}
	}
	self.setVerify = function(){
	    var pinfo = store.get("mw_pinfo");
	    var orderinfo = store.get("mw_orderinfo");
	    if(!pinfo || (orderinfo && pinfo.pid != orderinfo.pdt_id)){
	    	history.replaceState({foo:"bar"}, "", "index.html");
	    	modal_alert("商品参数错误","javascript:location.reload();")  
	    	return false;
	    }
	    return true;
	}
	self.setView = function(){
		self.setDateShow();
		self.render_propItems();
	}
	self.setData = function(){
		//本地应用数据
        var todayDate = new Date();
        self.calendarData = new Object(); 
	    self.calendarData.yearCur = todayDate.getFullYear();; //当天_年
	    self.calendarData.monthCur = todayDate.getMonth()+1; //当天_月
	    self.calendarData.dayCur = todayDate.getDate(); //当天_日
	    self.calendarData.weekFirst = new Date(self.calendarData.yearCur+'-'+self.calendarData.monthCur+'-1').getDay(); //本月1号的星期
	    self.calendarData.weekFirst == 0 ? self.calendarData.weekFirst = 7 : "";self.calendarData.dateStart = null;
	    self.calendarData.dateStart = null;
	    self.calendarData.dateEnd = null;
	    self.calendarData.dateStartElm = null;
	    self.calendarData.dateEndtElm = null;
	    self.calendarData.days = 0; //选择天数
	    self.calendarData.data = null; //当前月份json数据
	    self.calendarData.daysData = new Array(); //选中区间的日期数据
	    self.calendarData.daysGap = new Array(); //开选中区间是否存在无效日期
	    self.orderinfo = store.get("mw_orderinfo");
	    if(self.orderinfo){
	    	var dateStart = self.orderinfo.time_start.split("-");
	    	var dateEnd = self.orderinfo.time_end.split("-");
	    	self.calendarData.weekFirst = new Date(dateStart[0]+'-'+dateStart[1]+'-1').getDay();
	    	self.calendarData.weekFirst == 0 ? self.calendarData.weekFirst = 7 : "";
	    	self.calendarData.dateStart = [dateStart[0],dateStart[1],dateStart[2]];
	    	self.calendarData.dateEnd = [dateEnd[0],dateEnd[1],dateEnd[2]];
	    	self.calendarData.days = self.orderinfo.time_days;
	    }
	    //ajax日历提交数据
	    self.datePickerData = new Object(); 
	    if(self.orderinfo){
	    	self.datePickerData.year = self.calendarData.dateStart[0];
			self.datePickerData.month = self.calendarData.dateStart[1];
	    }
	    else{
	    	self.datePickerData.year = self.calendarData.yearCur;
			self.datePickerData.month = self.calendarData.monthCur;
	    }
    	//ajax配置项
        self.ajaxLock = true;
	}
	self.setBind = function(){
		//月份切换
		$("#monthPrev").click(function(){
			if(self.ajaxLock){
				return false;
			}
	        if(self.datePickerData.year <= self.calendarData.yearCur && self.datePickerData.month <= self.calendarData.monthCur){
	            return false;
	        }
	        if(self.datePickerData.month > 1){
	            self.datePickerData.month--;
	        }
	        else{
	            self.datePickerData.month = 12;
	            self.datePickerData.year--;
	        }  
	        self.setDaysGap("prev");
	        self.setDaysData("prev");
	        self.calendarData.weekFirst = new Date(self.datePickerData.year+'-'+self.datePickerData.month+'-1').getDay();
	        self.calendarData.weekFirst == 0 ? self.calendarData.weekFirst = 7 : "";
	        self.setDateShow();
	        self.begin();
	   })
	    $("#monthNext").click(function(){  
			if(self.ajaxLock){
				return false;
			}
	        self.setDaysGap("next");
	        self.setDaysData("next");
	        if(self.datePickerData.month < 12){
	            self.datePickerData.month++;
	        }
	        else{
	            self.datePickerData.month = 1;
	            self.datePickerData.year++;
	        }  
	        self.calendarData.weekFirst = new Date(self.datePickerData.year+'-'+self.datePickerData.month+'-1').getDay();
	        self.calendarData.weekFirst == 0 ? self.calendarData.weekFirst = 7 : "";
	        self.setDateShow();
	        self.begin();
	    })
	    //选择日期
        $("#monthDate").delegate("li","click",function(){
	    	var $this = $(this);	
	        if(self.ajaxLock || !$this.hasClass("valid")){
	            return false;
	        }
	        else{
	        	var dateStart = self.calendarData.dateStart;
	        	var dateEnd = self.calendarData.dateEnd;
	        	var year = self.datePickerData.year;
	        	var month = self.datePickerData.month;
	        	var day = parseInt($this.text());  
	        	var data = self.calendarData.data;
	        	if(dateStart){
	        		if(dateEnd){
	        			self.resetData();
	        			self.calendarData.dateStart = [year,month,day];
	        			self.calendarData.dateStartElm = $this;	  
	        		}
	        		else{
	        			//结束日期与开始日期为同一天
        				if(dateStart[0] == year && dateStart[1] == month && dateStart[2] == day){
			        		return false;
				        } 
				        //结束日期早于开始日期
				        if(year < dateStart[0] || (year == dateStart[0] && month < dateStart[1]) || (year == dateStart[0] && month == dateStart[1] && day < dateStart[2])){ 
				        	modal_alert("退房日期不能早于入住日期")
			        		return false;
				        }
				        //是否包含不可预约日期
				        if(self.calendarData.daysGap.length > 0){
				        	modal_alert("所选区间包含不可预约的日期，请重新选择退房日期")
	        				return false;
				        }
				        else{
				        	var sdate = 0;
				        	month == dateStart[1] ? sdate = self.calendarData.dateStart[2] : "";
				        	for(var i = sdate; i < day-1; i ++){
	        					if(!self.calendarData.data[i].status){
	        						modal_alert("所选区间包含不可预约的日期，请重新选择退房日期")
			        				return false;
	        					}
	        				}  
				        }
				        self.calendarData.dateEnd = [year,month,day];
        				self.calendarData.dateEndElm = $this;  
        				//保存选择的日期区间数据
        				self.setDaysData();
	        		}
        			self.setDatePickerHtml();
	        	}
	        	else{
	        		self.resetData();
	    			self.calendarData.dateStart = [year,month,day];
	        		self.calendarData.dateStartElm = $this;	  
	        		self.setDatePickerHtml();
	        		self.render_propItems();
	        	}
        		self.feeTotal();
	        }
	    })
        //计数器
	    $("#render_counter").delegate(".numAdd","click",function(){
	    	if(self.ajaxLock){
				return false;
			}
	    	self.counterAdd($(this).parents(".counterItem"));
	    })
	    $("#render_counter").delegate(".numReduce","click",function(){
	    	if(self.ajaxLock){
				return false;
			}
	    	self.counterReduce($(this).parents(".counterItem"));
	    }) 
	    //表单提交
	    $("#submitBtn").click(function(){
	    	if(self.ajaxLock){
				return false;
			}
	    	var form = document.getElementById("orderForm");
	    	if(checkForm(form)){
	    		var pinfo = store.get("mw_pinfo");
	    		var orderInfo = store.get("mw_orderinfo") || new Object();
	    		orderInfo.time_start = $("#feild_dateStart").val();
	    		orderInfo.time_end = $("#feild_dateEnd").val();
	    		orderInfo.time_days = $("#feild_daysNum").val();
	    		orderInfo.pdt_id = pinfo.pid;
				orderInfo.pdt_name = pinfo.pname;
				orderInfo.pdt_type = pinfo.ptype;
				orderInfo.pdt_desc = pinfo.desc;
				orderInfo.pdt_address = pinfo.map_address;
				orderInfo.pdt_pic = pinfo.size_img2;
				orderInfo.order_amount = $("#feild_feeTotal").val();
				orderInfo.order_amountServe = $("#feild_feeServe").val();
				orderInfo.order_amountTax = $("#feild_feeTax").val();
				orderInfo.order_amountBasic = $("#feild_feeBasic").val();
				orderInfo.order_data = [];
	    		$(".counterItem").each(function(){
	    			var $this = $(this);
	    			if($this.find(".numField").val() > 0){
	    				var obj = new Object();
	    				var info = $this.data("info");
		    			obj.id = info.id;
		    			obj.tname = info.roomCnt+"卧室，可住"+info.peopleCnt+"人";
		    			obj.num = parseInt($this.find(".numField").val());
		    			obj.extrabedNum = info.extraCnt;
		    			obj.price = info.roomPrice;
		    			orderInfo.order_data.push(obj);
	    			}
	    		})
	    		store.set("mw_orderinfo",orderInfo);
	    		location.href = form.action;
	    	}
	    	return false;
	    })
	}
	self.begin = function(){
        self.getAjaxParam();
        self.render_calendar();
    }
	self.getAjaxParam = function (){  	
		$(".propItemsField").each(function(){
			var $this = $(this);
			self.datePickerData[$this.attr("name")] = $this.val();
		})
	}
    //商品数量增加
	self.counterAdd = function(dom){
	    var fieldElm = dom.find(".numField");
	    var num = parseInt(fieldElm.val());
	    var max = dom.data("info").maxCnt;
		if(max && max <= num){
			modal_alert("最多可预定"+max+"栋")
			return;
		}
	    fieldElm.val(++num);
	    self.feeTotal();
	}	
	//商品数量减少
	self.counterReduce = function(dom){
	    var fieldElm = dom.find(".numField");
	    var num = parseInt(fieldElm.val());
	    if(num > fieldElm.data("min")){
	    	fieldElm.val(--num);
	    	self.feeTotal();
	    }
	}
	//天数计算
	self.daysCount = function(){
		var num = 0;
		var dateStart = self.calendarData.dateStart;
		var dateEnd = self.calendarData.dateEnd;
		if(dateStart && dateEnd){
			var d1 = new Date(dateStart[0],dateStart[1]-1,dateStart[2]);
		 	var d2 = new Date(dateEnd[0],dateEnd[1]-1,dateEnd[2]);
		 	num = (d2.getTime()-d1.getTime())/(1000*3600*24);
		}
		return num;
	}
	//总费用计算
	self.feeTotal = function(){
		var pinfo = store.get("mw_pinfo");
		var rate_serve = pinfo.service_fee_rate;
		var rate_tax = pinfo.tax_rate;
		var feeBasic = 0;
		var feeServe = 0;
		var feeTax = 0;
		var feeTotal = 0;
		var days = self.calendarData.days;
		if(self.calendarData.dateStart && self.calendarData.dateEnd && days > 0){
			$(".counterItem").each(function(){
    			var $this = $(this);
    			var num = $this.find(".numField").val();
    			if(num > 0){
    				var price = $this.find(".feeType").val();
    				var priceServe = Math.round(price*num*rate_serve);
    				var priceTax = Math.round(price*num*rate_tax);
    				var priceBasic = price-priceServe-priceTax;
					feeTotal += price*num*days;
					feeServe += priceServe*num*days;
					feeTax += priceTax*num*days;
    				feeBasic += priceBasic*num*days;
    			}
    		})
		}
		$("#feeBasic").text(feeBasic);
		$("#feeServe").text(feeServe);
		$("#feeTax").text(feeTax);
		$("#feeTotal").text(feeTotal);
		$("#feild_feeBasic").val(feeBasic);
		$("#feild_feeServe").val(feeServe);
		$("#feild_feeTax").val(feeTax);
		$("#feild_feeTotal").val(feeTotal);
	}
	self.loadDataHandle = function(data){ 
		self.calendarData.data = data;
		self.setDatePickerHtml();
	}
	//保存待选择日期数据
	self.setDaysData = function(flag){
		var dateStart = self.calendarData.dateStart;
		var dateEnd = self.calendarData.dateEnd;
		var year = self.datePickerData.year;
		var month = self.datePickerData.month;
		var daysData = self.calendarData.daysData;
		var data = self.calendarData.data;
		if(!dateStart || $("#daysNum").text() > 0){
			return;
		}
		if(flag == "prev"){
			for(var i = 0; i < daysData.length; i ++){
				if(year == daysData[i][0] && month == daysData[i][1]){
					daysData.splice(i,1);
					break;
				}
			}
		}
		else if(flag == "next"){
			if(month == dateStart[1]){
				daysData.push([year,month,data.slice(dateStart[2]-1)]);
			}
			else{
				daysData.push([year,month,data]);
			}
		}
		else{
			if(dateEnd[1] == dateStart[1]){
				daysData.push([year,month,data.slice(dateStart[2]-1,dateEnd[2]-1)]);
			}
			else{
				daysData.push([year,month,data.slice(0,dateEnd[2]-1)]);
			}
		}
		self.calendarData.daysData = daysData;
		$("#feild_daysData").val(JSON.stringify(daysData));
	}
	//检测当前月份是否存在不可选的日期
	self.setDaysGap = function(flag){
		var dateStart = self.calendarData.dateStart;
		var dateEnd = self.calendarData.dateEnd;
		var year = self.datePickerData.year;
		var month = self.datePickerData.month;
		var daysGap = self.calendarData.daysGap;
		if(!dateStart || dateEnd){
			return;
		}
		if(flag == "prev"){
			for(var i = 0; i < daysGap.length; i ++){
				if(year == daysGap[i][0] && month == daysGap[i][1]){
					daysGap.splice(i,1);
					break;
				}
			}
		}
		if(flag == "next"){
			if(month == dateStart[1]){
				if($("#monthDate>li.start").nextAll(".invalid").length > 0){
					daysGap.push([year,month]);
				}
			}
			else{
				if($("#monthDate>li.invalid").length > 0){
					daysGap.push([year,month]);
				}
			}
		}
		self.calendarData.daysGap = daysGap;
	}
	//重置日历数据
	self.resetData = function(){
		if(self.orderinfo){
			self.orderinfo.order_amount = 0;
			self.orderinfo.order_amountBasic = 0;
			self.orderinfo.order_amountServe = 0;
			self.orderinfo.order_amountTax = 0;
			self.orderinfo.order_data = [];
			store.set("mw_orderinfo",self.orderinfo);
		}
		self.calendarData.dateStart = null;
		self.calendarData.dateStartElm = null;
		self.calendarData.dateEnd = null;
		self.calendarData.dateEndElm = null;
		self.calendarData.days = 0;
		self.calendarData.daysData = [];
		$("#feild_dateStart").val("");
		$("#feild_dateEnd").val("");
		$("#feild_daysNum").val(0);
	}
	//获得月份的天数
    self.getDays = function(){
        var daysArray = [31,28,31,30,31,30,31,31,30,31,30,31];
        if(self.datePickerData.month == 2 && self.isLeapYear()){
            return 29;  
        }
        else{ 
            return daysArray[self.datePickerData.month-1];
        }
    }
    //判断是否为闰年
    self.isLeapYear = function(){
    	var year = self.datePickerData.year;
        if((year%4 == 0 && year%100 != 0) || year%400 == 0){
            return true;
        }
        else{
            return false;
        }
    }
    //年月显示
    self.setDateShow = function(){
        $("#year").text(self.datePickerData.year);
        $("#month").text(self.datePickerData.month);
    }
	//生成日历当前月份HTML
	self.setDatePickerHtml = function(){
		var days = self.getDays(); 
		var data = self.calendarData.data;
	    var dateHtml = "";
	    var classIndex = 0;
	    var num = 1;
		for(var i = 1; i <= 42; i ++){
	        if(i <= 7){
	            if(i < self.calendarData.weekFirst){
	                dateHtml += '<li class="null"></li>';
	            }
	            else{
	                dateHtml += self.getHtmlLogic(data,classIndex,num);
	                classIndex++;  
	                num++;
	            }
	        }
	        else{
	            if(num > days){
	                dateHtml += '<li class="null"></li>';
	            }
	            else{
	                dateHtml += self.getHtmlLogic(data,classIndex,num);
	                classIndex++; 
	            } 
	            num++;
	        }
	    }
	    $("#monthDate").html(dateHtml);	
	    if(self.calendarData.dateStart){
	    	var dateStart = self.calendarData.dateStart;
	    	$("#feild_dateStart").val(dateStart[0]+"-"+dateStart[1]+"-"+dateStart[2]);
	    }
	    if(self.calendarData.dateEnd){
	    	var dateEnd = self.calendarData.dateEnd;
	    	$("#feild_dateEnd").val(dateEnd[0]+"-"+dateEnd[1]+"-"+dateEnd[2]);
	    }
	    if(self.calendarData.dateStart && self.calendarData.dateEnd){
	    	var daysNum = self.daysCount();
	    	self.calendarData.days = daysNum;
	    	$("#daysNum").text(daysNum);
	    	$("#feild_daysNum").val(daysNum);
	    }
	}
	self.getHtmlLogic = function(data,classIndex,day){
		var dateHtml = "";
		var dateStart = self.calendarData.dateStart;
		var dateEnd = self.calendarData.dateEnd;
		var year = self.datePickerData.year;
		var month = self.datePickerData.month;
		if(data[classIndex].status == 0){
            dateHtml += '<li no="'+day+'" class="invalid"><p class="day">'+(day < 10 ? "0"+day : day)+'</p><p class="fee"></p></li>';
        }
        else if(data[classIndex].status == 1){  
        	if(dateStart){
        		if(dateEnd){
        			if(dateStart[0] == dateEnd[0] && dateStart[1] == dateEnd[1]){
        				if(dateStart[0] == year && dateStart[1] == month){ 
        					if(day == dateStart[2]){
		    					dateHtml += '<li no="'+day+'" class="valid booked start">';
		        			}
	        				else if(day == dateEnd[2]){
	        					dateHtml += '<li no="'+day+'" class="valid booked end">';
	        				}
	        				else if(day > dateStart[2] && day < dateEnd[2]){
	        					dateHtml += '<li no="'+day+'" class="valid booked">';
	        				}
		    				else{
		    					dateHtml += '<li no="'+day+'" class="valid">';
		    				}
        				}
        				else{
	    					dateHtml += '<li no="'+day+'" class="valid">';
        				}
        			}
        			else{
        				if(dateStart[0] == year && dateStart[1] == month){ 
	        				if(day > dateStart[2]){
		    					dateHtml += '<li no="'+day+'" class="valid booked">';
		        			}
	        				else if(day == dateStart[2]){
	        					dateHtml += '<li no="'+day+'" class="valid booked start">';
	        				}
		    				else{
		    					dateHtml += '<li no="'+day+'" class="valid">';
		    				}
        				}
        				else if(dateEnd[0] == year && dateEnd[1] == month){
        					if(day < dateEnd[2]){
		    					dateHtml += '<li no="'+day+'" class="valid booked">';
		        			}
        					else if(day == dateEnd[2]){
		    					dateHtml += '<li no="'+day+'" class="valid booked end">';
		        			}
		    				else{
		    					dateHtml += '<li no="'+day+'" class="valid">';
		    				}
        				}
        				else if(year > dateStart[0] && year < dateEnd[0]){
        					dateHtml += '<li no="'+day+'" class="valid booked">';
        				}
        				else if(dateStart[0] != dateEnd[0] && year == dateStart[0] && month > dateStart[1]){
        					dateHtml += '<li no="'+day+'" class="valid booked">';
        				}
        				else if(dateStart[0] != dateEnd[0] && year == dateEnd[0] && month < dateEnd[1]){
        					dateHtml += '<li no="'+day+'" class="valid booked">';
        				}
        				else if(dateStart[0] == dateEnd[0] && month > dateStart[1] && month < dateEnd[1]){
        					dateHtml += '<li no="'+day+'" class="valid booked">';
        				}
        				else{
	    					dateHtml += '<li no="'+day+'" class="valid">';
        				}
        			}
        		}
        		else{
        			if(dateStart[0] == year && dateStart[1] == month && dateStart[2] == day){
        				dateHtml += '<li no="'+day+'" class="valid booked start">';
        			}
        			else{
        				dateHtml += '<li no="'+day+'" class="valid">';
        			}
        		}
        	}
        	else{
        		dateHtml += '<li no="'+day+'" class="valid">';
        	}
        	dateHtml += '<p class="day">'+(day < 10 ? "0"+day : day)+'</p><p class="fee">¥'+data[classIndex].feeTotal+'</p></li>';
        }
        return dateHtml;
	}
	self.render_propItems = function(){
		var date = self.calendarData.dateStart;
		if(date){
			$.ajax({
				url : baseUrl+"mobile/villaRoomOptions.do",
				type : "get",
				data : {
					pid : store.get("mw_pinfo").pid,
					date : date
				},
		        beforeSend:function() {
		        	self.ajaxLock = true;
		        },
				success : function(data) {
		        	self.ajaxLock = false;
					if(data.status == 'success'){
						var renderData = data.body;
						$.each(renderData, function(key,value) {
							value.jsonstr = JSON.stringify(value)
						})
						if(self.orderinfo){
							$.each(self.orderinfo.order_data, function(key,value) {
								var typeItem = value;
								$.each(renderData, function(key,value) {
									if(typeItem.id == value.id){
										value.num = typeItem.num;
									}
								});
							});
						}
						var render = template.compile(tmpl_counterVilla);
						var html = render(renderData);
		 				$('#render_counter').html(html);
		 				self.feeTotal();
					} 
					else{
						modal_alert(data.message);
					}
				}
			});
		}
	}
	self.render_calendar = function(){
	    $.ajax({ //获取当前月的日程数据
	        url : baseUrl+"mobile/villaPriceCalendar.do",
	        type : "get",
	        data : {
				pid : store.get("mw_pinfo").pid,
				date : self.datePickerData.year+"-"+self.datePickerData.month+"-01"
			},
	        beforeSend:function() {
	        	self.ajaxLock = true;
	            $("#monthDate").html('<div id="monthLoading" class="monthLoading f-otvc"><i class="am-icon-spinner am-icon-pulse"></i>数据请求中</div>');
	        },
	        success:function(data) {   
	        	self.ajaxLock = false;
	         	if(data.status == 'success'){
					self.loadDataHandle(data.body);
				} 
				else{
					modal_alert(data.message);
				}
	        }
	    })
	}
}

$(function(){
	var odp = {};
	orderDatePicker.call(odp);
	odp.config();
})