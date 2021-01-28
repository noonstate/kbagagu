<%@page import="shop.kbgagu.www.vo.ItemVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
	ItemVo vo = (ItemVo) request.getAttribute("vo");
	String sq = request.getParameter("sq");
%>
<html>
<head>
<link rel="stylesheet" href="/css/admin.css" type="text/css">
	<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
	
	<meta charset="UTF-8">
	<title>상품 관리(수정, 삭제) 페이지</title>
    <style>
        .header,
        .container {
            width: 960px;
            margin: 0 auto;
        }

        .title {
            text-align: center;
        }

        .nowTime {
            text-align: right;
        }

        .container div {
            padding-bottom: 10px;
        }
    </style>
    
    <script>
   	var content = '<%=vo.getDetail()%>';
        var time = new Date();  // 현재시간 출력부분
        $(function () {
            $('#nowTime').text("현재 시간 : " + time.toLocaleDateString());
        });
        $(function() {
        <% if (vo.getCtgry() == 1) { %>
        	$('#ctgry option:eq(1)').prop("selected", true);
        <% } else if (vo.getCtgry() == 2) {%>
        	$('#ctgry option:eq(2)').prop("selected", true);
        <% } else if (vo.getCtgry() == 3) {%>
        	$('#ctgry option:eq(3)').prop("selected", true);
        <% } else if (vo.getCtgry() == 4) { %>
        	$('#ctgry option:eq(4)').prop("selected", true);
        <% } else { %>
        $('#ctgry option:eq(0)').prop("selected", true);
        <% }%>
        });
		
		
      	$(function () {
          	if (<%=vo.getSale_rate()%> != 0 || $('#saleRate').val() != 0) {
				$('#sale_price').css('display', '');
          	}
          	});
      	function checkSaleRate() {
          	var saleRate = parseInt($('#saleRate').val());
          	var price = $('#price').val();
			if (!isNaN(saleRate)) {
   	  	    	$('#salePrice').text((price * (1 - (parseFloat(saleRate / 100)))));
				$('#sale_price').css('display', '');
			} else {
				$('#salePrice').text(price);
				$('#sale_price').css('display', '');
			}
        }
    </script>
	<script>
		
 	 	function update() {
 	 		saveContent();
 	   	}
		function back() {
			location.href='/admin/manageitem';
		}
		function deleteitem() {
		
			var checkDeleteItem;
			var deleteItem = confirm("정말로 등록된 상품을 삭제하시겠습니까?");
			if (deleteItem == true) {
				$('#deleteForm').submit();
				checkDeleteItem = "상품이 목록에서 삭제되었습니다.";
			} else {
				return;
			}
		
		}
	</script>
</head>
<body>
	<%@include file ="/common/adminNav.jsp" %>
	
    <div class="header">
        <h1 class="title">상품 수정페이지</h1>
    </div>
    <div class="container">
        <div class="nowTime">
            <span id="nowTime"></span>
        </div>
        <form action="/admin/updateitemproc" method="post" id="editorForm" enctype="multipart/form-data">
            <div>
                상품번호 <input type="text" value="<%=vo.getItem_sq() %>" disabled> <!-- input disable? -->
                <input type="hidden" value="<%=vo.getItem_sq() %>" name="sq" >
                제품명 <input type="text" value="<%=vo.getItem_nm()%>" name="item_nm">
                <p>상품 등록일 <%=vo.getReg_date()%></p>
            </div>
            <div>
                카테고리 <select id ="ctgry" name="ctgry">
                    <!-- 추후에 카테고리 테이블에서 가져와야함 -->
                    <option value="0">카테고리 선택</option>
                    <option value="1">1번</option>
                    <option value="2">2번</option>
                    <option value="3">3번</option>
                    <option value="4">4번</option>
                </select> 
            </div>
            
            <div>
                <!-- 상품테이블에서 가지고와서 자바스크립트로 처리 -->
                <label>
                    신제품 여부 <input type="checkbox" name="new_fl" value="true" <%if(vo.isNew_fl()) {%> checked="checked"<% }%>>
                </label>
                <label>
                    제품추천 여부 <input type="checkbox" name="upvote_fl" value="true" <%if(vo.isUpvote_fl()) {%> checked="checked"<% }%>>
                </label>
            </div>
            <div>
                가격 <input type="text" id="price" name="price" onkeyup="checkSaleRate()" value="<%=vo.getPrice()%>">
                재고 <input type="text" name="invntry" value="<%=vo.getInvntry()%>">
                판매량 <input type="text" value="<%=vo.getSale_cnt() %>" disabled>
            </div>
            <div>
                <label>
                    할인여부 <input type="checkbox" id="sale_fl" name="sale_fl" value="true" <% if(vo.isSale_fl()) { %> checked <% }%>>
                </label>
                할인률 <input type="text" id="saleRate" name="sale_rate" onkeyup="checkSaleRate()" value="<%=vo.getSale_rate()%>">
                <div id=sale_price style="display: none;">
                할인률 적용 가격 <span id="salePrice">
                <% out.print((int) (vo.getPrice() * (1 - (double) vo.getSale_rate()/100))); %></span>
                </div>
            </div>
            <div>
                <label>
                    노출여부 <input type="checkbox" name="show_fl" value="true" <%if(vo.isShow_fl()) {%> checked<% }%>>
                </label>
            </div>
            <div>
                <p style="color: red;">이미지 선택창</p>
                <input type="file" id="file[0]" name="file[0]" onchange="fileUpload(0)" accept="image/*">
                <input type="file" id="file[1]" name="file[1]" onchange="fileUpload(1)" accept="image/*">
                <input type="file" id="file[2]" name="file[2]" onchange="fileUpload(2)" accept="image/*">
                <input type="file" id="file[3]" name="file[3]" onchange="fileUpload(3)" accept="image/*">
            	<input type="file" id="file[4]" name="file[4]" onchange="fileUpload(4)" accept="image/*">
            	<table>
            	<tr>
            		<th> 이미지 업로드 : </th>
            		<td>
            			<a href="javascript:;" onClick="thumbnail(0);">
            				<img src="<%=vo.getImg_n0() %>" style="display:inline;"/>
            			</a>
            			<a href="javascript:;" onClick="thumbnail(1);">
            				<img class="thumbnailImg" src="./plusimg.png" style="display:none;"/>
            			</a>
            			<a href="javascript:;" onClick="thumbnail(2);">
            				<img class="thumbnailImg" src="./plusimg.png" style="display:none;"/>
            			</a>
            			<a href="javascript:;" onClick="thumbnail(3);">
            				<img class="thumbnailImg" src="./plusimg.png" style="display:none;"/>
            			</a>
            			<a href="javascript:;" onClick="thumbnail(4);">
            				<img class="thumbnailImg" src="./plusimg.png" style="display:none;"/>
            			</a>
            		</td>
            	</tr>
            	</table>
            </div>
            <div>
                <p>상품상세정보</p>
                <jsp:include page="/editor/editorSkinForModify.jsp" flush="false"/>
            </div>
        </form>
    <form action="/admin/deleteitemproc" id="deleteForm">
		<input value="<%=vo.getItem_sq()%>" style="display: none;" name="sq">
	</form>
        <button onclick="update()">상품변경</button>
        <button onclick="deleteitem()">등록상품삭제</button>
        <button onclick="back()">취소</button>
    </div>
</body>
</html>