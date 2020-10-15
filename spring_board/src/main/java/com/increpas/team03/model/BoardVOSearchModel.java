package com.increpas.team03.model;

import java.util.ArrayList;
import java.util.List;

//검색 페이징 글 목록(원 데이터 자체) 및 부가 정보를 저장하는 모델 객체
//
public class BoardVOSearchModel 
extends BoardVOListModel {
	String searchOption;//작성자 혹은 내용
	String keyword;//검색어
	
	public String getSearchOption() {
		return searchOption;
	}

	public void setSearchOption(String searchOption) {
		this.searchOption = searchOption;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "BoardVOSearchModel [searchOption=" + searchOption + ", keyword=" + keyword + "]";
	}

	
	
	
	
}
