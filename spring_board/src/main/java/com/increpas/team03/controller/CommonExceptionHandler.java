package com.increpas.team03.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
//ControllerAdvice 주입으로 다수의 컨트롤러에서 발생하는 공통예외처리
//이 패키지 및 하위 패키지에 속한 컨트롤러에서 Exception 발생하면
//handleException()으로 예외 처리
//@ControllerAdvice("springapp.board")
@ControllerAdvice("com.increpas.team03.controller") 
public class CommonExceptionHandler {

	@ExceptionHandler(Exception.class)
	public String handleException() {
		return "error/common_exception";
	}
}
