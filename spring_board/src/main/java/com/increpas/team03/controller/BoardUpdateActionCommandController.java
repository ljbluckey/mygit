package com.increpas.team03.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.increpas.team03.dao.MybatisBoardDAO;
import com.increpas.team03.model.BoardVO;
import com.increpas.team03.service.BoardService;

@Controller
// //SessionAttributes명과 같은 모델애트리뷰의 인자에
// 세션의 boardVO를 저장(이전 페이지 상태 유지)
@SessionAttributes("boardVO") //세션에서 boardVO 검색 가져오기
public class BoardUpdateActionCommandController { // 수정폼으로 이동

	@Autowired
	private BoardService boardService;

	@ModelAttribute("modelMap") //ModelAttribute 메소드
	//RequestMapping 메소드 실행전에 자동 호출
	//뷰에 전달할 별도 공통 모델을 리턴값으로 지정
	//다양한 RequestMapping 메소드들의 공통 모델
	//그러면  리턴값이 model.addAttribute()자동 실행 스프링 모델에 저장 
	public Map<String,String> printBoardVO(HttpServletRequest request) {
		BoardVO vo = (BoardVO) request.getSession().getAttribute("boardVO");
		System.out.println("1. 세션에서 boardVO : " + vo);
		//리다이렉트 경우 비전달 
		Map<String,String> modelMap
		=new HashMap<String,String>();
		//jsp에서 ${modelMap.model1}
		modelMap.put("model1","부가공통뷰모델1");
		modelMap.put("model2","부가공통뷰모델2");
		return modelMap;
	}


	@RequestMapping("/board_update_action.do")//RequestMapping 메소드
	public String handle(HttpServletRequest request,@ModelAttribute BoardVO boardVO, 
			SessionStatus status) {
		//세션에서 가져온 boardVO가 boardVO 파라미터에 주입된후에 
		//수정된 글내용이 boardVO에  반영후 세션에 저장
		System.out.println("2. 수정된 boardVO : " + boardVO);
		BoardVO vo = (BoardVO) request.getSession().getAttribute("boardVO");
		System.out.println("3. 세션에서 boardVO : " + vo);
		boardService.updateBoard(boardVO);
		status.setComplete();// 세션의 데이터 boardVO 삭제
		vo = (BoardVO) request.getSession().getAttribute("boardVO");		
		// return new ModelAndView("redirect:/board_list.do"); //액션태그로도 가능
		return "redirect:board_list.do?pn=1";// 이동할 페이지로 기존 뷰리졸버방식아니라 상대경로(/생략)

	}

}
