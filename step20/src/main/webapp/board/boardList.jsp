<%@page import="board.dao.BoardDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="board.bean.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">

	function isLogin(seq) {
		if (${memId==null}) {
			alert("먼저 로그인 하세요.");
			location.href="../member/loginForm.do";
		} else {
			location.href="../board/boardView.do?seq=" + seq + "&pg=" + ${pg};
		}
		
	}
</script>

<style type="text/css">
p{
	margin-left: 20px;
	text-overflow: ellipsis;
	white-space: nowrap;
	width: 150px;
	overflow: hidden;
}
#paging{
	color: blue;
	text-decoration: none;
}
#currentpaging{
	color: red;
	text-decoration: underline;
}
#subjectA:link{
	color: black;
	text-decoration: none;
}
#subjectA:visited {
	color: black;
	text-decoration: none;
}
#subjectA:achive{
	color: black;
	text-decoration: none;
}
#subjectA:hover {
	color: blue;
	text-decoration: underline;
}
</style>
</head>
<body>
	<table border="1" style="width: 500px">
		<tr  style="background: yellow; text-align: center;">
			<td width="70">글번호</td>
			<td width="100">제목</td>
			<td width="100">작성자</td>
			<td width="100">작성일</td>
			<td width="150">조회수</td>
		</tr>
		<c:forEach var="dto" items="${list}">		
			<tr>
			<td><a id="subjectA" href="#" onclick="isLogin(${dto.getSeq()})" ><p align="center">${dto.getSeq()}</p></a></td>
					<td><a id="subjectA" href="#" onclick="isLogin(${dto.getSeq()})"><p align="center">${dto.getSubject()}</p></a></td>
					<td>${dto.getId()}</td>
					<td>${dto.getLogtime()}</td>
					<td>${dto.getHit()}</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5" align="center">
						<c:if test="${startPage>3}">
							[<a id="paging" href="../board/boardList.do?pg=${startPage-1}">이전</a>]
						</c:if>
						<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
							<c:if test="${pg==i}">
								[<a id="currentpaging" href="../board/boardList.do?pg=${i}">${i}</a>]
							</c:if>
							<c:if test="${pg!=i}">
								[<a id="paging" href="../board/boardList.do?pg=${i}">${i}</a>]
							</c:if>
						</c:forEach>
						<c:if test="${endPage<totalP}">
								[<a id="paging" href="../board/boardList.do?pg=${endPage+1}">다음</a>]
						</c:if>
				</td>
		</tr>
	</table>
	<a href="../board/boardWriteForm.do">새글 쓰기</a>
</body>
</html>