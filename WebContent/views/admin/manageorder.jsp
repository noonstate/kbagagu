<%@page import="shop.kbgagu.www.common.Pagenation"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="shop.kbgagu.www.common.Paser"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="shop.kbgagu.www.vo.OrderVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	ArrayList<OrderVo> list = (ArrayList<OrderVo>) request.getAttribute("order");
    	DecimalFormat format = new DecimalFormat("###,###");
    	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    	Pagenation pagenation = (Pagenation) request.getAttribute("pagenation");
    	String pagenum = request.getParameter("pn");
    	String category = (String) request.getAttribute("category");
    	String payment = (String) request.getAttribute("payment");
    %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/admin.css" type="text/css">
<meta charset="UTF-8">
<title>관리페이지 - 주문관리</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
<style>
	section {
		width: 74%;
		left:18%;
	}
</style>
<script>
	$(document).ready(function() {
		var category = <%=category%>
		var payment = <%=payment%>
		$('input:radio[name=category]:checked').removeAttr('checked');
		$('input:radio[name=category]').eq(category).attr('checked','checked');
		$('input:radio[name=payment]').eq(payment).attr('checked','checked');
		});
	function category() {
		var category = $('input:radio[name=category]:checked').val();
		if (category == null) {
			alert("카테고리를 선택하세요");
			return;
		}
		
		$('#categorySubmit').submit();
	}
	function updateOrderSttus() {
		$('#updateSttus').submit();
	}
 	function showContext(obj) {
		var th = $(obj).next();
		var tr = $(obj).next().next();
		th.toggle();
		tr.toggle();
	}
</script>
</head>
<body>
<%@include file ="/common/adminNav.jsp" %>
<section>
<h3>주문상태 조회</h3>
<div>
<span>주문상태 카테고리</span>
	<form action="/admin/manageorder?pn=1" method="post" id="categorySubmit">
		<label>
			<input type="radio" name="category" value="0" checked="checked"> 전체
		</label>
		<label>
			<input type="radio" name="category" value="1"> 결제중
		</label>
		<label>
			<input type="radio" name="category" value="2"> 결제완료
		</label>
		<label>
			<input type="radio" name="category" value="3"> 배송준비중
		</label>
		<label>
			<input type="radio" name="category" value="4"> 배송중
		</label>
		<label>
			<input type="radio" name="category" value="5"> 배송완료
		</label>
		<br>
		<label>
			<input type="radio" name="payment" value="0" checked="checked"> 전체
		</label>
		<label>
			<input type="radio" name="payment" value="1"> 무통장입금
		</label>
		<label>
			<input type="radio" name="payment" value="2"> 계좌이체
		</label>
		<label>
			<input type="radio" name="payment" value="3"> 카드결제
		</label>
	</form>
	<button class="btn btn-primary" onclick="category()">조회</button>
</div>

<table class="table table-dark table-striped table-hover">
	<tr>
		<th>주문번호</th>
		<th>상품이름</th>
		<th>할인률</th>
		<th>가격</th>
		<th>수량</th>
		<th>결제금액</th>
		<th>주문상태</th>
		<th>결제방법</th>
		<th>주문등록일</th>
		<th>관리기능</th>
	</tr>
	<% for (int i = 0; i < list.size(); i++) { %>
	<tr onclick="showContext(this)" style="cursor:pointer;">
		<td><%=list.get(i).getOrder_sq() %></td>
		<td><%=list.get(i).getItem_nm() %></td>
		<td><%=list.get(i).getSale_rate()%></td>
		<td><%=format.format(list.get(i).getPrice()) %></td>
		<td><%=list.get(i).getQy() %></td>
		<td><%=format.format(list.get(i).getQy() * (int) (list.get(i).getPrice() * (1 - (double) list.get(i).getSale_rate()/100))) %></td>
		<td><%=Paser.chgToSttus(list.get(i).getSttus()) %></td>
		<td><%=Paser.chgToPymnt(list.get(i).getPymnt()) %></td>
		<td><%=list.get(i).getReg_date().substring(0, 10) %></td>
		<td> 
		<%if (list.get(i).getSttus() < 3) { %>
		<form action="/admin/updatesttus?pn=<%=pagenation.getStartPageNumber()%>&category=<%=category%>&payment=<%=payment%>" method="post" id="updateSttus">
		<button class="btn btn-primary" onclick="updateOrderSttus()">배송준비중</button>
			<input type="hidden" name="order_sq" value="<%=list.get(i).getOrder_sq()%>">
		</form>
		<%} else { %>
		<span></span>
		<%} %>
		</td>
	</tr>
	<tr class="cntnt" style="display: none;">
		<th>주문자 성명</th>
		<th>주문자 전화번호</th>
		<th>우편번호</th>
		<th colspan="2">주문주소</th>
		<th colspan="2">상세주소</th>
		<th colspan="2">추가주소</th>
		<th>주문시 요청사항</th>
	</tr>
	<tr class="cntnt" style="display: none;">
		<td><%=list.get(i).getOrder_nm() %></td>
		<td><%=list.get(i).getOrder_tel() %></td>
		<td><%=list.get(i).getZip() %></td>
		<td colspan="2"><%=list.get(i).getAdres() %></td>
		<td colspan="2"><%=list.get(i).getDetail_adres() %></td>
		<td colspan="2"><%=list.get(i).getExtra_adres() %></td>
		<td><%=list.get(i).getRequst() %></td>
	</tr>
	<%} %>
</table>
		<div>
	<ul class="pagination justify-content-center">
	<% if (pagenation.getStartPageNumber() != 1) { %>
		<li class="page-item"><a class="page-link" href="/admin/manageorder?pn=<%=pagenation.getStartPageNumber() - 1%>&category=<%=category%>&payment=<%=payment%>"> << </a></li>
	<% }%>
	<% for ( int i = pagenation.getStartPageNumber(); i <= pagenation.getEndPageNumber(); i++) {
		if (i != Integer.parseInt(pagenum)) {
		%> 
		<li class="page-item"><a class="page-link" href="/admin/manageorder?pn=<%=i%>&category=<%=category%>&payment=<%=payment%>"><%=i%></a></li>
		<% } else { %> 
		<li class="page-item disabled"><a class="page-link" ><%=i%></a></li>
		<% }%>
		
	<%} %>
	<% if(pagenation.getTotalPageCount() != pagenation.getEndPageNumber()) { %>
	<li class="page-item"><a class="page-link" href="/admin/manageorder?pn=<%=pagenation.getEndPageNumber() + 1%>&category=<%=category%>&payment=<%=payment%>"> >> </a></li>
	<% } %>
	</ul>
	</div>
</section>
</body>
</html>