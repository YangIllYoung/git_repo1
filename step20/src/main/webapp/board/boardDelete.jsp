<%@page import="board.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%
    	System.out.println("boardDelete.jsp진입");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	/*<body onload="함수명">과 같은 기능*/
	
		 if(${result>0}){ 
			alert("글 삭제 성공");
			location.href="boardList.do?pg=1";
		}else{ 
			alert("글 삭제 실패");
			location.href="boardList.do?pg=1";
		} 
		 
</script>
</head>
<body>

</body>
</html>