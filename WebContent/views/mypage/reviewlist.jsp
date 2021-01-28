<%@page import="shop.kbgagu.www.common.Paser"%>
<%@page import="shop.kbgagu.www.common.Pagenation"%>
<%@page import="shop.kbgagu.www.vo.ReviewVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shop.kbgagu.www.common.LoginManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Pagenation pagenation = (Pagenation) request.getAttribute("pagenation");
	String pagenum = request.getParameter("pn");
	ArrayList<ReviewVo> rar = (ArrayList<ReviewVo>) request.getAttribute("rar"); // ReviewVo에서 Vo값 가져옴
	ReviewVo vo = (ReviewVo) request.getAttribute("vo");
%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/main.css" type="text/css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<meta charset="UTF-8">
<title>내가 남긴 리뷰</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script>
	
</script>
<style>
    .all_star {
        width: 180px;
        height: 25px;
        margin: 10px;
        font-family: "Font Awesome 5 Free";
        font-weight: 900;
    }
#productReview {
	text-align: center;
}

.navbar {
	float: left;
}

.productlist {
	display: inline-block;
	position: relative;
}

.productlist>div {
	float: left;
}

.list {
	display: inline-block;
	position: relative;
}

#reviewTable {
	margin: 0 auto;
}

#Pnation {
	text-align : center;
	padding : 50px;
}

#list {
	text-align : center;
}
</style>
</head>
<body>
	<%@include file ="/common/mainNav.jsp" %>

	<%
		if (sessionId != null) {
	%>
	<header>
		<h1 id="productReview">제품 리뷰</h1>
	</header>

	<section class="menu">
		<table id="reviewTable">
		
			<tr>
				<th>글 번호</th>
				<th>상품 번호</th>
				<th>제목</th>
				<th>조회수</th>
				<th>별점</th>
				<th>등록일시</th>
			</tr>

			<%
				for (int i = 0; i < rar.size(); i++) {
			%>
			<!-- tr에 온클릭 걸어서 글 클릭하면 해당 상품평 보는 페이지로 이동 -->
			<tr style="cursor:pointer;" 
			onClick="location.href='/mypage/reviewdetail?sq=<%=rar.get(i).getReview_sq() %>'" title="상품평 보기"> <!-- 컨트롤러 경로로 적어야함 -->
				<td id = "list"><%=rar.get(i).getReview_sq()%></td>
				<td id = "list"><%=rar.get(i).getItem_sq() %></td>
				<td id = "list"><%=rar.get(i).getReview_title()%></td>
				<td id = "list"><%=rar.get(i).getReview_hit()%></td>
				<td id = "list" class="all_star"><%=Paser.reviewToStar(rar.get(i).getStar_rating())%></td>
				<td id = "list"><%=rar.get(i).getReview_dttm().substring(0,16)%></td>
			</tr>
			<%
				}
			%>
		</table>

	<div id = "Pnation">
	
	<% if (pagenation.getStartPageNumber() != 1) { %>
		<a href="/mypage/reviewlist?pn=<%=pagenation.getStartPageNumber() - 1%>"> <= </a>
	<% }%>
	<% for ( int i = pagenation.getStartPageNumber(); i <= pagenation.getEndPageNumber(); i++) {
		if (i != Integer.parseInt(pagenum)) {
		%> 
		<a href="/mypage/reviewlist?pn=<%=i%>"><%=i%></a>
		<% } else { %> 
		<%=i%>
		<% }%>
		
	<%} %>
	<% if(pagenation.getTotalPageCount() != pagenation.getEndPageNumber()) { %>
	<a href="/mypage/reviewlist?pn=<%=pagenation.getEndPageNumber() + 1%>"> => </a>
	<% } %>
	</div>

	

	</section>

	



	<%
		} else {
	%>
	<%
		out.println("<script>alert('로그인이 만료되었습니다.'); location.href='/member/loginform';</script>"); }
	%>

	<%@include file ="/common/footer.jsp" %>
</body>
</html>