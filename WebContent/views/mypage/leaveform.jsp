
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/main.css" type="text/css">
<meta charset="UTF-8">
<title>회원탈퇴</title>
</head>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script>
	function checkPwd() {
		var pwd = $("#pwd");
		if (!pwd.val()) {
			alert('비밀번호를 입력하세요');
			pwd.val('');
			pwd.focus();
			return;
		}

		var regExpPwd = /^[a-zA-Z0-9!@#$%^&*]{4,12}$/;
		if (pwd.val().match(regExpPwd) == null) {
			alert("비밀번호가 일치하지 않습니다. 비밀번호를 확인해 주세요.");
			$('#pwd').val('');
			$('#pwd').focus();
			return;
		}
		if (!confirm('정말로 탈퇴 하시겠습니까?')) {
			return;
		}
		$('#leaveMember').submit();
	}
</script>
<body>
	<%@include file ="/common/mainNav.jsp" %>

	<section>
	<div>
	<form action="/mypage/leaveproc" method="post" id="leaveMember">
		<table>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" id="pwd" name="pwd"></td>
			</tr>
		</table>
	</form>
	<!-- form태그 밖에 버튼 빼놓으면 엔터키 안먹힘 --> 
	<button onclick="checkPwd()" type="button">확인</button>
	<button onclick="history.back()" type="button">취소</button>
	</div>
	</section>
	
	<%@include file ="/common/footer.jsp" %>
</body>
</html>