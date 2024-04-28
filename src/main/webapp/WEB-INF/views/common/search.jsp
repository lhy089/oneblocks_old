<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script src="../resources/js/common/search.js?t=<%= new java.util.Date() %>"></script>
<link href="../resources/css/common/search.css?t=<%= new java.util.Date() %>" rel="stylesheet" />

<div id="pageRoute" style="display:none;"><a href="" id="main2">N 판매량 조회</a></div>
<nav class="navbar navbar-expand-lg search-nav">		
  <div class="container-fluid">
    <h4 id="pageName">N 판매량 조회</h4>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="searchDiv" aria-controls="searchDiv" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="searchDiv">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        
      </ul>
    </div>
  </div>
</nav>


<script id="searchTemplate" type="x-tmpl-mustache">
 {{#searchParam}}
	<ul class="navbar-nav me-auto mb-2 mb-lg-0">
        
      </ul>
      <form class="d-flex a" role="search" action="/campaign/main" method="post" id="searchForm"> 
      	<i class="fa-solid fa-angle-left fa-2x search-element" onclick="campaignMainList('prev')" style="cursor:pointer;"></i>
      	<select id="dateFlag" name="dateFlag" onchange="campaignMainList('select')" size="1" class="search-element">
      		{{#options}}
				<option value="{{val}}" {{#sel}}selected{{/sel}}>{{txt}}</option>
			{{/options}}
		</select>
		<input type="hidden" id="flag" name="flag" value="{{flag}}"/>
      	<input type="date" id="startDate" name="startDate" class="datepicker form-control search-element" value="{{startDate}}"> 
      	<label class="search-element">~</label> 
      	<input type="date" id="endDate" name="endDate" class="datepicker form-control search-element" value="{{endDate}}">

		<i class="fa-solid fa-angle-right fa-2x" onclick="campaignMainList('next')" style="cursor:pointer;" ></i>
      </form>
  {{/searchParam}}
</script>

<script id="productSearchTemplate" type="x-tmpl-mustache">
 {{#searchParam}}
	<ul class="navbar-nav me-auto mb-2 mb-lg-0">
        
      </ul>
      <form class="d-flex a" role="search" id="searchForm"> 
      	<i class="fa-solid fa-angle-left fa-2x search-element" onclick="productList('','prev')" style="cursor:pointer;"></i>
      	<select id="dateFlag" name="dateFlag" onchange="productList('','select')" size="1" class="search-element">
      		{{#options}}
				<option value="{{val}}" {{#sel}}selected{{/sel}}>{{txt}}</option>
			{{/options}}
		</select>
		<input type="hidden" id="flag" name="flag" value="{{flag}}"/>
      	<input type="date" id="startDate" name="startDate" class="datepicker form-control search-element" value="{{startDate}}"> 
      	<label class="search-element">~</label> 
      	<input type="date" id="endDate" name="endDate" class="datepicker form-control search-element" value="{{endDate}}">

		<i class="fa-solid fa-angle-right fa-2x" onclick="productList('','next')" style="cursor:pointer;" ></i>
      </form>
  {{/searchParam}}
</script>

<script id="productDetailSearchTemplate" type="x-tmpl-mustache">
 {{#searchParam}}
	<ul class="navbar-nav me-auto mb-2 mb-lg-0">
        
      </ul>
      <form class="d-flex a" role="search" id="searchForm"> 
      	<i class="fa-solid fa-angle-left fa-2x search-element" onclick="productDetail('','prev')" style="cursor:pointer;"></i>
      	<select id="dateFlag" name="dateFlag" onchange="productDetail('','select')" size="1" class="search-element">
      		{{#options}}
				<option value="{{val}}" {{#sel}}selected{{/sel}}>{{txt}}</option>
			{{/options}}
		</select>
		<input type="hidden" id="flag" name="flag" value="{{flag}}"/>
      	<input type="date" id="startDate" name="startDate" class="datepicker form-control search-element" value="{{startDate}}"> 
      	<label class="search-element">~</label> 
      	<input type="date" id="endDate" name="endDate" class="datepicker form-control search-element" value="{{endDate}}">

		<i class="fa-solid fa-angle-right fa-2x" onclick="productDetail('','next')" style="cursor:pointer;" ></i>
      </form>
  {{/searchParam}}
</script>