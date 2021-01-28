<%@page import="java.text.DecimalFormat"%>
<%@page import="shop.kbgagu.www.common.Pagenation"%>
<%@page import="shop.kbgagu.www.vo.ItemVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	DecimalFormat moneyformatter = new DecimalFormat("###,###");
	ArrayList<ItemVo> list = (ArrayList<ItemVo>) request.getAttribute("list");
	Pagenation pagenation = (Pagenation) request.getAttribute("pagenation");
	String pagenum = request.getParameter("pn");
	String ctgry = request.getParameter("ctgry");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/main.css" type="text/css">
<meta charset="UTF-8">
<title>가구 둘러보기</title>
<style>
    body {
        margin : 0 auto;
        
        text-align: center;
    }
    .navbar {
    	height: 50px;
        float: left;
        margin: 20px;
    }
    .navbar>a{
    	text-decoration: none;
    	background-color: brown;
    	color: white;
    	font-weight: bolder;	
    }
    .productlist {
        display: inline-block;
        position: relative;
    }
    
    .productlist>div {
        float: left;
        padding-left: 100px;
        padding-bottom: 20px;
    }
    .list {
        display: inline-block;
        position: relative;
    }
</style>
</head>
<body>
    <%@include file ="/common/mainNav.jsp" %>
    
    <nav>
        <div class="navbar">
            <a href="/shop/list?pn=1&ctgry=0">전체상품</a>
        </div>
        <div class="navbar">
            <a href="/shop/list?pn=1&ctgry=1">테이블</a>
        </div>
        <div class="navbar">
            <a href="/shop/list?pn=1&ctgry=2">의자</a>
        </div>
        <div class="navbar">
            <a href="/shop/list?pn=1&ctgry=3">주방가구</a>
        </div>
        <div class="navbar">
            <a href="/shop/list?pn=1&ctgry=4">침대</a>
        </div>
    </nav>
    <section class="list">
        
        <div class="productlist">
        
        <%for( int i = 0; i < list.size(); i++) { %>
            <div>
             <a href="/shop/productdetail?pd=<%=list.get(i).getItem_sq()%>">
                <img src="<%=list.get(i).getImg_n0()%>"><br>
                <span><%=list.get(i).getItem_nm() %></span>
             </a>
				<span><%=moneyformatter.format(list.get(i).getPrice())%></span>
            </div>
        <%} %>

        </div>
       <div>
	
	<% if (pagenation.getStartPageNumber() != 1) { %>
		<a href="/shop/list?pn=<%=pagenation.getStartPageNumber() - 1%>&ctgry=<%=ctgry%>"> <= </a>
	<% }%>
	<% for ( int i = pagenation.getStartPageNumber(); i <= pagenation.getEndPageNumber(); i++) {
		if (i != Integer.parseInt(pagenum)) {
		%> 
		<a href="/shop/list?pn=<%=i%>&ctgry=<%=ctgry%>"><%=i%></a>
		<% } else { %> 
		<%=i%>
		<% }%>
		
	<%} %>
	<% if(pagenation.getTotalPageCount() != pagenation.getEndPageNumber()) { %>
	<a href="/shop/list?pn=<%=pagenation.getEndPageNumber() + 1%>&ctgry=<%=ctgry%>"> => </a>
	<% } %>
	</div>
        </section>
	
   <%@include file ="/common/footer.jsp" %>
</body>
</html>