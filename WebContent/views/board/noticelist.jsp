<%@page import="shop.kbgagu.www.common.Pagenation"%>
<%@page import="shop.kbgagu.www.vo.NoticeVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
ArrayList<NoticeVo> nar = (ArrayList<NoticeVo>) request.getAttribute("nar");
Pagenation pagenation = (Pagenation) request.getAttribute("pagenation");
String pagenum = request.getParameter("pn");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지글</title>
<link rel="stylesheet" href="/css/main.css" type="text/css">
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</head>
<body>

	<%@include file="/common/mainNav.jsp"%>

	<section>
		<div id ="noticeContainer">
		<h2>공지사항</h2>
		<br><br>
			<table class="table">
	
				<tr>
					<th>분류</th>
					<th>제목</th>
					<th>등록일</th>
					<th>조회수</th>
				</tr>
	
				<%
				for (int i = 0; i < nar.size(); i++) {
				%>
					<tr style="cursor: pointer;" onclick="location.href='/board/detail?sq=<%=nar.get(i).getNotice_sq()%>'" >
						<%if(nar.get(i).getNotice_ctgry()==1){%><td>[공지]</td><%}else if(nar.get(i).getNotice_ctgry()==2){ %><td>[이벤트]</td><%}%>
						<td><%=nar.get(i).getNotice_title()%></td>
						<td><%=nar.get(i).getNotice_dttm()%></td>
						<td><%=nar.get(i).getNotice_hit()%></td>
					</tr>
				<% } %>
	
			</table>
		</div>

	<div>
	<ul class="pagination justify-content-center">
	<% if (pagenation.getStartPageNumber() != 1) { %>
		<li class="page-item"><a class="page-link" href="/board/list?pn=<%=pagenation.getStartPageNumber() - 1%>"> prev </a></li>
	<% }%>
	<% for ( int i = pagenation.getStartPageNumber(); i <= pagenation.getEndPageNumber(); i++) {
		if (i != Integer.parseInt(pagenum)) {
		%> 
		<li class="page-item"><a class="page-link" href="/board/list?pn=<%=i%>"><%=i%></a></li>
		<% } else { %> 
		<li class="page-item disabled"><a class="page-link"><%=i%></a></li>
		<% }%>
		
	<%} %>
	<% if(pagenation.getTotalPageCount() != pagenation.getEndPageNumber()) { %>
	<li class="page-item"><a class="page-link" href="/board/list?pn=<%=pagenation.getEndPageNumber() + 1%>"> next </a></li>
	<% } %>
	</ul>
	</div>


	</section>

	<%@include file="/common/footer.jsp"%>


</body>
</html>