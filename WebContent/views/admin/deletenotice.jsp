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
<title>공지사항 삭제</title>
<link rel="stylesheet" href="/css/admin.css" type="text/css">
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
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
			<th>삭제</th>
		</tr>
		<form action="/admin/deletenoticeproc" id = "deletenoticeform">
		<%for(int i =0; i<nar.size(); i++){%> 
			<tr>
				<td><%=nar.get(i).getNotice_sq() %></td>
				<td><%=nar.get(i).getAdmin_sq() %></td>
				<td><%=nar.get(i).getNotice_ctgry() %></td>
				<td><%=nar.get(i).getNotice_title() %></td>				
				<td><%=nar.get(i).getNotice_hit() %></td>
				<td><%=nar.get(i).getNotice_dttm() %></td>
				<td><%=nar.get(i).isDel_fl() %></td>
				<%if(nar.get(i).isDel_fl()==false){ %>
				<td><input class = "deletecheckbox" type="checkbox" name="checkedSq" value="<%=nar.get(i).getNotice_sq()%>"></td>
				<%}%>
			</tr>
		<%}%>
		</form>
	</table>
	
	<div>
	
	<% if (pagenation.getStartPageNumber() != 1) { %>
		<a href="/admin/deletenotice?pn=<%=pagenation.getStartPageNumber() - 1%>"> <= </a>
	<% }%>
	<% for ( int i = pagenation.getStartPageNumber(); i <= pagenation.getEndPageNumber(); i++) {
		if (i != Integer.parseInt(pagenum)) {
		%> 
		<a href="/admin/deletenotice?pn=<%=i%>"><%=i%></a>
		<% } else { %> 
		<%=i%>
		<% }%>
		
	<%} %>
	<% if(pagenation.getTotalPageCount() != pagenation.getEndPageNumber()) { %>
	<a href="/admin/deletenotice?pn=<%=pagenation.getEndPageNumber() + 1%>"> => </a>
	<% } %>
	</div>
		<button onclick="deletenotices()">글삭제</button>
	</section>
	
	<script type="text/javascript">
		checkboxList = document.getElementsByName("checkedSq");
		
		function deletenotices(){
			var count = 0;
			for(var i = 0; i<checkboxList.length; i++){
				if(checkboxList[i].checked==true){
					count++
				}
			}
			if(count==0){
				alert('삭제할 게시글을 하나이상 선택하세요.');
				return
			}else{
				$('#deletenoticeform').submit();
			}
			
		}
	</script>
</body>
</html>