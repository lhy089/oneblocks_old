<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="resources/js/campaignDialog.js?t=<%= new java.util.Date() %>"></script>
<style>
 .modal-hidden {
	display:none;
 }
 
 .tbodyScroll {
 	display: block;
    max-height: 100px;
    overflow-y: scroll;
 }
 
 .trScroll {
  display: table;
  width: 100%;
}
</style>
</head>
<body>
	<!--  Modal Header -->
	<div class="modal-header">
		<h2 class="modal-title" id="campaignModalLabel" style="font-size: 20px; font-weight: bold;">새로운 캠페인 생성</h2>
		<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	</div>

	<!-- Modal body -->
	<div class="modal-body">
		<div id="mainForm" style="padding: 20px 10px 10px 10px;">
			<div id="joinForm">
				<div class="row">
					<div class="col-md-1 mb-3" style="width: 15%">캠페인명</div>
					<div class="col-md-7 mb-3" style="width: 73%">
						<input type="text" class="form-control" id="campaignName" name="campaignName" placeholder="" value="" required>
					</div>
					<div class="col-md-1 mb-3" style="width: 12%; text-align: right; padding-right: 25px;">0/30</div>
				</div>

				<div class="row">
					<div class="col-md-1 mb-3" style="width: 15%">N 상품 URL</div>
					<div class="col-md-7 mb-3" style="width: 73%">
						<input type="text" class="form-control" id="campaignUrl" name="campaignUrl" placeholder="" value="" required>
					</div>
					<div class="col-md-1 mb-3" style="width: 12%; text-align: right; padding-right: 20px;">
						<button id="getCampaignInfo" type="button" class="btn btn-primary" style="    background-color:#F6BE2C;">조회</button>
					</div>
				</div>
<div id="desc" class="modal-hidden">
<p>
추출된 상품에 대한 옵션과 추가구성상품은 아래와 같습니다. 아래의 옵션과 추가구성상품 중 해당 캠페인에 표시하고 싶은
항목을 선택해 주세요. 캠페인 설정을 완료하신 이후에도 표시되는 항목은 변경이 가능합니다.
    
</p>

    
</div>

				<div class="datatable-container modal-hidden" style="margin: 0 20px; background-color: white;">
					<label style="padding: 15px 15px 5px 10px;">옵션구성</label>
					<table id="datatablesSimple" class="datatable-table"
						style="background-color: white; width: 100%; text-align: center;">
						<thead style="background-color: #9BA5B7;">
							<tr class="trScroll">
								<th style="width: 10%;"><input type="checkbox" id="optionChk" value="" name="productChk"></th>
								<th style="width: 70%;">상품명(옵션명)</th>
								<th style="width: 20%;">옵션가</th>
							</tr>
						</thead>
						<tbody id="optionBody" class="tbodyScroll">
							
						</tbody>
					</table>
				</div>


				<div class="datatable-container modal-hidden" style="margin: 0 20px; background-color: white;">
					<label style="padding: 15px 15px 5px 10px;">추가구성상품</label>
					<table id="datatablesSimple" class="datatable-table" style="background-color: white; width: 100%; text-align: center;">
						<thead style="background-color: #9BA5B7;">
							<tr class="trScroll">
								<th style="width: 10%;"><input type="checkbox" id="supplementChk" name="productChk" value=""></th>
								<th style="width: 70%;">추가구성상품명</th>
								<th style="width: 20%;">옵션가</th>
							</tr>
						</thead>
						<tbody id="supplementBody" class="tbodyScroll">
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal Footer -->
	<div class="modal-footer modal-hidden">
		<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		<button type="button" class="btn btn-primary" id="saveCampaign" style="    background-color:#F6BE2C;">저장</button>
	</div>
</body>
</html>
