package com.oneblocks.configuration.web.bind.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.oneblocks.configuration.exception.BaseException;
import com.oneblocks.configuration.http.BaseResponse;

@ControllerAdvice
public class BaseControllerAdvice {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(value = { BaseException.class})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	private BaseResponse<?> HandleBaseExceoption(BaseException e, WebRequest request) {
		return new BaseResponse<String>(e.getResponseCode(), messageSource.getMessage(e.getResponseCode().name(),e.getArgs(),null));
	}
}
