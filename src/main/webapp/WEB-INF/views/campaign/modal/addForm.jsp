<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="../resources/js/campaign/modal/addForm.js?t=<%= new java.util.Date() %>"></script>
<link href="../resources/css/campaign/modal/addForm.css?t=<%= new java.util.Date() %>" rel="stylesheet" />
</head>
<body>
	<!--  Modal Header -->
	<div class="modal-header">
		<h2 class="modal-title" id="campaignModalLabel">새로운 캠페인 생성</h2>
		<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	</div>

	<!-- Modal body -->
	<div class="modal-body">
		<div id="mainForm">
			<div id="joinForm">
				<div class="row">
					<div class="col-md-1 mb-3 width15p">캠페인명</div>
					<div class="col-md-7 mb-3 width73p">
						<input type="text" class="form-control" id="campaignName" name="campaignName" placeholder="" value="" required>
					</div>
					<div class="col-md-1 mb-3 width12p custom-div2">0/30</div>
				</div>

				<div class="row">
					<div class="col-md-1 mb-3 width15p">N 상품 URL</div>
					<div class="col-md-7 mb-3 width73p">
						<input type="text" class="form-control" id="campaignUrl" name="campaignUrl" placeholder="" value="" required>
					</div>
					<div class="col-md-1 mb-3 width12p custom-div">
						<button id="getCampaignInfo" type="button" class="btn btn-primary custom-background-color-Y">조회</button>
					</div>
				</div>
				<div id="desc" class="modal-hidden">
					<p>추출된 상품에 대한 옵션과 추가구성상품은 아래와 같습니다. 아래의 옵션과 추가구성상품 중 해당 캠페인에 표시하고 싶은
						항목을 선택해 주세요. 캠페인 설정을 완료하신 이후에도 표시되는 항목은 변경이 가능합니다.
		 			</p>
				</div>

				<div class="datatable-container modal-hidden custom-modal-div">
					<label class="productlabel">옵션구성</label>
					<table id="datatablesSimple" class="datatable-table custom-modal-table">
						<thead class="custom-btn-background-color-G">
							<tr class="trScroll">
								<th class="width10p"><input type="checkbox" id="optionChk" value="" name="productChk"></th>
								<th class="width70p">상품명(옵션명)</th>
								<th class="width20p">옵션가</th>
							</tr>
						</thead>
						<tbody id="optionBody" class="tbodyScroll">
							
						</tbody>
					</table>
				</div>


				<div class="datatable-container modal-hidden custom-modal-div">
					<label class="productlabel">추가구성상품</label>
					<table id="datatablesSimple" class="datatable-table custom-modal-table">
						<thead class="custom-btn-background-color-G">
							<tr class="trScroll">
								<th class="width10p"><input type="checkbox" id="supplementChk" name="productChk" value=""></th>
								<th class="width70p">추가구성상품명</th>
								<th class="width20p">옵션가</th>
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
		<button type="button" class="btn btn-primary custom-background-color-Y" id="saveCampaign">저장</button>
	</div>
	
<script id="optionBodyTemplate" type="x-tmpl-mustache">
{{#optionListData}}
<tr data-index='0' class='trScroll'>
	<td class="width10p">
		<input type='checkbox' name='optionId' value='{{productId}}'/>
	</td>
	<td class="width70p">{{productName}}</td>
	<td class="width20p">{{productPrice}}</td>
</tr>
{{/optionListData}}
</script>

<script id="supplementBodyTemplate" type="x-tmpl-mustache">
{{#supplementList}}
<tr data-index='0' class='trScroll'>
	<td class="width10p">
		<input type='checkbox' name='supplementId' value='{{productId}}'/>
	</td>
	<td class="width70p">{{productName}}</td>
	<td class="width20p">{{productPrice}}</td>
</tr>
{{/supplementList}}
</script>

</body>
</html>
