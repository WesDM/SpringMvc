package com.wesdm.springmvc.spittr;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	/**
	 * Handles DuplicateSpittleException for methods in this controller
	 * 
	 * @return
	 */
	@ExceptionHandler(DuplicateSpittleException.class)
	public String handleDuplicateSpittle() {
		return "error/duplicate";
	}
}
