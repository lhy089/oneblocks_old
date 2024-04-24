$(document).ready(function(){
	$("#getCampaignInfo").click(function () {
		getCampaignInfo();
	});
});

var campaignObj = {};
var optionList = [];
var supplementList = [];

function getCampaignInfo() {
	var campaignUrl = $("#campaignUrl").val();
	if(campaignUrl.indexOf("?") > -1) {
		var campaignUrl = campaignUrl.substring(0,campaignUrl.indexOf("?"));
		$("#campaignUrl").val(campaignUrl);
		campaignUrl = encodeURIComponent(campaignUrl);
	}
	
	$.ajax({
		url: "/campaign/get",
		type: "GET",
		data: {"campaignUrl" : campaignUrl},
		async: false,
		success: function(data) 
		{ 
			$("input[name='productChk']").prop('checked',false);
			
			campaignObj = data.productInfo;
			optionList = data.optionList;
			supplementList = data.supplementList;
			
			$("#optionBody").empty();
			$("#supplementBody").empty();
			
			var optionListData = {optionListData : optionList};
    		var optionBodyTemplate = document.getElementById("optionBodyTemplate").innerHTML; 
    		Mustache.parse(optionBodyTemplate);
    		var rendered = Mustache.render(optionBodyTemplate,optionListData );
    		document.getElementById("optionBody").innerHTML = rendered;
    		
    		var supplementListData = {supplementListData : supplementList};
    		var supplementBodyTemplate = document.getElementById("supplementBodyTemplate").innerHTML; 
    		Mustache.parse(supplementBodyTemplate);
    		var rendered = Mustache.render(supplementBodyTemplate,supplementListData );
    		document.getElementById("supplementBody").innerHTML = rendered;
			
			$("#desc").removeClass("modal-hidden");
			$(".datatable-container").removeClass("modal-hidden");
			$(".modal-footer").removeClass("modal-hidden");
		},
		error: function() 
		{
			alert("조회에 실패 헀습니다. 브라우저에서 url이 정상 동작할 시 관리자에게 문의하세요.");
		}
	});	
}