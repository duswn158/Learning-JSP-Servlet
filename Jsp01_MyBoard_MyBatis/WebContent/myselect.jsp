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
<title>select</title>
</head>
<body>
<%
	int myno = Integer.parseInt(request.getParameter("myno"));

	MyDao dao = new MyDao();
	// MyDto 타입의 변수 dto에 dao.selectOne(myno); 메서드 동작된것 리턴
	MyDto dto = dao.selectOne(myno);
%>
	

	<h1>글 보기</h1>
		
		<table border="1">
						
			<tr>
				<td>이름</td>
				<td><input type="text" readonly="readonly" value="<%=dto.getMyname() %>"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" readonly="readonly" value="<%=dto.getMytitle() %>"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="4" cols="60" readonly="readonly"><%=dto.getMycontent() %></textarea></td>
			</tr>		
			
			<tr>
				<td colspan="2" align="right">
					<input type="button" value="수정" onclick="location.href='myupdate.jsp?myno=<%=dto.getMyno() %>'">
					<input type="button" value="삭제" onclick="location.href='mydelete.jsp?myno=<%=dto.getMyno() %>'">
					<input type="button" value="삭제" onclick="location.href='mylist.jsp'">
				</td>
			</tr>	
		</table>
		

</body>
</html>