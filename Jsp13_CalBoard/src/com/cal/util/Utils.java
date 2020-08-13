package com.cal.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import com.cal.dto.CalDto;

public class Utils {
	
	private String toDates;
	
	
	public String getToDates() {
		return toDates;
	}	
	
	// dto.mdate를 calendarlist.jsp에서 받아옴
	// 년월일시분 12자리  ex) 202007131214
	public void setToDates(String mdate) {
		// yyyy-MM-dd hh:mm:ss(00)
		// 각각 잘라서 년, 월, 일, 시, 분을 잘라 년-월-일 "공백" 시:분:00
		String m = mdate.substring(0, 4) + "-"
				+ mdate.substring(4, 6) + "-"
				+ mdate.substring(6, 8) + " "
				+ mdate.substring(8, 10) + ":"
				+ mdate.substring(10) + ":00";
		
		// 날짜를 원하는 형식으로 파싱해준다 (포멧설정)
		// yyyy-MM-dd HH mm (대소문자 구분, 각각 대소문자, 문자 따라 다르게 변환 api참조)
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일 HH시mm분");
		
		/*
		 * java.util.Date : 오라클의 날짜타입과 연동 불가
		 * java.sql.Date : 오라클의 날짜타입과 연동 가능
		 */
		
		// java.sql
		// java.sql.Date의 valueOf(String s) 메서드는 입력받 문자열 값을 가지고 날짜(Date)로 변경해 준다.
		// 이 때 주의할 것은 날짜형식이 yyyy-mm-dd로 되어야 한다.
		// java.sql.Timestamp -> java.Date를 상속받는다
		// 둘의 차이 : Date = 나노초까지 출력불가 밀리세컨드 출력 , Timestamp = 나노초(나노세컨드) 출력
		// JDBC 타임 스탬프 이스케이프 형식의 문자열 객체를 타임 스탬프 값으로 변환합니다.
		// return = 해당 타임 스탬프 값
		// Timestamp : yyyy-MM-dd hh:mm:ss 숫자로 되어있는 문자열을 받아 날짜로 만들어줌 뒤에 ff의 형식을 더 넣으면 나노초도 반환 
		Timestamp tm = Timestamp.valueOf(m);
		
		// toDates에 sdf변수의 포멧 모양으로 변수tm을저장
		// .format() 날짜를 날짜 / 시간 문자열로 형식화.
		toDates = sdf.format(tm);
		
	}


	// 한자리수 숫자에 0 붙여주는 메서드
	// 만일 들어온 숫자가 1자리라면 2자리로 변환
	public static String isTwo(String msg) {
		
		return (msg.length() < 2) ? "0"+msg : msg;
		
	}
	
	// 달력의 토요일, 일요일, 평일 색상 설정
	// 해석 숙제
	public static String fontColor(int date, int dayOfWeek) {
		
		String color = "";
		
		// 시작요일 -1 즉 해당월의 시작요일에 해당하는 숫자 -1 (그 날부터 시작되니까 하루전부터 세어야함) + 해당 날짜 % 7 == 0
		// ex) 2020년 7월일때, (4-1 = 3) + 4(토요일) = 7 즉 토요일 에는 파란색으로 칠하고,
		if((dayOfWeek-1 + date)%7 == 0) {
			color = "blue";
			//System.out.println(dayOfWeek-1 + " , " + date);
		} else if((dayOfWeek-1 + date)%7 == 1) {
			// 8번째 일 즉 일요일에는 빨간색
			color = "red";
		} else {
			// 나머지는 검정색
			color = "black";
		}
		
		return color;
	}
	
	// 일정의 제목이 길때 일정 글자수 이상부터는 ...으로 표기
	public static String getViewTitle(int i, List<CalDto> clist) {
		
		String date = isTwo(i+"");
		String res = "";
		
		// list 이기 때문에 안에있는값 다 비교해서 해당 날짜에 일정만 가지고 와야하기 때문에 for each문
		for (CalDto dto : clist) {
			// clist 일정(rownum으로 정렬한 dao메서드 리턴값)중에 내가 넣어준 일정과 같은 날짜의 일정이라면
			// clist에 date값과 현재 date값이 같다면. 삼항연산자로 비교 + p태그 안에 넣어줌
			// yyyyMMddHHmm 에서 date 잘라옴.
			if(dto.getMdate().substring(6, 8).equals(date)) {
				res += "<p>"
						// 삼항연산자 (글자 길이 따라 출력 조정)
					+ ((dto.getTitle().length() > 6) ? 
							dto.getTitle().substring(0, 6) + "..." :
								dto.getTitle())
					+"</p>";
			}
		}
		
		return res;
	}
	
}
