<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 사이드바 -->
<div id="layoutSidenav_nav">
	<div class="sb-sidenav-menu" style="padding: 20px 10px;">
		<div class="nav" style="height: 100%;">
			<div
				style="width: 100%; padding: 8px 10px 8px 10px; text-align: left; height: 40px; border-radius: 10px; background-color: #EEF1F4;">
				<p style="font-size: 15px; font-weight: bold; float: left;">N
					판매량 조회</p>
				<a href="#" style="float: right; display: none;"
					id="btnShowCampaignName">▽</a> <a href="#" style="float: right;"
					id="btnHideCampaignName">△</a>
			</div>
			<div id="campaignNameList" style="width: 100%; height: 80%;">
				<ul style="padding-left: 10%;">
					<c:forEach var="campaign" items="${myCampaignList}" varStatus="status">
						<li style='list-style-type: none;     line-height: 40px;'><a  <c:if test='${campaign.onOffYn ne "Y"}'>style='color:lightgray;'</c:if> href="/campaign/productList?campaignId=${campaign.campaignId}&pageNum=1" data-campaign-id='${campaign.campaignId}'> ${campaign.memberCampaignName}</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- 사이드바 -->