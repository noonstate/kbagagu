<%@page import="java.util.ArrayList"%>
<%@page import="shop.kbgagu.www.common.Paser"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="shop.kbgagu.www.vo.OrderVo"%>
<%@page import="shop.kbgagu.www.vo.ItemVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	DecimalFormat format = new DecimalFormat("###,###");
	ArrayList<ItemVo> itemList = (ArrayList<ItemVo>)request.getAttribute("itemList");	
	ArrayList<OrderVo> orderList = (ArrayList<OrderVo>)request.getAttribute("orderList");
	
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/main.css" type="text/css">
<meta charset="UTF-8">
<title>주문결과</title>
</head>
<body>
   <%@include file ="/common/mainNav.jsp" %>

    <section class="list">
    주문결과
   
    <table border="1">
    	<tr>
    		<td>상품이미지</td>
    		<td>주문상품명</td>
    		<td>개별가격</td>
    		<td>가격</td>
    		<td>수량</td>
    		<td>배송지</td>
    		<td>상세주소</td>
    		<td>상태</td>
    		<td>결제방법</td>
    		<td>요청사항</td>
    		<td>주문일자</td>
    	</tr>
    	<% for( int i =0; i < orderList.size(); i++) {%>
    	<tr>
    		<td><img src="<%=itemList.get(i).getImg_n0()%>"></td>
    		<td><%=itemList.get(i).getItem_nm() %></td>
    		<td><% out.print(format.format(itemList.get(i).getPrice())); %></td>
    		<td><% out.print(format.format(orderList.get(i).getQy() * (int) (itemList.get(i).getPrice() * (1 - (double) itemList.get(i).getSale_rate()/100)))); %></td>
    		<td><%=orderList.get(i).getQy() %></td>
    		<td><%=orderList.get(i).getAdres()%></td>
    		<td><%=orderList.get(i).getDetail_adres() + " " + orderList.get(i).getExtra_adres() %></td>
    		<td><%out.print(Paser.chgToSttus(orderList.get(i).getSttus()));%></td>
    		<td><%out.print(Paser.chgToPymnt(orderList.get(i).getPymnt()));%></td>
    		<td><%=orderList.get(i).getRequst()%></td>
    		<td><%=orderList.get(i).getReg_date()%></td>
    	</tr>
    	<%} %>
    </table>
    <button onclick="location.href='/shop/list'">상품페이지</button>
    </section>

    <%@include file ="/common/footer.jsp" %>
</body>
</html>