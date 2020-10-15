package com.increpas.team03.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.increpas.team03.dao.MybatisBoardDAO;
import com.increpas.team03.model.BoardVO;
import com.increpas.team03.model.BoardVOListModel;
import com.increpas.team03.model.BoardVOSearchModel;

/**
 * BO (Business Object)
 * - 서비스
 * - 트랜잭션
 */

@Service
public class BoardService {

	public static final int COUNT_PER_PAGE = 10;//2; // 하나의 페이지에 노출 시킬 글의 갯수(페이지 당 글 개수)

	@Autowired
	private MybatisBoardDAO boardDAO;

	
	
	/**
	 * 게시글 가져오기 
	 * @param requestPageNumber
	 * @return
	 */
	public BoardVOListModel getBoardVOList(int requestPageNumber) {
		
		// 페이징 처리
		if (requestPageNumber < 0) {
			throw new IllegalArgumentException("page number < 0 : "
					+ requestPageNumber);
		}

		// DAO에게 DB의 전체 글 개수조회 요청
		int totalBoardVOCount = boardDAO.selectCount();

		if (totalBoardVOCount == 0) {//글의 개수가 0이면
			
			//모델 : BoardVOListModel : 게시글 목록화면 VO 
			return new BoardVOListModel(); // AC에게 되돌려줄 모델(게시글 목록 화면) 리턴
		}

		int totalPageCount = calculateTotalPageCount(totalBoardVOCount);//1

		// 예) 시작글번호 (1-1) * 10 + 1 = 1 
		int firstRow = (requestPageNumber - 1) * COUNT_PER_PAGE + 1;
		// 예) 마지글번호 1 + (10 - 1) = 10
		int endRow = firstRow + COUNT_PER_PAGE - 1;

		// 예) 마지글번호 수정 10 > 2 ( 전체글수가 10보다 작거나 같으면) 전체글수 2
		if (endRow > totalBoardVOCount) {
			
			// 예) 전체글수 = 2
			endRow = totalBoardVOCount;
		}
		
		
		// DAO에게 DB Select 요청 글목록 리턴
		List<BoardVO> boardVOList = boardDAO.select(firstRow, endRow);

		
		//글목록 boardVOList을 BoardVOListModel 결과 모델로 생성
		//
		/**
		 * BoardVOListModel() 매개변수
		 * 	1. boardVOList : 글목록들
		 *  2. requestPageNumber : 1
		 *  3. totalPageCount : 1
		 *  4. firstRow : 1
		 *  5. endRow : 2
		 */
		BoardVOListModel boardVOListView = new BoardVOListModel(boardVOList,
				requestPageNumber, totalPageCount, firstRow, endRow);
		return boardVOListView;

	}

	
	
	private int calculateTotalPageCount(int totalBoardVOCount) {
		if (totalBoardVOCount == 0) {
			return 0;
		}
		
		// 글 갯수 ÷ 페이지당 글 갯수 : 예) 12/10 = 1 ,2/10 = 0 
		int pageCount = totalBoardVOCount / COUNT_PER_PAGE;
		
		//나머지(2)가 있으면 페이지수를 +1 증가
		if (totalBoardVOCount % COUNT_PER_PAGE > 0) {
			pageCount++;
		}
		
		return pageCount;
	}

	
	/**
	 * 상세보기 서비스
	 * 	- 업무로직
	 * 		1. 조회수 증가
	 * 		2. 글 리턴
	 * @param seq
	 * @return
	 */
	@Transactional	// 트랜잭션 처리
	public BoardVO findBySeqBoard(String seq) {		
		//조회수를 먼저 증가 시킨 후, 상세보기를 함.
		boardDAO.updateHitCount(seq);		
		return boardDAO.findBySeq(seq);
	}
	
	@Transactional //트랜잭션 테스트를 위해서 임시로 주석처리 → 트랜잭션이 각각 발생
	public void insertBoard(BoardVO vo) {
		
		/*트랜잭션 테스트를 위해서 임시로 주석처리*/ 
		 boardDAO.insert(vo);
		 
		
		
		
		/*트랜잭션 테스트를 위해서 임시 테스트 코딩 시작 
		vo.setSeq(200);
		boardDAO.insert(vo);
		boardDAO.insert(vo);
		트랜잭션 테스트를 위해서 임시 테스트 코딩 끝
		*/
	}
	
	@Transactional
	public void updateBoard(BoardVO vo) {
		boardDAO.update(vo);
	}
	
	@Transactional
	public void deleteBoard(String seq, String blevel) {
		boardDAO.delete(Integer.parseInt(seq), Integer.parseInt(blevel));
	}

	// ----------------------------------
	@Transactional
	public void insertReplyBoard(BoardVO vo) {		
		//boardDAO.insertReply(vo);
		boardDAO.replyUpdateBoard(vo);
		boardDAO.insertReply(vo);

	}



	public BoardVOSearchModel getBoardVOList2(String searchOption, 
			String keyword, int requestPageNumber) {
		// TODO Auto-generated method stub
		
		// DAO에게 DB Select 요청 글목록 리턴
		List<BoardVO> boardVOList 
		= boardDAO.search(searchOption,keyword,1, 10);
		// TODO Auto-generated method stub
		return null;
	}
}
