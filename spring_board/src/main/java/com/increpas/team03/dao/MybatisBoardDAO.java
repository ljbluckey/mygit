package com.increpas.team03.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.increpas.team03.model.BoardVO;


@Repository
public class MybatisBoardDAO {
	// CRUD
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate; // WEB-INF/config/board-common-config.xml 에서 선언한 내용
	
	// ----------R-------------
	/*
	 * public List select() { return
	 * sqlSessionTemplate.selectList("board_ns.selectAllBoards"); }
	 */

	
	/**
	 * 페이징 단위의 전체 글 조회 요청
	 * @param firstRow
	 * @param endRow
	 * @return
	 */
	public List<BoardVO> select(int firstRow, int endRow) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("firstRow", firstRow);
		map.put("endRow", endRow);
		
		//1-2 글행목록 List<BoardVO> 리턴
		return sqlSessionTemplate.selectList("board_ns.selectAllBoards",map);
	}

	
	/**
	 * 전체 글 갯수 리턴
	 * @return
	 */
	public int  selectCount() {		
		return sqlSessionTemplate.selectOne("board_ns.selectCount");
		
	}
	
	
	public void updateHitCount(String seq){
		sqlSessionTemplate.update("board_ns.updateHitCount", seq);
	}
	
	
	public BoardVO findBySeq(String seq) {
		/* BoardVO vo=new BoardVO();
		 * vo.setSeq(Integer.parseInt(seq));
		 * return (BoardVO)sqlSessionTemplate.selectOne(
		 * "board_ns.selectBySeqBoard", vo);
		 */
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("seq", seq);// 글번호 맵에 저장		

		return (BoardVO) sqlSessionTemplate.selectOne(
				"board_ns.selectBySeqBoard", map);

	}

	// ------------------------------
	// CUD
	// -----------------------------
	
	public void insert(BoardVO vo) {
//		HashMap<String, Object> map=new HashMap<String, Object>();
//		map.put("title", "abc");
//		map.put("content", "abc");
//		sqlSessionTemplate.insert("board_ns.insertBoard", map);
		
		int rowcnt= sqlSessionTemplate.insert("board_ns.insertBoard", vo);
		System.out.println(rowcnt + "행 삽입");
	}

	public void update(BoardVO vo) {
		int rowcnt= sqlSessionTemplate.update("board_ns.updateBoard", vo);
		System.out.println(rowcnt + "행 수정");
	}

	
	public void delete(int seq, int blevel) {
		BoardVO boardVO = new BoardVO();
		boardVO.setSeq(seq);
		boardVO.setBlevel(blevel);
		sqlSessionTemplate.delete("board_ns.deleteBoard", boardVO);
	}

	// insertReply()를 2개 메소드로 분리 
	//-> 서비스에서  2개 메소드를 트랜잭션으로 묶어서 호출하도록 수정 필요
//	public void insertReply(BoardVO vo) {
//		//기존답글순번이 1씩 증가 
//		sqlSessionTemplate.update("board_ns.replyUpdateBoard", vo);
//		//새답글순번이 1(원글순번(0)+1) ,들여쓰기는 원글들여쓰기+1
//		sqlSessionTemplate.insert("board_ns.insertReplyBoard", vo);
//
//	}	
	public void replyUpdateBoard(BoardVO vo) {
		//기존답글순번이 1씩 증가 
		sqlSessionTemplate.update("board_ns.replyUpdateBoard", vo);
	}
	public void insertReply(BoardVO vo) {
	
		//새답글순번이 1(원글순번+1) ,들여쓰기는 원글들여쓰기+1
		sqlSessionTemplate.insert("board_ns.insertReplyBoard", vo);

	}


	public List<BoardVO> search(String searchOption, String keyword,
			int i, int j) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("firstRow", i);
		map.put("endRow",j);
		map.put("searchOption", searchOption);
		map.put("keyword",keyword);
		
		//1-2 글행목록 List<BoardVO> 리턴
		return sqlSessionTemplate.selectList("board_ns.selectAllBoards2",map);
		
	}
	
	
	
}
