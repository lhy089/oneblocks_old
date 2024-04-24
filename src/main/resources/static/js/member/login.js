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
   
//   $("#submit").click(function() {
//		doLogin() ;
//   });
});

function doLogin() {
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
		dataType : 'json',
	    success: function(data) 
		{ debugger;
			
	    },
	    error: function() 
		{debugger;
	       console.log("AJAX Request 실패");
	    }
	});	
}