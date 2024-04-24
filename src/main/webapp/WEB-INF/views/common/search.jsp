<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script src="../resources/js/common/search.js?t=<%= new java.util.Date() %>"></script>
<link href="../resources/css/common/search.css?t=<%= new java.util.Date() %>" rel="stylesheet" />


<nav class="navbar navbar-expand-lg search-nav">
  <div class="container-fluid">
    <h3 id="pageName">N 판매량 조회</h3>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="searchDiv" aria-controls="searchDiv" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="searchDiv">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        
      </ul>
      <form class="d-flex" role="search" action="/campaign/main" method="post" id="searchForm"> 
      	<i class="fa-solid fa-angle-left fa-2x search-element" style="cursor:pointer;"></i>
      	<select id="dateFlag" name="dateFlag" onchange="changeDateSelect()" size="1" class="search-element">
      		<option value="none">날짜선택</option>
			<option value="yesterday" selected>어제</option>
			<option value="thisWeek">이번주</option>
			<option value="thisMonth">이번달</option>
			<option value="lastWeek">지난주</option>
			<option value="lastMonth">지난달</option>
			<option value="sevenDays">지난7일</option>
			<option value="thirtyDays">지난30일</option>
		</select>
		
		<input type="hidden" id="flag" name="flag" value=""/>
      	<input type="date" id="startDate" name="startDate" class="datepicker form-control search-element" value="${searchParam.startDate}"> 
      	<label class="search-element">~</label> 
      	<input type="date" id="endDate" name="endDate" class="datepicker form-control search-element" value="${searchParam.endDate}">

		<i class="fa-solid fa-angle-right fa-2x " style="cursor:pointer;" ></i>
      </form>
    </div>
  </div>
</nav>


<script id="searchTemplate" type="x-tmpl-mustache">
 {{#searchParam}}
	<ul class="navbar-nav me-auto mb-2 mb-lg-0">
        
      </ul>
      <form class="d-flex a" role="search" action="/campaign/main" method="post" id="searchForm"> 
      	<i class="fa-solid fa-angle-left fa-2x search-element" style="cursor:pointer;"></i>
      	<select id="dateFlag" name="dateFlag"  onchange="changeDateSelect()" size="1" class="search-element">
      		{{#options}}
				<option value="{{val}}" {{#sel}}selected{{/sel}}>{{txt}}</option>
			{{/options}}
		</select>
		<input type="hidden" id="flag" name="flag" value="{{flag}}"/>
      	<input type="date" id="startDate" name="startDate" class="datepicker form-control search-element" value="{{startDate}}"> 
      	<label class="search-element">~</label> 
      	<input type="date" id="endDate" name="endDate" class="datepicker form-control search-element" value="{{endDate}}">

		<i class="fa-solid fa-angle-right fa-2x " style="cursor:pointer;" ></i>
      </form>
  {{/searchParam}}
</script>