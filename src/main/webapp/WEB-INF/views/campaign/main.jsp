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
				<input type="hidden" id="productId" value=""/>
				<input type="hidden" id="orderFlag" value="c"/>
				<input type="hidden" id="orderKind" value="ASC"/>
				
				<div class="custom-float-right">

					<form action="/campaign/campaignList/excel" method="POST"
						name="excelForm" id="excelForm">
					</form>
					<button class="btn custom_borderBasic" id="btnExcelDownload" type="submit" form="excelForm">엑셀
						다운로드</button>
				</div>
			</div>

			<div class="datatable-container" id="campaignTableDiv">
			</div>
			<div class="datatable-container">
				<table class="table table-hover table-main">
					<thead id="tableHead">
					</thead>
					<tbody id="tableBody" class="table-group-divider">
					</tbody>
				</table>
			</div>
		<div class="datatable-bottom">
			<div class="center custom-float-none" id="paginationDiv">
					
			</div>
		</div>
	</div>
</div>
<script id="campaignTableHeadTemplate" type="x-tmpl-mustache">
<tr>
	<th scope="col"><input type="checkbox" name="campaignChk" id="campaignChk" value=""></th>
{{#campaignHead}}
	<th scope="col"><a href="javascript:void(0);" onclick="" class="datatable-sorter">{{headName}} {{#orderIcon}}<i class="fa-solid {{orderClass}}"></i>{{/orderIcon}}</a></th>
{{/campaignHead}}
</tr>
</script>

<script id="campaignTableBodyTemplate" type="x-tmpl-mustache">
{{#salesList}}
<tr data-index="0" {{^onOffYn}} style ="--bs-table-bg: #EEF1F4;"{{/onOffYn}} >
	<td><input type='checkbox' name='campaignId' value='{{campaignId}}'/></td>
	<td><a href="javascript:void(0);" onclick="productListInit('{{campaignId}}','date','1',''); return false;">{{memberCampaignName}}</a></td>
	{{#onOffYn}} 
		<td>
			<svg class="svg-inline--fa fa-toggle-on" name="toggleOnOff" value='{{campaignId}}' aria-hidden="true" data-prefix="fas" data-icon="toggle-on" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512" data-fa-i2svg="" style="color: #F6BE2C; font-size: 25px; cursor:pointer;">
				<path fill="currentColor" d="M192 64C86 64 0 150 0 256S86 448 192 448H384c106 0 192-86 192-192s-86-192-192-192H192zm192 96a96 96 0 1 1 0 192 96 96 0 1 1 0-192z"></path>
			</svg>
			<svg class="svg-inline--fa fa-toggle-off" name="toggleOnOff" value='{{campaignId}}' aria-hidden="true" data-prefix="fas" data-icon="toggle-off" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512" data-fa-i2svg="" style="color: #F6BE2C; font-size: 25px; cursor:pointer; display:none;"><path fill="currentColor" d="M384 128c70.7 0 128 57.3 128 128s-57.3 128-128 128H192c-70.7 0-128-57.3-128-128s57.3-128 128-128H384zM576 256c0-106-86-192-192-192H192C86 64 0 150 0 256S86 448 192 448H384c106 0 192-86 192-192zM192 352a96 96 0 1 0 0-192 96 96 0 1 0 0 192z"></path></svg>
		</td>
	{{/onOffYn}}
	{{^onOffYn}}
		<td>
			<svg class="svg-inline--fa fa-toggle-on" name="toggleOnOff" value='{{campaignId}}' aria-hidden="true" data-prefix="fas" data-icon="toggle-on" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512" data-fa-i2svg="" style="color: #F6BE2C; font-size: 25px; cursor:pointer; display:none;">
				<path fill="currentColor" d="M192 64C86 64 0 150 0 256S86 448 192 448H384c106 0 192-86 192-192s-86-192-192-192H192zm192 96a96 96 0 1 1 0 192 96 96 0 1 1 0-192z"></path>
			</svg>
			<svg class="svg-inline--fa fa-toggle-off" name="toggleOnOff" value='{{campaignId}}' aria-hidden="true" data-prefix="fas" data-icon="toggle-off" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512" data-fa-i2svg="" style="color: #F6BE2C; font-size: 25px; cursor:pointer;"><path fill="currentColor" d="M384 128c70.7 0 128 57.3 128 128s-57.3 128-128 128H192c-70.7 0-128-57.3-128-128s57.3-128 128-128H384zM576 256c0-106-86-192-192-192H192C86 64 0 150 0 256S86 448 192 448H384c106 0 192-86 192-192zM192 352a96 96 0 1 0 0-192 96 96 0 1 0 0 192z"></path></svg>
		</td>
	{{/onOffYn}}	
	<td>{{campaignPrice}}</td>
	<td>{{totalSalesQuantity}}</td>
	<td>{{totalSalesRevenue}}</td>
	<td>{{updateDate}}</td>
</tr>
{{/salesList}}
{{^salesList}}
<tr data-index="0" class="center"> 
	<td colspan="7">조회할 데이터가 없습니다.</td>
</tr>
{{/salesList}}
</script>

<script id="productTableHeadTemplate" type="x-tmpl-mustache">
<tr>
	<th scope="col"><input type="checkbox" name="campaignChk" id="campaignChk" value=""></th>
{{#productHead}}
	<th scope="col"><a href="javascript:void(0);" onclick="productListInit('','date','1','{{orderFlag}}'); return false;" class="datatable-sorter">{{headName}} {{#orderIcon}}<i class="fa-solid {{orderClass}}"></i>{{/orderIcon}}</a></th>
{{/productHead}}
</tr>
</script>

<script id="productTableBodyTemplate" type="x-tmpl-mustache">
{{#salesList}}
<tr data-index="0" {{^onOffYn}} style ="--bs-table-bg: #EEF1F4;"{{/onOffYn}} >
	<td><input type='checkbox' name='productId' value='{{productId}}'/></td>
	<td><a href="javascript:void(0);" onclick="productDetailInit('{{productId}}','date','1',''); return false;" >{{productName}}</a></td>
	{{#onOffYn}} 
		<td>
			<svg class="svg-inline--fa fa-toggle-on" name="toggleOnOff" value='{{productId}}' aria-hidden="true" data-prefix="fas" data-icon="toggle-on" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512" data-fa-i2svg="" style="color: #F6BE2C; font-size: 25px; cursor:pointer;">
				<path fill="currentColor" d="M192 64C86 64 0 150 0 256S86 448 192 448H384c106 0 192-86 192-192s-86-192-192-192H192zm192 96a96 96 0 1 1 0 192 96 96 0 1 1 0-192z"></path>
			</svg>
			<svg class="svg-inline--fa fa-toggle-off" name="toggleOnOff" value='{{productId}}' aria-hidden="true" data-prefix="fas" data-icon="toggle-off" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512" data-fa-i2svg="" style="color: #F6BE2C; font-size: 25px; cursor:pointer; display:none;"><path fill="currentColor" d="M384 128c70.7 0 128 57.3 128 128s-57.3 128-128 128H192c-70.7 0-128-57.3-128-128s57.3-128 128-128H384zM576 256c0-106-86-192-192-192H192C86 64 0 150 0 256S86 448 192 448H384c106 0 192-86 192-192zM192 352a96 96 0 1 0 0-192 96 96 0 1 0 0 192z"></path></svg>
		</td>
	{{/onOffYn}}
	{{^onOffYn}}
		<td>
			<svg class="svg-inline--fa fa-toggle-on" name="toggleOnOff" value='{{productId}}' aria-hidden="true" data-prefix="fas" data-icon="toggle-on" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512" data-fa-i2svg="" style="color: #F6BE2C; font-size: 25px; cursor:pointer; display:none;">
				<path fill="currentColor" d="M192 64C86 64 0 150 0 256S86 448 192 448H384c106 0 192-86 192-192s-86-192-192-192H192zm192 96a96 96 0 1 1 0 192 96 96 0 1 1 0-192z"></path>
			</svg>
			<svg class="svg-inline--fa fa-toggle-off" name="toggleOnOff" value='{{productId}}' aria-hidden="true" data-prefix="fas" data-icon="toggle-off" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512" data-fa-i2svg="" style="color: #F6BE2C; font-size: 25px; cursor:pointer;"><path fill="currentColor" d="M384 128c70.7 0 128 57.3 128 128s-57.3 128-128 128H192c-70.7 0-128-57.3-128-128s57.3-128 128-128H384zM576 256c0-106-86-192-192-192H192C86 64 0 150 0 256S86 448 192 448H384c106 0 192-86 192-192zM192 352a96 96 0 1 0 0-192 96 96 0 1 0 0 192z"></path></svg>
		</td>
	{{/onOffYn}}	
	<td>{{productPrice}}</td>
	<td>{{totalSalesQuantity}}</td>
	<td>{{totalSalesRevenue}}</td>
	<td>{{updateDate}}</td>
</tr>
{{/salesList}}
{{^salesList}}
<tr data-index="0" class="center"> 
	<td colspan="7">조회할 데이터가 없습니다.</td>
</tr>
{{/salesList}}
</script>

<script id="productDetailTableHeadTemplate" type="x-tmpl-mustache">
<tr>
{{#productDetailHead}}
	<th scope="col"><a href="javascript:void(0);" onclick="productDetailInit('','date','1',''); return false;" class="datatable-sorter">{{headName}} {{#orderIcon}}<i class="fa-solid {{orderClass}}"></i>{{/orderIcon}}</a></th>
{{/productDetailHead}}
</tr>
</script>
<script id="productDetailTableBodyTemplate" type="x-tmpl-mustache">
{{#salesList}}
<tr data-index="0" >
	<td>{{updateDate}}</td>
	<td>성공</td>	
	<td>{{productPrice}}</td>
	<td>{{totalSalesQuantity}}</td>
	<td>{{totalSalesRevenue}}</td>
	<td>{{updateDate}}</td>
</tr>
{{/salesList}}
{{^salesList}}
<tr data-index="0" class="center"> 
	<td colspan="6">조회할 데이터가 없습니다.</td>
</tr>
{{/salesList}}
</script>


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