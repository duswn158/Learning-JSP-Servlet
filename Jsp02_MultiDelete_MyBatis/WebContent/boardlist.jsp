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

<%
	MDBoardDao dao = new MDBoardDao();
	List<MDBoardDto> list = dao.selectList();
%>

<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<script type="text/javascript">

	// 전체 선택
	function allChk(bool){
		var chks = document.getElementsByName("chk");
		for (var i = 0; i < chks.length; i++){
			chks[i].checked = bool;
		}
	}
	
	$(function(){
		
		// 따로 버튼없이 아래 form태그 submit
		// 하나도 체크된것 없을때 alert창 띄우는 처리
		$("#muldelform").submit(function(){
			if($("#muldelform input:checked").length == 0){
				alert("하나 이상 체크 하셔야 됩니다.");
				
				// 이벤트 전파 막음 즉 아무것도 체크하지 않으면 submit이벤트 발생 하지 않음
				return false;
			}
		});	
		
	});
	
</script>

</head>
<body>

	<!-- 
		같은 폴더내에 있는 heade.jsp 파일을 추가(포함)한다
		즉 이름없이 합쳐지며
		현재 파일의 일부분 되어버린다.(완전흡수. css파일 html에 불러온것처럼)
		include 한 위치에 포함시킨다
		include 파일 내부에 링크가 있으면 폴더찾을때 경로도 전부 이곳을 기준으로 해주어야함
	 -->
	<%@ include file="./form/header.jsp" %>
	
	<h1>글 목록</h1>
	
	<!-- 선택글 삭제 버튼 클릭시 form submit으로  muldel에 보내서 multiDelete 처리 해줌 -->
	<form action="./muldel.jsp" method="post" id="muldelform">
	
		<table border="1">
			<col width="30">
			<col width="50">
			<col width="100">
			<col width="300">
			<col width="100">
			<tr>
				<th><input type="checkbox" name="all" onclick="allChk(this.checked);"></th>
				<th>번	호</th>
				<th>작  성  자</th>
				<th>제	목</th>
				<th>작  성  일</th>
			</tr>
<%
		// dao의 selectlist (즉 전체 리스트 불러오는 메서드)의 size가 0이라면,
		// 즉 저장된 컬럼이 하나도 없다면.
		if (list.size() == 0){
			
%>			
		
			<tr>
				<td colspan="5">------------글이 존재하지 않습니다---------</td>
			</tr>
			
<%
		} else {
			
			for(MDBoardDto dto : list){
		
%>
			
			<tr>
				<!-- 데이터 전달되면 muldel.jsp?chk1=..&chk2=.. 이런식으로 체크된것들이 데이터로 전달됨  -->
				<!-- value에 각기다른 Seq가 들어감 -->
				<td><input type="checkbox" name="chk" value="<%=dto.getSeq() %>"></td>
				<td><%=dto.getSeq() %></td>
				<td><%=dto.getWriter() %></td>
				<td><a href="boardselect.jsp?seq=<%=dto.getSeq() %>"><%=dto.getTitle() %></a></td>
				<td><%=dto.getRegdate() %></td>
			</tr>
			
<%
			}
		}
%>
			<tr>
				<td colspan="5" align="right">
					<input type="button" value="글 작성" onclick="location.href='boardinsert.jsp'">
					<input type="submit" value="선택글 삭제">
				</td>
			</tr>

		</table>
	
	</form>
	
	<%@ include file="./form/footer.jsp" %>
	
</body>
</html>