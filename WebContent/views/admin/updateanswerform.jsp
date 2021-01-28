<%@page import="shop.kbgagu.www.vo.QuestionVo"%>
<%@page import="shop.kbgagu.www.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	QuestionVo vo = (QuestionVo)request.getAttribute("vo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 답변페이지</title>
<link rel="stylesheet" href="/css/admin.css" type="text/css">
</head>
<body>
	<%@include file="/common/adminNav.jsp"%>

	<section>
	
		<div>
			<p>문의번호 : <%=vo.getQues_sq() %></p>
			<p>문의일 : <%=vo.getQues_dttm() %></p>
			<p>문의제목 : <%=vo.getQues_title() %></p>
			<p>문의내용 : <%=vo.getQues_cntnt() %></p>
		</div>
		
		<form action="/admin/updateanswerproc?sq=<%=vo.getQues_sq()%>" method="post">
			답변<textarea name="answer" id ="answer"><%=vo.getAnswer() %></textarea>
			<button>답변하기</button>
		</form>


	</section>
</body>
</html>