<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<link href="../resources/css/common/header.css?t=<%= new java.util.Date() %>" rel="stylesheet" />
<nav class="navbar navbar-expand-lg">
	<div class="container-fluid">
		<a class="navbar-brand custom-brand" href="javascript:void(0);" id="main">원블록스</a>
    	<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
     		<span class="navbar-toggler-icon"></span>
    	</button>
    	<div class="collapse navbar-collapse" id="navbarSupportedContent">
      		<ul class="navbar-nav me-auto mb-2 mb-lg-0">
 			</ul>
			<a id="logout" href="javascript:void(0);" onclick="logout(); return false;">로그아웃</a>
    	</div>
  	</div>
</nav>