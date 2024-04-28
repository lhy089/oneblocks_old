$(document).ready(function(){
	$("#campaignAdd").click(function() {
		callCampaignAddModal();
	});
	
	$("#btnDeleteCampaign").click(function() {
		deleteCampaign();
	});
	
	$("#btnModifyCampaign").click(function() {
		modifyCampaign($("#campaignId").val());
	})
	
	campaignMainListInit();
});

function campaignCheck() {
	if($("#campaignChk").is(':checked')) {
		$("input[name='campaignId']").prop('checked',true);
	}else {
		$("input[name='campaignId']").prop('checked',false);
	}	
}

function productCheck() {
	if($("#productChk").is(':checked')) {
		$("input[name='productId']").prop('checked',true);
	}else {
		$("input[name='productId']").prop('checked',false);
	}	
}

function callCampaignAddModal() {
	$('#campaignModal').modal('hide')
	$("#campaignModal .modal-content").load("/campaign/modal/addForm");
	$("#campaignModal").modal("show");
}

function deleteCampaign() {
	if($("input[name='campaignId']:checked").length == 0 ){
		alert("캠페인을 선택해주세요.");
		return false;
	}
	
	if(confirm("캠페인 상품 추적을 멈추시겠습니까?")) {
		var campaignIdList = [];
		$.each($("input[name='campaignId']:checked"), function(index, campaign) {
			campaignIdList.push(campaign.value);
		});
		
		deleteCampaign(campaignIdList);
	}
}

function deleteCampaign(campaignIdList) {
	$.ajax({
			url: "/campaign/delete",
			type: "POST",
			async: false,
			data: JSON.stringify(campaignIdList),
		    contentType : 'application/json; charset=UTF-8',
			dataType : 'json',
			success: function(data)
			{ 
				alert("처리 되었습니다.");
				location.reload(true);
			},
			error: function() 
			{
				console.log("AJAX Request 실패");
			}
		});
}

function toggleCampaign(campaignId, flag) {
	if (flag == "off") {
		if(confirm("캠페인 상품 추적을 멈추시겠습니까?")) {
			var campaignIdList = [];
			campaignIdList.push(campaignId);
			deleteCampaign(campaignIdList);
		}
	}else {
		modifyCampaign(campaignId);
	}
}

function modifyCampaign(campaignId) {
	$("#modifyModal .modal-content").load("/campaign/modal/modifyForm?campaignId="+campaignId);
	$("#modifyModal").modal("show");
}
	
	
// 최초진입, 새로고침
function campaignMainListInit() {
	var param = {
			flag: "select",
			dateFlag: "yesterday",
			startDate: null,
			endDate: null,
			orderFlag: 'c',
			orderKind: 'ASC',
			pageNum: 1
	};
	
	campaignMainList(param);
}

function pageSelect(pageNum) {
	var pageValue = $("#pageName").data().value;
	if(pageValue == "CAMPAIGN") {
		var param = {
			flag: "date",
			dateFlag: $("#dateFlag option:selected").val(),
			startDate: $("#startDate").val(),
			endDate: $("#endDate").val(),
			orderFlag: $('[data-order="ASC"], [data-order="DESC"]').data('value'),
			orderKind: $('.datatable-sorter').data('order') == 'ASC' ? 'ASC' : 'DESC',
			pageNum: pageNum
		};
		
		campaignMainList(param);
	} else if(pageValue == "PRODUCT") {
		productListInit('','date',pageNum,'');
	} else if(pageValue == "DETAIL") {
		productDetailInit('','date',pageNum,'');
	}
}

function productListInit(campaignId,flag,pageNum,orderFlag) { debugger;
	if(campaignId == "" || campaignId == null) {
		campaignId = $("#campaignId").val();
	}
	
	var orderKind = "";
	if(orderFlag == "" || orderFlag == null) {
		orderFlag = $("#orderFlag").val();
		orderKind = $("#orderKind").val();
	}else {
		var beforeOrderFlag = $("#orderFlag").val();
		if(beforeOrderFlag != orderFlag) {
			orderKind = 'ASC';
		}else {
			orderKind = $("#orderKind").val() == 'ASC' ? 'DESC' : 'ASC';
		}
	}
	
	var searchParam = {
			flag: flag,
			dateFlag: $("#dateFlag option:selected").val(),
			startDate: $("#startDate").val(),
			endDate: $("#endDate").val(),
			orderFlag: orderFlag,
			orderKind: orderKind,
			pageNum: pageNum
	};
	
	var campaignListSearchParam = {
		searchParam: searchParam,
		campaignId: campaignId
	}
	
	productList(campaignListSearchParam);
} 

