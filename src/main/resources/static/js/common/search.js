$(document).ready(function(){
	$("#main").click(function() {
		location.reload(true);
	})
});

function logout() {
	if(confirm("로그아웃 하시겠습니까?")) {
		location.href="/logout";
	}
}

function campaignMainListBySearch(flag) {
	var param= {
		flag: flag,
		dateFlag: $("#dateFlag option:selected").val(),
		startDate: $("#startDate").val(),
		endDate: $("#endDate").val(),
		orderFlag: $("#orderFlag").val(),
		orderKind: $("#orderKind").val(),
		pageNum: 1
	};
	
	campaignMainList(param);
}

function campaignMainList(param) {
	var searchParam = param;
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
			if(data.myCampaignList != null) {
				var myCampaignList = setCampaignListParam(data.myCampaignList);
				setTemplateView("leftTemplate", "campaignNameList", myCampaignList);
			}
			
			setNumber(data);
			// search 바 세팅
			var searchParam = setResutlSearchParam(data.searchParam);
			setTemplateView("searchTemplate", "searchDiv", searchParam);
	
			// 메인 테이블 세팅
			var salesList = setCampaignTableParam(data.salesList);		
			setTemplateView("campaignTableTemplate", "campaignTableDiv", salesList);
			setCampaignPage(data);
			
			var paging = pasingParam(data.salesList.length, data.paging);
			setTemplateView("paginationTemplate", "paginationDiv", paging);
			
			$("#pageName").data().value = "CAMPAIGN";
			
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

function setCampaignHeadParam(searchParam) {
	var options = [
		{orderFlag:'c', headName:'캠페인명'},
		{orderFlag:'o', headName:'On/Off'},
		{orderFlag:'p', headName:'판매가'},
		{orderFlag:'q', headName:'판매수량'},
		{orderFlag:'r', headName:'매출액'},
		{orderFlag:'u', headName:'업데이트'}
	];
	
	for(var i=0; i<options.length; i++) {
		if(options[i].orderFlag == searchParam.orderFlag) {
			options[i].orderIcon = true;
			options[i].orderClass = searchParam.orderKind=="ASC" ? 'fa-sort-up' : 'fa-sort-down';
		}
	}
	
	var campaignHead = {campaignHead : options};
	return campaignHead;
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

function setCampaignPage(data) { debugger;
	$("#orderFlag").val(data.searchParam.orderFlag);
	$("#orderKind").val(data.searchParam.orderKind);
	
	setOrderIcon(data);
}

function setOrderIcon(data) {
	
	$('.datatable-sorter').children('svg').remove();
	var orderIcon = data.searchParam.orderKind=="ASC" ? 
	' <svg class="svg-inline--fa fa-sort-up" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="sort-up" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 320 512" data-fa-i2svg=""><path fill="currentColor" d="M182.6 41.4c-12.5-12.5-32.8-12.5-45.3 0l-128 128c-9.2 9.2-11.9 22.9-6.9 34.9s16.6 19.8 29.6 19.8H288c12.9 0 24.6-7.8 29.6-19.8s2.2-25.7-6.9-34.9l-128-128z"></path></svg>'
	: ' <svg class="svg-inline--fa fa-sort-down" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="sort-down" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 320 512" data-fa-i2svg=""><path fill="currentColor" d="M182.6 470.6c-12.5 12.5-32.8 12.5-45.3 0l-128-128c-9.2-9.2-11.9-22.9-6.9-34.9s16.6-19.8 29.6-19.8H288c12.9 0 24.6 7.8 29.6 19.8s2.2 25.7-6.9 34.9l-128 128z"></path></svg>';
	
	$('[data-value="'+data.searchParam.orderFlag+'"]').append(orderIcon);
	
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

function setNumber(data) { debugger;
	$.each(data.salesList, function(index, option) {
		option.campaignPrice = option.campaignPrice == '-9999' ? "-" : Number(option.campaignPrice).toLocaleString('ko-KR');
		option.productPrice = option.productPrice == '-9999' ? "-" : Number(option.productPrice).toLocaleString('ko-KR');
		option.totalSalesQuantity = option.totalSalesQuantity == '-9999' ? "-" : Number(option.totalSalesQuantity).toLocaleString('ko-KR');
		option.totalSalesRevenue = option.totalSalesRevenue == '-9999' ? "-" : Number(option.totalSalesRevenue).toLocaleString('ko-KR');
	});
}