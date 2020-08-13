<%@page import="com.my.model.dao.MyDao"%>
<%@page import="com.my.model.dto.MyDto"%>
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
	// myupdate에서 myno를 보내주지 않으면, numberformatException null을 number타입으로 바꿀 수 없다는 에러
	int myno = Integer.parseInt(request.getParameter("myno"));
	String mytitle = request.getParameter("mytitle");
	String mycontent = request.getParameter("mycontent");
	
	MyDto dto = new MyDto(myno, mytitle, mycontent);
	MyDao dao = new MyDao();
	int res = dao.update(dto);
	if(res > 0){	
%>

	<script type="text/javascript">
		alert("수정성공");
		location.href="myselect.jsp?myno=<%=myno%>";
	</script>

<%
	} else {
%>

	<script type="text/javascript">
		alert("수정 실패");
		location.href = "myupdate.jsp?myno=<%=myno%>";
	</script>

<%
	}
%>

</body>
</html>