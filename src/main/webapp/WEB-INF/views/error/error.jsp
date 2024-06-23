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
    font-size: 3rem;
    text-align: center;
    line-height: 1.4;
}
.desc {
	text-align: center;
	font-size: 1.5rem;
    line-height: 1.4;
}

img {
	width:150px;
	height:150px;
}
</style>
<%
	response.setStatus(HttpServletResponse.SC_OK);
%>
	
</head>

<body>
	<!-- 주의/경고 -->
	<div class="system_msg_box">
		<div class="txt_side">
			<div class="tit">
				<strong>Error!</strong>
			</div>
			<div class="desc">요청하신 사항을 완료할 수 없습니다. 관리자에게 문의하세요.</div>
		</div>
	</div>
</body>
</html>
