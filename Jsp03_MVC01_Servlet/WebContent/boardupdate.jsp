<%@page import="com.mvc.model.dto.MVCBoardDto"%>
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

<% MVCBoardDto dto = (MVCBoardDto)request.getAttribute("list"); %>

	<h1>글 수정</h1>
		<form action="controller.do?command=updateres" method="post">
			
			<input type="hidden" name="seq" value="<%=dto.getSeq()%>">
			<table border="1">
				<tr>
					<th>작성자</th>
						<td><input type="text" readonly="readonly" value="<%=dto.getWriter() %>"></td>
					</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" value="<%=dto.getTitle() %>"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea cols="60" rows="10" name="content"><%=dto.getContent() %></textarea></td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<input type="button" value="취소" onclick="location.href='controller.do?command=list'">
						<input type="submit" value="수정">
						<input type="button" value="목록" onclick="location.href='controller.do?command=list'">
					</td>
				</tr>
			</table>
		</form>

</body>
</html>