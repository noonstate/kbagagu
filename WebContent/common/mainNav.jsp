<%@page import="shop.kbgagu.www.common.LoginManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	LoginManager lm = LoginManager.getInstance();
	String sessionId = lm.getMemberId(session); 
%>

	<nav id = "mainNav">
			<a href="/board/list?pn=1">NOTICE</a>
			<a href="/shop/list?pn=1&ctgry=0">SHOP</a>
			
			<a id = "homebtn" href="/"><img src = "/img/logo.png"></a>
			
			<% if(sessionId == null) { %> <!--불러올 페이지에 로그인 확인 있음 에러 무시-->
			<a href="/member/joinform">JOIN</a>
			<a href="/member/loginform">LOGIN</a>
			<% } else { %>
			<a href="/mypage">MYPAGE</a>
			<a href="/member/logoutproc">LOGOUT</a>
			<% } %>
	</nav>
	<div><!--네비바와 콘텐츠 간격용, 헤더 포지션이 fixed라 기본 간격을 줘야함--></div>