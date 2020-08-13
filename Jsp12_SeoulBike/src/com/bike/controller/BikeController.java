package com.bike.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bike.dao.BikeDao;
import com.bike.dto.BikeDto;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@WebServlet("/BikeController")
public class BikeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
     
    public BikeController() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		BikeDao dao = new BikeDao();
		
		if(command.equals("first")) {
			
			response.sendRedirect("bike01.jsp");
			
		} else if (command.equals("first_db")){
			
			// 양이 많은 데이터를 DB에 저장하고 싶음
			
			if(dao.delete()) {
				System.out.println("삭제 성공");
			}
		
			String[] bikeList = request.getParameterValues("bike");
			List<BikeDto> bikes = new ArrayList<BikeDto>();
			
			for (int i = 0; i < bikeList.length; i ++) {
				String[] tmp = bikeList[i].split("/");
				BikeDto dto = new BikeDto(tmp[0],
										Integer.parseInt(tmp[1]),
										tmp[2],
										tmp[3],
										Integer.parseInt(tmp[4]),
										Double.parseDouble(tmp[5]),
										Double.parseDouble(tmp[6]));
				bikes.add(dto);
			}
			
			int res = dao.insert(bikes);
			if (res > 0) {
				System.out.println("insert성공");
			} else {
				System.out.println("insert 실패");
			}
			
			response.sendRedirect("bike01.jsp");
			
		} else if (command.equals("second")) {
			response.sendRedirect("bike02.jsp");
		} else if (command.equals("second_db")) {
			
			// obj 즉 데이터 받아옴
			String objString = request.getParameter("obj");
			//System.out.println(objString);
			
			// objString 형태로 바뀜 (문자열로 넘어온걸 json으로 바꿈)
			// 클라이언트에서 전달해준 obj라고 이름 붙여진 json문자열을 json객체로 바꿔둔것
			JsonElement element = JsonParser.parseString(objString);
			
			// json 객체로 바꿀것
			
			// json object 와 json elemeny와의 차이점 :
			/*
			 * jsonObject : name과 value가 한쌍인 애들이 들어감 즉 json객체. {k : v}형식의 객체 = key : 문자열, value : JsonElement로 나옴 뭐가 나올지 모르기 때문.
			 * 따라서 key를 통해 value를 호출하면 JsonElement로 나옴
			 * JsonElement : json 요소임 -> JsonObject, JsonArray, JsonPrimitive, JsonNull 등을 통틀어 JsonElement로 말함 즘 value
			 * json 객체의 값을 꺼내오면 일단 JsonElement로 받아주자는것
			 */
			
			//System.out.println(element.getAsJsonObject().get("DESCRIPTION"));
			
			List<BikeDto> bikes = new ArrayList<BikeDto>();
			// .getAsJsonObject() 지정된 멤버를 JsonObject로 가져 오는 편의 메소드
			// .getAsJsonArray() 지정된 멤버를 JsonArray로 가져 오는 편의 메소드입니다.
			
			// element.getAsJsonObject().get("DATA").getAsJsonArray()
			// element.getAsJsonObject() : {k : v} 형태로 가져오겠다	(return JsonObject)
			// .get("DATA") : {k : v}형식에서 k가 "DATA"인 v를 가져오겠다. (return JsonElement)
			// .getAsJsonArray() : 위에서 가져온 v에서 [{},{},...,{}] 형태로 가져오겠다.
			// 즉 배열 형태
			for (int i = 0; i < element.getAsJsonObject().get("DATA").getAsJsonArray().size(); i++) {
				
				JsonObject tmp = element.getAsJsonObject().get("DATA")
										.getAsJsonArray().get(i).getAsJsonObject();
				// .get(i) : [{},{},... ,{}]에서 i번지 즉 Json배열의 i번지의 {}를 가져오겠다. (return JsonElement) 안에있는게 어떤 타입인지 모르니 JsonElement
				// .getAsJsonObject(); : return JsonObject = 위의 i번지를  Json으로 가져온다 이걸 하지 않으면 JsonElement가 리턴됨 {k:v}
				
				// 바로 아래 두줄을 한줄로 바꿔줌
				// ("addr_gu") 해당 key에 맞는 JsonElement
				String addr_gu = tmp.get("addr_gu").getAsString();
				int content_id = tmp.get("content_id").getAsInt();
				
				// 위의 한줄을 펼쳐놓은 형태
				JsonElement content_nm_je = tmp.get("content_nm");
				String content_nm = content_nm_je.getAsString();
				
				JsonElement new_addr_je = tmp.get("new_addr");
				String new_addr = new_addr_je.getAsString();
				
				int cradle_count = tmp.get("cradle_count").getAsInt();
				double longitude = tmp.get("longitude").getAsDouble();
				double latitude = tmp.get("latitude").getAsDouble();
				
				BikeDto dto = 
						new BikeDto(addr_gu, content_id, content_nm, new_addr, cradle_count, longitude, latitude);
				
				bikes.add(dto);
			
			}
			
			if(dao.delete()) {
				System.out.println("삭제 성공");
			}
			
			int res = dao.insert(bikes);
			if(res > 0) {
				System.out.println("저장 성공");
			} else {
				System.out.println("저장 실패");
			}
			
			// 문자가 아닌 res를 문자로 변환
			response.getWriter().append(res+"");
			
		}
		
		
		
	}

}
