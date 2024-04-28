<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<title></title>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>

	<script src="https://cdn.jsdelivr.net/npm/mustache@4.2.0/mustache.min.js"></script>
	
	<sitemesh:write property="head"/>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div id="layoutSidenav" style="display: flex;">
<%-- 		<jsp:include page="/campaign/list.do"></jsp:include> --%>
		<%@include file="/WEB-INF/views/common/left.jsp"%>
		<div id="layoutSidenav_content"  style="width: 100%">
			<%@include file="/WEB-INF/views/common/search.jsp"%>
			<sitemesh:write property="body"/>
		</div>
	</div>

<!-- ajax loading img -->
<div class="wrap-loading display-none">
    <div><img src="https://t1.daumcdn.net/cfile/tistory/233F6D505786DA870A" /></div>
</div>
<!-- ajax loading img -->
	
<!--  Modal -->
<div class="modal fade" id="campaignModal" style="--bs-modal-width: 60%;" tabindex="-1" aria-labelledby="campaignModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content" style="background-color: #EEF1F4;">
			<!-- campaignDialog.jsp -->
		</div>								
	</div> 
</div>

<div class="modal fade" id="modifyModal" style="--bs-modal-width: 60%;" tabindex="-1" aria-labelledby="modifyModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content" style="background-color: #EEF1F4;">
			<!-- campaignDialog.jsp -->
		</div>								
	</div> 
 </div>

</body>
</html>