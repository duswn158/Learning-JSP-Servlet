<%@page import="java.util.List"%>
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

<script type="text/javascript">
	function updateRole(myno){
		location.href="logincontroller.jsp?command=updateroleform&myno="+myno;
	}
</script>

</head>
<%
	List<MYMemberDto> list = (List<MYMemberDto>)request.getAttribute("enabled");
%>
<body>

	<h1>회원정보 조회 (myenabled = y)</h1>
	
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

<%
		if(list.size() == 0){
%>		
		<tr>
			<td>-------------회원정보 없음-----------------</td>
		</tr>	
<%			
		} else {
			for (MYMemberDto dto : list){
%>			
			<tr>
				<td><%=dto.getMyno() %></td>
				<td><%=dto.getMyid() %></td>
				<td><%=dto.getMyname() %></td>
				<td><%=dto.getMyemail() %></td>
				<td><%=dto.getMyrole() %></td>
				<td><button onclick="updateRole(<%=dto.getMyno() %>);">변경</button></td>
			</tr>			
<%			
			}
		}
%>		
		<tr>
			<td colspan="6"><input type="button" onclick = "" value="메인"></td>
		</tr>
		
	</table>

</body>
</html>