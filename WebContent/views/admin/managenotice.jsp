<%@page import="shop.kbgagu.www.common.Paser"%>
<%@page import="shop.kbgagu.www.vo.NoticeVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shop.kbgagu.www.common.Pagenation"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Pagenation pagenation = (Pagenation) request.getAttribute("pagenation");
	ArrayList<NoticeVo> nar = (ArrayList<NoticeVo>) request.getAttribute("nar");
	String pagenum = request.getParameter("pn");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 관리</title>
<link rel="stylesheet" href="/css/admin.css" type="text/css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</head>
<body>
	<%@include file ="/common/adminNav.jsp" %>
	
	<section>
		
			<table class="table table-dark table-striped">
		<tr>
			<th>공지번호</th>
			<th>작성관리자</th>
			<th>카테고리</th>
			<th>공지제목</th>
			<th>조회수</th>
			<th>등록시간</th>
			<th>삭제여부</th>
			<th>수정</th>
		</tr>
		
		<%for(int i =0; i<nar.size(); i++){%>
			<tr>
				<td><%=nar.get(i).getNotice_sq() %></td>
				<td><%=nar.get(i).getAdmin_sq() %></td>
				<td><%=nar.get(i).getNotice_ctgry() %></td>
				<td><%=nar.get(i).getNotice_title() %></td>				
				<td><%=nar.get(i).getNotice_hit() %></td>
				<td><%=nar.get(i).getNotice_dttm().substring(0,16) %></td>
				<td><%=Paser.boolToStr(nar.get(i).isDel_fl()) %></td>
				<td><button class="btn btn-primary" onclick="location.href='/admin/updatenotice?sq=<%=nar.get(i).getNotice_sq()%>'">수정</button></td>
			</tr>
		<%}%>
	</table>
	
	<div>
	<ul class="pagination justify-content-center">
		<% if (pagenation.getStartPageNumber() != 1) { %>
		<li class="page-item"><a class="page-link" href="/admin/managenotice?pn=<%=pagenation.getStartPageNumber() - 1%>"> << </a></li>
	<% }%>
	<% for ( int i = pagenation.getStartPageNumber(); i <= pagenation.getEndPageNumber(); i++) {
		if (i != Integer.parseInt(pagenum)) {
		%> 
		<li class="page-item"><a class="page-link" href="/admin/managenotice?pn=<%=i%>"><%=i%></a></li>
		<% } else { %> 
		<li class="page-item disabled"><a class="page-link" ><%=i%></a></li>
		<% }%>
		
	<%} %>
	<% if(pagenation.getTotalPageCount() != pagenation.getEndPageNumber()) { %>
	<li class="page-item"><a class="page-link" href="/admin/managenotice?pn=<%=pagenation.getEndPageNumber() + 1%>"> >> </a></li>
	<% } %>
	</ul>

	</div>
		
		<button class="btn btn-primary" onclick="location.href='/admin/insertnotice'">공지등록</button>
		<button class="btn btn-primary" onclick="location.href='/admin/deletenotice?pn=1'">공지삭제</button>
	</section>
	
	
</body>
</html>