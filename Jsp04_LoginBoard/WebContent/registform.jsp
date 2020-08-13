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
	function idCheck(){		
		
		// 중복확인 클릭시 아이디 입력한 값 찾아옴
		var doc= document.getElementsByName("myid")[0];
		
		// 유효성 검사 (trim() -> 공백써서 값이 들어가는걸로 될까봐)
		// 값을 썻는지 쓰지 않았는지 확인
		if(doc.value.trim() == "" || doc.value == null){
			alert("아이디를 입력해 주세요!");
		} else {
			// 값이 있을때 controller에 command와 id 넘기며 팝업 창 연다.
			// doc.value : id입력받은 값.
			open("logincontroller.jsp?command=idcheck&id="+doc.value,
					"",
					"width=200, height=200");
		}
	}
</script>

</head>
<body>

	<h1>회원가입</h1>
	
	<form action="logincontroller.jsp" method="post">
		<input type="hidden" name ="command" value="registres">
		<table border="1">
		<!-- 아이디, 비밀번호, 이름, 주소, 전화번호, 이메일 -->
			<tr>
				<th>아이디</th>
				<td>
					<!-- 굳이 쓸필요없는 title속성으로 중복체크를 할것 -->
					<input type="text" name="myid" required="required" title="n">
					<input type="button" value="중복체크" onclick="idCheck();">
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<!-- 필수작성 : required="required" -->
				<td><input type="text" name="mypw" required="required"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="myname" required="required"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="myaddr" required="required"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="myphone" required="required"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="myemail" required="required"></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="가입">
					<input type="button" value="취소">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>