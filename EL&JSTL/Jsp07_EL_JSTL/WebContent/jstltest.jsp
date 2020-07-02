<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>JSTL Page</h1>
	
	
	<!-- Jsp Standard Tag Library -->
   <table border="1">
      <tr>
         <th>이름</th>
         <th>국어</th>
         <th>영어</th>
         <th>수학</th>
         <th>총점</th>
         <th>평균</th>
         <th>등급</th>
      </tr>
      <!-- list 가져와서 뿌려줌, List, 배열 요소를 순서대로 반복해서 처리 -->
      <c:forEach items="${list }" var="score">
         <tr>
            <td>
            	<!-- if문, else는 존재하지 않음 -->
               <c:if test="${score.name eq '이름10' }">
                  <c:out value="홍길동">홍길동</c:out>
               </c:if>
               <!-- switch문 -->
               <c:choose>
                  <c:when test="${score.name eq '이름20' }">
                     <c:out value="${score.name }님!!"></c:out>
                  </c:when>
                  <c:when test="${score.name eq '이름30' }">
                     <c:out value="${score.name }"></c:out>
                  </c:when>                  
                  <c:otherwise>
                     <c:out value="누구지?"></c:out>
                  </c:otherwise>
               </c:choose>
            </td>
            <td>${score.kor }</td>
            <td>${score.eng }
            	<c:if test="${score.eng >= 70 }">
                     <c:out value="멋져!"></c:out>
                </c:if>
            </td>
            <td>${score.math }</td>
            <td>${score.sum }</td>
            <td>${score.avg }</td>
            <td>
            	<c:choose>
            		<!-- or연산 -->
	                <c:when test="${score.grade eq 'A' or score.grade eq 'B' }">
	                   <c:out value="pass"></c:out>
	                </c:when>  
	                <c:otherwise>
	                   <c:out value="fail"></c:out>
	                </c:otherwise>
               </c:choose>
            </td>
         </tr>
      </c:forEach>
      
   </table>

</body>
</html>