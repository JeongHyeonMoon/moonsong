package com.nts.moonsong.common;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.nts.moonsong.common.exception.FileDeleteFailException;
import com.nts.moonsong.common.exception.InvalidAccessException;
import com.nts.moonsong.common.exception.LoginRequiredException;
import com.nts.moonsong.common.exception.NotExistElementException;
import com.nts.moonsong.common.exception.AuthorizationNotExistException;
import com.nts.moonsong.common.exception.DuplicateInsertException;

/**
 * @author Naver 송주용
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = {JsonParseException.class, JsonMappingException.class, IOException.class})
	public void handle400Exception() {}

	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = {FileDeleteFailException.class})
	public void handle500Exception() {}

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = {NotExistElementException.class})
	public void handle404Exception() {}

	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(value = {AuthorizationNotExistException.class, InvalidAccessException.class})
	public void handle401Exception() {}

	@ResponseStatus(code = HttpStatus.NON_AUTHORITATIVE_INFORMATION)
	@ExceptionHandler(value = {LoginRequiredException.class})
	public void handle203Exception() {}

	@ExceptionHandler(value = {DuplicateInsertException.class})
	public void handle512Exception(HttpServletResponse res) {
		res.setStatus(512);
	}
}
