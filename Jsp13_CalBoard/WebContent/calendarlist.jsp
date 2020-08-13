<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 코드해석 : JSP_0713_태그해석.md 참조 -->
	<h1>일정 목록 보기</h1>
	
	<form action="cal.do" method="post">
		<input type="hidden" name="command" value="muldel">
		
		<jsp:useBean id="utils" class="com.cal.util.Utils"/>
		
		<table border="1">
			<col width="50">
			<col width="50">
			<col width="300">
			<col width="200">
			<col width="100">
			
			<tr>
				<th><input type="checkbox" name="all" onclick=""></th>
				<th>번	호</th>
				<th>제	목</th>
				<th>일	정</th>
				<th>작  성  일</th>
			</tr>
			<c:choose>
				<c:when test="${empty list }">
					<tr>
						<td colspan="5" align="center">
							-----------------일정이 없습니다---------------------
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${list }" var="dto">
						<tr>
							<td><input type="checkbox" name="chk" value="${dto.seq }"></td>
							<td>${dto.seq }</td>
							<td><a href="cal.do?command=select&seq=${dto.seq }">${dto.title }</a></td>
							<td>
								<jsp:setProperty property="toDates" name="utils" value="${dto.mdate }"/>
								<jsp:getProperty property="toDates" name="utils"/>
							</td>
							<td>
								<fmt:formatDate value="${dto.regdate }" pattern="yyyy:MM:dd"/>
							</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<tr>
				<td colspan="5">
					<input type="submit" value="삭제">
					<input type="button" value="돌아가기" onclick="">
				</td>
			</tr>
			
		</table>
	</form>

</body>
</html>