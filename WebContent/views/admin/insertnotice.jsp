<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<link rel="stylesheet" href="/css/admin.css" type="text/css">
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>공지 등록 페이지</title>
<style>
.header, .container {
	width: 960px;
	margin: 0 auto;
}

.title {
	text-align: center;
}

.nowTime {
	text-align: right;
}

.container div {
	padding-bottom: 10px;
}
</style>
</head>

<script type="text/javascript">
	var time = new Date(); // 현재시간 출력부분
	$(function() {
		$('#nowTime').text("현재 시간 : " + time.toLocaleDateString());
	});

	function registerNotice() {
		var item_nm = $("#notice_title");
		var content = $("#content");
		saveContent();
	}

	$("#editorForm").submit();
</script>
<body>
	<%@include file ="/common/adminNav.jsp" %>
	<div class="header">
		<h1 class="title">공지 등록페이지</h1>
	</div>
	<div class="container">
		<div class="nowTime">
			<span id="nowTime"></span>
		</div>
		<form action="/admin/insertnoticeproc" id="editorForm">
			<div class="inputArea">
				<label for="notice_title">공지제목 </label> <input type="text"
					id="notice_title" name="notice_title">
			</div>
				
			<div>
				공지 분류 
					<input type="radio" name="notice_ctgry" value=1 class="notice_ctgry">일반공지
					<input type="radio" name="notice_ctgry" value=2 class="notice_ctgry">이벤트
					<input type="radio" name="notice_ctgry" value=3 class="notice_ctgry">중요공지
				</select>
			</div>
               
			<div>
				<p>공지내용</p>
				<jsp:include page="/editor/editorSkinForRegister.jsp" flush="false" />
			</div>

		</form>
		<button onclick="registerNotice()">등록</button>
		<button onclick="history.back()">취소</button>
	</div>
</body>
</html>