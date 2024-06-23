$(document).ready(function(){ debugger;
//	productMainList();
});

function productMainList() {
	var searchParam = {
			flag: "date",
			dateFlag: $("#dateFlag option:selected").val(),
			startDate: $("#startDate").val(),
			endDate: $("#endDate").val(),
			orderFlag: 'c',
			orderKind: 'ASC',
			pageNum: 1
	};
	
	$.ajax({
		url: "/campaign/product",
	    type: "POST",
	    async: false,
	    data : JSON.stringify(searchParam),
		contentType : 'application/json',
		dataType : 'json',
	    success: function(data) 
		{ debugger;
		
			setNumber(data);
			// search 바 세팅
			var searchParam = setResutlSearchParam(data.searchParam);
			setTemplateView("productSearchTemplate", "searchDiv", searchParam);
					
			// 메인 테이블 세팅
			var salesList = setCampaignTableParam(data.salesList);		
			setTemplateView("productTableTemplate", "campaignTableDiv", salesList);
			
			var paging = pasingParam(data.salesList.length, data.paging);
			setTemplateView("paginationTemplate", "paginationDiv", paging);
			
	    },
	    error: function() 
		{debugger;
	       console.log("AJAX Request 실패");
	    }
	});
}

function setNumber(data) {
	$.each(data.salesList, function(index, option) {
		option.productPrice = option.productPrice == '-9999' ? "-" : Number(option.productPrice).toLocaleString('ko-KR');
		option.totalSalesQuantity = option.totalSalesQuantity == '-9999' ? "-" : Number(option.totalSalesQuantity).toLocaleString('ko-KR');
		option.totalSalesRevenue = option.totalSalesRevenue == '-9999' ? "-" : Number(option.totalSalesRevenue).toLocaleString('ko-KR');
	});
}