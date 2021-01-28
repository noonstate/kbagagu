<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>상품 등록 페이지</title>
<link rel="stylesheet" href="/css/admin.css" type="text/css">
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
</head>

<script type="text/javascript">

var time = new Date();  // 현재시간 출력부분
$(function () {
    $('#nowTime').text("현재 시간 : " + time.toLocaleDateString());
});

function registerItem(){
	var item_nm = $("#item_nm");
	var price = $("#price");
	var content = $("#content");
	
	saveContent();
}

$("#editorForm").submit();

</script>
<body>
	<%@include file ="/common/adminNav.jsp" %>
	
   	<div class="header">
        <h1 class="title">상품 등록페이지</h1>
	</div>
	<div class="container">
        <div class="nowTime">
            <span id="nowTime"></span>
        </div>
	<form action="/admin/insertitemproc" method="post" id="editorForm" enctype="multipart/form-data">
		<div class="inputArea">
			<label for="item_nm">상품명</label>
			<input type="text" id="item_nm" name="item_nm">	
		</div>
		<div class="inputArea">
			<label for="price">상품가격</label>
			<input type="text" id="price" name="price">	
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
                    신제품 여부 <input type="checkbox" name="new_fl" value="true"  checked="checked">
                </label>
                <label>
                    제품추천 여부 <input type="checkbox" name="upvote_fl" value="true"  checked="checked">
                </label>
            </div>
            <div>
                <label>
                    할인여부 <input type="checkbox" name="sale_fl" value="true">
                </label>
                할인률 <input type="text" name="sale_rate"value="">
                할인률 적용 가격 <span id="salePrice">
               </span>
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
            				<img style="display:inline;"/>
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
			<jsp:include page="/editor/editorSkinForRegister.jsp" flush="false"/>		
		</div>
		
	</form>
	<button onclick="registerItem()">등록</button>
	<button onclick="history.back()">취소</button>
	</div>
</body>
</html>