<%@page import="shop.kbgagu.www.vo.MemberVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<String> arid = (ArrayList<String>) request.getAttribute("arid");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/main.css" type="text/css">
<meta charset="UTF-8">
<title>아이디 찾기</title>
</head>
<body> 
	<%@include file ="/common/mainNav.jsp" %>
	
	<section>
		<b>가입된아이디</b>
		<ul>
			<%
				for (int i = 0; i < arid.size(); i++) {
			%>
			<li><%=arid.get(i)%></li>
			<%
				}
			%>
		</ul>
	</section>
	
	<%@include file ="/common/footer.jsp" %>
</body>
</html>