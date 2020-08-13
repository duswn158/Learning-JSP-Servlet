
<%
	// logout 한 뒤에 뒤로가기했을때 로그인되있지 않아야함
	// 서버가 가지고있던 캐시를 저장하지 않겠다
	response.setHeader("Pragma", "no-cache");			// http 1.0 일때
	response.setHeader("Cache-control", "no-store");	// http 1.1	일때
	response.setHeader("Expires", "0");					// proxy server 일때
	
	/*
		데이터가 변경되었을 때, 이전 내용을 화면에 보여주는 이유!
		-> 서버에서 다시 응답하는게 아니라 캐시에 저장된 내용을 가져오기 때문
	*/
%>

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
	
	
	if(dto == null){
		pageContext.forward("index.jsp");
	}
%>

	<h1><%=dto.getMyname() %> 님 환영합니다.</h1>
	
	<div>
		<span>등급 : <%=dto.getMyrole() %> <br>
		<a href="logincontroller.jsp?command=logout">logout</a></span>
	</div>

	<div>
		<a href="logincontroller.jsp?command=userlistall">회원정보 전체 조회</a>
	</div>
	<div>
		<a href="logincontroller.jsp?command=userlisten">회원정보 조회(myenabled = y)</a>
	</div>

</body>
</html>