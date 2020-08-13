<%@page import="com.muldel.dto.MDBoardDto"%>
<%@page import="com.muldel.dao.MDBoardDao"%>
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
	int seq = Integer.parseInt(request.getParameter("seq"));
	
	MDBoardDao dao = new MDBoardDao();
	
	// 현재 페이지에서 이전페이지(selectone)의 값을 뿌려줘야하기 때문에 selectOne 메서드 써야함
	MDBoardDto dto = dao.selectOne(seq);
	
%>

	<h1> 글 수정 </h1>
	
	<%@ include file="./form/header.jsp" %>
	
	<form action="boardupdateres.jsp" method="post">
		<input type="hidden" value="<%=dto.getSeq() %>" name="seq"> 
		<table border="1">
			<tr>
				<td>작성자</td>
				<td><input type="text" name = "writer" value="<%=dto.getWriter() %>" readonly="readonly"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name = "title" value="<%=dto.getTitle()%>"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<textarea rows="4" cols="60" name="content"><%=dto.getContent() %></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4" align = "right">
					<input type="submit" value="수정">
					<input type="button" value="취소" onclick="location.href='boardselect.jsp?seq=<%=dto.getSeq() %>'">
				</td>
			</tr>
		</table>
	
	</form>
	
	
	<%@ include file="./form/footer.jsp" %>

</body>
</html>