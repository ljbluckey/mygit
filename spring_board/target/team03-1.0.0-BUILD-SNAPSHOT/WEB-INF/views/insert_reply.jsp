<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="resources/css/style.css" rel="stylesheet">
</head>
<body>
	<h2 style="text-align: center;">게시판 답글 등록</h2>
	<hr />


	<form:form method="post" action="board_insert_reply.do"
		enctype="multipart/form-data" commandName="board">

		<!-- 4개 원글(부모글)행 정보(부모글번호,그룹번호(부모글번호),순번,들여쓰기 폭 개수)를 답글과 함께 컨츠롤러에 전달 -->
		<input type=hidden name="seq" value="${board.seq}"> <input
			type=hidden name="bref" value="${board.bref}"> <input
			type=hidden name="bstep" value="${board.bstep}"> <input
			type=hidden name="blevel" value="${board.blevel}">
		<table>
			<tr>
				<th>글제목</th>
				<td>
				<!-- type=search 삭제 x 아이콘 -->
				<input type="text" name="title" value="${board.title}"
					onclick="this.value=''" /> <form:errors path="title"/></td>
			</tr>
			<tr>
				<th>글쓴이</th>
				<td><input type="text" name="writer" /> <form:errors path="writer"/></td>
			</tr>
			<tr>
				<th>패스워드</th>
				<td><input type="password" class="password" name="password" /> 
				<form:errors path="password"/>
				</td>
			</tr>
			<tr>
				<th>글내용</th>
				<td><textarea rows="5" cols="60" name="content"></textarea></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td><input type="file" name="mfile"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="등록" />
				</td>
			</tr>
		</table>
	</form:form>

</body>
</html>










