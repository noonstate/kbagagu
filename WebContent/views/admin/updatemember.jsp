<%@page import="shop.kbgagu.www.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberVo vo = (MemberVo)request.getAttribute("vo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 비밀번호 재설정</title>
<link rel="stylesheet" href="/css/admin.css" type="text/css">
</head>
<body>
	<%@include file="/common/adminNav.jsp"%>

	<section>
		회원번호<%=vo.getMber_sq()%>
		회원이름<%=vo.getNm()%>
		회원아이디
		<%=vo.getId()%>
		전화번호<%=vo.getTel()%>

		<form action="/member/updatepwdproc">
		
			재설정할 비밀번호<input type="password" id="pwd" name="pwd">
			<input name="nm" style="display: none;" value="<%=vo.getNm()%>">
			<input name="id" style="display: none;" value="<%=vo.getId()%>">
			<input name="tel" style="display: none;" value="<%=vo.getTel()%>">

			<button>회원 비밀번호 재설정</button>
		</form>


	</section>
</body>
</html>