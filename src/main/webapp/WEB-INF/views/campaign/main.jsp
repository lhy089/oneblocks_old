<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>원블록스</title>
</head>
<body>
	<div class="card-body">
						<div class="datatable-wrapper datatable-loading no-footer sortable fixed-columns">
							<div class="datatable-top">
								<button class="btn" data-bs-toggle="modal" data-bs-target="campaignModal" style="border: solid 1px;  background-color:#F6BE2C;" id="campaignAdd" type="button">+ 캠페인추가</button>
<!-- 								<button class="btn" style="border: solid 1px;" id="btnModifyCampaign" type="button">수정</button> -->
								<button class="btn" style="border: solid 1px;" id="btnDeleteCampaign" type="button">삭제</button>
							
								<div style="float: right;">
									
 <form action="/campaign/campaignList/excel" method="POST" name="excelForm" id="excelForm">
    	 <input type="hidden" name="campaignId" id="campaignId" value="">
		<input type="hidden" name="campaignName" value="">
		<input type="hidden" name="startDate" id="startDate" value="240422">
		<input type="hidden" name="endDate" id="endDate" value="240422">
		<input type="hidden" name="dateFlag" id="dateFlag" value="yesterday">
  	</form>
									<button class="btn" style="border: solid 1px;" id="btnExcelDownload" type="submit" form="excelForm">엑셀 다운로드</button>
								</div>
							</div>
							
							<div class="datatable-container">
								<table id="datatablesSimple" class="datatable-table" style="text-align: center;">
									<thead>
										<tr>
												<th data-sortable="true" style="width: 4%;" aria-sort="descending" class="datatable-descending">
													<input type="checkbox" name="campaignChk" id="campaignChk" value=""></th>
												<th data-sortable="true" style="width: 29%;">
													<a href="#" class="datatable-sorter" data-value="c">캠페인명</a></th>
												<th data-sortable="true" style="width: 7%;">
													<a href="#" class="datatable-sorter" data-value="o">On/Off</a></th>
												<th data-sortable="true" style="width: 15%;">
													<a href="#" class="datatable-sorter" data-value="p">판매가</a></th>
												<th data-sortable="true" style="width: 15%;">
													<a href="#" class="datatable-sorter" data-value="q">판매수량</a></th>
												<th data-sortable="true" style="width: 15%;">
													<a href="#" class="datatable-sorter" data-value="r">매출액</a></th>
												<th data-sortable="true" style="width: 15%;">
													<a href="#" class="datatable-sorter" data-value="u"> 업데이트</a></th>
										</tr>
									</thead>
									<tbody>
										
											<tr data-index="0">
												<td><input type="checkbox" name="campaignId" value="5781585945"></td>
												<td><a href="/campaign/productList?campaignId=5781585945&amp;pageNum=1">57815859451캠페인</a></td>
												
													
														<td>
															<svg class="svg-inline--fa fa-toggle-on" name="toggleOnOff" value="5781585945" aria-hidden="true" data-prefix="fas" data-icon="toggle-on" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512" data-fa-i2svg="" style="color: #F6BE2C; font-size: 25px; cursor:pointer;">
																<path fill="currentColor" d="M192 64C86 64 0 150 0 256S86 448 192 448H384c106 0 192-86 192-192s-86-192-192-192H192zm192 96a96 96 0 1 1 0 192 96 96 0 1 1 0-192z"></path>
															</svg>
															<svg class="svg-inline--fa fa-toggle-off" name="toggleOnOff" value="5781585945" aria-hidden="true" data-prefix="fas" data-icon="toggle-off" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512" data-fa-i2svg="" style="color: #F6BE2C; font-size: 25px; cursor:pointer; display:none;"><path fill="currentColor" d="M384 128c70.7 0 128 57.3 128 128s-57.3 128-128 128H192c-70.7 0-128-57.3-128-128s57.3-128 128-128H384zM576 256c0-106-86-192-192-192H192C86 64 0 150 0 256S86 448 192 448H384c106 0 192-86 192-192zM192 352a96 96 0 1 0 0-192 96 96 0 1 0 0 192z"></path></svg>
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
								<div class="center" style="float: none;">
						
						
		<div class="pagination" id="pages">
			
				
			

			
				
					
						<a href="#" id="pageBtn" data-value="1" onclick="pageSelect('1');return false;" class="active">1</a>
					
					
				
			
				
					
					
						<a href="#" id="pageBtn" data-value="2" onclick="pageSelect('2');return false;"> 2</a>
					
				
			
			
				
			
		</div>
		
	</div>
							</div>
						</div>
					</div>
</body>
</html>