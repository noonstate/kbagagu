<%@page import="java.text.DecimalFormat"%>
<%@page import="shop.kbgagu.www.app.shop.vo.BasketProcVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
	ArrayList<BasketProcVo> list = (ArrayList<BasketProcVo>) request.getAttribute("basket");
	DecimalFormat format = new DecimalFormat("###,###");
%>
<html>
<head>
<link rel="stylesheet" href="/css/main.css" type="text/css">
<meta charset="UTF-8">
<title>장바구니</title>
	<script src="https://code.jquery.com/jquery-3.5.1.js"
		integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<script>
	function removeComma(str) {
		var temp = parseInt(str.replace(/,/g,""));

		return temp;
	}
	function numberWithCommas(x) {
	    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}

	function deleteBasket(obj, index) {
		var tr = $(obj).parent().parent();
		
		$.ajax({
			url: "/deletebasket.ajax",
			type: "post",
			dataType: "json",
			data: {
				basket_sq: index
			},
			error: function () {
				alert("통신실패");
			},
			success: function (data) {
				if (data.isTrue == 'true') {
					tr.remove();
				} else {
					return;
				}
			}
		});
		
	}

	function decreaseQy(index) {
		var qy = $(':focus').next().text();
		var price = $(':focus').parentsUntil("tbody").children().eq(6).text();
		var salePrice = $(':focus').parentsUntil("tbody").children().eq(7).text();
		price = removeComma(price);
		salePrice = removeComma(salePrice);
		
		qy = parseInt(qy) - 1;
		if (qy < 1) {
			qy = 1;
		}
		
		$(':focus').next().text(qy);
		
		qy = parseInt($(':focus').next().text());

		$.ajax({
			url: "/updateQy.ajax",
			type: "post",
			dataType: "json",
			data: {
				basket_sq: index,
				qy: qy
			},
			error: function () {
				alert("통신실패");
			}  
			
		});
		
		if (salePrice > 0) {
			price = (price - salePrice) * qy;
		} else {
			price = price * qy;
		}
		$(':focus').parentsUntil("tbody").children().eq(8).text(numberWithCommas(price));
		return;
	}

	function increaseQy(index) {
		var qy = parseInt($(':focus').prev().text());
		var price = $(':focus').parentsUntil("tbody").children().eq(6).text(); 
		var salePrice = $(':focus').parentsUntil("tbody").children().eq(7).text();
		price = removeComma(price);
		salePrice = removeComma(salePrice);
		
		qy = qy + 1;
		if (qy < 1) {
			qy = 1;
		}
		
		$(':focus').prev().text(qy);

		qy = parseInt($(':focus').prev().text());
		
		$.ajax({
			url: "/updateQy.ajax",
			type: "post",
			dataType: "json",
			data: {
				basket_sq: index,
				qy: qy
			},
			error: function () {
				alert("통신실패");
			}
			
		});
		
		if (salePrice > 0) {
			price = (price - salePrice) * qy;
		} else {
			price = price * qy;
		}
		$(':focus').parentsUntil("tbody").children().eq(8).text(numberWithCommas(price));
		return;
	}
	function orderInput() {
		if ($(':focus').is(':checked')) {
			$(':focus').next().prop('checked',true);
		} else {
			$(':focus').next().prop('checked',false);
		}
	}
	function order() {
		if ($('input:checkbox[name=order]:checked').val() == null) {
			alert('주문할 상품을 선택하세요');
			return;
		}
		$('#basketOrder').submit();
	}

</script>
</head>
<body>
	<%@include file ="/common/mainNav.jsp" %>

    <section class="list">
    장바구니
    <form action="/shop/multyorder" method="post" id=basketOrder > 
    <table border="1">
    	<tr>
    		<th>선택</th>
    		<th>대표이미지</th>
    		<th>상품이름</th>
    		<th>가격</th>
    		<th>할인가격</th>
    		<th>최종가격</th>
    		<th>수량</th>
    		<th>장바구니 등록일</th>
    		
    	</tr>
    <tbody id="content">
    
    	<%for(int i = 0; i < list.size(); i++) { %>
    	<tr>
    		<td><input type="checkbox" name="order" onclick="orderInput()" value="<%=list.get(i).getItem_sq()%>">
    		<input type="checkbox" style="display: none;" name="basketSq" value="<%=list.get(i).getBasket_sq()%>"></td>
	  		<td><img style="width: 100px;,height: 100px;" src="<%=list.get(i).getImg_n0()%>"/></td>
    		<td><%=list.get(i).getItem_nm() %></td>
    		<td><span><%=format.format(list.get(i).getPrice()) %></span></td>
    		<td><span><%=format.format((int) (list.get(i).getPrice() * (double) list.get(i).getSale_rate() / 100)) %></span></td>
    		<td><span><%=format.format(list.get(i).getQy() * (int) (list.get(i).getPrice() * (1 - (double) list.get(i).getSale_rate() / 100))) %></span></td>
    		<td><button type="button" onclick="decreaseQy(<%=list.get(i).getBasket_sq()%>)">-</button>
    			<span><%=list.get(i).getQy() %></span><button type="button" onclick="increaseQy(<%=list.get(i).getBasket_sq()%>)">+</button></td>
    		<td><%=list.get(i).getDttm() %></td>
    		<td><img src="/img/button.png" onclick="deleteBasket(this, <%=list.get(i).getBasket_sq()%>)"></td>
    	</tr>
    <%} %>
    </tbody>
    </table>
    </form> 
    <button onclick='location.href="/shop/list"'>계속 쇼핑하기</button>
    <button onclick='order()'>선택한 상품 결제하기</button>
    </section>

    <%@include file ="/common/footer.jsp" %>
</body>
</html>