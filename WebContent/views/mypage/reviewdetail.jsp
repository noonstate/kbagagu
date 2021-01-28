<%@page import="java.util.ArrayList"%>
<%@page import="shop.kbgagu.www.common.LoginManager"%>
<%@page import="shop.kbgagu.www.vo.ReviewVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
ReviewVo vo = (ReviewVo) request.getAttribute("vo");
%>
<head>
<meta charset="UTF-8">
<title>리뷰 확인</title>
<link rel="stylesheet" href="/css/main.css" type="text/css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
</head>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script type="text/javascript">
	function deleteReviewDetail() {
		var confirmDelete = confirm("해당 상품평을 삭제하시겠습니까?");
		if (confirmDelete == true) {
			alert("삭제 완료");
			$('#deleteReview').submit();
		} else if (confirmDelete == false) {
			alert("취소하셨습니다.");
			return;
		}
	}
</script>
<body>
<%@include file ="/common/mainNav.jsp" %>
<section>
	<%
	if (sessionId != null) {
	%>

	<table>
		<tr>
			<th>글 번호</th>
			<th>제목</th>
			<th>내용</th>
			<th>별점</th>
			<th>조회수</th>
			<th>글 작성시간</th>
		</tr>

		<tr>
			<td><%=vo.getReview_sq()%></td>
			<td><%=vo.getReview_title()%></td>
			<td><%=vo.getReview_cntnt()%></td>
			<td><%=vo.getStar_rating()%></td>
			<td><%=vo.getReview_hit()+1%></td>
			<td><%=vo.getReview_dttm().substring(0,16)%></td>
		</tr>
	</table>
	<!-- 버튼 온클릭때 review_sq 들고 들어가야함 -->
	<form action="/mypage/deletereviewdetailproc?sq=<%=vo.getReview_sq() %>" method="post"
		id="deleteReview">
		<input type="button" value="상품평 삭제" onclick="deleteReviewDetail();">
	</form>


	<%
	} else {
	%>

	<%
	out.println("<script>alert('로그인이 필요한 서비스입니다.'); location.href='/member/loginform';</script>");
	}
	%>
	</section>
	 <%@include file ="/common/footer.jsp" %>
</body>
</html>