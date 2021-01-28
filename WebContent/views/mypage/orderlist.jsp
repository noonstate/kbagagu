<%@page import="shop.kbgagu.www.common.Pagenation"%>
<%@page import="shop.kbgagu.www.common.Paser"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="shop.kbgagu.www.vo.OrderVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	    Pagenation pagenation = (Pagenation) request.getAttribute("pagenation");
    	ArrayList<OrderVo> orderList = (ArrayList<OrderVo>) request.getAttribute("order");
    	DecimalFormat format = new DecimalFormat("###,###");
    	String pagenum = request.getParameter("pn");
    %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="/css/main.css" type="text/css">
<meta charset="UTF-8">
<title>나의 주문</title>
<style>
	tr {
	height: 40px;
	}
	td {
	text-align: center;
	}
</style>
</head>
<script>
	function sttusChange(sttus, obj) {
		var temp = $(obj).parent();
		temp.find('input').val(sttus);
		temp.submit();
		}
</script>
<body>
<%@include file ="/common/mainNav.jsp" %>
<section>

<h1>나의 주문보기</h1>
<table>
	<tr>
		<td>순번</td>
		<td>상품이미지</td>
		<td>상품명</td>
		<td>할인률</td>
		<td>가격</td>
		<td>할인가격</td>
		<td>최종금액</td>
		<td>수량</td>
		<td>주문자 성명</td>
		<td>우편번호</td>
		<td>주소</td>
		<td>상세주소</td>
		<td>추가주소</td>
		<td>주문시 요청사항</td>
		<td>주문일시</td>
		<td>제품상태</td>
		<td colspan="2">주문관리</td>
	</tr>
	<%for(int i = 0; i < orderList.size(); i++) {%>
	<tr>
		<td><%=orderList.get(i).getOrder_sq() %></td>
		<td><img style="width: 100px;, height: 100px;" src = "<%=orderList.get(i).getImg_n0()%>"></td>
		<td><%=orderList.get(i).getItem_nm() %></td>
		<td><%=orderList.get(i).getSale_rate() %>%</td>
		<td><%=format.format(orderList.get(i).getQy() * orderList.get(i).getPrice()) %></td>
		<td><%=format.format(orderList.get(i).getQy() * (int) orderList.get(i).getPrice() * ((double) orderList.get(i).getSale_rate() / 100))%></td>
		<td><% out.print(format.format(orderList.get(i).getQy() * (int) orderList.get(i).getPrice() * ( 1 - (double) orderList.get(i).getSale_rate() / 100))); %></td> 
		<td><%=orderList.get(i).getQy()%></td>
		<td><%=orderList.get(i).getOrder_nm()%></td>
		<td><%=orderList.get(i).getZip() %></td>
		<td><%=orderList.get(i).getAdres() %></td>
		<td><%=orderList.get(i).getDetail_adres() %></td>
		<td><%=orderList.get(i).getExtra_adres() %></td>
		<td><%=orderList.get(i).getRequst() %></td>
		<td><%=orderList.get(i).getReg_date().substring(0,16) %></td>
		<td><%=Paser.chgToSttus(orderList.get(i).getSttus()) %></td>
		<td>
		<% switch(orderList.get(i).getSttus()) {  
		case 1:%>
		<form action="/mypage/ordersttuschange?order_sq=<%=orderList.get(i).getOrder_sq()%>" method="post" >
			<input type="hidden" id="sttus" name="sttus">
			<button onclick="sttusChange(-1, this)">결제취소</button>
		</form>
		<% break;
			case 2: case -4: case -5:%>
			<form action="/mypage/ordersttuschange?order_sq=<%=orderList.get(i).getOrder_sq()%>" method="post" >
			<input type="hidden" id="sttus" name="sttus">
			<button onclick="sttusChange(-2, this)">환불신청</button>
		</form>
		<% break;
			case 5: %>
			<form action="/mypage/ordersttuschange?order_sq=<%=orderList.get(i).getOrder_sq()%>" method="post" >
			<input type="hidden" id="sttus" name="sttus">
			<button onclick="sttusChange(-4, this)">교환신청</button>
			<button onclick="sttusChange(-2, this)">환불신청</button>
		</form>	
		<% break; } %>
		</td>
		<td><button onclick = "location.href='/mypage/writereview?sq=<%=orderList.get(i).getItem_sq()%>'">제품리뷰작성</button></td>
	</tr>
	<%} %>
</table>
		<div>
	
	<% if (pagenation.getStartPageNumber() != 1) { %>
		<a href="/mypage/order?pn=<%=pagenation.getStartPageNumber() - 1%>"> <= </a>
	<% }%>
	<% for ( int i = pagenation.getStartPageNumber(); i <= pagenation.getEndPageNumber(); i++) {
		if (i != Integer.parseInt(pagenum)) {
		%> 
		<a href="/mypage/order?pn=<%=i%>"><%=i%></a>
		<% } else { %> 
		<%=i%>
		<% }%>
		
	<%} %>
	<% if(pagenation.getTotalPageCount() != pagenation.getEndPageNumber()) { %>
	<a href="/mypage/order?pn=<%=pagenation.getEndPageNumber() + 1%>"> => </a>
	<% } %>
	</div>
</section>
	<%@include file ="/common/footer.jsp" %>
</body>
</html>