
$(function(){
	// 로딩된 후에 function 실행
	getBike();
	
});

//"DESCRIPTION"  : {문자열}  jsonobject 즉 json
//"DATA" : {배열} jsonarray 제이슨 배열
// json object안에 json string이 들어가있다

function getBike(){
	
	$.getJSON("json/bike.json", function(data){
		// jQuery.each() $.each(data, function(key, val)
		// data가 key와 val 하나하나에 적용된다
		// 이 $.each()함수는 $ (selector) .each () 와 동일하지 않으며 jQuery 객체를 독점적으로 반복하는 데 사용됩니다.
		// 이 $.each()함수는 객체이든 배열이든 모든 컬렉션을 반복하는 데 사용할 수 있습니다. 
		// data = DESC : {} 1개, DATA : [] 1개 즉 key두개 value 2개
		// 즉 아래의 .each는 2번돔
        $.each(data, function(key, val){
            if (key == "DESCRIPTION"){
                $("table").attr("border",1);
                $("thead").append(
                    "<tr>"+
                    // val = json object 이기 때문에 .key하면 value값이 나옴
                        "<th>"+val.ADDR_GU+"</th>"+
                        "<th>"+val.CONTENT_ID+"</th>"+
                        "<th>"+val.CONTENT_NM+"</th>"+
                        "<th>"+val.NEW_ADDR+"</th>"+
                        "<th>"+val.CRADLE_COUNT+"</th>"+
                        "<th>"+val.LONGITUDE+"</th>"+
                        "<th>"+val.LATITUDE+"</th>"+
                    "</tr>"
                );
                // else = key가 DATA 라면
                // val = jsonArry
            } else {
            	var list = val;
            	for (var i = 0; i < list.length; i++){
            		// list[i] 번지에는 json object가 들어있음 즉 첫번째줄
            		var str = list[i];
            		$("tbody").append(
            				"<tr>"+
            					"<td>"+str.addr_gu+"</td>"+
            					"<td>"+str.content_id+"</td>"+
            					"<td>"+str.content_nm+"</td>"+
            					"<td>"+str.new_addr+"</td>"+
            					"<td>"+str.cradle_count+"</td>"+
            					"<td>"+str.longitude+"</td>"+
            					"<td>"+str.latitude+
            						"<input type='hidden' name='bike' value='"+
            							str.addr_gu+"/"+
            							str.content_id+"/"+
            							str.content_nm+"/"+
            							str.new_addr+"/"+
            							str.cradle_count+"/"+
            							str.longitude+"/"+
            							str.latitude+"'/>"+
            						"</td>"+
            					"</tr>"
            		);
            	}
            }
        });
    });
	
}