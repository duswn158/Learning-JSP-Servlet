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
	MYMemberDto dto = (MYMemberDto) request.getAttribute("dto");
%>

	<h1>회원등급 변경 페이지</h1>
	
	<form action="logincontroller.jsp" method="post">
		<input type="hidden" name = "command" value="updateroleres">
		<input type="hidden" name = "myno" value="<%=dto.getMyno()%>">
		
		<table border="1">
			<col width="50">
			<col width="200">
			<col width="100">
			<tr>
				<th>번호</th>
				<td><%=dto.getMyno() %></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><%=dto.getMyname() %></td>
			</tr>
			<tr>
				<th>등급</th>
				<td>
					<select name="myrole">
						<!-- 회원 등급에따라 옵션이 선택 되어 있도록 -->
						<option value="USER" <%=dto.getMyrole().equals("USER")?"selected":"" %>>일반회원</option>
						<option value="ADMIN" <%=dto.getMyrole().equals("ADMIN")?"selected":""%>>관리자</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="변경 완료">
					<input type="button" value="목록">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>