<%@page import="java.text.DecimalFormat"%>
<%@page import="shop.kbgagu.www.vo.ItemVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shop.kbgagu.www.common.LoginManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<ItemVo> newItemList = (ArrayList<ItemVo>)request.getAttribute("newItemList");
	ArrayList<ItemVo> upvoteItemList = (ArrayList<ItemVo>)request.getAttribute("upvoteItemList");
	ArrayList<ItemVo> saleItemList = (ArrayList<ItemVo>)request.getAttribute("saleItemList");
	DecimalFormat moneyformatter = new DecimalFormat("###,###"); // 콤마찍어주는 자바 내장 객체
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KB가구</title>
<link rel="stylesheet" href="/css/main.css" type="text/css">
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>	
</head>
<body>

	<%@include file ="/common/mainNav.jsp" %>
	
	<div id = headercontainer >
		<header id = "slider">	
			<img src= "/img/slideimg1.jpg"><img src= "/img/slideimg2.jpg"><img src= "/img/slideimg3.jpg">	
		</header>
	</div>
	<div id = "gap"></div>

	<section>
		<div id = "maincontainer">
			<article class = "itemlist">
				<h4>신제품</h4>
				<br><br>
				<ul>	
						<%for(int i =0; i<newItemList.size();i++) {%>
						<a href ="shop/productdetail?pd=<%=newItemList.get(i).getItem_sq()%>">
							<li> 
								<img src="<%= newItemList.get(i).getImg_n0()%>">	
								<p><%=newItemList.get(i).getItem_nm() %></p>
								<p><%=moneyformatter.format(newItemList.get(i).getPrice())%>원</p>
								<p><span class="badge bg-info text-dark">NEW</span></p>
							</li>
						</a>
						<%}%>
				</ul>
			</article>
			
			<article class = "itemlist">
				<h4>MD추천제품</h4>
				<br><br>
				<ul>
					<%for(int i =0; i<upvoteItemList.size();i++) {%>
						<a href ="shop/productdetail?pd=<%=upvoteItemList.get(i).getItem_sq()%>">
							<li> 
								<img src="<%= upvoteItemList.get(i).getImg_n0()%>">
								<p><%=upvoteItemList.get(i).getItem_nm() %></p>
								<p><%=moneyformatter.format(upvoteItemList.get(i).getPrice())%>원</p>
								<p><span class="badge bg-dark">MD추천</span></p>
							</li>
						</a>
					<%}%>
				</ul>		
			</article>
			
			<article class = "itemlist">
				<h4>SALE</h4>
				<br><br>
				<ul>
					<%for(int i =0; i<saleItemList.size();i++) {%>
					<a href ="shop/productdetail?pd=<%=saleItemList.get(i).getItem_sq()%>">
						<li> 
							<img src="<%= saleItemList.get(i).getImg_n0()%>">
							<p><%=saleItemList.get(i).getItem_nm() %></p>
							<p><%=moneyformatter.format(saleItemList.get(i).getPrice())%>원</p>
							<p><span class="badge bg-danger">SALE</span></p>
						</li>
					</a>
					<%}%>
				</ul>		
			</article>
		</div>
	
	</section>
	
	<%@include file ="/common/footer.jsp" %>
	
	
	<script>
		var headerHeight = document.getElementById('slider').offsetHeight;
		const gap = document.getElementById('gap');
		let scrollLocation = document.documentElement.scrollTop;

		window.onload=function(){
			changeNavColor();
			changegap();
			slideMain();
		}
		
		window.onresize = function(event){
			changegap();
			changeNavColor();
		}
		
		window.addEventListener('scroll', changeNavColor);
	
		function slideMain() {
			var sliderRightPosition = 0;
			const slideimgcnt = $('#slider img').length;
			
			$('header').css("width",slideimgcnt*100 + "vw");
			$('#slider').css("transition","0.7s");
			
			var mainSlider = setInterval(() => {
				sliderRightPosition +=100;
				if(sliderRightPosition>=slideimgcnt * 100){
					sliderRightPosition = 0;
				}
				$('#slider').css("right", sliderRightPosition+"vw");
			}, 4000);
		}
		
		function changeNavColor(){
			scrollLocation = document.documentElement.scrollTop; // 현재 스크롤바 위치
			headerHeight = document.getElementById('slider').offsetHeight;
			if(scrollLocation+150 >= headerHeight){
				$('nav').css("background-color","rgba(0, 0, 0, 0.8)");
				$('nav>a').css("color","white");
				$('nav>a>img').attr("src","/img/logo.png");
				$('nav>a').hover(function(){$(this).css('color','orange');},function() {$(this).css('color','white');});
			}else{
				$('nav').css("background-color","rgba(0, 0, 0, 0)");
				$('nav>a').css("color","black");
				$('nav>a>img').attr("src","/img/logob.png");
				$('nav>a').hover(function(){$(this).css('color','orange');},function() {$(this).css('color','black');});
			}
		}
	
		function changegap(){
			headerHeight = document.getElementById('slider').offsetHeight;
			$('section').css("top",(headerHeight-100) + "px");
		}
		
		
	</script>
</body>
</html>