<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.increpas.team03.model.BoardVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	//브라우저 캐싱 X 
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.addHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 1L);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<head>
<title>게시글 목록</title>
<!-- <style type="text/css">
table,th,td {
	border: 1px, solid,black;
	border-collapse:collapse; 
}
</style> -->
<!-- 웹브라우저의  team03뒤의 /가 webapp-->
<link href="resources/css/style.css" rel="stylesheet">
</head>
<body>
	<h1>게시글 목록</h1>
	<table border="1">
		<!-- totalPageCount = 1 -->
		<c:if test="${listModel.totalPageCount > 0}">
			<tr>
				<!-- 1-2 [1/2] :글시작번호- 글마지막 번호 현재페이지 1 / 전체 페이지수 2  -->
				<td colspan="5">${listModel.startRow}-${listModel.endRow}
					[${listModel.requestPage}/${listModel.totalPageCount}]</td>
			</tr>
		</c:if>

		<tr>
			<th>글 번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>

		<c:choose>
			<%--
				choose 뒤에 !-- 주석태그 달지 말것. 에러 남.
				BoardVOListModel.isHasBoardVO 호출
				글이 비존재하면 false 리턴
				글이 존재하면 true 리턴
			 --%>
			<c:when test="${listModel.hasBoardVO == false}">
				<tr>
					<td colspan="5">게시글이 없습니다.</td>
				</tr>
			</c:when>
			<c:otherwise>
				<!-- 글을 1행씩 boardVO 에 저장시켜서 출력함 -->
				<c:forEach var="boardVO" items="${listModel.boardVOList}">
					<tr>
						<td>${boardVO.seq}</td>
						<td>
							<!-- 답글에 대한 들여쓰기 체크 -->
							<c:if test="${boardVO.blevel > 0}">
								<!-- 들여쓰기 횟수만큼 "-" 반복 -->
								<c:forEach begin="1" end="${boardVO.blevel}">-</c:forEach>&gt;
							</c:if> 
							<%--  
								<c:set var="query" value="seq=${boardVO.seq}&pn=${listModel.requestPage}"/>
								<a href='<c:url value="/board_detail.do?${query}"/>'> 
							--%> 
							<!-- 글 제목 링크 : 상세보기 
							c:url 상대경로 board_detail.do에 컨텍스트 붙여서 절대경로로 표현
							글번호와 현재 페이지번호(목록으로 되돌아올때 직전 보고 있던 해당 페이지가 노출 )를 전달-->
							<a href='<c:url value="/board_detail.do?seq=${boardVO.seq}&pn=${listModel.requestPage}"/>'>
								${boardVO.title} 
							</a>
						</td>
						<td>${boardVO.writer}</td>
						<td>${boardVO.regdate}</td>
						<td>${boardVO.hitcount}</td>
					</tr>
				</c:forEach>
				<tr>
					
					<!-- 페이지네비게이션 구성
						 : 최신 트렌트는 클라이언트단의 JQuery, Json에서 처리함 -->
					<td colspan="5">
					    <c:if test="${beginPage > 10}"> <!-- 예: 이전 [11][12] "이전"클릭시 10번으로-->
							<a href='<c:url value="/board_list.do?pn=${beginPage-1}"/>'>이전</a>
						</c:if> 
						<!-- 이전과 다음사이의 페이지 번호들 [9][10][11][12]-->
						<c:forEach var="pno" begin="${beginPage}" end="${endPage}">
							<a href='<c:url value="/board_list.do?pn=${pno}" />'>[${pno}]</a>
						</c:forEach> 
						<c:if test="${endPage < listModel.totalPageCount}"><!-- 예: [9][10] 다음 "다음"클릭시 11번으로-->
							<a href='<c:url value="/board_list.do?pn=${endPage + 1}"/>'>다음</a>
						</c:if>
					</td>
					
				</tr>
			</c:otherwise>
		</c:choose>

		<tr>
			<td colspan="5"><input type="button" value="글쓰기"
				onclick="location.href='board_insert.do';" /></td>
		</tr>
	</table>
	
	
	
</body>
</html>