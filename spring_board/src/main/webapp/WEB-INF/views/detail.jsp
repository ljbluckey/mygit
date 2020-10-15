<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 웹브라우저의  team03뒤의 /가 webapp
1. 상대경로 -->
 <link href="resources/css/style.css" rel="stylesheet"> 
<!-- 컨텍스트가 변경되더라도 c:url가 자동으로 컨텍스트를 붙인다 
2. 절대경로-->
<%-- <link href="<c:url value='/resources/css/style.css'/>" rel="stylesheet"> --%>
<script>
//각 버튼 클릭시 해당 요청 URL을 지정
	function uf() {
		//현재글 번호 전달 
		location.href = "board_update.do?seq=${boardVO.seq}";
	}
	//현재글 번호와 들여쓰기  전달, 0이면 원글이고 답글은 0이 아니다. 
	//원글삭제시 답글도 함께 삭제 
	function df() {
		location.href = "board/board_delete.do?seq=${boardVO.seq}&blevel=${boardVO.blevel}";
	}
	function lf() {
		// 이전 페이지번호를 목록에게 전달
		location.href = "board_list.do?pn=${pn}";
	}
    //추가될 답글로 현재글(부모글)번호 전달 
	function rf() {
		location.href = "board_insert_reply.do?seq=${boardVO.seq}";
	}
</script>
</head>
<body>
	<h2 align="center">게시판 상세 글보기</h2>
	<hr />
	<!-- 스프링 model에 저장된 boardVO와 useBean의  boardVO가 
	       같은 객체-->
	<jsp:useBean id="boardVO" type="com.increpas.team03.model.BoardVO" scope="request" />
	<%
	  System.out.println("boardVO=" + boardVO.hashCode());
	%>
	
	<table border="1">
		<tr>
			<th>글번호</th>
			<td>${boardVO.seq}</td>
		</tr>
		<tr>
			<th>글제목</th>
			<td>${boardVO.title}</td>
		</tr>
		<tr>
			<th>글내용</th>
			<td>${boardVO.content}</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
			<%
			String  upPath=null;
			if(boardVO.getUploadPath()!=null){
				//파일명 한글처리 안하면 IE10 처럼 구버전의 다운 오류 
			upPath = URLEncoder.encode(boardVO.getUploadPath(),"utf-8");
			}
			%>
			<!-- EL 방식은  IE10 처럼 구버전에서는 다운 오류이므로  JSP 태그 사용 -->
			<%-- <a href='file_download.do?filename=${boardVO.uploadPath}'>${boardVO.uploadPath}</a></td> --%>
			<a href='file_download.do?filename=<%=upPath%>'>${boardVO.uploadPath}</a></td>
		</tr>

		<tr>
			<td colspan="2" align="center"><input type="button" value="답글"
				onClick="rf()"> <input type="button" value="수정"
				onclick="uf()" /> <input type="button" value="삭제" onclick="df()" />
				<input type="button" value="목록" onclick="lf()" /></td>
		</tr>
	</table>
</body>
</html>










