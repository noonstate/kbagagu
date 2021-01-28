<%@page import="shop.kbgagu.www.app.shop.vo.BasketProcVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="shop.kbgagu.www.vo.OrderVo"%>
<%@page import="shop.kbgagu.www.vo.MemberVo"%>
<%@page import="shop.kbgagu.www.vo.ItemVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	DecimalFormat format = new DecimalFormat("###,###");				// 가격 포맷 ex) 100,000
	ItemVo itemVo = (ItemVo) request.getAttribute("itemVo"); 			// 상품정보
	MemberVo memberVo = (MemberVo) request.getAttribute("memberVo");	// 회원정보
	OrderVo orderVo = (OrderVo) request.getAttribute("orderVo");		// 주문정보
	ArrayList<BasketProcVo> basketList = (ArrayList<BasketProcVo>) request.getAttribute("basketList"); // 장바구니
	String strQy = (String) request.getAttribute("qy");
	int sumPrice = 0;
	int sumSalePrice = 0;
	int sumFinalPrice = 0;
%>
<html>
<head>
<link rel="stylesheet" href="/css/main.css" type="text/css">
<meta charset="UTF-8">
<title>주문하기</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function order() {
		var zip = $('#zip')
		var address = $('#address');
		var detailAddress = $('#detailAddress');
		var extraAddress = $('#extraAddress');
		var tel = $('#tel');
		if (!zip.val() && !address.val()) {
			alert('배송지 주소를 입력하세요.');
			return;	
		}
		
		if ($('input:radio[name="payment"]').is('checked')) {
			alert('결제수단을 선택해주세요.');
			return;
		}
		
		var regExpTel = /[0-1]{2}[016789]+[0-9]{7,8}/;
		if (tel.val().match(regExpTel) == null) {
			alert("올바른 전화번호를 입력하세요.");
			$('#tel').val('');
			$('#tel').focus();
			return;
		}
		$('#orderAction').submit();
	}
</script>
</head>
<body>
    <%@include file ="/common/mainNav.jsp" %>

    <section class="list">
		<form action="/shop/orderresult?sq=<%=memberVo.getMber_sq()%>" method="post" id="orderAction">
    <div>
		<span>결제정보 입력<br></span> 
			성명 <input type="text" name="orderNm" id="orderNm" value="<%=memberVo.getNm()%>"><br>
			
			<input type="text" name="zip" id="postcode" placeholder="우편번호" >
			<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
			<input type="text" name="address" id="address" placeholder="주소"><br>
			<input type="text" name="detailAddress" id="detailAddress" placeholder="상세주소">
			<input type="text" name="extraAddress" id="extraAddress" placeholder="추가주소"> <br>
			<div id="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 0;position:relative">
			<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" 
			style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
			</div>
			전화번호 <input type="text" name="tel" id="tel" value="<%=memberVo.getTel()%>">
			<div>
				<span>결제방법</span>
				<ul style="list-style: none;"> 
					<li>
					<input type="radio" id="acount" name="payment" value="1">
					<label for="acount">계좌이체</label>
					</li>
					<li>
					<input type="radio" id="atm" name="payment" value="2">
					<label for="atm">무통장입금</label>
					</li>
					<li>
					<input type="radio" id="card" name="payment" value="3">
					<label for="card">카드결제</label>
					</li>
				</ul>
			</div>
			<div> 배송시 요청사항 <input type="text" name="request" placeholder="최대 50자 까지 입력가능합니다.">
			</div>
			
	</div>
	<% if (request.getAttribute("qy") != null) { 
		int qy = Integer.parseInt((String) request.getAttribute("qy"));%>
	<div>
		주문상품 정보
	    <img src="<%=itemVo.getImg_n0()%>">
	    <span><%=itemVo.getItem_nm() %></span>
	    <input type="hidden" name="qy" value="<%=strQy %>">
	    <input type="hidden" name="item_sq" value="<%=itemVo.getItem_sq() %>">
	</div>
    <div>
    	최종결제 정보
    	수량 : <span><%=qy %></span>
    	상품금액 : <span><% out.print(format.format(itemVo.getPrice()));%></span>
    	할인금액 : <span><% out.print(format.format((int) (itemVo.getPrice() * ((double) itemVo.getSale_rate()/100))));%></span>
    	최종금액 : <span><% out.print(format.format(qy * (int) (itemVo.getPrice() * (1 - (double) itemVo.getSale_rate()/100)))); %></span>
    </div>
    <% } else { %>
    <div>
		주문상품 정보
    <% for(int i = 0; i < basketList.size(); i++) { %>
	    <img src="<%=basketList.get(i).getImg_n0()%>"> <input type="hidden" name="basket_sq" value=<%=basketList.get(i).getBasket_sq() %>>
	    <span><%=basketList.get(i).getItem_nm() %></span> <input type="hidden" name="item_sq" value=<%=basketList.get(i).getItem_sq() %>>
	    수량 : <span><%=basketList.get(i).getQy() %></span>
    	상품금액 : <span><% out.print(format.format(basketList.get(i).getPrice()));%></span> <input type="hidden" name="qy" value=<%=basketList.get(i).getQy() %>>
    	할인금액 : <span><% out.print(format.format((int) (basketList.get(i).getPrice() * ((double) basketList.get(i).getSale_rate()/100))));%></span>
    	최종금액 : <span><% out.print(format.format(basketList.get(i).getQy() * (int) (basketList.get(i).getPrice() * (1 - (double) basketList.get(i).getSale_rate()/100)))); %></span>
	<%} %>	    
	</div>
    <div> <% for(int i =0; i < basketList.size(); i++)	{
    			sumPrice += basketList.get(i).getQy() * basketList.get(i).getPrice();
    			sumSalePrice += basketList.get(i).getQy() * ((int) (basketList.get(i).getPrice() * ((double) basketList.get(i).getSale_rate()/100)));
    			sumFinalPrice += basketList.get(i).getQy() * ((int) (basketList.get(i).getPrice() * (1 - (double) basketList.get(i).getSale_rate()/100)));
    		} %>
    	최종결제 정보
   	 	상품금액 : <span><%=format.format(sumPrice)%>원</span>
    	할인금액 : <span><%=format.format(sumSalePrice) %>원</span>
    	결제금액 : <span><%=format.format(sumFinalPrice) %>원</span>
    </div>
    <%} %>
    	</form>
    	<button onclick="order()">결제하기</button>
    </section>

    <%@include file ="/common/footer.jsp" %>
    
    
   <script>
    // 우편번호 찾기 찾기 화면을 넣을 element
    var element_wrap = document.getElementById('wrap');
    
    function foldDaumPostcode() {
        // iframe을 넣은 element를 안보이게 한다.
        element_wrap.style.display = 'none';
    }

    function execDaumPostcode() {
        // 현재 scroll 위치를 저장해놓는다.
        var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
        new daum.Postcode({
            oncomplete: function(data) {
                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postcode').value = data.zonecode;
                document.getElementById("address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("detailAddress").focus();

                // iframe을 넣은 element를 안보이게 한다.
                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                element_wrap.style.display = 'none';

                // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
                document.body.scrollTop = currentScroll;
            },
            // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
            onresize : function(size) {
                element_wrap.style.height = size.height+'px';
            },
            width : '100%',
            height : '100%'
        }).embed(element_wrap);

        // iframe을 넣은 element를 보이게 한다.
        element_wrap.style.display = 'block';
    }
</script>
</body>
</html>