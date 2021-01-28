<%@page import="shop.kbgagu.www.admin.common.LoginManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	LoginManager lm = LoginManager.getInstance();
	String sessionId = lm.getMemberId(session); 
%>

<%if(sessionId == null){ %>
<script>location.href='/views/admin/loginform.jsp';</script>
<%}%>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script>
	function list() {
		$('ul').toggle();
	}
</script>
<nav>
	<a href='/admin'>Dashboard</a>
	<a href='/admin/manageitem?pn=1'>상품관리</a>
	<a id="orderList" href='javascript:list()'>주문관리</a>
		<ul style="display: none;">
			<li><a href='/admin/manageorder?pn=1'>주문관리</a></li> <!-- css적용 -->
			<li><a href='/admin/managechange?pn=1'>교환/환불상품관리</a></li>
		</ul>
	<a href='/admin/managemember?pn=1'>회원관리</a>
	<a href='/admin/managequestion?pn=1'>문의관리</a>
	<a href='/admin/managenotice?pn=1'>공지관리</a>
	<a href='/admin/managereview?pn=1'>리뷰관리</a>

	
	<a href='/admin/logoutproc'>로그아웃</a>
</nav>