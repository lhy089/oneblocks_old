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

				<div class="custom-float-right">

					<form action="/campaign/campaignList/excel" method="POST"
						name="excelForm" id="excelForm">
						<input type="hidden" name="campaignId" id="campaignId" value="">
						<input type="hidden" name="campaignName" value=""> <input
							type="hidden" name="startDate" id="startDate" value="240422">
						<input type="hidden" name="endDate" id="endDate" value="240422">
						<input type="hidden" name="dateFlag" id="dateFlag"
							value="yesterday">
					</form>
					<button class="btn custom_borderBasic" id="btnExcelDownload" type="submit" form="excelForm">엑셀
						다운로드</button>
				</div>
			</div>

			<div class="datatable-container">
			
			
			<table class="table table-hover table-main">
  <thead>
    <tr>
      <th scope="col"><input type="checkbox" name="campaignChk" id="campaignChk" value=""></th>
      <th scope="col"><a href="#" class="datatable-sorter" data-value="c">캠페인명</a></th>
      <th scope="col"><a href="#" class="datatable-sorter" data-value="o">On/Off</a></th>
      <th scope="col"><a href="#" class="datatable-sorter" data-value="o">판매가</a></th>
      <th scope="col"><a href="#" class="datatable-sorter" data-value="o">판매수량</a></th>
      <th scope="col"><a href="#" class="datatable-sorter" data-value="o">매출액</a></th>
      <th scope="col"><a href="#" class="datatable-sorter" data-value="o">업데이트</a></th>
    </tr>
  </thead>
  <tbody class="table-group-divider">
    <tr>
      <th scope="row"><input type="checkbox" name="campaignId" value="5781585945"></th>
      <td><a href="/campaign/productList?campaignId=5781585945&amp;pageNum=1">57815859451캠페인</a></td>
      <td><svg class="svg-inline--fa fa-toggle-on"
									name="toggleOnOff" value="5781585945" aria-hidden="true"
									data-prefix="fas" data-icon="toggle-on" role="img"
									xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512"
									data-fa-i2svg=""
									style="color: #F6BE2C; font-size: 25px; cursor: pointer;">
																<path fill="currentColor"
										d="M192 64C86 64 0 150 0 256S86 448 192 448H384c106 0 192-86 192-192s-86-192-192-192H192zm192 96a96 96 0 1 1 0 192 96 96 0 1 1 0-192z"></path>
															</svg> <svg class="svg-inline--fa fa-toggle-off"
									name="toggleOnOff" value="5781585945" aria-hidden="true"
									data-prefix="fas" data-icon="toggle-off" role="img"
									xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512"
									data-fa-i2svg=""
									style="color: #F6BE2C; font-size: 25px; cursor: pointer; display: none;">
									<path fill="currentColor"
										d="M384 128c70.7 0 128 57.3 128 128s-57.3 128-128 128H192c-70.7 0-128-57.3-128-128s57.3-128 128-128H384zM576 256c0-106-86-192-192-192H192C86 64 0 150 0 256S86 448 192 448H384c106 0 192-86 192-192zM192 352a96 96 0 1 0 0-192 96 96 0 1 0 0 192z"></path></svg>
							</td>
      <td>9,900</td>
							<td>-</td>
							<td>-</td>
							<td>2024-04-22</td>
    </tr>
     <tr>
      <th scope="row"><input type="checkbox" name="campaignId" value="5781585945"></th>
      <td><a href="/campaign/productList?campaignId=5781585945&amp;pageNum=1">57815859451캠페인</a></td>
      <td><svg class="svg-inline--fa fa-toggle-on custom-icon-on-off"
									name="toggleOnOff" value="5781585945" aria-hidden="true"
									data-prefix="fas" data-icon="toggle-on" role="img"
									xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512"
									data-fa-i2svg="">
																<path fill="currentColor"
										d="M192 64C86 64 0 150 0 256S86 448 192 448H384c106 0 192-86 192-192s-86-192-192-192H192zm192 96a96 96 0 1 1 0 192 96 96 0 1 1 0-192z"></path>
															</svg> <svg class="svg-inline--fa fa-toggle-off custom-icon-on-off"
									name="toggleOnOff" value="5781585945" aria-hidden="true"
									data-prefix="fas" data-icon="toggle-off" role="img"
									xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512"
									data-fa-i2svg=""
									style="display: none;">
									<path fill="currentColor"
										d="M384 128c70.7 0 128 57.3 128 128s-57.3 128-128 128H192c-70.7 0-128-57.3-128-128s57.3-128 128-128H384zM576 256c0-106-86-192-192-192H192C86 64 0 150 0 256S86 448 192 448H384c106 0 192-86 192-192zM192 352a96 96 0 1 0 0-192 96 96 0 1 0 0 192z"></path></svg>
							</td>
      <td>9,900</td>
							<td>-</td>
							<td>-</td>
							<td>2024-04-22</td>
    </tr>
    
  </tbody>
</table>
			
			</div>
			<div class="datatable-bottom">
				<div class="center custom-float-none">
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>