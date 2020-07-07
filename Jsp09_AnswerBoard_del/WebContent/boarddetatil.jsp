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
	
	<h1>글 자세히 보기</h1>
	
	<form action="controller.do" method="post">
		
		<input type="hidden" name="command" value="update">		
		<input type="hidden" name="boardno" value="">
		
		<table border = "1">
			
			<tr>
				<th>작  성  자</th>
				<td>
					<input type="text" name="writer" value ="${detail.writer }" readonly="readonly">			
				</td>				
			</tr>
			<tr>
				<th>제	 목</th>
				<td>
					<input type="text" name="title" value ="${detail.title }" readonly="readonly">			
				</td>
			</tr>
			<tr>
				<th>내	 용</th>
				<td colspan="2">
					<textarea cols="40" rows="6" readonly="readonly">${detail.content }</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2" align = "right">
					<input type="button" value="수정" onclick="location.href='controller.do?command=update&boardno=${detail.boardno }'">
					<input type="button" value="답글" onclick="location.href='controller.do?command=answerwrite&boardno=${detail.boardno }'">
					<input type="button" value="삭제" onclick="location.href='controller.do?command=delete&boardno=${detail.boardno }'">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>