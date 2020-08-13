package com.cal.score;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;


@WebServlet("/score.do")
public class CalScore extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public CalScore() {
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// ajax에서 넘어온 값 받아옴
		String name = request.getParameter("name");
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));
		
		// 평균
		int sum = kor + eng + math;
		double avg = sum/3.0;
		
		// json 객체로 바꿈		
		// import org.json.simple.JSONObject; -> json-simple-1.1.jar
		// json-simple-1.1.jar = Map을 상속받은 Object객체를 만들고있음
		// {"key" : "value"}
		JSONObject obj = new JSONObject();
		obj.put("name", name);
		obj.put("sum", sum);
		obj.put("avg", avg);
		
		// json 형태를 json객체의 형태로 바꾸어줌
		System.out.println("servlet에서 보내는 msg : " + obj.toJSONString());
		// servlet에서 보내는 msg : {"avg":1890.3333333333333,"name":"ㅇㅁ","sum":5671}
		// json 형태의 문자열
		
		// 응답
		PrintWriter out = response.getWriter();
		//out.print(obj); // 객체를 응답시키면 주소값이 나오지만 내부적으로 string으로 바꿔줌
		out.print(obj.toJSONString());
		
	}

}
