$(document).ready(function(){
	$("#getCampaignInfo").click(function () {
		getCampaignInfo();
	});
	
	$("#saveCampaign").click(function () {
		saveCampaign();
	});
	
	$("#optionChk").click(function () {
		allOptionCheck();
	});
	
	$("#supplementChk").click(function () {
		allSupplementCheck();
	});
});

var campaignObj = {};
var optionList = [];
var supplementList = [];

function getCampaignInfo() {
	var campaignUrl = setCampaignUrl();
	$.ajax({
		url: "/campaign/get",
		type: "GET",
		data: {"campaignUrl" : campaignUrl},
		async: true,
		success: function(data) 
		{ 
			setNumber(data);
			$("input[name='productChk']").prop('checked',false);
			setData(data);
			
			var optionListData = {optionListData : optionList};
			setTemplateView("optionBodyTemplate", "optionBody", optionListData);
    		
    		var supplementListData = {supplementListData : supplementList};
			setTemplateView("supplementBodyTemplate", "supplementBody", supplementListData);
		
			setModalView();
		},
		beforeSend:function(){
        	$('.wrap-loading').removeClass('display-none');
   	 	},
    	complete:function(){
        	$('.wrap-loading').addClass('display-none');
    	},
		error: function() 
		{
			alert("조회에 실패 헀습니다. 브라우저에서 url이 정상 동작할 시 관리자에게 문의하세요.");
		}
	});	
}

function setCampaignUrl() {
	var campaignUrl = $("#campaignUrl").val();
	if(campaignUrl.indexOf("?") > -1) {
		var campaignUrl = campaignUrl.substring(0,campaignUrl.indexOf("?"));
		$("#campaignUrl").val(campaignUrl);
		campaignUrl = encodeURIComponent(campaignUrl);
	}
	
	return campaignUrl;
}

function setNumber(data) {
	$.each(data.optionList, function(index, option) {
		option.productPrice = option.productPrice == '-9999' ? "-" : Number(option.productPrice).toLocaleString('ko-KR');
	});
	
	$.each(data.supplementList, function(index, option) {
		option.productPrice = option.productPrice == '-9999' ? "-" : Number(option.productPrice).toLocaleString('ko-KR');
	});
}

function setData(data) {
	campaignObj = data.productInfo;
	optionList = data.optionList;
	supplementList = data.supplementList;
}

function setModalView() {
	$("#desc").removeClass("modal-hidden");
	$(".datatable-container").removeClass("modal-hidden");
	$(".modal-footer").removeClass("modal-hidden");
}

function saveCampaign() {
	
	var campaignFormParam = setCampaignFormParam();

	$.ajax({
		url: "/campaign/new",
		type: "POST",
		async: true,
		data: JSON.stringify(campaignFormParam),
        contentType : 'application/json; charset=UTF-8',
        dataType : 'json',
		success: function(data) 
		{ 
			if(data.resultCd == "DUPLECATIONNAME") {
				alert("이미 등록된 캠페인명입니다.");
			} else if(data.resultCd == "DUPLECATIONCAMPAIGN") {
				alert("이미 등록된 캠페인입니다.");
			}else if(data.resultCd == "SUCCESS") {
				alert("저장되었습니다.");
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

function setCampaignFormParam() {
	var memberCampaignName = $("#campaignName").val();	
	if(memberCampaignName == null || memberCampaignName =='') {
		alert("캠페인 명을 입력하세요.");
		return;
	}
	
	campaignObj.campaignUrl = $("#campaignUrl").val();
	campaignObj.optionCnt = optionList.length;
	campaignObj.supplementCnt = supplementList.length;
	
	var productIdList = [];
	
	$.each($("input[name='optionId']:checked"), function(index, option) {
		productIdList.push(option.value);
	});
	
	$.each($("input[name='supplementId']:checked"), function(index, supplement) {
		productIdList.push(supplement.value);
	});
	
	$.each(optionList, function(index, option) {
		option.productPrice = option.productPrice.replace(/,/g, '');
	});
	
	$.each(supplementList, function(index, option) {
		option.productPrice = option.productPrice.replace(/,/g, '');
	});
	
	var campaignFormParam = {
			memberCampaignName : memberCampaignName,
			campaign  : campaignObj,
			optionList : optionList,
			supplementList : supplementList,
			productIdList : productIdList
	}
	
	return campaignFormParam;
}

function handleOnInput(el, maxlength) {
  if(el.value.length > maxlength)  {
    el.value = el.value.substr(0, maxlength);
    alert("캠페인명은 30자까지 입력할 수 있습니다.");
  }
}

function allOptionCheck() {
	if($("#optionChk").is(':checked')) {
		$("input[name='optionId']").prop('checked',true);
	}else {
		$("input[name='optionId']").prop('checked',false);
	}
}

function allSupplementCheck() {
	if($("#supplementChk").is(':checked')) {
		$("input[name='supplementId']").prop('checked',true);
	}else {
		$("input[name='supplementId']").prop('checked',false);
	}
}