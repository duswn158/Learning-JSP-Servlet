package com.cal.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cal.dao.CalDao;
import com.cal.dto.CalDto;
import com.cal.util.Utils;

@WebServlet("/cal.do")
public class CalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
    public CalController() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		
		CalDao dao = new CalDao();
		
		if(command.equals("calendar")) {
			response.sendRedirect("calendar.jsp");
			
		} else if(command.equals("insertcal")) {
			
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String date = request.getParameter("date");
			String hour = request.getParameter("hour");
			String min = request.getParameter("min");
			
			// mdate 12자리 테이블값 생성 (String) ex) 2020718232  즉 11자리, 9이하인 애들은 1자리로 출력되기 때문에 바꾸어주어야함
			String mdate = year + Utils.isTwo(month) + Utils.isTwo(date) + Utils.isTwo(hour) + Utils.isTwo(min);
			
			String id = request.getParameter("id");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			int res = dao.insertCalBoard(new CalDto(0, id, title, content, mdate, null));
			
			if (res > 0) {
				response.sendRedirect("cal.do?command=calendar");
			} else {
				request.setAttribute("msg", "일정 추가 실패");
				dispatch(request, response,"error.jsp");
			}
			
		} else if(command.equals("list")) {
			
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String date = request.getParameter("date");
			
			String yyyyMMdd = year + Utils.isTwo(month) + Utils.isTwo(date);
			
			List<CalDto> list = dao.getCalList("kh", yyyyMMdd);
			request.setAttribute("list", list);
			dispatch(request, response, "calendarlist.jsp");
			
		} else if(command.equals("select")) {
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			CalDto dto = dao.selectOne(seq);
			
			request.setAttribute("dto", dto);
			dispatch(request, response, "caldetail.jsp");
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doGet(request, response);
	}
	
	private void dispatch(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

}
