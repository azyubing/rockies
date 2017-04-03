$(document).ready(function() {
	$('#form-verify-msg').validator({
	    onValid: function(validity) {
	      	formOnValid(validity,this);
	    },
	    onInValid: function(validity) {
	      	formOnInValid(validity,this);
	    },
	    submit: function() {
		    var formValidity = this.isFormValid();
		    var form = $(this.$element.context);
		    var formData = formFieldPackage(form.find("[name]"));
		    var formAction = form.attr("action");
		    if(formValidity){
		    	$.ajax({
			     	url : baseUrl+"mobile/sendRequestMail.do",
			     	type : "post",
			     	data : formData,
			     	success: function(data) {
			    	 	if(data.status == 'success'){
			    	 		history.replaceState({foo:"bar"}, "", "customOver.html");
			    	 		location.reload();
			    	 	}
			    	 	else{
			    	 		modal_alert(data.message);
			    	 	}
			     	}
				});
		    }
		    return false;
	  	}
	});
})
