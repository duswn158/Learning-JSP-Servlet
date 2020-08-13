<%@page import="com.muldel.dto.MDBoardDto"%>
<%@page import="java.util.List"%>
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

<style type="text/css">
	
	table{
		border : 2px dotted pink;
		background-color: #ffe0e0;
	}
	
	th {
		color : white;
		font-size: 14pt;
		border-bottom : 1px solid white;
	}
	
	td{
		color : #333333;
		text-align : center;
		line-height : 30px;
	}

</style>

</head>
<body>

<%
	int seq = Integer.parseInt(request.getParameter("seq"));

	MDBoardDao dao = new MDBoardDao();
	
	// dao.selectOne(myno); 메서드 동작된것 리턴받을 변수가 필요 따라서 dto 타입 변수를 만듬
	MDBoardDto dto = dao.selectOne(seq);
%>

	<h1>글 자세히 보기</h1>
	
	<table>
		
		<tr>
			<th>번	호</th>
			<th>작  성  자</th>
			<th>제	목</th>
			<th>작  성  일</th>
		</tr>
		
		<tr>
			<td><%=dto.getSeq() %></td>
			<td><%=dto.getWriter() %></td>
			<td><%=dto.getTitle() %></td>
			<td><%=dto.getRegdate() %></td>
		</tr>
		
		<tr>
			<td colspan="4">
				<textarea cols="60" rows="10" readonly="readonly"><%=dto.getContent() %></textarea>
			</td>
		</tr>
		
		<tr>
			<td colspan="4" align = "right">
				<input type="button" value="수정" onclick="location.href='boardupdate.jsp?seq=<%=dto.getSeq() %>'">
				<input type="button" value="삭제" onclick="location.href='boarddelete.jsp?seq=<%=dto.getSeq() %>'">
				<input type="button" value="목록" onclick="location.href='boardlist.jsp'">
			</td>
		</tr>
		
	</table>


</body>
</html>