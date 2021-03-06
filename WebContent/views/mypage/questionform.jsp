<%@page import="shop.kbgagu.www.vo.MemberVo"%>
<%@page import="shop.kbgagu.www.common.LoginManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
MemberVo vo = (MemberVo)request.getAttribute("vo");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/main.css" type="text/css">
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>문의하기</title>
<style>
	body {
	margin: 0 auto;
	text-align: center;
}
</style>
</head>
<script type="text/javascript">

function insertQuestion(){
	var mber_sq = $("#mber_sq");
	var ques_title = $("#ques_title");
	var ques_cntnt = $("#ques_cntnt");
	
	$("#editorForm").submit();
}

</script>
<body>

	<%@include file ="/common/mainNav.jsp" %>
	
	<section>
	<h1>문의하기</h1>
		<form action="/mypage/insertquestion" id="editorForm">
			<table style = "margin-left: auto; margin-right: auto;" border="1" width="700px" >
				<tr>
					<td align="center">아이디</td>
					<td><%=vo.getId() %></td>
				</tr>
				<tr>
					<td align="center">제목</td>
					<td><input name="ques_title" id="ques_title" size="60"/></td>
				</tr>
				<tr>
					<td align="center">내용</td>
					<td>
						<textarea rows="30" cols="70" name="ques_cntnt" id="ques_cntnt"></textarea>	
					</td>
				</tr>			
			</table>
		</form>
		<button onclick="insertQuestion()">등록</button>
		<button onclick="history.back()">취소</button>
	</section>

	<%@include file ="/common/footer.jsp" %>
</body>
</html>