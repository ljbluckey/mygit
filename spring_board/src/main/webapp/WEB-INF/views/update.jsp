<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:useBean id="boardVO" class="com.increpas.team03.model.BoardVO"
	scope="request"></jsp:useBean>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function up() {//function up(fo) {

		//폼이후 비밀번호 일치 검증 (수정권한 검증) : 테이블 비밀번호와 폼입력 비밀번호 비교
		//폼이전 고려
		//var pass = document.getElementById("password");
		//querySelectorAll(선택자) 해당 선택자로 선택되는 요소를 모두 선택함.
		//querySelector(선택자) 해당 선택자로 선택되는 요소를  선택함.
		var pass = document.querySelector("#password")
		if (pass.value != "${boardVO.password}") {
			alert("비밀번호가 다르다");
			pass.focus();
			pass.value = "";
			return;
		}
		//일치하면 서버로 전송 forms[0]: 첫번째 폼태그 객체
		document.forms[0].submit();//폼태그name이 없어서
		//document.fo.submit();//폼태그name이 없어서
		//document.frm.submit();
	}
</script>
</head>
<body>
	<h2 align="center">게시판 글 수정</h2>
	<hr />
	<form method="post" action="board_update_action.do">
		<!-- name="frm" -->
		<input type="hidden" name="seq" value="${boardVO.seq}" />
		<!-- 수정할 글 번호 -->
		<table border="1">
			<tr>
				<th>글제목</th>
				<td><input type="text" name="title" value="${boardVO.title}" /></td>
			</tr>
			<tr>
				<th>글쓴이</th>
				<td><input type="text" name="writer" value="${boardVO.writer}" /></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="password" id="password" /></td>
			</tr>
			<tr>
				<th>글내용</th>
				<td><textarea rows="5" cols="60" name="content">${boardVO.content}</textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="button" value="수정"
					onclick="up()" /><!-- onclick="up(this.form)" --></td>
			</tr>
		</table>
	</form>
</body>
</html>










