
<%@page import="board.dao.BoardDAO"%>
<%@page import="board.bean.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<script type="text/javascript">
	/*<body onload="함수명">과 같은 기능*/
	if(${result > 0}) {
		alert("저장 성공");
		location.href="boardList.do?pg=1";
	} else {
		alert("저장 실패");
		location.href="boardWriteForm.do";
	}
</script>
</head>
<body>


</body>
</html>