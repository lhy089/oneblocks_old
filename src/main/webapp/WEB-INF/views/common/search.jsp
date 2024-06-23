<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script src="../resources/js/common/search.js?t=<%= new java.util.Date() %>"></script>
<link href="../resources/css/common/search.css?t=<%= new java.util.Date() %>" rel="stylesheet" />

<div id="pageRoute" style="display:none;"><a href="" id="main2">N 판매량 조회</a></div>
<nav class="navbar navbar-expand-lg search-nav">		
  <div class="container-fluid">
    <h4 id="pageName" data-value="CAMPAIGN">N 판매량 조회</h4>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="searchDiv" aria-controls="searchDiv" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="searchDiv">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        
      </ul>
    </div>
  </div>
</nav>


<%@include file="/WEB-INF/views/common/template/searchTemplate.jsp"%>
<%@include file="/WEB-INF/views/common/template/productSearchTemplate.jsp"%>
<%@include file="/WEB-INF/views/common/template/productDetailSearchTemplate.jsp"%>

