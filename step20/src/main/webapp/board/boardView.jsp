<%@page import="board.dao.BoardDAO"%>
<%@page import="board.bean.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" style="border-collapse: collapse;">
		<tr>
			<td colspan="3" width="450">
				<font size="5">${boardDTO.getSubject()}</font>
			</td>
		</tr>
		<tr>
			<td width="150">글번호: ${boardDTO.getSeq()}</td>
			<td width="150">작성자: ${boardDTO.getName()}</td>
			<td width="150">조회수: ${boardDTO.getHit()}</td>
		</tr>
		<tr  width="450" height="400" style="top: 50px;">
			<td colspan="3" valign="top" >
				<pre>${boardDTO.getContent()}</pre>
			</td>
		</tr>
	</table>
	
	<input type="button" value="목록" onclick="location.href='boardList.do?pg=${pg}'">
	<c:if test="${memId==boardDTO.id }">
	
		<input type="button" value="글수정" onclick="location.href='boardModifyForm.jsp?seq=${seq}&pg=${pg}'">
		<input type="button" value="글삭제" onclick="location.href='boardDelete.do?seq=${seq}&pg=${pg}'">
	</c:if>
</body>
</html>