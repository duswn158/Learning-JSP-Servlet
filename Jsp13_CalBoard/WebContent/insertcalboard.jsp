<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<%
	int year = Integer.parseInt(request.getParameter("year"));
	int month = Integer.parseInt(request.getParameter("month"));
	int date = Integer.parseInt(request.getParameter("date"));
	int lastDay = Integer.parseInt(request.getParameter("lastday"));
	
	Calendar cal = Calendar.getInstance();
	int hour = cal.get(Calendar.HOUR_OF_DAY);
	int min = cal.get(Calendar.MINUTE);

%>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 나중에 코드 이쁘게 바꾸고 하단에 ID에 로그인된 ID가 자동으로 들어가게 -->

	<h1>일정 작성하기</h1>
	
	<form action="cal.do" method="post">
		<input type="hidden" name="command" value="insertcal">
		
		<table border="1">
			<tr>
				<th>ID</th>
				<td><input type="text" name="id" value="kh" readonly="readonly"></td>
			</tr>
			<tr>
				<th>일정</th>
				<td>
					<select name="year">
<%
					for (int i = year-5; i<=year+5; i++){
%>
					<!-- 
						현재년도 기준 2015년 ~ 2025년까지 나와야되고
						현재년도가 셀렉트 되어있어야함 
					-->
					<option value="<%=i %>" <%=(year == i) ? "selected" : "" %> ><%=i %></option>

<%
					}
%>
					</select>년
					
					<select name="month">
<%
					for(int i = 1; i < 13; i++){
%>
					<option value="<%=i %>" <%=(month == i) ? "selected" : "" %> ><%=i %></option>
<%
					}
%>		
					</select>월
					
					<select name="date">

<%
					// lastDay 가져온 이유
					for(int i = 1; i <= lastDay; i++){
%>
					<option value="<%=i %>" <%=(date==i) ? "selected" : "" %> ><%=i %></option>
<%
					}
%>					
					
					</select>일
					
					<select name="hour">

<%
					for(int i = 0; i <= 24; i++){
%>
					<option value="<%=i %>" <%=(hour==i) ? "selected" : "" %> ><%=i %></option>
<%
					}
%>					
					
					</select>시
					
					<select name="min">

<%
					for(int i = 0; i < 60; i++){
%>
					<option value="<%=i %>" <%=(min==i) ? "selected" : "" %> ><%=i %></option>
<%
					}
%>						
					
					</select>분
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea cols="60" rows="10" name="content" style="resize: none;"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="일정작성">
					<input type="button" value="돌아가기" onclick="">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>