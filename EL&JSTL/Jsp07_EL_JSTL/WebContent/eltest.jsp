<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>Expression Language</h1>
	
	<table border="1">
		<tr>
			<!-- score.name : score객체의 getName을 호출한다 -->
			<!-- score.getName() 이것두 나옴 -->
			<th colspan="2">${score.name} 님의 점수</th>
		</tr>
		<tr>
			<th>국어</th>
			<td>${score.kor}</td>	
		</tr>
		<tr>
			<th>영어</th>
			<td>${score.eng}</td>
		</tr>
		<tr>
			<th>총점</th>
			<td>${score.sum}</td>
		</tr>
		<tr>
			<th>평균</th>
			<td>${score.avg}</td>
		</tr>
		<tr>
			<th>등급</th>
			<td>${score.grade}</td>
		</tr>
	</table>

</body>
</html>