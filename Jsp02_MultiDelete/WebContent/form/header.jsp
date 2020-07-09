<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
	header{
		background-color : skyblue;
		height : 50px;
	}
	footer{
		background-color : skyblue;
		height: 30px;
		text-align: center;
		line-height: 30px;
	}
	a{
		text-decoration: none;
	}
	
</style>

</head>
<body>

	<header>
		<!-- 
			boradlist는 현재 폴더인 form에 없음 하지만 boardlist.jsp에 흡수됬기 때문에
			./ = WebContent폴더가 됨. 그래서 가능. 
		-->
		<a href="./boardlist.jsp">게시판 홈</a>
	</header>

</body>
</html>