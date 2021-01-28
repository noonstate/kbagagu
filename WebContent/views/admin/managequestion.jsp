<%@page import="shop.kbgagu.www.vo.QuestionVo"%>
<%@page import="shop.kbgagu.www.vo.ReviewVo"%>
<%@page import="shop.kbgagu.www.vo.NoticeVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shop.kbgagu.www.common.Pagenation"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Pagenation pagenation = (Pagenation) request.getAttribute("pagenation");
	ArrayList<QuestionVo> qar = (ArrayList<QuestionVo>) request.getAttribute("qar");
	String pagenum = request.getParameter("pn");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리페이지 - 문의 관리</title>
<link rel="stylesheet" href="/css/admin.css" type="text/css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</head>
<body>
	<%@include file ="/common/adminNav.jsp" %>
	
	<section>
			<table class="table table-dark table-striped"s>
		<tr>
			<th>문의번호</th>
			<th>문의회원</th>
			<th>제목</th>
			<th>답변상태</th>
			<th>문의일</th>
			<th>답변하기</th>
		</tr>
		
		<%for(int i =0; i<qar.size(); i++){%>
			<tr>
				<td><%=qar.get(i).getQues_sq() %></td>
				<td><%=qar.get(i).getId() %></td>
				<td><%=qar.get(i).getQues_title() %></td>
				<%if(qar.get(i).getAdmin_sq()==0){ %><td>미답변</td><%} else{%><td>답변완료</td><%}%>
				<td><%=qar.get(i).getQues_dttm().substring(0,16) %></td>
				<td><button class="btn btn-primary" onclick="location.href='/admin/updateanswer?sq=<%=qar.get(i).getQues_sq() %>'">답변</button></td>
			</tr>
		<%}%>
	</table>
	
	<div>
	<ul class="pagination justify-content-center">
	<% if (pagenation.getStartPageNumber() != 1) { %>
		<li class="page-item"><a class="page-link" href="/admin/managequestion?pn=<%=pagenation.getStartPageNumber() - 1%>"> << </a></li>
	<% }%>
	<% for ( int i = pagenation.getStartPageNumber(); i <= pagenation.getEndPageNumber(); i++) {
		if (i != Integer.parseInt(pagenum)) {
		%> 
		<li class="page-item"><a class="page-link" href="/admin/managequestion?pn=<%=i%>"><%=i%></a></li>
		<% } else { %> 
		<li class="page-item disabled"><a class="page-link" ><%=i%></a></li>
		<% }%>
		
	<%} %>
	<% if(pagenation.getTotalPageCount() != pagenation.getEndPageNumber()) { %>
	<li class="page-item"><a class="page-link" href="/admin/managequestion?pn=<%=pagenation.getEndPageNumber() + 1%>"> >> </a></li>
	<% } %>
	</ul>
	</div>
		
	</section>
</body>
</html>