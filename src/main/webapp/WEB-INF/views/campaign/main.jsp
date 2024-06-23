<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>원블록스</title>
<script src="../resources/js/campaign/main.js?t=<%= new java.util.Date() %>"></script>
<link href="../resources/css/campaign/main.css?t=<%= new java.util.Date() %>" rel="stylesheet" />
</head>
<body>
	<div class="card-body">
		<div
			class="datatable-wrapper datatable-loading no-footer sortable fixed-columns">
			<div class="datatable-top">
				<button class="btn custom_borderBasic" data-bs-toggle="modal"
					data-bs-target="campaignModal" id="campaignAdd" type="button">+ 캠페인추가</button>
				<!-- 								<button class="btn" style="border: solid 1px;" id="btnModifyCampaign" type="button">수정</button> -->
				<button class="btn custom_borderBasic" id="btnDeleteCampaign" type="button">삭제</button>
					
				<button class="btn" style="border: solid 1px; background-color:#F6BE2C; display:none;" id="btnModifyCampaign" type="button">캠페인설정변경</button>
				
				<input type="hidden" id="campaignId" value=""/>
				<input type="hidden" id="memberCampaignName" value=""/>
				<input type="hidden" id="productId" value=""/>
				<input type="hidden" id="orderFlag" value="c"/>
				<input type="hidden" id="orderKind" value="ASC"/>
				
				<div class="custom-float-right">
					<form action="/campaign/excel/download" method="POST" name="excelForm" id="excelForm">
						<input type="hidden" name="pageName" id="pageNameForExcel" value="">
				    	<input type="hidden" name="campaignId" id="campaignIdForExcel" value="">
						<input type="hidden" name="productId" id="productIdForExcel" value="">
						<input type="hidden" name="searchParam.startDate" id="startDateForExcel" value="">
						<input type="hidden" name="searchParam.endDate" id="endDateForExcel" value="">
						<input type="hidden" name="searchParam.dateFlag" id="dateFlagForExcel" value="">
						<input type="hidden" name="searchParam.flag" id="flagForExcel" value="date">
						<input type="hidden" name="searchParam.orderFlag" id="orderFlagForExcel" value="">
						<input type="hidden" name="searchParam.orderKind" id="orderKindForExcel" value="">
				  	</form>
					<button class="btn custom_borderBasic" type="button" onclick="submitExcelForm()" form="excelForm">엑셀 다운로드</button>
				</div>
			</div>

			<div class="datatable-container" id="campaignTableDiv">
			</div>
			<div class="datatable-container" id="campaignTableDiv">
				
			</div>
		<div class="datatable-bottom">
			<div class="center custom-float-none" id="paginationDiv">
					
			</div>
		</div>
	</div>
</div>

<%@include file="/WEB-INF/views/common/template/campaignTableTamplate.jsp"%>
<%@include file="/WEB-INF/views/common/template/productTableTamplate.jsp"%>
<%@include file="/WEB-INF/views/common/template/productDetailTableTamplate.jsp"%>


<script id="paginationTemplate" type="x-tmpl-mustache">
{{#paging}}
<div class="pagination" id="pages">
	{{#isPrev}}
		<a href="#" id="pageBtn" data-value="{{prev}}"  onclick="pageSelect('{{prev}}');return false;"> &lt;</a>
	{{/isPrev}}
	
	{{#num}}
		<a href="#" id="pageBtn" data-value="{{value}}"  onclick="pageSelect('{{value}}');return false;" {{#active}} class="active" {{/active}}>{{value}}</a>
	{{/num}}

	{{#isNext}}
		<a href="#" id="pageBtn" data-value="{{next}}" onclick="pageSelect('{{next}}');return false;">  &gt;</a>
	{{/isNext}}
</div>
{{/paging}}
</script>

</body>

</html>