$(function() {
	// 버튼 클릭시
	$("#weaView").click(
			function() {
				
				// WeatherOpenServlert.java 로 넘김
				var url = "weatherOpen";
				// 주소 옵션 선택한 값 가져온다
				var code = $("#address option:selected").val();
				
				//ajax (비동기)
				$.ajax({
					//GET방식
					type : "GET",
					// weatherOpen?code=가져온 옵션의 value
					url : url + "?code=" + code,
					// 받아오는 데이터를 text로 받아옴
					dataType : "text",
					
					// weatherInfo.jsp까지 모두 성공시
					success : function(data) {
						
						// 받아온 데이터의 문자열 좌우에서 공백을 제거
						var temp = $.trim(data);
						
						// JSON 문자열의 구문을 분석하고, 그 결과에서 JavaScript 값이나 객체를 생성합니다
						// 위의 데이터의 공백을 제거한것을 가져와서 자바스크립트 객체생성
						// json문자열을 json객체로 바꾸어준다.
						var obj = JSON.parse(temp);
						// JavaScript 값이나 객체를 JSON 문자열로 변환합니다. parse와 반대
						JSON.stringify();
						
						// 객체.name key 값으로 value를 불러옴
						$("#pubDate").val(obj.pubDate);
						$("#temp").val(obj.temp);
						$("#x").val(obj.x);
						$("#y").val(obj.y);
						$("#reh").val(obj.reh);
						$("#pop").val(obj.pop);
						$("#wfKor").val(obj.wfKor);
						
						// 날씨 value
						var weather_condition = obj.wfKor;
						// 각 날씨별 이미지 뿌려줌
						if (weather_condition == "맑음"){
							$("#weather_img").attr("src","/Jsp11_Weather/image/sun.png");
						}else if (weather_condition == "비"){
							$("#weather_img").attr("src","/Jsp11_Weather/image/rain.png");
						}else if (weather_condition == "눈"){
							$("#weather_img").attr("src","/Jsp11_Weather/image/snow.png");
						}else if (weather_condition == "흐림"){
							$("#weather_img").attr("src","/Jsp11_Weather/image/cloud.png");
						}else if (weather_condition == "구름 조금"){
							$("#weather_img").attr("src","/Jsp11_Weather/image/cloud_sun.png");
						}else{
							$("#weather_img").attr("src","/Jsp11_Weather/image/etc.png");
						}
					},
					error : function() {
						alert("정보를 불러오는데 실패하였습니다.");
					}
				});
			});
});
