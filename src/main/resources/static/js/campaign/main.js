$(document).ready(function(){
	$("#campaignAdd").click(function() {
		$('#campaignModal').modal('hide')
		$("#campaignModal .modal-content").load("/campaign/modal/addForm");
		$("#campaignModal").modal("show");
	});
	
	campaignMainList();
});

function productList(campaignId) { debugger;
	
	var searchParam = {
			flag: "date",
			dateFlag: $("#dateFlag option:selected").val(),
			startDate: $("#startDate").val(),
			endDate: $("#endDate").val(),
			orderFlag: $('[data-order="A"], [data-order="D"]').data('value'),
			orderKind: $('.datatable-sorter').data('order') == 'A' ? 'ASC' : 'DESC',
			pageNum: 1
	};
	
	var campaignListSearchParam = {
		searchParam: searchParam,
		campaignId: campaignId
	}
	
	$.ajax({
		url: "/campaign/product",
	    type: "POST",
	    async: false,
	    data : JSON.stringify(campaignListSearchParam),
		contentType : 'application/json',
		dataType : 'json',
	    success: function(data) 
		{ debugger;
		
			// search 바 세팅
			var searchParam = setResutlSearchParam(data.searchParam);
			setTemplateView("productSearchTemplate", "searchDiv", searchParam);
					
			// 메인 테이블 세팅
			var salesList = setCampaignTableParam(data.salesList);		
			setTemplateView("productTableTemplate", "campaignTableDiv", salesList);
			setProductPage(data.campaign);
			
			var paging = pasingParam(data.salesList.length, data.paging);
			setTemplateView("paginationTemplate", "paginationDiv", paging);
			
	    },
	    error: function() 
		{debugger;
	       console.log("AJAX Request 실패");
	    }
	});
}

function setProductPage(campaign) { debugger;
	$("#pageName").text(campaign.campaignName);
	$("#campaignId").val(campaign.campaignId);
	$("#campaignAdd").hide();
	$("#btnDeleteCampaign").hide();
	$("#btnModifyCampaign").show();
	$("#pageRoute").text($("#pageRoute").text() + " / " + campaign.campaignName);
	$("#pageRoute").show();
}

function productDetail(productId) {
	
	var searchParam = {
			flag: "date",
			dateFlag: $("#dateFlag option:selected").val(),
			startDate: $("#startDate").val(),
			endDate: $("#endDate").val(),
			orderFlag: $('[data-order="A"], [data-order="D"]').data('value'),
			orderKind: $('.datatable-sorter').data('order') == 'A' ? 'ASC' : 'DESC',
			pageNum: 1
	};
	
	var campaignListSearchParam = {
		searchParam: searchParam,
		campaignId: $("#campaignId").val(),
		productId: productId
	}
	
	$.ajax({
		url: "/campaign/productDetail",
	    type: "POST",
	    async: false,
	    data : JSON.stringify(campaignListSearchParam),
		contentType : 'application/json',
		dataType : 'json',
	    success: function(data) 
		{ debugger;
		
			// search 바 세팅
			var searchParam = setResutlSearchParam(data.searchParam);
			setTemplateView("productSearchTemplate", "searchDiv", searchParam);
					
			// 메인 테이블 세팅
			var salesList = {salesList : data.salesList};
			setTemplateView("productDetailTableTemplate", "campaignTableDiv", salesList);
			setProductDetailPage(data.salesList[0].productName);
			
			var paging = pasingParam(data.salesList.length, data.paging);
			setTemplateView("paginationTemplate", "paginationDiv", paging);
			
	    },
	    error: function() 
		{debugger;
	       console.log("AJAX Request 실패");
	    }
	});
}

function setProductDetailPage(productName) {
	$("#pageName").text(productName);
	$("#pageRoute").text($("#pageRoute").text() + " / " + productName);
}