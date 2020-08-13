
$(function(){
	parseBike();
});

function parseBike(){
	// getJSON() 함수를 통하여 json파일을 읽어올수 있고
	// 서버로부터 받은 JSON 데이터를 로드하는 것, JSON DATA를 읽어오는 ajax
	// URI가 던져주는 결과를 JSON 형태로 받아오는 역할을 한다
	// .getJSON(url[,data][,success]
	// url : 정보를 요청할 혹은 읽어올 파일 위치정보
	// data : 서버로 보낼 data
	// success : 요청이 성공하면 실행될 콜백함수
	
	// bike.json에서 json객체를 data이름으로 가져와 함수실행 (ajax의 형태를 줄여놓은것 즉 비동기통신)
	$.getJSON("json/bike.json", function(data){
		$.ajax({
			// 서블릿 url
			// url : "bike.do?command=second_db" 좋지않음
			url : "bike.do",
			//post방식
			method:"post",
			// JSON.stringify() 메서드는 JavaScript 값이나 객체를 JSON 문자열로 변환합니다.
			// data를 json 문자열로 변환해 obj라는 key로 묶어서 컨트롤러로 보냄
			// 즉 json객체를 json문자열로 바꿔서 서버로 보냄
			data:{"obj":JSON.stringify(data),"command":"second_db"},
			
			success : function(msg){
				if (msg > 0){
					
					alert(msg);
					//bike.json을 key와 val로 나누어서
					$.each(data, function(key, val){
						
						if (key == "DESCRIPTION"){
							
							$("table").attr("border", 1);
							// tr태그를 생성하는 변수
							var $tr = $("<tr>");
							/*
							var obj = {
									id : "dd"
							}*/
							
							// for in
							/*
							 * for ... in 문은 객체의 모든 non-Symbol, enumerable properties을 반복합니다.
							 * 즉, object에 있는 항목들을 반복적으로 반환하여 '무언가'를 할 수 있게 해줍니다
							 * 객체를 하나씩 모두 꺼내서 var i변수로 사용 foreach와 비슷
							 * https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Statements/for...in
							 */
							// val = json
							// val을 하나씩 i에 넣을건데 key:value로 되어있다면 key값을 가져와줌
							for(var i in val){
								// 객체 리터럴형태로 뽑아온거임
								// tr태그 내부에 th태그를 만들어서 그 내부에  value값 넣음
								// for in이 key: value를 나누어 가져와줌
								
								//alert(i); //key값 가져옴
								//alert(var[i])// value값 가져옴
								// mdn참고... 그렇게 만들어놓았다구함
								
								// 제일 위에 한줄을 만들어줌
								$tr.append($("<th>")).html(val[i]);
							}
							
							$("thead").append($tr);
							
						} else {
							for(var i = 0; i < val.length; i++){
								var $tr = $("<tr>");
								// jsonArray 이기 때문에 i번지의 j번지를 가져와야함
								for (var j  in val[i]){
									// j번지의 key값을 불러오기 때문에 거기에 value를 불러올 수 있음
									$tr.append($("<td>").html(val[i][j]));
								}
								$("tbody").append($tr);
							}
						}
						
					});
				} else {
					alert("db 저장 실패...");
				}
			},
			error:function(){
				alert("통신 실패");
			}
		});
	});
}