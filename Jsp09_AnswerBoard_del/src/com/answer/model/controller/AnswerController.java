package com.answer.model.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.answer.model.biz.AnswerBiz;
import com.answer.model.dto.AnswerDto;


@WebServlet("/AnswerController")
public class AnswerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AnswerController() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("[" + command + "]");
		
		AnswerBiz biz = new AnswerBiz();
		
		if(command.equals("list")) {
			
			List<AnswerDto> list = biz.selectList();
			
			request.setAttribute("list", list);
			dispatch("boardlist.jsp", request, response);
			
		} else if(command.equals("insert")) {
			
			response.sendRedirect("boardwrite.jsp");
			
		} else if(command.equals("insertres")) {
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			
			AnswerDto dto = new AnswerDto(0,0,0,0,null,title,content,writer,null);
			
			int res = biz.insert(dto);
			
			if(res > 0) {
				response.sendRedirect("controller.do?command=list");
				System.out.println("성공");
			} else {
				response.sendRedirect("controller.do?command=list");
				System.out.println("시류ㅐ");
			} 
			
		} else if (command.equals("detail")) {
			
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			
			AnswerDto detail = biz.selectOne(boardno);
			
			request.setAttribute("detail", detail);
			dispatch("boarddetatil.jsp", request, response);
			
		} else if(command.equals("update")) {
			
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			
			AnswerDto dto = biz.selectOne(boardno);
			
			request.setAttribute("dto", dto);
			dispatch("boardupdate.jsp", request, response);
			
		} else if (command.equals("updateres")) {
			
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			
			AnswerDto dto = new AnswerDto(boardno, 0, 0, 0, null, title, content, writer, null);
			int res = biz.update(dto);
			
			if(res > 0) {
				response.sendRedirect("controller.do?command=list");
			} else {
				response.sendRedirect("controller.do?command=list");
				System.out.println("시르ㅠㅐ");
			}
			
		} else if (command.equals("delete")) {
			
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			
			int res = biz.delete(boardno);
			
			if(res > 0) {
				response.sendRedirect("controller.do?command=list");
			} else {
				response.sendRedirect("controller.do?command=list");
				System.out.println("시르패");
			}
			
		} else if (command.equals("answerwrite")) {
			
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			
			AnswerDto dto = biz.selectOne(boardno);
			
			request.setAttribute("dto", dto);
			dispatch("answerwrite.jsp", request, response);			
			
		} else if (command.equals("answerwriteres")) {
			
			int boardno = Integer.parseInt(request.getParameter("parentboardno"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			
			AnswerDto dto = new AnswerDto(boardno, 0, 0, 0, null, title, content, writer, null);
			int res = biz.answerproc(dto);
			
			if(res > 0) {
				response.sendRedirect("controller.do?command=list");
			} else {
				response.sendRedirect("controller.do?command=list");
				System.out.println("시르패");
			}
			
		} /*else if(command.equals("reply")) {
		
			// 리플이 달릴때 ㄴRE:ㄴRE: 가 아니라 ㄴRE:RE: 이런식으로 달리게
			// answerwrite 부분
			// 1. title이 ㄴRE: 로 저장 , 2. 1번에 ㄴRE:가 또 추가 즉 ㄴRE:ㄴRE:가 됨
			// replace로 받아오는 dto.title에 ㄴ을 공백으로 바꾸어줌
		
	         int parentno = Integer.parseInt(request.getParameter("boardno"));
	         
	         AnswerDto dto = biz.selectOne(parentno);
	         
	         dto.setTitle(dto.getTitle().replace("ㄴ", ""));
	         
	         request.setAttribute("dto", dto);	         
	         dispatch("reply.jsp", request, response);
	      }*/
		
	}
	
	public void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
		
	}
	
	public void jsResponse(HttpServletResponse response, String url, String msg) throws IOException {
		
		String s = "<script type='text/javascript'>" +
				"alert('"+ msg +"');" +
				"location.href = '" + url + "';"
				+ "</script>";
		
		response.getWriter().append(s);
	}

}
