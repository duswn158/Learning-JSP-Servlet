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

	<h1>일정 자세히 보기</h1>
	
	<input type="hidden" name="command" value="update">
	<jsp:useBean id="utils" class="com.cal.util.Utils"/>
		
	<table border="1">
		<tr>
			<th>ID</th>
			<td><input type="text" name="id" value="${dto.id }" readonly="readonly"></td>
		</tr>
		<tr>
			<th>일정</th>
			<td>
				<jsp:setProperty property="toDates" name="utils" value="${dto.mdate }"/>
				<jsp:getProperty property="toDates" name="utils"/>
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" value="${dto.title }" readonly="readonly"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea cols="60" rows="10" name="content" style="resize: none;" readonly="readonly">${dto.content }</textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="button" value="수정하기" onclick="">
				<input type="button" value="삭제하기" onclick="">
				<input type="button" value="돌아가기" onclick="location.href='cal.do?command=calendar'">
			</td>
		</tr>
	</table>

</body>
</html>