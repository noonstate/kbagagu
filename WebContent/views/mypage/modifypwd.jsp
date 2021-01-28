<%@page import="shop.kbgagu.www.common.LoginManager"%>
<%@page import="shop.kbgagu.www.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/main.css" type="text/css">
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>비밀번호 재설정</title>
</head>
<script>
	function update() {
		var pwd = $("#pwd");
		var pwdc = $("#pwdc");

		if (!pwd.val()) {
			alert("비밀번호를 입력하세요");
			pwd.val('');
			pwd.focus();
			return;
		}
		if (!pwdc.val()) {
			alert("비밀번호확인을 입력하세요");
			pwd.val('');
			pwdc.val('');
			pwdc.focus();
			return;

		}

		if (pwd.val() != pwdc.val()) {
			alert("비밀번호를 다시 확인해주세요");
			pwd.val('');
			pwdc.val('');
			pwd.focus();
			return;
		}

		var regExpPwd = /^[a-zA-Z0-9!@#$%^&*]{4,20}$/;
		if (pwd.val().match(regExpPwd) == null) {
			alert("잘못된 비밀번호 형식입니다.");
			$('#pwd').val('');
			$('#pwd_confirm').val('');
			$('#pwd').focus();
			return;
		}

		$("#uform").submit();
	}
</script>
<body>
	<%@include file ="/common/mainNav.jsp" %>
	
	<%if(sessionId != null) { %>
	
	<section>
		<div>
			<p>비밀번호 변경</p>
		</div>
		<div>
			<form action="/mypage/ModifyProcPwd" method="post" id="uform">
				<div>
					비밀번호 <input type="password" id="pwd" name="pwd"> <span>특수문자가
						포함된 4자 ~ 20자로 비밀번호를 구성해주세요.</span>
				</div>
				<div>
					비밀번호확인 <input type="password" id="pwdc" name="pwdc">
				</div>
			</form>
			<button onclick="update()">비밀번호 재설정</button>
			<button onclick="history.back()">취소</button>
		</div>
	</section>
	<%} else {%>
	<% out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>"); }%>

	<%@include file ="/common/footer.jsp" %>
</body>
</html>