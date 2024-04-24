$(document).ready(function(){
	$("#btnShowCampaignName").click(function() {
		$("#campaignNameList").show();
		$("#btnHideCampaignName").show();
		$("#btnShowCampaignName").hide();
			
	});
		
	$("#btnHideCampaignName").click(function() {
		$("#campaignNameList").hide();
		$("#btnShowCampaignName").show();
		$("#btnHideCampaignName").hide();
	});
});
