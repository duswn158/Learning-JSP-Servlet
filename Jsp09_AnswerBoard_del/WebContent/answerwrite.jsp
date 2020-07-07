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

<!-- 

	필요 요소
	update : boardno
	insert : title, content, writer, 부모의 boardno
	
 -->

	<h1>답변 글 작성</h1>
	
	<form action="controller.do" method="post">
	
		<input type="hidden" name="command" value="answerwriteres">
		
		<input type="hidden" name="parentboardno" value="${dto.boardno }">
		
		<table border="1">
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="ㄴRE:${dto.title }"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer" value="${dto.writer }"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="60" name="content">${dto.content }</textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="답변">
					<input type="button" value="취소" onclick="">
				</td>
			</tr>
		
		</table>
	
	</form>

</body>
</html>