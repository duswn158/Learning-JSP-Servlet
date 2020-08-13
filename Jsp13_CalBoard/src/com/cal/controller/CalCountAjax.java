package com.cal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cal.dao.CalDao;

import net.sf.json.JSONObject;

@WebServlet("/countajax.do")
public class CalCountAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CalCountAjax() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		// text/json (텍스트인데 json)
		response.setContentType("text/json; charset=UTF-8");
		
		String id = request.getParameter("id");
		String yyyyMMdd = request.getParameter("yyyyMMdd");
		System.out.println("id : "+ id +"\t yyyyMMdd : "+ yyyyMMdd);
		
		CalDao dao = new CalDao();
		int count  = dao.getViewCount(id, yyyyMMdd);
		System.out.println("일정 갯수 : " + count);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("calcount", count);
				
		// json-lib
		// map을 json object로 변환
		JSONObject obj = JSONObject.fromObject(map);
		PrintWriter out = response.getWriter();
		obj.write(out); // json은 특이하게 out.print가 아니라 이걸 사용함
		
	}

}
