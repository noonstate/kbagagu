<%@page import="shop.kbgagu.www.common.Pagenation"%>
<%@page import="shop.kbgagu.www.vo.ItemVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Pagenation pagenation = (Pagenation) request.getAttribute("pagenation");
	ArrayList<ItemVo> iar = (ArrayList<ItemVo>) request.getAttribute("iar");
	String pagenum = request.getParameter("pn");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리페이지 - 상품관리</title>
<link rel="stylesheet" href="/css/admin.css" type="text/css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
<style>
	#thumbnail {
		width: 300px;
		height: 100px;
	}
	td {
	vertical-align: middle;
	}
	
</style>

</head>
<body>
	<%@include file ="/common/adminNav.jsp" %>
	<section>
	<button class="btn btn-primary" onclick="location.href='/admin/insertitem'">상품등록</button>
	<table class="table table-dark table-striped">
		<tr>
			<th>상품 번호</th>
			<th>카테고리</th>
			<th>상품명</th>
			<th>가격</th>
			<th>썸네일이미지</th>
			<th>할인율</th>
			<th>재고</th>
			<th>판매량</th>
			<th>등록관리자</th>
			<th>등록일</th>
			<th>관리</th>
		</tr>
		
		<%for(int i =0; i<iar.size(); i++){%>
			<tr>
				<td><%=iar.get(i).getItem_sq() %></td>
				<td><%=iar.get(i).getCtgry() %></td>
				<td><%=iar.get(i).getItem_nm() %></td>
				<td><%=iar.get(i).getPrice() %></td>
				<td><img id="thumbnail" src ="<%=iar.get(i).getImg_n0() %>"></td>
				<td><%=iar.get(i).getSale_rate() %></td>
				<td><%=iar.get(i).getInvntry() %></td>
				<td><%=iar.get(i).getSale_cnt() %></td>
				<td><%=iar.get(i).getAdmin_sq() %></td>
				<td><%=iar.get(i).getReg_date() %></td>
				<td><button class="btn btn-primary" onclick="location.href='/admin/updateitem?sq=<%=iar.get(i).getItem_sq()%>'">관리</button></td>
			</tr>
		<%}%>
	</table>
	
	<div>
	<ul class="pagination justify-content-center">
	<% if (pagenation.getStartPageNumber() != 1) { %>
		<li class="page-item"><a class="page-link" href="/admin/manageitem?pn=<%=pagenation.getStartPageNumber() - 1%>"> << </a></li>
	<% }%>
	<% for ( int i = pagenation.getStartPageNumber(); i <= pagenation.getEndPageNumber(); i++) {
		if (i != Integer.parseInt(pagenum)) {
		%> 
		<li class="page-item"><a class="page-link" href="/admin/manageitem?pn=<%=i%>"><%=i%></a></li>
		<% } else { %> 
		<li class="page-item disabled"><a class="page-link" ><%=i%></a></li>
		<% }%>
		
	<%} %>
	<% if(pagenation.getTotalPageCount() != pagenation.getEndPageNumber()) { %>
	<li class="page-item"><a class="page-link" href="/admin/manageitem?pn=<%=pagenation.getEndPageNumber() + 1%>"> >> </a></li>
	<% } %>
	</ul>
	</div>
	
	</section>
</body>
</html>