<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script id="productTableTemplate" type="x-tmpl-mustache">
<table class="table table-hover table-main">
	<thead>
		<tr>
			<th style="width:5%;" scope="col"><input type="checkbox" name="campaignChk" id="campaignChk" value=""></th>
			<th style="width:50%;" scope="col"><a data-value="c" href="javascript:void(0);" onclick="productListInit('','date','1','c'); return false;" class="datatable-sorter">캠페인명</a></th>
			<th style="width:8%;" scope="col"><a data-value="o" href="javascript:void(0);" onclick="productListInit('','date','1','o'); return false;" class="datatable-sorter">On/Off</a></th>
			<th style="width:8%;" scope="col"><a data-value="p" href="javascript:void(0);" onclick="productListInit('','date','1','p'); return false;" class="datatable-sorter">판매가</a></th>
			<th style="width:8%;" scope="col"><a data-value="q" href="javascript:void(0);" onclick="productListInit('','date','1','q'); return false;" class="datatable-sorter">판매수량</a></th>
			<th style="width:8%;" scope="col"><a data-value="r" href="javascript:void(0);" onclick="productListInit('','date','1','r'); return false;" class="datatable-sorter">매출액</a></th>
			<th style="width:13%;" scope="col"><a data-value="u" href="javascript:void(0);" onclick="productListInit('','date','1','u'); return false;" class="datatable-sorter">업데이트</a></th>
		</tr>
  	</thead>
	<tbody class="table-group-divider">
{{#salesList}}
<tr data-index="0" {{^onOffYn}} style ="--bs-table-bg: #EEF1F4;"{{/onOffYn}} >
	<td><input type='checkbox' name='productId' value='{{productId}}'/></td>
	<td><a href="javascript:void(0);" onclick="productDetailInit('{{productId}}','date','1',''); return false;" >{{productName}}</a></td>
	{{#onOffYn}} 
		<td>
			<svg class="svg-inline--fa fa-toggle-on" name="toggleOnOff" onclick="toggleProduct('{{productId}}','N',this)" value='{{productId}}' aria-hidden="true" data-prefix="fas" data-icon="toggle-on" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512" data-fa-i2svg="" style="color: #F6BE2C; font-size: 25px; cursor:pointer;">
				<path fill="currentColor" d="M192 64C86 64 0 150 0 256S86 448 192 448H384c106 0 192-86 192-192s-86-192-192-192H192zm192 96a96 96 0 1 1 0 192 96 96 0 1 1 0-192z"></path>
			</svg>
			<svg class="svg-inline--fa fa-toggle-off" name="toggleOnOff" onclick="toggleProduct('{{productId}}','Y',this)" value='{{productId}}' aria-hidden="true" data-prefix="fas" data-icon="toggle-off" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512" data-fa-i2svg="" style="color: #F6BE2C; font-size: 25px; cursor:pointer; display:none;"><path fill="currentColor" d="M384 128c70.7 0 128 57.3 128 128s-57.3 128-128 128H192c-70.7 0-128-57.3-128-128s57.3-128 128-128H384zM576 256c0-106-86-192-192-192H192C86 64 0 150 0 256S86 448 192 448H384c106 0 192-86 192-192zM192 352a96 96 0 1 0 0-192 96 96 0 1 0 0 192z"></path></svg>
		</td>
	{{/onOffYn}}
	{{^onOffYn}}
		<td>
			<svg class="svg-inline--fa fa-toggle-on" name="toggleOnOff" onclick="toggleProduct('{{productId}}','N',this)" value='{{productId}}' aria-hidden="true" data-prefix="fas" data-icon="toggle-on" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512" data-fa-i2svg="" style="color: #F6BE2C; font-size: 25px; cursor:pointer; display:none;">
				<path fill="currentColor" d="M192 64C86 64 0 150 0 256S86 448 192 448H384c106 0 192-86 192-192s-86-192-192-192H192zm192 96a96 96 0 1 1 0 192 96 96 0 1 1 0-192z"></path>
			</svg>
			<svg class="svg-inline--fa fa-toggle-off" name="toggleOnOff" onclick="toggleProduct('{{productId}}','Y',this)" value='{{productId}}' aria-hidden="true" data-prefix="fas" data-icon="toggle-off" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512" data-fa-i2svg="" style="color: #F6BE2C; font-size: 25px; cursor:pointer;"><path fill="currentColor" d="M384 128c70.7 0 128 57.3 128 128s-57.3 128-128 128H192c-70.7 0-128-57.3-128-128s57.3-128 128-128H384zM576 256c0-106-86-192-192-192H192C86 64 0 150 0 256S86 448 192 448H384c106 0 192-86 192-192zM192 352a96 96 0 1 0 0-192 96 96 0 1 0 0 192z"></path></svg>
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
	</tbody>
</table>
</script>