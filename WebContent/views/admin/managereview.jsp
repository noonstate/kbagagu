<%@page import="shop.kbgagu.www.vo.ReviewVo"%>
<%@page import="shop.kbgagu.www.vo.NoticeVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shop.kbgagu.www.common.Pagenation"%>
<%@page import="shop.kbgagu.www.common.Paser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Pagenation pagenation = (Pagenation) request.getAttribute("pagenation");
	ArrayList<ReviewVo> rar = (ArrayList<ReviewVo>) request.getAttribute("rar");
	String pagenum = request.getParameter("pn");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리페이지 - 리뷰 관리</title>
<link rel="stylesheet" href="/css/admin.css" type="text/css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
	<style>
		td {
		vertical-align: middle;
		}
	</style>
<script>
	function showContext(obj) {
		var tr = $(obj).next();
			tr.toggle();
	}
	</script>
</head>
<body>
	<%@include file ="/common/adminNav.jsp" %>
	
	<section>
		
			<table class="table table-dark table-striped table-hover">
		<tr>
			<th>리뷰번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>별점</th>
			<th>작성일</th>
			<th>삭제여부</th>
			<th>삭제/복구</th>
		</tr>
		
		<%for(int i =0; i<rar.size(); i++){%>
			<tr onclick="showContext(this)" style="cursor:pointer;">
				<td><%=rar.get(i).getReview_sq() %></td>
				<td><%=rar.get(i).getNm()%></td>
				<td><%=rar.get(i).getReview_title() %></td>
				<td><%=rar.get(i).getStar_rating() %></td>				
				<td><%=rar.get(i).getReview_dttm().substring(0,16) %></td>
				<td><%=Paser.boolToStr(rar.get(i).isDel_fl()) %></td>
				<%if(rar.get(i).isDel_fl()){%>
				<td><button class="btn btn-primary" onclick="location.href='/admin/updatereview?sq=<%=rar.get(i).getReview_sq()%>'">복구</button></td>
				<%}else{ %>
				<td><button class="btn btn-primary" onclick="location.href='/admin/updatereview?sq=<%=rar.get(i).getReview_sq()%>'">삭제</button></td>
				<%} %>
			</tr>
			<tr style="display: none;">
				<td colspan="8"><%=rar.get(i).getReview_cntnt() %></td>
			</tr>
		<%}%>
	</table>
	
	
	<div>
	<ul class="pagination justify-content-center">
	<% if (pagenation.getStartPageNumber() != 1) { %>
		<li class="page-item"><a class="page-link" href="/admin/managereview?pn=<%=pagenation.getStartPageNumber() - 1%>"> << </a></li>
	<% }%>
	<% for ( int i = pagenation.getStartPageNumber(); i <= pagenation.getEndPageNumber(); i++) {
		if (i != Integer.parseInt(pagenum)) {
		%> 
		<li class="page-item"><a class="page-link" href="/admin/managereview?pn=<%=i%>"><%=i%></a></li>
		<% } else { %> 
		<li class="page-item disabled"><a class="page-link" ><%=i%></a></li>
		<% }%>
		
	<%} %>
	<% if(pagenation.getTotalPageCount() != pagenation.getEndPageNumber()) { %>
	<li class="page-item"><a class="page-link" href="/admin/managereview?pn=<%=pagenation.getEndPageNumber() + 1%>"> >> </a></li>
	<% } %>
	</ul>
	</div>
		
	</section>
</body>
</html>