package com.increpas.team03.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.log4j.Logger;
//import org.springframework.web.servlet.mvc.Controller;<-- 지우자 기존 Controller 충돌
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.increpas.team03.dao.MybatisBoardDAO;
import com.increpas.team03.model.BoardVOListModel;
import com.increpas.team03.model.BoardVOSearchModel;
import com.increpas.team03.service.BoardService;

@Controller
public class BoardSearchController {
	
	// 사용자 정의 로거 설정 : public static final 으로 보통 선언함. (한번만 설정하면 되므로) 
	// 로거 변수설정 <변수 설정을 해야 자동완성이 뜸>
	//public static final Logger logger = Logger.getLogger(BoardListController.class);//로거할 대상 클래스 설정
	
	
	// 사용할 빈객체를 스프링이 클래스타입으로
	// 판단하여 자동으로 주입(세터불필요)
	@Autowired
	private BoardService boardService;

	
	// RequestMapping메소드
	/*@RequestMapping("/board_list.do")
	public ModelAndView handleRequest() { // 모델과 연동
		
		BoardVOListModel boardList = boardService.getBoardVOList();
		return new ModelAndView("list", "boardList", boardList);
		
	}*/
	@RequestMapping("/search.do")
	public String handleRequest(@RequestParam("searchOption") String searchOption,
			@RequestParam(defaultValue = "")String keyword,
			String pn, Model model) { // 모델과 연동
		
		//logger.info("MYLOG : BoardListController ▷▷▷ board_list.do 실행");
		System.out.println("MYLOG : BoardListController ▷▷▷ board_list.do 실행");
		
		// 최종 모델 : BoardVOListModel, 페이지의 시작과 마지막 번호 // FC 에게 리턴
		int  requestPageNumber= Integer.parseInt(pn);
		
		// Service 메소드 호출 - 요청에 대한 결과모델 생성을 서비스에게 위임
		BoardVOSearchModel boardList 
		= boardService.getBoardVOList2(searchOption,keyword,requestPageNumber);
		
		model.addAttribute("listModel", boardList); // 글 내용을 저장
		
		
		// 페이지 네비게이션바 설정		
		if (boardList.getTotalPageCount() > 0) {//1 > 0
			
			// 리스트 화면의 페이지의 시작번호 //1-1 /10 =0 -> 0*10+1 =1
			int beginPageNumber = (boardList.getRequestPage() - 1) / 10 * 10 + 1;
			// 리스트 화면의 페이지의 마지막번호(기본 1...10)
			int endPageNumber = beginPageNumber + 9; //10
			if (endPageNumber > boardList.getTotalPageCount()) {//10 > 1
				endPageNumber = boardList.getTotalPageCount(); //1
			}
			
			model.addAttribute("beginPage", beginPageNumber);//글 시작페이지
			model.addAttribute("endPage", endPageNumber);
		}
		return "list";
		
	}
}
