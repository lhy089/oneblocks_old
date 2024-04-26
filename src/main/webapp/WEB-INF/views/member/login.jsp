<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="../resources/js/member/login.js?t=<%= new java.util.Date() %>"></script>
	<link rel="stylesheet" type="text/css" href="../resources/css/member/login.css?t=<%= new java.util.Date() %>">
	<title>Main</title>
</head>
<body>
	<div id="mainForm">
		<div class="mainLogo">
			<div>
				<h1 style="font-size:40px;">원블록스</h1>
				<p>로그인</p>
			</div>
		</div>
		
		<form>
		<div id="loginForm">
		 	<div class="login-item">
		 		<p class="login-label">Email</p>
		 		<input type="text" name="email" id="email" class="login-input" autocomplete="off"/>
		 	</div>
		 	<div class="login-item">
		 		<p class="login-label">Password</p>
		 		<input onkeyup="doLogin()" type="password" name="password" id="password" class="login-input" autocomplete="off"/>
		 	</div>
		 	
		 	<div class="login-sub-item">
		 		<input id="submit" type="text" class="login pull-right" value="Log In">
		 	</div>
		</div>
		</form>
	</div>
</body>
</html>
