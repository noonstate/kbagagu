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
<title>아이디 찾기</title>
</head>
<script>
	function find() {
		// 컴포넌트 변수에 할당
		var name = $("#nm");
		var tel = $("#tel");

		// 널체크
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

		//정규식체크
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

		// /member/findidproc 아이디 찾기 알고리즘으로 보내기 
		$("#findform").submit();
	}
</script>
<body>

	<%@include file ="/common/mainNav.jsp" %>

	<section>
		<div>
			<p>아이디 찾기</p>
		</div>
		<div>
			<form action="/member/findidproc" method="post" id="findform">
				<div>
					이름 <input type="text" id="nm" name="nm">
				</div>
				전화번호 <input type="text" id="tel" name="tel" placeholder="숫자만 입력">
			</form>
			<button onclick="find()">아이디 찾기</button>
			<button onclick="history.back()">취소</button>
		</div>
	</section>
	
	<%@include file ="/common/footer.jsp" %>
</body>
</html>