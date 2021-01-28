<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/admin.css" type="text/css">
	<script src="https://code.jquery.com/jquery-3.5.1.js"
		integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
	<meta charset="UTF-8">
	<title>로그인</title>
</head>
<script>

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
	}



</script>

<body>
	<h1>로그인</h1>
																			
	<form action="/admin/loginproc" method="post" id="loginform" onsubmit="return login()">
		<table>
			<tr>
				<td>아이디</td>
				<td><input type="text" id="id" name="id" placeholder="이메일"><br>
				<span id="msg"></span></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" id="pwd" name="pwd">
				</td>

			</tr>
		</table>
		<input type="submit" value="로그인">
	</form>
</body>
</html>