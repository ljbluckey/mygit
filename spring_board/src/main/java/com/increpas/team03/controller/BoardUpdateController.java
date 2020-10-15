package com.increpas.team03.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.increpas.team03.model.BoardVO;
import com.increpas.team03.service.BoardService;

@Controller
//Controller의 Session  
// SessionAttributes명과 같은 모델의 boardVO가 세션에 저장(다음페이지 상태 유지)
//1.@ModelAttribute 사용
//2. model.addAttribute()사용해서 객체 저장하는 경우 세션에 저장되도록 지정 가능

//등록 정보가 많아서 등록 폼 페이지 나누어지는 경우
//이전 이후를 오가면서 정보 유지 및 작성 가능 

//상세보기,수정,삭제시 글번호를 히든값이나 주소뒤에 붙이지 않고 
//세션에 저장해서 처리하는 것을 고려  
@SessionAttributes("boardVO")
public class BoardUpdateController {
	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/board_update.do", method = RequestMethod.GET)
	//public String handleRequest(@ModelAttribute("boardVO") BoardVO boardVO)//1. boardVO가 세션에 저장되록 지정
	public String handleRequest(@RequestParam String seq, Model model) {
		// TODO Auto-generated method stub
		// 수정할 글을 조회
		BoardVO vo = boardService.findBySeqBoard(seq);
		model.addAttribute("boardVO", vo); //2. boardVO가 세션에 저장되도록 지정
		// ModelAndView mav = new ModelAndView();
		// mav.setViewName("update");
		// return mav;
		return "update";
	}
}
