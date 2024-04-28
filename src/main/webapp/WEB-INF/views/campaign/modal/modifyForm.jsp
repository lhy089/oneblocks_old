<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="../resources/js/campaign/modal/modifyForm.js?t=<%= new java.util.Date() %>"></script>
<link href="../resources/css/campaign/modal/modifyForm.css?t=<%= new java.util.Date() %>" rel="stylesheet" />
</head>>
<body>
	<!--  Modal Header -->
	<div class="modal-header">
		<h2 class="modal-title" id="modifyModalLabel">캠페인 설정 변경</h2>
		<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	</div>

	<!-- Modal body -->
	<div class="modal-body">
		<div id="mainForm">
			<div id="joinForm">

				<div class="row">
					<div class="col-md-1 mb-3 width15p">N 상품 URL</div>
					<div class="col-md-7 mb-3 width73p">
						<p  id="campaignUrl">${campaign.campaignUrl}</p>
						<input type="hidden" id="modifyCampaignId" value="${campaign.campaignId}"/> 
					</div>
				</div>
				<div id="desc">
					<p>
					추출된 상품에 대한 옵션과 추가구성상품은 아래와 같습니다. 아래의 옵션과 추가구성상품 중 해당 캠페인에 표시하고 싶은
					항목을 선택해 주세요. 캠페인 설정을 완료하신 이후에도 표시되는 항목은 변경이 가능합니다. 
					</p>
				</div>

				<div class="datatable-container modal-hidden custom-modal-div">
					<label class="productlabel">옵션구성</label>
					<table id="datatablesSimple" class="table table-hover custom-modal-table">
						<thead class="custom-btn-background-color-G">
							<tr class="trScroll">
								<th class="width10p"><input type="checkbox" id="optionChk" value="" name="productChk"></th>
								<th class="width70p">상품명(옵션명)</th>
								<th class="width20p">옵션가</th>
							</tr>
						</thead>
						<tbody id="optionBody" class="tbodyScroll">
							<c:forEach var="option" items="${optionList}" varStatus="status">
								<tr data-index='0' class='trScroll'>
									<td class="width10p">
										<input type='checkbox' name='optionId' value='${option.productId}' <c:if test='${option.onOffYn eq "Y"}'>checked</c:if> />
									</td>
									<td class="width70p">${option.productName}</td>
									<td class="width18p">${option.productPrice}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>


				<div class="datatable-container modal-hidden custom-modal-div">
					<label class="productlabel">추가구성상품</label>
					<table id="datatablesSimple" class="table table-hover custom-modal-table">
						<thead class="custom-btn-background-color-G">
							<tr class="trScroll">
								<th class="width10p"><input type="checkbox" id="supplementChk" name="productChk" value=""></th>
								<th class="width70p">추가구성상품명</th>
								<th class="width20p">옵션가</th>
							</tr>
						</thead>
						<tbody id="supplementBody" class="tbodyScroll">
							<c:forEach var="supplement" items="${supplementList}" varStatus="status">
								<tr data-index='0' class='trScroll'>
									<td class="width10p">
										<input type='checkbox' name='supplementId' value='${supplement.productId}' <c:if test='${supplement.onOffYn eq "Y"}'>checked</c:if>  />
									</td>
									<td class="width70p">${supplement.productName}</td>
									<td class="width18p">${supplement.productPrice}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal Footer -->
	<div class="modal-footer modal-hidden">
		<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		<button type="button" class="btn btn-primary custom-background-color-Y" id="modifyCampaign">저장</button>
	</div>
</body>
</html>