function productList(campaignListSearchParam) { 
	
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
					debugger;
			// 메인 테이블 세팅
			var productHead = setProductHeadParam(data.searchParam);		
			setTemplateView("productTableHeadTemplate", "tableHead", productHead);
			
			var salesList = setCampaignTableParam(data.salesList);		
			setTemplateView("productTableBodyTemplate", "tableBody", salesList);
			setProductPage(data);
	
			var paging = pasingParam(data.salesList.length, data.paging);
			setTemplateView("paginationTemplate", "paginationDiv", paging);
			
	    },
	    error: function() 
		{debugger;
	       console.log("AJAX Request 실패");
	    }
	});
}

function setProductHeadParam(searchParam) {
	var options = [
		{orderFlag:'c', headName:'옵션명'},
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
	
	var productHead = {productHead : options};
	return productHead;
}

function setProductDetailHeadParam(searchParam) {
	var options = [
		{orderFlag:'c', headName:'날짜'},
		{orderFlag:'o', headName:'성공여부'},
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
	
	var productDetailHead = {productDetailHead : options};
	return productDetailHead;
}

function setProductPage(data) {
	$("#pageName").text(data.campaign.campaignName);
	$("#campaignId").val(data.campaign.campaignId);
	$("#campaignAdd").hide();
	$("#btnDeleteCampaign").hide();
	$("#btnModifyCampaign").show();
	$("#pageRoute").text($("#pageRoute").text() + " / " + data.campaign.campaignName);
	$("#pageRoute").show();
	$("#pageName").data().value = "PRODUCT";
	$("#orderFlag").val(data.searchParam.orderFlag);
	$("#orderKind").val(data.searchParam.orderKind);
}

function productDetailInit(productId, flag, pageNum, orderFlag) {
	if(productId == null || productId == "") {
		productId = $("#productId").val();
	}
	var orderKind = "";
	if(orderFlag == "" || orderFlag == null) {
		orderFlag = $("#orderFlag").val();
		orderKind = $("#orderKind").val();
	}else {
		var beforeOrderFlag = $("#orderFlag").val();
		if(beforeOrderFlag != orderFlag) {
			orderKind = 'ASC';
		}else {
			orderKind = $("#orderKind").val() == 'ASC' ? 'DESC' : 'ASC';
		}
	}
	var searchParam = {
			flag: flag,
			dateFlag: $("#dateFlag option:selected").val(),
			startDate: $("#startDate").val(),
			endDate: $("#endDate").val(),
			orderFlag: orderFlag,
			orderKind: orderKind,
			pageNum: pageNum
	};
	
	var campaignListSearchParam = {
		searchParam: searchParam,
		campaignId: $("#campaignId").val(),
		productId: productId
	}
	
	productDetail(campaignListSearchParam);
}

function productDetail(campaignListSearchParam) {
	
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
			setTemplateView("productDetailSearchTemplate", "searchDiv", searchParam);
					
			// 메인 테이블 세팅
			var productDetailHead = setProductDetailHeadParam(data.searchParam);		
			setTemplateView("productDetailTableHeadTemplate", "tableHead", productDetailHead);
			
			var salesList = setCampaignTableParam(data.salesList);		
			setTemplateView("productDetailTableBodyTemplate", "tableBody", salesList);
			setProductDetailPage(data);
			
			var paging = pasingParam(data.salesList.length, data.paging);
			setTemplateView("paginationTemplate", "paginationDiv", paging);
			
	    },
	    error: function() 
		{debugger;
	       console.log("AJAX Request 실패");
	    }
	});
}

function setProductDetailSearchParam(productId, dateFlag){
	if(productId != null && productId != "") {
		$("#productId").val(productId);
	}else {
		productId = $("#productId").val();	
	}
	if(dateFlag == null || dateFlag == '') {
		dateFlag = "date";
	}
	var searchParam = {
			flag: dateFlag,
			dateFlag: $("#dateFlag option:selected").val(),
			startDate: $("#startDate").val(),
			endDate: $("#endDate").val(),
			orderFlag: $('[data-order="ASC"], [data-order="DESC"]').data('value'),
			orderKind: $('.datatable-sorter').data('order') == 'ASC' ? 'ASC' : 'DESC',
			pageNum: 1
	};
	
	var campaignListSearchParam = {
		searchParam: searchParam,
		campaignId: $("#campaignId").val(),
		productId: productId
	}
	
	return campaignListSearchParam;
}

function setProductDetailPage(data) {
	$("#productId").val(data.product.productId);
	$("#pageName").text(data.product.productName);
//	$("#pageRoute").text($("#pageRoute").text() + " / " + productName);
	$("#pageName").data().value = "DETAIL";
	$("#orderFlag").val(data.searchParam.orderFlag);
	$("#orderKind").val(data.searchParam.orderKind);
}
