package com.increpas.team03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.increpas.team03.model.BoardVO;
import com.increpas.team03.service.BoardService;

@Controller
public class BoardDetailController {
	@Autowired
	private BoardService boardService;

	// HttpServletRequest으로 서블릿 API 사용
	@RequestMapping("/board_detail.do")
	public String handleRequest(@RequestParam("seq") String seq, String pn, Model model) {

		BoardVO vo = boardService.findBySeqBoard(seq);
		// 엔터 개행문자 \n 을 웹브라우저에서는 <br>로 변환
		// 이기능을 별도의 유틸클래스로 분리 메소드로 정의하는 편이 좋다
		vo.setContent(vo.getContent().replace("\n", "<br>"));
		model.addAttribute("boardVO", vo);
	    System.out.println("boardVO=" + vo.hashCode());
		model.addAttribute("pn", pn); //페이지번호를 model에 저장
		return "detail";
	}
}
