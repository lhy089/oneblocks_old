$(document).ready(function(){

});

function campaignMainList(flag) {
	
	var searchParam = setSearchParam(flag);
	
	$.ajax({
		url: "/campaign/main",
	    type: "POST",
	    async: false,
	    data : JSON.stringify(searchParam),
		contentType : 'application/json',
		dataType : 'json',
	    success: function(data) 
		{ 
			var searchParam = setResutlSearchParam(data.searchParam);
			setTemplateView("searchTemplate", "searchDiv", searchParam);
			
			var myCampaignList = setCampaignListParam(data.myCampaignList);
			setTemplateView("leftTemplate", "campaignNameList", myCampaignList);
			
			var salesList = data.salesList;
			for(var i=0; i<salesList.length; i++) {
				salesList[i].onOffYn = salesList[i].onOffYn == "Y" ? true : false;
				
			}
			salesList = {salesList : salesList};
			setTemplateView("campaignTableTemplate", "campaignTableDiv", salesList);
			
			var paging = {paging : null};
			if(data.salesList.length > 0) {
				paging = data.paging;
				paging.isPrev = paging.isPrev !=0 ? true : false;
				paging.isNext = paging.isNext !=0 ? true : false;
				paging.num = [];
				for(var i = paging.first; i<=paging.last; i++) {
					if(paging.pageNum == i) {
						paging.num.push({value:i, active:true});
					}else {
						paging.num.push({value:i});
					}
				}
				paging = {paging : paging};
			}
			setTemplateView("paginationTemplate", "paginationDiv", paging);
			
			
			
	    },
	    error: function() 
		{debugger;
	       console.log("AJAX Request 실패");
	    }
	});
}

function setSearchParam(flag) {
	var searchParam = {
		flag: flag,
		dateFlag: $("#dateFlag option:selected").val(),
		startDate: $("#startDate").val(),
		endDate: $("#endDate").val(),
		orderFlag: $('[data-order="A"], [data-order="D"]').data('value'),
		orderKind: $('.datatable-sorter').data('order') == 'A' ? 'ASC' : 'DESC',
		pageNum: 1
	};
	
	return searchParam;
} 

function setResutlSearchParam(searchParam) {
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
	searchParam = {searchParam : searchParam};
	return searchParam;
}

function setCampaignListParam(campaignListParam) {
	$.each(campaignListParam,function(index, value) {
		if(value.onOffYn=='Y') value.onOffYn=true;
		else value.onOffYn=false;
	});
    var myCampaignList = {myCampaignList : campaignListParam};
    return myCampaignList;
}

function setTemplateView(templateName, elementName, parameter) {
	var template = document.getElementById(templateName).innerHTML; 
	Mustache.parse(template);
    var rendered = Mustache.render(template, parameter );
   	document.getElementById(elementName).innerHTML = rendered;
}


function chageStartDate() {
	$("#endDate").focus();
}

function chageEndDate() {
	$("#dateFlag").val("none");
	$("#flag").val("date");
	$("#searchForm").submit();
}