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

	<a href="mycontroller.do?command=basic">Expression Language</a>
	<br>
	<a href="mycontroller.do?command=eltest">EL Test</a>
	<br>
	<a href="mycontroller.do?command=jstltest">JSTL test</a>

</body>
</html>