<%@page import="shop.kbgagu.www.common.Paser"%>
<%@page import="shop.kbgagu.www.vo.QuestionVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shop.kbgagu.www.common.LoginManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ArrayList<QuestionVo> que = (ArrayList<QuestionVo>) request.getAttribute("que"); 
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/main.css" type="text/css">
<meta charset="UTF-8">
<title>나의 문의목록</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
	<style>
#quetionlist {
	text-align: center;
}

body {
	margin: 0 auto;
	text-align: center;
}

.navbar {
	float: left;
}

.question {
	display: inline-block;
	position: relative;
}

.question>div {
	float: left;
}

.list {
	display: inline-block;
	position: relative;
}

#questionTable {
	margin: 0 auto;
}
</style>
<script>
	function showContext(obj) {
		var tr = $(obj).next();
		tr.toggle();
		tr.next().toggle();
		tr.next().next().toggle();
		}

	function updateQue(index) {
		location.href="/mypage/updatequestion?sq=" + index;
		}
</script>
</head>
<body>

	<%@include file ="/common/mainNav.jsp" %>
	
	<%
		if (sessionId != null) {
	%>
	<div>
		<h1 id="questionList">문의 목록</h1>
	</div>

	<section class="menu">

		<table id="questionTable" border="1">
			<tr>
				<th>글 번호</th>
				<th>제목</th>
				<th>등록일시</th>
			</tr>
			<%
				for (int i = 0; i < que.size(); i++) {
			%>
			<tr onclick="showContext(this)" style="cursor:pointer;">
				<td><%=i+1%></td>
				<td><%=Paser.chgToHTML(que.get(i).getQues_title())%></td>
				<td><%=que.get(i).getQues_dttm().substring(0,16)%></td>
			</tr>
			
			<tr style="display: none;">
				<td colspan="2"><%=Paser.chgToHTML(que.get(i).getQues_cntnt())%></td>
				<td><button onclick="updateQue(<%=que.get(i).getQues_sq()%>)">수정</button></td>
			</tr>
			<% if (que.get(i).getAnswer() != null && !que.get(i).getAnswer().equals("")) {%>
			<tr style="display: none;">
				<th colspan="3">답글</th>
			</tr>
			<tr style="display: none;">
				<td colspan="3"><%=Paser.chgToHTML(que.get(i).getAnswer())%></td>
			</tr>
			<%
				} else { %>
			<tr style="display: none;">
				<th colspan="3">답글</th>
			</tr>
			<tr style="display: none;">
				<td colspan="3">잠시만 기다려주세요.</td>
			</tr>
				<% }
			}
			%>
		</table>
	<a href="/mypage/question">문의하기</a>
	<a href="/mypage">뒤로가기</a>
	</section>
	<%
		} else {
	%>
	<%
		out.println("<script>alert('로그인이 만료되었습니다.'); location.href='/member/loginform';</script>");
	}
	%>

	
	<%@include file ="/common/footer.jsp" %>
</body>
</html>




