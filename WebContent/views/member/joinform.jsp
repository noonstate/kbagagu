<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<link rel="stylesheet" href="/css/main.css" type="text/css">
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<script>
	var isMailIdCheck = false;

	function initMailIdCheck() {
		isMailIdCheck = false;
	} // 체크여부를 초기화하는 함수

	// ajax 사용함수
	function checkMailId() {
		initMailIdCheck();  // 초기화
		var id = $("#id").val();  // 

		$.ajax({
			url : "/checkMailId.ajax",
			type : "post",
			dataType : "json",
			data : {
				id : id
			},
			error : function() {
				alert("통신실패");
			},
			success : function(data) {
				if (data.isTrue == 'true') {
					$("#idmsg").text("중복된 아이디입니다.").css("color", "red");
					isMailIdCheck = false;
				} else {
					$("#idmsg").text("사용가능한 아이디입니다.").css("color", "green");
					var regExp = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,3}$/;
					if (id == '') {
						$("#idmsg").text("아이디를 입력해주세요.");
					}
					if (id.match(regExp) == null) {
						$("#idmsg").text("아이디 형식이 맞지 않습니다. 이메일을 입력하세요.").css("color", "red");
					}
					isMailIdCheck = true;
				}
			}
		});
	}

	function join() {
		var id = $("#id");
		var pwd = $("#pwd");
		var pwdc = $("#pwdc");
		var name = $("#name");
		var tel = $("#tel");
		
		// input에서 빈칸체크
		if (!id.val()) {
			alert("아이디를 입력하세요");
			id.focus();
			return;
		}
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

		if (pwd.val() != pwdc.val()) {
			alert("비밀번호를 다시 확인해주세요");
			pwd.val('');
			pwdc.val('');
			pwd.focus();
			return;
		}

		// input 입력받은 값의 유효성 검사 (정규표현식)
		var regExp = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,3}$/;
		if (id.val().match(regExp) == null) {
			alert("잘못된 아이디 형식입니다.");
			$('#id').val('');
			$('#id').focus();
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
		var regExpName = /^[가-힣]{2,8}$/;
		if (name.val().match(regExpName) == null) {
			alert("올바른 이름을 입력하세요.");
			$('#name').val('');
			$('#name').focus();
			return;
		}

		var regExpTel = /[0-1]{2}[016789]+[0-9]{7,8}/;
		if (tel.val().match(regExpTel) == null) {
			alert("올바른 전화번호를 입력하세요.");
			$('#tel').val('');
			$('#tel').focus();
			return;
		}
		
		if (isMailIdCheck) {
			$("#joinform").submit();  // /member/joinproc 으로  submit
		} else {
			alert("아이디 중복확인을 실패했습니다.");
		}
	}
</script>

<body>

	<%@include file ="/common/mainNav.jsp" %>

	<section>
	<div id = "joinForm">
	
		<div>
			<h5>회원가입</h5>
		</div>
		<br>
		
			<form action="/member/joinproc" method="post" id="joinform">
				<p>이메일ID</p>
				<div class="form-floating mb-3">
					<input class="form-control" type="text" id="id" name="id" oninput="checkMailId()" placeholder="example@gmail.com">
					<label for="id">example@gmail.com</label>
					<span id="idmsg"></span>
				</div>
				<p>비밀번호</p>
				<div class="form-floating mb-3">
					<input class="form-control" type="password" id="pwd" name="pwd" placeholder="특수문자가 포함된 4~20자">
					<label for="pwd">특수문자가 포함된 4~20자</label>
				</div>
				<p>비밀번호확인</p>
				<div class="form-floating mb-3">
					<input class="form-control" type="password" id="pwdc" name="pwdc" placeholder="비밀번호 확인">
					<label for="pwdc">비밀번호확인</label>
				</div>
				<p>이름</p>
				<div class="form-floating mb-3">
				<input class="form-control" type="text" id="name" name="name" placeholder="한글 2~6자">
				<label for="name">한글 2~6자</label>
				</div>
				<p>전화번호</p>
				<div class="form-floating mb-3">
					<input class="form-control" type="text" id="tel" name="tel" placeholder="숫자만 입력">
					<label for="tel">숫자만 입력</label>
				</div>
			</form>

		<br>
			<button onclick="join()" type="button" class="btn btn-primary">회원가입</button>
			<button onclick="location.href='/'" type="button" class="btn btn-primary">취소</button>
		
	</div>
	</section>
	
	<%@include file ="/common/footer.jsp" %>
</body>

</html>