package com.my.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.score.Score;

@WebServlet("/mycontroller.do")
public class MYController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MYController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("[" + command + "]");
		
		if(command.equals("basic")) {
			
			response.sendRedirect("basic-arithmetic.jsp");
			
		} else if(command.equals("eltest")) {
			
			Score sc = new Score("홍길동", 100, 40, 60);
			
			request.setAttribute("score", sc);
			RequestDispatcher dispatch = request.getRequestDispatcher("eltest.jsp");
			dispatch.forward(request, response);
			
		} else if(command.equals("jstltest")) {
			List<Score> res = new ArrayList<Score>();
			
			for(int i = 10; i < 50; i+=10) {
				Score sc = new Score("이름"+i, 50+i, 50+i, 50+i);
				res.add(sc);
			}
			
			request.setAttribute("list", res);
			RequestDispatcher dispatch = request.getRequestDispatcher("jstltest.jsp");
			dispatch.forward(request, response);
			
		}
		
	}

}//class
