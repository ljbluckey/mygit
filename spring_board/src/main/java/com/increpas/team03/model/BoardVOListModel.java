package com.increpas.team03.model;

import java.util.ArrayList;
import java.util.List;

//페이징 글 목록(원 데이터 자체) 및 부가 정보를 저장하는 모델 객체
public class BoardVOListModel {	
	
	private List<BoardVO> boardVOList;	// 게시글의 목록
	private int requestPage;			// 요청 페이지 번호
	private int totalPageCount;		// 전체 페이지 수
	private int startRow;				// 요청(클릭) 페이지 시작 글번호
	private int endRow;					// 요청(클릭) 페이지 마지막 글번호
	
	
	// DB의 저장된 전체글의 개수가 0일때 리턴할 게시글 모델
	public BoardVOListModel() {
		this(new ArrayList<BoardVO>(), 0, 0, 0, 0);
	}
	
	public BoardVOListModel(List<BoardVO> boardVOList, int requestPageNumber,
			int totalPageCount, int startRow, int endRow) {
		this.boardVOList = boardVOList;
		this.requestPage = requestPageNumber;
		this.totalPageCount = totalPageCount;
		this.startRow = startRow;
		this.endRow = endRow;
	}

	public List<BoardVO> getBoardVOList() {
		return boardVOList;
	}
	//글목록의 글개수가 1개 이상이면 true
	//사용자 정의 메소드도 가능 : 일부<Smart Bean> 이라 부르기도 함
	//리스트 Empty 상태 : boolean 일때 get이 아닌 is로 메소드명 정의
	public boolean isHasBoardVO() {//hasBoardVO 속성은 없다
		return ! boardVOList.isEmpty();
	}

	public int getRequestPage() {
		return requestPage;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public int getStartRow() {
		return startRow;
	}

	public int getEndRow() {
		return endRow;
	}
	
}
