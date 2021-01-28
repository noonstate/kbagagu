<%@page import="shop.kbgagu.www.common.Paser"%>
<%@page import="shop.kbgagu.www.vo.MemberVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shop.kbgagu.www.common.Pagenation"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Pagenation pagenation = (Pagenation) request.getAttribute("pagenation");
	ArrayList<MemberVo> mar = (ArrayList<MemberVo>) request.getAttribute("mar");
	String pagenum = request.getParameter("pn");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자페이지 - 회원관리</title>
<link rel="stylesheet" href="/css/admin.css" type="text/css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</head>
<body>
	
	<%@include file ="/common/adminNav.jsp" %>
	<section>
		<table class="table table-dark table-striped">
		<tr>
			<th>회원번호</th>
			<th>이름</th>
			<th>id</th>
			<th>전화번호</th>
			<th>탈퇴여부</th>
			<th>가입일</th>
			<th>관리</th>
		</tr>
			<%for(int i =0; i<mar.size(); i++){%>
			<tr>
				<td><%=mar.get(i).getMber_sq() %></td>
				<td><%=mar.get(i).getNm() %></td>
				<td><%=mar.get(i).getId() %></td>
				<td><%=mar.get(i).getTel() %></td>
				<td><%=Paser.boolToStr(mar.get(i).isDel_fl()) %></td>
				<td><%=mar.get(i).getDttm() %></td>
				<td><button class="btn btn-primary" onclick="location.href='/admin/updatemember?sq=<%=mar.get(i).getMber_sq()%>';">관리</button></td>
			</tr>
		<%}%>
		</table>
		
		<div>
	<ul class="pagination justify-content-center">
	<% if (pagenation.getStartPageNumber() != 1) { %>
		<li class="page-item"><a class="page-link" href="/admin/managemember?pn=<%=pagenation.getStartPageNumber() - 1%>"> << </a></li>
	<% }%>
	<% for ( int i = pagenation.getStartPageNumber(); i <= pagenation.getEndPageNumber(); i++) {
		if (i != Integer.parseInt(pagenum)) {
		%> 
		<li class="page-item"><a class="page-link" href="/admin/managemember?pn=<%=i%>"><%=i%></a></li>
		<% } else { %> 
		<li class="page-item disabled"><a class="page-link" ><%=i%></a></li>
		<% }%>
		
	<%} %>
	<% if(pagenation.getTotalPageCount() != pagenation.getEndPageNumber()) { %>
	<li class="page-item"><a class="page-link" href="/admin/managemember?pn=<%=pagenation.getEndPageNumber() + 1%>"> >> </a></li>
	<% } %>
	</ul>
	</div>
	</section>
</body>
</html>