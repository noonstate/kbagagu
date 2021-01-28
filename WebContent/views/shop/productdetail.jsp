<%@page import="shop.kbgagu.www.common.Paser"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shop.kbgagu.www.vo.QuestionVo"%>
<%@page import="shop.kbgagu.www.vo.ReviewVo"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="shop.kbgagu.www.common.LoginManager"%>
<%@page import="shop.kbgagu.www.vo.ItemVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	ItemVo itemVo = (ItemVo) request.getAttribute("item");
	ArrayList<ReviewVo> review = (ArrayList<ReviewVo>) request.getAttribute("review");
	String pd = (String) request.getParameter("pd");
	DecimalFormat moneyformatter = new DecimalFormat("###,###");
	
%>
<html>
<head>
<link rel="stylesheet" href="/css/main.css" type="text/css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<meta charset="UTF-8">
<title>상품상세보기</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
			    <style>
        /* The Modal (background) */
        .searchModal {
        display: none; /* Hidden by default */
        position: fixed; /* Stay in place */
        z-index: 10; /* Sit on top */
        left: 0;
        top: 0;
        width: 100%; /* Full width */
        height: 100%; /* Full height */
        overflow: auto; /* Enable scroll if needed */
        background-color: rgb(0,0,0); /* Fallback color */
        background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
        }
        /* Modal Content/Box */
        .search-modal-content {
        background-color: #fefefe;
        margin: 15% auto; /* 15% from the top and centered */
        padding: 20px;
        border: 1px solid #888;
        width: 25%; /* Could be more or less, depending on screen size */
        }
        </style>
	<style>
    .all_star {
        width: 180px;
        height: 25px;
        margin: 10px;
        font-family: "Font Awesome 5 Free";
        font-weight: 900;
    }

</style>
	
<script>
function showModal() {
    $("#modal").show();
}

	function closeModal() {
   		$('.searchModal').hide();
	}

	
	function basketAdd(session) {
		var session = session;
		var itemSq = <%=pd%>
		var qy = $('#qy').val();
		if (qy == "") {
			qy = 1;
		}
		
		if (session != null) {
			 $.ajax({
				url: "/addbasket.ajax",
				type: "post",
				dataType: "json",
				data: {
					qy: qy,
					session: session,
					itemSq: itemSq
				},
				error: function () {
					alert("통신실패");
				},
				success: function (data) {
					if (data.isTrue != 'true') {
						alert('장바구니 저장에 실패했습니다.');
						return;
					} else { /* 차후 모달창 팝업으로 */
						showModal();
					}
				}
			}); 
			
			
		} else {
			alert("로그인후 사용가능한 기능입니다.");
			location.href='/member/loginform';
		}
	}
	
	function order(session) {
		var qy = $('#qy').val();
		if (qy == "") {
			qy = 1;
		}
		if (session != null) {
			location.href='/shop/orderview?qy='+ qy +'&pd=<%=itemVo.getItem_sq()%>';
		} else {
			alert("로그인후 주문해주세요.");
			location.href='/member/loginform';
		}
	}

 	function showContext(obj) {
		var tr = $(obj).next();
			tr.toggleClass('cntnt');
		/* if (tr.is(':visible')) {
		} else {
			tr.show();
		} */
	}
</script>
<style>
    body {
        margin : 0 auto;
        text-align: center;
    }
    .navbar {
        float: left;
    }
    .cntnt {
    	display: none;
    }
</style>

</head>

<body>
    <%@include file ="/common/mainNav.jsp" %>
    
    <section>
        <div>
            <div>
                <img src="<%=itemVo.getImg_n0()%>">
            </div>
            <div>
               <span> <%=itemVo.getItem_nm()%></span>
               <span> 가격 : <%=moneyformatter.format(itemVo.getPrice()) %> </span>
            </div>
            <div>
               수량선택 : <input type="text" id="qy" name="qy" placeholder="입력하지 않으면 1개입니다.">
          
            </div>
            <div>
                <button onclick="order(<%=lm.getMemberId(session)%>)">주문하기</button>
                <button onclick="basketAdd(<%=lm.getMemberId(session)%>)">장바구니담기</button>
            </div>
            
        </div>
        <div>
            제품상세정보
            <%=itemVo.getDetail() %>
            
        </div>
        <div>
            리뷰
            <div style="display: inline-block;">
            <button onclick="location.href='/mypage/writereview?sq=<%=itemVo.getItem_sq()%>'">리뷰쓰기</button>
            </div>
            <br>
            <div style="display: inline-block;">
            <table border="1"> 
                <tr>
                    <th>별점</th>
                    <th>리뷰</th>
                    <th>작성자</th>
                </tr>
            <tbody id="review">
            <% for(int i =0; i < review.size(); i++) { %>
                <tr class="tr" onclick="showContext(this)" style="cursor:pointer;">
                    <td class="all_star"><%=Paser.reviewToStar(review.get(i).getStar_rating()) %></td>
                    <td><%=review.get(i).getReview_title() %></td>
                    <td><%=review.get(i).getNm() %></td>
                </tr>
               	<tr class="cntnt">
               	<td colspan="3"><%=review.get(i).getReview_cntnt() %></td>
               	</tr>
            <%} %>
            </tbody>
            </table>
            </div>
        </div>

    </section>
        <div id="modal" class="searchModal">
        <div class="search-modal-content">
        	<div>
        		<h2>장바구니에 추가되었습니다.</h2>
        	</div>
            <div style="cursor:pointer;background-color:#DDDDDD;text-align: center;padding-bottom: 10px;padding-top: 10px;"
                onClick="closeModal()">
                <span class="pop_bt modalCloseBtn" style="font-size: 13pt;">계속쇼핑</span>
            </div>
            <hr>
            <div style="cursor:pointer;background-color:#DDDDDD;text-align: center;padding-bottom: 10px;padding-top: 10px;"
                onClick="location.href='/shop/basketview'">
                <span class="pop_bt modalCloseBtn" style="font-size: 13pt;">장바구니로 이동</span>
            </div>
        </div>
    </div>
	<%@include file ="/common/footer.jsp" %>

</body>
</html>