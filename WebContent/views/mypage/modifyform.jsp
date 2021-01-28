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
<title>회원정보 수정</title>
</head>
<script>
	function modifyMember() {
		var name = $("#nm");
		var tel = $("#tel");

		// 빈칸 체크
		if (!name.val()) {
			alert("이름을 입력하세요");
			name.val('');
			name.focus();
			return;
		}
		if (!tel.val()) {
			alert("전화번호를 입력하세요");
			tel.val('');
			tel.focus();
			return;

		}

		// 정규식 검사
		var regExpName = /^[가-힣]{2,8}$/;
		if (name.val().match(regExpName) == null) {
			alert("올바른 이름을 입력하세요.");
			$('#nm').val('');
			$('#nm').focus();
			return;
		}

		var regExpTel = /[0-1]{2}[016789]+[0-9]{7,8}/;
		if (tel.val().match(regExpTel) == null) {
			alert("올바른 전화번호를 입력하세요.");
			$('#tel').val('');
			$('#tel').focus();
			return;
		}
		$('#modifyMember').submit();
	}
</script>

<body>

	<%@include file ="/common/mainNav.jsp" %>

	<section>
		<div>
			<p>회원정보수정</p>
		</div>
		<div>
			<form action="/mypage/modifymyinfoproc" method="post" id="modifyMember">
				<div>
					이름 <input type="text" id="nm" name="nm" value="<%=vo.getNm()%>">
				</div>
				<div>
				전화번호 <input type="text" id="tel" name="tel" placeholder="숫자만 입력" value="<%=vo.getTel()%>">
				</div>
				

			</form>
			<button onclick="modifyMember()">회원정보수정</button>
			<button onclick="location.href='/mypage/ModifyPwd'">비밀번호 변경</button>
			<button onclick="history.back()">취소</button>
		</div>
	</section>
	
	<%@include file ="/common/footer.jsp" %>
</body>

</html>