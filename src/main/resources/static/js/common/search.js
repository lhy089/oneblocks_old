$(document).ready(function(){
	$("#main").click(function() {
		location.reload(true);
	})
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
		{ debugger;
			// 사이드바 세팅
			if(flag == undefined){
				var myCampaignList = setCampaignListParam(data.myCampaignList);
				setTemplateView("leftTemplate", "campaignNameList", myCampaignList);
			}
			
			// search 바 세팅
			var searchParam = setResutlSearchParam(data.searchParam);
			setTemplateView("searchTemplate", "searchDiv", searchParam);
					
			// 메인 세이블 세팅
			var salesList = setCampaignTableParam(data.salesList);		
			setTemplateView("campaignTableTemplate", "campaignTableDiv", salesList);
			
			var paging = pasingParam(data.salesList.length, data.paging);
			setTemplateView("paginationTemplate", "paginationDiv", paging);
			
	    },
	    error: function() 
		{debugger;
	       console.log("AJAX Request 실패");
	    }
	});
}

function setSearchParam(flag) {
	var searchParam = {};
	if(flag==null) {
		searchParam = {
			flag: "select",
			dateFlag: "yesterday",
			startDate: null,
			endDate: null,
			orderFlag: 'c',
			orderKind: 'ASC',
			pageNum: 1
		};
	} else {
		searchParam = {
			flag: flag,
			dateFlag: $("#dateFlag option:selected").val(),
			startDate: $("#startDate").val(),
			endDate: $("#endDate").val(),
			orderFlag: $('[data-order="A"], [data-order="D"]').data('value'),
			orderKind: $('.datatable-sorter').data('order') == 'A' ? 'ASC' : 'DESC',
			pageNum: 1
		};
	}
	
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

function setCampaignTableParam(salesList) {
	for(var i=0; i<salesList.length; i++) {
		salesList[i].onOffYn = salesList[i].onOffYn == "Y" ? true : false;
	}
	salesList = {salesList : salesList};
	return salesList;
}

function pasingParam(listSize, paging) {
	if(listSize > 0) {
		paging.isPrev = paging.prev !=0 ? true : false;
		paging.isNext = paging.next !=0 ? true : false;
		paging.num = [];
		for(var i = paging.first; i<=paging.last; i++) {
			if(paging.pageNum == i) {
				paging.num.push({value:i, active:true});
			}else {
				paging.num.push({value:i});
			}
		}
		paging = {paging : paging};
	}else {	
		paging = {paging : null};
	}
	return paging;
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