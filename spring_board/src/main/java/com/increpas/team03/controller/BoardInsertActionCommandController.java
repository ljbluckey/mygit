package com.increpas.team03.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.validation.Errors;
import com.increpas.team03.model.BoardVO;
import com.increpas.team03.service.BoardService;

@Controller
@RequestMapping("/board_insert.do")
public class BoardInsertActionCommandController {
	private String formViewName = "insert";
	@Autowired
	private BoardService boardService;

	
	@RequestMapping(method = RequestMethod.GET)
	public String form() {
		return formViewName;
	}
	// 유효성 검사결과를 담은 errors 객체가 BindingResult 에 전달
		// 커맨드 객체(VO,DTO) 를 통한 업로드 파일 접근
	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(HttpServletRequest request, @Valid @ModelAttribute("board") BoardVO boardVO,Errors errors) {
		System.out.println(">>>> BoardInsertActionCommandController  : onSubmit");
		
		//MultipartRequest 처리를 해야 함.
	    //System.out.println(">>>>>> request.getAttribute(title): "+request.getAttribute("title")); //
		System.out.println(">>>>>>> boardVO:"+ boardVO.toString());
		
		if(errors.hasErrors()){
			return formViewName;
		}	
		
		try {
			//transferTo()예외
			// 파일업로드 로직
			MultipartFile mfile = boardVO.getMfile();
			System.out.println("mfile=" + mfile); //파일미선택도 객체 생성
			
			
			//1. 업된파일처리(0바이트 파일 비저장) 
			//2. 서비스 호출
			if (mfile != null && mfile.getSize() != 0) { 
				String fileName = mfile.getOriginalFilename();// 업된 파일명
				// 파일명을 VO UploadPath에  설정
				boardVO.setUploadPath(fileName);
				System.out.println(">>>>> 파일명을 VO에  설정");
				// upload폴더의 물리적인 폴더 절대경로
				String upath = "c:/images";
				System.out.println(upath);
				File file = new File(upath + "/" + fileName); // upload폴더 File 객체 생성
				mfile.transferTo(file); // 파일을  upload폴더로 복사
				System.out.println(fileName + " upath" + "에 저장");
				System.out.println("파일크기=" + mfile.getSize() + "바이트");
			}
			// Service 메소드 호출
			boardService.insertBoard(boardVO);
			System.out.println(">>>>> Service 메소드 호출");
			//RedirectView객체  뷰리졸버를 경유 X
			// 뷰정보(글목록 요청명)만 설정 ,모델에 출력결과 저장 X
			//return "redirect:board_list.do";
			return "redirect:board_list.do?pn=1"; 
			
		} catch (Exception e) {
			System.out.println("error:" + e.getMessage().toString());
			return formViewName;
		}
	}

	

}
