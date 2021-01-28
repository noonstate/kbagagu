<%@page import="shop.kbgagu.www.common.LoginManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/main.css" type="text/css">
<meta charset="UTF-8">
<title>마이페이지</title>
</head>
<body>
	<%@include file ="/common/mainNav.jsp" %>
	
	<section>
	<%
		if (sessionId != null) {
	%>
	<a href="/mypage">마이페이지 홈</a>
	<a href="/mypage/userinfo">내정보</a>
	<a href="/shop/basketview">장바구니</a>
	<a href="/mypage/order?pn=1">주문아이템 리스트들</a>
	<a href="/mypage/reviewlist">제품 리뷰 목록</a>
	<a href="/mypage/questionlist">문의</a>
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