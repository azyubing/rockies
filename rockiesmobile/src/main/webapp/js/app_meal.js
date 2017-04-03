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
	    self.calendarData.weekFirst == 0 ? self.calendarData.weekFirst = 7 : "";
	    self.calendarData.dateStart = null;
	    self.calendarData.data = null; //当前月份json数据
	    self.orderinfo = store.get("mw_orderinfo");
	    if(self.orderinfo){
	    	var dateStart = self.orderinfo.time_start.split("-");
	    	self.calendarData.weekFirst = new Date(dateStart[0]+'-'+dateStart[1]+'-1').getDay();
	    	self.calendarData.weekFirst == 0 ? self.calendarData.weekFirst = 7 : "";
	    	self.calendarData.dateStart = [dateStart[0],dateStart[1],dateStart[2]];
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
        self.ajaxLock = false;
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
	        self.calendarData.weekFirst = new Date(self.datePickerData.year+'-'+self.datePickerData.month+'-1').getDay();
	        self.calendarData.weekFirst == 0 ? self.calendarData.weekFirst = 7 : "";
	        self.setDateShow();
	        self.begin();
	    })
	    $("#monthNext").click(function(){ 
			if(self.ajaxLock){
				return false;
			}
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
	        	var year = self.datePickerData.year;
	        	var month = self.datePickerData.month;
	        	var day = parseInt($this.text()); 
	        	var data = self.calendarData.data; 
				self.resetData();
    			self.calendarData.dateStart = [year,month,day];
        		self.setDatePickerHtml();
        		self.render_propItems();
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
	    		orderInfo.time_end = orderInfo.time_start;  //临时解决无法获取结束时间的BUG
	    		orderInfo.time_days = 1;  //临时解决无法获取结束时间的BUG
	    		orderInfo.pdt_id = pinfo.pid;
				orderInfo.pdt_name = pinfo.pname;
				orderInfo.pdt_type = pinfo.ptype;
				orderInfo.pdt_desc = pinfo.desc;
				orderInfo.pdt_address = pinfo.map_address;
				orderInfo.pdt_pic = pinfo.size_img2;
				orderInfo.order_amount = $("#feild_feeTotal").val();
				orderInfo.order_data = [];
	    		$(".counterItem").each(function(){
	    			var $this = $(this);
	    			if($this.find(".numField").val() > 0){
	    				var obj = new Object();
	    				var info = $this.data("info");
		    			obj.id = info.id;
		    			obj.tname = info.priceItem;
		    			obj.num = parseInt($this.find(".numField").val());
		    			obj.price = info.price;
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
	//总费用计算
	self.feeTotal = function(fee){
		var feeTotal = 0;
		if(self.calendarData.dateStart){
			$(".counterItem").each(function(){
				var num = $(this).find(".numField").val();
				var price = $(this).find(".feeType").val();
				feeTotal += price*num; 	
			})
		}
		$("#feeTotal").text(feeTotal);
		$("#feild_feeTotal").val(feeTotal);
	}
	self.loadDataHandle = function(data){ 
		self.calendarData.data = data;
		self.setDatePickerHtml();
	}
	//重置日历数据
	self.resetData = function(){
		if(self.orderinfo){
			self.orderinfo.order_amount = 0;
			self.orderinfo.order_data = [];
			store.set("mw_orderinfo",self.orderinfo);
		}
		self.calendarData.dateStart = null;
		$("#feild_dateStart").val("");
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
	}
	self.getHtmlLogic = function(data,classIndex,day){
		var dateHtml = "";
		var dateStart = self.calendarData.dateStart;
		var year = self.datePickerData.year;
		var month = self.datePickerData.month;
		var yearCur = self.calendarData.yearCur;
		var monthCur = self.calendarData.monthCur;
		var dayCur = self.calendarData.dayCur;
		if(data[classIndex].status == 0 || (yearCur == year && monthCur == month && dayCur > day)){
        	dateHtml += '<li no="'+day+'" class="invalid">';
       }
        else if(data[classIndex].status == 1){  
        	if(dateStart){
				if(dateStart[0] == year && dateStart[1] == month && dateStart[2] == day){ 
    				dateHtml += '<li no="'+day+'" class="valid booked start">';
				}
				else{
					dateHtml += '<li no="'+day+'" class="valid">';
				}
        	}
        	else{
        		dateHtml += '<li no="'+day+'" class="valid">';
        	}
        }
        dateHtml += '<p class="day">'+(day < 10 ? "0"+day : day)+'</p></li>';
        return dateHtml;
	}
	self.render_propItems = function(){
		var date = self.calendarData.dateStart;
		if(date){
			$.ajax({
				url : baseUrl+"mobile/packageOptions.do",
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
						var render = template.compile(tmpl_counter);
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
	        url : baseUrl+"mobile/packagePriceCalendar.do",
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