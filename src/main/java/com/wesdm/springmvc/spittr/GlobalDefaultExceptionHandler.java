package com.wesdm.springmvc.spittr;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Applies exception handling functionality to all @RequestMapping methods
 * @author Wesley
 *
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	
	/**
	 * Handles DuplicateSpittleException for all controllers
	 * 
	 * @return
	 */
	@ExceptionHandler(DuplicateSpittleException.class)
	public String handleDuplicateSpittle() {
		return "error/duplicate";
	}
}
