<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/main.css" type="text/css">
	<script src="https://code.jquery.com/jquery-3.5.1.js"
		integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
	<meta charset="UTF-8">
	<title>로그인</title>
	<style>
		.btn{
			height: 58px;
		}
		#gotojoin{
			border: 3px solid rgb(74,74,74);
		}
		#gotojoin:hover {
			background-color: rgb(74,74,74);
			color: rgb(255,255,244);
}
	</style>
</head>
<script>

	var isSuccess = false;
	function initSuccess() {
		isSuccess = false;
		} // 초기화
	function login() {
		initSuccess();
		var id = $("#id");
		var pwd = $("#pwd");
		// 빈칸 확인
		if (!id.val()) {
			alert("아이디를 입력하세요");
			id.focus();
			return false;
		}
		if (!pwd.val()) {
			alert("비밀번호를 입력하세요");
			pwd.val('');
			pwd.focus();
			$("#msg").text('');
			return false;
		}

		// 정규식 검사
		var regExp = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,3}$/;
		if (id.val().match(regExp) == null) {
			alert("아이디를 다시 확인해주세요.");
			$('#id').val('');
			$('#id').focus();
			return false;
		}

		var regExpPwd = /^[a-zA-Z0-9!@#$%^&*]{4,12}$/;
		if (pwd.val().match(regExpPwd) == null) {
			alert("비밀번호를 다시 확인해주세요.");
			$('#pwd').val('');
			$('#pwd').focus();
			return false;
		}

		// 비밀번호 맞는 여부를 보기위해서 만든부분
		$.ajax({
			url: "/login.ajax",
			type: "post",
			async: false,  // 동기
			dataType: "json",
			data: {
				id: id.val(),
				pwd: pwd.val()
			},
			error: function () {
				alert("통신실패");
			},
			success: function (data) {
				if (data.isTrue == 'true') {
					isSuccess =  true;  // 로그인성공
				} else {
					$("#msg").text("이메일과 비밀번호를 다시확인해주세요.").css("color", "red");
					$("#pwd").val('');
					isSuccess =  false;
				}  
			}
		});
		return isSuccess;
	 }
	 //비밀번호 찾기 양식으로 보냄
	 function findPwd(){
		location.href= "/member/findpwd";
	}
	 //아이디 찾기 양식으로 보냄	
	function findId(){
		location.href= "/member/findid";
	}
</script>

<body>
	<%@include file ="/common/mainNav.jsp" %>
	
	<section>
	
	<div id = "loginForm">
		<h5>로그인</h5>
		<br>															
		<form action="/member/loginproc" method="post" id="loginform" onsubmit="return login()">
			<div class="form-floating mb-3">
				<input class="form-control" type="text" id="id" name="id" placeholder="이메일아이디">
				<label for="id">이메일아이디</label><span id="msg"></span>
			</div>
		
			<div class="form-floating mb-3">
				<input class="form-control" type="password" id="pwd" name="pwd" placeholder="비밀번호">
				<label for="pwd">비밀번호</label>
			</div>
			<input type="submit" class="btn btn-primary" value="로그인">
			<br>
			<br>
			<div class="form-floating mb-3">
			<button type="button" class="btn" id="idfind" onclick="findId()">아이디 찾기</button> <br>
			<button type="button" class="btn" id="pwdfind" onclick="findPwd()">비밀번호 찾기</button>
			</div>
			<div class="form-floating mb-3">
			<button type="button" onfocus="onfocusEvent()" class="btn" id="gotojoin" onclick="location.href='/member/joinform'">회원가입</button>
			</div>
		</form>
		
	
	</div>
	</section>
	
	<%@include file ="/common/footer.jsp" %>
</body>
</html>