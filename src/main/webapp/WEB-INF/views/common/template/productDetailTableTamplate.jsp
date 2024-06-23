<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script id="productDetailTableTemplate" type="x-tmpl-mustache">
<table class="table table-hover table-main">
	<thead>
		<tr>
			<th style="width:17%;" scope="col"><a href="javascript:void(0);" onclick="productDetailInit('','date','1','c'); return false;" class="datatable-sorter" data-value="c">날짜</a></th>
			<th style="width:15%;" scope="col"><a href="javascript:void(0);" onclick="productDetailInit('','date','1','o'); return false;" class="datatable-sorter" data-value="o" >성공여부</a></th>
			<th style="width:17%;" scope="col"><a href="javascript:void(0);" onclick="productDetailInit('','date','1','p'); return false;" class="datatable-sorter" data-value="p">판매가</a></th>
			<th style="width:17%;" scope="col"><a href="javascript:void(0);" onclick="productDetailInit('','date','1','q'); return false;" class="datatable-sorter" data-value="q">판매수량</a></th>
			<th style="width:17%;" scope="col"><a href="javascript:void(0);" onclick="productDetailInit('','date','1','r'); return false;" class="datatable-sorter" data-value="r">매출액</a></th>
			<th style="width:17%;" scope="col"><a href="javascript:void(0);" onclick="productDetailInit('','date','1','u'); return false;" class="datatable-sorter" data-value="u">업데이트</a></th>
		</tr>
  	</thead>
	<tbody class="table-group-divider">
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
	</tbody>
</table>
</script>