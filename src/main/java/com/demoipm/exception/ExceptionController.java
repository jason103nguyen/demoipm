package com.demoipm.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler
	public String processExceptionDatabase(Exception ex, Model model) {
		
		model.addAttribute("msg", ex.getMessage());
		return "exception/error";
	}
	
	@ExceptionHandler
	public String processExceptionDatabase(DatabaseException ex, Model model) {
		
		model.addAttribute("msg", ex.getMessage());
		return "exception/error";
	}
}
