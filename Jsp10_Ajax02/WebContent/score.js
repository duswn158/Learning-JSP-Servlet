/**
 * 
 */

function getParameterValues(){
	
	var name = "name="+encodeURIComponent(document.getElementById("name").value);
	var kor = "kor="+document.getElementById("kor").value;
    var eng = "eng="+document.getElementById("eng").value;
    var math = "math="+document.getElementById("math").value;

    var queryString = "?"+name+"&"+kor+"&"+eng+"&"+math;

    return queryString;
	
}

function load(){
    
    var url = "score.do" + getParameterValues();
    //alert(url);
    
    httpRequest = new XMLHttpRequest();				// XHR(XMLHttpRequest) : 서버와 통신할때 필요한 객체(자바스크립트 객체)
    
    // 한번 넘어갈때마다 호출.
    // readystate가 change되는 이벤트가 발생한다면 callback 호출
    // callback -> 그냥 하단에 function 이름
    // 0에서 1번으로 갈때, 1에서 2로 갈때, ... 동작
    httpRequest.onreadystatechange=callback;		// State가 변경될때마다 처리할 함수를 호출 (callback)
    
    httpRequest.open("GET", url, true);				// true : 비동기 / false : 동기화 방식으로 동작
    
    httpRequest.send();								// .send() : get / .send(String) : post 방식
    
}

// callback : 무전기, call하면 back한다.
// 순간순간마다 요청해서 응답되고, 요청해서 응답되고 의 형태를 띈다
function callback(){
	
	alert("readyState : " + httpRequest.readyState);
	if(httpRequest.readyState == 4){
		alert("status : " + httpRequest.status);
		
		if(httpRequest.status == 200){
			var obj = JSON.parse(httpRequest.responseText);
			
			document.getElementById("result").innerHTML = 
				decodeURIComponent(obj.name) + "의 총점은 " +
				obj.sum + " 이고, 평균은 " + obj.avg + " 입니다.";
			
		} else {
			alert("데이터를 계산할 수 없습니다");
		}
	}
	
}

/*
 * XMLHttpRequest(XHR) : javascript object -> http를 통한 데이터 송수신 지원
 * 
 * on이 붙어있기때문에 이벤트 즉 readystate가 change되는 이벤트가 발생한다면.
 * <onreadystatechange>
 * 
 * - readyState
 * 0 : uninitialized - 실행(load)되지 않음
 * 1 : loading - 로드 중
 * 2 : loaded - 로드 됨
 * 3 : interactive - 통신 됨(상호 통신)
 * 4 : complete - 통신 완료
 * 
 * - status
 * 200 : 성공
 * 400 : bad request (post 방식인데 get으로 요청하거나 등등)
 * 401 : unauthorized
 * 403 : forbidden
 * 404 : not found
 * 500 : internal server error
 */