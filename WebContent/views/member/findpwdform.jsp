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
<title>비밀번호 찾기</title>
</head>
<script>
	function find() {
		
		// 사용자 입력가 입력할 input 태그 컴포넌드들
		var id = $("#id");
		var name = $("#nm");
		var tel = $("#tel");

		//사용자 입력 값 null체크
		if (!id.val()) {
			alert("아이디를 입력하세요");
			id.focus();
			return;
		}
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

		//사용자 입력 값 정규식 체크
		var regExp = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,3}$/;
		if (id.val().match(regExp) == null) {
			alert("잘못된 아이디 형식입니다.");
			$('#id').val('');
			$('#id').focus();
			return;
		}

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

		$("#findform").submit();
		// /member/findpwdproc 받은 정보 가지고 비밀번호 재설정 폼으로 보냄
	}
</script>
<body>

	<%@include file ="/common/mainNav.jsp" %>

	<section>
		<div>
			<p>비밀번호 찾기</p>
		</div>
		<div>
			<form action="/member/findpwdproc" method="post" id="findform">
				<div>
					아이디 <input type="text" id="id" name="id" placeholder="이메일">
				</div>
				<div>
					이름 <input type="text" id="nm" name="nm">
				</div>
				전화번호 <input type="text" id="tel" name="tel" placeholder="숫자만 입력">
			</form>
			<button onclick="find()">비밀번호 찾기</button>
			<button onclick="history.back()">취소</button>
		</div>
	</section>
	
	<%@include file ="/common/footer.jsp" %>
</body>
</html>