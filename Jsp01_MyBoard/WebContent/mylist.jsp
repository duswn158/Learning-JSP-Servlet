<%@page import="com.my.model.dto.MyDto"%>
<%@page import="java.util.List"%>
<%@page import="com.my.model.dao.MyDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mylist</title>
</head>
<body>

<%
	MyDao dao = new MyDao();
	List<MyDto> list = dao.selectList();	
%>
	<h1>글 목록</h1>
	
	<table border="1">
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>제목</th>
			<th>날짜</th>
			<!-- 내용은 글 하나씩 보는창에서 볼거니까 x -->
		</tr>
<%
	for(int i = 0; i < list.size(); i++){
%>
		<tr>
			<td><%=list.get(i).getMyno() %></td>
			<td><%=list.get(i).getMyname() %></td>
			<td><a href="myselect.jsp?myno=<%=list.get(i).getMyno() %>"><%=list.get(i).getMytitle() %></a></td>
			<td><%=list.get(i).getMydate() %></td>
		</tr>
<%
	}
%>
		<tr>
			<td colspan="4" align = "right">
				<input type="button" value="글작성" onclick="location.href='myinsert.jsp'">
			</td>
		</tr>

	</table>
</body>
</html>