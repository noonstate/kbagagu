<%@page import="shop.kbgagu.www.vo.NoticeVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	NoticeVo vo = (NoticeVo) request.getAttribute("vo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지<%=vo.getNotice_sq() %>번</title>
<link rel="stylesheet" href="/css/main.css" type="text/css">
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
</head>
<body>
	<%@include file="/common/mainNav.jsp"%>
	
	<section>
		제목 <p><%=vo.getNotice_title() %></p>
		조회수 <p><%=vo.getNotice_hit() +1 %></p>
		내용 <p><%=vo.getNotice_cntnt() %></p>
		분류 <p><%=vo.getNotice_ctgry() %></p>
		작성일 <p><%=vo.getNotice_dttm() %></p>
	</section>
	
	<%@include file="/common/footer.jsp"%>
</body>
</html>