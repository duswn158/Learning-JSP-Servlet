<%@page import="com.mvc.model.dto.MVCBoardDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<script type="text/javascript">
	
	function allcheck(bool){
		
		// Elemets : 여러개 가져올것
		var chk = document.getElementsByName("chk");
		
		// 전체선택 클릭 될때마다 전체선택 버튼과 같은 체크속성으로 바꾸어줌
		for(var i = 0; i < chk.length; i++){
			chk[i].checked = bool;
		}
			
	}
	
	$(function(){
		
		$("multiform").submit(function(){
			
			
		});
		
	});
	
	
</script>

</head>
<body>
<%List<MVCBoardDto> list = (List<MVCBoardDto>)request.getAttribute("list"); %>

	<h1>글 목록</h1>
	<form action="multichk.jsp" method="post" id="multiform">
		<table border="1">
			<col width="30">
			<col width="50">
			<col width="100">
			<col width="300">
			<col width="100">
			<tr>
				<th><input type="checkbox" name="allchk" onclick="allcheck(this.checked);"></th>
				<th>번	호</th>
				<th>작 성 자</th>
				<th>제	목</th>
				<th>작 성 일</th>
			</tr>
<%
	if (list.size() == 0){
%>
		<tr>
			<td colspan = "4">---------------작성된 글이 존재하지 않음 ----------------</td>
		</tr>
<%
	} else {
		// for문을 쓰지 않으면 dto로 가져와야하는게 list 형태이기때문에 에러가 나거나 null값이 나옴
		for(MVCBoardDto dto : list){
%>
		<tr>
			<td><input type="checkbox" name="chk" value="<%=dto.getSeq() %>"></td>
			<td><%=dto.getSeq() %></td>
			<td><%=dto.getWriter() %></td>
			<td><a href="controller.do?command=detail&seq=<%=dto.getSeq()%>"><%=dto.getTitle() %></a></td>
			<td><%=dto.getRegdate() %></td>
		</tr>
<% 
		}
	}
		
%>	
		<tr>
			<td colspan="5">
				<input type="button" value="글작성" onclick="location.href='controller.do?command=write'">
			</td>
		</tr>

	</table>
	</form>
</body>
</html>