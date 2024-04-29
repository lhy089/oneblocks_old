$(document).ready(function(){
   var formInputs = $('input[type="text"],input[type="password"]');
   formInputs.focus(function() {
       $(this).parent().children('p.login-label').addClass('formTop');
   });
   
   formInputs.focusout(function() {
      if ($.trim($(this).val()).length == 0){
      $(this).parent().children('p.login-label').removeClass('formTop');
      }
   });
   
   $("#submit").click(function() {
		doLogin() ;
   });
});

function doLogin() {
	if (window.event.keyCode != 13) {
		return false;
	}
	var memberLoginParam = {
		email: $("#email").val(),
		password: $("#password").val()
	};
	$.ajax({
		url: "/login",
	    type: "POST",
	    async: false,
	    data : JSON.stringify(memberLoginParam),
		contentType : 'application/json',
//		dataType : 'json',
	    success: function(data) 
		{ 
			if(data.resultCode == "SUCCESS") {
				location.href="/campaign/main";
			}else {
				alert("로그인에 실패했습니다. 다시 로그인해 주세요.");
				$(".login-input").val('');
				$("#email").focus();
			}
			
	    },
	    beforeSend:function(){
        	$('.wrap-loading').removeClass('display-none');
   	 	},
    	complete:function(){
        	$('.wrap-loading').addClass('display-none');
    	},
	    error: function() 
		{debugger;
	       console.log("AJAX Request 실패");
	    }
	});	
}