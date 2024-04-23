<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<title></title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
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
</body>
</html>