<%@page import="com.login.dto.MYMemberDto"%>
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
<%
	MYMemberDto dto = (MYMemberDto)session.getAttribute("dto");
%>

	<h1><%=dto.getMyname() %> 님 환영합니다</h1>
	<div>
		<span>등급 : <%=dto.getMyrole() %> <br>
		<a href="logincontroller.jsp?command=logout">logout</a></span>
	</div>

	<div>
		<p><%=dto.getMyno() %></p>
		<a href="logincontroller.jsp?command=myinfo&myno=<%=dto.getMyno() %>">내 정보 보기</a>
	</div>

</body>
</html>