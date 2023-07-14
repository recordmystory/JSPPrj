<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홀/짝수 MVC 구조</title>
</head>
<%
pageContext.setAttribute("result", "hello");
%>
<body>
	<%=request.getAttribute("result")%>입니다.
	<!-- EL 표기법 -->

	${names[1]}
	<br> ${notice.id}
	<br> ${result}
	<br> ${requestScope.result}
	<br> ${param.n/2}
	<br> ${header.accept}

	<!-- gt : 크거나 같다.
		 ge : 작거나 같다. -->

	<!--  empty / not empty
			${empty param.n} 
				==
		  ${param.n==null||param.n==''} -->

<!-- 삼항연산자 사용
 ${empty param.n?'값이 비어 있습니다.':param.n}-->

</body>
</html>