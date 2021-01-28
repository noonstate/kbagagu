<%@page import="shop.kbgagu.www.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	MemberVo vo = (MemberVo) request.getAttribute("vo");
%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/main.css" type="text/css">
<meta charset="UTF-8">
<title>내 정보 페이지</title>
</head>
<body>
	<%@include file ="/common/mainNav.jsp" %>
	
	<section>
	<%
		if (sessionId != null) {
	%>
	<p>
		이름 :
		<%=vo.getNm() %></p>
	<p>
		아이디 :
		<%=vo.getId() %></p>
	<p>
		전화번호 :
		<%=vo.getTel() %></p>
	<a href="/mypage/modifymyinfo">내정보수정</a>
	<a href="/mypage/leaveform">회원탈퇴</a>
	<%
		} else {
	%>
	<%
		out.println("<script>alert('로그인이 만료되었습니다.'); location.href='/member/loginform';</script>");
	}
	%>
	</section>
	
	
	<%@include file ="/common/footer.jsp" %>

</body>
</html>