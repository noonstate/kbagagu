<%@page import="java.util.ArrayList"%>
<%@page import="shop.kbgagu.www.vo.ReviewVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<link rel="stylesheet" href="/css/main.css" type="text/css">
  <meta charset="UTF-8">

  <title>상품평 남기기</title>
  <meta name="viewport" content="width=device-width">
  
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<style>
    .all_star {
        width: 180px;
        height: 25px;
        margin: 10px;
        font-family: "Font Awesome 5 Free";
        font-weight: 900;
    }

    .all_star option {
        font-family: "Font Awesome 5 Free";
        font-weight: 900;
    }
</style>

</head>
<script>
	function register() {
		$('#form').submit();
	}
</script>

<body>
	<%@include file ="/common/mainNav.jsp" %>
	<section>
<h1>상품평 남기기</h1>

<form action="/mypage/writereviewproc?sq=<%=request.getParameter("sq") %>" method="post" id="form">
제목<input type ="text" name="title" size=65>
<br>
<br>
내용<textarea id="cntnt" name="cntnt" rows="10" cols="65"></textarea>

<br>
<br>
    <select class="all_star" name="star-input">
        <option selected="selected">상품 평가하기</option>
        <option class="star" value="5">&#xf005 &#xf005 &#xf005 &#xf005 &#xf005</option>
        <option class="star" value="4">&#xf005 &#xf005 &#xf005 &#xf005</option>
        <option class="star" value="3">&#xf005 &#xf005 &#xf005</option>
        <option class="star" value="2">&#xf005 &#xf005</option>
        <option class="star" value="1">&#xf005</option>
    </select>
	<br>
	<br>
</form>
	<button onclick = "register()">상품평 등록</button>
 	<button onclick = "location.href='/views/mypage/mypage.jsp'" id="back" name="back">뒤로가기</button>
 	</section>
 		<%@include file ="/common/footer.jsp" %>
</body>

</html>