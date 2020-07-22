package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.model.dao.MVCBoardDao;
import com.mvc.model.dto.MVCBoardDto;

@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public BoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 인코딩 암기
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.printf("[%s]\n", command);
		
		// if문 들어가기전에 미리 객체생성
		MVCBoardDao dao = new MVCBoardDao();
		
		if(command.equals("list")) {
			//1.
			//2.
			List<MVCBoardDto> list = dao.selectList();	
			//3.
			request.setAttribute("list", list);
			//4.
			RequestDispatcher dispatch = request.getRequestDispatcher("boardlist.jsp");
			dispatch.forward(request, response);
			
		} else if(command.equals("write")) {
			
			response.sendRedirect("boardwrite.jsp");
		
		} else if (command.equals("writeres")) {
			
			// 1 값 받아옴
			String writer = request.getParameter("writer");
			String title  = request.getParameter("title");
			String content = request.getParameter("content");
			
			MVCBoardDto dto = new MVCBoardDto(writer, title, content);
			
			int res = dao.insert(dto);
			
			if(res > 0) {
				// 작성 완료, 성공시 list로 보냄		
				
				// 컨트롤러가 응답하여 처리를 모두했고, 그 뒤에 list로 재 요청 시켜서 다시 객체가 만들어짐
				// 새로고침 해서 다시 요청해봤자 객체가 없는 list만 응답됨
				// 컨트롤러가 아닌 클라이언트가 원하는 페이지를 처리만 한뒤에 다시 응답시킴
				response.sendRedirect("controller.do?command=list");
				
				// 새로고침 : 클라이언트에서 다시 요청하겠다
				// 아래와 같은 경우 forward를 사용했기 때문에
				// 서버안의 컨트롤러가 list에 권한,객체를 다담아서 보내두었고. list가 요청받아 응답시켜줌
				// 새로고침을 하면 다시 컨트롤러를 요청하게되고 request객체와 권한이 그대로 살아있기 때문에 새로고침 할때마다 글작성이 됨
				/*
				// list.jsp 에 값이 들어가서 보여져야 하기때문에 다시 list값을 가져와주어야함
				request.setAttribute("list", dao.selectList());
				RequestDispatcher dispatch = request.getRequestDispatcher("boardlist.jsp");
				dispatch.forward(request, response);
				*/
			} else {
				// 실패시 위의 객체가 저장되지 않도록 Redirect해서 글작성 페이지로 넘김
				response.sendRedirect("boardwrite.jsp");
			}
		} else if(command.equals("detail")) {
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			MVCBoardDto dto = dao.selectOne(seq);
			request.setAttribute("dto", dto);
			
			RequestDispatcher dispatch = request.getRequestDispatcher("boarddetail.jsp");			
			dispatch.forward(request, response);
			
		} else if(command.equals("delete")) {
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			int res = dao.delete(seq);
			
			if(res > 0) {
				
				response.sendRedirect("controller.do?command=list");
				
			} else {
				
				// dao.selectList(); 리턴값을 list라는 이름으로 담음
				request.setAttribute("list", dao.selectList());
				
				// boardlist로 forward 해준다.
				RequestDispatcher dispatch = request.getRequestDispatcher("boardlist.jsp");
				dispatch.forward(request, response);
				
			}
			
		} else if(command.equals("updateres")) {
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			String title  = request.getParameter("title");
			String content = request.getParameter("content");
			
			MVCBoardDto dto = new MVCBoardDto(seq, title, content);
			
			int res = dao.update(dto);
			
			// alert창 띄우기
			// print! 암기
			PrintWriter out = response.getWriter();				
			
			
			if(res > 0) {
				
				// println으로 응답이 끝나기때문에 response를 또 하지 않고 밑에 out.flush()에서 끝나버림 그렇기 때문에
				// 스크립트 구문안에 location으로 가라고 말해주어야함
				// 즉 하단의 명령문과 out명령어 둘중 하나만.
				out.println("<script type='text/javascript'> alert('성공'); location.href='controller.do?command=list' </script>");
				out.flush();
				
				//response.sendRedirect("controller.do?command=list");
				
				// 아래처럼 updateres에서 작성페이지로 보내기, 작성완료된것 처리하기를 한번에 하면
				// 객체지향 프로그램 : 객체를 기능별로 나눈다 에 맞지않고 유지보수가 힘들어지며
				// 한가지 기능이 무거워질때 다른기능의 응답이 느려질 수 있기때문에 좋지않다
				
				/*
				request.setAttribute("list", dao.selectList());
				RequestDispatcher dispatch = request.getRequestDispatcher("boardlist.jsp");
				dispatch.forward(request, response);
				*/
				
			} else {				
				
				//out.println("<script type='text/javascript'> alert('실패'); </script>");
				//out.flush();
				
				// Redirect로 보내면 seq를 보내는 문제는 둘째치고 해당 seq값에 해당하는 title,content등등을 뿌려줘야하기 때문에
				// selectOne 메서드가 필요하고 따라서 forward를 사용해주어야함
				//response.sendRedirect("boardupdate.jsp");
				
				
				request.setAttribute("list", dao.selectOne(seq));
				RequestDispatcher dispatch = request.getRequestDispatcher("boardupdate.jsp");
				dispatch.forward(request, response);
				
			}
			
		} else if (command.equals("update")) {
		
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			request.setAttribute("list", dao.selectOne(seq));
			RequestDispatcher dispatch = request.getRequestDispatcher("boardupdate.jsp");
			dispatch.forward(request, response);
	
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// post로 요청이 들어와도 doGet 메서드에서 처리하겠다
		doGet(request, response);
		
	}

}//class
