package com.increpas.team03.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.increpas.team03.service.BoardService;

@Controller
public class BoardDeleteController {
	@Autowired
	private BoardService boardService;

	// 답글 삭제, 원글삭제시 답글들도 함께 삭제
	//aaaaaaaaaaaaa
	@RequestMapping("/board/board_delete.do")
	public String handleRequest(String seq, String blevel) {

		boardService.deleteBoard(seq, blevel);

		return "redirect:/board_list.do?pn=1"; // 리스트페이지 이동

	}

}
