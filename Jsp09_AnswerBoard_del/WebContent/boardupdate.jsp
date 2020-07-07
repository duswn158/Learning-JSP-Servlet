<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>글 수정</h1>
	
	<form action="controller.do" method="post">
	
			<input type="hidden" name="command" value="updateres">
			<input type="hidden" name="boardno" value="${dto.boardno }">
			
			<table border = "1">
			<col width="100">
			<col width="300">
			<col width="100">
			<tr>
				<th>작  성 자</th>
				<th>제	 목</th>
				<th>작  성  일</th>
			</tr>
			<tr>
				<td>
					<input type="text" name="writer" readonly="readonly" value="${dto.writer }">			
				</td>
				<td>
					<input type="text" name="title" value="${dto.title }">					
				</td>
				<td>
					<input type="text" name="content" value="${dto.content }">	
				</td>
			</tr>
			<tr>
				<td colspan="3" align = "right">
					<input type="submit" value="수정">
					<input type="button" value="목록" onclick="location.href='controller.do?command=list'">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>