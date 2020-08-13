<%@page import="com.login.dto.MYMemberDto"%>
<%@page import="java.util.List"%>
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
	//List<MYMemberDto> list = (List<MYMemberDto>)request.getAttribute("dto");
	
	MYMemberDto dto = new MYMemberDto();
%>
<body>

	<h1>회원정보 조회</h1>
	
	<table border="1">
		<col width="50">
		<col width="100">
		<col width="100">
		<col width="500">
		<col width="50">
		<col width="100">
		<tr>
			<th>번	호</th>
			<th>아 이 디</th>
			<th>이	름</th>
			<th>이 메 일</th>
			<th>현재등급</th>
			<th>등급변경</th>
		</tr>
				
		<tr>
			<td><%=dto.getMyno() %></td>
			<td><%=dto.getMyid() %></td>
			<td><%=dto.getMyname() %></td>
			<td><%=dto.getMyaddr() %></td>
			<td><%=dto.getMyphone() %></td>
			<td><%=dto.getMyemail() %></td>
				
			<td><button onclick="updateRole(<%=dto.getMyno() %>);">변경</button></td>
		</tr> 			
	
		<tr>
			<td colspan="6"><input type="button" onclick = "" value="메인"></td>
		</tr>
		
	</table>


</body>
</html>