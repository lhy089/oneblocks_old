<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head lang="ko">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
	<meta charset="utf-8">
	  <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style>
.system_msg_box {
	width: 830px;
	margin: 50px auto;
	border: 1px solid #ddd;
	background-color: #fff;
	border-radius: 5px;
}
.txt_side {
	padding: 10px 30px 30px;
    text-align: center;
    line-height: 1.4;
}
.tit {
	margin-bottom: 20px;
    font-size: 2rem;
    text-align: center;
    line-height: 1.4;
}
.desc {
	text-align: center;
}

img {
	width:150px;
	height:150px;
}
</style>
<%
	response.setStatus(HttpServletResponse.SC_OK);
%>
<script>
function reload() { 
	location.href = "/";
}

</script>
</head>

<body>
	<!-- 주의/경고 -->
	<div class="system_msg_box">
		<div class="txt_side">
			<div class="tit">
				<strong>No Login Session</strong>
			</div>
			<div class="desc">로그인 세션이 만료되었습니다. 다시 로그인해 주세요.</div>
			<div><button type="button" onclick="reload()">확인</button></div>
		</div>
	</div>
</body>
</html>
