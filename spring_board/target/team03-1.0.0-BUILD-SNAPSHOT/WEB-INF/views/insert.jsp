<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 각종 태그라이브러리(클래스에 상응하는 태그 집합) 포함 -->
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
	<h2 align="center">게시판 글 등록</h2>
	<hr />

	<!-- 최초 화면 출력시 모델(요청영역에 저장)에서  board 이름의 커맨드 객체 검색 @ModelAttribute("board")-->
	<form:form method="post" action="board_insert.do" enctype="multipart/form-data" 
	commandName="board">

		<table>
			<tr>
				<th>글제목</th>
				<td><input type="text" name="title" value="${board.title}"/> <form:errors path="title"/></td>
			</tr>
			<tr>
				<th>글쓴이</th>
				<td><input type="text" name="writer" value="${board.writer}"/> <form:errors path="writer"/></td>
			</tr>
			<tr>
				<th>패스워드</th>
				<td><input type="password" class="password" name="password"/> <form:errors path="password"/>

				</td>
			</tr>
			<tr>
				<th>글내용</th>
				<td><textarea rows="5" cols="60" name="content" >${board.content}</textarea></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td><input type="file" name="mfile"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="등록" /></td>
			</tr>
		</table>
	</form:form>

</body>
</html>










