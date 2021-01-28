<%@page import="shop.kbgagu.www.admin.statistics.VisitVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ArrayList<VisitVo> avs = (ArrayList<VisitVo>) request.getAttribute("avs");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link rel="stylesheet" href="/css/admin.css" type="text/css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<!-- 차트 링크 -->
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>

</head>
<body>

	<%@include file="/common/adminNav.jsp"%>

	<section>

		<article>
			<div>
				<div id="container" style="width: 700px">
					<canvas id="myChart"></canvas>
				</div>
			</div>
		</article>


	</section>


	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>


	<script> 
	var ctx = document.getElementById('myChart').getContext('2d'); 
	
	var labels = new Array(<%
			for (int i = 0; i < 30; i++) {
				%>
					'<%=avs.get(30-(i+1)).getDate()%>',
				<%
				}
				%>);
	
	var data = new Array(<%
			for (int i = 0; i < 30; i++) {
				%>
					'<%=avs.get(30-(i+1)).getHit()%>',
				<%
				}
				%>);
	
	var chart = new Chart(ctx, {  
		type: 'line',  
		
		data: { labels: labels,
			
		datasets: [{ label: '일간 방문자수', backgroundColor: 'transparent', borderColor: 'red', data: data }] },  
		options: {
			legend: { display: false }, 
			title: { display : true, text: '일간 방문자수' }

		} }); 
	</script>


</body>
</html>