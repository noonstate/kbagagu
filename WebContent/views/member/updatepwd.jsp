<%@page import="shop.kbgagu.www.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 
	MemberVo vo = (MemberVo) request.getAttribute("vo");
%>
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
		
		//사용자에게 입력받을 컴포넌트
		var pwd = $("#pwd");
		var pwdc = $("#pwdc");

 		//입력란 null체크
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

		//입력 값 같은지
		if (pwd.val() != pwdc.val()) {
			alert("비밀번호를 다시 확인해주세요");
			pwd.val('');
			pwdc.val('');
			pwd.focus();
			return;		
		} 

		//입력값 정규식 체크
		var regExpPwd = /^[a-zA-Z0-9!@#$%^&*]{4,20}$/;
		if (pwd.val().match(regExpPwd) == null) {
			alert("잘못된 비밀번호 형식입니다.");
			$('#pwd').val('');
			$('#pwd_confirm').val('');
			$('#pwd').focus();
			return;
		}

		$("#uform").submit(); 
		// /member/updatepwdproc 최종적으로 비밀번호 변경할 알고리즘
	}
</script>
<body>

	<%@include file ="/common/mainNav.jsp" %>

	<section>
		<div>
			<p>비밀번호재설정</p>
		</div>
		<div>
			<form action="/member/updatepwdproc" method="post" id="uform">
				<div>
					비밀번호 <input type="password" id="pwd" name="pwd"> <span>특수문자가
						포함된 4자 ~ 20자로 비밀번호를 구성해주세요.</span>
				</div>
				<div>
					비밀번호확인 <input type="password" id="pwdc" name="pwdc">
				</div>
				<input name="nm" style="display: none;" value="<%=vo.getNm()%>">
				<input name="id" style="display: none;" value="<%=vo.getId()%>">
				<input name="tel" style="display: none;" value="<%=vo.getTel()%>">
			</form>
			<button onclick="update()">비밀번호 재설정</button>
			<button onclick="history.back()">취소</button>
		</div>
	</section>
	
	<%@include file ="/common/footer.jsp" %>
</body>
</html>