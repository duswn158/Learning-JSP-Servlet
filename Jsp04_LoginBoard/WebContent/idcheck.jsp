<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	onload = function(){
		// id 변수에 myid 가져와서 넣어줌 즉 중복체크 창에서 id 중복체크하며 그대로 전달
		var id = opener.document.getElementsByName("myid")[0].value;
		document.getElementsByName("id")[0].value = id;
	}
	
	function confirmId(bool){
		// 아이디 중복검사한 boolean값 아래 html에서 받아와서
		if(bool == "true"){
			// true 면 title 속성을 y로 + 비밀번호 입력창에 포커스
			opener.document.getElementsByName("myid")[0].title = 'y';
			opener.document.getElementsByName("mypw")[0].focus();
		} else {
			opener.document.getElementsByName("myid")[0].focus();
		}
		self.close();
		
	} 
	
</script>

</head>
<body>
<%
	// controller에서 boolean값 받아옴
	String idnotused = request.getParameter("idnotused");
%>

	<table border="1">
		<tr>
			<td>
				<input type="text" name="id">
			</td>
		</tr>
		<tr>
			<!-- 삼항 연산자 (아이디 중복검사 유효하면 아이디 생성 혹은 중복된 아이디 존재 출력) -->
			<td><%=idnotused.equals("true")?"아이디 생성 가능" : "중복된 아이디 존재" %></td>
		</tr>
		<tr>
			<td>
				<!-- ''를 붙이는이유 :  -->
				<input type="button" value="확인" onclick="confirmId('<%=idnotused %>')">
			</td>
		</tr>
	</table>
	

</body>
</html>