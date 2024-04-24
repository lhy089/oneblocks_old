$(document).ready(function(){
//	$("#dateFlag").change(function() {
//		changeDateSelect();
//	})
});

function changeDateSelect() {
 	var searchParam = {
		flag: 'select',
		dateFlag: $("#dateFlag option:selected").val()
	};
	$.ajax({
		url: "/campaign/main",
	    type: "POST",
	    async: false,
	    data : JSON.stringify(searchParam),
		contentType : 'application/json',
		dataType : 'json',
	    success: function(data) 
		{ 
			var searchParam = data.searchParam;
			var options = [
				{val:'none', txt:'날짜선택'},
				{val:'yesterday', txt:'어제'},
				{val:'thisWeek', txt:'이번주'},
				{val:'thisMonth', txt:'이번달'},
				{val:'lastWeek', txt:'지난주'},
				{val:'lastMonth', txt:'지난달'},
				{val:'sevenDays', txt:'지난7일'},
				{val:'thirtyDays', txt:'지난30일'}
			];
			
			for(var i=0; i<options.length; i++) {
				if(options[i].val == searchParam.dateFlag) options[i].sel = true;
			}
			searchParam.options = options;
			searchParam = {searchParam : data.searchParam};
			
    		var template = document.getElementById("searchTemplate").innerHTML; 
    		Mustache.parse(template);
    		var rendered = Mustache.render(template,searchParam );
    		document.getElementById("searchDiv").innerHTML = rendered;
    		
    		$.each(data.myCampaignList,function(index,value) {
				if(value.onOffYn=='Y') value.onOffYn=true;
				else value.onOffYn=false;
			});
    		var myCampaignList = {myCampaignList : data.myCampaignList};
    		var leftTemplate = document.getElementById("leftTemplate").innerHTML; 
    		Mustache.parse(leftTemplate);
    		var rendered = Mustache.render(leftTemplate,myCampaignList );
    		document.getElementById("campaignNameList").innerHTML = rendered;
	    },
	    error: function() 
		{debugger;
	       console.log("AJAX Request 실패");
	    }
	});	
//	if($("#dateFlag").val() != "none") {
//		$("#flag").val("select");
//		$("#searchForm").submit();
//	}
}

function chageStartDate() {
	$("#endDate").focus();
}

function chageEndDate() {
	$("#dateFlag").val("none");
	$("#flag").val("date");
	$("#searchForm").submit();
}