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

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<script type="text/javascript">

	function allChk(bool){
		var chks = document.getElementsByName("chk");
		for (var i = 0; i < chks.length; i++){
			chks[i].checked = bool;
		}
	}
	
	function(){
		
	}

</script>

</head>
<body>

	<h1>글 목록</h1>
	
	<table border="1">
		<col width="50">
		<col width="50">
		<col width="300">
		<col width="100">
		<col width="100">
		
		<tr>
			<th><input type="checkbox" name="all" onclick="allChk(this.checked);"></th>
			<th>번 	호</th>
			<th>제	목</th>
			<th>작 성 자</th>
			<th>작 성 일</th>
		</tr>
		
		<c:choose>
			<c:when test="${empty list }">
				<tr>
					<td>--------------작성된 글이 존재하지 않습니다-----------</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items = "${list }" var="dto">
					<c:choose>
						<c:when test="${dto.delflag eq 'Y' }">
							<tr>
								<td colspan="5" align="center">--------------작성된 글이 존재하지 않습니다-----------</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td><input type="checkbox" name="chk" value="${dto.boardno }"></td>
								<td>${dto.boardno }</td>
								<td>	
									<c:forEach begin="1" end ="${dto.titletab }">
										&nbsp;
									</c:forEach>
									<a href="controller.do?command=detail&boardno=${dto.boardno }">${dto.title }</a>									
								</td>
								<td>${dto.writer }</td>
								<td>${dto.regdate }</td>		
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<tr>
			<td colspan="5" align="right">
				<input type="button" value="글작성" onclick="location.href='controller.do?command=insert'">
			</td>
		</tr>		
	</table>

</body>
</html>