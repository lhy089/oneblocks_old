<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script src="../resources/js/common/left.js?t=<%= new java.util.Date() %>"></script>
<link href="../resources/css/common/left.css?t=<%= new java.util.Date() %>" rel="stylesheet" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 사이드바 -->
<div id="layoutSidenav_nav">
	<nav class="sb-sidenav accordion" id="sidenavAccordion">
	<div class="sb-sidenav-menu custom-sidenav-menu">
		<div class="nav custom-nav">
			<div id="leftNav-title">
				<p class="custom-float-left" >N 판매량 조회</p>
				<a href="#" class="custom-float-right" style="display: none;" id="btnShowCampaignName">▽</a> 
				<a href="#" class="custom-float-right" id="btnHideCampaignName">△</a>
			</div>
			<div id="campaignNameList">
				
			</div>
		</div>
	</div>
	</nav>
</div>
<!-- 사이드바 -->

<script id="leftTemplate" type="x-tmpl-mustache">
<ul>
{{#myCampaignList}}
	<li><a  {{^onOffYn}} style="color:lightgray;" {{/onOffYn}} href="#" onclick="productList('{{campaignId}}')"> {{memberCampaignName}}</a></li>
{{/myCampaignList}}
</ul>
</script>