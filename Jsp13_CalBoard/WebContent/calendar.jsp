<%@page import="com.cal.dto.CalDto"%>
<%@page import="com.cal.dao.CalDao"%>
<%@page import="java.util.List"%>
<%@page import="com.cal.util.Utils"%>
<%@page import="java.util.Calendar"%>
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
	
		#calendar{
			border-collapse : collapse;
			border : 1px solid gray;
		}
		
		#calendar th{
			width: 80px;
			border: 1px solid gray;
		}
		
		#calendar td{
			width : 80px;
			height: 80px;
			border : 1px solid gray;
			text-align: left;
			vertical-align: top;
			position: relative;
		}
		
		a{
		 text-decoration: none;
		}
		
		.clist > p {
			font-size: 5px;
			margin: 1px;
			background-color: skyblue;
		}
		
		.cpreview{
			position : absolute;
			top : -30px;
			left : 10px;
			background-color: skyblue;
			width: 40px;
			height: 40px;
			text-align: center;
			line-height: 40px;
			border-radius: 40px 40px 40px 40px;
		}
	
</style>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	
	// 2자리로 date와 month를 만들것
	
	function isTwo(n){
		return (n.length < 2) ? "0"+n : n;
	}
	
	$(function(){
		
		$(".countview").hover(
			
				function (){
					var aCountView = $(this);
					// y 클래스는 테이블에 스판태그
					var year = $(".y").text().trim();
					var month = $(".m").text().trim();
					var cDate = aCountView.text().trim();
					
					var yyyyMMdd = year + isTwo(month) + isTwo(cDate);
					
					$.ajax({
						
						type : "post",
						url : "countajax.do",
						data : "id=kh&yyyyMMdd="+yyyyMMdd,
						dataType : "json",
						async : false, //동기.
						success : function(msg){
							// msg가 json이라 key값 요청시 value값 나옴
							var count = msg.calcount;
							// 손가락 올린 a태그 다음으로
							aCountView.after("<div class='cpreview'>"+count+"</div>");
						},
						error : function(){
							alert("서버 통신 실패");
						}
						
					});
					
				},
				function (){
					$(".cpreview").remove();
				}
		
		);
		
	});
</script>

</head>
<body>

<%
	// Calendar 클래스는 추상 클래스로 생성자를 제공하지 않는다. 따라서, Calendar 클래스의 객체를 생성하기 위해 new 연산자를 사용할 수 없다.
	// 그 대신 getInstance() 메소드를 사용하여 현재 날짜와 시간의 객체를 얻어올 수 있다.
	Calendar cal = Calendar.getInstance();

	int year = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH) + 1; //month = 0부터 시작
	
	String paramYear = request.getParameter("year");
	String paramMonth = request.getParameter("month");
	
	if(paramYear != null){
		year = Integer.parseInt(paramYear);
	}
	if(paramMonth != null){
		month = Integer.parseInt(paramMonth);
	}
	
	if (month > 12){
		year ++;
		month = 1;
	}
	if (month < 1){
		year --;
		month = 12;
	}
	
	// Calendar.set()이라는 함수로 내가 원하는 값으로 날짜와 시간을 설정 할수 있다.
	cal.set(year, month-1, 1);
	
	//int start = cal.get(Calendar.DAY_OF_WEEK);
	
	// 1일의 요일
	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
	
	// 마지막 일
	int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	
	// 달력에 일정 표현
	CalDao dao = new CalDao();
	String yyyyMM = year + Utils.isTwo(month+"");
	List<CalDto> clist = dao.getViewList("kh", yyyyMM);
%>

	<table id = "calendar">
		
		<caption>
			<a href="calendar.jsp?year=<%=year-1 %>&month=<%=month %>">◀◀</a>
			<a href="calendar.jsp?year=<%=year %>&month=<%=month-1 %>">◁</a>
			
				<span class="y"><%=year %></span> 년
				<span class="m"><%=month %></span> 월
							
			<a href="calendar.jsp?year=<%=year %>&month=<%=month+1 %>">▷</a>
			<a href="calendar.jsp?year=<%=year+1 %>&month=<%=month %>">▶▶</a>
		</caption>
		
		<tr>
			<th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th>
		</tr>
		<tr>
	
<%
		//공백
		for (int i = 0; i < dayOfWeek-1; i++){
			// out : JSP꺼라서 가능
			out.println("<td>&nbsp;</td>");
		}
		
		for (int i = 1; i <= lastDay; i++){
			
%>
		<td>
			<a class="countview" href="cal.do?command=list&year=<%=year%>&month=<%=month%>&date=<%=i%>" style="color : <%=Utils.fontColor(i, dayOfWeek) %>"><%=i %></a>
			
			<a href="insertcalboard.jsp?year=<%=year%>&month=<%=month%>&date=<%=i%>&lastday=<%=lastDay%>">
				<img alt="일정추가" src="img/pen.png" style="width:10px; height:10px;">
			</a>
			
			<!-- div 안에 p태그 만들어질것 -->
			<div class="clist">
				<%=Utils.getViewTitle(i, clist) %>			
			</div>			
			
		</td>
<%			
			if((dayOfWeek-1 + i) % 7 == 0){
				out.print("</tr><tr>");
			}
			
		}
		
		// 뒤쪽 공백
		// (7-(dayOfWeek-1 + lastDay)%7)%7 ??
		for (int i = 0; i < (7-(dayOfWeek-1 + lastDay)%7)%7; i++){
			out.print("<td>&nbsp;</td>");
		}
%>	
		
<%-- <%	
		for(int i = 1; i < start; i++){
%>
			<td> </td>
<% 
		}
%>


<%
		for(int i = 1; i <= cal.getActualMaximum(Calendar.DATE); i++){
			
%>
			<td><%=i %></td>
			
<%				
			
			if(start % 7 == 0){	
%>	
			<tr></tr>
<%
			}
		
			start++;
		}
%> --%>
		</tr>
		
	</table>


</body>
</html>