package com.wesdm.springmvc.spittr;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Duplicate Spittle")
public class DuplicateSpittleException extends Exception {

}
