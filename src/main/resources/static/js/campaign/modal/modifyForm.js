$(document).ready(function() {
	$("#optionChk").click(function () { debugger;
		optionListCheck();
	});
	
	$("#supplementChk").click(function () {
		supplementListCheck();
	});
	
	$("#modifyCampaign").click(function() {
		modifyCmapaign();
	});
});

function optionListCheck() {
	if($("#optionChk").is(':checked')) {
		$("input[name='optionId']").prop('checked',true);
	}else {
		$("input[name='optionId']").prop('checked',false);
	}
}

function supplementListCheck() {
	if($("#supplementChk").is(':checked')) {
		$("input[name='supplementId']").prop('checked',true);
	}else {
		$("input[name='supplementId']").prop('checked',false);
	}
}

function modifyCmapaign() {
	var campaignFormParam = getCampaignFormParam();
	
	$.ajax({
		url: "/campaign/modify/on",
		type: "POST",
		async: false,
		data: JSON.stringify(campaignFormParam),
	    contentType : 'application/json; charset=UTF-8',
	    dataType : 'json',
		success: function(data) 
		{ 
			if(data.resultCd == "SUCCESS") {
				alert("저장되었습니다.");
				$("#modifyModal").modal('hide')
				location.reload(true);
			}
		},
		beforeSend:function(){
        	$('.wrap-loading').removeClass('display-none');
   	 	},
    	complete:function(){
        	$('.wrap-loading').addClass('display-none');
    	},
		error: function() 
		{
			console.log("AJAX Request 실패");
		}
	});		
}

function getCampaignFormParam() {
	var campaign = {campaignId : $("#modifyCampaignId").val()};
	var productIdList = [];
		
	$.each($("input[name='optionId']:checked"), function(index, option) {
		productIdList.push(option.value);
	});
		
	$.each($("input[name='supplementId']:checked"), function(index, supplement) {
		productIdList.push(supplement.value);
	});
		
	var campaignFormParam = {
		campaign : campaign,
		productIdList : productIdList
	}
	
	return campaignFormParam;
}