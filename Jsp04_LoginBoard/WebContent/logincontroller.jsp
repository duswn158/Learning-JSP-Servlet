<%@page import="java.util.List"%>
<%@page import="com.login.dto.MYMemberDto"%>
<%@page import="com.login.dao.MYMemberDao"%>
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
	String command = request.getParameter("command");
	System.out.println("<"+command+">");
	
	MYMemberDao dao = new MYMemberDao();
	
	if (command.equals("login")){
		String myid = request.getParameter("myid");
		String mypw = request.getParameter("mypw");
		MYMemberDto dto = dao.login(myid, mypw);
		
		if (dto != null){
			// session scope에 담기
			session.setAttribute("dto", dto);
			// sesstion 유효 시간 : 10분동안 활동이 없으면 invaludate
			// default : 30분 / 음수일 때 무제한
			session.setMaxInactiveInterval(10*60);
			
			if (dto.getMyrole().equals("ADMIN")){
				// 서버가 응답하다가 adminmain.jsp로 다시 요청시킴
				response.sendRedirect("adminmain.jsp");
			} else if (dto.getMyrole().equals("USER")){
				response.sendRedirect("usermain.jsp");
			}
		} else { 
%>
	<script type="text/javascript">
		alert("login 실패");
		location.href="index.jsp";
		
	</script>
<%
		}
			
	} else if (command.equals("registform")){

		response.sendRedirect("registform.jsp");
	
	} else if (command.equals("idcheck")){
		
		// 1. 받아줄 데이터 받음
		// id 입력받은 값 가져옴
		String id = request.getParameter("id");
		
		// 2. 데이터베이스 연결해서 처리할거 있으면 처리
		// id 중복체크 메서드 id값 넣어서 실행
		MYMemberDto dto = dao.idCheck(id); //dto가 나옴
		
		// id가 사용되고있지 않다.
		boolean idnotused = true;
		
		// 메서드로 리턴해온 값이 null이 아니면 즉 id가 사용되고 있으면
		if(dto.getMyid() != null){
			// dto.getMyid() != null -> null포인트 에러남 이미 널이기 때문
			// 조건이 dto != null 일때는
			// 무조건 false가 뜸, dto = 무조건적으로 null값이 아님 일단 dao에 객체가 하나 만들어져 있기 때문.
			// 사이드 이펙트. 다른 페이지에서 기본값을 어떻게 주냐에 따라 다른곳에서 에러가 날수 있음
			
			// id가 사용되고 있으므로 false 처리
			idnotused = false;
		}
		
		// 3. 보낼 데이터 있으면 저장
		
		
		// 4. 보냄
		// id 중복검사 완료한 boolean값 보냄
		response.sendRedirect("idcheck.jsp?idnotused="+idnotused);
		
	} else if (command.equals("registres")){
		
		String myid = request.getParameter("myid");
		String mypw = request.getParameter("mypw");
		String myname = request.getParameter("myname");
		String myaddr = request.getParameter("myaddr");
		String myphone = request.getParameter("myphone");
		String myemail = request.getParameter("myemail");
		
		MYMemberDto dto = new MYMemberDto(0, myid, mypw, myname, myaddr, myphone, myemail, null, null);
		
		// dao에서 boolean타입으로 바꾸어둠
		boolean res = dao.insertUser(dto);
		
		if (res){
						
%>
		<script type="text/javascript">
			alert("회원가입 성공");
			location.href = "index.jsp";
		</script>

<%		
		} else {
			
%>			
		<script type="text/javascript">
			alert("회원가입 실패");
			location.href = "registform.jsp";
		</script>
<%		
		}
	} else if(command.equals("logout")){
		
		// session 객체가 가진 값 삭제
		session.invalidate();
		
		response.sendRedirect("index.jsp");
		
	} else if(command.equals("userlistall")){
		
		
	} else if(command.equals("userlisten")){
		//1
		//2.
		List<MYMemberDto> list = dao.selectEnabled();
		
		//3.
		request.setAttribute("enabled", list);
		
		//4.
		pageContext.forward("userlisten.jsp");
		
	} else if (command.equals("updateroleform")){
		
		int myno = Integer.parseInt(request.getParameter("myno"));
		
		MYMemberDto dto = dao.selectUser(myno);
		
		request.setAttribute("dto", dto);
		
		pageContext.forward("updaterole.jsp");
		
	} else if (command.equals("updateroleres")){
		
		//1.
		int myno = Integer.parseInt(request.getParameter("myno"));
		String myrole = request.getParameter("myrole");
		
		//2.
		int res = dao.updateUserRole(myno, myrole);
		
		//3.
				
		//4.
		if (res > 0){
%>
		<script type="text/javascript">
			alert("회원등급 변경 성공");
			location.href="logincontroller.jsp?command=userlisten";
		</script>
<%
		} else {
%>
		<script type="text/javascript">
			alert("회원등급 변경 시르패");
			location.href="logincontroller.jsp?command=updateroleform&myno=<%=myno%>";
		</script>
<%			
		}
		
	}
	
%>
</body>
</html>