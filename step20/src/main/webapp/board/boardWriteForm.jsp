<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../script/boardScript.js">
</script>
</head>
<body>
	<h2 style="text-align: center;">글쓰기</h2>
	<form action="../board/boardWrite.do" method="post" name="frm2">
	<table border="1" style="margin: auto;">
		<tr>
			<td>제목</td>
			<td><input type="text" name="subject"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="content" rows="15" cols="45" style="width: 400px; height: 300px;"></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="글쓰기" onclick="buttun1()">
				<input type="reset" value="다시작성">
			</td>
		</tr>
	</table>
	</form>
</body>
</html>