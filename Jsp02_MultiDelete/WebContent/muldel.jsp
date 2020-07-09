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

<!-- 컨트롤러의 역할 -->

<%
	// name = "chk" 인 value값 여러개를 한번에 받아준다.
	// ?chk=1&chk=2&... 의 형식으로 넘어올것임
	// key : value (name : value) 로 넘어와서
	// 배열에 저장될때는 value만 저장 [1, 2, 3, ...]
	// boardlist에서 각기다른 seq가 찍힌 chk가 넘어옴 
	String[] seq = request.getParameterValues("chk");

	// 유효성 검사 -> ex)회원가입 할 때 반드시 작성해줘야하는것
   	// 값이 들어가 있는지 체크
	if (seq == null || seq.length == 0){
%>
	<script type="text/javascript">
		alert("하나 이상 체크해 주세요");
		location.href="boardlist.jsp";
	</script>
<%
	} else {
		MDBoardDao dao = new MDBoardDao();
		int res = dao.multiDelete(seq);
		if(res > 0){
%>
	<script type="text/javascript">
		alert ("선택한 글들을 삭제 성공 하였습니다.");
		location.href="boardlist.jsp";	
	</script>

<%
		} else {
%>

	<script type="text/javascript">
		alert("선택된 글들 삭제 실패");
		location.href="boardlist.jsp";
	</script>
	
<%
		}
	}
%>	
</body>
</html>