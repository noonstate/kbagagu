<%@page import="shop.kbgagu.www.vo.NoticeVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	NoticeVo vo = (NoticeVo) request.getAttribute("vo");
%>
<!DOCTYPE html>

<html>
<head>
<link rel="stylesheet" href="/css/admin.css" type="text/css">
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>공지 수정 페이지</title>
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
	var content = '<%=vo.getNotice_cntnt()%>';
		var time = new Date(); // 현재시간 출력부분
		$(function() {
			$('#nowTime').text("현재 시간 : " + time.toLocaleDateString());
		});
	
		function updateNotice() {
			var item_nm = $("#notice_title");
			var content = $("#content");
			saveContent();
		}

	$("#editorForm").submit();
</script>
<body>
	<%@include file ="/common/adminNav.jsp" %>
	
	<div class="header">
		<h1 class="title">공지 수정페이지</h1>
	</div>
	<div class="container">
		<div class="nowTime">
			<span id="nowTime"></span>
		</div>
		<form action="/admin/updatenoticeproc" method="post" id="editorForm">
			<div>
				공지 번호 <p><%=vo.getNotice_sq() %></p>
				<input type="hidden" value="<%=vo.getNotice_sq() %>" name = "notice_sq">
			</div>
			<div class="inputArea">
				<label for="notice_title"> 공지 제목  </label> <input type="text"
					id="notice_title" name="notice_title" value="<%=vo.getNotice_title() %>">
			</div>
				
			<div>
				공지 분류 
					<%if(vo.getNotice_ctgry()==1){
					%>
					<input type="radio" name="notice_ctgry" value=1 class="notice_ctgry" checked="checked">일반공지
					<input type="radio" name="notice_ctgry" value=2 class="notice_ctgry">이벤트
					<input type="radio" name="notice_ctgry" value=3 class="notice_ctgry">중요공지
					<% } %>
					<%if(vo.getNotice_ctgry()==2){
					%>
					<input type="radio" name="notice_ctgry" value=1 class="notice_ctgry">일반공지
					<input type="radio" name="notice_ctgry" value=2 class="notice_ctgry" checked="checked">이벤트
					<input type="radio" name="notice_ctgry" value=3 class="notice_ctgry">중요공지
					<% } %>
					<%if(vo.getNotice_ctgry()==3){
					%>
					<input type="radio" name="notice_ctgry" value=1 class="notice_ctgry">일반공지
					<input type="radio" name="notice_ctgry" value=2 class="notice_ctgry">이벤트
					<input type="radio" name="notice_ctgry" value=3 class="notice_ctgry" checked="checked">중요공지
					<% } %>
				
			</div>
               
			<div>
				<p>공지내용</p>
				<jsp:include page="/editor/editorSkinForModify.jsp" flush="false"/>
			</div>

		</form>
		<button onclick="updateNotice()">수정</button>
		<button onclick="history.back()">취소</button>
	</div>
</body>
</html>